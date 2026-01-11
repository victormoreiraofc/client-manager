package screen;

import Data.Usuario;
import Data.CTCONTAB;
import Screen.MensagemUtil;
import Screen.TelaUpdateLoadingOverview;
import Screen.TelaLoadingOverview;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JTextField;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import java.awt.Color;
import Screen.FonteUtils;
import Data.I18nManager;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import Data.LayoutManager;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
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
        configurarLinguagens();
        atualizarTextos();
        carregarCredenciais();
        jlibErroLogin.setVisible(false);
        setIcon();
        setResizable(false);

        txtLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtSenha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));

        Color corCampo = new Color(0, 0, 0, 0);
        txtLogin.setBackground(corCampo);
        txtLogin.setOpaque(false);
        txtLogin.setCaretColor(Color.WHITE);

        txtSenha.setBackground(corCampo);
        txtSenha.setOpaque(false);
        txtSenha.setCaretColor(Color.WHITE);
        txtSenha.setForeground(Color.WHITE);

        addPlaceholder(txtLogin, "email@exemplo.com");

    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
    }

    private void salvarCredenciais(String usuario, String senha) {
        if (usuario == null || usuario.isEmpty()) {
            return;
        }

        String fileName = "login_" + usuario + ".properties";
        Properties props = new Properties();
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            props.setProperty("usuario", usuario);
            props.setProperty("senha", senha);
            props.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarCredenciais() {
        File dir = new File(".");
        File[] files = dir.listFiles((d, name) -> name.startsWith("login_") && name.endsWith(".properties"));

        if (files != null && files.length > 0) {
            for (File file : files) {
                Properties props = new Properties();
                try (FileInputStream in = new FileInputStream(file)) {
                    props.load(in);
                    String usuario = props.getProperty("usuario");
                    String senha = props.getProperty("senha");

                    if (usuario != null && senha != null) {
                        txtLogin.setText(usuario);
                        txtSenha.setText(senha);
                        chbLembre.setSelected(true);
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
        cmbLinguagens.addItem("Português:pt_BR");
        cmbLinguagens.addItem("English:en_US");
        cmbLinguagens.addItem("Español:es_ES");
        cmbLinguagens.addItem("Deutsch:de_DE");
        cmbLinguagens.addItem("Français:fr_FR");
        cmbLinguagens.addItem("Italiano:it_IT");
        cmbLinguagens.addItem("日本語:ja_JP");
        cmbLinguagens.addItem("한국어:ko_KR");
        cmbLinguagens.addItem("中文:zh_CN");

        Locale currentLocale = I18nManager.getCurrentLocale();
        String currentLocaleTag = currentLocale.getLanguage() + "_" + currentLocale.getCountry();

        for (int i = 0; i < cmbLinguagens.getItemCount(); i++) {
            if (cmbLinguagens.getItemAt(i).contains(":" + currentLocaleTag)) {
                cmbLinguagens.setSelectedIndex(i);
                break;
            }
        }
    }

    private void aplicarFonteSistema(java.awt.Container container, boolean isAsiatico) {
        for (java.awt.Component c : container.getComponents()) {

            Font fonteAtual = c.getFont();
            int tamanho = fonteAtual.getSize();
            int estilo = fonteAtual.getStyle();

            if (isAsiatico) {
                c.setFont(new Font("SansSerif", estilo, tamanho));
            } else {
                c.setFont(FonteUtils.carregarLato(tamanho).deriveFont(estilo));
            }

            if (c instanceof java.awt.Container) {
                aplicarFonteSistema((java.awt.Container) c, isAsiatico);
            }
        }
    }

    private void atualizarTextos() {
        Locale loc = I18nManager.getCurrentLocale();
        String langTag = loc.toString();
        boolean isAsiatico = loc.getLanguage().equals("ja") || loc.getLanguage().equals("ko") || loc.getLanguage().equals("zh");

        aplicarFonteSistema(this.getContentPane(), isAsiatico);

        setTitle(I18nManager.getString("auth.login.window_title"));
        jilbTitulo.setText(I18nManager.getString("auth.login.header.title"));
        jilbTexto.setText(I18nManager.getString("auth.login.header.description"));
        jilbTexto2.setText(I18nManager.getString("auth.login.header.subtitle"));
        btnGoogle.setText(I18nManager.getString("auth.login.social.google"));
        jilbTexto3.setText(I18nManager.getString("auth.login.divider.text"));
        jilbEmailOuUsuario.setText(I18nManager.getString("auth.login.form.label.email"));
        jilbSenha.setText(I18nManager.getString("auth.login.form.label.password"));
        chbLembre.setText(I18nManager.getString("auth.login.form.checkbox.remember"));
        jlibEsqueceuASenha.setText(I18nManager.getString("auth.login.form.link.forgot_password"));
        btnLogin.setText(I18nManager.getString("auth.login.form.button.submit"));
        jilbAindaNaoTemConta.setText(I18nManager.getString("auth.login.footer.not_registered"));
        jilbRegistreSe.setText(I18nManager.getString("auth.login.footer.link.create_account"));
        jilbCreditos.setText(I18nManager.getString("auth.login.footer.copyright"));
        jlibErroLogin.setText(I18nManager.getString("auth.login.feedback.error.invalid_credentials"));

        Map<String, JComponent> compMap = new HashMap<>();
        compMap.put("layout.login.header.title", jilbTitulo);
        compMap.put("layout.login.header.description", jilbTexto);
        compMap.put("layout.login.header.subtitle", jilbTexto2);
        compMap.put("layout.login.divider.text", jilbTexto3);
        compMap.put("layout.login.form.label.email", jilbEmailOuUsuario);
        compMap.put("layout.login.form.label.password", jilbSenha);
        compMap.put("layout.login.form.button.submit", btnLogin);
        compMap.put("layout.login.footer.not_registered", jilbAindaNaoTemConta);
        compMap.put("layout.login.footer.link.create_account", jilbRegistreSe);
        compMap.put("layout.login.form.checkbox.remember", chbLembre);
        compMap.put("layout.login.feedback.error.invalid_credentials", jlibErroLogin);
        compMap.put("layout.login.line.right", jilbLinha4);
        compMap.put("layout.login.line.left", jilbLinha3);
        compMap.put("layout.login.social.google", btnGoogle);
        compMap.put("layout.login.form.link.forgot_password", jlibEsqueceuASenha);
        compMap.put("layout.login.form.placeholder.email", txtLogin);
        compMap.put("layout.login.form.placeholder.password", txtSenha);
        compMap.put("layout.login.form.show.password", chbMostrarSenha);
        compMap.put("layout.login.form.button.register", btnResgistrar);

        LayoutManager.aplicarLayout(langTag, compMap);

        this.getContentPane().revalidate();
        this.getContentPane().repaint();

        if (txtLogin.getForeground().equals(Color.GRAY)) {
            addPlaceholder(txtLogin, I18nManager.getString("auth.login.form.placeholder.email"));
        }
    }

    private void estilizarComboLinguagem() {
        Locale loc = I18nManager.getCurrentLocale();
        boolean isAsiatico = loc.getLanguage().equals("ja")
                || loc.getLanguage().equals("ko")
                || loc.getLanguage().equals("zh");

        UIManager.put("ComboBox.background", new Color(11, 26, 53));
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", new Color(30, 50, 80));
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);

        cmbLinguagens.setRenderer(new javax.swing.ListCellRenderer<String>() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList<? extends String> list, String value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout());
                panel.setBackground(isSelected ? new Color(30, 50, 80) : new Color(11, 26, 53));

                if (value == null) {
                    return panel;
                }

                String[] parts = value.split(":");
                String nomeExibicao = parts[0];
                String localeTag = parts.length > 1 ? parts[1] : "";

                JLabel labelIcon = new JLabel();
                try {
                    java.net.URL imgUrl = getClass().getResource("/images/flags/" + localeTag + ".png");
                    if (imgUrl != null) {
                        java.awt.Image img = javax.imageio.ImageIO.read(imgUrl)
                                .getScaledInstance(18, 12, java.awt.Image.SCALE_SMOOTH);
                        labelIcon.setIcon(new ImageIcon(img));
                    }
                } catch (Exception e) {
                }

                labelIcon.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 5));
                panel.add(labelIcon, java.awt.BorderLayout.WEST);

                JLabel labelTexto = new JLabel(nomeExibicao, SwingConstants.CENTER);
                labelTexto.setForeground(Color.WHITE);

                if (value.matches(".*[\\u4e00-\\u9fa5\\u3040-\\u309f\\uac00-\\ud7af].*") || isAsiatico) {
                    labelTexto.setFont(new Font("SansSerif", Font.PLAIN, 12));
                } else {
                    labelTexto.setFont(FonteUtils.carregarLato(12f));
                }

                panel.add(labelTexto, java.awt.BorderLayout.CENTER);
                panel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

                return panel;
            }
        });

        cmbLinguagens.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected javax.swing.plaf.basic.ComboPopup createPopup() {
                return new javax.swing.plaf.basic.BasicComboPopup(comboBox) {
                    @Override
                    protected javax.swing.JScrollPane createScroller() {
                        javax.swing.JScrollPane scroller = new javax.swing.JScrollPane(list);
                        scroller.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                        scroller.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(0, 0));
                        scroller.setBorder(null);
                        return scroller;
                    }
                };
            }

            @Override
            protected javax.swing.JButton createArrowButton() {
                javax.swing.JButton button = new javax.swing.JButton();
                try {
                    java.net.URL url = getClass().getResource("/images/Angle Down Icon.png");
                    if (url != null) {
                        java.awt.Image img = javax.imageio.ImageIO.read(url)
                                .getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
                        button.setIcon(new javax.swing.ImageIcon(img));
                    }
                } catch (Exception e) {
                }
                button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
                button.setContentAreaFilled(false);
                return button;
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFecharTela = new javax.swing.JButton();
        btnMaximizarTela = new javax.swing.JButton();
        btnMinimizarTela = new javax.swing.JButton();
        cmbLinguagens = new javax.swing.JComboBox<>();
        jlibErroLogin = new javax.swing.JLabel();
        jlibEsqueceuASenha = new javax.swing.JLabel();
        btnEsqueceuSenha = new javax.swing.JButton();
        chbMostrarSenha = new javax.swing.JCheckBox();
        txtLogin = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        jilbEmailOuUsuario = new javax.swing.JLabel();
        jilbSenha = new javax.swing.JLabel();
        chbLembre = new javax.swing.JCheckBox();
        jilbCreditos = new javax.swing.JLabel();
        jilbAindaNaoTemConta = new javax.swing.JLabel();
        jilbRegistreSe = new javax.swing.JLabel();
        btnResgistrar = new javax.swing.JButton();
        jilbTitulo = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jilbTexto = new javax.swing.JLabel();
        jilbTexto2 = new javax.swing.JLabel();
        jilbLinha = new javax.swing.JLabel();
        jilbLinha4 = new javax.swing.JLabel();
        jilbLinha3 = new javax.swing.JLabel();
        jilbTexto3 = new javax.swing.JLabel();
        btnGoogle = new javax.swing.JButton();
        jlibBlueSquad = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CT Contab Manager");
        getContentPane().setLayout(null);

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
        cmbLinguagens.setBounds(250, 20, 130, 40);

        jlibErroLogin.setFont(FonteUtils.carregarLato(13f));
        jlibErroLogin.setForeground(new java.awt.Color(255, 0, 0));
        jlibErroLogin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlibErroLogin.setText("Seu e-mail ou senha estão incorretos.");
        getContentPane().add(jlibErroLogin);
        jlibErroLogin.setBounds(140, 310, 220, 40);

        jlibEsqueceuASenha.setFont(FonteUtils.carregarLato(13f));
        jlibEsqueceuASenha.setForeground(new java.awt.Color(16, 168, 105));
        jlibEsqueceuASenha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlibEsqueceuASenha.setText("Esqueceu a senha?");
        getContentPane().add(jlibEsqueceuASenha);
        jlibEsqueceuASenha.setBounds(190, 450, 170, 30);

        btnEsqueceuSenha.setBackground(new java.awt.Color(30, 30, 30));
        btnEsqueceuSenha.setContentAreaFilled(false);
        btnEsqueceuSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsqueceuSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(btnEsqueceuSenha);
        btnEsqueceuSenha.setBounds(250, 460, 110, 20);

        chbMostrarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-aberto.png"))); // NOI18N
        chbMostrarSenha.setInheritsPopupMenu(true);
        chbMostrarSenha.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-fechado.png"))); // NOI18N
        chbMostrarSenha.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-fechado.png"))); // NOI18N
        chbMostrarSenha.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-fechado.png"))); // NOI18N
        chbMostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMostrarSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(chbMostrarSenha);
        chbMostrarSenha.setBounds(320, 410, 30, 40);

        txtLogin.setBackground(new java.awt.Color(4, 21, 57));
        txtLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(115, 115, 115));
        txtLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        txtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoginKeyPressed(evt);
            }
        });
        getContentPane().add(txtLogin);
        txtLogin.setBounds(30, 340, 330, 40);
        addPlaceholder(txtLogin, "  email@exemplo.com");

        btnLogin.setBackground(new java.awt.Color(17, 168, 100));
        btnLogin.setFont(FonteUtils.carregarLato(14f));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLogin.setBorderPainted(false);
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLoginMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLoginMouseReleased(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLoginKeyPressed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(30, 500, 330, 40);

        txtSenha.setBackground(new java.awt.Color(4, 21, 57));
        txtSenha.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtSenha.setForeground(new java.awt.Color(115, 115, 115));
        txtSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(30, 410, 330, 40);

        jilbEmailOuUsuario.setFont(FonteUtils.carregarLato(13f));
        jilbEmailOuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jilbEmailOuUsuario.setText("Email");
        getContentPane().add(jilbEmailOuUsuario);
        jilbEmailOuUsuario.setBounds(30, 320, 190, 20);

        jilbSenha.setFont(FonteUtils.carregarLato(13f));
        jilbSenha.setForeground(new java.awt.Color(255, 255, 255));
        jilbSenha.setText("Senha");
        getContentPane().add(jilbSenha);
        jilbSenha.setBounds(30, 390, 170, 20);

        chbLembre.setFont(FonteUtils.carregarLato(13f));
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
        chbLembre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbLembreActionPerformed(evt);
            }
        });
        getContentPane().add(chbLembre);
        chbLembre.setBounds(30, 450, 100, 30);

        jilbCreditos.setBackground(new java.awt.Color(255, 255, 255));
        jilbCreditos.setFont(FonteUtils.carregarLato(13f));
        jilbCreditos.setForeground(new java.awt.Color(255, 255, 255));
        jilbCreditos.setText("  © 2025 CT Contab. Todos os direitos reservados.");
        getContentPane().add(jilbCreditos);
        jilbCreditos.setBounds(10, 700, 350, 40);

        jilbAindaNaoTemConta.setBackground(new java.awt.Color(255, 255, 255));
        jilbAindaNaoTemConta.setFont(FonteUtils.carregarLato(13f));
        jilbAindaNaoTemConta.setForeground(new java.awt.Color(255, 255, 255));
        jilbAindaNaoTemConta.setText(" Não registrado ainda?");
        getContentPane().add(jilbAindaNaoTemConta);
        jilbAindaNaoTemConta.setBounds(30, 540, 140, 20);

        jilbRegistreSe.setFont(FonteUtils.carregarLato(13f));
        jilbRegistreSe.setForeground(new java.awt.Color(16, 168, 105));
        jilbRegistreSe.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jilbRegistreSe.setText("Crie uma conta");
        getContentPane().add(jilbRegistreSe);
        jilbRegistreSe.setBounds(100, 540, 140, 20);

        btnResgistrar.setBackground(new java.awt.Color(30, 30, 30));
        btnResgistrar.setContentAreaFilled(false);
        btnResgistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResgistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnResgistrar);
        btnResgistrar.setBounds(150, 540, 120, 20);

        jilbTitulo.setFont(FonteUtils.carregarRalewayMedium(30f));
        jilbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jilbTitulo.setText("Login");
        jilbTitulo.setPreferredSize(new java.awt.Dimension(100, 100));
        getContentPane().add(jilbTitulo);
        jilbTitulo.setBounds(30, 100, 100, 50);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logoctcontab.png"))); // NOI18N
        lblLogo.setPreferredSize(new java.awt.Dimension(40, 59));
        getContentPane().add(lblLogo);
        lblLogo.setBounds(10, 20, 180, 40);

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

        jilbLinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line 1.png"))); // NOI18N
        jilbLinha.setText("jLabel1");
        getContentPane().add(jilbLinha);
        jilbLinha.setBounds(0, 0, 390, 160);

        jilbLinha4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line 1.png"))); // NOI18N
        jilbLinha4.setText("jLabel6");
        getContentPane().add(jilbLinha4);
        jilbLinha4.setBounds(280, 290, 80, 16);

        jilbLinha3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line 1.png"))); // NOI18N
        jilbLinha3.setText("jLabel6");
        getContentPane().add(jilbLinha3);
        jilbLinha3.setBounds(30, 290, 80, 16);

        jilbTexto3.setFont(FonteUtils.carregarLato(13f));
        jilbTexto3.setForeground(new java.awt.Color(255, 255, 255));
        jilbTexto3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jilbTexto3.setText("ou faça login com o email");
        getContentPane().add(jilbTexto3);
        jilbTexto3.setBounds(110, 280, 170, 30);

        btnGoogle.setBackground(new Color(0, 0, 0, 0));
        btnGoogle.setForeground(new java.awt.Color(255, 255, 255));
        btnGoogle.setFont(FonteUtils.carregarLato(13f));
        btnGoogle.setText("Sing in with Google");
        btnGoogle.setFocusPainted(false);
        btnGoogle.setIcon(new ImageIcon(getClass().getResource("/images/image 1.png")));
        btnGoogle.setHorizontalAlignment(SwingConstants.CENTER);
        btnGoogle.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnGoogle.setIconTextGap(5);
        btnGoogle.setContentAreaFilled(false);
        btnGoogle.setBorderPainted(true);
        btnGoogle.setOpaque(false);
        btnGoogle.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1, true));
        btnGoogle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoogle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoogleActionPerformed(evt);
            }
        });
        getContentPane().add(btnGoogle);
        btnGoogle.setBounds(30, 225, 330, 40);

        jlibBlueSquad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Overlay.png"))); // NOI18N
        getContentPane().add(jlibBlueSquad);
        jlibBlueSquad.setBounds(0, 0, 390, 750);

        Background.setForeground(new java.awt.Color(255, 255, 255));
        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background Login.png"))); // NOI18N
        Background.setText("jLabel3");
        getContentPane().add(Background);
        Background.setBounds(40, 0, 1440, 760);

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLoginActionPerformed
        try {
            Usuario usuarioLogado = CTCONTAB.fazerLoginU(txtLogin.getText(), new String(txtSenha.getPassword()));

            if (usuarioLogado != null) {
                txtLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                        javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                txtSenha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                        javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                        javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                if (chbLembre.isSelected()) {
                    salvarCredenciais(txtLogin.getText(), new String(txtSenha.getPassword()));
                }

                String idiomaAtualDaTela = I18nManager.getCurrentLocale().toString();
                CTCONTAB.atualizarIdiomaUsuario(txtLogin.getText(), idiomaAtualDaTela);
                new Dashboard(usuarioLogado).setVisible(true);
                dispose();
            } else {
                mostrarMensagemErro();
            }

        } catch (ClassNotFoundException | SQLException x) {
            MensagemUtil.exibirErro("Erro na conexão com o banco de dados!");
        }
    }// GEN-LAST:event_btnLoginActionPerformed

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
        jlibErroLogin.setForeground(Color.RED);
        jlibErroLogin.setVisible(true);

        txtLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtSenha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
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
                        txtLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(corSuave, 1, true),
                                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                        txtSenha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                                javax.swing.BorderFactory.createLineBorder(corSuave, 1, true),
                                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                    });

                    Thread.sleep(30);
                }

                javax.swing.SwingUtilities.invokeLater(() -> {
                    txtLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                            javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                    txtSenha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)),
                            javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void btnResgistrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnResgistrarActionPerformed
        dispose();
        new TelaRegistrar().setVisible(true);
    }// GEN-LAST:event_btnResgistrarActionPerformed

    private void chbLembreActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_chbLembreActionPerformed
    }// GEN-LAST:event_chbLembreActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtSenhaActionPerformed

    private void chbMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_chbMostrarSenhaActionPerformed
        if (chbMostrarSenha.isSelected()) {
            txtSenha.setEchoChar((char) 0);
        } else {
            txtSenha.setEchoChar('•');
        }
    }// GEN-LAST:event_chbMostrarSenhaActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtLoginActionPerformed

    private void btnEsqueceuSenhaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEsqueceuSenhaActionPerformed
    }// GEN-LAST:event_btnEsqueceuSenhaActionPerformed

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnLoginMouseEntered
        btnLogin.setBackground(new java.awt.Color(20, 190, 115));
    }// GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnLoginMouseExited
        btnLogin.setBackground(new java.awt.Color(17, 168, 100));
    }// GEN-LAST:event_btnLoginMouseExited

    private void btnLoginMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnLoginMousePressed
        btnLogin.setBackground(new java.awt.Color(14, 140, 85));
    }// GEN-LAST:event_btnLoginMousePressed

    private void btnLoginMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnLoginMouseReleased
        btnLogin.setBackground(new java.awt.Color(17, 168, 100));
    }// GEN-LAST:event_btnLoginMouseReleased

    private void btnLoginKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_btnLoginKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            btnLogin.doClick();
        }
    }// GEN-LAST:event_btnLoginKeyPressed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtSenhaKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            btnLogin.doClick();
        }
    }// GEN-LAST:event_txtSenhaKeyPressed

    private void txtLoginKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtLoginKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            btnLogin.doClick();
        }
    }// GEN-LAST:event_txtLoginKeyPressed

    private void btnGoogleActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGoogleActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnGoogleActionPerformed

    private void cmbLinguagensActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cmbLinguagensActionPerformed
        Object selected = cmbLinguagens.getSelectedItem();
        if (selected != null) {
            String item = selected.toString();
            if (item.contains(":")) {
                String localeTag = item.split(":")[1];
                String[] parts = localeTag.split("_");
                I18nManager.setLocale(new Locale(parts[0], parts[1]));
                atualizarTextos();
                estilizarComboLinguagem();
            }
        }
    }// GEN-LAST:event_cmbLinguagensActionPerformed

    private void btnFecharTelaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFecharTelaActionPerformed
        System.exit(0);
    }// GEN-LAST:event_btnFecharTelaActionPerformed

    private void btnMaximizarTelaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMaximizarTelaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnMaximizarTelaActionPerformed

    private void btnMinimizarTelaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnMinimizarTelaMouseClicked
        setState(javax.swing.JFrame.ICONIFIED);
    }// GEN-LAST:event_btnMinimizarTelaMouseClicked

    private void btnMinimizarTelaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMinimizarTelaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnMinimizarTelaActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            TelaUpdateLoadingOverview splashUpdate = new TelaUpdateLoadingOverview();
            splashUpdate.setVisible(true);

            javax.swing.Timer timer = new javax.swing.Timer(3000, e -> {
                splashUpdate.dispose();
                TelaLoadingOverview splashLoading = new TelaLoadingOverview();
                splashLoading.setVisible(true);

                javax.swing.Timer timer2 = new javax.swing.Timer(3500, e2 -> {
                    splashLoading.dispose();
                    new TelaLogin().setVisible(true);
                });
                timer2.setRepeats(false);
                timer2.start();
            });
            timer.setRepeats(false);
            timer.start();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnEsqueceuSenha;
    private javax.swing.JButton btnFecharTela;
    private javax.swing.JButton btnGoogle;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnMaximizarTela;
    private javax.swing.JButton btnMinimizarTela;
    private javax.swing.JButton btnResgistrar;
    private javax.swing.JCheckBox chbLembre;
    private javax.swing.JCheckBox chbMostrarSenha;
    private javax.swing.JComboBox<String> cmbLinguagens;
    private javax.swing.JLabel jilbAindaNaoTemConta;
    private javax.swing.JLabel jilbCreditos;
    private javax.swing.JLabel jilbEmailOuUsuario;
    private javax.swing.JLabel jilbLinha;
    private javax.swing.JLabel jilbLinha3;
    private javax.swing.JLabel jilbLinha4;
    private javax.swing.JLabel jilbRegistreSe;
    private javax.swing.JLabel jilbSenha;
    private javax.swing.JLabel jilbTexto;
    private javax.swing.JLabel jilbTexto2;
    private javax.swing.JLabel jilbTexto3;
    private javax.swing.JLabel jilbTitulo;
    private javax.swing.JLabel jlibBlueSquad;
    private javax.swing.JLabel jlibErroLogin;
    private javax.swing.JLabel jlibEsqueceuASenha;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
