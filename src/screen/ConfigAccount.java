package screen;

import Data.AuditUtil;
import Data.CTCONTAB;
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

public class ConfigAccount extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private final Color COR_PADRAO = new Color(28, 37, 54);
    private final Color COR_HOVER_GERAL = new Color(50, 63, 89);
    private final Color COR_HOVER_FECHAR = new Color(196, 43, 28);
    private javax.swing.Timer timerVoltarHome;
    private final Color COR_ABA_ATIVA = new Color(205, 168, 12);
    private final Color COR_ABA_INATIVA = new Color(209, 213, 219);
    private final Color COR_BOTAO_PERIGO = new Color(220, 68, 80);
    private final Color COR_BOTAO_FUNDO = new Color(28, 37, 54);
    private final Color COR_BOTAO_FUNDO_HOVER = new Color(37, 49, 71);

    public ConfigAccount(Usuario usuario) {
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
        configurarInformacoes();
        arredondarPaineis();
        configurarBotoes();
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
            BufferedImage UserID = ImageIO.read(getClass().getResource("/images/User ID Icon.png"));
            BufferedImage UserPermission = ImageIO.read(getClass().getResource("/images/User Permission Icon.png"));
            BufferedImage UserRegister = ImageIO.read(getClass().getResource("/images/User Register Icon.png"));
            BufferedImage UserLastAcess = ImageIO.read(getClass().getResource("/images/User Last Acess Icon.png"));
            BufferedImage UserStatus = ImageIO.read(getClass().getResource("/images/User Status Icon.png"));
            BufferedImage UserEmail = ImageIO.read(getClass().getResource("/images/User Email Register Icon.png"));
            BufferedImage UserTrash = ImageIO.read(getClass().getResource("/images/User Trash Icon.png"));
            BufferedImage UserExit = ImageIO.read(getClass().getResource("/images/User Exit Icon.png"));
            BufferedImage UserTrash2 = ImageIO.read(getClass().getResource("/images/User Trash Icon.png"));
            BufferedImage UserExit2 = ImageIO.read(getClass().getResource("/images/User Exit Icon.png"));

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
            TipIconLabel1.setIcon(gerarIconePerfeito(UserID, 20, 20));
            TipIconLabel5.setIcon(gerarIconePerfeito(UserPermission, 20, 20));
            TipIconLabel6.setIcon(gerarIconePerfeito(UserRegister, 20, 20));
            TipIconLabel7.setIcon(gerarIconePerfeito(UserLastAcess, 20, 20));
            TipIconLabel8.setIcon(gerarIconePerfeito(UserEmail, 20, 20));
            TipIconLabel9.setIcon(gerarIconePerfeito(UserStatus, 20, 20));
            TipIconLabel10.setIcon(gerarIconePerfeito(UserTrash, 25, 25));
            TipIconLabel11.setIcon(gerarIconePerfeito(UserExit, 25, 25));
            ButtonArrowIcon2.setIcon(gerarIconePerfeito(UserTrash2, 13, 13));
            ButtonArrowIcon3.setIcon(gerarIconePerfeito(UserExit2, 13, 13));

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
        ProfileIconNameLabel.setText(I18nManager.getString("auth.account.title"));
        IDPersonalInfoLabel.setText(I18nManager.getString("auth.account.field.id"));
        IDPersonalInfoLabel1.setText(I18nManager.getString("auth.account.field.role"));
        IDPersonalInfoLabel2.setText(I18nManager.getString("auth.account.field.created"));
        IDPersonalInfoLabel3.setText(I18nManager.getString("auth.account.field.last_access"));
        IDPersonalInfoLabel4.setText(I18nManager.getString("auth.account.field.email"));
        IDPersonalInfoLabel5.setText(I18nManager.getString("auth.account.field.status"));
        ProfileIconNameLabel1.setText(I18nManager.getString("auth.account.danger.title"));
        TitleTipLabel.setText(I18nManager.getString("auth.account.danger.delete.title"));
        TipLabel1.setText(I18nManager.getString("auth.account.danger.delete.description"));
        registerButtonLabel4.setText(I18nManager.getString("auth.account.danger.delete.button"));
        TitleTipLabel1.setText(I18nManager.getString("auth.account.danger.logout.title"));
        TipLabel5.setText(I18nManager.getString("auth.account.danger.logout.description"));
        registerButtonLabel5.setText(I18nManager.getString("auth.account.danger.logout.button"));
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
        AccountLabel.setForeground(COR_ABA_ATIVA);
        AccountSeparator.setVisible(true);

        ProfileLabel.setForeground(COR_ABA_INATIVA);
        ProfileSeparator.setVisible(false);
        SecurityLabel.setForeground(COR_ABA_INATIVA);
        SecuritySeparator.setVisible(false);
        PreferencesLabel.setForeground(COR_ABA_INATIVA);
        PreferencesSeparator.setVisible(false);

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
        PreferencesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                irParaHome();
            }
        });
    }

    private String vazioPara(String valor) {
        return (valor == null || valor.trim().isEmpty()) ? "-" : valor;
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
        try {
            java.time.LocalDateTime momento = java.time.LocalDateTime.parse(acesso.trim().replace(' ', 'T'));
            return String.format(I18nManager.getString("auth.account.last_access.at"),
                    momento.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    momento.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));
        } catch (Exception e) {
            return I18nManager.getString("auth.account.last_access.first");
        }
    }

    private void configurarInformacoes() {
        String[] dados = null;
        try {
            dados = CTCONTAB.buscarInformacoesConta(usuarioLogado.getUsuario());
        } catch (Exception e) {
            System.err.println("Erro ao buscar informações da conta: " + e.getMessage());
        }

        String id = String.valueOf(usuarioLogado.getId());
        String cargo = null;
        String email = usuarioLogado.getEmail();
        String permissao = usuarioLogado.getPermissao();
        String cadastro = null;

        if (dados != null) {
            id = dados[0];
            cargo = dados[1];
            email = dados[2];
            permissao = dados[3];
            cadastro = dados[4];
        }

        IDPersonalInfoLabel6.setText(vazioPara(id));
        IDPersonalInfoLabel7.setText((cargo == null || cargo.trim().isEmpty()) ? textoPermissao(permissao) : cargo);
        IDPersonalInfoLabel8.setText(vazioPara(cadastro));
        IDPersonalInfoLabel9.setText(textoUltimoAcesso(usuarioLogado.getUltimoAcesso()));
        IDPersonalInfoLabel10.setText(vazioPara(email));
        IDPersonalInfoLabel11.setText(I18nManager.getString("auth.account.status.active"));

        javax.swing.JLabel[] valores = {IDPersonalInfoLabel6, IDPersonalInfoLabel7, IDPersonalInfoLabel8,
            IDPersonalInfoLabel9, IDPersonalInfoLabel10, IDPersonalInfoLabel11};
        for (javax.swing.JLabel valor : valores) {
            valor.setToolTipText(valor.getText());
        }
    }

    private javax.swing.JPanel arredondarPainel(javax.swing.JPanel original, int raio, Color corBorda) {
        java.awt.Container pai = original.getParent();
        int posicao = pai.getComponentZOrder(original);

        RoundedPanel arredondado = new RoundedPanel(raio);
        arredondado.setBackground(original.getBackground());
        arredondado.setBounds(original.getBounds());
        if (corBorda != null) {
            arredondado.setShowBorder(true);
            arredondado.setBorderColor(corBorda);
        }

        pai.remove(original);
        pai.add(arredondado, posicao);
        pai.revalidate();
        pai.repaint();
        return arredondado;
    }

    private void arredondarPaineis() {
        EditInfoPanel = arredondarPainel(EditInfoPanel, 15, null);
        EditInfoPanel1 = arredondarPainel(EditInfoPanel1, 15, COR_BOTAO_PERIGO);
        EditInfoPanel2 = arredondarPainel(EditInfoPanel2, 15, COR_BOTAO_PERIGO);
        EditInfoPanel3 = arredondarPainel(EditInfoPanel3, 15, COR_BOTAO_PERIGO);
    }

    private void configurarBotoes() {
        registerButton4.setFocusPainted(false);
        registerButton4.setBorderPainted(false);
        registerButton5.setFocusPainted(false);
        registerButton5.setBorderPainted(false);

        estilizarBotaoContorno(registerButtonPanel4, registerButtonLabel4, COR_BOTAO_PERIGO);
        estilizarBotaoContorno(registerButtonPanel5, registerButtonLabel5, COR_BOTAO_PERIGO);
    }

    private void estilizarBotaoContorno(javax.swing.JPanel painel, javax.swing.JLabel rotulo, Color cor) {
        RoundedPanel arredondado = (RoundedPanel) painel;
        arredondado.setRadius(8);
        arredondado.setShowBorder(true);
        arredondado.setBorderColor(cor);
        painel.setBackground(COR_BOTAO_FUNDO);

        rotulo.setForeground(cor);
        rotulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rotulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 0));
    }

    private void hoverBotaoContorno(javax.swing.JPanel painel, boolean entrou) {
        painel.setBackground(entrou ? COR_BOTAO_FUNDO_HOVER : COR_BOTAO_FUNDO);
        setCursor(new java.awt.Cursor(entrou ? java.awt.Cursor.HAND_CURSOR : java.awt.Cursor.DEFAULT_CURSOR));
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        horizontalDivider5 = new javax.swing.JPanel();
        horizontalDivider4 = new javax.swing.JPanel();
        horizontalDivider3 = new javax.swing.JPanel();
        horizontalDivider2 = new javax.swing.JPanel();
        horizontalDivider1 = new javax.swing.JPanel();
        ButtonArrowIcon3 = new javax.swing.JLabel();
        ButtonArrowIcon2 = new javax.swing.JLabel();
        registerButton5 = new javax.swing.JButton();
        registerButtonLabel5 = new javax.swing.JLabel();
        registerButtonPanel5 = new RoundedPanel(40);
        registerButton4 = new javax.swing.JButton();
        registerButtonLabel4 = new javax.swing.JLabel();
        registerButtonPanel4 = new RoundedPanel(40);
        TipIconLabel11 = new javax.swing.JLabel();
        TipIconLabel10 = new javax.swing.JLabel();
        TipIconLabel9 = new javax.swing.JLabel();
        TipIconLabel8 = new javax.swing.JLabel();
        TipIconLabel7 = new javax.swing.JLabel();
        TipIconLabel6 = new javax.swing.JLabel();
        TipIconLabel5 = new javax.swing.JLabel();
        TipIconLabel1 = new javax.swing.JLabel();
        TitleTipLabel1 = new javax.swing.JLabel();
        TipLabel5 = new javax.swing.JLabel();
        IDPersonalInfoLabel10 = new javax.swing.JLabel();
        IDPersonalInfoLabel8 = new javax.swing.JLabel();
        IDPersonalInfoLabel11 = new javax.swing.JLabel();
        IDPersonalInfoLabel9 = new javax.swing.JLabel();
        IDPersonalInfoLabel6 = new javax.swing.JLabel();
        IDPersonalInfoLabel7 = new javax.swing.JLabel();
        IDPersonalInfoLabel5 = new javax.swing.JLabel();
        IDPersonalInfoLabel4 = new javax.swing.JLabel();
        IDPersonalInfoLabel3 = new javax.swing.JLabel();
        IDPersonalInfoLabel2 = new javax.swing.JLabel();
        IDPersonalInfoLabel1 = new javax.swing.JLabel();
        IDPersonalInfoLabel = new javax.swing.JLabel();
        TipLabel1 = new javax.swing.JLabel();
        arrowBackButton = new javax.swing.JButton();
        arrowBackgroundButton = new RoundedPanel(30);
        TitleTipLabel = new javax.swing.JLabel();
        ProfileIconNameLabel1 = new javax.swing.JLabel();
        ProfileIconNameLabel = new javax.swing.JLabel();
        EditInfoPanel3 = new javax.swing.JPanel();
        EditInfoPanel2 = new javax.swing.JPanel();
        EditInfoPanel1 = new javax.swing.JPanel();
        EditInfoPanel = new javax.swing.JPanel();
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

        horizontalDivider5.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(horizontalDivider5);
        horizontalDivider5.setBounds(293, 485, 435, 2);

        horizontalDivider4.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(horizontalDivider4);
        horizontalDivider4.setBounds(293, 431, 435, 2);

        horizontalDivider3.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(horizontalDivider3);
        horizontalDivider3.setBounds(293, 377, 435, 2);

        horizontalDivider2.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(horizontalDivider2);
        horizontalDivider2.setBounds(293, 323, 435, 2);

        horizontalDivider1.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(horizontalDivider1);
        horizontalDivider1.setBounds(293, 269, 435, 2);

        ButtonArrowIcon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ButtonArrowIcon3);
        ButtonArrowIcon3.setBounds(1191, 450, 15, 15);

        ButtonArrowIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(ButtonArrowIcon2);
        ButtonArrowIcon2.setBounds(1191, 299, 15, 15);

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
        registerButton5.setBounds(1051, 443, 160, 28);

        registerButtonLabel5.setFont(FonteUtils.carregarRobotoRegular(11f));
        registerButtonLabel5.setForeground(new java.awt.Color(9, 10, 12));
        registerButtonLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerButtonLabel5.setText("Visualizar");
        getContentPane().add(registerButtonLabel5);
        registerButtonLabel5.setBounds(1051, 443, 160, 28);

        registerButtonPanel5.setBackground(new java.awt.Color(234, 70, 69));
        registerButtonPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonPanel5MouseEntered(evt);
            }
        });
        getContentPane().add(registerButtonPanel5);
        registerButtonPanel5.setBounds(1051, 443, 160, 28);

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
        registerButton4.setBounds(1051, 292, 160, 28);

        registerButtonLabel4.setFont(FonteUtils.carregarRobotoRegular(11f));
        registerButtonLabel4.setForeground(new java.awt.Color(9, 10, 12));
        registerButtonLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerButtonLabel4.setText("Visualizar");
        getContentPane().add(registerButtonLabel4);
        registerButtonLabel4.setBounds(1051, 292, 160, 28);

        registerButtonPanel4.setBackground(new java.awt.Color(234, 70, 69));
        registerButtonPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonPanel4MouseEntered(evt);
            }
        });
        getContentPane().add(registerButtonPanel4);
        registerButtonPanel4.setBounds(1051, 292, 160, 28);

        TipIconLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel11);
        TipIconLabel11.setBounds(816, 443, 25, 25);

        TipIconLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel10);
        TipIconLabel10.setBounds(816, 292, 25, 25);

        TipIconLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel9);
        TipIconLabel9.setBounds(293, 506, 20, 20);

        TipIconLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel8);
        TipIconLabel8.setBounds(293, 452, 20, 20);

        TipIconLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel7);
        TipIconLabel7.setBounds(293, 398, 20, 20);

        TipIconLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel6);
        TipIconLabel6.setBounds(293, 344, 20, 20);

        TipIconLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel5);
        TipIconLabel5.setBounds(293, 290, 20, 20);

        TipIconLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel1);
        TipIconLabel1.setBounds(293, 236, 20, 20);

        TitleTipLabel1.setFont(FonteUtils.carregarRobotoRegular(13f));
        TitleTipLabel1.setForeground(new java.awt.Color(234, 70, 69));
        TitleTipLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TitleTipLabel1.setText("Excluir Conta");
        TitleTipLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        TitleTipLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TitleTipLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TitleTipLabel1MouseExited(evt);
            }
        });
        getContentPane().add(TitleTipLabel1);
        TitleTipLabel1.setBounds(856, 434, 180, 20);

        TipLabel5.setFont(FonteUtils.carregarRobotoRegular(11f));
        TipLabel5.setForeground(new java.awt.Color(209, 213, 219));
        TipLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TipLabel5.setText("<html>Encerre sua sessão atual em todos os dispositivos.</html>");
        TipLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        TipLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TipLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TipLabel5MouseExited(evt);
            }
        });
        getContentPane().add(TipLabel5);
        TipLabel5.setBounds(856, 452, 180, 40);

        IDPersonalInfoLabel10.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel10.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel10.setText("evanilson.rocha@ctcontab.com.br");
        IDPersonalInfoLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel10MouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel10);
        IDPersonalInfoLabel10.setBounds(465, 455, 260, 14);

        IDPersonalInfoLabel8.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel8.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel8.setText("25/12/2026");
        IDPersonalInfoLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel8MouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel8);
        IDPersonalInfoLabel8.setBounds(465, 347, 260, 14);

        IDPersonalInfoLabel11.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel11.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel11.setText("Ativa");
        IDPersonalInfoLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel11MouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel11);
        IDPersonalInfoLabel11.setBounds(465, 509, 260, 14);

        IDPersonalInfoLabel9.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel9.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel9.setText("25/12/2026 às 14:35");
        IDPersonalInfoLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel9MouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel9);
        IDPersonalInfoLabel9.setBounds(465, 401, 260, 14);

        IDPersonalInfoLabel6.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel6.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel6.setText("13002");
        IDPersonalInfoLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel6MouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel6);
        IDPersonalInfoLabel6.setBounds(465, 239, 260, 14);

        IDPersonalInfoLabel7.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel7.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel7.setText("Administrador");
        IDPersonalInfoLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel7MouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel7);
        IDPersonalInfoLabel7.setBounds(465, 293, 260, 14);

        IDPersonalInfoLabel5.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel5.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel5.setText("Status da Conta");
        IDPersonalInfoLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel5MouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel5);
        IDPersonalInfoLabel5.setBounds(326, 509, 130, 14);

        IDPersonalInfoLabel4.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel4.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel4.setText("E-mail Cadastrado");
        IDPersonalInfoLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel4MouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel4);
        IDPersonalInfoLabel4.setBounds(326, 455, 130, 14);

        IDPersonalInfoLabel3.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel3.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel3.setText("Último Acesso");
        IDPersonalInfoLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel3MouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel3);
        IDPersonalInfoLabel3.setBounds(326, 401, 130, 14);

        IDPersonalInfoLabel2.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel2.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel2.setText("Data de Cadastro");
        IDPersonalInfoLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel2MouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel2);
        IDPersonalInfoLabel2.setBounds(326, 347, 130, 14);

        IDPersonalInfoLabel1.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel1.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel1.setText("Cargo");
        IDPersonalInfoLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabel1MouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel1);
        IDPersonalInfoLabel1.setBounds(326, 293, 130, 14);

        IDPersonalInfoLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        IDPersonalInfoLabel.setForeground(new java.awt.Color(209, 213, 219));
        IDPersonalInfoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        IDPersonalInfoLabel.setText("ID do Usuário");
        IDPersonalInfoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IDPersonalInfoLabelMouseExited(evt);
            }
        });
        getContentPane().add(IDPersonalInfoLabel);
        IDPersonalInfoLabel.setBounds(326, 239, 130, 14);

        TipLabel1.setFont(FonteUtils.carregarRobotoRegular(11f));
        TipLabel1.setForeground(new java.awt.Color(209, 213, 219));
        TipLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TipLabel1.setText("<html>Encerre sua sessão atual em todos os dispositivos.</html>");
        TipLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        TipLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TipLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TipLabel1MouseExited(evt);
            }
        });
        getContentPane().add(TipLabel1);
        TipLabel1.setBounds(856, 292, 180, 40);

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

        TitleTipLabel.setFont(FonteUtils.carregarRobotoRegular(13f));
        TitleTipLabel.setForeground(new java.awt.Color(234, 70, 69));
        TitleTipLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TitleTipLabel.setText("Encerrar Sessão");
        TitleTipLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        TitleTipLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TitleTipLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TitleTipLabelMouseExited(evt);
            }
        });
        getContentPane().add(TitleTipLabel);
        TitleTipLabel.setBounds(856, 274, 180, 20);

        ProfileIconNameLabel1.setFont(FonteUtils.carregarRobotoSemiBold(15f));
        ProfileIconNameLabel1.setForeground(new java.awt.Color(234, 70, 69));
        ProfileIconNameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ProfileIconNameLabel1.setText("Zona de Perigo");
        ProfileIconNameLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ProfileIconNameLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProfileIconNameLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProfileIconNameLabel1MouseExited(evt);
            }
        });
        getContentPane().add(ProfileIconNameLabel1);
        ProfileIconNameLabel1.setBounds(796, 198, 330, 20);

        ProfileIconNameLabel.setFont(FonteUtils.carregarRobotoSemiBold(15f));
        ProfileIconNameLabel.setForeground(new java.awt.Color(209, 213, 219));
        ProfileIconNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ProfileIconNameLabel.setText("Informações Pessoais");
        ProfileIconNameLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ProfileIconNameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProfileIconNameLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProfileIconNameLabelMouseExited(evt);
            }
        });
        getContentPane().add(ProfileIconNameLabel);
        ProfileIconNameLabel.setBounds(293, 198, 330, 20);

        EditInfoPanel3.setBackground(new java.awt.Color(33, 35, 49));
        getContentPane().add(EditInfoPanel3);
        EditInfoPanel3.setBounds(797, 387, 434, 137);

        EditInfoPanel2.setBackground(new java.awt.Color(33, 35, 49));
        getContentPane().add(EditInfoPanel2);
        EditInfoPanel2.setBounds(797, 236, 434, 137);

        EditInfoPanel1.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(EditInfoPanel1);
        EditInfoPanel1.setBounds(779, 180, 469, 366);

        EditInfoPanel.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(EditInfoPanel);
        EditInfoPanel.setBounds(276, 180, 469, 366);

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
        new Home(usuarioLogado).setVisible(true);
        this.dispose();
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
        AccountLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_AccountLabelMouseExited

    private void AccountButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccountButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AccountLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_AccountButtonMouseEntered

    private void AccountButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccountButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        AccountLabel.setForeground(COR_ABA_ATIVA);
    }//GEN-LAST:event_AccountButtonMouseExited

    private void AccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountButtonActionPerformed
        irParaConta();
    }//GEN-LAST:event_AccountButtonActionPerformed

    private void ProfileIconNameLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileIconNameLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileIconNameLabelMouseEntered

    private void ProfileIconNameLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileIconNameLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileIconNameLabelMouseExited

    private void IDPersonalInfoLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabelMouseEntered

    private void IDPersonalInfoLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabelMouseExited

    private void TitleTipLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitleTipLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TitleTipLabelMouseEntered

    private void TitleTipLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitleTipLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TitleTipLabelMouseExited

    private void TipLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TipLabel1MouseEntered

    private void TipLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipLabel1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TipLabel1MouseExited

    private void ProfileIconNameLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileIconNameLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileIconNameLabel1MouseEntered

    private void ProfileIconNameLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileIconNameLabel1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileIconNameLabel1MouseExited

    private void IDPersonalInfoLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel1MouseEntered

    private void IDPersonalInfoLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel1MouseExited

    private void IDPersonalInfoLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel2MouseEntered

    private void IDPersonalInfoLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel2MouseExited

    private void IDPersonalInfoLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel3MouseEntered

    private void IDPersonalInfoLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel3MouseExited

    private void IDPersonalInfoLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel4MouseEntered

    private void IDPersonalInfoLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel4MouseExited

    private void IDPersonalInfoLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel5MouseEntered

    private void IDPersonalInfoLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel5MouseExited

    private void IDPersonalInfoLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel6MouseEntered

    private void IDPersonalInfoLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel6MouseExited

    private void IDPersonalInfoLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel7MouseEntered

    private void IDPersonalInfoLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel7MouseExited

    private void IDPersonalInfoLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel8MouseEntered

    private void IDPersonalInfoLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel8MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel8MouseExited

    private void IDPersonalInfoLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel9MouseEntered

    private void IDPersonalInfoLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel9MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel9MouseExited

    private void IDPersonalInfoLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel10MouseEntered

    private void IDPersonalInfoLabel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel10MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel10MouseExited

    private void IDPersonalInfoLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel11MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel11MouseEntered

    private void IDPersonalInfoLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IDPersonalInfoLabel11MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_IDPersonalInfoLabel11MouseExited

    private void TitleTipLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitleTipLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TitleTipLabel1MouseEntered

    private void TitleTipLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitleTipLabel1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TitleTipLabel1MouseExited

    private void TipLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipLabel5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TipLabel5MouseEntered

    private void TipLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipLabel5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TipLabel5MouseExited

    private void registerButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton4MouseEntered
        hoverBotaoContorno(registerButtonPanel4, true);
    }//GEN-LAST:event_registerButton4MouseEntered

    private void registerButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton4MouseExited
        hoverBotaoContorno(registerButtonPanel4, false);
    }//GEN-LAST:event_registerButton4MouseExited

    private void registerButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButton4ActionPerformed
        excluirConta();
    }//GEN-LAST:event_registerButton4ActionPerformed

    private void registerButtonPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonPanel4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonPanel4MouseEntered

    private void registerButtonPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonPanel5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_registerButtonPanel5MouseEntered

    private void registerButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton5MouseEntered
        hoverBotaoContorno(registerButtonPanel5, true);
    }//GEN-LAST:event_registerButton5MouseEntered

    private void registerButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButton5MouseExited
        hoverBotaoContorno(registerButtonPanel5, false);
    }//GEN-LAST:event_registerButton5MouseExited

    private void registerButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButton5ActionPerformed
        encerrarSessao();
    }//GEN-LAST:event_registerButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(ConfigAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    class RoundedPanel extends javax.swing.JPanel {

        private int radius;
        private boolean showBorder = false;
        private Color borderColor = new Color(220, 68, 80);

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
    private javax.swing.JLabel ButtonArrowIcon2;
    private javax.swing.JLabel ButtonArrowIcon3;
    private javax.swing.JPanel EditInfoPanel;
    private javax.swing.JPanel EditInfoPanel1;
    private javax.swing.JPanel EditInfoPanel2;
    private javax.swing.JPanel EditInfoPanel3;
    private javax.swing.JPanel Ellipse1;
    private javax.swing.JPanel Ellipse2;
    private javax.swing.JPanel Ellipse3;
    private javax.swing.JLabel IDPersonalInfoLabel;
    private javax.swing.JLabel IDPersonalInfoLabel1;
    private javax.swing.JLabel IDPersonalInfoLabel10;
    private javax.swing.JLabel IDPersonalInfoLabel11;
    private javax.swing.JLabel IDPersonalInfoLabel2;
    private javax.swing.JLabel IDPersonalInfoLabel3;
    private javax.swing.JLabel IDPersonalInfoLabel4;
    private javax.swing.JLabel IDPersonalInfoLabel5;
    private javax.swing.JLabel IDPersonalInfoLabel6;
    private javax.swing.JLabel IDPersonalInfoLabel7;
    private javax.swing.JLabel IDPersonalInfoLabel8;
    private javax.swing.JLabel IDPersonalInfoLabel9;
    private javax.swing.JButton PreferencesButton;
    private javax.swing.JLabel PreferencesLabel;
    private javax.swing.JPanel PreferencesSeparator;
    private javax.swing.JButton ProfileButton;
    private javax.swing.JLabel ProfileIconNameLabel;
    private javax.swing.JLabel ProfileIconNameLabel1;
    private javax.swing.JLabel ProfileLabel;
    private javax.swing.JPanel ProfileSeparator;
    private javax.swing.JButton SecurityButton;
    private javax.swing.JLabel SecurityLabel;
    private javax.swing.JPanel SecuritySeparator;
    private javax.swing.JPanel Separator;
    private javax.swing.JLabel TipIconLabel1;
    private javax.swing.JLabel TipIconLabel10;
    private javax.swing.JLabel TipIconLabel11;
    private javax.swing.JLabel TipIconLabel5;
    private javax.swing.JLabel TipIconLabel6;
    private javax.swing.JLabel TipIconLabel7;
    private javax.swing.JLabel TipIconLabel8;
    private javax.swing.JLabel TipIconLabel9;
    private javax.swing.JLabel TipLabel1;
    private javax.swing.JLabel TipLabel5;
    private javax.swing.JLabel TitleTipLabel;
    private javax.swing.JLabel TitleTipLabel1;
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
    private javax.swing.JPanel horizontalDivider1;
    private javax.swing.JPanel horizontalDivider2;
    private javax.swing.JPanel horizontalDivider3;
    private javax.swing.JPanel horizontalDivider4;
    private javax.swing.JPanel horizontalDivider5;
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
    private javax.swing.JButton registerButton4;
    private javax.swing.JButton registerButton5;
    private javax.swing.JLabel registerButtonLabel4;
    private javax.swing.JLabel registerButtonLabel5;
    private javax.swing.JPanel registerButtonPanel4;
    private javax.swing.JPanel registerButtonPanel5;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
