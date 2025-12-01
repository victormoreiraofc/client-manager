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
// Imports adicionados para a função de salvar no banco de dados
import Data.CTCONTAB;
import Data.Cliente; // Assumido que esta classe está disponível no pacote Data
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
// Fim dos imports adicionados

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

    // Método para centralizar a inicialização do JDialog
    private void inicializarUI() {
        // AJUSTE FINAL: Altura reduzida para 600px.
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

        // --- PAINEL PRINCIPAL (Fundo arredondado e borda) ---
        JPanel panel = new JPanel() {
            private final int ARCO = 20;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fundo Escuro (Cor 28, 46, 74)
                g2.setColor(new Color(28, 46, 74));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARCO, ARCO);

                // Borda Sutil (Cor 42, 62, 97)
                g2.setColor(new Color(42, 62, 97));
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARCO, ARCO);

                g2.dispose();
            }
        };

        // AJUSTE: Mantido EmptyBorder mínimo para que o JPanel preencha quase todo o JDialog
        panel.setBorder(new EmptyBorder(2, 2, 2, 2));
        panel.setLayout(new GridBagLayout());

        // Adiciona o painel principal ao content pane do JDialog com BorderLayout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        int paddingHorizontal = 25;
        // Padding vertical entre label e campo (mantido em 6)
        int paddingVerticalLabel = 6;
        // Padding vertical abaixo dos campos com erro (mantido em 10)
        int paddingVerticalField = 10;

        // --- Título e Botão Fechar (ROW 0) ---
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Ocupa as 2 colunas principais
        gbc.weightx = 1;
        // AJUSTE: Padding superior de 5 e inferior de 8 (para o título)
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

        // --- ROW 1: Nome Completo (Label + Field) ---
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

        // --- ROW 2: Serviço (Label + Field) ---
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

        // ----------------------------------------------------------------------
        // --- ROW 3: Tipo de Pessoa (COL 0) e Situação do Serviço (COL 1) ---
        // ----------------------------------------------------------------------
        // COL 0: Tipo de Pessoa (Label)
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(paddingVerticalLabel, paddingHorizontal, 2, 10);
        gbc.gridy++;
        JLabel lblTipoPessoa = new JLabel("Tipo de Pessoa*");
        configurarLabel(lblTipoPessoa);
        panel.add(lblTipoPessoa, gbc);

        // COL 1: Situação do Serviço (Label)
        gbc.gridx = 1;
        gbc.insets = new Insets(paddingVerticalLabel, 10, 2, paddingHorizontal);
        JLabel lblSituacaoServico = new JLabel("Situação do Serviço*");
        configurarLabel(lblSituacaoServico);
        panel.add(lblSituacaoServico, gbc);

        gbc.gridy++;

        // COL 0: Tipo de Pessoa (Field)
        gbc.gridx = 0;
        gbc.insets = new Insets(2, paddingHorizontal, 0, 10); // Zero de padding inferior temporário
        txtTipoPessoa = criarCampoTexto();
        addPlaceholder(txtTipoPessoa, "Física, Jurídica ou NI"); // Placeholder atualizado
        panel.add(txtTipoPessoa, gbc);

        // COL 1: Situação do Serviço (Field)
        gbc.gridx = 1;
        gbc.insets = new Insets(2, 10, 0, paddingHorizontal); // Zero de padding inferior temporário
        txtSituacaoServico = criarCampoTexto();
        addPlaceholder(txtSituacaoServico, "Pendente, Em andamento ou Concluído"); // Placeholder atualizado
        panel.add(txtSituacaoServico, gbc);

        gbc.gridy++;

        // COL 0: Erro Tipo de Pessoa (Label)
        gbc.gridx = 0;
        gbc.insets = new Insets(2, paddingHorizontal, paddingVerticalField, 10);
        lblErroTipoPessoa = criarLabelErro();
        panel.add(lblErroTipoPessoa, gbc);

        // COL 1: Erro Situação do Serviço (Label)
        gbc.gridx = 1;
        gbc.insets = new Insets(2, 10, paddingVerticalField, paddingHorizontal);
        lblErroSituacaoServico = criarLabelErro();
        panel.add(lblErroSituacaoServico, gbc);

        // ----------------------------------------------------------------------
        // --- Voltar para 1 coluna completa ---
        // ----------------------------------------------------------------------
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;

        // --- ROW 4: Email (Label + Field) ---
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

        // --- ROW 5: Telefone (Label + Field) ---
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

        // --- ROW 6: Celular (Label + Field) ---
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

        // --- ROW 7: Observações (Label + TextArea) ---
        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        JLabel lblObservacoes = new JLabel("Observações");
        configurarLabel(lblObservacoes);
        panel.add(lblObservacoes, gbc);

        // AJUSTE: Padding inferior reduzido de 8 para 5
        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtObservacoes = criarCampoTextArea();
        addPlaceholderTextArea(txtObservacoes, "Observações sobre o cliente.");
        txtObservacoes.setPreferredSize(new Dimension(txtObservacoes.getPreferredSize().width, 70));
        panel.add(txtObservacoes, gbc);

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
                // Sem borda
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

        // ESC fecha a janela
        panel.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    // =========================================================================
    //                            MÉTODOS AUXILIARES DE UI
    // =========================================================================
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
                // Garante que a borda volta ao normal ao perder o foco
                if (field.getBorder() instanceof CompoundBorder) {
                    CompoundBorder border = (CompoundBorder) field.getBorder();
                    if (border.getOutsideBorder() instanceof LineBorder) {
                        LineBorder lineBorder = (LineBorder) border.getOutsideBorder();
                        if (lineBorder.getLineColor().equals(Color.RED)) {
                            setCampoBordaNormal(field);
                            // Se for campo de combo, também limpa o erro
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

    // Sobrecarga para JTextArea
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
                BorderFactory.createLineBorder(new Color(50, 70, 100)), // Borda padrão
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private void setCampoBordaErro(JTextField txt) {
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED), // Borda vermelha de erro
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

    // =========================================================================
    //                                LÓGICA DO BLUR
    // =========================================================================
    // Métodos de BLUR mantidos.
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
                return "Pendente"; // ENUM: 'Pendente'
            case "EM ANDAMENTO":
                return "Em andamento"; // ENUM: 'Em andamento'
            case "CONCLUIDO":
                return "Concluido"; // ENUM: 'Concluido'
            default:
                return input.trim(); 
        }
    }

    private String normalizarTipoPessoa(String input) {
        if (input == null || input.trim().isEmpty()) return "";
        String upperInput = input.trim().toUpperCase().replaceAll("Í", "I"); 

        switch (upperInput) {
            case "FISICA":
                return "Fisica"; // ENUM: 'Fisica'
            case "JURIDICA":
                return "Juridica"; // ENUM: 'Juridica'
            case "NI":
                return "NI"; // ENUM: 'NI'
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

        // 1. Validação dos campos 'combo'
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

        // Validação dos campos obrigatórios remanescentes
        if (nome.isEmpty() || nome.equals("Nome Completo") || servico.isEmpty() || servico.equals("Qual foi o serviço prestado?")) {
            new NotificationToast(null, "Por favor, preencha todos os campos obrigatórios (*).").setVisible(true);
            return;
        }

        if (usuarioLogado != null) {
            MDC.put("usuario_criador", usuarioLogado.getUsuario());
            //logger.info("Tentativa de criar cliente por: " + usuarioLogado.getUsuario());
        }

        try {
            // Instancia o objeto Cliente
            Cliente cliente = new Cliente();

            // Popula os dados do cliente com os valores normalizados
            cliente.setNome(nome);
            cliente.setTipoPessoa(tipoPessoaFinal); // <-- Usando o valor normalizado
            cliente.setSituacaoServico(situacaoServicoFinal); // <-- Usando o valor normalizado
            cliente.setServico(servico);
            cliente.setTelefone(telefone);
            cliente.setEmail(email);
            cliente.setCelular(celular);
            cliente.setObservacoes(observacoes);
            cliente.setDataCadastro(dataCadastro);
            cliente.setUsuario(usuario);

            // Chama a função de registro no CTCONTAB
            CTCONTAB.registrarCliente(cliente);

            String msg = "Você cadastrou " + nome + " como novo cliente!";
            CTCONTAB.criarNotificacao("admin", msg); // Salva no histórico

            // Sucesso: Exibe o toast e fecha a janela (dispose)
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