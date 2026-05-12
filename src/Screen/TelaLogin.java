package screen;

import Data.Usuario;
import Data.CTCONTAB;
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
import Data.I18nManager;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import Data.LayoutManager;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import javax.imageio.ImageIO;

public class TelaLogin extends javax.swing.JFrame {

    private final Color COR_PADRAO = new Color(4, 19, 53);
    private final Color COR_HOVER_GERAL = new Color(26, 41, 75);
    private final Color COR_HOVER_FECHAR = Color.RED;
    private final String CAMINHO_CONFIG = System.getProperty("user.home") + File.separator + ".ctcontab";
    private final String NOME_ARQUIVO = "last_session.properties";

    public TelaLogin() {
        initComponents();
        setUndecorated(true);
        setResizable(false);
        setIcon();
        configurarLinguagens();
        atualizarTextos();
        aplicarImagensDeAltaQualidade();
        carregarCredenciais();
        jlibErroLogin.setVisible(false);
        setBackground(new java.awt.Color(0, 0, 0, 0));
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

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
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Logo Icon.png")));
    }

    private void aplicarImagensDeAltaQualidade() {
        try {
            BufferedImage LogoComplet = ImageIO.read(getClass().getResource("/images/Complet Logo Icon.png"));
            BufferedImage closeNormal = ImageIO.read(getClass().getResource("/images/Close Icon.png"));
            BufferedImage closeWhite = ImageIO.read(getClass().getResource("/images/Close White Icon.png"));
            BufferedImage minNormal = ImageIO.read(getClass().getResource("/images/Minimize Icon.png"));
            BufferedImage minWhite = ImageIO.read(getClass().getResource("/images/Minimize White Icon.png"));
            BufferedImage maxNormal = ImageIO.read(getClass().getResource("/images/Maximize Icon.png"));
            BufferedImage maxWhite = ImageIO.read(getClass().getResource("/images/Maximize White Icon.png"));
            BufferedImage Background = ImageIO.read(getClass().getResource("/images/Background Login.png"));
            BufferedImage OpenEye = ImageIO.read(getClass().getResource("/images/Eye Password.png"));
            BufferedImage CloseEye = ImageIO.read(getClass().getResource("/images/Eye Password Close.png"));

            configurarBotaoControle(btnFecharTela, closeNormal, closeWhite, 11, 11, COR_HOVER_FECHAR);
            configurarBotaoControle(btnMinimizarTela, minNormal, minWhite, 11, 2, COR_HOVER_GERAL);
            configurarBotaoControle(btnMaximizarTela, maxNormal, maxWhite, 11, 11, COR_HOVER_GERAL);

            lblLogo.setIcon(gerarIconePerfeito(LogoComplet, 170, 40));
            lblBackground.setIcon(gerarIconePerfeito(Background, 1440, 760));
            chbMostrarSenha.setIcon(gerarIconePerfeito(OpenEye, 18, 18));
            chbMostrarSenha.setRolloverIcon(gerarIconePerfeito(CloseEye, 18, 18));
            chbMostrarSenha.setRolloverSelectedIcon(gerarIconePerfeito(CloseEye, 18, 18));
            chbMostrarSenha.setSelectedIcon(gerarIconePerfeito(CloseEye, 18, 18));

        } catch (IOException e) {
            System.err.println("Erro ao carregar recursos: " + e.getMessage());
        }
    }

