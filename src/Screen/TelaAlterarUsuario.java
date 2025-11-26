package screen;

import Data.CTCONTAB;
import Data.Usuario;
import Screen.FonteUtils;
import Screen.MensagemUtil;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.sql.SQLException;
import java.util.logging.Level;
import org.slf4j.MDC;

public class TelaAlterarUsuario extends JDialog {

    private JTextField txtNomeAtual;
    private JTextField txtNovoNome;
    private Usuario usuarioLogado;
    private JFrame parentFrame;
    private Component glassPaneOriginal;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAlterarUsuario.class.getName());

    public TelaAlterarUsuario(JFrame parent, Usuario usuarioLogado) {
        super(parent, "Alterando seu usuário atual:", ModalityType.APPLICATION_MODAL);
        this.usuarioLogado = usuarioLogado;
        this.parentFrame = parent;

        // Mesma dimensão da tela de E-mail (pois tem a mesma qtd de campos)
        setSize(600, 270); 
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

        // --- PAINEL PRINCIPAL ---
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int arco = 20;

                // Fundo Escuro
                g2.setColor(new Color(28, 46, 74));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arco, arco);

                // Borda Sutil
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

        // --- Título ---
        gbc.insets = new Insets(10, 25, 15, 25);
        gbc.gridy = 0;
        JLabel lblTitulo = new JLabel("Alterando seu usuário atual:");
        try {
            lblTitulo.setFont(FonteUtils.carregarRobotoExtraBold(20f));
        } catch (Exception e) { lblTitulo.setFont(new Font("Arial", Font.BOLD, 20)); }
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo, gbc);

        // --- Nome Atual (Label) ---
        gbc.insets = new Insets(4, 25, 2, 25);
        gbc.gridy++;
        JLabel lblNomeAtual = new JLabel("Nome Atual");
        lblNomeAtual.setForeground(new Color(180, 200, 230)); 
        configurarFonteLabel(lblNomeAtual);
        panel.add(lblNomeAtual, gbc);

        // --- Nome Atual (Field) ---
        gbc.insets = new Insets(2, 25, 6, 25);
        gbc.gridy++;
        txtNomeAtual = criarCampoTexto();
        txtNomeAtual.setText(usuarioLogado.getUsuario());
        txtNomeAtual.setEditable(false);
        txtNomeAtual.setForeground(Color.GRAY); // Visual de "apenas leitura"
        panel.add(txtNomeAtual, gbc);

        // --- Novo Nome (Label) ---
        gbc.insets = new Insets(4, 25, 2, 25);
        gbc.gridy++;
        JLabel lblNovoNome = new JLabel("Novo Nome");
        lblNovoNome.setForeground(new Color(180, 200, 230)); 
        configurarFonteLabel(lblNovoNome);
        panel.add(lblNovoNome, gbc);

        // --- Novo Nome (Field) ---
        gbc.insets = new Insets(2, 25, 15, 25);
        gbc.gridy++;
        txtNovoNome = criarCampoTexto();
        panel.add(txtNovoNome, gbc);

        // --- PAINEL DE BOTÕES ---
        gbc.insets = new Insets(5, 15, 15, 15);
        gbc.gridy++;
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        botoes.setOpaque(false);

        // ==========================================
        //         BOTÃO CANCELAR
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
        btnCancelar.setPreferredSize(new Dimension(100, 35)); // Largura MENOR
        btnCancelar.setForeground(new Color(200, 200, 200));
        btnCancelar.addActionListener(e -> dispose());

        // ==========================================
        //         BOTÃO ATUALIZAR
        // ==========================================
        JButton btnAtualizar = new JButton("Atualizar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Azul Vibrante
                g2.setColor(new Color(45, 156, 219)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

                super.paintComponent(g2);
                g2.dispose();
            }
            
            @Override
            protected void paintBorder(Graphics g) {
                // Sem borda
            }
        };

        configurarEstiloBotao(btnAtualizar);
        btnAtualizar.setPreferredSize(new Dimension(145, 35)); // Largura MAIOR
        btnAtualizar.setForeground(new Color(20, 30, 50)); 
        btnAtualizar.addActionListener(e -> atualizarUsuario());

        botoes.add(btnCancelar);
        botoes.add(btnAtualizar);
        panel.add(botoes, gbc);

        // ESC fecha a janela
        panel.registerKeyboardAction(e -> dispose(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    // --- Métodos Auxiliares de UI ---

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

    // --- Lógica de Negócio ---

    private void atualizarUsuario() {
        MDC.put("usuario", usuarioLogado.getUsuario());

        String nomeAtual = txtNomeAtual.getText().trim();
        String nomeNovo = txtNovoNome.getText().trim();

        if (nomeNovo.isEmpty()) {
            MensagemUtil.exibirErro("Digite o novo nome de usuário!");
            return;
        }

        if (!nomeNovo.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            MensagemUtil.exibirErro("O nome de usuário só pode conter letras!");
            return;
        }

        if (nomeNovo.equals(nomeAtual)) {
            MensagemUtil.exibirErro("O novo nome deve ser diferente do atual!");
            return;
        }

        try {
            CTCONTAB.atualizarNomeUsuario(nomeAtual, nomeNovo);
            usuarioLogado.setUsuario(nomeNovo);
            MensagemUtil.exibirSucesso("Nome de usuário atualizado com sucesso!");
            logger.info("Usuário alterou o nome de " + nomeAtual + " para " + nomeNovo);
            dispose();
        } catch (SQLException e) {
            if (e.getMessage().contains("O nome de usuário já está em uso") || e.getMessage().contains("Duplicate entry")) {
                MensagemUtil.exibirErro("Este nome de usuário já está sendo usado. Escolha outro.");
            } else {
                MensagemUtil.exibirErro("Erro: " + e.getMessage());
                logger.log(Level.SEVERE, "Erro SQL", e);
            }
        } catch (ClassNotFoundException e) {
            MensagemUtil.exibirErro("Erro ao atualizar o nome de usuário.");
            logger.log(Level.SEVERE, "Erro Driver", e);
        }
    }
}