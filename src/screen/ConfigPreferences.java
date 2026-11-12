package screen;

import Data.AuditUtil;
import Data.CTCONTAB;
import Data.I18nManager;
import Data.IconUtil;
import Data.Usuario;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ConfigPreferences extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private final Color COR_PADRAO = new Color(28, 37, 54);
    private final Color COR_HOVER_GERAL = new Color(50, 63, 89);
    private final Color COR_HOVER_FECHAR = new Color(196, 43, 28);
    private javax.swing.Timer timerVoltarHome;
    private final Color COR_ABA_ATIVA = new Color(205, 168, 12);
    private final Color COR_ABA_INATIVA = new Color(209, 213, 219);
    private final Color COR_COMBO_FUNDO = new Color(37, 49, 71);
    private final Color COR_COMBO_SELECIONADO = new Color(50, 63, 89);
    private final Color COR_COMBO_BORDA = new Color(50, 63, 89);
    private final Color COR_COMBO_TEXTO = new Color(209, 213, 219);
    private final Color COR_DOURADO = new Color(205, 168, 12);
    private final Color COR_SWITCH_OFF = new Color(58, 70, 94);
    private final Color COR_SWITCH_ON = new Color(237, 241, 242);
    private final java.util.Map<String, javax.swing.Icon> cacheBandeiras = new java.util.HashMap<>();
    private boolean carregandoIdioma = false;

    public ConfigPreferences(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        setUndecorated(true);
        setIcon();
        aplicarImagensDeAltaQualidade();
        localizeAuthScreen();
        filtrarSidebar();
        IconUtil.setIcon(usuarioLogado, lblUsuarioIcon, 30, 30);
        setBackground(new java.awt.Color(0, 0, 0, 0));
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        pnlButtonEllipse.setVisible(false);
        arrowBackgroundButton.setVisible(false);
        lblUsuarioName.setText(usuarioLogado.getUsuario());

        timerVoltarHome = new javax.swing.Timer(300, e -> {
            if (txtBuscar.getText().trim().isEmpty()) {
                pnlBarButtonConfig.setVisible(true);
                pnlButtonConfig.setVisible(true);
            }
        });
        timerVoltarHome.setRepeats(false);

        txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrarSidebar();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrarSidebar();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrarSidebar();
            }
        });

        getContentPane().setComponentZOrder(pnlLateralBar, getContentPane().getComponentCount() - 1);
        getContentPane().setComponentZOrder(pnlMainBackground, getContentPane().getComponentCount() - 1);

        txtBuscar.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 76, 89), 2),
                javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));

        configurarAbas();
        arredondarPaineis();
        configurarCombos();
        configurarSwitches();
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Logo Icon.png")));
    }

    private void aplicarImagensDeAltaQualidade() {
        try {
            BufferedImage LogoIcon = ImageIO.read(getClass().getResource("/images/Logo Icon.png"));
            BufferedImage CloseDarkWhiteIcon = ImageIO.read(getClass().getResource("/images/Close Dark White Icon.png"));
            BufferedImage MaximizeDarkWhiteIcon = ImageIO.read(getClass().getResource("/images/Maximize Dark White Icon.png"));
            BufferedImage MinimizeWhiteDarkIcon = ImageIO.read(getClass().getResource("/images/Minimize White Dark Icon.png"));
            BufferedImage SettingsIconLateralBar = ImageIO.read(getClass().getResource("/images/Settings Icon Lateral Bar.png"));
            BufferedImage TasksIconLateralBar = ImageIO.read(getClass().getResource("/images/Tasks Icon Lateral Bar.png"));
            BufferedImage ReportIconLateralBar = ImageIO.read(getClass().getResource("/images/Report Icon Lateral Bar.png"));
            BufferedImage ClientIconLateralBar = ImageIO.read(getClass().getResource("/images/Client Icon Lateral Bar.png"));
            BufferedImage AdministrativeIconLateralBar = ImageIO.read(getClass().getResource("/images/Administrative Icon Lateral Bar.png"));
            BufferedImage CalendarIconLateralBar = ImageIO.read(getClass().getResource("/images/Calendar Icon Lateral Bar.png"));
            BufferedImage DashboardIconLateralBar = ImageIO.read(getClass().getResource("/images/Dashboard Icon Lateral Bar.png"));
            BufferedImage JohnDoe = ImageIO.read(getClass().getResource("/images/Jonh Doe Icon.png"));
            BufferedImage MagnifierIcon = ImageIO.read(getClass().getResource("/images/Magnifier Icon.png"));
            BufferedImage LeftArrow = ImageIO.read(getClass().getResource("/images/Left Arrow Icon.png"));
            BufferedImage LanguageGlobe = ImageIO.read(getClass().getResource("/images/Globe Icon.png"));
            BufferedImage DownArrow = ImageIO.read(getClass().getResource("/images/Angle Down Icon.png"));
            BufferedImage NotifyTask = ImageIO.read(getClass().getResource("/images/Notify Task Icon.png"));
            BufferedImage NotifyMensage = ImageIO.read(getClass().getResource("/images/Notify Mesage Icon.png"));
            BufferedImage NotifyEmail = ImageIO.read(getClass().getResource("/images/Notify Email Icon.png"));

            configurarBotaoControle(btnClose, CloseDarkWhiteIcon, CloseDarkWhiteIcon, 12, 12, COR_HOVER_FECHAR);
            configurarBotaoControle(btnMaximize, MaximizeDarkWhiteIcon, MaximizeDarkWhiteIcon, 12, 12, COR_HOVER_GERAL);
            configurarBotaoControle(btnMinimize, MinimizeWhiteDarkIcon, MinimizeWhiteDarkIcon, 12, 2, COR_HOVER_GERAL);

            lblLogo.setIcon(gerarIconePerfeito(LogoIcon, 25, 25));
            lblIconHome.setIcon(gerarIconePerfeito(DashboardIconLateralBar, 14, 14));
            lblIconCalendar.setIcon(gerarIconePerfeito(CalendarIconLateralBar, 14, 14));
            lblIconClient.setIcon(gerarIconePerfeito(ClientIconLateralBar, 14, 14));
            lblIconReport.setIcon(gerarIconePerfeito(ReportIconLateralBar, 14, 14));
            lblIconTask.setIcon(gerarIconePerfeito(TasksIconLateralBar, 14, 14));
            lblIconConfig.setIcon(gerarIconePerfeito(SettingsIconLateralBar, 14, 14));
            lblIconAdmin.setIcon(gerarIconePerfeito(AdministrativeIconLateralBar, 14, 14));
            lblUsuarioIcon.setIcon(gerarIconePerfeito(JohnDoe, 30, 30));
            lblIconSearch.setIcon(gerarIconePerfeito(MagnifierIcon, 13, 13));
            arrowBackButton.setIcon(gerarIconePerfeito(LeftArrow, 12, 12));
            LanguageIconLabel.setIcon(gerarIconePerfeito(LanguageGlobe, 13, 13));
            NotifyIconLabel.setIcon(gerarIconePerfeito(NotifyMensage, 23, 23));
            NotifyIconLabel2.setIcon(gerarIconePerfeito(NotifyTask, 23, 23));
            NotifyIconLabel3.setIcon(gerarIconePerfeito(NotifyEmail, 23, 23));
            LanguageArrowIconLabel.setIcon(gerarIconePerfeito(DownArrow, 10, 10));
            OtherPreferencesArrowIconLabel.setIcon(gerarIconePerfeito(DownArrow, 10, 10));
            OtherPreferencesArrowIconLabel2.setIcon(gerarIconePerfeito(DownArrow, 10, 10));
            OtherPreferencesArrowIconLabel3.setIcon(gerarIconePerfeito(DownArrow, 10, 10));

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

        setTitle(I18nManager.getString("auth.config.window_title"));
        lblButtonHome.setText(I18nManager.getString("auth.config.button.home"));
        lblButtonCalendar.setText(I18nManager.getString("auth.config.button.calendar"));
        lblButtonClient.setText(I18nManager.getString("auth.config.button.client"));
        lblButtonReport.setText(I18nManager.getString("auth.config.button.report"));
        lblButtonTask.setText(I18nManager.getString("auth.config.button.task"));
        lblButtonConfig.setText(I18nManager.getString("auth.config.button.config"));
        lblButtonAdmin.setText(I18nManager.getString("auth.config.button.admin"));
        lblTitleBanner.setText(I18nManager.getString("auth.config.title"));
        ProfileLabel.setText(I18nManager.getString("auth.config.tab.profile"));
        SecurityLabel.setText(I18nManager.getString("auth.config.tab.security"));
        PreferencesLabel.setText(I18nManager.getString("auth.config.tab.preferences"));
        AccountLabel.setText(I18nManager.getString("auth.config.tab.account"));
        SystemLinguageLabel.setText(I18nManager.getString("auth.preferences.language.title"));
        SystemLinguageDescLabel.setText(I18nManager.getString("auth.preferences.language.description"));
        InterfaceThemeLabel.setText(I18nManager.getString("auth.preferences.theme.title"));
        InterfaceThemeDescLabel.setText(I18nManager.getString("auth.preferences.theme.description"));
        InterfaceThemeSubTitleLabel.setText(I18nManager.getString("auth.preferences.theme.dark.title"));
        InterfaceThemeSubDescLabel.setText(I18nManager.getString("auth.preferences.theme.dark.description"));
        InterfaceThemeSubTitleLabel2.setText(I18nManager.getString("auth.preferences.theme.light.title"));
        InterfaceThemeSubDescLabel2.setText(I18nManager.getString("auth.preferences.theme.light.description"));
        NotifyLabel.setText(I18nManager.getString("auth.preferences.notify.title"));
        NotifyDescLabel.setText(I18nManager.getString("auth.preferences.notify.description"));
        NotifySubTitleLabel.setText(I18nManager.getString("auth.preferences.notify.push.title"));
        NotifySubDescLabel.setText(I18nManager.getString("auth.preferences.notify.push.description"));
        NotifySubTitleLabel2.setText(I18nManager.getString("auth.preferences.notify.tasks.title"));
        NotifySubDescLabel2.setText(I18nManager.getString("auth.preferences.notify.tasks.description"));
        NotifySubTitleLabel3.setText(I18nManager.getString("auth.preferences.notify.email.title"));
        NotifySubDescLabel3.setText(I18nManager.getString("auth.preferences.notify.email.description"));
        OtherPreferencesLabel.setText(I18nManager.getString("auth.preferences.other.title"));
        OtherPreferencesDescLabel.setText(I18nManager.getString("auth.preferences.other.description"));
        TimeZoneSubTitleLabel.setText(I18nManager.getString("auth.preferences.other.timezone"));
        DateFormatSubTitleLabel.setText(I18nManager.getString("auth.preferences.other.date_format"));
        HourFormatSubTitleLabel.setText(I18nManager.getString("auth.preferences.other.hour_format"));
        ((RoundedTextField) txtBuscar).setPlaceholder(I18nManager.getString("auth.config.button.search"));

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    private void filtrarSidebar() {
        String busca = txtBuscar.getText().toLowerCase().trim();
        boolean estaBuscando = !busca.isEmpty();

        Object[][] gruposMenu = {
            {btnHome, lblIconHome, lblButtonHome, pnlBarButtonHome, pnlButtonHome, "home"},
            {btnCalendar, lblIconCalendar, lblButtonCalendar, pnlBarButtonCalendar, pnlButtonCalendar, "calendário"},
            {btnClient, lblIconClient, lblButtonClient, pnlBarButtonClient, pnlButtonClient, "clientes"},
            {btnReport, lblIconReport, lblButtonReport, pnlBarButtonReport, pnlButtonReport, "relatórios"},
            {btnTask, lblIconTask, lblButtonTask, pnlBarButtonTask, pnlButtonTask, "tarefas"},
            {btnConfig, lblIconConfig, lblButtonConfig, pnlBarButtonConfig, pnlButtonConfig, "configurações"},
            {btnAdmin, lblIconAdmin, lblButtonAdmin, pnlBarButtonAdmin, pnlButtonAdmin, "administração"}
        };

        int currentY = 90;
        int gap = 35;

        for (Object[] grupo : gruposMenu) {
            javax.swing.JButton btn = (javax.swing.JButton) grupo[0];
            javax.swing.JLabel icon = (javax.swing.JLabel) grupo[1];
            javax.swing.JLabel label = (javax.swing.JLabel) grupo[2];
            javax.swing.JPanel bar = (javax.swing.JPanel) grupo[3];
            javax.swing.JPanel pnl = (javax.swing.JPanel) grupo[4];
            String nomeFiltro = (String) grupo[5];

            boolean corresponde = !estaBuscando || nomeFiltro.contains(busca);

            btn.setVisible(corresponde);
            icon.setVisible(corresponde);
            label.setVisible(corresponde);

            if (corresponde) {
                btn.setLocation(btn.getX(), currentY);
                icon.setLocation(icon.getX(), currentY + 8);
                label.setLocation(label.getX(), currentY + 8);
                bar.setLocation(bar.getX(), currentY + 8);
                pnl.setLocation(pnl.getX(), currentY);

                if (btn == btnConfig && !estaBuscando) {
                    bar.setVisible(true);
                    pnl.setVisible(true);
                } else {
                    bar.setVisible(false);
                    pnl.setVisible(false);
                }
                currentY += gap;
            } else {
                bar.setVisible(false);
                pnl.setVisible(false);
            }
        }
        getContentPane().setComponentZOrder(lblUsuarioIcon, 0);
        getContentPane().setComponentZOrder(lblUsuarioName, 0);
        getContentPane().setComponentZOrder(horizontalDivider, 0);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void gerenciarHover(javax.swing.JPanel pnlBar, javax.swing.JPanel pnlBtn, boolean entrar) {
        timerVoltarHome.stop();

        pnlBarButtonHome.setVisible(false);
        pnlButtonHome.setVisible(false);
        pnlBarButtonCalendar.setVisible(false);
        pnlButtonCalendar.setVisible(false);
        pnlBarButtonClient.setVisible(false);
        pnlButtonClient.setVisible(false);
        pnlBarButtonReport.setVisible(false);
        pnlButtonReport.setVisible(false);
        pnlBarButtonTask.setVisible(false);
        pnlButtonTask.setVisible(false);
        pnlBarButtonConfig.setVisible(false);
        pnlButtonConfig.setVisible(false);
        pnlBarButtonAdmin.setVisible(false);
        pnlButtonAdmin.setVisible(false);

        if (entrar) {
            pnlBar.setVisible(true);
            pnlBtn.setVisible(true);
        } else {
            timerVoltarHome.start();
        }
    }

    private void irParaHome() {
        new Home(usuarioLogado).setVisible(true);
        this.dispose();
    }
    
    private void irParaCalendario() {
        new Calendar(usuarioLogado).setVisible(true);
        this.dispose();
    }
    
    private void irParaClientes() {
        new Client(usuarioLogado).setVisible(true);
        this.dispose();
    }
    
    private void irParaRelatorios() {
        new Report(usuarioLogado).setVisible(true);
        this.dispose();
    }
    
    private void irParaTarefas() {
        new Task(usuarioLogado).setVisible(true);
        this.dispose();
    }
    
    private void irParaConfiguracoes() {
        new ConfigProfile(usuarioLogado).setVisible(true);
        this.dispose();
    }
    
    private void irParaAdministracao() {
        new Admin(usuarioLogado).setVisible(true);
        this.dispose();
    }

    private void irParaPerfil() {
        new ConfigProfile(usuarioLogado).setVisible(true);
        this.dispose();
    }

    private void irParaSeguranca() {
        new ConfigSecurity(usuarioLogado).setVisible(true);
        this.dispose();
    }
    
    private void irParaPreferencias() {
        new ConfigPreferences(usuarioLogado).setVisible(true);
        this.dispose();
    }

    private void irParaConta() {
        new ConfigAccount(usuarioLogado).setVisible(true);
        this.dispose();
    }
    
    private void configurarAbas() {
        PreferencesLabel.setForeground(COR_ABA_ATIVA);
        PreferencesSeparator.setVisible(true);

        ProfileLabel.setForeground(COR_ABA_INATIVA);
        ProfileSeparator.setVisible(false);
        SecurityLabel.setForeground(COR_ABA_INATIVA);
        SecuritySeparator.setVisible(false);
        AccountLabel.setForeground(COR_ABA_INATIVA);
        AccountSeparator.setVisible(false);

        ProfileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                irParaPerfil();
            }
        });
        SecurityLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                irParaSeguranca();
            }
        });
        AccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                irParaConta();
            }
        });
    }

    private javax.swing.JPanel arredondarPainel(javax.swing.JPanel original, int raio) {
        java.awt.Container pai = original.getParent();
        int posicao = pai.getComponentZOrder(original);

        RoundedPanel arredondado = new RoundedPanel(raio);
        arredondado.setBackground(original.getBackground());
        arredondado.setBounds(original.getBounds());

        pai.remove(original);
        pai.add(arredondado, posicao);
        pai.revalidate();
        pai.repaint();
        return arredondado;
    }

    private void arredondarPaineis() {
        InterfaceThemeSubPanel = arredondarPainel(InterfaceThemeSubPanel, 10);
        InterfaceThemeSubPanel2 = arredondarPainel(InterfaceThemeSubPanel2, 10);
    }

    private boolean textoAsiatico(String texto) {
        Locale loc = I18nManager.getCurrentLocale();
        String idioma = loc.getLanguage();
        return idioma.equals("ja") || idioma.equals("ko") || idioma.equals("zh")
                || (texto != null && texto.matches(".*[\\u4e00-\\u9fa5\\u3040-\\u309f\\uac00-\\ud7af].*"));
    }

    private java.awt.Font fonteCombo(String texto) {
        if (textoAsiatico(texto)) {
            return new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13);
        }
        return FonteUtils.carregarRobotoRegular(13f);
    }

    private javax.swing.Icon bandeira(String tag) {
        if (tag == null || tag.isEmpty()) {
            return null;
        }
        if (!cacheBandeiras.containsKey(tag)) {
            javax.swing.Icon icone = null;
            try {
                java.net.URL url = getClass().getResource("/images/flags/" + tag + ".png");
                if (url != null) {
                    icone = gerarIconePerfeito(ImageIO.read(url), 18, 12);
                }
            } catch (Exception e) {
                icone = null;
            }
            cacheBandeiras.put(tag, icone);
        }
        return cacheBandeiras.get(tag);
    }

    private javax.swing.ListCellRenderer<String> criarRenderer(boolean comBandeira, int margemEsquerda) {
        return (lista, valor, indice, selecionado, comFoco) -> {
            boolean fechado = indice < 0;
            String texto = valor == null ? "" : valor;
            String tag = "";
            if (comBandeira && texto.contains(":")) {
                String[] partes = texto.split(":");
                texto = partes[0];
                tag = partes.length > 1 ? partes[1] : "";
            }

            javax.swing.JPanel painel = new javax.swing.JPanel(new java.awt.BorderLayout());
            painel.setOpaque(!fechado);
            painel.setBackground(selecionado ? COR_COMBO_SELECIONADO : COR_COMBO_FUNDO);

            if (comBandeira && !fechado) {
                javax.swing.JLabel icone = new javax.swing.JLabel(bandeira(tag));
                icone.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 8));
                painel.add(icone, java.awt.BorderLayout.WEST);
            }

            javax.swing.JLabel rotulo = new javax.swing.JLabel(texto);
            rotulo.setForeground(COR_COMBO_TEXTO);
            rotulo.setFont(fonteCombo(texto));
            painel.add(rotulo, java.awt.BorderLayout.CENTER);

            painel.setBorder(fechado
                    ? javax.swing.BorderFactory.createEmptyBorder(0, margemEsquerda, 0, 30)
                    : javax.swing.BorderFactory.createEmptyBorder(7, 12, 7, 12));
            return painel;
        };
    }

    private class ComboArredondadoUI extends javax.swing.plaf.basic.BasicComboBoxUI {

        @Override
        public void paint(java.awt.Graphics g, javax.swing.JComponent componente) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(COR_COMBO_FUNDO);
            g2.fillRoundRect(0, 0, componente.getWidth(), componente.getHeight(), 12, 12);
            g2.dispose();
            super.paint(g, componente);
        }

        @Override
        public void paintCurrentValueBackground(java.awt.Graphics g, java.awt.Rectangle limites, boolean comFoco) {
        }

        @Override
        protected javax.swing.JButton createArrowButton() {
            javax.swing.JButton botao = new javax.swing.JButton();
            botao.setBorder(null);
            botao.setContentAreaFilled(false);
            botao.setFocusable(false);
            botao.setVisible(false);
            return botao;
        }

        @Override
        protected javax.swing.plaf.basic.ComboPopup createPopup() {
            javax.swing.plaf.basic.BasicComboPopup popup = new javax.swing.plaf.basic.BasicComboPopup(comboBox) {
                @Override
                protected javax.swing.JScrollPane createScroller() {
                    javax.swing.JScrollPane rolagem = new javax.swing.JScrollPane(list,
                            javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                            javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    rolagem.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(0, 0));
                    rolagem.setBorder(null);
                    rolagem.getViewport().setBackground(COR_COMBO_FUNDO);
                    return rolagem;
                }
            };
            popup.setBorder(javax.swing.BorderFactory.createLineBorder(COR_COMBO_BORDA));
            popup.getList().setBackground(COR_COMBO_FUNDO);
            return popup;
        }
    }

    private void estilizarCombo(javax.swing.JComboBox<String> combo, boolean comBandeira, int margemEsquerda) {
        combo.setUI(new ComboArredondadoUI());
        combo.setOpaque(false);
        combo.setBorder(null);
        combo.setBackground(COR_COMBO_FUNDO);
        combo.setForeground(COR_COMBO_TEXTO);
        combo.setRenderer(criarRenderer(comBandeira, margemEsquerda));
        combo.setMaximumRowCount(9);
        combo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void configurarCombos() {
        estilizarCombo(LinguageComboBox, true, 32);
        estilizarCombo(TimeZoneComboBox, false, 10);
        estilizarCombo(DateFormatComboBox, false, 10);
        estilizarCombo(HourFormatComboBox, false, 10);

        popularIdiomas();
        popularOutrasPreferencias();

        LinguageComboBox.addActionListener(e -> {
            if (!carregandoIdioma) {
                aplicarIdiomaSelecionado();
            }
        });
    }

    private void popularIdiomas() {
        carregandoIdioma = true;
        LinguageComboBox.removeAllItems();
        LinguageComboBox.addItem("Português (Brasil):pt_BR");
        LinguageComboBox.addItem("English (United States):en_US");
        LinguageComboBox.addItem("Español (España):es_ES");
        LinguageComboBox.addItem("Deutsch (Deutschland):de_DE");
        LinguageComboBox.addItem("Français (France):fr_FR");
        LinguageComboBox.addItem("Italiano (Italia):it_IT");
        LinguageComboBox.addItem("日本語 (日本):ja_JP");
        LinguageComboBox.addItem("한국어 (대한민국):ko_KR");
        LinguageComboBox.addItem("中文 (中国):zh_CN");

        Locale atual = I18nManager.getCurrentLocale();
        String tagAtual = atual.getLanguage() + "_" + atual.getCountry();
        for (int i = 0; i < LinguageComboBox.getItemCount(); i++) {
            if (LinguageComboBox.getItemAt(i).endsWith(":" + tagAtual)) {
                LinguageComboBox.setSelectedIndex(i);
                break;
            }
        }
        carregandoIdioma = false;
    }

    private void preencherCombo(javax.swing.JComboBox<String> combo, String[] chaves) {
        int selecionado = Math.max(0, combo.getSelectedIndex());
        combo.removeAllItems();
        for (String chave : chaves) {
            combo.addItem(I18nManager.getString(chave));
        }
        combo.setSelectedIndex(Math.min(selecionado, combo.getItemCount() - 1));
    }

    private void popularOutrasPreferencias() {
        preencherCombo(TimeZoneComboBox, new String[]{
            "auth.preferences.timezone.brasilia", "auth.preferences.timezone.manaus",
            "auth.preferences.timezone.rio_branco", "auth.preferences.timezone.noronha",
            "auth.preferences.timezone.utc", "auth.preferences.timezone.lisboa",
            "auth.preferences.timezone.new_york", "auth.preferences.timezone.tokyo"});
        preencherCombo(DateFormatComboBox, new String[]{
            "auth.preferences.date_format.dmy", "auth.preferences.date_format.mdy", "auth.preferences.date_format.ymd"});
        preencherCombo(HourFormatComboBox, new String[]{
            "auth.preferences.hour_format.h24", "auth.preferences.hour_format.h12"});
    }

    private void aplicarIdiomaSelecionado() {
        Object selecionado = LinguageComboBox.getSelectedItem();
        if (selecionado == null || !selecionado.toString().contains(":")) {
            return;
        }

        String tag = selecionado.toString().split(":")[1];
        String[] partes = tag.split("_");
        I18nManager.setLocale(new Locale(partes[0], partes[1]));

        try {
            CTCONTAB.atualizarIdiomaUsuario(usuarioLogado.getEmail(), tag);
        } catch (Exception ex) {
            System.err.println("Erro ao salvar idioma do usuário: " + ex.getMessage());
        }

        localizeAuthScreen();
        popularOutrasPreferencias();
        LinguageComboBox.repaint();
    }

    private javax.swing.Icon iconeCirculo(boolean ativo) {
        int escala = 4;
        int tamanho = 23;
        BufferedImage img = new BufferedImage(tamanho * escala, tamanho * escala, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new java.awt.BasicStroke(2f * escala));
        g.setColor(ativo ? COR_DOURADO : COR_COMBO_TEXTO);
        g.draw(new java.awt.geom.Ellipse2D.Double(escala, escala, (tamanho - 2) * escala, (tamanho - 2) * escala));
        if (ativo) {
            double miolo = 11 * escala;
            double inicio = (tamanho * escala - miolo) / 2;
            g.fill(new java.awt.geom.Ellipse2D.Double(inicio, inicio, miolo, miolo));
        }
        g.dispose();
        return gerarIconePerfeito(img, tamanho, tamanho);
    }

    private javax.swing.Icon iconeSwitch(boolean ligado) {
        int escala = 4;
        int largura = 40;
        int altura = 20;
        BufferedImage img = new BufferedImage(largura * escala, altura * escala, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(ligado ? COR_SWITCH_ON : COR_SWITCH_OFF);
        g.fillRoundRect(0, 0, largura * escala, altura * escala, altura * escala, altura * escala);
        int botao = 14 * escala;
        int margem = 3 * escala;
        g.setColor(ligado ? COR_PADRAO : COR_COMBO_TEXTO);
        g.fillOval(ligado ? largura * escala - botao - margem : margem, margem, botao, botao);
        g.dispose();
        return gerarIconePerfeito(img, largura, altura);
    }

    private void prepararToggle(javax.swing.JToggleButton botao, javax.swing.Icon desligado, javax.swing.Icon ligado) {
        botao.setText("");
        botao.setIcon(desligado);
        botao.setRolloverIcon(desligado);
        botao.setPressedIcon(desligado);
        botao.setSelectedIcon(ligado);
        botao.setRolloverSelectedIcon(ligado);
        botao.setBorder(null);
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        botao.setOpaque(false);
        botao.setMargin(new java.awt.Insets(0, 0, 0, 0));
        botao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void configurarSwitches() {
        javax.swing.Icon circuloOff = iconeCirculo(false);
        javax.swing.Icon circuloOn = iconeCirculo(true);
        prepararToggle(InterfaceThemeOnOffButton, circuloOff, circuloOn);
        prepararToggle(InterfaceThemeOnOffButton2, circuloOff, circuloOn);
        
        InterfaceThemeOnOffButton.setSelected(true);

        InterfaceThemeOnOffButton.addActionListener(e -> {
            if (InterfaceThemeOnOffButton.isSelected()) {
                InterfaceThemeOnOffButton2.setSelected(false);
            }
        });
        InterfaceThemeOnOffButton2.addActionListener(e -> {
            if (InterfaceThemeOnOffButton2.isSelected()) {
                InterfaceThemeOnOffButton.setSelected(false);
            }
        });

        javax.swing.Icon switchOff = iconeSwitch(false);
        javax.swing.Icon switchOn = iconeSwitch(true);
        javax.swing.JToggleButton[] notificacoes = {NotifyOnOffButton, NotifyOnOffButton2, NotifyOnOffButton3};
        for (javax.swing.JToggleButton botao : notificacoes) {
            prepararToggle(botao, switchOff, switchOn);
            botao.setSelected(true);
            botao.setBounds(botao.getX() + botao.getWidth() - 44, botao.getY() + (botao.getHeight() - 24) / 2, 44, 24);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OtherPreferencesArrowIconLabel3 = new javax.swing.JLabel();
        OtherPreferencesArrowIconLabel2 = new javax.swing.JLabel();
        OtherPreferencesArrowIconLabel = new javax.swing.JLabel();
        NotifyIconLabel3 = new javax.swing.JLabel();
        NotifyIconLabel2 = new javax.swing.JLabel();
        NotifyIconLabel = new javax.swing.JLabel();
        InterfaceThemeOnOffButton2 = new javax.swing.JToggleButton();
        InterfaceThemeOnOffButton = new javax.swing.JToggleButton();
        NotifyOnOffButton3 = new javax.swing.JToggleButton();
        NotifyOnOffButton2 = new javax.swing.JToggleButton();
        NotifyOnOffButton = new javax.swing.JToggleButton();
        NotifySubDescLabel3 = new javax.swing.JLabel();
        NotifySubDescLabel2 = new javax.swing.JLabel();
        NotifySubDescLabel = new javax.swing.JLabel();
        NotifySubTitleLabel3 = new javax.swing.JLabel();
        NotifySubTitleLabel2 = new javax.swing.JLabel();
        NotifySubTitleLabel = new javax.swing.JLabel();
        LanguageArrowIconLabel = new javax.swing.JLabel();
        LanguageIconLabel = new javax.swing.JLabel();
        InterfaceThemeSubDescLabel2 = new javax.swing.JLabel();
        InterfaceThemeSubDescLabel = new javax.swing.JLabel();
        InterfaceThemeSubTitleLabel2 = new javax.swing.JLabel();
        InterfaceThemeSubTitleLabel = new javax.swing.JLabel();
        InterfaceThemeSubPanel2 = new javax.swing.JPanel();
        InterfaceThemeSubPanel = new javax.swing.JPanel();
        HourFormatSubTitleLabel = new javax.swing.JLabel();
        DateFormatSubTitleLabel = new javax.swing.JLabel();
        TimeZoneSubTitleLabel = new javax.swing.JLabel();
        HourFormatComboBox = new javax.swing.JComboBox<>();
        DateFormatComboBox = new javax.swing.JComboBox<>();
        TimeZoneComboBox = new javax.swing.JComboBox<>();
        LinguageComboBox = new javax.swing.JComboBox<>();
        InterfaceThemeDescLabel = new javax.swing.JLabel();
        OtherPreferencesDescLabel = new javax.swing.JLabel();
        NotifyDescLabel = new javax.swing.JLabel();
        SystemLinguageDescLabel = new javax.swing.JLabel();
        OtherPreferencesLabel = new javax.swing.JLabel();
        NotifyLabel = new javax.swing.JLabel();
        InterfaceThemeLabel = new javax.swing.JLabel();
        SystemLinguageLabel = new javax.swing.JLabel();
        OtherPreferencesPanel = new javax.swing.JPanel();
        NotifyPanel = new javax.swing.JPanel();
        InterfaceThemePanel = new javax.swing.JPanel();
        SystemLinguagePanel = new javax.swing.JPanel();
        arrowBackButton = new javax.swing.JButton();
        arrowBackgroundButton = new RoundedPanel(30);
        AccountButton = new javax.swing.JButton();
        AccountSeparator = new javax.swing.JPanel();
        AccountLabel = new javax.swing.JLabel();
        PreferencesButton = new javax.swing.JButton();
        PreferencesSeparator = new javax.swing.JPanel();
        PreferencesLabel = new javax.swing.JLabel();
        SecurityButton = new javax.swing.JButton();
        SecuritySeparator = new javax.swing.JPanel();
        SecurityLabel = new javax.swing.JLabel();
        ProfileButton = new javax.swing.JButton();
        ProfileSeparator = new javax.swing.JPanel();
        ProfileLabel = new javax.swing.JLabel();
        Separator = new javax.swing.JPanel();
        lblIconSearch = new javax.swing.JLabel();
        txtBuscar = new RoundedTextField();
        lblUsuarioName = new javax.swing.JLabel();
        lblUsuarioIcon = new javax.swing.JLabel();
        horizontalDivider = new javax.swing.JPanel();
        lblTitleBanner = new javax.swing.JLabel();
        btnAdmin = new javax.swing.JButton();
        lblIconAdmin = new javax.swing.JLabel();
        lblButtonAdmin = new javax.swing.JLabel();
        pnlBarButtonAdmin = new RoundedPanel(5);
        pnlButtonAdmin = new RoundedPanel(15);
        btnConfig = new javax.swing.JButton();
        lblIconConfig = new javax.swing.JLabel();
        lblButtonConfig = new javax.swing.JLabel();
        pnlBarButtonConfig = new RoundedPanel(5);
        pnlButtonConfig = new RoundedPanel(15);
        btnTask = new javax.swing.JButton();
        lblIconTask = new javax.swing.JLabel();
        lblButtonTask = new javax.swing.JLabel();
        pnlBarButtonTask = new RoundedPanel(5);
        pnlButtonTask = new RoundedPanel(15);
        btnReport = new javax.swing.JButton();
        lblIconReport = new javax.swing.JLabel();
        lblButtonReport = new javax.swing.JLabel();
        pnlBarButtonReport = new RoundedPanel(5);
        pnlButtonReport = new RoundedPanel(15);
        btnClient = new javax.swing.JButton();
        lblIconClient = new javax.swing.JLabel();
        lblButtonClient = new javax.swing.JLabel();
        pnlBarButtonClient = new RoundedPanel(5);
        pnlButtonClient = new RoundedPanel(15);
        btnCalendar = new javax.swing.JButton();
        lblIconCalendar = new javax.swing.JLabel();
        lblButtonCalendar = new javax.swing.JLabel();
        pnlBarButtonCalendar = new RoundedPanel(5);
        pnlButtonCalendar = new RoundedPanel(15);
        btnHome = new javax.swing.JButton();
        lblIconHome = new javax.swing.JLabel();
        lblButtonHome = new javax.swing.JLabel();
        pnlBarButtonHome = new RoundedPanel(5);
        pnlButtonHome = new RoundedPanel(15);
        btnClose = new javax.swing.JButton();
        btnMaximize = new javax.swing.JButton();
        btnMinimize = new javax.swing.JButton();
        btnEllipse = new javax.swing.JButton();
        Ellipse3 = new RoundedPanel(30);
        Ellipse2 = new RoundedPanel(30);
        Ellipse1 = new RoundedPanel(30);
        pnlButtonEllipse = new RoundedPanel(30);
        lblLogoText = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        pnlLateralBar = new javax.swing.JPanel();
        pnlSuperiorBar = new javax.swing.JPanel();
        pnlMainBackground = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().setLayout(null);

        OtherPreferencesArrowIconLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(OtherPreferencesArrowIconLabel3);
        OtherPreferencesArrowIconLabel3.setBounds(1213, 423, 10, 10);

        OtherPreferencesArrowIconLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(OtherPreferencesArrowIconLabel2);
        OtherPreferencesArrowIconLabel2.setBounds(1213, 353, 10, 10);

        OtherPreferencesArrowIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(OtherPreferencesArrowIconLabel);
        OtherPreferencesArrowIconLabel.setBounds(1213, 283, 10, 10);

        NotifyIconLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(NotifyIconLabel3);
        NotifyIconLabel3.setBounds(601, 395, 23, 23);

        NotifyIconLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(NotifyIconLabel2);
        NotifyIconLabel2.setBounds(601, 333, 23, 23);

        NotifyIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(NotifyIconLabel);
        NotifyIconLabel.setBounds(601, 270, 23, 23);
        getContentPane().add(InterfaceThemeOnOffButton2);
        InterfaceThemeOnOffButton2.setBounds(302, 492, 23, 23);
        getContentPane().add(InterfaceThemeOnOffButton);
        InterfaceThemeOnOffButton.setBounds(302, 417, 23, 23);
        getContentPane().add(NotifyOnOffButton3);
        NotifyOnOffButton3.setBounds(895, 393, 30, 30);
        getContentPane().add(NotifyOnOffButton2);
        NotifyOnOffButton2.setBounds(895, 331, 30, 30);
        getContentPane().add(NotifyOnOffButton);
        NotifyOnOffButton.setBounds(895, 268, 30, 30);

        NotifySubDescLabel3.setFont(FonteUtils.carregarRobotoRegular(11f));
        NotifySubDescLabel3.setForeground(new java.awt.Color(209, 213, 219));
        NotifySubDescLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NotifySubDescLabel3.setText("Comunicados e atualizações por email");
        NotifySubDescLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        NotifySubDescLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NotifySubDescLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NotifySubDescLabel3MouseExited(evt);
            }
        });
        getContentPane().add(NotifySubDescLabel3);
        NotifySubDescLabel3.setBounds(637, 409, 270, 20);

        NotifySubDescLabel2.setFont(FonteUtils.carregarRobotoRegular(11f));
        NotifySubDescLabel2.setForeground(new java.awt.Color(209, 213, 219));
        NotifySubDescLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NotifySubDescLabel2.setText("Alerta sobre prazos e pendências");
        NotifySubDescLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        NotifySubDescLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NotifySubDescLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NotifySubDescLabel2MouseExited(evt);
            }
        });
        getContentPane().add(NotifySubDescLabel2);
        NotifySubDescLabel2.setBounds(637, 345, 270, 20);

        NotifySubDescLabel.setFont(FonteUtils.carregarRobotoRegular(11f));
        NotifySubDescLabel.setForeground(new java.awt.Color(209, 213, 219));
        NotifySubDescLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NotifySubDescLabel.setText("Ative para receber notificações");
        NotifySubDescLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        NotifySubDescLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NotifySubDescLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NotifySubDescLabelMouseExited(evt);
            }
        });
        getContentPane().add(NotifySubDescLabel);
        NotifySubDescLabel.setBounds(637, 282, 270, 20);

        NotifySubTitleLabel3.setFont(FonteUtils.carregarRobotoSemiBold(12f));
        NotifySubTitleLabel3.setForeground(new java.awt.Color(209, 213, 219));
        NotifySubTitleLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NotifySubTitleLabel3.setText("Receber emails do sistema");
        NotifySubTitleLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        NotifySubTitleLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NotifySubTitleLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NotifySubTitleLabel3MouseExited(evt);
            }
        });
        getContentPane().add(NotifySubTitleLabel3);
        NotifySubTitleLabel3.setBounds(637, 392, 270, 20);

        NotifySubTitleLabel2.setFont(FonteUtils.carregarRobotoSemiBold(12f));
        NotifySubTitleLabel2.setForeground(new java.awt.Color(209, 213, 219));
        NotifySubTitleLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NotifySubTitleLabel2.setText("Receber alerta de tarefas");
        NotifySubTitleLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        NotifySubTitleLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NotifySubTitleLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NotifySubTitleLabel2MouseExited(evt);
            }
        });
        getContentPane().add(NotifySubTitleLabel2);
        NotifySubTitleLabel2.setBounds(637, 328, 270, 20);

        NotifySubTitleLabel.setFont(FonteUtils.carregarRobotoSemiBold(12f));
        NotifySubTitleLabel.setForeground(new java.awt.Color(209, 213, 219));
        NotifySubTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NotifySubTitleLabel.setText("Receber notificações");
        NotifySubTitleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        NotifySubTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NotifySubTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NotifySubTitleLabelMouseExited(evt);
            }
        });
        getContentPane().add(NotifySubTitleLabel);
        NotifySubTitleLabel.setBounds(637, 265, 270, 20);

        LanguageArrowIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(LanguageArrowIconLabel);
        LanguageArrowIconLabel.setBounds(521, 265, 10, 10);

        LanguageIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(LanguageIconLabel);
        LanguageIconLabel.setBounds(297, 262, 15, 15);

        InterfaceThemeSubDescLabel2.setFont(FonteUtils.carregarRobotoRegular(11f));
        InterfaceThemeSubDescLabel2.setForeground(new java.awt.Color(209, 213, 219));
        InterfaceThemeSubDescLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        InterfaceThemeSubDescLabel2.setText("Tema claro");
        InterfaceThemeSubDescLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        InterfaceThemeSubDescLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InterfaceThemeSubDescLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InterfaceThemeSubDescLabel2MouseExited(evt);
            }
        });
        getContentPane().add(InterfaceThemeSubDescLabel2);
        InterfaceThemeSubDescLabel2.setBounds(339, 506, 190, 20);

        InterfaceThemeSubDescLabel.setFont(FonteUtils.carregarRobotoRegular(11f));
        InterfaceThemeSubDescLabel.setForeground(new java.awt.Color(209, 213, 219));
        InterfaceThemeSubDescLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        InterfaceThemeSubDescLabel.setText("Tema escuro padrão");
        InterfaceThemeSubDescLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        InterfaceThemeSubDescLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InterfaceThemeSubDescLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InterfaceThemeSubDescLabelMouseExited(evt);
            }
        });
        getContentPane().add(InterfaceThemeSubDescLabel);
        InterfaceThemeSubDescLabel.setBounds(339, 431, 190, 20);

        InterfaceThemeSubTitleLabel2.setFont(FonteUtils.carregarRobotoSemiBold(12f));
        InterfaceThemeSubTitleLabel2.setForeground(new java.awt.Color(209, 213, 219));
        InterfaceThemeSubTitleLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        InterfaceThemeSubTitleLabel2.setText("Claro");
        InterfaceThemeSubTitleLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        InterfaceThemeSubTitleLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InterfaceThemeSubTitleLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InterfaceThemeSubTitleLabel2MouseExited(evt);
            }
        });
        getContentPane().add(InterfaceThemeSubTitleLabel2);
        InterfaceThemeSubTitleLabel2.setBounds(339, 487, 190, 20);

        InterfaceThemeSubTitleLabel.setFont(FonteUtils.carregarRobotoSemiBold(12f));
        InterfaceThemeSubTitleLabel.setForeground(new java.awt.Color(209, 213, 219));
        InterfaceThemeSubTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        InterfaceThemeSubTitleLabel.setText("Escuro");
        InterfaceThemeSubTitleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        InterfaceThemeSubTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InterfaceThemeSubTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InterfaceThemeSubTitleLabelMouseExited(evt);
            }
        });
        getContentPane().add(InterfaceThemeSubTitleLabel);
        InterfaceThemeSubTitleLabel.setBounds(339, 412, 190, 20);

        InterfaceThemeSubPanel2.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(InterfaceThemeSubPanel2);
        InterfaceThemeSubPanel2.setBounds(288, 474, 254, 58);

        InterfaceThemeSubPanel.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(InterfaceThemeSubPanel);
        InterfaceThemeSubPanel.setBounds(288, 399, 254, 58);

        HourFormatSubTitleLabel.setFont(FonteUtils.carregarRobotoRegular(11f));
        HourFormatSubTitleLabel.setForeground(new java.awt.Color(209, 213, 219));
        HourFormatSubTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        HourFormatSubTitleLabel.setText("Formato da hora");
        HourFormatSubTitleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        HourFormatSubTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HourFormatSubTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                HourFormatSubTitleLabelMouseExited(evt);
            }
        });
        getContentPane().add(HourFormatSubTitleLabel);
        HourFormatSubTitleLabel.setBounds(982, 391, 250, 20);

        DateFormatSubTitleLabel.setFont(FonteUtils.carregarRobotoRegular(11f));
        DateFormatSubTitleLabel.setForeground(new java.awt.Color(209, 213, 219));
        DateFormatSubTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DateFormatSubTitleLabel.setText("Formato da data");
        DateFormatSubTitleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        DateFormatSubTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DateFormatSubTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DateFormatSubTitleLabelMouseExited(evt);
            }
        });
        getContentPane().add(DateFormatSubTitleLabel);
        DateFormatSubTitleLabel.setBounds(982, 321, 250, 20);

        TimeZoneSubTitleLabel.setFont(FonteUtils.carregarRobotoRegular(11f));
        TimeZoneSubTitleLabel.setForeground(new java.awt.Color(209, 213, 219));
        TimeZoneSubTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TimeZoneSubTitleLabel.setText("Fuso Horário");
        TimeZoneSubTitleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        TimeZoneSubTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TimeZoneSubTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TimeZoneSubTitleLabelMouseExited(evt);
            }
        });
        getContentPane().add(TimeZoneSubTitleLabel);
        TimeZoneSubTitleLabel.setBounds(982, 251, 250, 20);

        HourFormatComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        HourFormatComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HourFormatComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(HourFormatComboBox);
        HourFormatComboBox.setBounds(980, 410, 254, 35);

        DateFormatComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(DateFormatComboBox);
        DateFormatComboBox.setBounds(980, 340, 254, 35);

        TimeZoneComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(TimeZoneComboBox);
        TimeZoneComboBox.setBounds(980, 270, 254, 35);

        LinguageComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(LinguageComboBox);
        LinguageComboBox.setBounds(288, 252, 254, 35);

        InterfaceThemeDescLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        InterfaceThemeDescLabel.setForeground(new java.awt.Color(209, 213, 219));
        InterfaceThemeDescLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        InterfaceThemeDescLabel.setText("Escolha o tema de aparência da aplicação.");
        InterfaceThemeDescLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        InterfaceThemeDescLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InterfaceThemeDescLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InterfaceThemeDescLabelMouseExited(evt);
            }
        });
        getContentPane().add(InterfaceThemeDescLabel);
        InterfaceThemeDescLabel.setBounds(289, 368, 250, 20);

        OtherPreferencesDescLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        OtherPreferencesDescLabel.setForeground(new java.awt.Color(209, 213, 219));
        OtherPreferencesDescLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        OtherPreferencesDescLabel.setText("Configurações adcionais da aplicação");
        OtherPreferencesDescLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        OtherPreferencesDescLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OtherPreferencesDescLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OtherPreferencesDescLabelMouseExited(evt);
            }
        });
        getContentPane().add(OtherPreferencesDescLabel);
        OtherPreferencesDescLabel.setBounds(982, 221, 250, 20);

        NotifyDescLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        NotifyDescLabel.setForeground(new java.awt.Color(209, 213, 219));
        NotifyDescLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NotifyDescLabel.setText("Gerencie como você deseja receber notificações.");
        NotifyDescLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        NotifyDescLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NotifyDescLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NotifyDescLabelMouseExited(evt);
            }
        });
        getContentPane().add(NotifyDescLabel);
        NotifyDescLabel.setBounds(601, 221, 320, 20);

        SystemLinguageDescLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        SystemLinguageDescLabel.setForeground(new java.awt.Color(209, 213, 219));
        SystemLinguageDescLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SystemLinguageDescLabel.setText("Selecione o idioma da aplicação.");
        SystemLinguageDescLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        SystemLinguageDescLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SystemLinguageDescLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SystemLinguageDescLabelMouseExited(evt);
            }
        });
        getContentPane().add(SystemLinguageDescLabel);
        SystemLinguageDescLabel.setBounds(289, 221, 250, 20);

        OtherPreferencesLabel.setFont(FonteUtils.carregarRobotoSemiBold(15f));
        OtherPreferencesLabel.setForeground(new java.awt.Color(209, 213, 219));
        OtherPreferencesLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        OtherPreferencesLabel.setText("Outras Preferências");
        OtherPreferencesLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        OtherPreferencesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OtherPreferencesLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OtherPreferencesLabelMouseExited(evt);
            }
        });
        getContentPane().add(OtherPreferencesLabel);
        OtherPreferencesLabel.setBounds(980, 190, 250, 20);

        NotifyLabel.setFont(FonteUtils.carregarRobotoSemiBold(15f));
        NotifyLabel.setForeground(new java.awt.Color(209, 213, 219));
        NotifyLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NotifyLabel.setText("Notificações");
        NotifyLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        NotifyLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NotifyLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NotifyLabelMouseExited(evt);
            }
        });
        getContentPane().add(NotifyLabel);
        NotifyLabel.setBounds(601, 190, 250, 20);

        InterfaceThemeLabel.setFont(FonteUtils.carregarRobotoSemiBold(15f));
        InterfaceThemeLabel.setForeground(new java.awt.Color(209, 213, 219));
        InterfaceThemeLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        InterfaceThemeLabel.setText("Tema da Interface");
        InterfaceThemeLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        InterfaceThemeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                InterfaceThemeLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                InterfaceThemeLabelMouseExited(evt);
            }
        });
        getContentPane().add(InterfaceThemeLabel);
        InterfaceThemeLabel.setBounds(289, 337, 250, 20);

        SystemLinguageLabel.setFont(FonteUtils.carregarRobotoSemiBold(15f));
        SystemLinguageLabel.setForeground(new java.awt.Color(209, 213, 219));
        SystemLinguageLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SystemLinguageLabel.setText("Idioma do Sistema");
        SystemLinguageLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        SystemLinguageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SystemLinguageLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SystemLinguageLabelMouseExited(evt);
            }
        });
        getContentPane().add(SystemLinguageLabel);
        SystemLinguageLabel.setBounds(289, 190, 250, 20);

        OtherPreferencesPanel.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(OtherPreferencesPanel);
        OtherPreferencesPanel.setBounds(967, 175, 281, 373);

        NotifyPanel.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(NotifyPanel);
        NotifyPanel.setBounds(586, 175, 352, 373);

        InterfaceThemePanel.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(InterfaceThemePanel);
        InterfaceThemePanel.setBounds(276, 322, 281, 226);

        SystemLinguagePanel.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(SystemLinguagePanel);
        SystemLinguagePanel.setBounds(276, 175, 281, 132);

        arrowBackButton.setBackground(new java.awt.Color(51, 65, 85));
        arrowBackButton.setContentAreaFilled(false);
        arrowBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                arrowBackButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                arrowBackButtonMouseExited(evt);
            }
        });
        arrowBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrowBackButtonActionPerformed(evt);
            }
        });
        getContentPane().add(arrowBackButton);
        arrowBackButton.setBounds(5, 6, 28, 28);

        arrowBackgroundButton.setBackground(new java.awt.Color(51, 65, 85));
        getContentPane().add(arrowBackgroundButton);
        arrowBackgroundButton.setBounds(5, 6, 28, 28);

        AccountButton.setBackground(new java.awt.Color(51, 65, 85));
        AccountButton.setContentAreaFilled(false);
        AccountButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AccountButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AccountButtonMouseExited(evt);
            }
        });
        AccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountButtonActionPerformed(evt);
            }
        });
        getContentPane().add(AccountButton);
        AccountButton.setBounds(606, 108, 100, 30);

        AccountSeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(AccountSeparator);
        AccountSeparator.setBounds(606, 137, 100, 1);

        AccountLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        AccountLabel.setForeground(new java.awt.Color(209, 213, 219));
        AccountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AccountLabel.setText("Conta");
        AccountLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        AccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AccountLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AccountLabelMouseExited(evt);
            }
        });
        getContentPane().add(AccountLabel);
        AccountLabel.setBounds(606, 112, 100, 20);

        PreferencesButton.setBackground(new java.awt.Color(51, 65, 85));
        PreferencesButton.setContentAreaFilled(false);
        PreferencesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PreferencesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PreferencesButtonMouseExited(evt);
            }
        });
        PreferencesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreferencesButtonActionPerformed(evt);
            }
        });
        getContentPane().add(PreferencesButton);
        PreferencesButton.setBounds(496, 108, 100, 30);

        PreferencesSeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(PreferencesSeparator);
        PreferencesSeparator.setBounds(496, 137, 100, 1);

        PreferencesLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        PreferencesLabel.setForeground(new java.awt.Color(209, 213, 219));
        PreferencesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PreferencesLabel.setText("Preferências");
        PreferencesLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        PreferencesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PreferencesLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PreferencesLabelMouseExited(evt);
            }
        });
        getContentPane().add(PreferencesLabel);
        PreferencesLabel.setBounds(496, 112, 100, 20);

        SecurityButton.setBackground(new java.awt.Color(51, 65, 85));
        SecurityButton.setContentAreaFilled(false);
        SecurityButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SecurityButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SecurityButtonMouseExited(evt);
            }
        });
        SecurityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SecurityButtonActionPerformed(evt);
            }
        });
        getContentPane().add(SecurityButton);
        SecurityButton.setBounds(386, 108, 100, 30);

        SecuritySeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(SecuritySeparator);
        SecuritySeparator.setBounds(386, 137, 100, 1);

        SecurityLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        SecurityLabel.setForeground(new java.awt.Color(209, 213, 219));
        SecurityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SecurityLabel.setText("Segurança");
        SecurityLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        SecurityLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SecurityLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SecurityLabelMouseExited(evt);
            }
        });
        getContentPane().add(SecurityLabel);
        SecurityLabel.setBounds(386, 112, 100, 20);

        ProfileButton.setBackground(new java.awt.Color(51, 65, 85));
        ProfileButton.setContentAreaFilled(false);
        ProfileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProfileButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProfileButtonMouseExited(evt);
            }
        });
        ProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfileButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ProfileButton);
        ProfileButton.setBounds(276, 108, 100, 30);

        ProfileSeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(ProfileSeparator);
        ProfileSeparator.setBounds(276, 137, 100, 1);

        ProfileLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        ProfileLabel.setForeground(new java.awt.Color(209, 213, 219));
        ProfileLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileLabel.setText("Perfil");
        ProfileLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ProfileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProfileLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProfileLabelMouseExited(evt);
            }
        });
        getContentPane().add(ProfileLabel);
        ProfileLabel.setBounds(276, 112, 100, 20);

        Separator.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(Separator);
        Separator.setBounds(240, 137, 1040, 1);

        lblIconSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Magnifier Icon.png"))); // NOI18N
        getContentPane().add(lblIconSearch);
        lblIconSearch.setBounds(210, 55, 13, 13);

        txtBuscar.setBackground(new java.awt.Color(45, 55, 72));
        txtBuscar.setFont(FonteUtils.carregarLato(12f));
        txtBuscar.setForeground(new java.awt.Color(255, 255, 255));
        txtBuscar.setBorder(null);
        getContentPane().add(txtBuscar);
        txtBuscar.setBounds(7, 50, 225, 26);

        lblUsuarioName.setFont(FonteUtils.carregarRobotoRegular(14f));
        lblUsuarioName.setForeground(new java.awt.Color(209, 213, 219));
        lblUsuarioName.setText("John Doe");
        getContentPane().add(lblUsuarioName);
        lblUsuarioName.setBounds(55, 680, 160, 30);

        lblUsuarioIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(lblUsuarioIcon);
        lblUsuarioIcon.setBounds(13, 680, 30, 30);

        horizontalDivider.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(horizontalDivider);
        horizontalDivider.setBounds(0, 665, 240, 2);

        lblTitleBanner.setFont(FonteUtils.carregarRobotoBold(20f));
        lblTitleBanner.setForeground(new java.awt.Color(237, 247, 248));
        lblTitleBanner.setText("Configurações");
        lblTitleBanner.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblTitleBanner);
        lblTitleBanner.setBounds(276, 69, 328, 30);

        btnAdmin.setBackground(new java.awt.Color(51, 65, 85));
        btnAdmin.setContentAreaFilled(false);
        btnAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdminMouseExited(evt);
            }
        });
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdmin);
        btnAdmin.setBounds(5, 300, 232, 30);

        lblIconAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Administrative Icon Lateral Bar.png"))); // NOI18N
        getContentPane().add(lblIconAdmin);
        lblIconAdmin.setBounds(25, 308, 14, 14);

        lblButtonAdmin.setFont(FonteUtils.carregarRobotoRegular(12f));
        lblButtonAdmin.setForeground(new java.awt.Color(209, 213, 219));
        lblButtonAdmin.setText("Administração");
        getContentPane().add(lblButtonAdmin);
        lblButtonAdmin.setBounds(55, 308, 150, 14);

        pnlBarButtonAdmin.setBackground(new java.awt.Color(208, 170, 10));
        getContentPane().add(pnlBarButtonAdmin);
        pnlBarButtonAdmin.setBounds(5, 308, 3, 15);

        pnlButtonAdmin.setBackground(new java.awt.Color(51, 65, 85));
        getContentPane().add(pnlButtonAdmin);
        pnlButtonAdmin.setBounds(5, 300, 232, 30);

        btnConfig.setBackground(new java.awt.Color(51, 65, 85));
        btnConfig.setContentAreaFilled(false);
        btnConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfigMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfigMouseExited(evt);
            }
        });
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfig);
        btnConfig.setBounds(5, 265, 232, 30);

        lblIconConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Settings Icon Lateral Bar.png"))); // NOI18N
        getContentPane().add(lblIconConfig);
        lblIconConfig.setBounds(25, 273, 14, 14);

        lblButtonConfig.setFont(FonteUtils.carregarRobotoRegular(12f));
        lblButtonConfig.setForeground(new java.awt.Color(209, 213, 219));
        lblButtonConfig.setText("Configurações");
        getContentPane().add(lblButtonConfig);
        lblButtonConfig.setBounds(55, 273, 150, 14);

        pnlBarButtonConfig.setBackground(new java.awt.Color(208, 170, 10));
        getContentPane().add(pnlBarButtonConfig);
        pnlBarButtonConfig.setBounds(5, 273, 3, 15);

        pnlButtonConfig.setBackground(new java.awt.Color(51, 65, 85));
        getContentPane().add(pnlButtonConfig);
        pnlButtonConfig.setBounds(5, 265, 232, 30);

        btnTask.setBackground(new java.awt.Color(51, 65, 85));
        btnTask.setContentAreaFilled(false);
        btnTask.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTaskMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTaskMouseExited(evt);
            }
        });
        btnTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaskActionPerformed(evt);
            }
        });
        getContentPane().add(btnTask);
        btnTask.setBounds(5, 230, 232, 30);

        lblIconTask.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Tasks Icon Lateral Bar.png"))); // NOI18N
        getContentPane().add(lblIconTask);
        lblIconTask.setBounds(25, 238, 14, 14);

        lblButtonTask.setFont(FonteUtils.carregarRobotoRegular(12f));
        lblButtonTask.setForeground(new java.awt.Color(209, 213, 219));
        lblButtonTask.setText("Tarefas");
        getContentPane().add(lblButtonTask);
        lblButtonTask.setBounds(55, 238, 150, 14);

        pnlBarButtonTask.setBackground(new java.awt.Color(208, 170, 10));
        getContentPane().add(pnlBarButtonTask);
        pnlBarButtonTask.setBounds(5, 238, 3, 15);

        pnlButtonTask.setBackground(new java.awt.Color(51, 65, 85));
        getContentPane().add(pnlButtonTask);
        pnlButtonTask.setBounds(5, 230, 232, 30);

        btnReport.setBackground(new java.awt.Color(51, 65, 85));
        btnReport.setContentAreaFilled(false);
        btnReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReportMouseExited(evt);
            }
        });
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });
        getContentPane().add(btnReport);
        btnReport.setBounds(5, 195, 232, 30);

        lblIconReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Report Icon Lateral Bar.png"))); // NOI18N
        getContentPane().add(lblIconReport);
        lblIconReport.setBounds(25, 203, 14, 14);

        lblButtonReport.setFont(FonteUtils.carregarRobotoRegular(12f));
        lblButtonReport.setForeground(new java.awt.Color(209, 213, 219));
        lblButtonReport.setText("Relatórios");
        getContentPane().add(lblButtonReport);
        lblButtonReport.setBounds(55, 203, 150, 14);

        pnlBarButtonReport.setBackground(new java.awt.Color(208, 170, 10));
        getContentPane().add(pnlBarButtonReport);
        pnlBarButtonReport.setBounds(5, 203, 3, 15);

        pnlButtonReport.setBackground(new java.awt.Color(51, 65, 85));
        getContentPane().add(pnlButtonReport);
        pnlButtonReport.setBounds(5, 195, 232, 30);

        btnClient.setBackground(new java.awt.Color(51, 65, 85));
        btnClient.setContentAreaFilled(false);
        btnClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClientMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClientMouseExited(evt);
            }
        });
        btnClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientActionPerformed(evt);
            }
        });
        getContentPane().add(btnClient);
        btnClient.setBounds(5, 160, 232, 30);

        lblIconClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Client Icon Lateral Bar.png"))); // NOI18N
        getContentPane().add(lblIconClient);
        lblIconClient.setBounds(25, 168, 14, 14);

        lblButtonClient.setFont(FonteUtils.carregarRobotoRegular(12f));
        lblButtonClient.setForeground(new java.awt.Color(209, 213, 219));
        lblButtonClient.setText("Clientes");
        getContentPane().add(lblButtonClient);
        lblButtonClient.setBounds(55, 168, 150, 14);

        pnlBarButtonClient.setBackground(new java.awt.Color(208, 170, 10));
        getContentPane().add(pnlBarButtonClient);
        pnlBarButtonClient.setBounds(5, 168, 3, 15);

        pnlButtonClient.setBackground(new java.awt.Color(51, 65, 85));
        getContentPane().add(pnlButtonClient);
        pnlButtonClient.setBounds(5, 160, 232, 30);

        btnCalendar.setBackground(new java.awt.Color(51, 65, 85));
        btnCalendar.setContentAreaFilled(false);
        btnCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCalendarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCalendarMouseExited(evt);
            }
        });
        btnCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalendar);
        btnCalendar.setBounds(5, 125, 232, 30);

        lblIconCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar Icon Lateral Bar.png"))); // NOI18N
        getContentPane().add(lblIconCalendar);
        lblIconCalendar.setBounds(25, 133, 14, 14);

        lblButtonCalendar.setFont(FonteUtils.carregarRobotoRegular(12f));
        lblButtonCalendar.setForeground(new java.awt.Color(209, 213, 219));
        lblButtonCalendar.setText("Calendário");
        getContentPane().add(lblButtonCalendar);
        lblButtonCalendar.setBounds(55, 133, 150, 14);

        pnlBarButtonCalendar.setBackground(new java.awt.Color(208, 170, 10));
        getContentPane().add(pnlBarButtonCalendar);
        pnlBarButtonCalendar.setBounds(5, 133, 3, 15);

        pnlButtonCalendar.setBackground(new java.awt.Color(51, 65, 85));
        getContentPane().add(pnlButtonCalendar);
        pnlButtonCalendar.setBounds(5, 125, 232, 30);

        btnHome.setBackground(new java.awt.Color(51, 65, 85));
        btnHome.setContentAreaFilled(false);
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnHome);
        btnHome.setBounds(5, 90, 232, 30);

        lblIconHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Icon Lateral Bar.png"))); // NOI18N
        getContentPane().add(lblIconHome);
        lblIconHome.setBounds(25, 98, 14, 14);

        lblButtonHome.setFont(FonteUtils.carregarRobotoRegular(12f));
        lblButtonHome.setForeground(new java.awt.Color(209, 213, 219));
        lblButtonHome.setText("Home");
        getContentPane().add(lblButtonHome);
        lblButtonHome.setBounds(55, 98, 150, 14);

        pnlBarButtonHome.setBackground(new java.awt.Color(208, 170, 10));
        getContentPane().add(pnlBarButtonHome);
        pnlBarButtonHome.setBounds(5, 98, 3, 15);

        pnlButtonHome.setBackground(new java.awt.Color(51, 65, 85));
        getContentPane().add(pnlButtonHome);
        pnlButtonHome.setBounds(5, 90, 232, 30);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Dark White Icon.png"))); // NOI18N
        btnClose.setContentAreaFilled(false);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose);
        btnClose.setBounds(1240, 0, 40, 40);

        btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Maximize Dark White Icon.png"))); // NOI18N
        btnMaximize.setContentAreaFilled(false);
        btnMaximize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizeActionPerformed(evt);
            }
        });
        getContentPane().add(btnMaximize);
        btnMaximize.setBounds(1200, 0, 40, 40);

        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minimize White Dark Icon.png"))); // NOI18N
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });
        getContentPane().add(btnMinimize);
        btnMinimize.setBounds(1160, 0, 40, 40);

        btnEllipse.setBackground(new java.awt.Color(51, 65, 85));
        btnEllipse.setContentAreaFilled(false);
        btnEllipse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEllipseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEllipseMouseExited(evt);
            }
        });
        btnEllipse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEllipseActionPerformed(evt);
            }
        });
        getContentPane().add(btnEllipse);
        btnEllipse.setBounds(202, 6, 28, 28);

        Ellipse3.setBackground(new java.awt.Color(209, 213, 219));
        getContentPane().add(Ellipse3);
        Ellipse3.setBounds(220, 18, 3, 3);

        Ellipse2.setBackground(new java.awt.Color(209, 213, 219));
        getContentPane().add(Ellipse2);
        Ellipse2.setBounds(215, 18, 3, 3);

        Ellipse1.setBackground(new java.awt.Color(209, 213, 219));
        getContentPane().add(Ellipse1);
        Ellipse1.setBounds(210, 18, 3, 3);

        pnlButtonEllipse.setBackground(new java.awt.Color(51, 65, 85));
        getContentPane().add(pnlButtonEllipse);
        pnlButtonEllipse.setBounds(202, 6, 28, 28);

        lblLogoText.setFont(FonteUtils.carregarRobotoMedium(13f));
        lblLogoText.setForeground(new java.awt.Color(209, 213, 219));
        lblLogoText.setText("CT CONTAB");
        getContentPane().add(lblLogoText);
        lblLogoText.setBounds(70, 13, 150, 15);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Icon.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(35, 8, 25, 25);

        pnlLateralBar.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(pnlLateralBar);
        pnlLateralBar.setBounds(0, 40, 240, 680);

        pnlSuperiorBar.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(pnlSuperiorBar);
        pnlSuperiorBar.setBounds(0, 0, 1280, 40);

        pnlMainBackground.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(pnlMainBackground);
        pnlMainBackground.setBounds(0, 0, 1280, 720);

        setSize(new java.awt.Dimension(1280, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnMaximizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizeActionPerformed
    }//GEN-LAST:event_btnMaximizeActionPerformed

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        gerenciarHover(pnlBarButtonHome, pnlButtonHome, true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        gerenciarHover(pnlBarButtonHome, pnlButtonHome, false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnHomeMouseExited

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        irParaHome();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnCalendarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalendarMouseEntered
        gerenciarHover(pnlBarButtonCalendar, pnlButtonCalendar, true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnCalendarMouseEntered

    private void btnCalendarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalendarMouseExited
        gerenciarHover(pnlBarButtonCalendar, pnlButtonCalendar, false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnCalendarMouseExited

    private void btnCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarActionPerformed
        irParaCalendario();
    }//GEN-LAST:event_btnCalendarActionPerformed

    private void btnClientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientMouseEntered
        gerenciarHover(pnlBarButtonClient, pnlButtonClient, true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnClientMouseEntered

    private void btnClientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientMouseExited
        gerenciarHover(pnlBarButtonClient, pnlButtonClient, false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnClientMouseExited

    private void btnClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientActionPerformed
        irParaClientes();
    }//GEN-LAST:event_btnClientActionPerformed

    private void btnReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportMouseEntered
        gerenciarHover(pnlBarButtonReport, pnlButtonReport, true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnReportMouseEntered

    private void btnReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportMouseExited
        gerenciarHover(pnlBarButtonReport, pnlButtonReport, false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnReportMouseExited

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        irParaRelatorios();
    }//GEN-LAST:event_btnReportActionPerformed

    private void btnTaskMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaskMouseEntered
        gerenciarHover(pnlBarButtonTask, pnlButtonTask, true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnTaskMouseEntered

    private void btnTaskMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaskMouseExited
        gerenciarHover(pnlBarButtonTask, pnlButtonTask, false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnTaskMouseExited

    private void btnTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaskActionPerformed
        irParaTarefas();
    }//GEN-LAST:event_btnTaskActionPerformed

    private void btnConfigMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigMouseEntered
        timerVoltarHome.stop();
        pnlBarButtonConfig.setVisible(true);
        pnlButtonConfig.setVisible(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnConfigMouseEntered

    private void btnConfigMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigMouseExited
        timerVoltarHome.start();
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnConfigMouseExited

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        irParaConfiguracoes();
    }//GEN-LAST:event_btnConfigActionPerformed

    private void btnAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminMouseEntered
        gerenciarHover(pnlBarButtonAdmin, pnlButtonAdmin, true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnAdminMouseEntered

    private void btnAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminMouseExited
        gerenciarHover(pnlBarButtonAdmin, pnlButtonAdmin, false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnAdminMouseExited

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        irParaAdministracao();
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnEllipseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEllipseMouseEntered
        pnlButtonEllipse.setVisible(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnEllipseMouseEntered

    private void btnEllipseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEllipseMouseExited
        pnlButtonEllipse.setVisible(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnEllipseMouseExited

    private void btnEllipseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEllipseActionPerformed
    }//GEN-LAST:event_btnEllipseActionPerformed

    private void ProfileButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProfileLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_ProfileButtonMouseEntered

    private void ProfileButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProfileLabel.setForeground(COR_ABA_INATIVA);
    }//GEN-LAST:event_ProfileButtonMouseExited

    private void ProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileButtonActionPerformed
        irParaPerfil();
    }//GEN-LAST:event_ProfileButtonActionPerformed

    private void SecurityButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SecurityButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SecurityLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_SecurityButtonMouseEntered

    private void SecurityButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SecurityButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SecurityLabel.setForeground(COR_ABA_INATIVA);
    }//GEN-LAST:event_SecurityButtonMouseExited

    private void SecurityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SecurityButtonActionPerformed
        irParaSeguranca();
    }//GEN-LAST:event_SecurityButtonActionPerformed

    private void arrowBackButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arrowBackButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        arrowBackgroundButton.setVisible(true);
    }//GEN-LAST:event_arrowBackButtonMouseEntered

    private void arrowBackButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arrowBackButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        arrowBackgroundButton.setVisible(false);
    }//GEN-LAST:event_arrowBackButtonMouseExited

    private void arrowBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrowBackButtonActionPerformed
        irParaConfiguracoes();
    }//GEN-LAST:event_arrowBackButtonActionPerformed

    private void ProfileLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProfileLabel.setForeground(new java.awt.Color(205, 168, 12));
    }//GEN-LAST:event_ProfileLabelMouseEntered

    private void ProfileLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProfileLabel.setForeground(new java.awt.Color(209, 213, 219));
    }//GEN-LAST:event_ProfileLabelMouseExited

    private void SecurityLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SecurityLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SecurityLabel.setForeground(new java.awt.Color(205, 168, 12));
    }//GEN-LAST:event_SecurityLabelMouseEntered

    private void SecurityLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SecurityLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SecurityLabel.setForeground(COR_ABA_INATIVA);
    }//GEN-LAST:event_SecurityLabelMouseExited

    private void PreferencesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreferencesLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PreferencesLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_PreferencesLabelMouseEntered

    private void PreferencesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreferencesLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PreferencesLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_PreferencesLabelMouseExited

    private void PreferencesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreferencesButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PreferencesLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_PreferencesButtonMouseEntered

    private void PreferencesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreferencesButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PreferencesLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_PreferencesButtonMouseExited

    private void PreferencesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreferencesButtonActionPerformed
        irParaPreferencias();
    }//GEN-LAST:event_PreferencesButtonActionPerformed

    private void AccountLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccountLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AccountLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_AccountLabelMouseEntered

    private void AccountLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccountLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        AccountLabel.setForeground(COR_ABA_INATIVA);
    }//GEN-LAST:event_AccountLabelMouseExited

    private void AccountButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccountButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AccountLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_AccountButtonMouseEntered

    private void AccountButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccountButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        AccountLabel.setForeground(COR_ABA_INATIVA);
    }//GEN-LAST:event_AccountButtonMouseExited

    private void AccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountButtonActionPerformed
        irParaConta();
    }//GEN-LAST:event_AccountButtonActionPerformed

    private void SystemLinguageLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SystemLinguageLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SystemLinguageLabelMouseEntered

    private void SystemLinguageLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SystemLinguageLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_SystemLinguageLabelMouseExited

    private void InterfaceThemeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeLabelMouseEntered

    private void InterfaceThemeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeLabelMouseExited

    private void NotifyLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifyLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifyLabelMouseEntered

    private void NotifyLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifyLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifyLabelMouseExited

    private void OtherPreferencesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OtherPreferencesLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_OtherPreferencesLabelMouseEntered

    private void OtherPreferencesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OtherPreferencesLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_OtherPreferencesLabelMouseExited

    private void SystemLinguageDescLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SystemLinguageDescLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SystemLinguageDescLabelMouseEntered

    private void SystemLinguageDescLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SystemLinguageDescLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_SystemLinguageDescLabelMouseExited

    private void NotifyDescLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifyDescLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifyDescLabelMouseEntered

    private void NotifyDescLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifyDescLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifyDescLabelMouseExited

    private void OtherPreferencesDescLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OtherPreferencesDescLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_OtherPreferencesDescLabelMouseEntered

    private void OtherPreferencesDescLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OtherPreferencesDescLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_OtherPreferencesDescLabelMouseExited

    private void InterfaceThemeDescLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeDescLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeDescLabelMouseEntered

    private void InterfaceThemeDescLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeDescLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeDescLabelMouseExited

    private void TimeZoneSubTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TimeZoneSubTitleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TimeZoneSubTitleLabelMouseEntered

    private void TimeZoneSubTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TimeZoneSubTitleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TimeZoneSubTitleLabelMouseExited

    private void DateFormatSubTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DateFormatSubTitleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DateFormatSubTitleLabelMouseEntered

    private void DateFormatSubTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DateFormatSubTitleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DateFormatSubTitleLabelMouseExited

    private void HourFormatSubTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HourFormatSubTitleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_HourFormatSubTitleLabelMouseEntered

    private void HourFormatSubTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HourFormatSubTitleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_HourFormatSubTitleLabelMouseExited

    private void InterfaceThemeSubTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeSubTitleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeSubTitleLabelMouseEntered

    private void InterfaceThemeSubTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeSubTitleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeSubTitleLabelMouseExited

    private void InterfaceThemeSubTitleLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeSubTitleLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeSubTitleLabel2MouseEntered

    private void InterfaceThemeSubTitleLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeSubTitleLabel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeSubTitleLabel2MouseExited

    private void InterfaceThemeSubDescLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeSubDescLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeSubDescLabel2MouseEntered

    private void InterfaceThemeSubDescLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeSubDescLabel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeSubDescLabel2MouseExited

    private void InterfaceThemeSubDescLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeSubDescLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeSubDescLabelMouseEntered

    private void InterfaceThemeSubDescLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InterfaceThemeSubDescLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_InterfaceThemeSubDescLabelMouseExited

    private void NotifySubTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubTitleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubTitleLabelMouseEntered

    private void NotifySubTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubTitleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubTitleLabelMouseExited

    private void NotifySubDescLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubDescLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubDescLabelMouseEntered

    private void NotifySubDescLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubDescLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubDescLabelMouseExited

    private void NotifySubTitleLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubTitleLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubTitleLabel2MouseEntered

    private void NotifySubTitleLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubTitleLabel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubTitleLabel2MouseExited

    private void NotifySubDescLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubDescLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubDescLabel2MouseEntered

    private void NotifySubDescLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubDescLabel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubDescLabel2MouseExited

    private void NotifySubTitleLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubTitleLabel3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubTitleLabel3MouseEntered

    private void NotifySubTitleLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubTitleLabel3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubTitleLabel3MouseExited

    private void NotifySubDescLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubDescLabel3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubDescLabel3MouseEntered

    private void NotifySubDescLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotifySubDescLabel3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifySubDescLabel3MouseExited

    private void HourFormatComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HourFormatComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HourFormatComboBoxActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfigPreferences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigPreferences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigPreferences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigPreferences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    class RoundedPanel extends javax.swing.JPanel {

        private int radius;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
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

    class RoundedTextField extends javax.swing.JTextField {

        private String placeholderText = "";

        public RoundedTextField() {
            setOpaque(false);
            javax.swing.border.Border padding = javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10);
            setBorder(padding);
            setForeground(java.awt.Color.WHITE);
            setCaretColor(java.awt.Color.WHITE);
        }

        public void setPlaceholder(String text) {
            this.placeholderText = text;
            repaint();
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new java.awt.Color(40, 50, 70));
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);

            super.paintComponent(g);

            if (getText().isEmpty()) {
                g2.setColor(new java.awt.Color(150, 150, 150));
                g2.setFont(getFont());
                java.awt.FontMetrics fm = g2.getFontMetrics();
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(placeholderText, 8, y);
            }
            g2.dispose();
        }

        @Override
        protected void paintBorder(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new java.awt.Color(62, 76, 89));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
            g2.dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AccountButton;
    private javax.swing.JLabel AccountLabel;
    private javax.swing.JPanel AccountSeparator;
    private javax.swing.JComboBox<String> DateFormatComboBox;
    private javax.swing.JLabel DateFormatSubTitleLabel;
    private javax.swing.JPanel Ellipse1;
    private javax.swing.JPanel Ellipse2;
    private javax.swing.JPanel Ellipse3;
    private javax.swing.JComboBox<String> HourFormatComboBox;
    private javax.swing.JLabel HourFormatSubTitleLabel;
    private javax.swing.JLabel InterfaceThemeDescLabel;
    private javax.swing.JLabel InterfaceThemeLabel;
    private javax.swing.JToggleButton InterfaceThemeOnOffButton;
    private javax.swing.JToggleButton InterfaceThemeOnOffButton2;
    private javax.swing.JPanel InterfaceThemePanel;
    private javax.swing.JLabel InterfaceThemeSubDescLabel;
    private javax.swing.JLabel InterfaceThemeSubDescLabel2;
    private javax.swing.JPanel InterfaceThemeSubPanel;
    private javax.swing.JPanel InterfaceThemeSubPanel2;
    private javax.swing.JLabel InterfaceThemeSubTitleLabel;
    private javax.swing.JLabel InterfaceThemeSubTitleLabel2;
    private javax.swing.JLabel LanguageArrowIconLabel;
    private javax.swing.JLabel LanguageIconLabel;
    private javax.swing.JComboBox<String> LinguageComboBox;
    private javax.swing.JLabel NotifyDescLabel;
    private javax.swing.JLabel NotifyIconLabel;
    private javax.swing.JLabel NotifyIconLabel2;
    private javax.swing.JLabel NotifyIconLabel3;
    private javax.swing.JLabel NotifyLabel;
    private javax.swing.JToggleButton NotifyOnOffButton;
    private javax.swing.JToggleButton NotifyOnOffButton2;
    private javax.swing.JToggleButton NotifyOnOffButton3;
    private javax.swing.JPanel NotifyPanel;
    private javax.swing.JLabel NotifySubDescLabel;
    private javax.swing.JLabel NotifySubDescLabel2;
    private javax.swing.JLabel NotifySubDescLabel3;
    private javax.swing.JLabel NotifySubTitleLabel;
    private javax.swing.JLabel NotifySubTitleLabel2;
    private javax.swing.JLabel NotifySubTitleLabel3;
    private javax.swing.JLabel OtherPreferencesArrowIconLabel;
    private javax.swing.JLabel OtherPreferencesArrowIconLabel2;
    private javax.swing.JLabel OtherPreferencesArrowIconLabel3;
    private javax.swing.JLabel OtherPreferencesDescLabel;
    private javax.swing.JLabel OtherPreferencesLabel;
    private javax.swing.JPanel OtherPreferencesPanel;
    private javax.swing.JButton PreferencesButton;
    private javax.swing.JLabel PreferencesLabel;
    private javax.swing.JPanel PreferencesSeparator;
    private javax.swing.JButton ProfileButton;
    private javax.swing.JLabel ProfileLabel;
    private javax.swing.JPanel ProfileSeparator;
    private javax.swing.JButton SecurityButton;
    private javax.swing.JLabel SecurityLabel;
    private javax.swing.JPanel SecuritySeparator;
    private javax.swing.JPanel Separator;
    private javax.swing.JLabel SystemLinguageDescLabel;
    private javax.swing.JLabel SystemLinguageLabel;
    private javax.swing.JPanel SystemLinguagePanel;
    private javax.swing.JComboBox<String> TimeZoneComboBox;
    private javax.swing.JLabel TimeZoneSubTitleLabel;
    private javax.swing.JButton arrowBackButton;
    private javax.swing.JPanel arrowBackgroundButton;
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnCalendar;
    private javax.swing.JButton btnClient;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnConfig;
    private javax.swing.JButton btnEllipse;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnMaximize;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnTask;
    private javax.swing.JPanel horizontalDivider;
    private javax.swing.JLabel lblButtonAdmin;
    private javax.swing.JLabel lblButtonCalendar;
    private javax.swing.JLabel lblButtonClient;
    private javax.swing.JLabel lblButtonConfig;
    private javax.swing.JLabel lblButtonHome;
    private javax.swing.JLabel lblButtonReport;
    private javax.swing.JLabel lblButtonTask;
    private javax.swing.JLabel lblIconAdmin;
    private javax.swing.JLabel lblIconCalendar;
    private javax.swing.JLabel lblIconClient;
    private javax.swing.JLabel lblIconConfig;
    private javax.swing.JLabel lblIconHome;
    private javax.swing.JLabel lblIconReport;
    private javax.swing.JLabel lblIconSearch;
    private javax.swing.JLabel lblIconTask;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoText;
    private javax.swing.JLabel lblTitleBanner;
    private javax.swing.JLabel lblUsuarioIcon;
    private javax.swing.JLabel lblUsuarioName;
    private javax.swing.JPanel pnlBarButtonAdmin;
    private javax.swing.JPanel pnlBarButtonCalendar;
    private javax.swing.JPanel pnlBarButtonClient;
    private javax.swing.JPanel pnlBarButtonConfig;
    private javax.swing.JPanel pnlBarButtonHome;
    private javax.swing.JPanel pnlBarButtonReport;
    private javax.swing.JPanel pnlBarButtonTask;
    private javax.swing.JPanel pnlButtonAdmin;
    private javax.swing.JPanel pnlButtonCalendar;
    private javax.swing.JPanel pnlButtonClient;
    private javax.swing.JPanel pnlButtonConfig;
    private javax.swing.JPanel pnlButtonEllipse;
    private javax.swing.JPanel pnlButtonHome;
    private javax.swing.JPanel pnlButtonReport;
    private javax.swing.JPanel pnlButtonTask;
    private javax.swing.JPanel pnlLateralBar;
    private javax.swing.JPanel pnlMainBackground;
    private javax.swing.JPanel pnlSuperiorBar;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
