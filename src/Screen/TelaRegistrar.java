package screen;

import Data.CTCONTAB;
import Data.I18nManager;
import Screen.FonteUtils;
import Screen.MensagemUtil;
import java.sql.SQLException;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.JTextField;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.util.Locale;
import javax.swing.JLabel;

public class TelaRegistrar extends javax.swing.JFrame {

    private static final Logger logger = LoggerFactory.getLogger(TelaRegistrar.class);

    private ImageIcon globoIcone;

    public TelaRegistrar() {
        initComponents();

        try {
            java.net.URL url = getClass().getResource("/images/Close Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Close Icon.png ou src/images/Close Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(11, 11, java.awt.Image.SCALE_SMOOTH);
                btnFecharTela.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            btnFecharTela.setText("X");
        }

        try {
            java.net.URL url = getClass().getResource("/images/Maximize Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Maximize Icon.png ou src/images/Maximize Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(11, 11, java.awt.Image.SCALE_SMOOTH);
                btnMaximizarTela.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            btnMaximizarTela.setText("[]");
        }

        try {
            java.net.URL url = getClass().getResource("/images/Minimize Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Minimize Icon.png ou src/images/Minimize Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(11, 2, java.awt.Image.SCALE_SMOOTH);
                btnMinimizarTela.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            btnMinimizarTela.setText("-");
        }

        try {
            java.net.URL url = getClass().getResource("/images/Complet Logo Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Complet Logo Icon.png ou src/images/Complet Logo Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(160, 40, java.awt.Image.SCALE_SMOOTH);
                lblLogo.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblLogo.setText("-");
        }

        setUndecorated(true);
        configurarLinguagens(); // Novo: Adicionado para popular o ComboBox
        atualizarTextos();
        jlibErroRegistro.setVisible(false);
        setIcon();
        setResizable(false);

        txtUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtSenha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtSenha2.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));

        Color corCampo = new Color(0, 0, 0, 0);
        txtUsuario.setBackground(corCampo);
        txtUsuario.setOpaque(false);
        txtUsuario.setCaretColor(Color.WHITE);
        txtUsuario.setForeground(Color.WHITE);

        txtEmail.setBackground(corCampo);
        txtEmail.setOpaque(false);
        txtEmail.setCaretColor(Color.WHITE);

        txtSenha.setBackground(corCampo);
        txtSenha.setOpaque(false);
        txtSenha.setCaretColor(Color.WHITE);
        txtSenha.setForeground(Color.WHITE);

        txtSenha2.setBackground(corCampo);
        txtSenha2.setOpaque(false);
        txtSenha2.setCaretColor(Color.WHITE);
        txtSenha2.setForeground(Color.WHITE);

        addPlaceholder(txtEmail, "email@exemplo.com");

        jlibErroRegistro.setVisible(false);
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
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
            }
        });
    }

    private void configurarLinguagens() {
        cmbLinguagens.removeAllItems();
        // Formato: "Nome de Exibição:código_país"
        cmbLinguagens.addItem("Português:pt_BR");
        cmbLinguagens.addItem("English:en_US");
        cmbLinguagens.addItem("Español:es_LA"); // Exemplo de outro idioma

        // Seleciona o item que corresponde à Locale atual do Manager
        Locale currentLocale = I18nManager.getCurrentLocale();
        String currentLocaleTag = currentLocale.getLanguage() + "_" + currentLocale.getCountry();

        for (int i = 0; i < cmbLinguagens.getItemCount(); i++) {
            if (cmbLinguagens.getItemAt(i).contains(":" + currentLocaleTag)) {
                cmbLinguagens.setSelectedIndex(i);
                break;
            }
        }
    }

    private void atualizarTextos() {
        // Altera o título da janela
        setTitle(I18nManager.getString("screen_register_windown_title"));

        // Altera todos os JLabel e JButton usando o Manager
        jilbTitulo.setText(I18nManager.getString("screen_register_title_register"));
        jilbUsuario.setText(I18nManager.getString("screen_register_username"));
        jilbEmail.setText(I18nManager.getString("screen_register_user_email"));
        jilbSenha.setText(I18nManager.getString("screen_register_user_password"));
        jilbSenha2.setText(I18nManager.getString("screen_register_confirm_password"));
        chbLembre.setText(I18nManager.getString("screen_register_remindme"));
        jilbAindaNaoTemConta.setText(I18nManager.getString("screen_register_didyouhaveaccont"));
        jilbRegistreSe.setText(I18nManager.getString("screen_register_login"));
        jilbCreditos.setText(I18nManager.getString("screen_register_credits"));
        jilbTexto.setText(I18nManager.getString("screen_register_description"));
        jilbTexto2.setText(I18nManager.getString("screen_register_description2"));
        btnRegistrar.setText(I18nManager.getString("screen_register_register"));

        if (txtEmail.getForeground().equals(Color.GRAY)) {
            addPlaceholder(txtEmail, I18nManager.getString("screen_register_placeholder_email"));
        }

        jlibErroRegistro.setText(I18nManager.getString("screen_register_error_register"));
    }

    private void estilizarComboLinguagem() {

        this.globoIcone = null;

        try {
            java.net.URL url = getClass().getResource("/images/Globe Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada: /images/Globe Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(14, 14, java.awt.Image.SCALE_SMOOTH);
                this.globoIcone = new ImageIcon(img);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar o ícone do globo.");
            e.printStackTrace();
        }
        
        UIManager.put("ComboBox.background", new Color(11, 26, 53));
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", new Color(30, 50, 80));
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("ComboBox.buttonBackground", new Color(11, 26, 53));
        UIManager.put("ComboBox.border", BorderFactory.createEmptyBorder());

        cmbLinguagens.setFont(FonteUtils.carregarLato(13f));
        cmbLinguagens.setForeground(Color.WHITE);
        cmbLinguagens.setBackground(new Color(11, 26, 53));
        cmbLinguagens.setOpaque(false);
        cmbLinguagens.setBorder(null);
        cmbLinguagens.setFocusable(false);

        cmbLinguagens.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                java.awt.Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (c instanceof JLabel && value != null) {
                    JLabel label = (JLabel) c;
                    String fullString = value.toString();

                    if (fullString.contains(":")) {
                        String nomeExibicao = fullString.split(":")[0];
                        label.setText(nomeExibicao);
                    }

                    label.setIcon(globoIcone);
                    label.setIconTextGap(5);
                    label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    label.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                }

                setBackground(isSelected ? new Color(30, 50, 80) : new Color(11, 26, 53));
                setForeground(Color.WHITE);
                setFont(FonteUtils.carregarLato(13f));
                setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
                return c; // Retorna o componente do Renderer
            }
        });
        
        cmbLinguagens.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected javax.swing.JButton createArrowButton() {
                javax.swing.JButton button = new javax.swing.JButton(); // Cria o botão sem texto

                // Lógica para carregar o Ícone da Seta
                try {
                    java.net.URL url = getClass().getResource("/images/Angle Down Icon.png");

                    if (url == null) {
                        System.err.println("Ícone da seta não encontrado: /images/Angle Down Icon.png");
                    } else {
                        java.awt.Image img = javax.imageio.ImageIO.read(url)
                                .getScaledInstance(12, 12, java.awt.Image.SCALE_SMOOTH);
                        button.setIcon(new javax.swing.ImageIcon(img));
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao carregar o ícone da seta.");
                    e.printStackTrace();
                }

                // Estilização do Botão
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setForeground(Color.WHITE);
                button.setBackground(new Color(11, 26, 53));
                button.setFocusable(false);
                button.setContentAreaFilled(false);
                button.setOpaque(false);

                return button;
            }
        }); // FIM DO setUI
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnResgistrar = new javax.swing.JButton();
        jilbRegistreSe = new javax.swing.JLabel();
        jilbAindaNaoTemConta = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jilbLinha = new javax.swing.JLabel();
        cmbLinguagens = new javax.swing.JComboBox<>();
        jilbTitulo = new javax.swing.JLabel();
        jilbTexto = new javax.swing.JLabel();
        jilbTexto2 = new javax.swing.JLabel();
        btnFecharTela = new javax.swing.JButton();
        btnMaximizarTela = new javax.swing.JButton();
        btnMinimizarTela = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jilbUsuario = new javax.swing.JLabel();
        jilbEmail = new javax.swing.JLabel();
        jilbSenha = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jilbSenha2 = new javax.swing.JLabel();
        chbLembre = new javax.swing.JCheckBox();
        jlibErroRegistro = new javax.swing.JLabel();
        chbMostrarSenha = new javax.swing.JCheckBox();
        txtSenha2 = new javax.swing.JPasswordField();
        txtSenha = new javax.swing.JPasswordField();
        jilbCreditos = new javax.swing.JLabel();
        jlibBlueSquad = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CT Contab Manager");
        getContentPane().setLayout(null);

        btnResgistrar.setBackground(new java.awt.Color(30, 30, 30));
        btnResgistrar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnResgistrar.setForeground(new java.awt.Color(16, 165, 103));
        btnResgistrar.setContentAreaFilled(false);
        btnResgistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResgistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnResgistrar);
        btnResgistrar.setBounds(110, 580, 130, 20);

        jilbRegistreSe.setFont(FonteUtils.carregarLato(13f));
        jilbRegistreSe.setForeground(new java.awt.Color(16, 168, 105));
        jilbRegistreSe.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jilbRegistreSe.setText("Login");
        getContentPane().add(jilbRegistreSe);
        jilbRegistreSe.setBounds(120, 580, 50, 20);

        jilbAindaNaoTemConta.setBackground(new java.awt.Color(255, 255, 255));
        jilbAindaNaoTemConta.setFont(FonteUtils.carregarLato(13f));
        jilbAindaNaoTemConta.setForeground(new java.awt.Color(255, 255, 255));
        jilbAindaNaoTemConta.setText("Você já tem conta?");
        getContentPane().add(jilbAindaNaoTemConta);
        jilbAindaNaoTemConta.setBounds(30, 580, 140, 20);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logoctcontab.png"))); // NOI18N
        lblLogo.setPreferredSize(new java.awt.Dimension(40, 59));
        getContentPane().add(lblLogo);
        lblLogo.setBounds(10, 20, 180, 40);

        jilbLinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line 1.png"))); // NOI18N
        jilbLinha.setText("jLabel1");
        getContentPane().add(jilbLinha);
        jilbLinha.setBounds(0, 0, 390, 160);

        cmbLinguagens.setBackground(new java.awt.Color(11, 26, 53));
        cmbLinguagens.setFont(FonteUtils.carregarLato(10f));
        cmbLinguagens.setForeground(new java.awt.Color(255, 255, 255));
        cmbLinguagens.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Português" }));
        cmbLinguagens.setBorder(null);
        cmbLinguagens.setOpaque(false);
        cmbLinguagens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLinguagensActionPerformed(evt);
            }
        });
        getContentPane().add(cmbLinguagens);
        cmbLinguagens.setBounds(200, 20, 180, 40);

        jilbTitulo.setFont(FonteUtils.carregarRalewayMedium(30f));
        jilbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jilbTitulo.setText("Registre-se");
        jilbTitulo.setPreferredSize(new java.awt.Dimension(100, 100));
        getContentPane().add(jilbTitulo);
        jilbTitulo.setBounds(30, 100, 300, 50);

        jilbTexto.setFont(FonteUtils.carregarLato(13f));
        jilbTexto.setForeground(new java.awt.Color(255, 255, 255));
        jilbTexto.setText("Toda a organização da sua contabilidade em um só lugar, ");
        getContentPane().add(jilbTexto);
        jilbTexto.setBounds(30, 160, 390, 30);

        jilbTexto2.setFont(FonteUtils.carregarLato(13f));
        jilbTexto2.setForeground(new java.awt.Color(255, 255, 255));
        jilbTexto2.setText("totalmente digital.");
        getContentPane().add(jilbTexto2);
        jilbTexto2.setBounds(30, 180, 390, 30);

        btnFecharTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        btnFecharTela.setContentAreaFilled(false);
        btnFecharTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnFecharTela);
        btnFecharTela.setBounds(1425, 0, 15, 25);

        btnMaximizarTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Maximize Icon.png"))); // NOI18N
        btnMaximizarTela.setContentAreaFilled(false);
        btnMaximizarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizarTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnMaximizarTela);
        btnMaximizarTela.setBounds(1390, 0, 15, 25);

        btnMinimizarTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minimize Icon.png"))); // NOI18N
        btnMinimizarTela.setContentAreaFilled(false);
        btnMinimizarTela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizarTelaMouseClicked(evt);
            }
        });
        btnMinimizarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnMinimizarTela);
        btnMinimizarTela.setBounds(1355, 0, 15, 25);

        btnRegistrar.setBackground(new java.awt.Color(17, 168, 100));
        btnRegistrar.setFont(FonteUtils.carregarLato(14f));
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRegistrarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseReleased(evt);
            }
        });
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrar);
        btnRegistrar.setBounds(30, 540, 330, 40);

        jilbUsuario.setFont(FonteUtils.carregarLato(13f));
        jilbUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jilbUsuario.setText("Usuário");
        getContentPane().add(jilbUsuario);
        jilbUsuario.setBounds(30, 230, 190, 16);

        jilbEmail.setFont(FonteUtils.carregarLato(13f));
        jilbEmail.setForeground(new java.awt.Color(255, 255, 255));
        jilbEmail.setText("Email");
        getContentPane().add(jilbEmail);
        jilbEmail.setBounds(30, 300, 190, 16);

        jilbSenha.setFont(FonteUtils.carregarLato(13f));
        jilbSenha.setForeground(new java.awt.Color(255, 255, 255));
        jilbSenha.setText("Senha");
        getContentPane().add(jilbSenha);
        jilbSenha.setBounds(30, 370, 170, 20);

        txtEmail.setBackground(new java.awt.Color(4, 21, 57));
        txtEmail.setForeground(new java.awt.Color(115, 115, 115));
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        getContentPane().add(txtEmail);
        txtEmail.setBounds(30, 320, 330, 40);
        addPlaceholder(txtEmail, "  email@exemplo.com");

        txtUsuario.setBackground(new java.awt.Color(4, 21, 57));
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
        txtUsuario.setCaretColor(new java.awt.Color(255, 255, 255));
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(30, 250, 330, 40);

        jilbSenha2.setFont(FonteUtils.carregarLato(13f));
        jilbSenha2.setForeground(new java.awt.Color(255, 255, 255));
        jilbSenha2.setText("Confirme a Senha");
        getContentPane().add(jilbSenha2);
        jilbSenha2.setBounds(30, 440, 170, 20);

        chbLembre.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        chbLembre.setForeground(new java.awt.Color(255, 255, 255));
        chbLembre.setText("Lembre me?");
        chbLembre.setIcon(createCheckboxIcon(false));
        chbLembre.setSelectedIcon(createCheckboxIcon(true));
        chbLembre.setRolloverIcon(createCheckboxIcon(false));
        chbLembre.setRolloverSelectedIcon(createCheckboxIcon(true));
        chbLembre.setContentAreaFilled(false);
        chbLembre.setBorderPainted(false);
        chbLembre.setFocusPainted(false);
        chbLembre.setOpaque(false);
        getContentPane().add(chbLembre);
        chbLembre.setBounds(30, 500, 110, 30);

        jlibErroRegistro.setFont(FonteUtils.carregarLato(10f));
        jlibErroRegistro.setForeground(new java.awt.Color(255, 0, 0));
        jlibErroRegistro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlibErroRegistro.setText("Seu e-mail, senha ou usuário estão incorretos.");
        getContentPane().add(jlibErroRegistro);
        jlibErroRegistro.setBounds(90, 500, 270, 30);

        chbMostrarSenha.setContentAreaFilled(false);
        chbMostrarSenha.setFocusPainted(false);
        chbMostrarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-aberto.png"))); // NOI18N
        chbMostrarSenha.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-fechado.png"))); // NOI18N
        chbMostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMostrarSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(chbMostrarSenha);
        chbMostrarSenha.setBounds(320, 390, 30, 40);

        txtSenha2.setBackground(new java.awt.Color(4, 21, 57));
        txtSenha2.setForeground(new java.awt.Color(115, 115, 115));
        txtSenha2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
        getContentPane().add(txtSenha2);
        txtSenha2.setBounds(30, 460, 330, 40);

        txtSenha.setBackground(new java.awt.Color(4, 21, 57));
        txtSenha.setForeground(new java.awt.Color(255, 255, 255));
        txtSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(30, 390, 330, 40);

        jilbCreditos.setBackground(new java.awt.Color(255, 255, 255));
        jilbCreditos.setFont(FonteUtils.carregarLato(13f));
        jilbCreditos.setForeground(new java.awt.Color(255, 255, 255));
        jilbCreditos.setText("  © 2025 CT Contab. Todos os direitos reservados.");
        getContentPane().add(jilbCreditos);
        jilbCreditos.setBounds(10, 700, 350, 40);

        jlibBlueSquad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Overlay.png"))); // NOI18N
        getContentPane().add(jlibBlueSquad);
        jlibBlueSquad.setBounds(0, 0, 390, 750);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background Login.png"))); // NOI18N
        Background.setText("jLabel3");
        getContentPane().add(Background);
        Background.setBounds(40, 0, 1420, 760);

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

        String usuario = txtUsuario.getText().trim();
        String email = txtEmail.getText().trim();
        String senha = txtSenha.getText().trim();
        String confirmarSenha = txtSenha2.getText().trim();

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        boolean emailValido = email.matches(emailRegex);

        boolean usuarioValido = usuario.length() >= 5;

        boolean senhasIguais = senha.equals(confirmarSenha);

        if (!emailValido || !usuarioValido || !senhasIguais) {
            mostrarMensagemErro();
            return;
        }
        try {
            CTCONTAB.registrarUsuario(txtUsuario.getText(), txtEmail.getText(), txtSenha.getText());
            MensagemUtil.exibirSucesso("Usuário cadastrado com sucesso");
            logger.info("[{}] criou uma nova conta", txtUsuario.getText());
            dispose();
            new TelaLogin().setVisible(true);
        } catch (ClassNotFoundException x) {
            MensagemUtil.exibirErro("Driver JDBC não encontrado!");
        } catch (SQLException x) {
            if (x.getMessage().contains("Duplicate entry")) {
                MensagemUtil.exibirErro("Este Usuário já está cadastrado");
            } else {
                MensagemUtil.exibirErro("Erro na conexão com o banco de dados!");
            }
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private ImageIcon createCheckboxIcon(boolean checked) {
        int size = 18;
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (checked) {
            g.setColor(new java.awt.Color(16, 165, 103));
            g.fillRoundRect(0, 0, size, size, 4, 4);
            g.setColor(java.awt.Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 14));
            g.drawString("✓", 3, size - 4);
        } else {
            g.setColor(new java.awt.Color(84, 84, 84));
            g.drawRoundRect(0, 0, size - 1, size - 1, 4, 4);
        }

        g.dispose();
        return new ImageIcon(img);
    }

    private void mostrarMensagemErro() {
        jlibErroRegistro.setText("Seu e-mail, senha ou usuário estão incorretos.");
        jlibErroRegistro.setForeground(Color.RED);
        jlibErroRegistro.setVisible(true);

        txtUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtSenha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtSenha2.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));

        new Thread(() -> {
            try {
                Thread.sleep(2000);

                for (int i = 0; i <= 20; i++) {
                    int r = 255 - (int) ((255 - 84) * (i / 20.0));
                    int g = 0 + (int) ((84 - 0) * (i / 20.0));
                    int b = 0 + (int) ((84 - 0) * (i / 20.0));
                    Color corSuave = new Color(r, g, b);

                    javax.swing.SwingUtilities.invokeLater(() -> {
                        txtUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(corSuave, 1, true),
                                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                        txtSenha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(corSuave, 1, true),
                                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                        txtEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(corSuave, 1, true),
                                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                        txtSenha2.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(corSuave, 1, true),
                                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                    });

                    Thread.sleep(30);
                }

                javax.swing.SwingUtilities.invokeLater(() -> {
                    txtUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                            javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                    txtSenha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                            javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                    txtEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                            javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                    txtSenha2.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                            javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased

    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased

    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void btnRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseEntered
        btnRegistrar.setBackground(new java.awt.Color(20, 190, 115));
    }//GEN-LAST:event_btnRegistrarMouseEntered

    private void btnRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseExited
        btnRegistrar.setBackground(new java.awt.Color(17, 168, 100));
    }//GEN-LAST:event_btnRegistrarMouseExited

    private void btnRegistrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMousePressed
        btnRegistrar.setBackground(new java.awt.Color(14, 140, 85));
    }//GEN-LAST:event_btnRegistrarMousePressed

    private void btnRegistrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseReleased
        btnRegistrar.setBackground(new java.awt.Color(17, 168, 100));
    }//GEN-LAST:event_btnRegistrarMouseReleased

    private void btnResgistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResgistrarActionPerformed
        dispose();
        new TelaLogin().setVisible(true);
    }//GEN-LAST:event_btnResgistrarActionPerformed

    private void btnRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarMouseClicked

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void chbMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMostrarSenhaActionPerformed
        if (chbMostrarSenha.isSelected()) {
            txtSenha.setEchoChar((char) 0);
            txtSenha2.setEchoChar((char) 0);
        } else {
            txtSenha.setEchoChar('•');
            txtSenha2.setEchoChar('•');
        }

    }//GEN-LAST:event_chbMostrarSenhaActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnFecharTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharTelaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnFecharTelaActionPerformed

    private void btnMaximizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaximizarTelaActionPerformed

    private void btnMinimizarTelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarTelaMouseClicked
        setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarTelaMouseClicked

    private void btnMinimizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMinimizarTelaActionPerformed

    private void cmbLinguagensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLinguagensActionPerformed
        String selectedItem = (String) cmbLinguagens.getSelectedItem();
        if (selectedItem != null && selectedItem.contains(":")) {
            // Extrai a tag da Locale (ex: "pt_BR")
            String localeString = selectedItem.split(":")[1];
            String[] parts = localeString.split("_");

            // Cria a nova Locale (ex: new Locale("pt", "BR"))
            Locale newLocale = new Locale(parts[0], parts[1]);

            // **DEFINE A NOVA LOCALE GLOBALMENTE**
            I18nManager.setLocale(newLocale);

            // Atualiza todos os textos na tela atual
            atualizarTextos();

            // Re-estiliza o ComboBox para garantir a renderização correta
            estilizarComboLinguagem();
        }
    }//GEN-LAST:event_cmbLinguagensActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRegistrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnFecharTela;
    private javax.swing.JButton btnMaximizarTela;
    private javax.swing.JButton btnMinimizarTela;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnResgistrar;
    private javax.swing.JCheckBox chbLembre;
    private javax.swing.JCheckBox chbMostrarSenha;
    private javax.swing.JComboBox<String> cmbLinguagens;
    private javax.swing.JLabel jilbAindaNaoTemConta;
    private javax.swing.JLabel jilbCreditos;
    private javax.swing.JLabel jilbEmail;
    private javax.swing.JLabel jilbLinha;
    private javax.swing.JLabel jilbRegistreSe;
    private javax.swing.JLabel jilbSenha;
    private javax.swing.JLabel jilbSenha2;
    private javax.swing.JLabel jilbTexto;
    private javax.swing.JLabel jilbTexto2;
    private javax.swing.JLabel jilbTitulo;
    private javax.swing.JLabel jilbUsuario;
    private javax.swing.JLabel jlibBlueSquad;
    private javax.swing.JLabel jlibErroRegistro;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JPasswordField txtSenha2;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
