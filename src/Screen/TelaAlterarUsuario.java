package screen;

import Data.Usuario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.logging.Level;
import org.slf4j.MDC;
import Screen.FonteUtils;
// Importação simulada para classes de dados/conexão

public class TelaAlterarUsuario extends JDialog {

    private JTextField txtUsuarioAtual;
    private JTextField txtNovoUsuario;
    private Usuario usuarioLogado;
    private JFrame parentFrame;
    private Component glassPaneOriginal;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAlterarUsuario.class.getName());

    public TelaAlterarUsuario(JFrame parent, Usuario usuarioLogado) {
        super(parent, "Alterando seu nome de usuário:", ModalityType.APPLICATION_MODAL);
        this.usuarioLogado = usuarioLogado;
        this.parentFrame = parent;

        // Dimensões ligeiramente menores, pois só há 2 campos.
        setSize(600, 283); 
        setLocationRelativeTo(parent);
        setUndecorated(true);
        setResizable(false);

        aplicarEfeitoDesfoqueFundo();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                removerEfeitoDesfoqueFundo();
            }
        });

        setBackground(new Color(0, 0, 0, 0));

        // --- PAINEL PRINCIPAL (Fundo arredondado e borda) ---
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int arco = 20;

                // Fundo Escuro (Cor 28, 46, 74)
                g2.setColor(new Color(28, 46, 74));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arco, arco);

                // Borda Sutil (Cor 42, 62, 97)
                g2.setColor(new Color(42, 62, 97));
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arco, arco);

                g2.dispose();
            }
        };

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;

        // --- Título e Botão Fechar (ROW 0) ---
        gbc.insets = new Insets(10, 25, 15, 15); 
        gbc.gridy = 0;
        
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setOpaque(false);
        
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.insets = new Insets(0, 0, 0, 0);

        // 1. Título
        gbcHeader.gridx = 0;
        gbcHeader.weightx = 1.0;
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblTitulo = new JLabel("Alterando seu nome de usuário:");
        try {
            lblTitulo.setFont(FonteUtils.carregarRobotoExtraBold(20f));
        } catch (Exception e) { lblTitulo.setFont(new Font("Arial", Font.BOLD, 20)); }
        lblTitulo.setForeground(Color.WHITE);
        headerPanel.add(lblTitulo, gbcHeader);
        
        // 2. Botão Fechar (Customizado)
        gbcHeader.gridx = 1;
        gbcHeader.weightx = 0;
        gbcHeader.fill = GridBagConstraints.NONE;
        JButton btnClose = criarBotaoFechar(); 
        headerPanel.add(btnClose, gbcHeader);

        panel.add(headerPanel, gbc);

        // --- Usuário atual (Label) ---
        gbc.insets = new Insets(4, 25, 2, 25);
        gbc.gridy = 1; 
        JLabel lblUsuarioAtual = new JLabel("Usuário Atual");
        lblUsuarioAtual.setForeground(new Color(180, 200, 230));
        configurarFonteLabel(lblUsuarioAtual);
        panel.add(lblUsuarioAtual, gbc);

        // --- Usuário atual (Field) ---
        gbc.insets = new Insets(2, 25, 6, 25);
        gbc.gridy++;
        txtUsuarioAtual = criarCampoTexto();
        txtUsuarioAtual.setText(usuarioLogado != null ? usuarioLogado.getUsuario() : "");
        txtUsuarioAtual.setEditable(false);
        txtUsuarioAtual.setForeground(Color.GRAY);
        panel.add(txtUsuarioAtual, gbc);

        // --- Novo Usuário (Label) ---
        gbc.insets = new Insets(4, 25, 2, 25);
        gbc.gridy++;
        JLabel lblNovoUsuario = new JLabel("Novo Nome de Usuário");
        lblNovoUsuario.setForeground(new Color(180, 200, 230));
        configurarFonteLabel(lblNovoUsuario);
        panel.add(lblNovoUsuario, gbc);

        // --- Novo Usuário (Field) ---
        gbc.insets = new Insets(2, 25, 15, 25);
        gbc.gridy++;
        txtNovoUsuario = criarCampoTexto();
        panel.add(txtNovoUsuario, gbc);

        // --- PAINEL DE BOTÕES ---
        gbc.insets = new Insets(5, 15, 15, 15);
        gbc.gridy++;
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0)); 
        botoes.setOpaque(false);

        // ==========================================
        //         BOTÃO CANCELAR (Ghost Style)
        // ==========================================
        JButton btnCancelar = new JButton("Cancelar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(28, 46, 74)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g2); 
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(65, 85, 115)); 
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
                g2.dispose();
            }
        };
        
        configurarEstiloBotao(btnCancelar);
        btnCancelar.setPreferredSize(new Dimension(100, 35)); 
        btnCancelar.setForeground(new Color(200, 200, 200));
        btnCancelar.addActionListener(e -> dispose());

        // ==========================================
        //         BOTÃO ATUALIZAR (Solid Blue)
        // ==========================================
        JButton btnAtualizar = new JButton("Atualizar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(45, 156, 219)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g2);
                g2.dispose();
            }
            
            @Override
            protected void paintBorder(Graphics g) {}
        };

        configurarEstiloBotao(btnAtualizar);
        btnAtualizar.setPreferredSize(new Dimension(145, 35)); 
        btnAtualizar.setForeground(new Color(20, 30, 50)); 
        btnAtualizar.addActionListener(e -> atualizarUsuario());

        botoes.add(btnCancelar);
        botoes.add(btnAtualizar);
        panel.add(botoes, gbc);

        panel.registerKeyboardAction(e -> dispose(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    // =========================================================================
    //                            MÉTODOS AUXILIARES DE UI
    // =========================================================================

    /**
     * Cria e estiliza o botão de fechar ('X') com o novo padrão:
     * Quadrado 35x35, arco 12, borda 2.0f (Cor 42, 62, 97), ícone 1.5f (Cor 168, 178, 195).
     */
    private JButton criarBotaoFechar() {
        JButton btnClose = new JButton() {
            private final Color defaultColor = new Color(28, 46, 74); 
            private final Color borderColor = new Color(42, 62, 97); // Cor da borda
            private final Color xColor = new Color(168, 178, 195); // Cor do 'X'

            {
                setPreferredSize(new Dimension(35, 35)); 
                setOpaque(false);
                setContentAreaFilled(false);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setBorder(BorderFactory.createEmptyBorder());
                setFocusPainted(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int w = getWidth();
                int h = getHeight();
                int arco = 12; 

                // 1. Fundo 
                g2.setColor(defaultColor);
                g2.fillRoundRect(0, 0, w, h, arco, arco);
                
                // 2. Borda do Botão (Grossura: 2.0f)
                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(2.0f)); 
                g2.drawRoundRect(0, 0, w - 1, h - 1, arco, arco);

                // 3. Desenha o 'X'
                g2.setColor(xColor);
                g2.setStroke(new BasicStroke(1.5f)); 
                int padding = 12; 

                g2.drawLine(padding, padding, w - padding, h - padding);
                g2.drawLine(w - padding, padding, padding, h - padding);

                g2.dispose();
            }
        };

        btnClose.addActionListener(e -> dispose());
        return btnClose;
    }

    private void configurarFonteLabel(JLabel label) {
        try {
            label.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        } catch (Exception e) {
            label.setFont(new Font("Arial", Font.PLAIN, 13));
        }
    }

    private void configurarEstiloBotao(JButton btn) {
        btn.setUI(new BasicButtonUI());
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        try {
            btn.setFont(FonteUtils.carregarRalewayMedium(13f));
        } catch (Exception e) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        }
    }

    private JTextField criarCampoTexto() {
        JTextField txt = new JTextField();
        txt.setBackground(new Color(25, 40, 65));
        txt.setForeground(Color.WHITE);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setCaretColor(Color.WHITE);
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 70, 100)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return txt;
    }
    
    // =========================================================================
    //                             LÓGICA DO BLUR
    // =========================================================================

    private void aplicarEfeitoDesfoqueFundo() {
        if (parentFrame != null) {
            glassPaneOriginal = parentFrame.getGlassPane();
            BufferedImage imagemCapturada = null;
            try {
                Robot robot = new Robot();
                Rectangle bounds = parentFrame.getBounds();
                imagemCapturada = robot.createScreenCapture(bounds);
            } catch (AWTException e) {
                logger.log(Level.SEVERE, "Erro ao capturar tela", e);
                return;
            }

            final BufferedImage imagemDesfocada = aplicarBoxBlur(imagemCapturada, 3);

            JPanel blurPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    if (imagemDesfocada != null) {
                        g.drawImage(imagemDesfocada, 0, 0, getWidth(), getHeight(), this);
                    }
                    g.setColor(new Color(0, 0, 0, 80));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            };
            blurPanel.setLayout(null);
            blurPanel.setOpaque(true);
            blurPanel.addMouseListener(new MouseAdapter() {});
            parentFrame.setGlassPane(blurPanel);
            blurPanel.setVisible(true);
        }
    }

    private void removerEfeitoDesfoqueFundo() {
        if (parentFrame != null && glassPaneOriginal != null) {
            parentFrame.setGlassPane(glassPaneOriginal);
            glassPaneOriginal.setVisible(false);
            parentFrame.getContentPane().revalidate();
            parentFrame.getContentPane().repaint();
        }
    }

    private BufferedImage aplicarBoxBlur(BufferedImage imagemOriginal, int raio) {
        if (imagemOriginal == null) return null;
        int tamanho = raio * 2 + 1;
        float peso = 1.0f / (tamanho * tamanho);
        float[] kernel = new float[tamanho * tamanho];
        for (int i = 0; i < kernel.length; i++) kernel[i] = peso;
        ConvolveOp op = new ConvolveOp(new Kernel(tamanho, tamanho, kernel), ConvolveOp.EDGE_NO_OP, null);
        return op.filter(imagemOriginal, null);
    }

    // =========================================================================
    //                            LÓGICA DE NEGÓCIO
    // =========================================================================
    
    private void atualizarUsuario() {
        if (usuarioLogado == null) return;
        MDC.put("usuario", usuarioLogado.getUsuario());
        String usuarioAtual = txtUsuarioAtual.getText().trim();
        String usuarioNovo = txtNovoUsuario.getText().trim();

        if (usuarioNovo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite o novo nome de usuário.");
            return;
        }

        try {
            if (!usuarioNovo.equals(usuarioAtual)) {
                // Simulação da atualização no objeto/banco de dados
                usuarioLogado.setUsuario(usuarioNovo); 
                
                JOptionPane.showMessageDialog(this, "Nome de usuário alterado com sucesso!");
                logger.info("Usuário alterado de " + usuarioAtual + " para " + usuarioNovo);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "O novo nome de usuário é igual ao atual.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao atualizar", e);
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }
}