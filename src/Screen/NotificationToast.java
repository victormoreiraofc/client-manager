package screen;

import Screen.FonteUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class NotificationToast extends JWindow {

    // Altura fixa, mas largura será calculada
    private final int ALTURA = 75; 
    private int larguraDinamica; 
    
    private float opacidade = 1.0f;
    private Timer timerAnimacao;
    private int xDestino;

    // CORES
    private final Color COR_FUNDO = new Color(28, 46, 74);
    private final Color COR_BORDA = new Color(45, 156, 219);
    private final Color COR_TEXTO = Color.WHITE;
    
    private final int PADDING_BORDA = 8; 

    public NotificationToast(Component componentePai, String mensagem) {
        setAlwaysOnTop(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BorderLayout());

        // --- 1. CALCULAR LARGURA BASEADA NO TEXTO ---
        this.larguraDinamica = calcularLarguraIdeal(mensagem);
        setSize(larguraDinamica, ALTURA);

        // --- PAINEL DE FUNDO ---
        JPanel contentPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fundo Arredondado
                g2.setColor(COR_FUNDO);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Borda Interna
                g2.setColor(COR_BORDA);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(
                        PADDING_BORDA, 
                        PADDING_BORDA, 
                        getWidth() - (PADDING_BORDA * 2) - 1, 
                        getHeight() - (PADDING_BORDA * 2) - 1, 
                        15, 15
                );
                g2.dispose();
            }
        };
        contentPanel.setOpaque(false);
        // Margem interna para o conteúdo não tocar na borda azul
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 15)); 

        GridBagConstraints gbc = new GridBagConstraints();

        // --- 2. ÍCONE ---
        ImageIcon iconOriginal = new ImageIcon(getClass().getResource("/images/Alert Notification Icon.png"));
        Image imgEscalada = iconOriginal.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(imgEscalada));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 12); 
        contentPanel.add(iconLabel, gbc);

        // --- 3. TEXTO ---
        JTextPane textPane = new JTextPane();
        textPane.setText(mensagem);
        textPane.setEditable(false);
        textPane.setFocusable(false);
        textPane.setOpaque(false);
        textPane.setBackground(new Color(0, 0, 0, 0)); 
        textPane.setForeground(COR_TEXTO);
        textPane.setBorder(null);
        
        try {
            textPane.setFont(FonteUtils.carregarRobotoMedium(14f));
        } catch (Exception e) {
            textPane.setFont(new Font("SansSerif", Font.PLAIN, 13));
        }
        
        // Centralizar texto verticalmente
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH; 
        contentPanel.add(textPane, gbc);

        add(contentPanel);
        
        // Recorte para ficar arredondado no Windows
        setShape(new RoundRectangle2D.Double(0, 0, larguraDinamica, ALTURA, 20, 20));

        // --- 4. POSICIONAMENTO ---
        configurarPosicao(componentePai);
        
        iniciarAnimacao();
    }
    
    /**
     * Calcula a largura necessária baseada no tamanho da frase
     */
    private int calcularLarguraIdeal(String msg) {
        // Cria uma fonte temporária para medir
        Font font;
        try {
            font = FonteUtils.carregarRobotoMedium(13f);
        } catch (Exception e) {
            font = new Font("SansSerif", Font.PLAIN, 13);
        }
        
        Canvas c = new Canvas();
        FontMetrics fm = c.getFontMetrics(font);
        int larguraTexto = fm.stringWidth(msg);
        
        // Largura = PaddingEsquerdo(20) + Icone(22) + Gap(12) + Texto + PaddingDireito(30)
        int total = 20 + 22 + 12 + larguraTexto + 30;
        
        // Define limites: Mínimo 250px, Máximo 500px (para não ficar gigante)
        return Math.max(250, Math.min(total, 500));
    }
    
    private void configurarPosicao(Component pai) {
        // Ajustes finos solicitados
        int margemDireita = 60; // 10 a 15px da borda
        int topoY = 130;        // Desceu mais (antes era 100/110)

        Dimension telaSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xBase;
        int yBase;

        if (pai != null && pai.isShowing()) {
            Point locPai = pai.getLocationOnScreen();
            
            // LÓGICA DE CRESCIMENTO PARA A ESQUERDA:
            // Pega a borda DIREITA da janela pai e subtrai a largura (que varia)
            // BordaDireita = locPai.x + pai.getWidth()
            // PosicaoX = BordaDireita - LarguraDinamica - Margem
            xBase = (locPai.x + pai.getWidth()) - this.larguraDinamica - margemDireita;
            yBase = locPai.y + topoY;
        } else {
            // Se não tiver pai, usa o monitor
            xBase = telaSize.width - this.larguraDinamica - margemDireita;
            yBase = topoY;
        }

        // TRAVA DE SEGURANÇA:
        // Garante que não saia da tela do monitor pela direita
        int limiteDireitoMonitor = telaSize.width - this.larguraDinamica - margemDireita;
        
        // Usa o menor valor (o mais à esquerda), garantindo que fique dentro
        xDestino = Math.min(xBase, limiteDireitoMonitor);
        
        // Posição inicial da animação (escondido na direita)
        setLocation(telaSize.width, yBase);
    }

    private void iniciarAnimacao() {
        timerAnimacao = new Timer(10, new ActionListener() {
            int velocidade = 25;
            @Override
            public void actionPerformed(ActionEvent e) {
                Point loc = getLocation();
                if (loc.x > xDestino) {
                    // Animação vindo da direita
                    setLocation(loc.x - velocidade, loc.y);
                } else {
                    // Trava na posição exata
                    setLocation(xDestino, loc.y);
                    timerAnimacao.stop();
                    new Timer(5000, ev -> iniciarDesaparecimento()).start();
                }
            }
        });
        timerAnimacao.start();
    }

    private void iniciarDesaparecimento() {
        timerAnimacao = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opacidade -= 0.05f;
                if (opacidade <= 0) {
                    opacidade = 0;
                    timerAnimacao.stop();
                    dispose();
                }
                try {
                    setOpacity(opacidade);
                } catch (Exception ex) { dispose(); }
            }
        });
        timerAnimacao.start();
    }
}