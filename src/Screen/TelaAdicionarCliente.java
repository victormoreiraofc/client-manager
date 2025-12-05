package screen;

import Data.Usuario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.logging.Level;
import org.slf4j.MDC;
import Screen.FonteUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import Data.CTCONTAB;
import Data.Cliente;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaAdicionarCliente extends JDialog {

    private JTextField txtNomeCompleto;
    private JTextField txtServico;
    private JTextField txtTipoPessoa;
    private JTextField txtSituacaoServico;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JTextField txtCelular;
    private JTextArea txtObservacoes;

    private JLabel lblErroTipoPessoa;
    private JLabel lblErroSituacaoServico;

    private Usuario usuarioLogado;
    private JFrame parentFrame;
    private Component glassPaneOriginal;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAdicionarCliente.class.getName());

    private static final Set<String> OPCOES_TIPO_PESSOA = new HashSet<>(Arrays.asList("FISICA", "JURIDICA", "NI"));
    private static final Set<String> OPCOES_SITUACAO_SERVICO = new HashSet<>(Arrays.asList("PENDENTE", "EM ANDAMENTO", "CONCLUIDO"));

    public TelaAdicionarCliente(JFrame parent, Usuario usuarioLogado) {
        super(parent, "Adicionar um novo cliente", ModalityType.APPLICATION_MODAL);
        this.parentFrame = parent;
        this.usuarioLogado = usuarioLogado;
        inicializarUI();
    }

    public TelaAdicionarCliente(JFrame parent) {
        this(parent, null);
    }

    private void inicializarUI() {
        setSize(900, 630);
        setLocationRelativeTo(parentFrame);
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

        JPanel panel = new JPanel() {
            private final int ARCO = 20;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(28, 46, 74));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARCO, ARCO);
                g2.setColor(new Color(42, 62, 97));
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARCO, ARCO);
                g2.dispose();
            }
        };

        panel.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.setLayout(new GridBagLayout());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        int paddingHorizontal = 25;
        int paddingVerticalLabel = 6;
        int paddingVerticalField = 10;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, paddingHorizontal, 8, 15);
        gbc.gridy = 0;

        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setOpaque(false);

        GridBagConstraints gbcHeader = new GridBagConstraints();

        gbcHeader.gridx = 0;
        gbcHeader.weightx = 1.0;
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Adicionar um novo cliente");
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
        JLabel lblNomeCompleto = new JLabel("Nome Completo*");
        configurarLabel(lblNomeCompleto);
        panel.add(lblNomeCompleto, gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtNomeCompleto = criarCampoTexto();
        addPlaceholder(txtNomeCompleto, "Nome Completo");
        panel.add(txtNomeCompleto, gbc);

        gbc.insets = new Insets(paddingVerticalLabel, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        JLabel lblServico = new JLabel("Serviço*");
        configurarLabel(lblServico);
        panel.add(lblServico, gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtServico = criarCampoTexto();
        addPlaceholder(txtServico, "Qual foi o serviço prestado?");
        panel.add(txtServico, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(paddingVerticalLabel, paddingHorizontal, 2, 10);
        gbc.gridy++;
        JLabel lblTipoPessoa = new JLabel("Tipo de Pessoa*");
        configurarLabel(lblTipoPessoa);
        panel.add(lblTipoPessoa, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(paddingVerticalLabel, 10, 2, paddingHorizontal);
        JLabel lblSituacaoServico = new JLabel("Situação do Serviço*");
        configurarLabel(lblSituacaoServico);
        panel.add(lblSituacaoServico, gbc);

        gbc.gridy++;

        gbc.gridx = 0;
        gbc.insets = new Insets(2, paddingHorizontal, 0, 10); // Zero de padding inferior temporário
        txtTipoPessoa = criarCampoTexto();
        addPlaceholder(txtTipoPessoa, "Física, Jurídica ou NI"); // Placeholder atualizado
        panel.add(txtTipoPessoa, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(2, 10, 0, paddingHorizontal); // Zero de padding inferior temporário
        txtSituacaoServico = criarCampoTexto();
        addPlaceholder(txtSituacaoServico, "Pendente, Em andamento ou Concluído"); // Placeholder atualizado
        panel.add(txtSituacaoServico, gbc);

        gbc.gridy++;

        gbc.gridx = 0;
        gbc.insets = new Insets(2, paddingHorizontal, paddingVerticalField, 10);
        lblErroTipoPessoa = criarLabelErro();
        panel.add(lblErroTipoPessoa, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(2, 10, paddingVerticalField, paddingHorizontal);
        lblErroSituacaoServico = criarLabelErro();
        panel.add(lblErroSituacaoServico, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;

        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        JLabel lblEmail = new JLabel("Email*");
        configurarLabel(lblEmail);
        panel.add(lblEmail, gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtEmail = criarCampoTexto();
        addPlaceholder(txtEmail, "emaildousuario@gmail.com");
        panel.add(txtEmail, gbc);

        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        JLabel lblTelefone = new JLabel("Telefone");
        configurarLabel(lblTelefone);
        panel.add(lblTelefone, gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtTelefone = criarCampoTexto();
        addPlaceholder(txtTelefone, "999999999");
        panel.add(txtTelefone, gbc);

        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        JLabel lblCelular = new JLabel("Celular");
        configurarLabel(lblCelular);
        panel.add(lblCelular, gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtCelular = criarCampoTexto();
        addPlaceholder(txtCelular, "999999999");
        panel.add(txtCelular, gbc);

        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        JLabel lblObservacoes = new JLabel("Observações");
        configurarLabel(lblObservacoes);
        panel.add(lblObservacoes, gbc);

        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtObservacoes = criarCampoTextArea();
        addPlaceholderTextArea(txtObservacoes, "Observações sobre o cliente.");
        txtObservacoes.setPreferredSize(new Dimension(txtObservacoes.getPreferredSize().width, 70));
        panel.add(txtObservacoes, gbc);

        gbc.insets = new Insets(5, 15, 1, 15);
        gbc.gridy++;

        JPanel botoesWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoesWrapper.setOpaque(false);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        botoes.setOpaque(false);

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

        JButton btnCriarCliente = new JButton("Criar Cliente") {
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
            protected void paintBorder(Graphics g) {
            }
        };

        configurarEstiloBotao(btnCriarCliente);
        btnCriarCliente.setPreferredSize(new Dimension(145, 35));
        btnCriarCliente.setForeground(new Color(20, 30, 50));
        btnCriarCliente.addActionListener(e -> salvarCliente());

        botoes.add(btnCancelar);
        botoes.add(btnCriarCliente);
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

    private void addPlaceholder(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
                if (field.getBorder() instanceof CompoundBorder) {
                    CompoundBorder border = (CompoundBorder) field.getBorder();
                    if (border.getOutsideBorder() instanceof LineBorder) {
                        LineBorder lineBorder = (LineBorder) border.getOutsideBorder();
                        if (lineBorder.getLineColor().equals(Color.RED)) {
                            setCampoBordaNormal(field);
                            if (field == txtTipoPessoa) {
                                lblErroTipoPessoa.setText("");
                            } else if (field == txtSituacaoServico) {
                                lblErroSituacaoServico.setText("");
                            }
                        }
                    }
                }
            }
        });
    }

    private void addPlaceholderTextArea(JTextArea area, String placeholder) {
        area.setText(placeholder);
        area.setForeground(Color.GRAY);

        area.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (area.getText().equals(placeholder)) {
                    area.setText("");
                    area.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (area.getText().isEmpty()) {
                    area.setText(placeholder);
                    area.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void configurarLabel(JLabel label) {
        label.setForeground(new Color(168, 178, 195));
        label.setFont(FonteUtils.carregarRobotoSemiBold(13f));
    }

    private JLabel criarLabelErro() {
        JLabel lbl = new JLabel("");
        lbl.setForeground(new Color(255, 100, 100)); // Vermelho Suave
        lbl.setFont(FonteUtils.carregarRobotoSemiBold(11f));
        return lbl;
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

    private JTextField criarCampoTexto() {
        JTextField txt = new JTextField();
        setCampoBordaNormal(txt);
        txt.setBackground(new Color(25, 40, 65));
        txt.setForeground(Color.WHITE);
        txt.setFont(FonteUtils.carregarRalewayMedium(12f));
        txt.setCaretColor(Color.WHITE);
        return txt;
    }

    private void setCampoBordaNormal(JTextField txt) {
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 70, 100)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private void setCampoBordaErro(JTextField txt) {
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private JTextArea criarCampoTextArea() {
        JTextArea area = new JTextArea();
        area.setBackground(new Color(25, 40, 65));
        area.setForeground(Color.WHITE);
        area.setFont(FonteUtils.carregarRalewayMedium(12f));
        area.setCaretColor(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 70, 100)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return area;
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
            blurPanel.addMouseListener(new MouseAdapter() {
            });
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

    private String normalizarSituacaoServico(String input) {
        if (input == null || input.trim().isEmpty()) return "";
        String upperInput = input.trim().toUpperCase(); 
        upperInput = upperInput.replaceAll("Í", "I");

        switch (upperInput) {
            case "PENDENTE":
                return "Pendente";
            case "EM ANDAMENTO":
                return "Em andamento";
            case "CONCLUIDO":
                return "Concluido";
            default:
                return input.trim(); 
        }
    }

    private String normalizarTipoPessoa(String input) {
        if (input == null || input.trim().isEmpty()) return "";
        String upperInput = input.trim().toUpperCase().replaceAll("Í", "I"); 

        switch (upperInput) {
            case "FISICA":
                return "Fisica";
            case "JURIDICA":
                return "Juridica";
            case "NI":
                return "NI";
            default:
                return input.trim();
        }
    }

    private boolean validarCamposCombo() {
        boolean camposValidos = true;

        String tipoPessoa = txtTipoPessoa.getText().trim();
        String tipoPessoaUpper = tipoPessoa.toUpperCase().replaceAll("Í", "I");
        boolean tipoPessoaValido = OPCOES_TIPO_PESSOA.contains(tipoPessoaUpper);

        if (tipoPessoa.isEmpty() || tipoPessoa.equals("Física, Jurídica ou NI") || !tipoPessoaValido) {
            setCampoBordaErro(txtTipoPessoa);
            lblErroTipoPessoa.setText("Valor inválido. Use: Fisica, Juridica ou NI.");
            camposValidos = false;
        } else {
            setCampoBordaNormal(txtTipoPessoa);
            lblErroTipoPessoa.setText("");
        }

        String situacaoServico = txtSituacaoServico.getText().trim();
        String situacaoServicoUpper = situacaoServico.toUpperCase().replaceAll("Í", "I"); 
        boolean situacaoServicoValida = OPCOES_SITUACAO_SERVICO.contains(situacaoServicoUpper);

        if (situacaoServico.isEmpty() || situacaoServico.equals("Pendente, Em andamento ou Concluído") || !situacaoServicoValida) {
            setCampoBordaErro(txtSituacaoServico);
            lblErroSituacaoServico.setText("Valor inválido. Use: Pendente, Em andamento ou Concluido.");
            camposValidos = false;
        } else {
            setCampoBordaNormal(txtSituacaoServico);
            lblErroSituacaoServico.setText("");
        }

        return camposValidos;
    }

    private void salvarCliente() {

        if (!validarCamposCombo()) {
            new NotificationToast(null, "Por favor, corrija os erros nos campos antes de continuar.").setVisible(true);
            return;
        }

        String nome = txtNomeCompleto.getText().trim();
        String servico = txtServico.getText().trim();
        String email = txtEmail.getText().trim();
        String tipoPessoaFinal = normalizarTipoPessoa(txtTipoPessoa.getText());
        String situacaoServicoFinal = normalizarSituacaoServico(txtSituacaoServico.getText());
        String telefone = txtTelefone.getText().trim();
        telefone = telefone.equals("999999999") ? "" : telefone;

        String celular = txtCelular.getText().trim();
        celular = celular.equals("999999999") ? "" : celular;

        String observacoes = txtObservacoes.getText().trim();
        observacoes = observacoes.equals("Observações sobre o cliente.") ? "" : observacoes;

        String usuario = usuarioLogado != null ? usuarioLogado.getUsuario() : "SISTEMA_NAO_LOGADO";
        String dataCadastro = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        if (nome.isEmpty() || nome.equals("Nome Completo") || servico.isEmpty() || servico.equals("Qual foi o serviço prestado?")) {
            new NotificationToast(null, "Por favor, preencha todos os campos obrigatórios (*).").setVisible(true);
            return;
        }

        if (usuarioLogado != null) {
            MDC.put("usuario_criador", usuarioLogado.getUsuario());
        }

        try {
            Cliente cliente = new Cliente();

            cliente.setNome(nome);
            cliente.setTipoPessoa(tipoPessoaFinal);
            cliente.setSituacaoServico(situacaoServicoFinal);
            cliente.setServico(servico);
            cliente.setTelefone(telefone);
            cliente.setEmail(email);
            cliente.setCelular(celular);
            cliente.setObservacoes(observacoes);
            cliente.setDataCadastro(dataCadastro);
            cliente.setUsuario(usuario);

            CTCONTAB.registrarCliente(cliente);

            String msg = "Você cadastrou " + nome + " como novo cliente!";
            CTCONTAB.criarNotificacao("admin", msg);

            NotificationToast toast = new NotificationToast(null, msg);
            toast.setVisible(true);

            dispose();

        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Erro de configuração: Driver do banco de dados não encontrado.", e);
            new NotificationToast(null, "Erro de configuração: Driver do banco de dados não encontrado.").setVisible(true);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro SQL ao salvar cliente no banco de dados", e);
            new NotificationToast(null, "Erro ao tentar salvar cliente no banco de dados: " + e.getMessage()).setVisible(true);
        }
    }
}