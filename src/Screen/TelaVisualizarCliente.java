package Screen;

import Data.Cliente;
import Data.Usuario;
import Screen.FonteUtils;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import Data.IconUtil;

public class TelaVisualizarCliente extends JDialog {

    private Usuario usuarioLogado;
    private Cliente clienteVisualizar;
    private JFrame parentFrame;
    private Component glassPaneOriginal;

    public TelaVisualizarCliente(JFrame parent, Usuario usuario, Cliente cliente) {
        super(parent, "Visualizar Cliente", ModalityType.APPLICATION_MODAL);
        this.parentFrame = parent;
        this.usuarioLogado = usuario;
        this.clienteVisualizar = cliente;

        setSize(900, 570);
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
        inicializarUI();
    }

    private void inicializarUI() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(28, 46, 74));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(42, 62, 97));
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }
        };

        panel.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.setLayout(new GridBagLayout());
        getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        int paddingHorizontal = 25;
        int paddingVerticalLabel = 6;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, paddingHorizontal, 8, paddingHorizontal);
        gbc.gridy = 0;

        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setOpaque(false);

        GridBagConstraints gbcHeader = new GridBagConstraints();

        gbcHeader.gridx = 0;
        gbcHeader.weightx = 1.0;
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Visualizar o cliente");
        lblTitulo.setFont(FonteUtils.carregarRobotoExtraBold(20f));
        lblTitulo.setForeground(new java.awt.Color(236, 235, 235));
        headerPanel.add(lblTitulo, gbcHeader);

        gbcHeader.gridx = 1;
        gbcHeader.weightx = 0;
        gbcHeader.fill = GridBagConstraints.NONE;
        JButton btnClose = criarBotaoFechar();
        headerPanel.add(btnClose, gbcHeader);

        panel.add(headerPanel, gbc);

        gbc.gridwidth = 2;
        gbc.insets = new Insets(paddingVerticalLabel, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy = 1;
        panel.add(createLabel("Nome Completo"), gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        panel.add(criarCampoTextoLeitura(clienteVisualizar.getNome()), gbc);

        gbc.insets = new Insets(paddingVerticalLabel, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        panel.add(createLabel("Serviço"), gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        panel.add(criarCampoTextoLeitura(clienteVisualizar.getServico()), gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.insets = new Insets(paddingVerticalLabel, paddingHorizontal, 2, 10);
        panel.add(createLabel("Tipo de Pessoa"), gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(paddingVerticalLabel, 10, 2, paddingHorizontal);
        panel.add(createLabel("Situação do Serviço"), gbc);

        gbc.gridy++;

        gbc.gridx = 0;
        gbc.insets = new Insets(2, paddingHorizontal, 5, 10);
        panel.add(criarCampoTextoLeitura(clienteVisualizar.getTipoPessoa()), gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(2, 10, 5, paddingHorizontal);
        panel.add(criarCampoTextoLeitura(clienteVisualizar.getSituacaoServico()), gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;

        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        panel.add(createLabel("Email"), gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        panel.add(criarCampoTextoLeitura(clienteVisualizar.getEmail()), gbc);

        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        panel.add(createLabel("Telefone"), gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        panel.add(criarCampoTextoLeitura(clienteVisualizar.getTelefone()), gbc);

        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        panel.add(createLabel("Celular"), gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        panel.add(criarCampoTextoLeitura(clienteVisualizar.getCelular()), gbc);

        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        panel.add(createLabel("Observações"), gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        JTextArea txtObs = criarAreaTextoLeitura(clienteVisualizar.getObservacoes());
        txtObs.setPreferredSize(new Dimension(txtObs.getPreferredSize().width, 70));
        JScrollPane scrollObs = new JScrollPane(txtObs);
        scrollObs.setBorder(null);
        scrollObs.setOpaque(false);
        scrollObs.getViewport().setOpaque(false);
        panel.add(scrollObs, gbc);

        panel.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
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

    private JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(new Color(168, 178, 195));
        l.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        return l;
    }

    private JTextField criarCampoTextoLeitura(String text) {
        JTextField t = new JTextField(text);
        t.setEditable(false);
        t.setBackground(new Color(25, 40, 65));
        t.setForeground(new Color(200, 200, 200));
        t.setFont(FonteUtils.carregarRalewayMedium(12f));
        t.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 70, 100)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return t;
    }

    private JTextArea criarAreaTextoLeitura(String text) {
        JTextArea t = new JTextArea(text);
        t.setEditable(false);
        t.setLineWrap(true);
        t.setWrapStyleWord(true);
        t.setBackground(new Color(25, 40, 65));
        t.setForeground(new Color(200, 200, 200));
        t.setFont(FonteUtils.carregarRalewayMedium(12f));
        t.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 70, 100)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return t;
    }

    private void aplicarEfeitoDesfoqueFundo() {
        if (parentFrame != null) {
            glassPaneOriginal = parentFrame.getGlassPane();
            try {
                Robot robot = new Robot();
                BufferedImage imagemCapturada = robot.createScreenCapture(parentFrame.getBounds());
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
                parentFrame.setGlassPane(blurPanel);
                blurPanel.setVisible(true);
            } catch (Exception e) {
            }
        }
    }

    private void removerEfeitoDesfoqueFundo() {
        if (parentFrame != null && glassPaneOriginal != null) {
            parentFrame.setGlassPane(glassPaneOriginal);
            glassPaneOriginal.setVisible(false);
        }
    }

    private BufferedImage aplicarBoxBlur(BufferedImage imagemOriginal, int raio) {
        if (imagemOriginal == null) {
            return null;
        }
        int tamanho = raio * 2 + 1;
        float peso = 1.0f / (tamanho * tamanho);
        float[] kernel = new float[tamanho * tamanho];
        for (int i = 0; i < kernel.length; i++) {
            kernel[i] = peso;
        }
        ConvolveOp op = new ConvolveOp(new Kernel(tamanho, tamanho, kernel), ConvolveOp.EDGE_NO_OP, null);
        return op.filter(imagemOriginal, null);
    }
}
