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

public class TelaAlterarSenha extends JDialog {

    private JPasswordField txtSenhaAtual;
    private JPasswordField txtNovaSenha;
    private JPasswordField txtConfirmarSenha;
    private Usuario usuarioLogado;
    private JFrame parentFrame;
    private Component glassPaneOriginal;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAlterarSenha.class.getName());

    // Mudei o parent para JFrame para garantir o funcionamento do GlassPane (Blur)
    public TelaAlterarSenha(JFrame parent, Usuario usuarioLogado) {
        super(parent, "Alterando sua senha:", ModalityType.APPLICATION_MODAL);
        this.usuarioLogado = usuarioLogado;
        this.parentFrame = parent;

        // Altura ajustada para 350px para caber os 3 campos confortavelmente
        // Largura mantida em 600px para consistência com a tela de email
        setSize(600, 335); 
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

        // --- PAINEL PRINCIPAL (Igual TelaAlterarEmail) ---
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
        JLabel lblTitulo = new JLabel("Alterando sua senha:");
        try {
            lblTitulo.setFont(FonteUtils.carregarRobotoExtraBold(20f));
        } catch (Exception e) { lblTitulo.setFont(new Font("Arial", Font.BOLD, 20)); }
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo, gbc);

        // --- Senha Atual (Label) ---
        gbc.insets = new Insets(4, 25, 2, 25);
        gbc.gridy++;
        JLabel lblSenhaAtual = new JLabel("Senha Atual");
        lblSenhaAtual.setForeground(new Color(180, 200, 230)); 
        configurarFonteLabel(lblSenhaAtual);
        panel.add(lblSenhaAtual, gbc);

        // --- Senha Atual (Field) ---
        gbc.insets = new Insets(2, 25, 6, 25);
        gbc.gridy++;
        txtSenhaAtual = criarCampoSenha();
        panel.add(txtSenhaAtual, gbc);

        // --- Nova Senha (Label) ---
        gbc.insets = new Insets(4, 25, 2, 25);
        gbc.gridy++;
        JLabel lblNovaSenha = new JLabel("Nova Senha");
        lblNovaSenha.setForeground(new Color(180, 200, 230)); 
        configurarFonteLabel(lblNovaSenha);
        panel.add(lblNovaSenha, gbc);

        // --- Nova Senha (Field) ---
        gbc.insets = new Insets(2, 25, 6, 25);
        gbc.gridy++;
        txtNovaSenha = criarCampoSenha();
        panel.add(txtNovaSenha, gbc);

        // --- Confirmar Nova Senha (Label) ---
        gbc.insets = new Insets(4, 25, 2, 25);
        gbc.gridy++;
        JLabel lblConfirmarSenha = new JLabel("Confirmar Nova Senha");
        lblConfirmarSenha.setForeground(new Color(180, 200, 230)); 
        configurarFonteLabel(lblConfirmarSenha);
        panel.add(lblConfirmarSenha, gbc);

        // --- Confirmar Nova Senha (Field) ---
        gbc.insets = new Insets(2, 25, 15, 25);
        gbc.gridy++;
        txtConfirmarSenha = criarCampoSenha();
        panel.add(txtConfirmarSenha, gbc);

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
        btnAtualizar.addActionListener(e -> atualizarSenha());

        botoes.add(btnCancelar);
        botoes.add(btnAtualizar);
        panel.add(botoes, gbc);

        // ESC fecha a janela
        panel.registerKeyboardAction(e -> dispose(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    // --- Métodos Auxiliares de UI (Idênticos ao TelaAlterarEmail) ---

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

    private JPasswordField criarCampoSenha() {
        JPasswordField txt = new JPasswordField();
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

    // --- Lógica de Negócio (Preservada) ---

    private void atualizarSenha() {
        if (usuarioLogado == null) return;
        MDC.put("usuario", usuarioLogado.getUsuario());

        String senhaAtual = new String(txtSenhaAtual.getPassword()).trim();
        String senhaNova = new String(txtNovaSenha.getPassword()).trim();
        String senhaConfirmar = new String(txtConfirmarSenha.getPassword()).trim();

        if (senhaAtual.isEmpty() || senhaNova.isEmpty() || senhaConfirmar.isEmpty()) {
            MensagemUtil.exibirErro("Preencha todos os campos!");
            return;
        }

        if (!senhaNova.equals(senhaConfirmar)) {
            MensagemUtil.exibirErro("As senhas não coincidem!");
            return;
        }

        if (senhaNova.length() < 4) {
            MensagemUtil.exibirErro("A nova senha deve ter pelo menos 4 caracteres!");
            return;
        }

        try {
            // Busca a senha atual direto do banco
            String senhaBanco = CTCONTAB.buscarSenhaPorEmail(usuarioLogado.getEmail());

            if (!senhaAtual.equals(senhaBanco)) {
                MensagemUtil.exibirErro("A senha atual está incorreta!");
                return;
            }

            // Atualiza no banco
            CTCONTAB.atualizarSenhaUsuario(usuarioLogado.getEmail(), senhaNova);
            MensagemUtil.exibirSucesso("Senha alterada com sucesso!");
            logger.info("Usuário alterou a senha com sucesso: " + usuarioLogado.getUsuario());
            dispose();

        } catch (SQLException e) {
            MensagemUtil.exibirErro("Erro ao atualizar senha: " + e.getMessage());
            logger.log(Level.SEVERE, "SQL Error", e);
        } catch (ClassNotFoundException e) {
            MensagemUtil.exibirErro("Erro ao acessar o banco de dados.");
            logger.log(Level.SEVERE, "DB Connection Error", e);
        }
    }
}