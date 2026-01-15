package screen;

import Data.CTCONTAB;
import Data.I18nManager;
import Data.IconUtil;
import Data.Usuario;
import screen.FonteUtils;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Locale;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Dashboard extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private int mouseX, mouseY;
    private final java.util.Map<javax.swing.JLabel, String> hoverLabelsTraduziveis = new java.util.HashMap<>();
    private final Color COR_PADRAO = new Color(4, 19, 53);
    private final Color COR_HOVER_GERAL = new Color(26, 41, 75);
    private final Color COR_HOVER_FECHAR = Color.RED;

    public Dashboard(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        aplicarImagensDeAltaQualidade();
        setUndecorated(true);
        setResizable(false);
        setIcon();
        carregarKPIs();
        atualizarTextos();
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
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
            BufferedImage DashboardNormal = ImageIO.read(getClass().getResource("/images/Dashboard Icon Active.png"));
            BufferedImage DashboardHover = ImageIO.read(getClass().getResource("/images/Dashboard Icon Hover.png"));
            BufferedImage CalendarNormal = ImageIO.read(getClass().getResource("/images/Calendar Icon.png"));
            BufferedImage CalendarHover = ImageIO.read(getClass().getResource("/images/Calendar Icon Hover.png"));
            BufferedImage ClientNormal = ImageIO.read(getClass().getResource("/images/Client Icon.png"));
            BufferedImage ClientHover = ImageIO.read(getClass().getResource("/images/Client Icon Hover.png"));
            BufferedImage TaskNormal = ImageIO.read(getClass().getResource("/images/Tasks Icon.png"));
            BufferedImage TaskHover = ImageIO.read(getClass().getResource("/images/Tasks Icon Hover.png"));
            BufferedImage SettingsNormal = ImageIO.read(getClass().getResource("/images/Settings Icon.png"));
            BufferedImage SettingsHover = ImageIO.read(getClass().getResource("/images/Settings Icon Hover.png"));
            BufferedImage ReportNormal = ImageIO.read(getClass().getResource("/images/Report Icon.png"));
            BufferedImage ReportHover = ImageIO.read(getClass().getResource("/images/Report Icon  Hover.png"));
            BufferedImage AdministrativeNormal = ImageIO.read(getClass().getResource("/images/Administrative Icon.png"));
            BufferedImage AdministrativeHover = ImageIO.read(getClass().getResource("/images/Administrative Icon Hover.png"));
            BufferedImage NotificationNormal = ImageIO.read(getClass().getResource("/images/Notification Bell.png"));
            BufferedImage NotificationHover = ImageIO.read(getClass().getResource("/images/Notification Bell Hover.png"));
            BufferedImage DivisorSuperiorBar = ImageIO.read(getClass().getResource("/images/Divider Icon.png"));
            BufferedImage MonthlyClients = ImageIO.read(getClass().getResource("/images/Monthly Clients Icon.png"));
            BufferedImage TotalClients = ImageIO.read(getClass().getResource("/images/Total Clients Icon.png"));
            BufferedImage PendingTasks = ImageIO.read(getClass().getResource("/images/Pending Tasks Icon.png"));
            BufferedImage TotalReports = ImageIO.read(getClass().getResource("/images/Total Reports Icon.png"));
            BufferedImage UnfulfilledTasks = ImageIO.read(getClass().getResource("/images/Unfulfilled Tasks Icon.png"));
            BufferedImage ReportCompleted = ImageIO.read(getClass().getResource("/images/Report Completed Icon.png"));
            BufferedImage ArrowNewClient = ImageIO.read(getClass().getResource("/images/Button Icon.png"));
            BufferedImage BannerNewClient = ImageIO.read(getClass().getResource("/images/Client Button.png"));
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
            lblDivisorTela.setIcon(gerarIconePerfeito(DivisorSuperiorBar, 2, 11));
            lblIconClientesMensais.setIcon(gerarIconePerfeito(MonthlyClients, 28, 28));
            lblIconTotalClientes.setIcon(gerarIconePerfeito(TotalClients, 28, 28));
            lblIconTarefasPendentes.setIcon(gerarIconePerfeito(PendingTasks, 28, 28));
            lblIconTotalRelatorios.setIcon(gerarIconePerfeito(TotalReports, 28, 28));
            lblIconTarefasNaoRealizadas.setIcon(gerarIconePerfeito(UnfulfilledTasks, 28, 28));
            lblIconTarefasFinalizadas.setIcon(gerarIconePerfeito(ReportCompleted, 28, 28));
            lblSetaAdicionarClientes.setIcon(gerarIconePerfeito(ArrowNewClient, 50, 40));
            btnAdicionarCliente.setIcon(gerarIconePerfeito(BannerNewClient, 1340, 150));
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

    private String calcularPorcentagem(int atual, int anterior) {
        if (anterior <= 0) {
            return atual > 0 ? "100%" : "000%";
        }
        double calculo = ((double) atual / anterior) * 100;
        if (calculo > 100) {
            calculo = 100;
        }
        return String.format("%03.0f%%", calculo);
    }

    private String calcularTendencia(int atual, int anterior) {
        double variacao;

        if (anterior <= 0) {
            variacao = (atual > 0) ? 9.999 : 0.000;
        } else {
            variacao = ((double) atual / anterior) - 1;

            if (variacao > 9.999) {
                variacao = 9.999;
            }
            if (variacao < -9.999) {
                variacao = -9.999;
            }
        }

        return String.format("%.3f%%", variacao).replace(",", ".");
    }

    private void carregarKPIs() {
        try {
            int KPINovosClientes = Data.CTCONTAB.novosclientesdomes();
            lblKPIValueClientesMensais.setText(String.format("%04d", KPINovosClientes));

            int cMesAtual = Data.CTCONTAB.novosclientesdomes();
            int cMesPassado = Data.CTCONTAB.clientesMesPassado();
            lblValueClientesMensais.setText(calcularPorcentagem(cMesAtual, cMesPassado));

            lblTrendIndicatorClientesMensais.setText(calcularTendencia(CTCONTAB.clientesSemanaAtual(), CTCONTAB.clientesSemanaPassada()));

            int KPITotalClientes = Data.CTCONTAB.clienteTotalRegis();
            lblKPIValueTotalClientes.setText(String.format("%04d", KPITotalClientes));

            int cTotalAtual = Data.CTCONTAB.clienteTotalRegis();
            int cAnoPassado = Data.CTCONTAB.clientesAnoPassado();
            lblValueTotalClientes.setText(calcularPorcentagem(cTotalAtual, cAnoPassado));

            lblTrendIndicatorTotalClientes.setText(calcularTendencia(CTCONTAB.novosclientesdomes(), CTCONTAB.clientesMesPassado()));

            int KPITarefasPendentes = Data.CTCONTAB.serviçosNaoRealizados();
            lblKPIValueTarefasPendentes.setText(String.format("%04d", KPITarefasPendentes));

            int tPendAtual = Data.CTCONTAB.serviçosNaoRealizados();
            int tPendPassada = Data.CTCONTAB.tarefasSemanaPassada("Em andamento");
            lblValueTarefasPendentes.setText(calcularPorcentagem(tPendAtual, tPendPassada));

            lblTrendIndicatorTarefasPendentes.setText(calcularTendencia(CTCONTAB.tarefasStatusMesAtual("Em andamento"), CTCONTAB.tarefasStatusMesPassado("Em andamento")));

            int KPITotalRelatorios = Data.CTCONTAB.totalRelatorios();
            lblKPIValueTotalRelatorios.setText(String.format("%04d", KPITotalRelatorios));

            int rTotalAtual = Data.CTCONTAB.totalRelatorios();
            int rAnoPassado = Data.CTCONTAB.relatoriosAnoPassado();
            lblValueTotalRelatorios.setText(calcularPorcentagem(rTotalAtual, rAnoPassado));

            lblTrendIndicatorTotalRelatorios.setText(calcularTendencia(CTCONTAB.relatoriosMesAtual(), CTCONTAB.relatoriosMesPassado()));

            int KPITarefasNaoRealizadas = Data.CTCONTAB.tarefaPendentes();
            lblKPIValueTarefasNaoRealizadas.setText(String.format("%04d", KPITarefasNaoRealizadas));

            int tAndAtual = Data.CTCONTAB.tarefaPendentes();
            int tAndPassada = Data.CTCONTAB.tarefasSemanaPassada("Pendente");
            lblValueTarefasNaoRealizadas.setText(calcularPorcentagem(tAndAtual, tAndPassada));

            lblTrendIndicatorTarefasNaoRealizadas.setText(calcularTendencia(CTCONTAB.tarefasStatusMesAtual("Pendente"), CTCONTAB.tarefasStatusMesPassado("Pendente")));

            int KPITarefasFinalizadas = Data.CTCONTAB.serviçosRealizados();
            lblKPIValueTarefasFinalizadas.setText(String.format("%04d", KPITarefasFinalizadas));

            int tConcAtual = Data.CTCONTAB.serviçosRealizados();
            int tConcPassada = Data.CTCONTAB.tarefasSemanaPassada("Concluido");
            lblValueTarefasFinalizadas.setText(calcularPorcentagem(tConcAtual, tConcPassada));

            lblTrendIndicatorTarefasFinalizadas.setText(calcularTendencia(CTCONTAB.tarefasStatusMesAtual("Concluido"), CTCONTAB.tarefasStatusMesPassado("Concluido")));

        } catch (Exception e) {
            System.err.println("Erro ao carregar KPIs: " + e.getMessage());

            lblKPIValueClientesMensais.setText("0000");
            lblKPIValueTotalClientes.setText("0000");
            lblKPIValueTarefasPendentes.setText("0000");
            lblKPIValueTotalRelatorios.setText("0000");
            lblKPIValueTarefasNaoRealizadas.setText("0000");
            lblKPIValueTarefasFinalizadas.setText("0000");

            lblValueClientesMensais.setText("000%");
            lblValueTotalClientes.setText("000%");
            lblValueTarefasPendentes.setText("000%");
            lblValueTotalRelatorios.setText("000%");
            lblValueTarefasNaoRealizadas.setText("000%");
            lblValueTarefasFinalizadas.setText("000%");
        }
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
        lblAdicionarClientes.setText(I18nManager.getString("dashboard.banner.action.register"));
        lblTitleClientesMensais.setText(I18nManager.getString("dashboard.card.new_clients.header"));
        lblTitleTrendClientesMensais.setText(I18nManager.getString("dashboard.card.new_clients.title"));
        lblHeaderClientesMensais.setText(I18nManager.getString("dashboard.card.new_clients.subtitle"));
        lblDescriçãoClientesMensais.setText(I18nManager.getString("dashboard.card.new_clients.description"));
        lblTitleTotalClientes.setText(I18nManager.getString("dashboard.card.total_clients.header"));
        lblTitleTrendTotalClientes.setText(I18nManager.getString("dashboard.card.total_clients.title"));
        lblHeaderTotalClientes.setText(I18nManager.getString("dashboard.card.total_clients.subtitle"));
        lblDescriçãoTotalClientes.setText(I18nManager.getString("dashboard.card.total_clients.description"));
        lblTitleTarefasPendentes.setText(I18nManager.getString("dashboard.card.pending_tasks.header"));
        lblTitleTrendTarefasPendentes.setText(I18nManager.getString("dashboard.card.pending_tasks.title"));
        lblHeaderTarefasPendentes.setText(I18nManager.getString("dashboard.card.pending_tasks.subtitle"));
        lblDescriçãoTarefasPendentes.setText(I18nManager.getString("dashboard.card.pending_tasks.description"));
        lblTitleTotalRelatorios.setText(I18nManager.getString("dashboard.card.total_reports.header"));
        lblTitleTrendTotalRelatorios.setText(I18nManager.getString("dashboard.card.total_reports.title"));
        lblHeaderTotalRelatorios.setText(I18nManager.getString("dashboard.card.total_reports.subtitle"));
        lblDescriçãoTotalRelatorios.setText(I18nManager.getString("dashboard.card.total_reports.description"));
        lblTitleTarefasNaoRealizadas.setText(I18nManager.getString("dashboard.card.unfinished_tasks.header"));
        lblTitleTrendTarefasNaoRealizadas.setText(I18nManager.getString("dashboard.card.unfinished_tasks.title"));
        lblHeaderTarefasNaoRealizadas.setText(I18nManager.getString("dashboard.card.unfinished_tasks.subtitle"));
        lblDescriçãoTarefasNaoRealizadas.setText(I18nManager.getString("dashboard.card.unfinished_tasks.description"));
        lblTitleTarefasFinalizadas.setText(I18nManager.getString("dashboard.card.completed_tasks.header"));
        lblTitleTrendTarefasFinalizadas.setText(I18nManager.getString("dashboard.card.completed_tasks.title"));
        lblHeaderTarefasFinalizadas.setText(I18nManager.getString("dashboard.card.completed_tasks.subtitle"));
        lblDescriçãoTarefasFinalizadas.setText(I18nManager.getString("dashboard.card.completed_tasks.description"));

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

        lblSetaAdicionarClientes = new javax.swing.JLabel();
        lblAdicionarClientes = new javax.swing.JLabel();
        btnAdicionarCliente = new RoundedButton(25);
        lblTituloPagina = new javax.swing.JLabel();
        lblLogoTexto = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblDivisorTela = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        btnAdministracao = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        btnUserIcon = new javax.swing.JButton();
        btnNotificacoes = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();
        btnMaximizarTela = new javax.swing.JButton();
        btnMinimizarTela = new javax.swing.JButton();
        btnFecharTela = new javax.swing.JButton();
        lblBarraSuperior = new javax.swing.JLabel();
        lblBarraLateral = new javax.swing.JLabel();
        lblDescriçãoTarefasFinalizadas = new javax.swing.JLabel();
        pnlCardTarefasFinalizadas = new RoundedPanel(20);
        lblHeaderTarefasFinalizadas = new javax.swing.JLabel();
        lblTitleTarefasFinalizadas = new javax.swing.JLabel();
        lblKPIValueTarefasFinalizadas = new javax.swing.JLabel();
        lblTrendIndicatorTarefasFinalizadas = new javax.swing.JLabel();
        lblTitleTrendTarefasFinalizadas = new javax.swing.JLabel();
        lblIconTarefasFinalizadas = new javax.swing.JLabel();
        pnlBackgroundTarefasFinalizadas = new RoundedPanel(10);
        lblValueTarefasFinalizadas = new javax.swing.JLabel();
        pnlGraficoTarefasFinalizadas = new RoundedPanel(10);
        lblDescriçãoTarefasNaoRealizadas = new javax.swing.JLabel();
        pnlCardTarefasNaoRealizadas = new RoundedPanel(15);
        lblHeaderTarefasNaoRealizadas = new javax.swing.JLabel();
        lblTitleTarefasNaoRealizadas = new javax.swing.JLabel();
        lblKPIValueTarefasNaoRealizadas = new javax.swing.JLabel();
        lblTrendIndicatorTarefasNaoRealizadas = new javax.swing.JLabel();
        lblTitleTrendTarefasNaoRealizadas = new javax.swing.JLabel();
        lblIconTarefasNaoRealizadas = new javax.swing.JLabel();
        pnlBackgroundTarefasNaoRealizadas = new RoundedPanel(10);
        lblValueTarefasNaoRealizadas = new javax.swing.JLabel();
        pnlGraficoTarefasNaoRealizadas = new RoundedPanel(10);
        lblDescriçãoTotalRelatorios = new javax.swing.JLabel();
        pnlCardTotalRelatorios = new RoundedPanel(15);
        lblHeaderTotalRelatorios = new javax.swing.JLabel();
        lblTitleTotalRelatorios = new javax.swing.JLabel();
        lblKPIValueTotalRelatorios = new javax.swing.JLabel();
        lblTrendIndicatorTotalRelatorios = new javax.swing.JLabel();
        lblTitleTrendTotalRelatorios = new javax.swing.JLabel();
        lblIconTotalRelatorios = new javax.swing.JLabel();
        pnlBackgroundTotalRelatorios = new RoundedPanel(10);
        lblValueTotalRelatorios = new javax.swing.JLabel();
        pnlGraficoTotalRelatorios = new RoundedPanel(10);
        lblDescriçãoTarefasPendentes = new javax.swing.JLabel();
        pnlCardTarefasPendentes = new RoundedPanel(15);
        lblHeaderTarefasPendentes = new javax.swing.JLabel();
        lblTitleTarefasPendentes = new javax.swing.JLabel();
        lblKPIValueTarefasPendentes = new javax.swing.JLabel();
        lblTrendIndicatorTarefasPendentes = new javax.swing.JLabel();
        lblTitleTrendTarefasPendentes = new javax.swing.JLabel();
        lblIconTarefasPendentes = new javax.swing.JLabel();
        pnlBackgroundTarefasPendentes = new RoundedPanel(10);
        lblValueTarefasPendentes = new javax.swing.JLabel();
        pnlGraficoTarefasPendentes = new RoundedPanel(10);
        lblDescriçãoTotalClientes = new javax.swing.JLabel();
        pnlCardTotalClientes = new RoundedPanel(15);
        lblHeaderTotalClientes = new javax.swing.JLabel();
        lblTitleTotalClientes = new javax.swing.JLabel();
        lblKPIValueTotalClientes = new javax.swing.JLabel();
        lblTrendIndicatorTotalClientes = new javax.swing.JLabel();
        lblTitleTrendTotalClientes = new javax.swing.JLabel();
        lblIconTotalClientes = new javax.swing.JLabel();
        pnlBackgroundTotalClientes = new RoundedPanel(10);
        lblValueTotalClientes = new javax.swing.JLabel();
        pnlGraficoTotalClientes = new RoundedPanel(10);
        lblDescriçãoClientesMensais = new javax.swing.JLabel();
        pnlCardClientesMensais = new RoundedPanel(15);
        lblValueClientesMensais = new javax.swing.JLabel();
        lblHeaderClientesMensais = new javax.swing.JLabel();
        lblTitleClientesMensais = new javax.swing.JLabel();
        lblTrendIndicatorClientesMensais = new javax.swing.JLabel();
        lblKPIValueClientesMensais = new javax.swing.JLabel();
        lblTitleTrendClientesMensais = new javax.swing.JLabel();
        lblIconClientesMensais = new javax.swing.JLabel();
        lblIconSucessKPIValueClientesMensais = new javax.swing.JLabel();
        lblIconSucessValueClientesMensais = new javax.swing.JLabel();
        pnlBackgroundClientesMensais = new RoundedPanel(10);
        pnlGraficoClienteMensais = new RoundedPanel(10);
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblSetaAdicionarClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSetaAdicionarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Button Icon.png"))); // NOI18N
        getContentPane().add(lblSetaAdicionarClientes);
        lblSetaAdicionarClientes.setBounds(1350, 260, 60, 40);

        lblAdicionarClientes.setFont(FonteUtils.carregarRoboto(22f));
        lblAdicionarClientes.setForeground(new java.awt.Color(255, 255, 255));
        lblAdicionarClientes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAdicionarClientes.setText("CADASTRAR NOVOS CLIENTES");
        getContentPane().add(lblAdicionarClientes);
        lblAdicionarClientes.setBounds(800, 160, 610, 40);

        btnAdicionarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastrar-imagem.jpg"))); // NOI18N
        btnAdicionarCliente.setText("jButton1");
        btnAdicionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarCliente);
        btnAdicionarCliente.setBounds(100, 160, 1320, 150);

        lblTituloPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPagina.setText("DASHBOARD");
        lblTituloPagina.setFont(FonteUtils.carregarRoboto(13f));
        getContentPane().add(lblTituloPagina);
        lblTituloPagina.setBounds(0, 3, 1440, 20);

        lblLogoTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Text Icon.png"))); // NOI18N
        getContentPane().add(lblLogoTexto);
        lblLogoTexto.setBounds(80, 35, 176, 46);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Icon.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(15, 35, 40, 40);

        lblDivisorTela.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDivisorTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        lblDivisorTela.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblDivisorTela.setPreferredSize(new java.awt.Dimension(13, 13));
        getContentPane().add(lblDivisorTela);
        lblDivisorTela.setBounds(1345, 0, 15, 25);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1390, 30, 50, 50);

        btnAdministracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Administrative Icon.png"))); // NOI18N
        btnAdministracao.setContentAreaFilled(false);
        btnAdministracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdministracao);
        btnAdministracao.setBounds(19, 480, 30, 30);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Settings Icon.png"))); // NOI18N
        btnConfiguracoes.setContentAreaFilled(false);
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfiguracoes);
        btnConfiguracoes.setBounds(19, 440, 30, 30);

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

        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Icon.png"))); // NOI18N
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        getContentPane().add(btnDashboard);
        btnDashboard.setBounds(19, 240, 30, 30);

        btnUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Administrative Icon.png"))); // NOI18N
        btnUserIcon.setContentAreaFilled(false);
        btnUserIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserIconActionPerformed(evt);
            }
        });
        getContentPane().add(btnUserIcon);
        btnUserIcon.setBounds(1390, 30, 50, 50);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Notification Bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1340, 35, 40, 40);

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

        lblDescriçãoTarefasFinalizadas.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblDescriçãoTarefasFinalizadas.setForeground(new java.awt.Color(156, 163, 175));
        lblDescriçãoTarefasFinalizadas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriçãoTarefasFinalizadas.setText("<html><p style=\"text-align: justify;\">Indica todas as tarefas já finalizadas, servindo como um termômetro da produtividade e do progresso das atividades.</p></html>");
        lblDescriçãoTarefasFinalizadas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblDescriçãoTarefasFinalizadas);
        lblDescriçãoTarefasFinalizadas.setBounds(1220, 555, 190, 80);

        pnlCardTarefasFinalizadas.setBackground(new java.awt.Color(255, 255, 255, 15));
        pnlCardTarefasFinalizadas.setLayout(null);

        lblHeaderTarefasFinalizadas.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblHeaderTarefasFinalizadas.setForeground(new java.awt.Color(199, 199, 199));
        lblHeaderTarefasFinalizadas.setText("Número de tarefas concluídas.");
        pnlCardTarefasFinalizadas.add(lblHeaderTarefasFinalizadas);
        lblHeaderTarefasFinalizadas.setBounds(10, 210, 200, 16);

        lblTitleTarefasFinalizadas.setFont(FonteUtils.carregarInterExtraBold(14f));
        lblTitleTarefasFinalizadas.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleTarefasFinalizadas.setText("Tarefas Finalizadas");
        pnlCardTarefasFinalizadas.add(lblTitleTarefasFinalizadas);
        lblTitleTarefasFinalizadas.setBounds(10, 5, 190, 20);

        lblKPIValueTarefasFinalizadas.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblKPIValueTarefasFinalizadas.setForeground(new java.awt.Color(255, 255, 255));
        lblKPIValueTarefasFinalizadas.setText("0000");
        pnlCardTarefasFinalizadas.add(lblKPIValueTarefasFinalizadas);
        lblKPIValueTarefasFinalizadas.setBounds(9, 175, 120, 20);

        lblTrendIndicatorTarefasFinalizadas.setFont(FonteUtils.carregarInterSemiBold(12f));
        lblTrendIndicatorTarefasFinalizadas.setForeground(new java.awt.Color(199, 199, 199));
        lblTrendIndicatorTarefasFinalizadas.setText("0.000 %");
        pnlCardTarefasFinalizadas.add(lblTrendIndicatorTarefasFinalizadas);
        lblTrendIndicatorTarefasFinalizadas.setBounds(10, 195, 120, 16);

        lblTitleTrendTarefasFinalizadas.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblTitleTrendTarefasFinalizadas.setForeground(new java.awt.Color(199, 199, 199));
        lblTitleTrendTarefasFinalizadas.setText("Tarefas já finalizadas.");
        pnlCardTarefasFinalizadas.add(lblTitleTrendTarefasFinalizadas);
        lblTitleTrendTarefasFinalizadas.setBounds(10, 155, 200, 16);

        lblIconTarefasFinalizadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlCardTarefasFinalizadas.add(lblIconTarefasFinalizadas);
        lblIconTarefasFinalizadas.setBounds(153, 160, 45, 45);

        pnlBackgroundTarefasFinalizadas.setBackground(new java.awt.Color(58, 255, 92));
        pnlCardTarefasFinalizadas.add(pnlBackgroundTarefasFinalizadas);
        pnlBackgroundTarefasFinalizadas.setBounds(153, 160, 45, 45);

        lblValueTarefasFinalizadas.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblValueTarefasFinalizadas.setForeground(new java.awt.Color(199, 199, 199));
        lblValueTarefasFinalizadas.setText("100%");
        pnlCardTarefasFinalizadas.add(lblValueTarefasFinalizadas);
        lblValueTarefasFinalizadas.setBounds(130, 80, 70, 20);

        pnlGraficoTarefasFinalizadas.setBackground(new java.awt.Color(51, 51, 51));
        pnlCardTarefasFinalizadas.add(pnlGraficoTarefasFinalizadas);
        pnlGraficoTarefasFinalizadas.setBounds(10, 40, 110, 100);

        getContentPane().add(pnlCardTarefasFinalizadas);
        pnlCardTarefasFinalizadas.setBounds(1210, 330, 208, 303);

        lblDescriçãoTarefasNaoRealizadas.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblDescriçãoTarefasNaoRealizadas.setForeground(new java.awt.Color(156, 163, 175));
        lblDescriçãoTarefasNaoRealizadas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriçãoTarefasNaoRealizadas.setText("<html><p style=\"text-align: justify;\">Este painel mostra as tarefas que foram iniciadas, mas não finalizadas, ajudando a identificar gargalos e priorizar ações.</p></html>");
        lblDescriçãoTarefasNaoRealizadas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblDescriçãoTarefasNaoRealizadas);
        lblDescriçãoTarefasNaoRealizadas.setBounds(998, 555, 190, 80);

        pnlCardTarefasNaoRealizadas.setBackground(new java.awt.Color(255, 255, 255, 15));
        pnlCardTarefasNaoRealizadas.setLayout(null);

        lblHeaderTarefasNaoRealizadas.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblHeaderTarefasNaoRealizadas.setForeground(new java.awt.Color(199, 199, 199));
        lblHeaderTarefasNaoRealizadas.setText("Número de tarefas não concluídas.");
        pnlCardTarefasNaoRealizadas.add(lblHeaderTarefasNaoRealizadas);
        lblHeaderTarefasNaoRealizadas.setBounds(10, 210, 200, 16);

        lblTitleTarefasNaoRealizadas.setFont(FonteUtils.carregarInterExtraBold(14f));
        lblTitleTarefasNaoRealizadas.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleTarefasNaoRealizadas.setText("Tarefas Não Realizadas");
        pnlCardTarefasNaoRealizadas.add(lblTitleTarefasNaoRealizadas);
        lblTitleTarefasNaoRealizadas.setBounds(10, 5, 190, 20);

        lblKPIValueTarefasNaoRealizadas.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblKPIValueTarefasNaoRealizadas.setForeground(new java.awt.Color(255, 255, 255));
        lblKPIValueTarefasNaoRealizadas.setText("0000");
        pnlCardTarefasNaoRealizadas.add(lblKPIValueTarefasNaoRealizadas);
        lblKPIValueTarefasNaoRealizadas.setBounds(9, 175, 120, 20);

        lblTrendIndicatorTarefasNaoRealizadas.setFont(FonteUtils.carregarInterSemiBold(12f));
        lblTrendIndicatorTarefasNaoRealizadas.setForeground(new java.awt.Color(199, 199, 199));
        lblTrendIndicatorTarefasNaoRealizadas.setText("0.000 %");
        pnlCardTarefasNaoRealizadas.add(lblTrendIndicatorTarefasNaoRealizadas);
        lblTrendIndicatorTarefasNaoRealizadas.setBounds(10, 195, 120, 16);

        lblTitleTrendTarefasNaoRealizadas.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblTitleTrendTarefasNaoRealizadas.setForeground(new java.awt.Color(199, 199, 199));
        lblTitleTrendTarefasNaoRealizadas.setText("Tarefas não realizadas.");
        pnlCardTarefasNaoRealizadas.add(lblTitleTrendTarefasNaoRealizadas);
        lblTitleTrendTarefasNaoRealizadas.setBounds(10, 155, 200, 16);

        lblIconTarefasNaoRealizadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlCardTarefasNaoRealizadas.add(lblIconTarefasNaoRealizadas);
        lblIconTarefasNaoRealizadas.setBounds(153, 160, 45, 45);

        pnlBackgroundTarefasNaoRealizadas.setBackground(new java.awt.Color(255, 82, 82));
        pnlCardTarefasNaoRealizadas.add(pnlBackgroundTarefasNaoRealizadas);
        pnlBackgroundTarefasNaoRealizadas.setBounds(153, 160, 45, 45);

        lblValueTarefasNaoRealizadas.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblValueTarefasNaoRealizadas.setForeground(new java.awt.Color(199, 199, 199));
        lblValueTarefasNaoRealizadas.setText("100%");
        pnlCardTarefasNaoRealizadas.add(lblValueTarefasNaoRealizadas);
        lblValueTarefasNaoRealizadas.setBounds(130, 80, 70, 20);

        pnlGraficoTarefasNaoRealizadas.setBackground(new java.awt.Color(51, 51, 51));
        pnlCardTarefasNaoRealizadas.add(pnlGraficoTarefasNaoRealizadas);
        pnlGraficoTarefasNaoRealizadas.setBounds(10, 40, 110, 100);

        getContentPane().add(pnlCardTarefasNaoRealizadas);
        pnlCardTarefasNaoRealizadas.setBounds(988, 330, 208, 303);

        lblDescriçãoTotalRelatorios.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblDescriçãoTotalRelatorios.setForeground(new java.awt.Color(156, 163, 175));
        lblDescriçãoTotalRelatorios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriçãoTotalRelatorios.setText("<html><p style=\"text-align: justify;\">Aqui você acompanha quantos relatórios foram emitidos, permitindo analisar o volume de registros e atividades documentadas.</p></html>");
        lblDescriçãoTotalRelatorios.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblDescriçãoTotalRelatorios);
        lblDescriçãoTotalRelatorios.setBounds(776, 555, 190, 80);

        pnlCardTotalRelatorios.setBackground(new java.awt.Color(255, 255, 255, 15));
        pnlCardTotalRelatorios.setLayout(null);

        lblHeaderTotalRelatorios.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblHeaderTotalRelatorios.setForeground(new java.awt.Color(199, 199, 199));
        lblHeaderTotalRelatorios.setText("Número de relatórios gerados.");
        pnlCardTotalRelatorios.add(lblHeaderTotalRelatorios);
        lblHeaderTotalRelatorios.setBounds(10, 210, 200, 16);

        lblTitleTotalRelatorios.setFont(FonteUtils.carregarInterExtraBold(14f));
        lblTitleTotalRelatorios.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleTotalRelatorios.setText("Total de Relatórios");
        pnlCardTotalRelatorios.add(lblTitleTotalRelatorios);
        lblTitleTotalRelatorios.setBounds(10, 5, 190, 20);

        lblKPIValueTotalRelatorios.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblKPIValueTotalRelatorios.setForeground(new java.awt.Color(255, 255, 255));
        lblKPIValueTotalRelatorios.setText("0000");
        pnlCardTotalRelatorios.add(lblKPIValueTotalRelatorios);
        lblKPIValueTotalRelatorios.setBounds(9, 175, 120, 20);

        lblTrendIndicatorTotalRelatorios.setFont(FonteUtils.carregarInterSemiBold(12f));
        lblTrendIndicatorTotalRelatorios.setForeground(new java.awt.Color(199, 199, 199));
        lblTrendIndicatorTotalRelatorios.setText("0.000 %");
        pnlCardTotalRelatorios.add(lblTrendIndicatorTotalRelatorios);
        lblTrendIndicatorTotalRelatorios.setBounds(10, 195, 120, 16);

        lblTitleTrendTotalRelatorios.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblTitleTrendTotalRelatorios.setForeground(new java.awt.Color(199, 199, 199));
        lblTitleTrendTotalRelatorios.setText("Todas os relatórios feitos.");
        pnlCardTotalRelatorios.add(lblTitleTrendTotalRelatorios);
        lblTitleTrendTotalRelatorios.setBounds(10, 155, 200, 16);

        lblIconTotalRelatorios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlCardTotalRelatorios.add(lblIconTotalRelatorios);
        lblIconTotalRelatorios.setBounds(153, 160, 45, 45);

        pnlBackgroundTotalRelatorios.setBackground(new java.awt.Color(186, 104, 200));
        pnlCardTotalRelatorios.add(pnlBackgroundTotalRelatorios);
        pnlBackgroundTotalRelatorios.setBounds(153, 160, 45, 45);

        lblValueTotalRelatorios.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblValueTotalRelatorios.setForeground(new java.awt.Color(199, 199, 199));
        lblValueTotalRelatorios.setText("100%");
        pnlCardTotalRelatorios.add(lblValueTotalRelatorios);
        lblValueTotalRelatorios.setBounds(130, 80, 70, 20);

        pnlGraficoTotalRelatorios.setBackground(new java.awt.Color(51, 51, 51));
        pnlCardTotalRelatorios.add(pnlGraficoTotalRelatorios);
        pnlGraficoTotalRelatorios.setBounds(10, 40, 110, 100);

        getContentPane().add(pnlCardTotalRelatorios);
        pnlCardTotalRelatorios.setBounds(766, 330, 208, 303);

        lblDescriçãoTarefasPendentes.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblDescriçãoTarefasPendentes.setForeground(new java.awt.Color(156, 163, 175));
        lblDescriçãoTarefasPendentes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriçãoTarefasPendentes.setText("<html><p style=\"text-align: justify;\">Este painel indica todas as tarefas que ainda estão pendentes, facilitando o controle das demandas que precisam ser concluídas.</p></html>");
        lblDescriçãoTarefasPendentes.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblDescriçãoTarefasPendentes);
        lblDescriçãoTarefasPendentes.setBounds(554, 555, 190, 80);

        pnlCardTarefasPendentes.setBackground(new java.awt.Color(255, 255, 255, 15));
        pnlCardTarefasPendentes.setLayout(null);

        lblHeaderTarefasPendentes.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblHeaderTarefasPendentes.setForeground(new java.awt.Color(199, 199, 199));
        lblHeaderTarefasPendentes.setText("Número de tarefas em aguardando.");
        pnlCardTarefasPendentes.add(lblHeaderTarefasPendentes);
        lblHeaderTarefasPendentes.setBounds(10, 210, 200, 16);

        lblTitleTarefasPendentes.setFont(FonteUtils.carregarInterExtraBold(14f));
        lblTitleTarefasPendentes.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleTarefasPendentes.setText("Tarefas Pendentes");
        pnlCardTarefasPendentes.add(lblTitleTarefasPendentes);
        lblTitleTarefasPendentes.setBounds(10, 5, 190, 20);

        lblKPIValueTarefasPendentes.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblKPIValueTarefasPendentes.setForeground(new java.awt.Color(255, 255, 255));
        lblKPIValueTarefasPendentes.setText("0000");
        pnlCardTarefasPendentes.add(lblKPIValueTarefasPendentes);
        lblKPIValueTarefasPendentes.setBounds(9, 175, 120, 20);

        lblTrendIndicatorTarefasPendentes.setFont(FonteUtils.carregarInterSemiBold(12f));
        lblTrendIndicatorTarefasPendentes.setForeground(new java.awt.Color(199, 199, 199));
        lblTrendIndicatorTarefasPendentes.setText("0.000 %");
        pnlCardTarefasPendentes.add(lblTrendIndicatorTarefasPendentes);
        lblTrendIndicatorTarefasPendentes.setBounds(10, 195, 120, 16);

        lblTitleTrendTarefasPendentes.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblTitleTrendTarefasPendentes.setForeground(new java.awt.Color(199, 199, 199));
        lblTitleTrendTarefasPendentes.setText("Todas as tarefas pendentes.");
        pnlCardTarefasPendentes.add(lblTitleTrendTarefasPendentes);
        lblTitleTrendTarefasPendentes.setBounds(10, 155, 200, 16);

        lblIconTarefasPendentes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlCardTarefasPendentes.add(lblIconTarefasPendentes);
        lblIconTarefasPendentes.setBounds(153, 160, 45, 45);

        pnlBackgroundTarefasPendentes.setBackground(new java.awt.Color(255, 183, 77));
        pnlCardTarefasPendentes.add(pnlBackgroundTarefasPendentes);
        pnlBackgroundTarefasPendentes.setBounds(153, 160, 45, 45);

        lblValueTarefasPendentes.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblValueTarefasPendentes.setForeground(new java.awt.Color(199, 199, 199));
        lblValueTarefasPendentes.setText("100%");
        pnlCardTarefasPendentes.add(lblValueTarefasPendentes);
        lblValueTarefasPendentes.setBounds(130, 80, 70, 20);

        pnlGraficoTarefasPendentes.setBackground(new java.awt.Color(51, 51, 51));
        pnlCardTarefasPendentes.add(pnlGraficoTarefasPendentes);
        pnlGraficoTarefasPendentes.setBounds(10, 40, 110, 100);

        getContentPane().add(pnlCardTarefasPendentes);
        pnlCardTarefasPendentes.setBounds(544, 330, 208, 303);

        lblDescriçãoTotalClientes.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblDescriçãoTotalClientes.setForeground(new java.awt.Color(156, 163, 175));
        lblDescriçãoTotalClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriçãoTotalClientes.setText("<html><p style=\"text-align: justify;\">Este painel mostra o total acumulado de clientes cadastrados, permitindo acompanhar o crescimento da base ao longo do tempo.</p></html>");
        lblDescriçãoTotalClientes.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblDescriçãoTotalClientes);
        lblDescriçãoTotalClientes.setBounds(332, 555, 190, 80);

        pnlCardTotalClientes.setBackground(new java.awt.Color(255, 255, 255, 15));
        pnlCardTotalClientes.setLayout(null);

        lblHeaderTotalClientes.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblHeaderTotalClientes.setForeground(new java.awt.Color(199, 199, 199));
        lblHeaderTotalClientes.setText("Número de clientes registrados.");
        pnlCardTotalClientes.add(lblHeaderTotalClientes);
        lblHeaderTotalClientes.setBounds(10, 210, 200, 16);

        lblTitleTotalClientes.setFont(FonteUtils.carregarInterExtraBold(14f));
        lblTitleTotalClientes.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleTotalClientes.setText("Total de Clientes");
        pnlCardTotalClientes.add(lblTitleTotalClientes);
        lblTitleTotalClientes.setBounds(10, 5, 190, 20);

        lblKPIValueTotalClientes.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblKPIValueTotalClientes.setForeground(new java.awt.Color(255, 255, 255));
        lblKPIValueTotalClientes.setText("0000");
        pnlCardTotalClientes.add(lblKPIValueTotalClientes);
        lblKPIValueTotalClientes.setBounds(9, 175, 120, 20);

        lblTrendIndicatorTotalClientes.setFont(FonteUtils.carregarInterSemiBold(12f));
        lblTrendIndicatorTotalClientes.setForeground(new java.awt.Color(199, 199, 199));
        lblTrendIndicatorTotalClientes.setText("0.000 %");
        pnlCardTotalClientes.add(lblTrendIndicatorTotalClientes);
        lblTrendIndicatorTotalClientes.setBounds(10, 195, 120, 16);

        lblTitleTrendTotalClientes.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblTitleTrendTotalClientes.setForeground(new java.awt.Color(199, 199, 199));
        lblTitleTrendTotalClientes.setText("Todos clientes registrados.");
        pnlCardTotalClientes.add(lblTitleTrendTotalClientes);
        lblTitleTrendTotalClientes.setBounds(10, 155, 200, 16);

        lblIconTotalClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlCardTotalClientes.add(lblIconTotalClientes);
        lblIconTotalClientes.setBounds(153, 160, 45, 45);

        pnlBackgroundTotalClientes.setBackground(new java.awt.Color(79, 172, 255));
        pnlCardTotalClientes.add(pnlBackgroundTotalClientes);
        pnlBackgroundTotalClientes.setBounds(153, 160, 45, 45);

        lblValueTotalClientes.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblValueTotalClientes.setForeground(new java.awt.Color(199, 199, 199));
        lblValueTotalClientes.setText("100%");
        pnlCardTotalClientes.add(lblValueTotalClientes);
        lblValueTotalClientes.setBounds(130, 80, 70, 20);

        pnlGraficoTotalClientes.setBackground(new java.awt.Color(51, 51, 51));
        pnlCardTotalClientes.add(pnlGraficoTotalClientes);
        pnlGraficoTotalClientes.setBounds(10, 40, 110, 100);

        getContentPane().add(pnlCardTotalClientes);
        pnlCardTotalClientes.setBounds(322, 330, 208, 303);

        lblDescriçãoClientesMensais.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblDescriçãoClientesMensais.setForeground(new java.awt.Color(156, 163, 175));
        lblDescriçãoClientesMensais.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDescriçãoClientesMensais.setText("<html><p style=\"text-align: justify;\">Este painel mostra a quantidade total de novos clientes que adquirimos no mês, permitindo acompanhar o crescimento do nosso público.</p></html>");
        lblDescriçãoClientesMensais.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblDescriçãoClientesMensais);
        lblDescriçãoClientesMensais.setBounds(110, 555, 190, 80);

        pnlCardClientesMensais.setBackground(new java.awt.Color(255, 255, 255, 15));
        pnlCardClientesMensais.setLayout(null);

        lblValueClientesMensais.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblValueClientesMensais.setForeground(new java.awt.Color(199, 199, 199));
        lblValueClientesMensais.setText("100%");
        pnlCardClientesMensais.add(lblValueClientesMensais);
        lblValueClientesMensais.setBounds(130, 80, 70, 20);

        lblHeaderClientesMensais.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblHeaderClientesMensais.setForeground(new java.awt.Color(199, 199, 199));
        lblHeaderClientesMensais.setText("Número de novos clientes.");
        pnlCardClientesMensais.add(lblHeaderClientesMensais);
        lblHeaderClientesMensais.setBounds(10, 210, 200, 16);

        lblTitleClientesMensais.setFont(FonteUtils.carregarInterExtraBold(14f));
        lblTitleClientesMensais.setForeground(new java.awt.Color(255, 255, 255));
        lblTitleClientesMensais.setText("Clientes Mensais");
        pnlCardClientesMensais.add(lblTitleClientesMensais);
        lblTitleClientesMensais.setBounds(10, 5, 190, 20);

        lblTrendIndicatorClientesMensais.setFont(FonteUtils.carregarInterSemiBold(12f));
        lblTrendIndicatorClientesMensais.setForeground(new java.awt.Color(199, 199, 199));
        lblTrendIndicatorClientesMensais.setText("0.000 %");
        pnlCardClientesMensais.add(lblTrendIndicatorClientesMensais);
        lblTrendIndicatorClientesMensais.setBounds(10, 195, 120, 16);

        lblKPIValueClientesMensais.setFont(FonteUtils.carregarInterSemiBold(22f));
        lblKPIValueClientesMensais.setForeground(new java.awt.Color(255, 255, 255));
        lblKPIValueClientesMensais.setText("0000");
        pnlCardClientesMensais.add(lblKPIValueClientesMensais);
        lblKPIValueClientesMensais.setBounds(9, 175, 120, 20);

        lblTitleTrendClientesMensais.setFont(FonteUtils.carregarInterSemiBold(11f));
        lblTitleTrendClientesMensais.setForeground(new java.awt.Color(199, 199, 199));
        lblTitleTrendClientesMensais.setText("Novos clientes nesse mês.");
        pnlCardClientesMensais.add(lblTitleTrendClientesMensais);
        lblTitleTrendClientesMensais.setBounds(10, 155, 200, 16);

        lblIconClientesMensais.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlCardClientesMensais.add(lblIconClientesMensais);
        lblIconClientesMensais.setBounds(153, 160, 45, 45);
        pnlCardClientesMensais.add(lblIconSucessKPIValueClientesMensais);
        lblIconSucessKPIValueClientesMensais.setBounds(180, 80, 15, 15);
        pnlCardClientesMensais.add(lblIconSucessValueClientesMensais);
        lblIconSucessValueClientesMensais.setBounds(60, 195, 15, 15);

        pnlBackgroundClientesMensais.setBackground(new java.awt.Color(0, 217, 255));
        pnlCardClientesMensais.add(pnlBackgroundClientesMensais);
        pnlBackgroundClientesMensais.setBounds(153, 160, 45, 45);

        pnlGraficoClienteMensais.setBackground(new java.awt.Color(51, 51, 51));
        pnlCardClientesMensais.add(pnlGraficoClienteMensais);
        pnlGraficoClienteMensais.setBounds(10, 40, 110, 100);

        getContentPane().add(pnlCardClientesMensais);
        pnlCardClientesMensais.setBounds(100, 330, 208, 303);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Background.png"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1450, 750);

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

    private void btnMaximizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaximizarTelaActionPerformed

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        new TelaConfiguracao(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConfiguracoesActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInfoActionPerformed

    private void btnMinimizarTelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarTelaMouseClicked
        setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarTelaMouseClicked

    private void btnMinimizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMinimizarTelaActionPerformed

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosActionPerformed
        new TelaRelatorioTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRelatoriosActionPerformed

    private void btnTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarefasActionPerformed
        new TelaTarefaTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTarefasActionPerformed

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        new TelaEventoTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCalendarioActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        new TelaClienteTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnUserIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserIconActionPerformed
        new TelaConfiguracao(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUserIconActionPerformed

    private void btnFecharTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharTelaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnFecharTelaActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        new Dashboard(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnAdministracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracaoActionPerformed
        new TelaAdminTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdministracaoActionPerformed

    private void btnAdicionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarClienteActionPerformed
        TelaAdicionarCliente popup = new TelaAdicionarCliente(this, usuarioLogado);
        popup.setVisible(true);
    }//GEN-LAST:event_btnAdicionarClienteActionPerformed

    private void btnFecharTelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharTelaMouseEntered
    }//GEN-LAST:event_btnFecharTelaMouseEntered

    private void btnFecharTelaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFecharTelaMouseExited
    }//GEN-LAST:event_btnFecharTelaMouseExited

    private void btnMinimizarTelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarTelaMouseEntered
    }//GEN-LAST:event_btnMinimizarTelaMouseEntered

    private void btnMinimizarTelaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarTelaMouseExited
    }//GEN-LAST:event_btnMinimizarTelaMouseExited

    private void btnMaximizarTelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizarTelaMouseEntered
    }//GEN-LAST:event_btnMaximizarTelaMouseEntered

    private void btnMaximizarTelaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizarTelaMouseExited
    }//GEN-LAST:event_btnMaximizarTelaMouseExited

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
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
    private javax.swing.JButton btnAdicionarCliente;
    private javax.swing.JButton btnAdministracao;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnClientes;
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
    private javax.swing.JLabel lblAdicionarClientes;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBarraLateral;
    private javax.swing.JLabel lblBarraSuperior;
    private javax.swing.JLabel lblDescriçãoClientesMensais;
    private javax.swing.JLabel lblDescriçãoTarefasFinalizadas;
    private javax.swing.JLabel lblDescriçãoTarefasNaoRealizadas;
    private javax.swing.JLabel lblDescriçãoTarefasPendentes;
    private javax.swing.JLabel lblDescriçãoTotalClientes;
    private javax.swing.JLabel lblDescriçãoTotalRelatorios;
    private javax.swing.JLabel lblDivisorTela;
    private javax.swing.JLabel lblHeaderClientesMensais;
    private javax.swing.JLabel lblHeaderTarefasFinalizadas;
    private javax.swing.JLabel lblHeaderTarefasNaoRealizadas;
    private javax.swing.JLabel lblHeaderTarefasPendentes;
    private javax.swing.JLabel lblHeaderTotalClientes;
    private javax.swing.JLabel lblHeaderTotalRelatorios;
    private javax.swing.JLabel lblIconClientesMensais;
    private javax.swing.JLabel lblIconSucessKPIValueClientesMensais;
    private javax.swing.JLabel lblIconSucessValueClientesMensais;
    private javax.swing.JLabel lblIconTarefasFinalizadas;
    private javax.swing.JLabel lblIconTarefasNaoRealizadas;
    private javax.swing.JLabel lblIconTarefasPendentes;
    private javax.swing.JLabel lblIconTotalClientes;
    private javax.swing.JLabel lblIconTotalRelatorios;
    private javax.swing.JLabel lblKPIValueClientesMensais;
    private javax.swing.JLabel lblKPIValueTarefasFinalizadas;
    private javax.swing.JLabel lblKPIValueTarefasNaoRealizadas;
    private javax.swing.JLabel lblKPIValueTarefasPendentes;
    private javax.swing.JLabel lblKPIValueTotalClientes;
    private javax.swing.JLabel lblKPIValueTotalRelatorios;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoTexto;
    private javax.swing.JLabel lblSetaAdicionarClientes;
    private javax.swing.JLabel lblTitleClientesMensais;
    private javax.swing.JLabel lblTitleTarefasFinalizadas;
    private javax.swing.JLabel lblTitleTarefasNaoRealizadas;
    private javax.swing.JLabel lblTitleTarefasPendentes;
    private javax.swing.JLabel lblTitleTotalClientes;
    private javax.swing.JLabel lblTitleTotalRelatorios;
    private javax.swing.JLabel lblTitleTrendClientesMensais;
    private javax.swing.JLabel lblTitleTrendTarefasFinalizadas;
    private javax.swing.JLabel lblTitleTrendTarefasNaoRealizadas;
    private javax.swing.JLabel lblTitleTrendTarefasPendentes;
    private javax.swing.JLabel lblTitleTrendTotalClientes;
    private javax.swing.JLabel lblTitleTrendTotalRelatorios;
    private javax.swing.JLabel lblTituloPagina;
    private javax.swing.JLabel lblTrendIndicatorClientesMensais;
    private javax.swing.JLabel lblTrendIndicatorTarefasFinalizadas;
    private javax.swing.JLabel lblTrendIndicatorTarefasNaoRealizadas;
    private javax.swing.JLabel lblTrendIndicatorTarefasPendentes;
    private javax.swing.JLabel lblTrendIndicatorTotalClientes;
    private javax.swing.JLabel lblTrendIndicatorTotalRelatorios;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JLabel lblValueClientesMensais;
    private javax.swing.JLabel lblValueTarefasFinalizadas;
    private javax.swing.JLabel lblValueTarefasNaoRealizadas;
    private javax.swing.JLabel lblValueTarefasPendentes;
    private javax.swing.JLabel lblValueTotalClientes;
    private javax.swing.JLabel lblValueTotalRelatorios;
    private javax.swing.JPanel pnlBackgroundClientesMensais;
    private javax.swing.JPanel pnlBackgroundTarefasFinalizadas;
    private javax.swing.JPanel pnlBackgroundTarefasNaoRealizadas;
    private javax.swing.JPanel pnlBackgroundTarefasPendentes;
    private javax.swing.JPanel pnlBackgroundTotalClientes;
    private javax.swing.JPanel pnlBackgroundTotalRelatorios;
    private javax.swing.JPanel pnlCardClientesMensais;
    private javax.swing.JPanel pnlCardTarefasFinalizadas;
    private javax.swing.JPanel pnlCardTarefasNaoRealizadas;
    private javax.swing.JPanel pnlCardTarefasPendentes;
    private javax.swing.JPanel pnlCardTotalClientes;
    private javax.swing.JPanel pnlCardTotalRelatorios;
    private javax.swing.JPanel pnlGraficoClienteMensais;
    private javax.swing.JPanel pnlGraficoTarefasFinalizadas;
    private javax.swing.JPanel pnlGraficoTarefasNaoRealizadas;
    private javax.swing.JPanel pnlGraficoTarefasPendentes;
    private javax.swing.JPanel pnlGraficoTotalClientes;
    private javax.swing.JPanel pnlGraficoTotalRelatorios;
    // End of variables declaration//GEN-END:variables
}
