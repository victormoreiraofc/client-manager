package screen;

import Data.CTCONTAB;
import Data.I18nManager;
import Data.Usuario;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Login extends javax.swing.JFrame {

    private final Color DEFAULT_COLOR = new Color(25, 25, 25);
    private final Color GENERAL_HOVER_COLOR = new Color(45, 45, 45);
    private final Color CLOSE_HOVER_COLOR = new Color(196, 43, 28);
    private static final int MAX_LENGTH = 50;

    public Login() {
        initComponents();
        setUndecorated(true);
        setIcon();
        setImages();
        localizeAuthScreen();
        populateLanguageSelector();
        setBackground(new java.awt.Color(0, 0, 0, 0));
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
        ((RoundedPanel) pnlButtonRestart).setCustomBorder(new Color(28, 37, 54), 2);
        setupInputField((RoundedTextField) usernameField, "Usuário", 50);
        setupInputField((RoundedTextField) passwordField, "Senha", 50);
        lblDefaultUser.setVisible(true);
        lblHoverUser.setVisible(false);
        lblContainUser.setVisible(false);
        lblDefaultPassword.setVisible(true);
        lblHoverPassword.setVisible(false);
        lblContainPassword.setVisible(false);
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Logo Icon.png")));
    }

    private void setImages() {
        try {
            BufferedImage LogoIcon = ImageIO.read(getClass().getResource("/images/Logo Icon Black White.png"));
            BufferedImage CloseDarkWhiteIcon = ImageIO.read(getClass().getResource("/images/Close Dark White Icon.png"));
            BufferedImage MaximizeDarkWhiteIcon = ImageIO.read(getClass().getResource("/images/Maximize Dark White Icon.png"));
            BufferedImage MinimizeWhiteDarkIcon = ImageIO.read(getClass().getResource("/images/Minimize White Dark Icon.png"));
            BufferedImage GoogleIcon = ImageIO.read(getClass().getResource("/images/Google Icon.png"));
            BufferedImage AppleIcon = ImageIO.read(getClass().getResource("/images/Apple Icon.png"));

            ControlButton(closeButton, CloseDarkWhiteIcon, CloseDarkWhiteIcon, 12, 12, CLOSE_HOVER_COLOR);
            ControlButton(maximizeButton, MaximizeDarkWhiteIcon, MaximizeDarkWhiteIcon, 12, 12, GENERAL_HOVER_COLOR);
            ControlButton(minimizeButton, MinimizeWhiteDarkIcon, MinimizeWhiteDarkIcon, 12, 2, GENERAL_HOVER_COLOR);

            lblLogo.setIcon(HighQualityImage(LogoIcon, 30, 30));
            lblLogoGoogle.setIcon(HighQualityImage(GoogleIcon, 20, 20));
            lblLogoApple.setIcon(HighQualityImage(AppleIcon, 20, 20));

        } catch (IOException e) {
            System.err.println("Erro ao carregar recursos: " + e.getMessage());
        }
    }

    private void ControlButton(javax.swing.JButton botao, BufferedImage normal, BufferedImage hover, int w, int h, Color corHover) {
        javax.swing.Icon iconNormal = HighQualityImage(normal, w, h);
        javax.swing.Icon iconHover = HighQualityImage(hover, w, h);

        botao.setIcon(iconNormal);
        botao.setRolloverIcon(iconHover);
        botao.setBackground(DEFAULT_COLOR);

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
                botao.setBackground(DEFAULT_COLOR);
            }
        });
    }

    private javax.swing.Icon HighQualityImage(BufferedImage img, int targetW, int targetH) {
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

        return new SoftIcon(lastImg, targetW, targetH);
    }

    private void setupInputField(RoundedTextField campo, String label, int limite) {
        campo.setLabelTexto(label);
        campo.setLimiteCaracteres(limite);
        campo.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 0, 15));
        campo.setText("");

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                campo.setAtivado(true);
                campo.repaint();
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                campo.setAtivado(false);
                campo.repaint();
            }
        });

        campo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                if (campo.getText().length() > limite) {
                    campo.setText(campo.getText().substring(0, limite));
                }
                campo.repaint();
            }
        });
    }

    private void applyRegionalTypography(java.awt.Container container, boolean isAsiatico) {
        for (java.awt.Component c : container.getComponents()) {

            Font fonteAtual = c.getFont();
            int tamanho = fonteAtual.getSize();
            int estilo = fonteAtual.getStyle();

            if (isAsiatico) {
                c.setFont(new Font("SansSerif", estilo, tamanho));
            } else {

            }

            if (c instanceof java.awt.Container) {
                applyRegionalTypography((java.awt.Container) c, isAsiatico);
            }
        }
    }

    private void localizeAuthScreen() {
        Locale loc = I18nManager.getCurrentLocale();
        String langTag = loc.toString();
        boolean isAsiatico = loc.getLanguage().equals("ja") || loc.getLanguage().equals("ko") || loc.getLanguage().equals("zh");

        applyRegionalTypography(this.getContentPane(), isAsiatico);

        setTitle(I18nManager.getString("auth.login.window_title"));
        lblTitle.setText(I18nManager.getString("auth.login.header.title"));
        lblEnterGoogle.setText(I18nManager.getString("auth.login.social.google"));
        lblEnterApple.setText(I18nManager.getString("auth.login.social.apple"));
        lblOr.setText(I18nManager.getString("auth.login.divider.text"));
        lblUnderstandTitle.setText(I18nManager.getString("auth.login.confirm"));
        lblRestartTitle.setText(I18nManager.getString("auth.login.forgotpassword"));
        btnNotAcount.setText(I18nManager.getString("auth.login.newacount"));
        lblHoverUser.setText(I18nManager.getString("auth.login.hover.user"));
        lblDefaultUser.setText(I18nManager.getString("auth.login.default.user"));
        lblHoverPassword.setText(I18nManager.getString("auth.login.hover.password"));
        lblDefaultPassword.setText(I18nManager.getString("auth.login.default.password"));

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    private void updateFieldCounter(javax.swing.JTextField textField, javax.swing.JLabel feedbackLabel) {
        int currentLength = textField.getText().length();
        feedbackLabel.setText(currentLength + " / " + MAX_LENGTH);
    }

    private void populateLanguageSelector() {
        languageComboBox.removeAllItems();
        languageComboBox.addItem("Português:pt_BR");
        languageComboBox.addItem("English:en_US");
        languageComboBox.addItem("Español:es_ES");
        languageComboBox.addItem("Deutsch:de_DE");
        languageComboBox.addItem("Français:fr_FR");
        languageComboBox.addItem("Italiano:it_IT");
        languageComboBox.addItem("日本語:ja_JP");
        languageComboBox.addItem("한국어:ko_KR");
        languageComboBox.addItem("中文:zh_CN");

        Locale currentLocale = I18nManager.getCurrentLocale();
        String currentLocaleTag = currentLocale.getLanguage() + "_" + currentLocale.getCountry();

        for (int i = 0; i < languageComboBox.getItemCount(); i++) {
            if (languageComboBox.getItemAt(i).contains(":" + currentLocaleTag)) {
                languageComboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void customizeLanguageSelectorUI() {
        Locale loc = I18nManager.getCurrentLocale();
        boolean isAsiatico = loc.getLanguage().equals("ja")
                || loc.getLanguage().equals("ko")
                || loc.getLanguage().equals("zh");

        UIManager.put("ComboBox.background", new Color(25, 25, 25));
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", new Color(45, 45, 45));
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);

        languageComboBox.setRenderer(new javax.swing.ListCellRenderer<String>() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList<? extends String> list, String value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout());
                panel.setBackground(isSelected ? new Color(45, 45, 45) : new Color(25, 25, 25));

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
                        labelIcon.setIcon(HighQualityImage(flagImg, 18, 12));
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
                    labelTexto.setFont(FonteUtils.carregarRobotoBold(12f));
                }

                panel.add(labelTexto, java.awt.BorderLayout.CENTER);
                panel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
                //carregarCredenciais();
                return panel;
            }
        });

        languageComboBox.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
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
                        button.setIcon(HighQualityImage(angleImg, 10, 10));
                    }
                } catch (Exception e) {
                }
                button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
                button.setContentAreaFilled(false);
                return button;
            }
        });
    }

    private void handleFormNavigation(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() != java.awt.event.KeyEvent.VK_ENTER) {
            return;
        }

        if (isFieldEmpty(usernameField)) {
            usernameField.requestFocusInWindow();
        } else if (isFieldEmpty(passwordField)) {
            passwordField.requestFocusInWindow();
        } else {
            submitButton.doClick();
        }
    }

    private boolean isFieldEmpty(javax.swing.JTextField field) {
        return field.getText().trim().isEmpty();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNotAcount = new javax.swing.JButton();
        lblContainPassword = new javax.swing.JLabel();
        lblContainUser = new javax.swing.JLabel();
        lblHoverPassword = new javax.swing.JLabel();
        lblHoverUser = new javax.swing.JLabel();
        lblDefaultUser = new javax.swing.JLabel();
        lblDefaultPassword = new javax.swing.JLabel();
        languageComboBox = new javax.swing.JComboBox<>();
        passwordField = new RoundedTextField(10);
        usernameField = new RoundedTextField(10);
        lblOr = new javax.swing.JLabel();
        horizontalRightDivider = new javax.swing.JSeparator();
        horizontalLeftDivider = new javax.swing.JSeparator();
        btnEnterApple = new javax.swing.JButton();
        pnlButtonApple = new RoundedPanel(40);
        lblLogoApple = new javax.swing.JLabel();
        lblEnterApple = new javax.swing.JLabel();
        btnEnterGoogle = new javax.swing.JButton();
        pnlButtonGoogle = new RoundedPanel(40);
        lblLogoGoogle = new javax.swing.JLabel();
        lblEnterGoogle = new javax.swing.JLabel();
        forgotPasswordButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        lblRestartTitle = new javax.swing.JLabel();
        pnlButtonRestart = new RoundedPanel(40);
        lblUnderstandTitle = new javax.swing.JLabel();
        pnlButtonUnderstand = new RoundedPanel(40);
        lblTitle = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        minimizeButton = new javax.swing.JButton();
        maximizeButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        pnlMainBackground = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        btnNotAcount.setBackground(new java.awt.Color(237, 241, 242));
        btnNotAcount.setFont(FonteUtils.carregarRobotoBold(13f));
        btnNotAcount.setText("<html><font color='#48494B'>Não tem uma conta? </font><font color='#287FCE'><b>Inscreva-se</b></font></html>");
        btnNotAcount.setContentAreaFilled(false);
        btnNotAcount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNotAcount.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNotAcount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNotAcountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNotAcountMouseExited(evt);
            }
        });
        btnNotAcount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotAcountActionPerformed(evt);
            }
        });
        getContentPane().add(btnNotAcount);
        btnNotAcount.setBounds(495, 589, 330, 25);

        lblContainPassword.setFont(FonteUtils.carregarRobotoBold(11f));
        lblContainPassword.setForeground(new java.awt.Color(102, 107, 111));
        lblContainPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblContainPassword.setText("0 / 50");
        getContentPane().add(lblContainPassword);
        lblContainPassword.setBounds(730, 434, 35, 14);

        lblContainUser.setFont(FonteUtils.carregarRobotoBold(11f));
        lblContainUser.setForeground(new java.awt.Color(102, 107, 111));
        lblContainUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblContainUser.setText("0 / 50");
        getContentPane().add(lblContainUser);
        lblContainUser.setBounds(730, 373, 35, 14);

        lblHoverPassword.setFont(FonteUtils.carregarRobotoBold(11f));
        lblHoverPassword.setForeground(new java.awt.Color(40, 127, 206));
        lblHoverPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHoverPassword.setText("Senha");
        getContentPane().add(lblHoverPassword);
        lblHoverPassword.setBounds(515, 434, 182, 14);

        lblHoverUser.setFont(FonteUtils.carregarRobotoBold(11f));
        lblHoverUser.setForeground(new java.awt.Color(40, 127, 206));
        lblHoverUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHoverUser.setText("Usuário");
        getContentPane().add(lblHoverUser);
        lblHoverUser.setBounds(515, 373, 182, 14);

        lblDefaultUser.setFont(FonteUtils.carregarRobotoBold(14f));
        lblDefaultUser.setForeground(new java.awt.Color(94, 99, 103));
        lblDefaultUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDefaultUser.setText("Usuário");
        getContentPane().add(lblDefaultUser);
        lblDefaultUser.setBounds(515, 381, 247, 25);

        lblDefaultPassword.setFont(FonteUtils.carregarRobotoBold(14f));
        lblDefaultPassword.setForeground(new java.awt.Color(94, 99, 103));
        lblDefaultPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDefaultPassword.setText("Senha");
        getContentPane().add(lblDefaultPassword);
        lblDefaultPassword.setBounds(515, 441, 247, 25);

        languageComboBox.setBackground(new java.awt.Color(25, 25, 25));
        languageComboBox.setFont(FonteUtils.carregarRobotoBold(12f));
        languageComboBox.setForeground(new java.awt.Color(255, 255, 255));
        languageComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Português" }));
        languageComboBox.setBorder(null);
        languageComboBox.setOpaque(false);
        languageComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                languageComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(languageComboBox);
        languageComboBox.setBounds(1134, 50, 130, 25);

        passwordField.setBackground(new java.awt.Color(25, 25, 25));
        passwordField.setFont(FonteUtils.carregarRobotoBold(13f));
        passwordField.setForeground(new java.awt.Color(255, 255, 255));
        passwordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        });
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passwordFieldKeyTyped(evt);
            }
        });
        getContentPane().add(passwordField);
        passwordField.setBounds(507, 429, 265, 49);

        usernameField.setBackground(new java.awt.Color(25, 25, 25));
        usernameField.setFont(FonteUtils.carregarRobotoBold(13f));
        usernameField.setForeground(new java.awt.Color(255, 255, 255));
        usernameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFieldFocusLost(evt);
            }
        });
        usernameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usernameFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                usernameFieldMousePressed(evt);
            }
        });
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });
        usernameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameFieldKeyTyped(evt);
            }
        });
        getContentPane().add(usernameField);
        usernameField.setBounds(507, 369, 265, 49);

        lblOr.setFont(FonteUtils.carregarRobotoBold(13f));
        lblOr.setForeground(new java.awt.Color(209, 213, 219));
        lblOr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOr.setText("Ou");
        getContentPane().add(lblOr);
        lblOr.setBounds(545, 334, 182, 14);

        horizontalRightDivider.setBackground(new java.awt.Color(217, 217, 217));
        getContentPane().add(horizontalRightDivider);
        horizontalRightDivider.setBounds(652, 341, 114, 2);

        horizontalLeftDivider.setBackground(new java.awt.Color(217, 217, 217));
        getContentPane().add(horizontalLeftDivider);
        horizontalLeftDivider.setBounds(507, 341, 114, 2);

        btnEnterApple.setBackground(new java.awt.Color(237, 241, 242));
        btnEnterApple.setContentAreaFilled(false);
        btnEnterApple.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEnterAppleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEnterAppleMouseExited(evt);
            }
        });
        btnEnterApple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterAppleActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnterApple);
        btnEnterApple.setBounds(507, 276, 268, 36);

        pnlButtonApple.setBackground(new java.awt.Color(237, 241, 242));
        pnlButtonApple.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlButtonAppleMouseEntered(evt);
            }
        });
        pnlButtonApple.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 8));

        lblLogoApple.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogoApple.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlButtonApple.add(lblLogoApple);

        lblEnterApple.setFont(FonteUtils.carregarRobotoBold(13f));
        lblEnterApple.setForeground(new java.awt.Color(9, 10, 12));
        lblEnterApple.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEnterApple.setText("Entrar com Apple");
        lblEnterApple.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlButtonApple.add(lblEnterApple);

        getContentPane().add(pnlButtonApple);
        pnlButtonApple.setBounds(507, 276, 268, 36);

        btnEnterGoogle.setBackground(new java.awt.Color(237, 241, 242));
        btnEnterGoogle.setContentAreaFilled(false);
        btnEnterGoogle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEnterGoogleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEnterGoogleMouseExited(evt);
            }
        });
        btnEnterGoogle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterGoogleActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnterGoogle);
        btnEnterGoogle.setBounds(507, 233, 268, 36);

        pnlButtonGoogle.setBackground(new java.awt.Color(237, 241, 242));
        pnlButtonGoogle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlButtonGoogleMouseEntered(evt);
            }
        });
        pnlButtonGoogle.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 8));

        lblLogoGoogle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlButtonGoogle.add(lblLogoGoogle);

        lblEnterGoogle.setFont(FonteUtils.carregarRobotoBold(13f));
        lblEnterGoogle.setForeground(new java.awt.Color(9, 10, 12));
        lblEnterGoogle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEnterGoogle.setText("Fazer login com o Google");
        lblEnterGoogle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlButtonGoogle.add(lblEnterGoogle);

        getContentPane().add(pnlButtonGoogle);
        pnlButtonGoogle.setBounds(507, 233, 268, 36);

        forgotPasswordButton.setBackground(new java.awt.Color(25, 25, 25));
        forgotPasswordButton.setBorder(null);
        forgotPasswordButton.setContentAreaFilled(false);
        forgotPasswordButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgotPasswordButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                forgotPasswordButtonMouseExited(evt);
            }
        });
        forgotPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgotPasswordButtonActionPerformed(evt);
            }
        });
        getContentPane().add(forgotPasswordButton);
        forgotPasswordButton.setBounds(506, 546, 268, 36);

        submitButton.setBackground(new java.awt.Color(237, 241, 242));
        submitButton.setContentAreaFilled(false);
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButtonMouseExited(evt);
            }
        });
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(submitButton);
        submitButton.setBounds(507, 503, 268, 36);

        lblRestartTitle.setFont(FonteUtils.carregarRobotoBold(13f));
        lblRestartTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblRestartTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRestartTitle.setText("Esqueceu sua senha?");
        getContentPane().add(lblRestartTitle);
        lblRestartTitle.setBounds(506, 557, 268, 14);

        pnlButtonRestart.setBackground(new java.awt.Color(25, 25, 25));
        getContentPane().add(pnlButtonRestart);
        pnlButtonRestart.setBounds(506, 546, 268, 36);

        lblUnderstandTitle.setFont(FonteUtils.carregarRobotoBold(13f));
        lblUnderstandTitle.setForeground(new java.awt.Color(9, 10, 12));
        lblUnderstandTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUnderstandTitle.setText("Avançar");
        getContentPane().add(lblUnderstandTitle);
        lblUnderstandTitle.setBounds(549, 513, 182, 14);

        pnlButtonUnderstand.setBackground(new java.awt.Color(237, 241, 242));
        pnlButtonUnderstand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlButtonUnderstandMouseEntered(evt);
            }
        });
        getContentPane().add(pnlButtonUnderstand);
        pnlButtonUnderstand.setBounds(507, 503, 268, 36);

        lblTitle.setFont(FonteUtils.carregarRobotoBold(30f));
        lblTitle.setForeground(new java.awt.Color(209, 213, 219));
        lblTitle.setText("Acesse sua conta");
        lblTitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblTitle);
        lblTitle.setBounds(504, 168, 450, 40);
        getContentPane().add(lblLogo);
        lblLogo.setBounds(624, 106, 30, 30);

        minimizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minimize White Dark Icon.png"))); // NOI18N
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimizeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(minimizeButton);
        minimizeButton.setBounds(1160, 0, 40, 40);

        maximizeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Maximize Dark White Icon.png"))); // NOI18N
        maximizeButton.setContentAreaFilled(false);
        maximizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maximizeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(maximizeButton);
        maximizeButton.setBounds(1200, 0, 40, 40);

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Dark White Icon.png"))); // NOI18N
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeButton);
        closeButton.setBounds(1240, 0, 40, 40);

        pnlMainBackground.setBackground(new java.awt.Color(25, 25, 25));
        getContentPane().add(pnlMainBackground);
        pnlMainBackground.setBounds(0, 0, 1280, 720);

        setSize(new java.awt.Dimension(1280, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void minimizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimizeButtonActionPerformed
        setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeButtonActionPerformed

    private void maximizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maximizeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maximizeButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void pnlButtonUnderstandMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlButtonUnderstandMouseEntered

    }//GEN-LAST:event_pnlButtonUnderstandMouseEntered

    private void submitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseEntered
        pnlButtonUnderstand.setBackground(new java.awt.Color(190, 190, 190));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_submitButtonMouseEntered

    private void submitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseExited
        pnlButtonUnderstand.setBackground(new java.awt.Color(237, 241, 242));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_submitButtonMouseExited

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        try {
            Usuario usuarioLogado = CTCONTAB.fazerLoginU(usernameField.getText(), new String(passwordField.getText()));

            if (usuarioLogado != null) {
                String idiomaAtualDaTela = I18nManager.getCurrentLocale().toString();
                CTCONTAB.atualizarIdiomaUsuario(usernameField.getText(), idiomaAtualDaTela);
                new Home(usuarioLogado).setVisible(true);
                dispose();
            } else {
                //System.out.println("Falta as informações");
            }

        } catch (ClassNotFoundException | SQLException x) {
            MensagemUtil.exibirErro("Não conseguimos conectar ao banco de dados no momento. Por favor, verifique sua internet ou tente novamente mais tarde.");
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void forgotPasswordButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotPasswordButtonMouseEntered
        pnlButtonRestart.setBackground(new java.awt.Color(50, 50, 50));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_forgotPasswordButtonMouseEntered

    private void forgotPasswordButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotPasswordButtonMouseExited
        pnlButtonRestart.setBackground(new java.awt.Color(25, 25, 25));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_forgotPasswordButtonMouseExited

    private void forgotPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forgotPasswordButtonActionPerformed
        new Error().setVisible(true);
        dispose();
    }//GEN-LAST:event_forgotPasswordButtonActionPerformed

    private void btnEnterGoogleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnterGoogleMouseEntered
        pnlButtonGoogle.setBackground(new java.awt.Color(190, 190, 190));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnEnterGoogleMouseEntered

    private void btnEnterGoogleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnterGoogleMouseExited
        pnlButtonGoogle.setBackground(new java.awt.Color(237, 241, 242));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnEnterGoogleMouseExited

    private void btnEnterGoogleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterGoogleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnterGoogleActionPerformed

    private void pnlButtonGoogleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlButtonGoogleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlButtonGoogleMouseEntered

    private void pnlButtonAppleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlButtonAppleMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlButtonAppleMouseEntered

    private void btnEnterAppleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnterAppleMouseEntered
        pnlButtonApple.setBackground(new java.awt.Color(190, 190, 190));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnEnterAppleMouseEntered

    private void btnEnterAppleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnterAppleMouseExited
        pnlButtonApple.setBackground(new java.awt.Color(237, 241, 242));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnEnterAppleMouseExited

    private void btnEnterAppleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterAppleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnterAppleActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void languageComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languageComboBoxActionPerformed
        Object selected = languageComboBox.getSelectedItem();
        if (selected != null) {
            String item = selected.toString();
            if (item.contains(":")) {
                String localeTag = item.split(":")[1];
                String[] parts = localeTag.split("_");
                I18nManager.setLocale(new Locale(parts[0], parts[1]));
                localizeAuthScreen();
                customizeLanguageSelectorUI();
            }
        }
    }//GEN-LAST:event_languageComboBoxActionPerformed

    private void usernameFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameFieldMousePressed

    }//GEN-LAST:event_usernameFieldMousePressed

    private void usernameFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameFieldMouseExited

    }//GEN-LAST:event_usernameFieldMouseExited

    private void usernameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFieldFocusGained
        lblDefaultUser.setVisible(false);
        lblHoverUser.setVisible(true);
        lblContainUser.setVisible(true);
    }//GEN-LAST:event_usernameFieldFocusGained

    private void usernameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFieldFocusLost
        if (usernameField.getText().isEmpty()) {
            lblDefaultUser.setVisible(true);
            lblHoverUser.setVisible(false);
            lblContainUser.setVisible(false);
        } else {
            lblDefaultUser.setVisible(false);
        }
    }//GEN-LAST:event_usernameFieldFocusLost

    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusGained
        lblDefaultPassword.setVisible(false);
        lblHoverPassword.setVisible(true);
        lblContainPassword.setVisible(true);
    }//GEN-LAST:event_passwordFieldFocusGained

    private void passwordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusLost
        if (usernameField.getText().isEmpty()) {
            lblDefaultPassword.setVisible(true);
            lblHoverPassword.setVisible(false);
            lblContainPassword.setVisible(false);
        } else {
            lblDefaultPassword.setVisible(false);
        }
    }//GEN-LAST:event_passwordFieldFocusLost

    private void usernameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameFieldKeyTyped
        if (usernameField.getText().length() >= MAX_LENGTH) {
            evt.consume();
        }
    }//GEN-LAST:event_usernameFieldKeyTyped

    private void passwordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyTyped
        if (passwordField.getText().length() >= MAX_LENGTH) {
            evt.consume();
        }
    }//GEN-LAST:event_passwordFieldKeyTyped

    private void usernameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameFieldKeyReleased
        updateFieldCounter(usernameField, lblContainUser);
    }//GEN-LAST:event_usernameFieldKeyReleased

    private void passwordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyReleased
        updateFieldCounter(passwordField, lblContainPassword);
    }//GEN-LAST:event_passwordFieldKeyReleased

    private void btnNotAcountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNotAcountMouseEntered
        btnNotAcount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNotAcountMouseEntered

    private void btnNotAcountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNotAcountMouseExited
        btnNotAcount.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnNotAcountMouseExited

    private void btnNotAcountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotAcountActionPerformed
        new Register().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnNotAcountActionPerformed

    private void usernameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameFieldKeyPressed
        handleFormNavigation(evt);
    }//GEN-LAST:event_usernameFieldKeyPressed

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        handleFormNavigation(evt);
    }//GEN-LAST:event_passwordFieldKeyPressed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    class RoundedPanel extends javax.swing.JPanel {

        private int radius;
        private Color borderColor = null;
        private int borderThickness = 0;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        public void setCustomBorder(Color color, int thickness) {
            this.borderColor = color;
            this.borderThickness = thickness;
            repaint();
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            if (borderColor != null && borderThickness > 0) {
                g2.setColor(borderColor);
                g2.setStroke(new java.awt.BasicStroke(borderThickness));

                g2.drawRoundRect(borderThickness / 2, borderThickness / 2,
                        getWidth() - borderThickness, getHeight() - borderThickness,
                        radius, radius);
            }

            g2.dispose();
            super.paintComponent(g);
        }
    }

    class SoftIcon implements javax.swing.Icon {

        private final Image imagem;
        private final int larguraExibicao;
        private final int alturaExibicao;

        public SoftIcon(Image imagem, int larguraExibicao, int alturaExibicao) {
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

    class RoundedTextField extends javax.swing.JTextField {

        private int radius;
        private String labelTexto = "";
        private int limiteCaracteres = 50;
        private boolean isFocused = false;
        private final Color COR_AZUL = new Color(59, 130, 246);
        private final Color COR_BORDA_PADRAO = new Color(28, 37, 54);
        private final Color COR_CINZA_LABEL = new Color(120, 120, 120);

        public RoundedTextField(int radius) {
            this.radius = radius;
            setOpaque(false);
            setBackground(new Color(25, 25, 25));
            setForeground(Color.WHITE);
            setCaretColor(Color.WHITE);
            setMargin(new java.awt.Insets(15, 10, 0, 15));
        }

        public void setLabelTexto(String t) {
            this.labelTexto = t;
        }

        public void setLimiteCaracteres(int l) {
            this.limiteCaracteres = l;
        }

        public void setAtivado(boolean status) {
            this.isFocused = status;
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

            if (isFocused || !getText().isEmpty()) {
                g2.setFont(new Font("SansSerif", Font.BOLD, 11));
                g2.setColor(COR_AZUL);
                g2.drawString(labelTexto, 15, 18);

                g2.setColor(new Color(150, 150, 150));
                String contador = getText().length() + " / " + limiteCaracteres;
                int largContador = g2.getFontMetrics().stringWidth(contador);
                g2.drawString(contador, getWidth() - largContador - 15, 18);
            } else {
                g2.setFont(new Font("SansSerif", Font.BOLD, 14));
                g2.setColor(COR_CINZA_LABEL);
                g2.drawString(labelTexto, 15, (getHeight() / 2) + 7);
            }

            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(isFocused ? COR_AZUL : COR_BORDA_PADRAO);
            g2.setStroke(new java.awt.BasicStroke(2));
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radius, radius);
            g2.dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnterApple;
    private javax.swing.JButton btnEnterGoogle;
    private javax.swing.JButton btnNotAcount;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton forgotPasswordButton;
    private javax.swing.JSeparator horizontalLeftDivider;
    private javax.swing.JSeparator horizontalRightDivider;
    private javax.swing.JComboBox<String> languageComboBox;
    private javax.swing.JLabel lblContainPassword;
    private javax.swing.JLabel lblContainUser;
    private javax.swing.JLabel lblDefaultPassword;
    private javax.swing.JLabel lblDefaultUser;
    private javax.swing.JLabel lblEnterApple;
    private javax.swing.JLabel lblEnterGoogle;
    private javax.swing.JLabel lblHoverPassword;
    private javax.swing.JLabel lblHoverUser;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoApple;
    private javax.swing.JLabel lblLogoGoogle;
    private javax.swing.JLabel lblOr;
    private javax.swing.JLabel lblRestartTitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUnderstandTitle;
    private javax.swing.JButton maximizeButton;
    private javax.swing.JButton minimizeButton;
    private javax.swing.JTextField passwordField;
    private javax.swing.JPanel pnlButtonApple;
    private javax.swing.JPanel pnlButtonGoogle;
    private javax.swing.JPanel pnlButtonRestart;
    private javax.swing.JPanel pnlButtonUnderstand;
    private javax.swing.JPanel pnlMainBackground;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
