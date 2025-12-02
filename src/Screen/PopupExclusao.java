package Screen;

import Data.CTCONTAB;
import Data.Cliente;
import Data.Usuario;
import Screen.FonteUtils; // Ajuste se seu pacote for 'Screen' ou 'screen' (no seu código varia)
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

public class PopupExclusao extends JDialog {

    private Usuario usuarioLogado;
    private Cliente clienteParaExcluir;
    private JFrame parentFrame;
    private Component glassPaneOriginal;
    private boolean excluiu = false;

    public PopupExclusao(JFrame parent, Usuario usuarioLogado, Cliente cliente) {
        super(parent, "Confirmação", ModalityType.APPLICATION_MODAL);
        this.parentFrame = parent;
        this.usuarioLogado = usuarioLogado;
        this.clienteParaExcluir = cliente;

        // Tamanho similar ao da imagem enviada
        setSize(600, 200);
        setLocationRelativeTo(parent);
        setUndecorated(true);
        setResizable(false);
        setBackground(new Color(0, 0, 0, 0));

        aplicarEfeitoDesfoqueFundo();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                removerEfeitoDesfoqueFundo();
            }
        });

        inicializarUI();
    }

    public boolean isExcluiu() {
        return excluiu;
    }

    private void inicializarUI() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(28, 46, 74)); // Fundo Escuro
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(42, 62, 97)); // Borda
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }
        };
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
       // Espaçamento horizontal idêntico à TelaAdicionarCliente
        int paddingHorizontal = 25;

        // --- ROW 0: HEADER (Título e Fechar) ---
        gbc.gridy = 0; gbc.gridx = 0; gbc.gridwidth = 2;
        // Insets idênticos para posicionar o 'X' corretamente
        gbc.insets = new Insets(2, paddingHorizontal, 8, paddingHorizontal);
        
         JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setOpaque(false);

        GridBagConstraints gbcHeader = new GridBagConstraints();

        gbcHeader.gridx = 0;
        gbcHeader.weightx = 1.0;
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblMsg = new JLabel("Você tem certeza que deseja deletar esse cliente?");
        lblMsg.setFont(FonteUtils.carregarRobotoExtraBold(18f));
        lblMsg.setForeground(new java.awt.Color(236, 235, 235));
        headerPanel.add(lblMsg, gbcHeader);

        gbcHeader.gridx = 1;
        gbcHeader.weightx = 0;
        gbcHeader.fill = GridBagConstraints.NONE;
        JButton btnClose = criarBotaoFechar();
        headerPanel.add(btnClose, gbcHeader);

        panel.add(headerPanel, gbc);
        
        // --- PAINEL DE BOTÕES (ROW 8) ---
        // AJUSTE: Padding inferior reduzido de 3 para 1
        gbc.insets = new Insets(5, 15, 1, 15);
        gbc.gridy++;

        JPanel botoesWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoesWrapper.setOpaque(false);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        botoes.setOpaque(false);

        // ==========================================
        // BOTÃO CANCELAR
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
        // BOTÃO CRIAR CLIENTE
        // ==========================================
        JButton btnCriarCliente = new JButton("Excluir") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(17, 168, 100));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // Sem borda
            }
        };

        configurarEstiloBotao(btnCriarCliente);
        btnCriarCliente.setPreferredSize(new Dimension(145, 35));
        btnCriarCliente.setForeground(new Color(20, 30, 50));
        btnCriarCliente.addActionListener(e -> confirmarExclusao());

        botoes.add(btnCancelar);
        botoes.add(btnCriarCliente);
        botoesWrapper.add(botoes);
        panel.add(botoesWrapper, gbc);

        // ESC fecha a janela
        panel.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }


 private JButton criarBotaoFechar() {
        JButton btnClose = new JButton() {
            private final Color defaultColor = new Color(28, 46, 74);
            private final Color borderColor = new Color(42, 62, 97);

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

                g2.setColor(defaultColor);
                g2.fillRoundRect(0, 0, w, h, arco, arco);

                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(2.0f));
                g2.drawRoundRect(0, 0, w - 1, h - 1, arco, arco);

                g2.setColor(new Color(168, 178, 195));
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


    private void confirmarExclusao() {
        try {
            CTCONTAB.excluirRegistro("cliente", "ID", clienteParaExcluir.getId());
            
            // Auditoria/Notificação
            if (usuarioLogado != null) {
                CTCONTAB.registrarAuditoria(usuarioLogado.getUsuario(), "Excluiu o cliente: " + clienteParaExcluir.getNome());
            }
            
            this.excluiu = true;
            dispose();
            
            // Popup de Sucesso (Toast)
            //new NotificationToast(null, "Cliente deletado com sucesso!").setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
        }
    }
    
     private void configurarEstiloBotao(JButton btn) {
        btn.setUI(new BasicButtonUI());
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(FonteUtils.carregarRalewayMedium(13f));
    }

    // --- Métodos de Blur (Padrão do seu projeto) ---
    private void aplicarEfeitoDesfoqueFundo() {
        if (parentFrame != null) {
            glassPaneOriginal = parentFrame.getGlassPane();
            try {
                Robot robot = new Robot();
                Rectangle bounds = parentFrame.getBounds();
                BufferedImage imagemCapturada = robot.createScreenCapture(bounds);
                final BufferedImage imagemDesfocada = aplicarBoxBlur(imagemCapturada, 3);

                JPanel blurPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        if (imagemDesfocada != null) g.drawImage(imagemDesfocada, 0, 0, getWidth(), getHeight(), this);
                        g.setColor(new Color(0, 0, 0, 80));
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                };
                parentFrame.setGlassPane(blurPanel);
                blurPanel.setVisible(true);
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    private void removerEfeitoDesfoqueFundo() {
        if (parentFrame != null && glassPaneOriginal != null) {
            parentFrame.setGlassPane(glassPaneOriginal);
            glassPaneOriginal.setVisible(false);
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
}