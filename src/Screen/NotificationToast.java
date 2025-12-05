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

    private final int ALTURA = 75; 
    private int larguraDinamica;
    private float opacidade = 1.0f;
    private Timer timerAnimacao;
    private int xDestino;

    private final Color COR_FUNDO = new Color(28, 46, 74);
    private final Color COR_BORDA = new Color(45, 156, 219);
    private final Color COR_TEXTO = Color.WHITE;
    
    private final int PADDING_BORDA = 8; 

    public NotificationToast(Component componentePai, String mensagem) {
        setAlwaysOnTop(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BorderLayout());
        this.larguraDinamica = calcularLarguraIdeal(mensagem);
        setSize(larguraDinamica, ALTURA);

        JPanel contentPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(COR_FUNDO);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

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
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 15)); 

        GridBagConstraints gbc = new GridBagConstraints();

        ImageIcon iconOriginal = new ImageIcon(getClass().getResource("/images/Alert Notification Icon.png"));
        Image imgEscalada = iconOriginal.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(imgEscalada));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 12); 
        contentPanel.add(iconLabel, gbc);

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
        
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH; 
        contentPanel.add(textPane, gbc);

        add(contentPanel);
        
        setShape(new RoundRectangle2D.Double(0, 0, larguraDinamica, ALTURA, 20, 20));

        configurarPosicao(componentePai);
        
        iniciarAnimacao();
    }

    private int calcularLarguraIdeal(String msg) {
        Font font;
        try {
            font = FonteUtils.carregarRobotoMedium(13f);
        } catch (Exception e) {
            font = new Font("SansSerif", Font.PLAIN, 13);
        }
        
        Canvas c = new Canvas();
        FontMetrics fm = c.getFontMetrics(font);
        int larguraTexto = fm.stringWidth(msg);
        int total = 20 + 22 + 12 + larguraTexto + 30;
        
        return Math.max(250, Math.min(total, 500));
    }
    
    private void configurarPosicao(Component pai) {
        int margemDireita = 60;
        int topoY = 130;

        Dimension telaSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xBase;
        int yBase;

        if (pai != null && pai.isShowing()) {
            Point locPai = pai.getLocationOnScreen();
            xBase = (locPai.x + pai.getWidth()) - this.larguraDinamica - margemDireita;
            yBase = locPai.y + topoY;
        } else {
            xBase = telaSize.width - this.larguraDinamica - margemDireita;
            yBase = topoY;
        }

        int limiteDireitoMonitor = telaSize.width - this.larguraDinamica - margemDireita;
        xDestino = Math.min(xBase, limiteDireitoMonitor);
        
        setLocation(telaSize.width, yBase);
    }

    private void iniciarAnimacao() {
        timerAnimacao = new Timer(10, new ActionListener() {
            int velocidade = 25;
            @Override
            public void actionPerformed(ActionEvent e) {
                Point loc = getLocation();
                if (loc.x > xDestino) {
                    setLocation(loc.x - velocidade, loc.y);
                } else {
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