    private void configurarBotaoControle(javax.swing.JButton botao, BufferedImage normal, BufferedImage hover, int w, int h, Color corHover) {
        javax.swing.Icon iconNormal = gerarIconePerfeito(normal, w, h);
        javax.swing.Icon iconHover = gerarIconePerfeito(hover, w, h);

        botao.setIcon(iconNormal);
        botao.setRolloverIcon(iconHover);
        botao.setBackground(COR_PADRAO);

        botao.setBorder(null);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setMargin(new java.awt.Insets(0, 0, 0, 0));
        botao.setContentAreaFilled(false);

        botao.setOpaque(true);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setText("");

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(corHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(COR_PADRAO);
            }
        });
    }

    private javax.swing.Icon gerarIconePerfeito(BufferedImage img, int targetW, int targetH) {
        int scaleFactor = 2;
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage lastImg = img;

        int wAlvoRenderizacao = targetW * scaleFactor;
        int hAlvoRenderizacao = targetH * scaleFactor;

        while (w > wAlvoRenderizacao || h > hAlvoRenderizacao) {
            w = Math.max(w / 2, wAlvoRenderizacao);
            h = Math.max(h / 2, hAlvoRenderizacao);

            BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(lastImg, 0, 0, w, h, null);
            g2.dispose();
            lastImg = tmp;
        }

        return new IconeSuave(lastImg, targetW, targetH);
    }

    private void salvarCredenciais(String usuario, String senha) {
        try {
            File pasta = new File(CAMINHO_CONFIG);
            if (!pasta.exists()) {
                pasta.mkdirs();
            }
            File arquivo = new File(pasta, NOME_ARQUIVO);
            Properties props = new Properties();

            props.setProperty("usuario", usuario);
            props.setProperty("senha", senha);

            try (FileOutputStream out = new FileOutputStream(arquivo)) {
                props.store(out, "Ultimo login realizado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarCredenciais() {
        File arquivo = new File(CAMINHO_CONFIG, NOME_ARQUIVO);
        if (arquivo.exists()) {
            Properties props = new Properties();
            try (FileInputStream in = new FileInputStream(arquivo)) {
                props.load(in);
                String usuario = props.getProperty("usuario");
                String senha = props.getProperty("senha");

                if (usuario != null && !usuario.isEmpty()) {
                    txtLogin.setText(usuario);
                    txtLogin.setForeground(Color.WHITE);

                    txtSenha.setText(senha);
                    txtSenha.setForeground(Color.WHITE);

                    chbLembre.setSelected(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
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
        addPlaceholder(txtLogin, I18nManager.getString("auth.login.form.placeholder.email"));

        Map<String, JComponent> compMap = new HashMap<>();
        compMap.put("layout.login.header.title", jilbTitulo);
        compMap.put("layout.login.header.description", jilbTexto);
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
                        java.awt.image.BufferedImage flagImg = javax.imageio.ImageIO.read(imgUrl);
                        labelIcon.setIcon(gerarIconePerfeito(flagImg, 18, 12));
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
                carregarCredenciais();
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
                        java.awt.image.BufferedImage angleImg = javax.imageio.ImageIO.read(url);
                        button.setIcon(gerarIconePerfeito(angleImg, 10, 10));
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
        btnMinimizarTela = new javax.swing.JButton();
        btnMaximizarTela = new javax.swing.JButton();
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
        jilbLinha = new javax.swing.JLabel();
        jilbLinha4 = new javax.swing.JLabel();
        jilbLinha3 = new javax.swing.JLabel();
        jilbTexto3 = new javax.swing.JLabel();
        btnGoogle = new javax.swing.JButton();
        jlibBlueSquad = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CT Contab Manager");
        getContentPane().setLayout(null);

        btnFecharTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        btnFecharTela.setContentAreaFilled(false);
        btnFecharTela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFecharTelaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFecharTelaMouseExited(evt);
            }
        });
        btnFecharTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnFecharTela);
        btnFecharTela.setBounds(1420, 0, 30, 25);

        btnMinimizarTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minimize Icon.png"))); // NOI18N
        btnMinimizarTela.setContentAreaFilled(false);
        btnMinimizarTela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizarTelaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizarTelaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizarTelaMouseExited(evt);
            }
        });
        btnMinimizarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnMinimizarTela);
        btnMinimizarTela.setBounds(1360, 0, 30, 25);

        btnMaximizarTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Maximize Icon.png"))); // NOI18N
        btnMaximizarTela.setContentAreaFilled(false);
        btnMaximizarTela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMaximizarTelaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMaximizarTelaMouseExited(evt);
            }
        });
        btnMaximizarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizarTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnMaximizarTela);
        btnMaximizarTela.setBounds(1390, 0, 30, 25);

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
        jlibErroLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibErroLogin.setText("Seu e-mail ou senha estão incorretos.");
        getContentPane().add(jlibErroLogin);
        jlibErroLogin.setBounds(30, 560, 330, 20);

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

        chbMostrarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Eye Password.png"))); // NOI18N
        chbMostrarSenha.setInheritsPopupMenu(true);
        chbMostrarSenha.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Eye Password Close.png"))); // NOI18N
        chbMostrarSenha.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Eye Password Close.png"))); // NOI18N
        chbMostrarSenha.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Eye Password Close.png"))); // NOI18N
        chbMostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMostrarSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(chbMostrarSenha);
        chbMostrarSenha.setBounds(330, 410, 30, 40);

        txtLogin.setBackground(new java.awt.Color(4, 21, 57));
        txtLogin.setFont(FonteUtils.carregarLato(12f));
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
        txtSenha.setFont(FonteUtils.carregarLato(12f));
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
        jilbEmailOuUsuario.setText("Email ou Nome de usuário");
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
        jilbCreditos.setBounds(10, 710, 350, 40);

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

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Complet Logo Icon.png"))); // NOI18N
        lblLogo.setPreferredSize(new java.awt.Dimension(40, 59));
        getContentPane().add(lblLogo);
        lblLogo.setBounds(10, 20, 180, 40);

        jilbTexto.setFont(FonteUtils.carregarLato(13f));
        jilbTexto.setForeground(new java.awt.Color(255, 255, 255));
        jilbTexto.setText("<html><p style=\"text-align: justify;\">Toda a organização da sua contabilidade em um só lugar, totalmente digital.</p></html>");
        getContentPane().add(jilbTexto);
        jilbTexto.setBounds(30, 140, 330, 80);

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
        try {
            java.net.URL url = getClass().getResource("/images/Google Icon.png");
            if (url != null) {
                java.awt.image.BufferedImage Google = javax.imageio.ImageIO.read(url);
                btnGoogle.setIcon(gerarIconePerfeito(Google, 22, 22));
            }
        } catch (Exception e) {
        }
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

        lblBackground.setForeground(new java.awt.Color(255, 255, 255));
        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background Login.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(40, 0, 1440, 760);

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharTelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharTelaMouseEntered

    }//GEN-LAST:event_btnFecharTelaMouseEntered

    private void btnFecharTelaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharTelaMouseExited

    }//GEN-LAST:event_btnFecharTelaMouseExited

    private void btnFecharTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharTelaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnFecharTelaActionPerformed

    private void btnMinimizarTelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarTelaMouseClicked
        setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarTelaMouseClicked

    private void btnMinimizarTelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarTelaMouseEntered

    }//GEN-LAST:event_btnMinimizarTelaMouseEntered

    private void btnMinimizarTelaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarTelaMouseExited

    }//GEN-LAST:event_btnMinimizarTelaMouseExited

    private void btnMinimizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMinimizarTelaActionPerformed

    private void btnMaximizarTelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizarTelaMouseEntered

    }//GEN-LAST:event_btnMaximizarTelaMouseEntered

    private void btnMaximizarTelaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizarTelaMouseExited

    }//GEN-LAST:event_btnMaximizarTelaMouseExited

    private void btnMaximizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaximizarTelaActionPerformed

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
                } else {
                    new File(CAMINHO_CONFIG, NOME_ARQUIVO).delete();
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
        txtLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtSenha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 1, true),
                javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));

        jlibErroLogin.setForeground(Color.RED);
        jlibErroLogin.setVisible(true);

        new Thread(() -> {
            try {
                Thread.sleep(4000);

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

                jlibErroLogin.setVisible(false);

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

    class IconeSuave implements javax.swing.Icon {

        private final Image imagem;
        private final int larguraExibicao;
        private final int alturaExibicao;

        public IconeSuave(Image imagem, int larguraExibicao, int alturaExibicao) {
            this.imagem = imagem;
            this.larguraExibicao = larguraExibicao;
            this.alturaExibicao = alturaExibicao;
        }

        @Override
        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(imagem, x, y, larguraExibicao, alturaExibicao, null);
            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return larguraExibicao;
        }

        @Override
        public int getIconHeight() {
            return alturaExibicao;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jilbTexto3;
    private javax.swing.JLabel jilbTitulo;
    private javax.swing.JLabel jlibBlueSquad;
    private javax.swing.JLabel jlibErroLogin;
    private javax.swing.JLabel jlibEsqueceuASenha;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
