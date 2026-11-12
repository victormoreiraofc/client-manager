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

public class ConfigProfile extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private final Color COR_PADRAO = new Color(28, 37, 54);
    private final Color COR_HOVER_GERAL = new Color(50, 63, 89);
    private final Color COR_HOVER_FECHAR = new Color(196, 43, 28);
    private javax.swing.Timer timerVoltarHome;
    private final Color COR_REGISTRAR_PADRAO = new Color(205, 168, 12);
    private final Color COR_REGISTRAR_HOVER = new Color(183, 150, 11);
    private final Color COR_BOTAO_DOURADO = new Color(205, 168, 12);
    private final Color COR_BOTAO_PERIGO = new Color(220, 68, 80);
    private final Color COR_BOTAO_FUNDO = new Color(28, 37, 54);
    private final Color COR_BOTAO_FUNDO_HOVER = new Color(37, 49, 71);
    private final Color COR_ABA_ATIVA = new Color(205, 168, 12);
    private final Color COR_ABA_INATIVA = new Color(209, 213, 219);
    private static final long TAMANHO_MAX_IMAGEM = 2L * 1024 * 1024;
    private static final int TAMANHO_MAX_BLOB = 60000;
    private String telefoneOriginal = "";

    public ConfigProfile(Usuario usuario) {
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

        configurarPerfil();
        configurarCampos();
        configurarAbas();
        configurarBotoes();
        arredondarMiniIcones();
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
            
            BufferedImage ProfileIcon = ImageIO.read(getClass().getResource("/images/Jonh Doe Icon.png"));
            BufferedImage ProfileIconBig = ImageIO.read(getClass().getResource("/images/Jonh Doe Icon.png"));
            BufferedImage ProfileMiniCam = ImageIO.read(getClass().getResource("/images/Profile Camera Icon.png"));
            BufferedImage ProfileMiniCam2 = ImageIO.read(getClass().getResource("/images/Profile Camera Icon.png"));
            BufferedImage ButtonSecurityIcon = ImageIO.read(getClass().getResource("/images/Security Button Icon.png"));
            BufferedImage ButtonPreferencesIcon = ImageIO.read(getClass().getResource("/images/Preferences Icon.png"));
            BufferedImage ButtonAccountIcon = ImageIO.read(getClass().getResource("/images/Account Icon.png"));
            BufferedImage ButtonDangerZoneIcon = ImageIO.read(getClass().getResource("/images/Danger Zone Icon.png"));
            BufferedImage ButtonArrow = ImageIO.read(getClass().getResource("/images/Yellow Arrow Right Icon.png"));
            BufferedImage ButtonArrow1 = ImageIO.read(getClass().getResource("/images/Yellow Arrow Right Icon.png"));
            BufferedImage ButtonArrow2 = ImageIO.read(getClass().getResource("/images/Yellow Arrow Right Icon.png"));
            BufferedImage ButtonArrow3 = ImageIO.read(getClass().getResource("/images/Red Arrow Right Icon.png"));
            BufferedImage ButtonArrow4 = ImageIO.read(getClass().getResource("/images/Red Arrow Right Icon.png"));
       

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
            ProfileIconLabel.setIcon(gerarIconePerfeito(ProfileIcon, 75, 75));
            ProfileIconBigLabel.setIcon(gerarIconePerfeito(ProfileIconBig, 100, 100));
            
            ProfileMiniIcon.setIcon(gerarIconePerfeito(ProfileMiniCam, 10, 10));
            ProfileMiniIcon2.setIcon(gerarIconePerfeito(ProfileMiniCam2, 13, 13));
            ButtonSecurityIconLabel.setIcon(gerarIconePerfeito(ButtonSecurityIcon, 15, 15));
            ButtonPreferencesIconLabel.setIcon(gerarIconePerfeito(ButtonPreferencesIcon, 15, 15));
            ButtonAccountIconLabel.setIcon(gerarIconePerfeito(ButtonAccountIcon, 15, 15));
            ButtonDangerZoneIconLabel.setIcon(gerarIconePerfeito(ButtonDangerZoneIcon, 15, 15));
            ButtonArrowIcon.setIcon(gerarIconePerfeito(ButtonArrow, 10, 10));
            ButtonArrowIcon1.setIcon(gerarIconePerfeito(ButtonArrow1, 10, 10));
            ButtonArrowIcon2.setIcon(gerarIconePerfeito(ButtonArrow2, 10, 10));
            ButtonArrowIcon3.setIcon(gerarIconePerfeito(ButtonArrow3, 10, 10));
            ButtonArrowIcon4.setIcon(gerarIconePerfeito(ButtonArrow4, 10, 10));

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
        ProfileIconNameLabel.setText(I18nManager.getString("auth.config.profile.photo_title"));
        ProfileDescriptionLabel.setText(I18nManager.getString("auth.config.profile.photo_hint"));
        UserNameLabel.setText(I18nManager.getString("auth.config.field.user"));
        UserEmailLabel.setText(I18nManager.getString("auth.config.field.email"));
        UserPhoneLabel.setText(I18nManager.getString("auth.config.field.phone"));
        registerButtonLabel.setText(I18nManager.getString("auth.config.button.save"));
        registerButtonLabel1.setText(I18nManager.getString("auth.config.button.change_image"));
        ButtonSecurityTitleLabel.setText(I18nManager.getString("auth.config.card.security.title"));
        ButtonSecurityDescriptionLabel.setText(I18nManager.getString("auth.config.card.security.description"));
        registerButtonLabel2.setText(I18nManager.getString("auth.config.card.security.button"));
        ButtonPreferencesTitleLabel.setText(I18nManager.getString("auth.config.card.preferences.title"));
        ButtonPreferencesDescriptionLabel.setText(I18nManager.getString("auth.config.card.preferences.description"));
        registerButtonLabel3.setText(I18nManager.getString("auth.config.card.preferences.button"));
        ButtonAccountTitleLabel.setText(I18nManager.getString("auth.config.card.account.title"));
        ButtonAccountDescriptionLabel.setText(I18nManager.getString("auth.config.card.account.description"));
        registerButtonLabel4.setText(I18nManager.getString("auth.config.card.account.button"));
        ButtonDangerZoneTitleLabel.setText(I18nManager.getString("auth.config.card.danger.title"));
        ButtonDangerZoneDescriptionLabel.setText(I18nManager.getString("auth.config.card.danger.description"));
        registerButtonLabel5.setText(I18nManager.getString("auth.config.card.danger.button_delete"));
        registerButtonLabel6.setText(I18nManager.getString("auth.config.card.danger.button_logout"));
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
        ProfileLabel.setForeground(COR_ABA_ATIVA);
        ProfileSeparator.setVisible(true);

        SecurityLabel.setForeground(COR_ABA_INATIVA);
        SecuritySeparator.setVisible(false);
        PreferencesLabel.setForeground(COR_ABA_INATIVA);
        PreferencesSeparator.setVisible(false);
        AccountLabel.setForeground(COR_ABA_INATIVA);
        AccountSeparator.setVisible(false);

        java.awt.event.MouseAdapter cliqueParaHome = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                irParaHome();
            }
        };
        SecurityLabel.addMouseListener(cliqueParaHome);
        PreferencesLabel.addMouseListener(cliqueParaHome);
        AccountLabel.addMouseListener(cliqueParaHome);
    }

    private void configurarPerfil() {
        IconUtil.setIcon(usuarioLogado, ProfileIconLabel, 75, 75);
        IconUtil.setIcon(usuarioLogado, ProfileIconBigLabel, 100, 100);

        ProfileNameLabel.setText(capitalizar(usuarioLogado.getUsuario()));
        UserNameField.setText(usuarioLogado.getUsuario());
        UserEmailField.setText(usuarioLogado.getEmail());
        PermissionLabel.setText(textoPermissao(usuarioLogado.getPermissao()));
        LastAcessLabel.setText(textoUltimoAcesso(usuarioLogado.getUltimoAcesso()));

        try {
            telefoneOriginal = CTCONTAB.buscarTelefone(usuarioLogado.getUsuario());
        } catch (Exception e) {
            System.err.println("Erro ao buscar telefone: " + e.getMessage());
            telefoneOriginal = "";
        }
        UserPhoneField.setText(telefoneOriginal);
    }

    private String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return "";
        }
        return Character.toUpperCase(texto.charAt(0)) + texto.substring(1);
    }

    private void configurarCampos() {
        javax.swing.JTextField[] campos = {UserNameField, UserEmailField, UserPhoneField};
        for (javax.swing.JTextField campo : campos) {
            campo.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    campo.getBorder(),
                    javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        }
    }

    private javax.swing.JPanel arredondarPainel(javax.swing.JPanel original) {
        java.awt.Container pai = original.getParent();
        int posicao = pai.getComponentZOrder(original);
        int diametro = Math.min(original.getWidth(), original.getHeight());

        RoundedPanel redondo = new RoundedPanel(diametro);
        redondo.setBackground(original.getBackground());
        redondo.setBounds(original.getBounds());

        pai.remove(original);
        pai.add(redondo, posicao);
        pai.revalidate();
        pai.repaint();
        return redondo;
    }

    private void arredondarMiniIcones() {
        ProfileMiniIconLabel = arredondarPainel(ProfileMiniIconLabel);
        ProfileMiniIconLabel1 = arredondarPainel(ProfileMiniIconLabel1);
    }

    private void encerrarSessao() {
        new Login().setVisible(true);
        this.dispose();
    }

    private void excluirConta() {
        boolean confirmado = MensagemUtil.exibirConfirmacao(
                I18nManager.getString("auth.config.delete_account.title"),
                String.format(I18nManager.getString("auth.config.delete_account.message"), usuarioLogado.getUsuario()),
                I18nManager.getString("auth.config.delete_account.button.yes"),
                I18nManager.getString("auth.config.delete_account.button.no"));

        if (!confirmado) {
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            String nome = usuarioLogado.getUsuario();
            if (CTCONTAB.deleteUsuario(usuarioLogado.getId())) {
                AuditUtil.registrar(nome, "audit.account_deleted", nome);
                MensagemUtil.exibirSucesso(I18nManager.getString("auth.config.delete_account.success"));
                new Login().setVisible(true);
                this.dispose();
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.config.delete_account.linked"));
        } catch (Exception ex) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.config.delete_account.error"));
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private String textoPermissao(String permissao) {
        if (permissao != null) {
            switch (permissao.trim().toUpperCase()) {
                case "ADM":
                    return I18nManager.getString("auth.config.permission.adm");
                case "SUPER_ADM":
                    return I18nManager.getString("auth.config.permission.super_adm");
                default:
                    break;
            }
        }
        return I18nManager.getString("auth.config.permission.null");
    }

    private String textoUltimoAcesso(String acesso) {
        String detalhe;
        try {
            java.time.LocalDateTime momento = java.time.LocalDateTime.parse(acesso.trim().replace(' ', 'T'));
            java.time.LocalDate hoje = java.time.LocalDate.now();
            String hora = momento.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
            if (momento.toLocalDate().equals(hoje)) {
                detalhe = String.format(I18nManager.getString("auth.config.last_access.today"), hora);
            } else if (momento.toLocalDate().equals(hoje.minusDays(1))) {
                detalhe = String.format(I18nManager.getString("auth.config.last_access.yesterday"), hora);
            } else {
                detalhe = String.format(I18nManager.getString("auth.config.last_access.date"),
                        momento.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")), hora);
            }
        } catch (Exception e) {
            return I18nManager.getString("auth.config.last_access.first");
        }
        return String.format(I18nManager.getString("auth.config.last_access.label"), detalhe);
    }

    private void configurarBotoes() {
        javax.swing.JButton[] botoes = {registerButton1, registerButton2, registerButton3, registerButton4, registerButton5, registerButton6};
        for (javax.swing.JButton botao : botoes) {
            botao.setFocusPainted(false);
            botao.setBorderPainted(false);
        }

        estilizarBotaoContorno(registerButtonPanel1, registerButtonLabel1, COR_BOTAO_DOURADO, false);
        estilizarBotaoContorno(registerButtonPanel2, registerButtonLabel2, COR_BOTAO_DOURADO, true);
        estilizarBotaoContorno(registerButtonPanel3, registerButtonLabel3, COR_BOTAO_DOURADO, true);
        estilizarBotaoContorno(registerButtonPanel4, registerButtonLabel4, COR_BOTAO_DOURADO, true);
        estilizarBotaoContorno(registerButtonPanel5, registerButtonLabel5, COR_BOTAO_PERIGO, true);
        estilizarBotaoContorno(registerButtonPanel6, registerButtonLabel6, COR_BOTAO_PERIGO, true);

        registerButtonPanel.setBackground(COR_REGISTRAR_PADRAO);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
    }

    private void estilizarBotaoContorno(javax.swing.JPanel painel, javax.swing.JLabel rotulo, Color cor, boolean alinharEsquerda) {
        RoundedPanel arredondado = (RoundedPanel) painel;
        arredondado.setRadius(8);
        arredondado.setShowBorder(true);
        arredondado.setBorderColor(cor);
        painel.setBackground(COR_BOTAO_FUNDO);

        rotulo.setForeground(cor);
        if (alinharEsquerda) {
            rotulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            rotulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 0));
        } else {
            rotulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        }
    }

    private void hoverBotaoContorno(javax.swing.JPanel painel, boolean entrou) {
        painel.setBackground(entrou ? COR_BOTAO_FUNDO_HOVER : COR_BOTAO_FUNDO);
        setCursor(new java.awt.Cursor(entrou ? java.awt.Cursor.HAND_CURSOR : java.awt.Cursor.DEFAULT_CURSOR));
    }

    private void handleProfileSave() {
        String antigoUsuario = usuarioLogado.getUsuario();
        String antigoEmail = usuarioLogado.getEmail();
        String novoUsuario = UserNameField.getText().trim();
        String novoEmail = UserEmailField.getText().trim();
        String novoTelefone = UserPhoneField.getText().trim();

        if (!novoUsuario.matches("^[a-zA-Z0-9]{1,50}$")) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.config.error.username_invalid"));
            return;
        }
        if (!novoEmail.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$") || novoEmail.length() > 100) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.config.error.email_invalid"));
            return;
        }
        if (!novoTelefone.matches("^[0-9+()\\-\\s]{0,30}$")) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.config.error.phone_invalid"));
            return;
        }

        boolean mudouUsuario = !novoUsuario.equals(antigoUsuario);
        boolean mudouEmail = !novoEmail.equals(antigoEmail);
        boolean mudouTelefone = !novoTelefone.equals(telefoneOriginal);

        if (!mudouUsuario && !mudouEmail && !mudouTelefone) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.config.error.no_changes"));
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            String duplicado = CTCONTAB.verificarDuplicidadePerfil(antigoUsuario, novoUsuario, novoEmail);
            if ("USER".equals(duplicado)) {
                MensagemUtil.exibirErro(I18nManager.getString("auth.config.error.username_taken"));
                return;
            }
            if ("EMAIL".equals(duplicado)) {
                MensagemUtil.exibirErro(I18nManager.getString("auth.config.error.email_taken"));
                return;
            }

            if (CTCONTAB.atualizarPerfil(antigoUsuario, novoUsuario, novoEmail, novoTelefone)) {
                String telefoneAntigo = telefoneOriginal.isEmpty() ? "-" : telefoneOriginal;
                String telefoneNovo = novoTelefone.isEmpty() ? "-" : novoTelefone;

                if (mudouUsuario) {
                    AuditUtil.registrar(novoUsuario, "audit.profile_username_changed", antigoUsuario, novoUsuario);
                }
                if (mudouEmail) {
                    AuditUtil.registrar(novoUsuario, "audit.profile_email_changed", novoUsuario, antigoEmail, novoEmail);
                }
                if (mudouTelefone) {
                    AuditUtil.registrar(novoUsuario, "audit.profile_phone_changed", novoUsuario, telefoneAntigo, telefoneNovo);
                }

                usuarioLogado.setUsuario(novoUsuario);
                usuarioLogado.setEmail(novoEmail);
                usuarioLogado.setTelefone(novoTelefone);
                telefoneOriginal = novoTelefone;

                ProfileNameLabel.setText(capitalizar(novoUsuario));
                lblUsuarioName.setText(novoUsuario);

                MensagemUtil.exibirSucesso(I18nManager.getString("auth.config.save_success"));
            }
        } catch (Exception ex) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.config.save_error"));
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private void escolherImagemPerfil() {
        javax.swing.JFileChooser seletor = new javax.swing.JFileChooser();
        seletor.setDialogTitle(I18nManager.getString("auth.config.image.chooser_title"));
        seletor.setAcceptAllFileFilterUsed(false);
        seletor.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                I18nManager.getString("auth.config.image.chooser_filter"), "png", "jpg", "jpeg", "gif", "bmp"));

        if (seletor.showOpenDialog(this) != javax.swing.JFileChooser.APPROVE_OPTION) {
            return;
        }

        java.io.File arquivo = seletor.getSelectedFile();
        if (arquivo.length() > TAMANHO_MAX_IMAGEM) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.config.image.too_large"));
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            BufferedImage original = ImageIO.read(arquivo);
            if (original == null) {
                MensagemUtil.exibirErro(I18nManager.getString("auth.config.image.invalid"));
                return;
            }

            byte[] bytes = prepararImagemParaBanco(original);
            if (CTCONTAB.atualizarImagemUsuario(usuarioLogado.getUsuario(), bytes)) {
                usuarioLogado.setImagem(bytes);
                IconUtil.setIcon(usuarioLogado, ProfileIconLabel, 75, 75);
                IconUtil.setIcon(usuarioLogado, ProfileIconBigLabel, 100, 100);
                IconUtil.setIcon(usuarioLogado, lblUsuarioIcon, 30, 30);

                AuditUtil.registrar(usuarioLogado.getUsuario(), "audit.profile_image_changed", usuarioLogado.getUsuario());
                MensagemUtil.exibirSucesso(I18nManager.getString("auth.config.image.success"));
            }
        } catch (Exception ex) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.config.image.error"));
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private BufferedImage reduzirImagem(BufferedImage origem, int lado, boolean comAlfa) {
        BufferedImage atual = origem;
        int w = origem.getWidth();
        while (w / 2 >= lado) {
            w = w / 2;
            BufferedImage tmp = new BufferedImage(w, w, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = tmp.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.drawImage(atual, 0, 0, w, w, null);
            g.dispose();
            atual = tmp;
        }

        BufferedImage saida = new BufferedImage(lado, lado, comAlfa ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = saida.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        if (!comAlfa) {
            g2.setColor(new Color(37, 49, 71));
            g2.fillRect(0, 0, lado, lado);
        }
        g2.drawImage(atual, 0, 0, lado, lado, null);
        g2.dispose();
        return saida;
    }

    private byte[] codificarImagem(BufferedImage imagem, String formato, float qualidade) throws IOException {
        java.io.ByteArrayOutputStream saida = new java.io.ByteArrayOutputStream();
        if (formato.equals("jpeg")) {
            javax.imageio.ImageWriter escritor = ImageIO.getImageWritersByFormatName("jpeg").next();
            javax.imageio.ImageWriteParam parametros = escritor.getDefaultWriteParam();
            parametros.setCompressionMode(javax.imageio.ImageWriteParam.MODE_EXPLICIT);
            parametros.setCompressionQuality(qualidade);
            try (javax.imageio.stream.ImageOutputStream fluxo = ImageIO.createImageOutputStream(saida)) {
                escritor.setOutput(fluxo);
                escritor.write(null, new javax.imageio.IIOImage(imagem, null, null), parametros);
            } finally {
                escritor.dispose();
            }
        } else {
            ImageIO.write(imagem, formato, saida);
        }
        return saida.toByteArray();
    }

    private byte[] prepararImagemParaBanco(BufferedImage original) throws IOException {
        int origem = Math.min(original.getWidth(), original.getHeight());
        BufferedImage quadrada = original.getSubimage((original.getWidth() - origem) / 2, (original.getHeight() - origem) / 2, origem, origem);
        boolean temAlfa = original.getColorModel().hasAlpha();

        for (int tamanho : new int[]{256, 192, 128, 96}) {
            int lado = Math.min(tamanho, origem);

            if (temAlfa) {
                byte[] png = codificarImagem(reduzirImagem(quadrada, lado, true), "png", 1f);
                if (png.length <= TAMANHO_MAX_BLOB) {
                    return png;
                }
            }

            BufferedImage semAlfa = reduzirImagem(quadrada, lado, false);
            for (float qualidade = 0.92f; qualidade >= 0.4f; qualidade -= 0.1f) {
                byte[] jpg = codificarImagem(semAlfa, "jpeg", qualidade);
                if (jpg.length <= TAMANHO_MAX_BLOB) {
                    return jpg;
                }
            }
        }
        throw new IOException("Imagem muito complexa para o banco de dados");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ButtonArrowIcon4 = new javax.swing.JLabel();
        ButtonArrowIcon3 = new javax.swing.JLabel();
        ButtonArrowIcon2 = new javax.swing.JLabel();
        ButtonArrowIcon1 = new javax.swing.JLabel();
        ButtonArrowIcon = new javax.swing.JLabel();
        ProfileMiniIcon2 = new javax.swing.JLabel();
        ProfileMiniIcon = new javax.swing.JLabel();
        ProfileMiniIconLabel1 = new javax.swing.JPanel();
        ProfileMiniIconLabel = new javax.swing.JPanel();
        arrowBackButton = new javax.swing.JButton();
        arrowBackgroundButton = new RoundedPanel(30);
        registerButton6 = new javax.swing.JButton();
        registerButton5 = new javax.swing.JButton();
        registerButton4 = new javax.swing.JButton();
        registerButton3 = new javax.swing.JButton();
        registerButton2 = new javax.swing.JButton();
        registerButton1 = new javax.swing.JButton();
        registerButtonLabel6 = new javax.swing.JLabel();
        registerButtonLabel5 = new javax.swing.JLabel();
        registerButtonLabel4 = new javax.swing.JLabel();
        registerButtonLabel3 = new javax.swing.JLabel();
        registerButtonLabel2 = new javax.swing.JLabel();
        registerButtonLabel1 = new javax.swing.JLabel();
        registerButtonPanel6 = new RoundedPanel(40);
        registerButtonPanel5 = new RoundedPanel(40);
        registerButtonPanel4 = new RoundedPanel(40);
        registerButtonPanel3 = new RoundedPanel(40);
        registerButtonPanel2 = new RoundedPanel(40);
        registerButtonPanel1 = new RoundedPanel(40);
        ButtonVerticalDivider3 = new javax.swing.JPanel();
        ButtonVerticalDivider2 = new javax.swing.JPanel();
        ButtonVerticalDivider = new javax.swing.JPanel();
        ProfileVerticalDivider = new javax.swing.JPanel();
        ButtonDangerZoneIconLabel = new javax.swing.JLabel();
        ButtonAccountIconLabel = new javax.swing.JLabel();
        ButtonPreferencesIconLabel = new javax.swing.JLabel();
        ButtonSecurityIconLabel = new javax.swing.JLabel();
        ButtonDangerZoneTitleLabel = new javax.swing.JLabel();
        ButtonAccountTitleLabel = new javax.swing.JLabel();
        ButtonPreferencesTitleLabel = new javax.swing.JLabel();
        ButtonSecurityTitleLabel = new javax.swing.JLabel();
        ButtonDangerZoneDescriptionLabel = new javax.swing.JLabel();
        ButtonAccountDescriptionLabel = new javax.swing.JLabel();
        ButtonPreferencesDescriptionLabel = new javax.swing.JLabel();
        ButtonSecurityDescriptionLabel = new javax.swing.JLabel();
        UserPhoneField = new RoundedTextField();
        UserEmailField = new RoundedTextField();
        UserNameField = new RoundedTextField();
        ProfileDescriptionLabel = new javax.swing.JLabel();
        ProfileIconBigLabel = new javax.swing.JLabel();
        UserPhoneLabel = new javax.swing.JLabel();
        UserEmailLabel = new javax.swing.JLabel();
        UserNameLabel = new javax.swing.JLabel();
        ProfileIconNameLabel = new javax.swing.JLabel();
        registerButton = new javax.swing.JButton();
        registerButtonLabel = new javax.swing.JLabel();
        registerButtonPanel = new RoundedPanel(40);
        ButtonsInfoPanel = new javax.swing.JPanel();
        EditInfoPanel = new javax.swing.JPanel();
        ProfileNameLabel = new javax.swing.JLabel();
        PermissionLabel = new javax.swing.JLabel();
        LastAcessLabel = new javax.swing.JLabel();
        ProfileIconLabel = new javax.swing.JLabel();
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

        ButtonArrowIcon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ButtonArrowIcon4);
        ButtonArrowIcon4.setBounds(1197, 587, 10, 10);

        ButtonArrowIcon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ButtonArrowIcon3);
        ButtonArrowIcon3.setBounds(1197, 620, 10, 10);

        ButtonArrowIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ButtonArrowIcon2);
        ButtonArrowIcon2.setBounds(950, 620, 10, 10);

        ButtonArrowIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ButtonArrowIcon1);
        ButtonArrowIcon1.setBounds(703, 620, 10, 10);

        ButtonArrowIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ButtonArrowIcon);
        ButtonArrowIcon.setBounds(456, 620, 10, 10);

        ProfileMiniIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ProfileMiniIcon2);
        ProfileMiniIcon2.setBounds(385, 382, 13, 13);

        ProfileMiniIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ProfileMiniIcon);
        ProfileMiniIcon.setBounds(331, 166, 10, 10);

        ProfileMiniIconLabel1.setBackground(new java.awt.Color(40, 52, 72));
        getContentPane().add(ProfileMiniIconLabel1);
        ProfileMiniIconLabel1.setBounds(326, 161, 20, 20);

        ProfileMiniIconLabel.setBackground(new java.awt.Color(40, 52, 72));
        getContentPane().add(ProfileMiniIconLabel);
        ProfileMiniIconLabel.setBounds(379, 376, 25, 25);

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

        registerButton6.setBackground(new java.awt.Color(237, 241, 242));
        registerButton6.setContentAreaFilled(false);
        registerButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton6MouseExited(evt);
            }
        });
        registerButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(registerButton6);
        registerButton6.setBounds(1037, 578, 184, 28);

        registerButton5.setBackground(new java.awt.Color(237, 241, 242));
        registerButton5.setContentAreaFilled(false);
        registerButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton5MouseExited(evt);
            }
        });
        registerButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(registerButton5);
        registerButton5.setBounds(1037, 611, 184, 28);

        registerButton4.setBackground(new java.awt.Color(237, 241, 242));
        registerButton4.setContentAreaFilled(false);
        registerButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton4MouseExited(evt);
            }
        });
        registerButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(registerButton4);
        registerButton4.setBounds(790, 611, 184, 28);

        registerButton3.setBackground(new java.awt.Color(237, 241, 242));
        registerButton3.setContentAreaFilled(false);
        registerButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton3MouseExited(evt);
            }
        });
        registerButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(registerButton3);
        registerButton3.setBounds(543, 611, 184, 28);

        registerButton2.setBackground(new java.awt.Color(237, 241, 242));
        registerButton2.setContentAreaFilled(false);
        registerButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton2MouseExited(evt);
            }
        });
        registerButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(registerButton2);
        registerButton2.setBounds(296, 611, 184, 28);

        registerButton1.setBackground(new java.awt.Color(237, 241, 242));
        registerButton1.setContentAreaFilled(false);
        registerButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton1MouseExited(evt);
            }
        });
        registerButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(registerButton1);
        registerButton1.setBounds(300, 412, 127, 28);

        registerButtonLabel6.setFont(FonteUtils.carregarRobotoRegular(12f));
        registerButtonLabel6.setForeground(new java.awt.Color(9, 10, 12));
        registerButtonLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerButtonLabel6.setText("Encerrar Sessão");
        getContentPane().add(registerButtonLabel6);
        registerButtonLabel6.setBounds(1037, 578, 184, 28);

        registerButtonLabel5.setFont(FonteUtils.carregarRobotoRegular(12f));
        registerButtonLabel5.setForeground(new java.awt.Color(9, 10, 12));
        registerButtonLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerButtonLabel5.setText("Excluir Conta");
        getContentPane().add(registerButtonLabel5);
        registerButtonLabel5.setBounds(1037, 611, 184, 28);

        registerButtonLabel4.setFont(FonteUtils.carregarRobotoRegular(12f));
        registerButtonLabel4.setForeground(new java.awt.Color(9, 10, 12));
        registerButtonLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerButtonLabel4.setText("Visualizar");
        getContentPane().add(registerButtonLabel4);
        registerButtonLabel4.setBounds(790, 611, 184, 28);

        registerButtonLabel3.setFont(FonteUtils.carregarRobotoRegular(12f));
        registerButtonLabel3.setForeground(new java.awt.Color(9, 10, 12));
        registerButtonLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerButtonLabel3.setText("Configurar");
        getContentPane().add(registerButtonLabel3);
        registerButtonLabel3.setBounds(543, 611, 184, 28);

        registerButtonLabel2.setFont(FonteUtils.carregarRobotoRegular(12f));
        registerButtonLabel2.setForeground(new java.awt.Color(9, 10, 12));
        registerButtonLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerButtonLabel2.setText("Gerenciar Senha");
        getContentPane().add(registerButtonLabel2);
        registerButtonLabel2.setBounds(296, 611, 184, 28);

        registerButtonLabel1.setFont(FonteUtils.carregarRobotoRegular(12f));
        registerButtonLabel1.setForeground(new java.awt.Color(9, 10, 12));
        registerButtonLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerButtonLabel1.setText("Alterar Imagem");
        getContentPane().add(registerButtonLabel1);
        registerButtonLabel1.setBounds(300, 412, 127, 28);

        registerButtonPanel6.setBackground(new java.awt.Color(234, 70, 69));
        registerButtonPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonPanel6MouseEntered(evt);
            }
        });
        getContentPane().add(registerButtonPanel6);
        registerButtonPanel6.setBounds(1037, 578, 184, 28);

        registerButtonPanel5.setBackground(new java.awt.Color(234, 70, 69));
        registerButtonPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonPanel5MouseEntered(evt);
            }
        });
        getContentPane().add(registerButtonPanel5);
        registerButtonPanel5.setBounds(1037, 611, 184, 28);

        registerButtonPanel4.setBackground(new java.awt.Color(205, 168, 12));
        registerButtonPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonPanel4MouseEntered(evt);
            }
        });
        getContentPane().add(registerButtonPanel4);
        registerButtonPanel4.setBounds(790, 611, 184, 28);

        registerButtonPanel3.setBackground(new java.awt.Color(205, 168, 12));
        registerButtonPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonPanel3MouseEntered(evt);
            }
        });
        getContentPane().add(registerButtonPanel3);
        registerButtonPanel3.setBounds(543, 611, 184, 28);

        registerButtonPanel2.setBackground(new java.awt.Color(205, 168, 12));
        registerButtonPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonPanel2MouseEntered(evt);
            }
        });
        getContentPane().add(registerButtonPanel2);
        registerButtonPanel2.setBounds(296, 611, 184, 28);

        registerButtonPanel1.setBackground(new java.awt.Color(205, 168, 12));
        registerButtonPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonPanel1MouseEntered(evt);
            }
        });
        getContentPane().add(registerButtonPanel1);
        registerButtonPanel1.setBounds(300, 412, 127, 28);

        ButtonVerticalDivider3.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(ButtonVerticalDivider3);
        ButtonVerticalDivider3.setBounds(1005, 520, 2, 119);

        ButtonVerticalDivider2.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(ButtonVerticalDivider2);
        ButtonVerticalDivider2.setBounds(758, 520, 2, 119);

        ButtonVerticalDivider.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(ButtonVerticalDivider);
        ButtonVerticalDivider.setBounds(511, 520, 2, 119);

        ProfileVerticalDivider.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(ProfileVerticalDivider);
        ProfileVerticalDivider.setBounds(446, 262, 2, 210);

        ButtonDangerZoneIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ButtonDangerZoneIconLabel);
        ButtonDangerZoneIconLabel.setBounds(1037, 513, 15, 15);

        ButtonAccountIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ButtonAccountIconLabel);
        ButtonAccountIconLabel.setBounds(790, 513, 15, 15);

        ButtonPreferencesIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ButtonPreferencesIconLabel);
        ButtonPreferencesIconLabel.setBounds(543, 513, 15, 15);

        ButtonSecurityIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ButtonSecurityIconLabel);
        ButtonSecurityIconLabel.setBounds(296, 513, 15, 15);

        ButtonDangerZoneTitleLabel.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        ButtonDangerZoneTitleLabel.setForeground(new java.awt.Color(234, 70, 69));
        ButtonDangerZoneTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ButtonDangerZoneTitleLabel.setText("Zona de Perigo");
        ButtonDangerZoneTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonDangerZoneTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonDangerZoneTitleLabelMouseExited(evt);
            }
        });
        getContentPane().add(ButtonDangerZoneTitleLabel);
        ButtonDangerZoneTitleLabel.setBounds(1062, 513, 160, 14);

        ButtonAccountTitleLabel.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        ButtonAccountTitleLabel.setForeground(new java.awt.Color(209, 213, 219));
        ButtonAccountTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ButtonAccountTitleLabel.setText("Conta");
        ButtonAccountTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonAccountTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonAccountTitleLabelMouseExited(evt);
            }
        });
        getContentPane().add(ButtonAccountTitleLabel);
        ButtonAccountTitleLabel.setBounds(815, 513, 160, 14);

        ButtonPreferencesTitleLabel.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        ButtonPreferencesTitleLabel.setForeground(new java.awt.Color(209, 213, 219));
        ButtonPreferencesTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ButtonPreferencesTitleLabel.setText("Preferências");
        ButtonPreferencesTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonPreferencesTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonPreferencesTitleLabelMouseExited(evt);
            }
        });
        getContentPane().add(ButtonPreferencesTitleLabel);
        ButtonPreferencesTitleLabel.setBounds(568, 513, 160, 14);

        ButtonSecurityTitleLabel.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        ButtonSecurityTitleLabel.setForeground(new java.awt.Color(209, 213, 219));
        ButtonSecurityTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ButtonSecurityTitleLabel.setText("Segurança");
        ButtonSecurityTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonSecurityTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonSecurityTitleLabelMouseExited(evt);
            }
        });
        getContentPane().add(ButtonSecurityTitleLabel);
        ButtonSecurityTitleLabel.setBounds(321, 513, 160, 14);

        ButtonDangerZoneDescriptionLabel.setFont(FonteUtils.carregarRobotoRegular(10f));
        ButtonDangerZoneDescriptionLabel.setForeground(new java.awt.Color(209, 213, 219));
        ButtonDangerZoneDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ButtonDangerZoneDescriptionLabel.setText("<html>Altere sua senha e mantenha sua conta segura.</html>");
        ButtonDangerZoneDescriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ButtonDangerZoneDescriptionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonDangerZoneDescriptionLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonDangerZoneDescriptionLabelMouseExited(evt);
            }
        });
        getContentPane().add(ButtonDangerZoneDescriptionLabel);
        ButtonDangerZoneDescriptionLabel.setBounds(1037, 542, 184, 40);

        ButtonAccountDescriptionLabel.setFont(FonteUtils.carregarRobotoRegular(10f));
        ButtonAccountDescriptionLabel.setForeground(new java.awt.Color(209, 213, 219));
        ButtonAccountDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ButtonAccountDescriptionLabel.setText("<html>Veja informações da sua conta e atividades recentes.</html>");
        ButtonAccountDescriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ButtonAccountDescriptionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonAccountDescriptionLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonAccountDescriptionLabelMouseExited(evt);
            }
        });
        getContentPane().add(ButtonAccountDescriptionLabel);
        ButtonAccountDescriptionLabel.setBounds(790, 542, 184, 40);

        ButtonPreferencesDescriptionLabel.setFont(FonteUtils.carregarRobotoRegular(10f));
        ButtonPreferencesDescriptionLabel.setForeground(new java.awt.Color(209, 213, 219));
        ButtonPreferencesDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ButtonPreferencesDescriptionLabel.setText("<html>Gerencie idioma, tema e preferências do sistema.</html>");
        ButtonPreferencesDescriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ButtonPreferencesDescriptionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonPreferencesDescriptionLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonPreferencesDescriptionLabelMouseExited(evt);
            }
        });
        getContentPane().add(ButtonPreferencesDescriptionLabel);
        ButtonPreferencesDescriptionLabel.setBounds(543, 542, 184, 40);

        ButtonSecurityDescriptionLabel.setFont(FonteUtils.carregarRobotoRegular(10f));
        ButtonSecurityDescriptionLabel.setForeground(new java.awt.Color(209, 213, 219));
        ButtonSecurityDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ButtonSecurityDescriptionLabel.setText("<html>Altere sua senha e mantenha sua conta segura.</html>");
        ButtonSecurityDescriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ButtonSecurityDescriptionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonSecurityDescriptionLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonSecurityDescriptionLabelMouseExited(evt);
            }
        });
        getContentPane().add(ButtonSecurityDescriptionLabel);
        ButtonSecurityDescriptionLabel.setBounds(296, 542, 184, 40);

        UserPhoneField.setBackground(new java.awt.Color(25, 25, 25));
        UserPhoneField.setFont(FonteUtils.carregarRobotoMedium(12f));
        UserPhoneField.setForeground(new java.awt.Color(209, 213, 219));
        UserPhoneField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        UserPhoneField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UserPhoneFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                UserPhoneFieldFocusLost(evt);
            }
        });
        UserPhoneField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UserPhoneFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UserPhoneFieldMousePressed(evt);
            }
        });
        UserPhoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserPhoneFieldActionPerformed(evt);
            }
        });
        UserPhoneField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UserPhoneFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UserPhoneFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UserPhoneFieldKeyTyped(evt);
            }
        });
        getContentPane().add(UserPhoneField);
        UserPhoneField.setBounds(483, 427, 738, 35);

        UserEmailField.setBackground(new java.awt.Color(25, 25, 25));
        UserEmailField.setFont(FonteUtils.carregarRobotoMedium(12f));
        UserEmailField.setForeground(new java.awt.Color(209, 213, 219));
        UserEmailField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        UserEmailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UserEmailFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                UserEmailFieldFocusLost(evt);
            }
        });
        UserEmailField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UserEmailFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UserEmailFieldMousePressed(evt);
            }
        });
        UserEmailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserEmailFieldActionPerformed(evt);
            }
        });
        UserEmailField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UserEmailFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UserEmailFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UserEmailFieldKeyTyped(evt);
            }
        });
        getContentPane().add(UserEmailField);
        UserEmailField.setBounds(483, 358, 738, 35);

        UserNameField.setBackground(new java.awt.Color(25, 25, 25));
        UserNameField.setFont(FonteUtils.carregarRobotoMedium(12f));
        UserNameField.setForeground(new java.awt.Color(209, 213, 219));
        UserNameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        UserNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UserNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                UserNameFieldFocusLost(evt);
            }
        });
        UserNameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UserNameFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UserNameFieldMousePressed(evt);
            }
        });
        UserNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserNameFieldActionPerformed(evt);
            }
        });
        UserNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UserNameFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UserNameFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UserNameFieldKeyTyped(evt);
            }
        });
        getContentPane().add(UserNameField);
        UserNameField.setBounds(483, 289, 738, 35);

        ProfileDescriptionLabel.setFont(FonteUtils.carregarRobotoRegular(8f));
        ProfileDescriptionLabel.setForeground(new java.awt.Color(209, 213, 219));
        ProfileDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProfileDescriptionLabel.setText("PNG, JPG ou GIF. Máx. 2MB.");
        ProfileDescriptionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProfileDescriptionLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProfileDescriptionLabelMouseExited(evt);
            }
        });
        getContentPane().add(ProfileDescriptionLabel);
        ProfileDescriptionLabel.setBounds(296, 445, 127, 14);

        ProfileIconBigLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ProfileIconBigLabel);
        ProfileIconBigLabel.setBounds(309, 301, 100, 100);

        UserPhoneLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        UserPhoneLabel.setForeground(new java.awt.Color(209, 213, 219));
        UserPhoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        UserPhoneLabel.setText("Telefone");
        UserPhoneLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UserPhoneLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UserPhoneLabelMouseExited(evt);
            }
        });
        getContentPane().add(UserPhoneLabel);
        UserPhoneLabel.setBounds(483, 409, 330, 14);

        UserEmailLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        UserEmailLabel.setForeground(new java.awt.Color(209, 213, 219));
        UserEmailLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        UserEmailLabel.setText("Email");
        UserEmailLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UserEmailLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UserEmailLabelMouseExited(evt);
            }
        });
        getContentPane().add(UserEmailLabel);
        UserEmailLabel.setBounds(483, 340, 330, 14);

        UserNameLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        UserNameLabel.setForeground(new java.awt.Color(209, 213, 219));
        UserNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        UserNameLabel.setText("Usuário");
        UserNameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UserNameLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UserNameLabelMouseExited(evt);
            }
        });
        getContentPane().add(UserNameLabel);
        UserNameLabel.setBounds(483, 271, 330, 14);

        ProfileIconNameLabel.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        ProfileIconNameLabel.setForeground(new java.awt.Color(209, 213, 219));
        ProfileIconNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ProfileIconNameLabel.setText("Foto de Perfil");
        ProfileIconNameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProfileIconNameLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProfileIconNameLabelMouseExited(evt);
            }
        });
        getContentPane().add(ProfileIconNameLabel);
        ProfileIconNameLabel.setBounds(300, 266, 330, 14);

        registerButton.setBackground(new java.awt.Color(237, 241, 242));
        registerButton.setContentAreaFilled(false);
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButtonMouseExited(evt);
            }
        });
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        getContentPane().add(registerButton);
        registerButton.setBounds(1060, 672, 191, 30);

        registerButtonLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        registerButtonLabel.setForeground(new java.awt.Color(9, 10, 12));
        registerButtonLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerButtonLabel.setText("Salvar alterações");
        getContentPane().add(registerButtonLabel);
        registerButtonLabel.setBounds(1060, 672, 191, 30);

        registerButtonPanel.setBackground(new java.awt.Color(205, 168, 12));
        registerButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonPanelMouseEntered(evt);
            }
        });
        getContentPane().add(registerButtonPanel);
        registerButtonPanel.setBounds(1060, 672, 191, 30);

        ButtonsInfoPanel.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(ButtonsInfoPanel);
        ButtonsInfoPanel.setBounds(276, 494, 976, 160);

        EditInfoPanel.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(EditInfoPanel);
        EditInfoPanel.setBounds(276, 250, 976, 234);

        ProfileNameLabel.setFont(FonteUtils.carregarRoboto(12f));
        ProfileNameLabel.setForeground(new java.awt.Color(209, 213, 219));
        ProfileNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ProfileNameLabel.setText("Evanilson Macedo Rocha");
        ProfileNameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProfileNameLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProfileNameLabelMouseExited(evt);
            }
        });
        getContentPane().add(ProfileNameLabel);
        ProfileNameLabel.setBounds(365, 117, 330, 14);

        PermissionLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        PermissionLabel.setForeground(new java.awt.Color(205, 168, 12));
        PermissionLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PermissionLabel.setText("Administrador");
        PermissionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PermissionLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PermissionLabelMouseExited(evt);
            }
        });
        getContentPane().add(PermissionLabel);
        PermissionLabel.setBounds(365, 134, 330, 14);

        LastAcessLabel.setFont(FonteUtils.carregarRobotoRegular(11f));
        LastAcessLabel.setForeground(new java.awt.Color(209, 213, 219));
        LastAcessLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LastAcessLabel.setText("Último acesso: Hoje às 14:35");
        LastAcessLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LastAcessLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LastAcessLabelMouseExited(evt);
            }
        });
        getContentPane().add(LastAcessLabel);
        LastAcessLabel.setBounds(365, 156, 330, 14);

        ProfileIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ProfileIconLabel);
        ProfileIconLabel.setBounds(276, 107, 75, 75);

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
        AccountButton.setBounds(606, 203, 100, 30);

        AccountSeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(AccountSeparator);
        AccountSeparator.setBounds(606, 232, 100, 1);

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
        AccountLabel.setBounds(606, 207, 100, 20);

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
        PreferencesButton.setBounds(496, 203, 100, 30);

        PreferencesSeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(PreferencesSeparator);
        PreferencesSeparator.setBounds(496, 232, 100, 1);

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
        PreferencesLabel.setBounds(496, 207, 100, 20);

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
        SecurityButton.setBounds(386, 203, 100, 30);

        SecuritySeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(SecuritySeparator);
        SecuritySeparator.setBounds(386, 232, 100, 1);

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
        SecurityLabel.setBounds(386, 207, 100, 20);

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
        ProfileButton.setBounds(276, 203, 100, 30);

        ProfileSeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(ProfileSeparator);
        ProfileSeparator.setBounds(276, 232, 100, 1);

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
        ProfileLabel.setBounds(276, 207, 100, 20);

        Separator.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(Separator);
        Separator.setBounds(240, 232, 1040, 1);

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
    }//GEN-LAST:event_ProfileButtonMouseEntered

    private void ProfileButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        irParaHome();
    }//GEN-LAST:event_arrowBackButtonActionPerformed

    private void ProfileLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProfileLabel.setForeground(new java.awt.Color(205, 168, 12));
    }//GEN-LAST:event_ProfileLabelMouseEntered

    private void ProfileLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProfileLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_ProfileLabelMouseExited

    private void SecurityLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SecurityLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SecurityLabel.setForeground(new java.awt.Color(205, 168, 12));
    }//GEN-LAST:event_SecurityLabelMouseEntered

    private void SecurityLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SecurityLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SecurityLabel.setForeground(new java.awt.Color(209, 213, 219));
    }//GEN-LAST:event_SecurityLabelMouseExited

    private void PreferencesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreferencesLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PreferencesLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_PreferencesLabelMouseEntered

    private void PreferencesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreferencesLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PreferencesLabel.setForeground(COR_ABA_INATIVA);
    }//GEN-LAST:event_PreferencesLabelMouseExited

    private void PreferencesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreferencesButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PreferencesLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_PreferencesButtonMouseEntered

    private void PreferencesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreferencesButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PreferencesLabel.setForeground(COR_ABA_INATIVA);
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

    private void LastAcessLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LastAcessLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_LastAcessLabelMouseEntered

    private void LastAcessLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LastAcessLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_LastAcessLabelMouseExited

    private void PermissionLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PermissionLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PermissionLabelMouseEntered

    private void PermissionLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PermissionLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PermissionLabelMouseExited

    private void ProfileNameLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileNameLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileNameLabelMouseEntered

    private void ProfileNameLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileNameLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileNameLabelMouseExited

    private void registerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseEntered
        registerButtonPanel.setBackground(COR_REGISTRAR_HOVER);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_registerButtonMouseEntered

    private void registerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseExited
        registerButtonPanel.setBackground(COR_REGISTRAR_PADRAO);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_registerButtonMouseExited

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        handleProfileSave();
    }//GEN-LAST:event_registerButtonActionPerformed

    private void registerButtonPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonPanelMouseEntered

    }//GEN-LAST:event_registerButtonPanelMouseEntered

    private void ProfileIconNameLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileIconNameLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileIconNameLabelMouseEntered

    private void ProfileIconNameLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileIconNameLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileIconNameLabelMouseExited

    private void UserNameLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserNameLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_UserNameLabelMouseEntered

    private void UserNameLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserNameLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_UserNameLabelMouseExited

    private void UserEmailLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserEmailLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_UserEmailLabelMouseEntered

    private void UserEmailLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserEmailLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_UserEmailLabelMouseExited

    private void UserPhoneLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserPhoneLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_UserPhoneLabelMouseEntered

    private void UserPhoneLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserPhoneLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_UserPhoneLabelMouseExited

    private void ProfileDescriptionLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileDescriptionLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileDescriptionLabelMouseEntered

    private void ProfileDescriptionLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileDescriptionLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileDescriptionLabelMouseExited

    private void UserNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UserNameFieldFocusGained

    }//GEN-LAST:event_UserNameFieldFocusGained

    private void UserNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UserNameFieldFocusLost

    }//GEN-LAST:event_UserNameFieldFocusLost

    private void UserNameFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserNameFieldMouseExited

    }//GEN-LAST:event_UserNameFieldMouseExited

    private void UserNameFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserNameFieldMousePressed

    }//GEN-LAST:event_UserNameFieldMousePressed

    private void UserNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserNameFieldActionPerformed

    }//GEN-LAST:event_UserNameFieldActionPerformed

    private void UserNameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserNameFieldKeyPressed

    }//GEN-LAST:event_UserNameFieldKeyPressed

    private void UserNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserNameFieldKeyReleased

    }//GEN-LAST:event_UserNameFieldKeyReleased

    private void UserNameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserNameFieldKeyTyped

    }//GEN-LAST:event_UserNameFieldKeyTyped

    private void UserEmailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UserEmailFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_UserEmailFieldFocusGained

    private void UserEmailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UserEmailFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_UserEmailFieldFocusLost

    private void UserEmailFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserEmailFieldMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_UserEmailFieldMouseExited

    private void UserEmailFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserEmailFieldMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserEmailFieldMousePressed

    private void UserEmailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserEmailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserEmailFieldActionPerformed

    private void UserEmailFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserEmailFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserEmailFieldKeyPressed

    private void UserEmailFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserEmailFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_UserEmailFieldKeyReleased

    private void UserEmailFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserEmailFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_UserEmailFieldKeyTyped

    private void UserPhoneFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UserPhoneFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_UserPhoneFieldFocusGained

    private void UserPhoneFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UserPhoneFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_UserPhoneFieldFocusLost

    private void UserPhoneFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserPhoneFieldMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_UserPhoneFieldMouseExited

    private void UserPhoneFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserPhoneFieldMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserPhoneFieldMousePressed

    private void UserPhoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserPhoneFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserPhoneFieldActionPerformed

    private void UserPhoneFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserPhoneFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserPhoneFieldKeyPressed

    private void UserPhoneFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserPhoneFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_UserPhoneFieldKeyReleased

    private void UserPhoneFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserPhoneFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_UserPhoneFieldKeyTyped

    private void ButtonSecurityDescriptionLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonSecurityDescriptionLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonSecurityDescriptionLabelMouseEntered

    private void ButtonSecurityDescriptionLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonSecurityDescriptionLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonSecurityDescriptionLabelMouseExited

    private void ButtonPreferencesDescriptionLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonPreferencesDescriptionLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonPreferencesDescriptionLabelMouseEntered

    private void ButtonPreferencesDescriptionLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonPreferencesDescriptionLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonPreferencesDescriptionLabelMouseExited

    private void ButtonAccountDescriptionLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonAccountDescriptionLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonAccountDescriptionLabelMouseEntered

    private void ButtonAccountDescriptionLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonAccountDescriptionLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonAccountDescriptionLabelMouseExited

    private void ButtonDangerZoneDescriptionLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonDangerZoneDescriptionLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDangerZoneDescriptionLabelMouseEntered

    private void ButtonDangerZoneDescriptionLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonDangerZoneDescriptionLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDangerZoneDescriptionLabelMouseExited

    private void ButtonSecurityTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonSecurityTitleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonSecurityTitleLabelMouseEntered

    private void ButtonSecurityTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonSecurityTitleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonSecurityTitleLabelMouseExited

    private void ButtonPreferencesTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonPreferencesTitleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonPreferencesTitleLabelMouseEntered

    private void ButtonPreferencesTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonPreferencesTitleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonPreferencesTitleLabelMouseExited

    private void ButtonAccountTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonAccountTitleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonAccountTitleLabelMouseEntered

    private void ButtonAccountTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonAccountTitleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonAccountTitleLabelMouseExited

    private void ButtonDangerZoneTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonDangerZoneTitleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDangerZoneTitleLabelMouseEntered

    private void ButtonDangerZoneTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonDangerZoneTitleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDangerZoneTitleLabelMouseExited

    private void registerButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton1MouseEntered
        hoverBotaoContorno(registerButtonPanel1, true);
    }//GEN-LAST:event_registerButton1MouseEntered

    private void registerButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton1MouseExited
        hoverBotaoContorno(registerButtonPanel1, false);
    }//GEN-LAST:event_registerButton1MouseExited

    private void registerButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButton1ActionPerformed
        escolherImagemPerfil();
    }//GEN-LAST:event_registerButton1ActionPerformed

    private void registerButtonPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonPanel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonPanel1MouseEntered

    private void registerButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton2MouseEntered
        hoverBotaoContorno(registerButtonPanel2, true);
    }//GEN-LAST:event_registerButton2MouseEntered

    private void registerButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton2MouseExited
        hoverBotaoContorno(registerButtonPanel2, false);
    }//GEN-LAST:event_registerButton2MouseExited

    private void registerButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButton2ActionPerformed
        irParaSeguranca();
    }//GEN-LAST:event_registerButton2ActionPerformed

    private void registerButtonPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonPanel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonPanel2MouseEntered

    private void registerButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton3MouseEntered
        hoverBotaoContorno(registerButtonPanel3, true);
    }//GEN-LAST:event_registerButton3MouseEntered

    private void registerButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton3MouseExited
        hoverBotaoContorno(registerButtonPanel3, false);
    }//GEN-LAST:event_registerButton3MouseExited

    private void registerButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButton3ActionPerformed
        irParaPreferencias();
    }//GEN-LAST:event_registerButton3ActionPerformed

    private void registerButtonPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonPanel3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonPanel3MouseEntered

    private void registerButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton4MouseEntered
        hoverBotaoContorno(registerButtonPanel4, true);
    }//GEN-LAST:event_registerButton4MouseEntered

    private void registerButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton4MouseExited
        hoverBotaoContorno(registerButtonPanel4, false);
    }//GEN-LAST:event_registerButton4MouseExited

    private void registerButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButton4ActionPerformed
        irParaConta();
    }//GEN-LAST:event_registerButton4ActionPerformed

    private void registerButtonPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonPanel4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonPanel4MouseEntered

    private void registerButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton5MouseEntered
        hoverBotaoContorno(registerButtonPanel5, true);
    }//GEN-LAST:event_registerButton5MouseEntered

    private void registerButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton5MouseExited
        hoverBotaoContorno(registerButtonPanel5, false);
    }//GEN-LAST:event_registerButton5MouseExited

    private void registerButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButton5ActionPerformed
        excluirConta();
    }//GEN-LAST:event_registerButton5ActionPerformed

    private void registerButtonPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonPanel5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonPanel5MouseEntered

    private void registerButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton6MouseEntered
        hoverBotaoContorno(registerButtonPanel6, true);
    }//GEN-LAST:event_registerButton6MouseEntered

    private void registerButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton6MouseExited
        hoverBotaoContorno(registerButtonPanel6, false);
    }//GEN-LAST:event_registerButton6MouseExited

    private void registerButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButton6ActionPerformed
        encerrarSessao();
    }//GEN-LAST:event_registerButton6ActionPerformed

    private void registerButtonPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonPanel6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonPanel6MouseEntered

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
            java.util.logging.Logger.getLogger(ConfigProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
        private boolean showBorder = false;
        private Color borderColor = new Color(205, 168, 12);

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        public void setRadius(int radius) {
            this.radius = radius;
            repaint();
        }

        public void setShowBorder(boolean mostrar) {
            this.showBorder = mostrar;
            repaint();
        }

        public void setBorderColor(Color cor) {
            this.borderColor = cor;
            repaint();
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            if (showBorder) {
                g2.setStroke(new java.awt.BasicStroke(1f));
                g2.setColor(borderColor);
                g2.draw(new RoundRectangle2D.Float(0.5f, 0.5f, getWidth() - 1f, getHeight() - 1f, radius, radius));
            }
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
    private javax.swing.JLabel ButtonAccountDescriptionLabel;
    private javax.swing.JLabel ButtonAccountIconLabel;
    private javax.swing.JLabel ButtonAccountTitleLabel;
    private javax.swing.JLabel ButtonArrowIcon;
    private javax.swing.JLabel ButtonArrowIcon1;
    private javax.swing.JLabel ButtonArrowIcon2;
    private javax.swing.JLabel ButtonArrowIcon3;
    private javax.swing.JLabel ButtonArrowIcon4;
    private javax.swing.JLabel ButtonDangerZoneDescriptionLabel;
    private javax.swing.JLabel ButtonDangerZoneIconLabel;
    private javax.swing.JLabel ButtonDangerZoneTitleLabel;
    private javax.swing.JLabel ButtonPreferencesDescriptionLabel;
    private javax.swing.JLabel ButtonPreferencesIconLabel;
    private javax.swing.JLabel ButtonPreferencesTitleLabel;
    private javax.swing.JLabel ButtonSecurityDescriptionLabel;
    private javax.swing.JLabel ButtonSecurityIconLabel;
    private javax.swing.JLabel ButtonSecurityTitleLabel;
    private javax.swing.JPanel ButtonVerticalDivider;
    private javax.swing.JPanel ButtonVerticalDivider2;
    private javax.swing.JPanel ButtonVerticalDivider3;
    private javax.swing.JPanel ButtonsInfoPanel;
    private javax.swing.JPanel EditInfoPanel;
    private javax.swing.JPanel Ellipse1;
    private javax.swing.JPanel Ellipse2;
    private javax.swing.JPanel Ellipse3;
    private javax.swing.JLabel LastAcessLabel;
    private javax.swing.JLabel PermissionLabel;
    private javax.swing.JButton PreferencesButton;
    private javax.swing.JLabel PreferencesLabel;
    private javax.swing.JPanel PreferencesSeparator;
    private javax.swing.JButton ProfileButton;
    private javax.swing.JLabel ProfileDescriptionLabel;
    private javax.swing.JLabel ProfileIconBigLabel;
    private javax.swing.JLabel ProfileIconLabel;
    private javax.swing.JLabel ProfileIconNameLabel;
    private javax.swing.JLabel ProfileLabel;
    private javax.swing.JLabel ProfileMiniIcon;
    private javax.swing.JLabel ProfileMiniIcon2;
    private javax.swing.JPanel ProfileMiniIconLabel;
    private javax.swing.JPanel ProfileMiniIconLabel1;
    private javax.swing.JLabel ProfileNameLabel;
    private javax.swing.JPanel ProfileSeparator;
    private javax.swing.JPanel ProfileVerticalDivider;
    private javax.swing.JButton SecurityButton;
    private javax.swing.JLabel SecurityLabel;
    private javax.swing.JPanel SecuritySeparator;
    private javax.swing.JPanel Separator;
    private javax.swing.JTextField UserEmailField;
    private javax.swing.JLabel UserEmailLabel;
    private javax.swing.JTextField UserNameField;
    private javax.swing.JLabel UserNameLabel;
    private javax.swing.JTextField UserPhoneField;
    private javax.swing.JLabel UserPhoneLabel;
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
    private javax.swing.JButton registerButton;
    private javax.swing.JButton registerButton1;
    private javax.swing.JButton registerButton2;
    private javax.swing.JButton registerButton3;
    private javax.swing.JButton registerButton4;
    private javax.swing.JButton registerButton5;
    private javax.swing.JButton registerButton6;
    private javax.swing.JLabel registerButtonLabel;
    private javax.swing.JLabel registerButtonLabel1;
    private javax.swing.JLabel registerButtonLabel2;
    private javax.swing.JLabel registerButtonLabel3;
    private javax.swing.JLabel registerButtonLabel4;
    private javax.swing.JLabel registerButtonLabel5;
    private javax.swing.JLabel registerButtonLabel6;
    private javax.swing.JPanel registerButtonPanel;
    private javax.swing.JPanel registerButtonPanel1;
    private javax.swing.JPanel registerButtonPanel2;
    private javax.swing.JPanel registerButtonPanel3;
    private javax.swing.JPanel registerButtonPanel4;
    private javax.swing.JPanel registerButtonPanel5;
    private javax.swing.JPanel registerButtonPanel6;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
