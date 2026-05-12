package screen;

import Data.CTCONTAB;
import Data.I18nManager;
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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import Data.LayoutManager;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.SwingConstants;

public class TelaRegistrar extends javax.swing.JFrame {

    private static final Logger logger = LoggerFactory.getLogger(TelaRegistrar.class);
    private final Color COR_PADRAO = new Color(4, 19, 53);
    private final Color COR_HOVER_GERAL = new Color(26, 41, 75);
    private final Color COR_HOVER_FECHAR = Color.RED;

    public TelaRegistrar() {
        initComponents();
        setUndecorated(true);
        setResizable(false);
        setIcon();
        configurarLinguagens();
        atualizarTextos();
        aplicarImagensDeAltaQualidade();
        jlibErroRegistro.setVisible(false);
        setBackground(new java.awt.Color(0, 0, 0, 0));
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

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

        setTitle(I18nManager.getString("auth.register.window_title"));
        jilbTitulo.setText(I18nManager.getString("auth.register.header.title"));
        jilbTexto.setText(I18nManager.getString("auth.register.header.description"));
        jilbUsuario.setText(I18nManager.getString("auth.register.form.label.username"));
        jilbEmail.setText(I18nManager.getString("auth.register.form.label.email"));
        jilbSenha.setText(I18nManager.getString("auth.register.form.label.password"));
        jilbSenha2.setText(I18nManager.getString("auth.register.form.label.confirm_password"));
        chbLembre.setText(I18nManager.getString("auth.register.form.checkbox.remember"));
        btnRegistrar.setText(I18nManager.getString("auth.register.form.button.submit"));
        jilbAindaNaoTemConta.setText(I18nManager.getString("auth.register.footer.has_account"));
        jilbRegistreSe.setText(I18nManager.getString("auth.register.footer.link.login"));
        jilbCreditos.setText(I18nManager.getString("auth.register.footer.copyright"));
        jlibErroRegistro.setText(I18nManager.getString("auth.register.feedback.error.invalid_data"));
        addPlaceholder(txtEmail, I18nManager.getString("auth.register.form.placeholder.email"));

        Map<String, JComponent> compMap = new HashMap<>();
        compMap.put("layout.register.header.title", jilbTitulo);
        compMap.put("layout.register.header.description", jilbTexto);
        compMap.put("layout.register.form.label.username", jilbUsuario);
        compMap.put("layout.register.form.field.username", txtUsuario);
        compMap.put("layout.register.form.label.email", jilbEmail);
        compMap.put("layout.register.form.field.email", txtEmail);
        compMap.put("layout.register.form.label.password", jilbSenha);
        compMap.put("layout.register.form.field.password", txtSenha);
        compMap.put("layout.register.form.label.confirm_password", jilbSenha2);
        compMap.put("layout.register.form.field.confirm_password", txtSenha2);
        compMap.put("layout.register.form.show.password", chbMostrarSenha);
        compMap.put("layout.register.form.checkbox.remember", chbLembre);
        compMap.put("layout.register.form.button.submit", btnRegistrar);
        compMap.put("layout.register.footer.has_account", jilbAindaNaoTemConta);
        compMap.put("layout.register.footer.link.login", jilbRegistreSe);
        compMap.put("layout.register.feedback.error.invalid_data", jlibErroRegistro);
        compMap.put("layout.register.form.button.login", btnResgistrar);

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMinimizarTela = new javax.swing.JButton();
        btnMaximizarTela = new javax.swing.JButton();
        btnFecharTela = new javax.swing.JButton();
        chbMostrarSenha = new javax.swing.JCheckBox();
        btnResgistrar = new javax.swing.JButton();
        jilbRegistreSe = new javax.swing.JLabel();
        jilbAindaNaoTemConta = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jilbLinha = new javax.swing.JLabel();
        cmbLinguagens = new javax.swing.JComboBox<>();
        jilbTitulo = new javax.swing.JLabel();
        jilbTexto = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jilbUsuario = new javax.swing.JLabel();
        jilbEmail = new javax.swing.JLabel();
        jilbSenha = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jilbSenha2 = new javax.swing.JLabel();
        chbLembre = new javax.swing.JCheckBox();
        jlibErroRegistro = new javax.swing.JLabel();
        txtSenha2 = new javax.swing.JPasswordField();
        txtSenha = new javax.swing.JPasswordField();
        jilbCreditos = new javax.swing.JLabel();
        jlibBlueSquad = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CT Contab Manager");
        getContentPane().setLayout(null);

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
        chbMostrarSenha.setBounds(330, 390, 30, 40);

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

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Complet Logo Icon.png"))); // NOI18N
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
        cmbLinguagens.setBounds(250, 20, 130, 40);

        jilbTitulo.setFont(FonteUtils.carregarRalewayMedium(30f));
        jilbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jilbTitulo.setText("Registre-se");
        jilbTitulo.setPreferredSize(new java.awt.Dimension(100, 100));
        getContentPane().add(jilbTitulo);
        jilbTitulo.setBounds(30, 100, 300, 50);

        jilbTexto.setFont(FonteUtils.carregarLato(13f));
        jilbTexto.setForeground(new java.awt.Color(255, 255, 255));
        jilbTexto.setText("<html><p style=\"text-align: justify;\">Toda a organização da sua contabilidade em um só lugar, totalmente digital.</p></html>");
        getContentPane().add(jilbTexto);
        jilbTexto.setBounds(30, 140, 330, 90);

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
        jlibErroRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibErroRegistro.setText("Seu e-mail, senha ou usuário estão incorretos.");
        getContentPane().add(jlibErroRegistro);
        jlibErroRegistro.setBounds(30, 600, 330, 20);

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
        jilbCreditos.setBounds(10, 710, 350, 40);

        jlibBlueSquad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Overlay.png"))); // NOI18N
        getContentPane().add(jlibBlueSquad);
        jlibBlueSquad.setBounds(0, 0, 390, 750);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background Login.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(40, 0, 1420, 760);

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
        jlibErroRegistro.setForeground(Color.RED);
        jlibErroRegistro.setFont(FonteUtils.carregarLato(13f));
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
                Thread.sleep(4000);

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

                jlibErroRegistro.setVisible(false);

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

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

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

    private void chbMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMostrarSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbMostrarSenhaActionPerformed

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

    private void btnFecharTelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharTelaMouseEntered

    }//GEN-LAST:event_btnFecharTelaMouseEntered

    private void btnFecharTelaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharTelaMouseExited

    }//GEN-LAST:event_btnFecharTelaMouseExited

    private void btnFecharTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharTelaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnFecharTelaActionPerformed

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
    private javax.swing.JLabel jilbTitulo;
    private javax.swing.JLabel jilbUsuario;
    private javax.swing.JLabel jlibBlueSquad;
    private javax.swing.JLabel jlibErroRegistro;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JPasswordField txtSenha2;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
