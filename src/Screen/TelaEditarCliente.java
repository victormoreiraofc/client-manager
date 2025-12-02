package Screen;

import Data.CTCONTAB;
import Data.Cliente;
import Data.Usuario;
import Screen.FonteUtils;
import Data.IconUtil; // Import necessário para o ícone
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

public class TelaEditarCliente extends JDialog {

    private JTextField txtNome, txtServico, txtTipoPessoa, txtSituacao, txtEmail, txtTelefone, txtCelular;
    private JTextArea txtObservacoes;
    private Usuario usuarioLogado;
    private Cliente clienteEditar;
    private JFrame parentFrame;
    private Component glassPaneOriginal;
    private boolean salvou = false;

    // Logger
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaEditarCliente.class.getName());

    public TelaEditarCliente(JFrame parent, Usuario usuario, Cliente cliente) {
        super(parent, "Editar Cliente", ModalityType.APPLICATION_MODAL);
        this.parentFrame = parent;
        this.usuarioLogado = usuario;
        this.clienteEditar = cliente;

        // Dimensões idênticas à TelaAdicionarCliente
        setSize(900, 630);
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
        setBackground(new Color(0,0,0,0));
        inicializarUI();
    }
    
    public boolean isSalvou() { return salvou; }

    private void inicializarUI() {
        // --- PAINEL PRINCIPAL (Design idêntico) ---
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
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        
        // Espaçamento horizontal idêntico à TelaAdicionarCliente
        int paddingHorizontal = 25;

        // --- ROW 0: HEADER (Título e Fechar) ---
        gbc.gridy = 0; gbc.gridx = 0; gbc.gridwidth = 2;
        // Insets idênticos para posicionar o 'X' corretamente
        gbc.insets = new Insets(5, paddingHorizontal, 8, paddingHorizontal);
        
         JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setOpaque(false);

        GridBagConstraints gbcHeader = new GridBagConstraints();

        gbcHeader.gridx = 0;
        gbcHeader.weightx = 1.0;
        gbcHeader.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Editar um cliente");
        lblTitulo.setFont(FonteUtils.carregarRobotoExtraBold(20f));
        lblTitulo.setForeground(new java.awt.Color(236, 235, 235));
        headerPanel.add(lblTitulo, gbcHeader);

        gbcHeader.gridx = 1;
        gbcHeader.weightx = 0;
        gbcHeader.fill = GridBagConstraints.NONE;
        JButton btnClose = criarBotaoFechar();
        headerPanel.add(btnClose, gbcHeader);

        panel.add(headerPanel, gbc);

        // --- CAMPOS (Layout e Estilo idênticos) ---
        
        // Nome
        gbc.insets = new Insets(6, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        panel.add(createLabel("Nome Completo*"), gbc);
        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtNome = createTextField(clienteEditar.getNome());
        panel.add(txtNome, gbc);

        // Serviço
        gbc.insets = new Insets(6, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        panel.add(createLabel("Serviço*"), gbc);
        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtServico = createTextField(clienteEditar.getServico());
        panel.add(txtServico, gbc);

        // Linha Dupla (Tipo / Situação)
        gbc.gridy++;
        gbc.gridwidth = 1;
        
        gbc.gridx = 0;
        gbc.insets = new Insets(6, paddingHorizontal, 2, 10);
        panel.add(createLabel("Tipo de Pessoa*"), gbc);
        
        gbc.gridx = 1;
        gbc.insets = new Insets(6, 10, 2, paddingHorizontal);
        panel.add(createLabel("Situação do Serviço*"), gbc);
        
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.insets = new Insets(2, paddingHorizontal, 5, 10);
        txtTipoPessoa = createTextField(clienteEditar.getTipoPessoa());
        panel.add(txtTipoPessoa, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(2, 10, 5, paddingHorizontal);
        txtSituacao = createTextField(clienteEditar.getSituacaoServico());
        panel.add(txtSituacao, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;

        // Email
        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        panel.add(createLabel("Email*"), gbc);
        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtEmail = createTextField(clienteEditar.getEmail());
        panel.add(txtEmail, gbc);

        // Telefone
        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        panel.add(createLabel("Telefone"), gbc);
        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtTelefone = createTextField(clienteEditar.getTelefone());
        panel.add(txtTelefone, gbc);

        // Celular
        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        panel.add(createLabel("Celular"), gbc);
        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtCelular = createTextField(clienteEditar.getCelular());
        panel.add(txtCelular, gbc);

        // Obs
        gbc.insets = new Insets(4, paddingHorizontal, 2, paddingHorizontal);
        gbc.gridy++;
        panel.add(createLabel("Observações"), gbc);
        gbc.insets = new Insets(2, paddingHorizontal, 5, paddingHorizontal);
        gbc.gridy++;
        txtObservacoes = new JTextArea(clienteEditar.getObservacoes());
        styleTextArea(txtObservacoes);
        txtObservacoes.setPreferredSize(new Dimension(100, 60)); // Altura idêntica
        JScrollPane scrollObs = new JScrollPane(txtObservacoes);
        scrollObs.setBorder(null);
        scrollObs.setOpaque(false);
        scrollObs.getViewport().setOpaque(false);
        panel.add(scrollObs, gbc);        
        
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
        JButton btnCriarCliente = new JButton("Salvar Cliente") {
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
        btnCriarCliente.addActionListener(e -> salvarAlteracoes());

        botoes.add(btnCancelar);
        botoes.add(btnCriarCliente);
        botoesWrapper.add(botoes);
        panel.add(botoesWrapper, gbc);

        // ESC fecha a janela
        panel.registerKeyboardAction(e -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    
    }

    private void salvarAlteracoes() {
        // Coleta dados
        clienteEditar.setNome(txtNome.getText().trim());
        clienteEditar.setServico(txtServico.getText().trim());
        clienteEditar.setTipoPessoa(txtTipoPessoa.getText().trim());
        clienteEditar.setSituacaoServico(txtSituacao.getText().trim());
        clienteEditar.setEmail(txtEmail.getText().trim());
        clienteEditar.setTelefone(txtTelefone.getText().trim());
        clienteEditar.setCelular(txtCelular.getText().trim());
        clienteEditar.setObservacoes(txtObservacoes.getText().trim());
        
        // Simples validação (mantenha a sua lógica de validação aqui)
        if(clienteEditar.getNome().isEmpty() || clienteEditar.getEmail().isEmpty()) {
            //new NotificationToast(null, "Preencha os campos obrigatórios!").setVisible(true);
            return;
        }

        try {
            // FUNÇÃO PRINCIPAL DE EDIÇÃO
            CTCONTAB.atualizarCliente(clienteEditar);
            
            // Simulação de registro de auditoria (se necessário)
            // CTCONTAB.registrarAuditoria(usuarioLogado.getUsuario(), "Editou o cliente ID: " + clienteEditar.getId());
            
            String msg = "Cliente ID " + clienteEditar.getId() + " atualizado com sucesso!";
            //new NotificationToast(null, msg).setVisible(true);
            this.salvou = true;
            dispose();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao salvar alterações no cliente: " + clienteEditar.getId(), e);
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + e.getMessage());
        }
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

    private JLabel createLabel(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(new Color(168, 178, 195));
        l.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        return l;
    }
    
    // Estilo do JTextField (Campos de Entrada)
    private JTextField createTextField(String t) {
        JTextField f = new JTextField(t);
        f.setBackground(new Color(25, 40, 65));
        f.setForeground(Color.WHITE);
        f.setFont(FonteUtils.carregarRalewayMedium(12f));
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(50,70,100)),
            BorderFactory.createEmptyBorder(8,10,8,10)));
        return f;
    }
    
    // Estilo do JTextArea (Observações)
    private void styleTextArea(JTextArea a) {
        a.setBackground(new Color(25, 40, 65));
        a.setForeground(Color.WHITE);
        a.setFont(FonteUtils.carregarRalewayMedium(12f));
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        a.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(50,70,100)),
            BorderFactory.createEmptyBorder(8,10,8,10)));
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
    
    // --- Blur Helpers (Inalterados) ---
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
                        if (imagemDesfocada != null) g.drawImage(imagemDesfocada, 0, 0, getWidth(), getHeight(), this);
                        g.setColor(new Color(0, 0, 0, 80));
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                };
                parentFrame.setGlassPane(blurPanel);
                blurPanel.setVisible(true);
            } catch (Exception e) {}
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