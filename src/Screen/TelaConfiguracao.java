package screen;

import Data.I18nManager;
import Data.IconUtil;
import java.awt.*;
import javax.swing.*;
import Data.PermissaoUtil;
import Data.Usuario;
import screen.FonteUtils;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class TelaConfiguracao extends javax.swing.JFrame {

    private static final Logger logger = LoggerFactory.getLogger(TelaConfiguracao.class);

    private Usuario usuarioLogado;
    private int mouseX, mouseY;
    private final java.util.Map<javax.swing.JLabel, String> hoverLabelsTraduziveis = new java.util.HashMap<>();
    private final Color COR_PADRAO = new Color(4, 19, 53);
    private final Color COR_HOVER_GERAL = new Color(26, 41, 75);
    private final Color COR_HOVER_FECHAR = Color.RED;

    public TelaConfiguracao(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        aplicarImagensDeAltaQualidade();
        setUndecorated(true);
        setResizable(false);
        setIcon();
        atualizarTextos();
        IconUtil.setIcon(usuarioLogado, lblUserIcon, 40, 40);
        IconUtil.setIcon(usuarioLogado, lblUserIconConfig, 80, 80);
        pnlButtonEmail.setVisible(false);
        pnlButtonPerfil.setVisible(false);
        pnlButtonUpdate.setVisible(false);
        pnlButtonUserName.setVisible(false);
        pnlButtonLanguages.setVisible(false);
        pnlButtonPassword.setVisible(false);
        setBackground(new java.awt.Color(0, 0, 0, 0));
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        addHoverLabel(btnDashboard, "navigation.sidebar.dashboard", true);
        addHoverLabel(btnCalendario, "navigation.sidebar.calendar", true);
        addHoverLabel(btnClientes, "navigation.sidebar.clients", true);
        addHoverLabel(btnRelatorios, "navigation.sidebar.reports", true);
        addHoverLabel(btnTarefas, "navigation.sidebar.tasks", true);
        addHoverLabel(btnConfiguracoes, "navigation.sidebar.settings", true);
        addHoverLabel(btnAdministracao, "navigation.sidebar.admin", true);
        addHoverLabel(btnNotificacoes, "navigation.sidebar.notify", true);
        addHoverLabel(btnInfo, "navigation.sidebar.help", true);
        addHoverLabel(btnUserIcon, usuarioLogado.getUsuario(), false);
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Logo Icon.png")));
    }

    private void aplicarImagensDeAltaQualidade() {
        try {
            BufferedImage DashboardNormal = ImageIO.read(getClass().getResource("/images/Dashboard Icon.png"));
            BufferedImage DashboardHover = ImageIO.read(getClass().getResource("/images/Dashboard Icon Hover.png"));
            BufferedImage CalendarNormal = ImageIO.read(getClass().getResource("/images/Calendar Icon.png"));
            BufferedImage CalendarHover = ImageIO.read(getClass().getResource("/images/Calendar Icon Hover.png"));
            BufferedImage ClientNormal = ImageIO.read(getClass().getResource("/images/Client Icon.png"));
            BufferedImage ClientHover = ImageIO.read(getClass().getResource("/images/Client Icon Hover.png"));
            BufferedImage TaskNormal = ImageIO.read(getClass().getResource("/images/Tasks Icon.png"));
            BufferedImage TaskHover = ImageIO.read(getClass().getResource("/images/Tasks Icon Hover.png"));
            BufferedImage SettingsNormal = ImageIO.read(getClass().getResource("/images/Settings Icon Active.png"));
            BufferedImage SettingsHover = ImageIO.read(getClass().getResource("/images/Settings Icon Hover.png"));
            BufferedImage ReportNormal = ImageIO.read(getClass().getResource("/images/Report Icon.png"));
            BufferedImage ReportHover = ImageIO.read(getClass().getResource("/images/Report Icon  Hover.png"));
            BufferedImage AdministrativeNormal = ImageIO.read(getClass().getResource("/images/Administrative Icon.png"));
            BufferedImage AdministrativeHover = ImageIO.read(getClass().getResource("/images/Administrative Icon Hover.png"));
            BufferedImage NotificationNormal = ImageIO.read(getClass().getResource("/images/Notification Bell.png"));
            BufferedImage NotificationHover = ImageIO.read(getClass().getResource("/images/Notification Bell Hover.png"));
            BufferedImage DivisorSuperiorBar = ImageIO.read(getClass().getResource("/images/Divider Icon.png"));
            BufferedImage Background = ImageIO.read(getClass().getResource("/images/Dashboard Background.png"));
            BufferedImage imgLogo = ImageIO.read(getClass().getResource("/images/Logo Icon.png"));
            BufferedImage closeNormal = ImageIO.read(getClass().getResource("/images/Close Icon.png"));
            BufferedImage closeWhite = ImageIO.read(getClass().getResource("/images/Close White Icon.png"));
            BufferedImage minNormal = ImageIO.read(getClass().getResource("/images/Minimize Icon.png"));
            BufferedImage minWhite = ImageIO.read(getClass().getResource("/images/Minimize White Icon.png"));
            BufferedImage maxNormal = ImageIO.read(getClass().getResource("/images/Maximize Icon.png"));
            BufferedImage maxWhite = ImageIO.read(getClass().getResource("/images/Maximize White Icon.png"));
            BufferedImage infoNormal = ImageIO.read(getClass().getResource("/images/Information Icon.png"));
            BufferedImage infoWhite = ImageIO.read(getClass().getResource("/images/Information White Icon.png"));
            BufferedImage LogoText = ImageIO.read(getClass().getResource("/images/Logo Text Icon.png"));
            BufferedImage UserIcon = ImageIO.read(getClass().getResource("/images/Jonh Doe Icon.png"));
            BufferedImage Security = ImageIO.read(getClass().getResource("/images/Security Icon.png"));
            BufferedImage Update = ImageIO.read(getClass().getResource("/images/Update Icon.png"));
            BufferedImage UpdateButton = ImageIO.read(getClass().getResource("/images/Update Button Icon.png"));
            BufferedImage EmailButton = ImageIO.read(getClass().getResource("/images/Email Button Icon.png"));
            BufferedImage LanguagesButton = ImageIO.read(getClass().getResource("/images/Languages Button Icon.png"));
            BufferedImage UserButton = ImageIO.read(getClass().getResource("/images/User Button Icon.png"));
            BufferedImage UserNameButton = ImageIO.read(getClass().getResource("/images/UserName Button Icon.png"));
            BufferedImage PasswordButton = ImageIO.read(getClass().getResource("/images/Password Button Icon.png"));
            BufferedImage WorkingButton = ImageIO.read(getClass().getResource("/images/Working Icon.png"));
            BufferedImage FailButton = ImageIO.read(getClass().getResource("/images/Fail Icon.png"));

            configurarBotaoControle(btnFecharTela, closeNormal, closeWhite, 11, 11, COR_HOVER_FECHAR);
            configurarBotaoControle(btnMinimizarTela, minNormal, minWhite, 11, 2, COR_HOVER_GERAL);
            configurarBotaoControle(btnMaximizarTela, maxNormal, maxWhite, 11, 11, COR_HOVER_GERAL);
            configurarBotaoControle(btnInfo, infoNormal, infoWhite, 13, 13, COR_HOVER_GERAL);

            configurarBotaoComHover(btnDashboard, DashboardNormal, DashboardHover, 22, 22);
            configurarBotaoComHover(btnCalendario, CalendarNormal, CalendarHover, 22, 22);
            configurarBotaoComHover(btnClientes, ClientNormal, ClientHover, 22, 22);
            configurarBotaoComHover(btnTarefas, TaskNormal, TaskHover, 22, 22);
            configurarBotaoComHover(btnConfiguracoes, SettingsNormal, SettingsHover, 22, 22);
            configurarBotaoComHover(btnRelatorios, ReportNormal, ReportHover, 22, 22);
            configurarBotaoComHover(btnAdministracao, AdministrativeNormal, AdministrativeHover, 22, 22);
            configurarBotaoComHover(btnNotificacoes, NotificationNormal, NotificationHover, 22, 22);

            lblLogo.setIcon(gerarIconePerfeito(imgLogo, 40, 40));
            lblIconPerfil.setIcon(gerarIconePerfeito(UserButton, 40, 40));
            lblIconEmail.setIcon(gerarIconePerfeito(EmailButton, 40, 40));
            lblIconPassword.setIcon(gerarIconePerfeito(PasswordButton, 40, 40));
            lblIconUserName.setIcon(gerarIconePerfeito(UserNameButton, 40, 40));
            lblIconUpdate.setIcon(gerarIconePerfeito(UpdateButton, 40, 40));
            lblIconLanguages.setIcon(gerarIconePerfeito(LanguagesButton, 40, 40));
            lblIconUpdateSystem.setIcon(gerarIconePerfeito(Update, 35, 35));
            lblIconSecurity.setIcon(gerarIconePerfeito(Security, 35, 35));
            lblIconUpdateSystemSucess.setIcon(gerarIconePerfeito(WorkingButton, 20, 20));
            lblIconSecuritySucess.setIcon(gerarIconePerfeito(WorkingButton, 20, 20));
            lblDivisorTela.setIcon(gerarIconePerfeito(DivisorSuperiorBar, 2, 11));
            lblBackground.setIcon(gerarIconePerfeito(Background, 1450, 750));
            lblLogoTexto.setIcon(gerarIconePerfeito(LogoText, 176, 46));
            lblUserIcon.setIcon(gerarIconePerfeito(UserIcon, 50, 50));
            lblLogo.setText("");

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

    private void configurarBotaoComHover(javax.swing.JButton botao, BufferedImage active, BufferedImage hover, int w, int h) {
        botao.setIcon(gerarIconePerfeito(active, w, h));
        botao.setRolloverIcon(gerarIconePerfeito(hover, w, h));
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setText("");
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

    private void addHoverLabel(javax.swing.JButton botao, String chaveOuTexto, boolean isI18n) {
        String textoInicial = isI18n ? Data.I18nManager.getString(chaveOuTexto) : chaveOuTexto;

        java.util.Locale loc = Data.I18nManager.getCurrentLocale();
        String lang = loc.getLanguage();
        boolean isAsiatico = lang.equals("ja") || lang.equals("ko") || lang.equals("zh");

        javax.swing.JLabel label = new javax.swing.JLabel(textoInicial, javax.swing.SwingConstants.CENTER) {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new java.awt.Color(7, 30, 70));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g2);
                g2.dispose();
            }
        };

        label.setOpaque(false);
        label.setForeground(java.awt.Color.WHITE);
        label.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setVisible(false);

        if (isAsiatico) {
            label.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        } else {
            label.setFont(screen.FonteUtils.carregarSofiaSansBlack(13f));
        }

        botao.getParent().add(label, 0);
        if (isI18n) {
            hoverLabelsTraduziveis.put(label, chaveOuTexto);
        }

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setSize(label.getPreferredSize());
                label.setVisible(true);
                botao.getParent().repaint();

                if (botao == btnUserIcon) {
                    label.setLocation(botao.getParent().getWidth() - label.getWidth() - 10, botao.getY() + botao.getHeight() + 5);
                } else if (botao == btnNotificacoes || botao == btnInfo) {
                    label.setLocation(botao.getX() + (botao.getWidth() - label.getWidth()) / 2, botao.getY() + botao.getHeight() + 5);
                } else {
                    label.setLocation(botao.getX() + botao.getWidth() + 30, botao.getY() + (botao.getHeight() - label.getHeight()) / 2);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setVisible(false);
            }
        });
    }

    private void aplicarFonteSistema(java.awt.Container container, boolean isAsiatico) {
        for (java.awt.Component c : container.getComponents()) {

            Font fonteAtual = c.getFont();
            int tamanho = fonteAtual.getSize();
            int estilo = fonteAtual.getStyle();

            if (isAsiatico) {
                c.setFont(new Font("SansSerif", estilo, tamanho));
            } else {
            }

            if (c instanceof java.awt.Container) {
                aplicarFonteSistema((java.awt.Container) c, isAsiatico);
            }
        }
    }

    private void atualizarTextos() {
        Locale loc = I18nManager.getCurrentLocale();
        String lang = loc.getLanguage();
        boolean isAsiatico = lang.equals("ja") || lang.equals("ko") || lang.equals("zh");

        aplicarFonteSistema(this.getContentPane(), isAsiatico);

        setTitle(I18nManager.getString("auth.login.window_title"));

        lblTituloPagina.setText(I18nManager.getString("dashboard.header.title"));
        lblNomeUsuario.setText(usuarioLogado.getUsuario());
        lblEmailUsuario.setText(usuarioLogado.getEmail());

        for (java.util.Map.Entry<javax.swing.JLabel, String> entry : hoverLabelsTraduziveis.entrySet()) {
            javax.swing.JLabel lbl = entry.getKey();
            lbl.setText(Data.I18nManager.getString(entry.getValue()));

            if (isAsiatico) {
                lbl.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
            } else {
                lbl.setFont(screen.FonteUtils.carregarSofiaSansBlack(13f));
            }
        }

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIconSecuritySucess = new javax.swing.JLabel();
        lblIconUpdateSystemSucess = new javax.swing.JLabel();
        lblDescriptionSecurity = new javax.swing.JLabel();
        lblDescriptionUpdateSystem = new javax.swing.JLabel();
        lblTitleUpdateSystem = new javax.swing.JLabel();
        lblTitleSecurity = new javax.swing.JLabel();
        lblIconUpdateSystem = new javax.swing.JLabel();
        lblIconSecurity = new javax.swing.JLabel();
        btnConfigEmail = new javax.swing.JButton();
        lblTitleLinguages = new javax.swing.JLabel();
        lblIconLanguages = new javax.swing.JLabel();
        lblDescriptionLinguages = new javax.swing.JLabel();
        lblIconUpdate = new javax.swing.JLabel();
        lblTitleUpdate = new javax.swing.JLabel();
        lblTitleUserName = new javax.swing.JLabel();
        lblDescriptionUpdate = new javax.swing.JLabel();
        lblIconUserName = new javax.swing.JLabel();
        lblDescriptionUserName = new javax.swing.JLabel();
        lblDescriptionPassword = new javax.swing.JLabel();
        lblIconPassword = new javax.swing.JLabel();
        lblTitlePassword = new javax.swing.JLabel();
        lblIconPerfil = new javax.swing.JLabel();
        lblDescriptionPerfil = new javax.swing.JLabel();
        lblTitlePerfil = new javax.swing.JLabel();
        lblDescriptionEmail = new javax.swing.JLabel();
        lblTitleEmail = new javax.swing.JLabel();
        lblIconEmail = new javax.swing.JLabel();
        lblSystemUsuario = new javax.swing.JLabel();
        lblEmailUsuario = new javax.swing.JLabel();
        lblNomeUsuario = new javax.swing.JLabel();
        btnConfigLanguages = new javax.swing.JButton();
        pnlButtonLanguages = new javax.swing.JPanel();
        btnConfigUpdate = new javax.swing.JButton();
        pnlButtonUpdate = new javax.swing.JPanel();
        pnlButtonUserName = new javax.swing.JPanel();
        btnConfigUserName = new javax.swing.JButton();
        btnConfigPassword = new javax.swing.JButton();
        pnlButtonPassword = new javax.swing.JPanel();
        pnlButtonPerfil = new javax.swing.JPanel();
        btnConfigPerfil = new javax.swing.JButton();
        pnlButtonEmail = new javax.swing.JPanel();
        btnUserIconConfig = new javax.swing.JButton();
        lblUserIconConfig = new javax.swing.JLabel();
        lblDivisorTela = new javax.swing.JLabel();
        btnInfo = new javax.swing.JButton();
        btnMaximizarTela = new javax.swing.JButton();
        btnMinimizarTela = new javax.swing.JButton();
        btnFecharTela = new javax.swing.JButton();
        lblTituloPagina = new javax.swing.JLabel();
        lblLogoTexto = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        btnUserIcon = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnAdministracao = new javax.swing.JButton();
        btnNotificacoes = new javax.swing.JButton();
        lblBarraSuperior = new javax.swing.JLabel();
        lblBarraLateral = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configurações - CT CONTAB");
        getContentPane().setLayout(null);

        lblIconSecuritySucess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconSecuritySucess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Working Icon.png"))); // NOI18N
        getContentPane().add(lblIconSecuritySucess);
        lblIconSecuritySucess.setBounds(1390, 130, 22, 22);

        lblIconUpdateSystemSucess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconUpdateSystemSucess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Working Icon.png"))); // NOI18N
        getContentPane().add(lblIconUpdateSystemSucess);
        lblIconUpdateSystemSucess.setBounds(1290, 130, 22, 22);

        lblDescriptionSecurity.setFont(FonteUtils.carregarInterBold(11f));
        lblDescriptionSecurity.setForeground(new java.awt.Color(130, 128, 128));
        lblDescriptionSecurity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescriptionSecurity.setText("Protegido");
        getContentPane().add(lblDescriptionSecurity);
        lblDescriptionSecurity.setBounds(1340, 170, 90, 20);

        lblDescriptionUpdateSystem.setFont(FonteUtils.carregarInterBold(11f));
        lblDescriptionUpdateSystem.setForeground(new java.awt.Color(130, 128, 128));
        lblDescriptionUpdateSystem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescriptionUpdateSystem.setText("Atualizado");
        getContentPane().add(lblDescriptionUpdateSystem);
        lblDescriptionUpdateSystem.setBounds(1240, 170, 90, 20);

        lblTitleUpdateSystem.setFont(FonteUtils.carregarInterBold(13f));
        lblTitleUpdateSystem.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleUpdateSystem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleUpdateSystem.setText("Atualização");
        getContentPane().add(lblTitleUpdateSystem);
        lblTitleUpdateSystem.setBounds(1240, 150, 90, 20);

        lblTitleSecurity.setFont(FonteUtils.carregarInterBold(13f));
        lblTitleSecurity.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleSecurity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleSecurity.setText("Segurança");
        getContentPane().add(lblTitleSecurity);
        lblTitleSecurity.setBounds(1340, 150, 90, 20);

        lblIconUpdateSystem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconUpdateSystem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Update Icon.png"))); // NOI18N
        getContentPane().add(lblIconUpdateSystem);
        lblIconUpdateSystem.setBounds(1260, 100, 50, 50);

        lblIconSecurity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconSecurity.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Security Icon.png"))); // NOI18N
        getContentPane().add(lblIconSecurity);
        lblIconSecurity.setBounds(1360, 100, 50, 50);

        btnConfigEmail.setBackground(new java.awt.Color(30, 30, 30));
        btnConfigEmail.setContentAreaFilled(false);
        btnConfigEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfigEmailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfigEmailMouseExited(evt);
            }
        });
        btnConfigEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigEmailActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfigEmail);
        btnConfigEmail.setBounds(110, 240, 320, 80);

        lblTitleLinguages.setFont(FonteUtils.carregarInterBold(14f));
        lblTitleLinguages.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleLinguages.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleLinguages.setText("Linguagens");
        getContentPane().add(lblTitleLinguages);
        lblTitleLinguages.setBounds(530, 260, 240, 20);

        lblIconLanguages.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconLanguages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Languages Button Icon.png"))); // NOI18N
        getContentPane().add(lblIconLanguages);
        lblIconLanguages.setBounds(470, 260, 40, 40);

        lblDescriptionLinguages.setFont(FonteUtils.carregarInterBold(11f));
        lblDescriptionLinguages.setForeground(new java.awt.Color(130, 128, 128));
        lblDescriptionLinguages.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriptionLinguages.setText("Defina sua lingua de origem");
        getContentPane().add(lblDescriptionLinguages);
        lblDescriptionLinguages.setBounds(530, 280, 240, 20);

        lblIconUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Update Button Icon.png"))); // NOI18N
        getContentPane().add(lblIconUpdate);
        lblIconUpdate.setBounds(130, 660, 40, 40);

        lblTitleUpdate.setFont(FonteUtils.carregarInterBold(14f));
        lblTitleUpdate.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleUpdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleUpdate.setText("Atualizações");
        getContentPane().add(lblTitleUpdate);
        lblTitleUpdate.setBounds(190, 660, 240, 20);

        lblTitleUserName.setFont(FonteUtils.carregarInterBold(14f));
        lblTitleUserName.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleUserName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleUserName.setText("Usuário");
        getContentPane().add(lblTitleUserName);
        lblTitleUserName.setBounds(190, 560, 240, 20);

        lblDescriptionUpdate.setFont(FonteUtils.carregarInterBold(11f));
        lblDescriptionUpdate.setForeground(new java.awt.Color(130, 128, 128));
        lblDescriptionUpdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriptionUpdate.setText("Verifique se há atualizações pendentes");
        getContentPane().add(lblDescriptionUpdate);
        lblDescriptionUpdate.setBounds(190, 680, 240, 20);

        lblIconUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconUserName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/UserName Button Icon.png"))); // NOI18N
        getContentPane().add(lblIconUserName);
        lblIconUserName.setBounds(130, 560, 40, 40);

        lblDescriptionUserName.setFont(FonteUtils.carregarInterBold(11f));
        lblDescriptionUserName.setForeground(new java.awt.Color(130, 128, 128));
        lblDescriptionUserName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriptionUserName.setText("Altere o seu nome de usuário");
        getContentPane().add(lblDescriptionUserName);
        lblDescriptionUserName.setBounds(190, 580, 240, 20);

        lblDescriptionPassword.setFont(FonteUtils.carregarInterBold(11f));
        lblDescriptionPassword.setForeground(new java.awt.Color(130, 128, 128));
        lblDescriptionPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriptionPassword.setText("Altere a senha de seu perfil");
        getContentPane().add(lblDescriptionPassword);
        lblDescriptionPassword.setBounds(190, 480, 240, 20);

        lblIconPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Password Button Icon.png"))); // NOI18N
        getContentPane().add(lblIconPassword);
        lblIconPassword.setBounds(130, 460, 40, 40);

        lblTitlePassword.setFont(FonteUtils.carregarInterBold(14f));
        lblTitlePassword.setForeground(new java.awt.Color(255, 255, 255));
        lblTitlePassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitlePassword.setText("Senha");
        getContentPane().add(lblTitlePassword);
        lblTitlePassword.setBounds(190, 460, 240, 20);

        lblIconPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/User Button Icon.png"))); // NOI18N
        getContentPane().add(lblIconPerfil);
        lblIconPerfil.setBounds(130, 360, 40, 40);

        lblDescriptionPerfil.setFont(FonteUtils.carregarInterBold(11f));
        lblDescriptionPerfil.setForeground(new java.awt.Color(130, 128, 128));
        lblDescriptionPerfil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriptionPerfil.setText("Altere a imagem de seu perfil");
        getContentPane().add(lblDescriptionPerfil);
        lblDescriptionPerfil.setBounds(190, 380, 240, 20);

        lblTitlePerfil.setFont(FonteUtils.carregarInterBold(14f));
        lblTitlePerfil.setForeground(new java.awt.Color(255, 255, 255));
        lblTitlePerfil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitlePerfil.setText("Imagem de Perfil");
        getContentPane().add(lblTitlePerfil);
        lblTitlePerfil.setBounds(190, 360, 240, 20);

        lblDescriptionEmail.setFont(FonteUtils.carregarInterBold(11f));
        lblDescriptionEmail.setForeground(new java.awt.Color(130, 128, 128));
        lblDescriptionEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriptionEmail.setText("Altere seu email atual");
        getContentPane().add(lblDescriptionEmail);
        lblDescriptionEmail.setBounds(190, 280, 240, 20);

        lblTitleEmail.setFont(FonteUtils.carregarInterBold(14f));
        lblTitleEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleEmail.setText("Email");
        getContentPane().add(lblTitleEmail);
        lblTitleEmail.setBounds(190, 260, 240, 20);

        lblIconEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Email Button Icon.png"))); // NOI18N
        getContentPane().add(lblIconEmail);
        lblIconEmail.setBounds(130, 260, 40, 40);

        lblSystemUsuario.setFont(FonteUtils.carregarInterSemiBold(12f));
        lblSystemUsuario.setForeground(new java.awt.Color(45, 156, 219));
        lblSystemUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSystemUsuario.setText("Software Gerenciador de Clientes");
        getContentPane().add(lblSystemUsuario);
        lblSystemUsuario.setBounds(190, 165, 240, 20);

        lblEmailUsuario.setFont(FonteUtils.carregarInterSemiBold(12f));
        lblEmailUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblEmailUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEmailUsuario.setText("seuemail@gmail.com");
        getContentPane().add(lblEmailUsuario);
        lblEmailUsuario.setBounds(190, 150, 240, 20);

        lblNomeUsuario.setFont(FonteUtils.carregarInterExtraBold(32f));
        lblNomeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNomeUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNomeUsuario.setText("Nome de Usuario");
        getContentPane().add(lblNomeUsuario);
        lblNomeUsuario.setBounds(190, 115, 240, 30);

        btnConfigLanguages.setBackground(new java.awt.Color(30, 30, 30));
        btnConfigLanguages.setContentAreaFilled(false);
        btnConfigLanguages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfigLanguagesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfigLanguagesMouseExited(evt);
            }
        });
        btnConfigLanguages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigLanguagesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfigLanguages);
        btnConfigLanguages.setBounds(450, 240, 320, 80);

        pnlButtonLanguages.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(pnlButtonLanguages);
        pnlButtonLanguages.setBounds(450, 240, 320, 80);

        btnConfigUpdate.setBackground(new java.awt.Color(30, 30, 30));
        btnConfigUpdate.setContentAreaFilled(false);
        btnConfigUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfigUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfigUpdateMouseExited(evt);
            }
        });
        btnConfigUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfigUpdate);
        btnConfigUpdate.setBounds(110, 640, 320, 80);

        pnlButtonUpdate.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(pnlButtonUpdate);
        pnlButtonUpdate.setBounds(110, 640, 320, 80);

        pnlButtonUserName.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(pnlButtonUserName);
        pnlButtonUserName.setBounds(110, 540, 320, 80);

        btnConfigUserName.setBackground(new java.awt.Color(30, 30, 30));
        btnConfigUserName.setContentAreaFilled(false);
        btnConfigUserName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfigUserNameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfigUserNameMouseExited(evt);
            }
        });
        btnConfigUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigUserNameActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfigUserName);
        btnConfigUserName.setBounds(110, 540, 320, 80);

        btnConfigPassword.setBackground(new java.awt.Color(30, 30, 30));
        btnConfigPassword.setContentAreaFilled(false);
        btnConfigPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfigPasswordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfigPasswordMouseExited(evt);
            }
        });
        btnConfigPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfigPassword);
        btnConfigPassword.setBounds(110, 440, 320, 80);

        pnlButtonPassword.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(pnlButtonPassword);
        pnlButtonPassword.setBounds(110, 440, 320, 80);

        pnlButtonPerfil.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(pnlButtonPerfil);
        pnlButtonPerfil.setBounds(110, 340, 320, 80);

        btnConfigPerfil.setBackground(new java.awt.Color(30, 30, 30));
        btnConfigPerfil.setContentAreaFilled(false);
        btnConfigPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfigPerfilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfigPerfilMouseExited(evt);
            }
        });
        btnConfigPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigPerfilActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfigPerfil);
        btnConfigPerfil.setBounds(110, 340, 320, 80);

        pnlButtonEmail.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(pnlButtonEmail);
        pnlButtonEmail.setBounds(110, 240, 320, 80);

        btnUserIconConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Administrative Icon.png"))); // NOI18N
        btnUserIconConfig.setContentAreaFilled(false);
        btnUserIconConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserIconConfigActionPerformed(evt);
            }
        });
        getContentPane().add(btnUserIconConfig);
        btnUserIconConfig.setBounds(100, 110, 80, 80);

        lblUserIconConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(lblUserIconConfig);
        lblUserIconConfig.setBounds(100, 110, 80, 80);

        lblDivisorTela.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDivisorTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        lblDivisorTela.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblDivisorTela.setPreferredSize(new java.awt.Dimension(13, 13));
        getContentPane().add(lblDivisorTela);
        lblDivisorTela.setBounds(1345, 0, 15, 25);

        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Information Icon.png"))); // NOI18N
        btnInfo.setContentAreaFilled(false);
        btnInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });
        getContentPane().add(btnInfo);
        btnInfo.setBounds(1315, 0, 30, 25);

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

        lblTituloPagina.setForeground(new java.awt.Color(199, 199, 199));
        lblTituloPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPagina.setText("CONFIGURAÇÕES");
        lblTituloPagina.setFont(FonteUtils.carregarRoboto(13f));
        getContentPane().add(lblTituloPagina);
        lblTituloPagina.setBounds(0, 3, 1440, 20);

        lblLogoTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Text Icon.png"))); // NOI18N
        getContentPane().add(lblLogoTexto);
        lblLogoTexto.setBounds(80, 35, 176, 46);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Icon.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(15, 35, 40, 40);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1390, 30, 512, 50);

        btnUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Administrative Icon.png"))); // NOI18N
        btnUserIcon.setContentAreaFilled(false);
        btnUserIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserIconActionPerformed(evt);
            }
        });
        getContentPane().add(btnUserIcon);
        btnUserIcon.setBounds(1390, 30, 50, 50);

        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Icon.png"))); // NOI18N
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        getContentPane().add(btnDashboard);
        btnDashboard.setBounds(19, 240, 30, 30);

        btnCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar Icon.png"))); // NOI18N
        btnCalendario.setContentAreaFilled(false);
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalendario);
        btnCalendario.setBounds(19, 280, 30, 30);

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Client Icon.png"))); // NOI18N
        btnClientes.setContentAreaFilled(false);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnClientes);
        btnClientes.setBounds(19, 320, 30, 30);

        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Report Icon.png"))); // NOI18N
        btnRelatorios.setContentAreaFilled(false);
        btnRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosActionPerformed(evt);
            }
        });
        getContentPane().add(btnRelatorios);
        btnRelatorios.setBounds(19, 360, 30, 30);

        btnTarefas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Tasks Icon.png"))); // NOI18N
        btnTarefas.setContentAreaFilled(false);
        btnTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarefasActionPerformed(evt);
            }
        });
        getContentPane().add(btnTarefas);
        btnTarefas.setBounds(19, 400, 30, 30);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Settings Icon.png"))); // NOI18N
        btnConfiguracoes.setContentAreaFilled(false);
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfiguracoes);
        btnConfiguracoes.setBounds(19, 440, 30, 30);

        btnAdministracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Administrative Icon.png"))); // NOI18N
        btnAdministracao.setContentAreaFilled(false);
        btnAdministracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdministracao);
        btnAdministracao.setBounds(19, 480, 30, 30);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Notification Bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1340, 35, 40, 40);

        lblBarraSuperior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/NIGHT-ABYSS Color.png"))); // NOI18N
        lblBarraSuperior.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblBarraSuperiorMouseDragged(evt);
            }
        });
        lblBarraSuperior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblBarraSuperiorMousePressed(evt);
            }
        });
        getContentPane().add(lblBarraSuperior);
        lblBarraSuperior.setBounds(0, 0, 1480, 25);

        lblBarraLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DEEP-OCEAN Color.png"))); // NOI18N
        getContentPane().add(lblBarraLateral);
        lblBarraLateral.setBounds(0, 0, 70, 750);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Background.png"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1460, 750);

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void lblBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBarraSuperiorMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - mouseX, y - mouseY);
    }//GEN-LAST:event_lblBarraSuperiorMouseDragged

    private void lblBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBarraSuperiorMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_lblBarraSuperiorMousePressed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        new Dashboard(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        new TelaEventoTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCalendarioActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        new TelaClienteTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosActionPerformed
        new TelaRelatorioTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRelatoriosActionPerformed

    private void btnTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarefasActionPerformed
        new TelaTarefaTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTarefasActionPerformed

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        new TelaConfiguracao(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConfiguracoesActionPerformed

    private void btnAdministracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracaoActionPerformed
        new TelaAdminTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdministracaoActionPerformed

    private void btnUserIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserIconActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUserIconActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInfoActionPerformed

    private void btnMaximizarTelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizarTelaMouseEntered

    }//GEN-LAST:event_btnMaximizarTelaMouseEntered

    private void btnMaximizarTelaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizarTelaMouseExited

    }//GEN-LAST:event_btnMaximizarTelaMouseExited

    private void btnMaximizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaximizarTelaActionPerformed

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

    private void btnFecharTelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharTelaMouseEntered

    }//GEN-LAST:event_btnFecharTelaMouseEntered

    private void btnFecharTelaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharTelaMouseExited

    }//GEN-LAST:event_btnFecharTelaMouseExited

    private void btnFecharTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharTelaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnFecharTelaActionPerformed

    private void btnUserIconConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserIconConfigActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUserIconConfigActionPerformed

    private void btnConfigEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigEmailActionPerformed
        TelaAlterarEmail popup = new TelaAlterarEmail(this, usuarioLogado);
        popup.setVisible(true);
    }//GEN-LAST:event_btnConfigEmailActionPerformed

    private void btnConfigEmailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigEmailMouseEntered
        pnlButtonEmail.setVisible(true);
    }//GEN-LAST:event_btnConfigEmailMouseEntered

    private void btnConfigEmailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigEmailMouseExited
        pnlButtonEmail.setVisible(false);
    }//GEN-LAST:event_btnConfigEmailMouseExited

    private void btnConfigPerfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigPerfilMouseEntered
        pnlButtonPerfil.setVisible(true);
    }//GEN-LAST:event_btnConfigPerfilMouseEntered

    private void btnConfigPerfilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigPerfilMouseExited
        pnlButtonPerfil.setVisible(false);
    }//GEN-LAST:event_btnConfigPerfilMouseExited

    private void btnConfigPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfigPerfilActionPerformed

    private void btnConfigPasswordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigPasswordMouseEntered
        pnlButtonPassword.setVisible(true);
    }//GEN-LAST:event_btnConfigPasswordMouseEntered

    private void btnConfigPasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigPasswordMouseExited
        pnlButtonPassword.setVisible(false);
    }//GEN-LAST:event_btnConfigPasswordMouseExited

    private void btnConfigPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfigPasswordActionPerformed

    private void btnConfigUserNameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigUserNameMouseEntered
        pnlButtonUserName.setVisible(true);
    }//GEN-LAST:event_btnConfigUserNameMouseEntered

    private void btnConfigUserNameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigUserNameMouseExited
        pnlButtonUserName.setVisible(false);
    }//GEN-LAST:event_btnConfigUserNameMouseExited

    private void btnConfigUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfigUserNameActionPerformed

    private void btnConfigUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigUpdateMouseEntered
        pnlButtonUpdate.setVisible(true);
    }//GEN-LAST:event_btnConfigUpdateMouseEntered

    private void btnConfigUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigUpdateMouseExited
        pnlButtonUpdate.setVisible(false);
    }//GEN-LAST:event_btnConfigUpdateMouseExited

    private void btnConfigUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfigUpdateActionPerformed

    private void btnConfigLanguagesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigLanguagesMouseEntered
        pnlButtonLanguages.setVisible(true);
    }//GEN-LAST:event_btnConfigLanguagesMouseEntered

    private void btnConfigLanguagesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigLanguagesMouseExited
        pnlButtonLanguages.setVisible(false);
    }//GEN-LAST:event_btnConfigLanguagesMouseExited

    private void btnConfigLanguagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigLanguagesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfigLanguagesActionPerformed

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

    class RoundedButton extends javax.swing.JButton {

        private int radius;
        private java.awt.Rectangle originalBounds;
        private int scaleAmount = 1;

        public RoundedButton(int radius) {
            this.radius = radius;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    if (originalBounds == null) {
                        originalBounds = getBounds();
                    }

                    setBounds(originalBounds.x - scaleAmount,
                            originalBounds.y - scaleAmount,
                            originalBounds.width + (scaleAmount * 2),
                            originalBounds.height + (scaleAmount * 2));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    if (originalBounds != null) {
                        setBounds(originalBounds);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setClip(new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));

            super.paintComponent(g2);
            g2.dispose();
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdministracao;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfigEmail;
    private javax.swing.JButton btnConfigLanguages;
    private javax.swing.JButton btnConfigPassword;
    private javax.swing.JButton btnConfigPerfil;
    private javax.swing.JButton btnConfigUpdate;
    private javax.swing.JButton btnConfigUserName;
    private javax.swing.JButton btnConfiguracoes;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnFecharTela;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnMaximizarTela;
    private javax.swing.JButton btnMinimizarTela;
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnTarefas;
    private javax.swing.JButton btnUserIcon;
    private javax.swing.JButton btnUserIconConfig;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBarraLateral;
    private javax.swing.JLabel lblBarraSuperior;
    private javax.swing.JLabel lblDescriptionEmail;
    private javax.swing.JLabel lblDescriptionLinguages;
    private javax.swing.JLabel lblDescriptionPassword;
    private javax.swing.JLabel lblDescriptionPerfil;
    private javax.swing.JLabel lblDescriptionSecurity;
    private javax.swing.JLabel lblDescriptionUpdate;
    private javax.swing.JLabel lblDescriptionUpdateSystem;
    private javax.swing.JLabel lblDescriptionUserName;
    private javax.swing.JLabel lblDivisorTela;
    private javax.swing.JLabel lblEmailUsuario;
    private javax.swing.JLabel lblIconEmail;
    private javax.swing.JLabel lblIconLanguages;
    private javax.swing.JLabel lblIconPassword;
    private javax.swing.JLabel lblIconPerfil;
    private javax.swing.JLabel lblIconSecurity;
    private javax.swing.JLabel lblIconSecuritySucess;
    private javax.swing.JLabel lblIconUpdate;
    private javax.swing.JLabel lblIconUpdateSystem;
    private javax.swing.JLabel lblIconUpdateSystemSucess;
    private javax.swing.JLabel lblIconUserName;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoTexto;
    private javax.swing.JLabel lblNomeUsuario;
    private javax.swing.JLabel lblSystemUsuario;
    private javax.swing.JLabel lblTitleEmail;
    private javax.swing.JLabel lblTitleLinguages;
    private javax.swing.JLabel lblTitlePassword;
    private javax.swing.JLabel lblTitlePerfil;
    private javax.swing.JLabel lblTitleSecurity;
    private javax.swing.JLabel lblTitleUpdate;
    private javax.swing.JLabel lblTitleUpdateSystem;
    private javax.swing.JLabel lblTitleUserName;
    private javax.swing.JLabel lblTituloPagina;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JLabel lblUserIconConfig;
    private javax.swing.JPanel pnlButtonEmail;
    private javax.swing.JPanel pnlButtonLanguages;
    private javax.swing.JPanel pnlButtonPassword;
    private javax.swing.JPanel pnlButtonPerfil;
    private javax.swing.JPanel pnlButtonUpdate;
    private javax.swing.JPanel pnlButtonUserName;
    // End of variables declaration//GEN-END:variables
}
