package Screen;

import Data.CTCONTAB;
import Data.Cliente;
import Data.Usuario;
import Screen.FonteUtils;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border; 
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

        setSize(600, 180); 
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
        
        int paddingHorizontal = 25;

        gbc.gridy = 0; 
        gbc.gridx = 0; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.NORTHEAST; 
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, paddingHorizontal, 5, paddingHorizontal);
        
        JButton btnClose = criarBotaoFechar();
        panel.add(btnClose, gbc);

        gbc.gridy = 1; 
        gbc.gridx = 0; 
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 1.0; 
        gbc.insets = new Insets(0, 0, 0, 0); 
        
        JLabel lblMsg = new JLabel("Você tem certeza que deseja deletar esse cliente?");
        lblMsg.setFont(FonteUtils.carregarRobotoExtraBold(18f));
        lblMsg.setForeground(new java.awt.Color(236, 235, 235));
        panel.add(lblMsg, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 15, 0, 15);

        JPanel botoesWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoesWrapper.setOpaque(false);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); 
        botoes.setOpaque(false);

        JButton btnCancelar = new JButton("Cancelar") {
            @Override
            public Border getBorder() {
                return new EmptyBorder(0, 8, 0, 0); 
            }
            
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
                
                int x = 1;
                int y = 1;
                int w = getWidth() - 2;
                int h = getHeight() - 2;
                
                g2.drawRoundRect(x, y, w, h, 12, 12);
                g2.dispose();
            }
        };

        configurarEstiloBotao(btnCancelar);
        btnCancelar.setPreferredSize(new Dimension(100, 35));
        btnCancelar.setForeground(new Color(200, 200, 200));
        btnCancelar.addActionListener(e -> dispose());

        JButton btnExcluir = new JButton("Excluir") {
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
            }
        };

        configurarEstiloBotao(btnExcluir);
        btnExcluir.setPreferredSize(new Dimension(145, 35));
        btnExcluir.setForeground(new Color(20, 30, 50));
        btnExcluir.addActionListener(e -> confirmarExclusao());

        botoes.add(btnCancelar);
        botoes.add(btnExcluir);
        botoesWrapper.add(botoes);
        panel.add(botoesWrapper, gbc);

        panel.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }


    private JButton criarBotaoFechar() {
        JButton btnClose = new JButton() {
            private final Color defaultColor = new Color(28, 46, 74);
            private final Color borderColor = new Color(42, 62, 97);

            {
                setPreferredSize(new Dimension(30, 30)); 
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
                int arco = 10;

                g2.setColor(defaultColor);
                g2.fillRoundRect(0, 0, w, h, arco, arco);

                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(2.0f));
                g2.drawRoundRect(0, 0, w - 1, h - 1, arco, arco);

                g2.setColor(new Color(168, 178, 195));
                g2.setStroke(new BasicStroke(1.5f));
                int padding = 9; 

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
            
            if (usuarioLogado != null) {
                CTCONTAB.registrarAuditoria(usuarioLogado.getUsuario(), "Excluiu o cliente: " + clienteParaExcluir.getNome());
            }
            
            this.excluiu = true;
            dispose();
            
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