package screen;

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
import java.awt.FontMetrics;

public class Calendar extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private final Color COR_PADRAO = new Color(28, 37, 54);
    private final Color COR_HOVER_GERAL = new Color(50, 63, 89);
    private final Color COR_HOVER_FECHAR = new Color(196, 43, 28);
    private final Color COR_NORMAL_BTN = new Color(72, 79, 82);
    private final Color COR_HOVER_BTN = new Color(205, 168, 12);
    private javax.swing.Timer timerVoltarHome;
    private javax.swing.Timer timerExpandir;
    private final int LARGURA_MIN = 30;
    private int LARGURA_MAX = 114;
    private final Color COR_FUNDO_AREA = new Color(37, 49, 71);
    private final Color COR_CELULA = new Color(28, 37, 54);
    private final Color COR_TEXTO = new Color(209, 213, 219);
    private final Color COR_EVENTO = new Color(0xDD, 0xA9, 0x2A);
    private final Color COR_EVENTO_TEXTO = new Color(0, 0, 0);
    private final Color COR_HOJE = new Color(0xDD, 0xA9, 0x2A);
    private CalendarioPanel painelCalendario;
    private java.time.LocalDate mesAtual = java.time.LocalDate.now().withDayOfMonth(1);
    private int ticketCarga = 0;
    private javax.swing.Timer timerAtualizacao;

    public Calendar(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        setUndecorated(true);
        setIcon();
        aplicarImagensDeAltaQualidade();
        localizeAuthScreen();
        filtrarSidebar();
        configurarBotaoAnimado();
        IconUtil.setIcon(usuarioLogado, lblUsuarioIcon, 30, 30);
        setBackground(new java.awt.Color(0, 0, 0, 0));
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        pnlButtonEllipse.setVisible(false);
        arrowBackgroundButton.setVisible(false);
        lblUsuarioName.setText(usuarioLogado.getUsuario());

        timerVoltarHome = new javax.swing.Timer(300, e -> {
            if (txtBuscar.getText().trim().isEmpty()) {
                pnlBarButtonCalendar.setVisible(true);
                pnlButtonCalendar.setVisible(true);
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

        configurarCalendario();
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
            BufferedImage PlusIcon = ImageIO.read(getClass().getResource("/images/Plus Icon.png"));
            BufferedImage PlusIconBlack = ImageIO.read(getClass().getResource("/images/Plus Icon Black.png"));
            BufferedImage LeftArrow = ImageIO.read(getClass().getResource("/images/Left Arrow Icon.png"));
            BufferedImage YellowRightArrow = ImageIO.read(getClass().getResource("/images/Yellow Arrow Right Icon.png"));
            BufferedImage YellowLeftArrow = ImageIO.read(getClass().getResource("/images/Yellow Arrow Left Icon.png"));

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
            PlusIconLabel.setIcon(gerarIconePerfeito(PlusIcon, 12, 12));
            PlusBlackIconLabel.setIcon(gerarIconePerfeito(PlusIconBlack, 12, 12));
            arrowBackButton.setIcon(gerarIconePerfeito(LeftArrow, 12, 12));
            TipIconLabel1.setIcon(gerarIconePerfeito(YellowLeftArrow, 12, 12));
            TipIconLabel2.setIcon(gerarIconePerfeito(YellowRightArrow, 12, 12));

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

    private static final int TEXTO_DIREITA_FIXA = 1222;
    private static final int BOTAO_PADDING_ESQUERDO = 20;
    private static final int BOTAO_LARGURA_MIN_EXPANDIDO = 114;

    private void ajustarLarguraBotaoNovoCliente() {
        String texto = lblTextoNovoCliente.getText();
        FontMetrics fm = lblTextoNovoCliente.getFontMetrics(lblTextoNovoCliente.getFont());
        int larguraTexto = fm.stringWidth(texto);

        int novoXTexto = TEXTO_DIREITA_FIXA - (larguraTexto + 4);
        lblTextoNovoCliente.setBounds(novoXTexto, lblTextoNovoCliente.getY(),
                larguraTexto + 4, lblTextoNovoCliente.getHeight());

        int paddingDireitoIcone = (1221 + LARGURA_MIN) - TEXTO_DIREITA_FIXA;
        int larguraNecessaria = paddingDireitoIcone + larguraTexto + 4 + BOTAO_PADDING_ESQUERDO;
        LARGURA_MAX = Math.max(BOTAO_LARGURA_MIN_EXPANDIDO, larguraNecessaria);
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
        lblTitleBanner.setText(I18nManager.getString("auth.calendar.title"));
        lblTextoNovoCliente.setText(I18nManager.getString("auth.calendar.label.new_event_text"));
        ajustarLarguraBotaoNovoCliente();
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

                if (btn == btnCalendar && !estaBuscando) {
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

    private void configurarBotaoAnimado() {
        NewClientButtonPanel.setBounds(NewClientButtonPanel.getX(), NewClientButtonPanel.getY(), LARGURA_MIN, 30);
        NewClientButtonPanel.setBackground(COR_NORMAL_BTN);
        lblTextoNovoCliente.setVisible(false);
        PlusBlackIconLabel.setVisible(false);

        NewClientButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                animarBotao(true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                animarBotao(false);
            }
        });
    }

    private void animarBotao(boolean expandir) {
        if (timerExpandir != null && timerExpandir.isRunning()) {
            timerExpandir.stop();
        }

        final int X_FIXO_DIREITA = 1221 + LARGURA_MIN;

        timerExpandir = new javax.swing.Timer(10, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int larguraAtual = NewClientButtonPanel.getWidth();
                int velocidade = 6;
                int novaLargura;

                if (expandir) {
                    if (larguraAtual < LARGURA_MAX) {
                        novaLargura = Math.min(larguraAtual + velocidade, LARGURA_MAX);
                        NewClientButtonPanel.setBounds(X_FIXO_DIREITA - novaLargura, NewClientButtonPanel.getY(), novaLargura, 30);
                        NewClientButtonPanel.setBackground(COR_HOVER_BTN);

                        if (novaLargura > 80) {
                            lblTextoNovoCliente.setVisible(true);
                            PlusBlackIconLabel.setVisible(true);
                            PlusIconLabel.setVisible(false);
                        }
                    } else {
                        timerExpandir.stop();
                    }
                } else {
                    if (larguraAtual > LARGURA_MIN) {
                        novaLargura = Math.max(larguraAtual - velocidade, LARGURA_MIN);
                        NewClientButtonPanel.setBounds(X_FIXO_DIREITA - novaLargura, NewClientButtonPanel.getY(), novaLargura, 30);
                        NewClientButtonPanel.setBackground(COR_NORMAL_BTN);

                        if (novaLargura < 80) {
                            lblTextoNovoCliente.setVisible(false);
                            PlusBlackIconLabel.setVisible(false);
                            PlusIconLabel.setVisible(true);
                        }
                    } else {
                        timerExpandir.stop();
                    }
                }
                NewClientButtonPanel.revalidate();
                NewClientButtonPanel.repaint();
            }
        });
        timerExpandir.start();
    }

    private String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return "";
        }
        return texto.substring(0, 1).toUpperCase(I18nManager.getCurrentLocale()) + texto.substring(1);
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
    
    private void irParaNovoEvento() {
        new NewEvent(usuarioLogado).setVisible(true);
        this.dispose();
    }

    private void configurarCalendario() {
        painelCalendario = new CalendarioPanel();
        painelCalendario.setPreferredSize(new Dimension(976, jScrollPane1.getHeight()));

        jScrollPane1.setViewportView(painelCalendario);
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.getViewport().setBackground(COR_FUNDO_AREA);
        jScrollPane1.setVisible(true);

        TimeZoneSubTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        configurarSetaMes(TipIconLabel1, -1);
        configurarSetaMes(TipIconLabel2, 1);

        atualizarMes();

        timerAtualizacao = new javax.swing.Timer(60000, e -> {
            painelCalendario.setHoje(java.time.LocalDate.now());
            carregarEventos();
        });
        timerAtualizacao.start();
    }

    private void configurarSetaMes(javax.swing.JLabel seta, int deslocamento) {
        seta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        seta.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        seta.setBounds(seta.getX() - 6, seta.getY() - 6, seta.getWidth() + 12, seta.getHeight() + 12);
        seta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        seta.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mesAtual = mesAtual.plusMonths(deslocamento);
                atualizarMes();
            }
        });
    }

    private void atualizarMes() {
        Locale loc = I18nManager.getCurrentLocale();
        String nomeMes = capitalizar(mesAtual.getMonth().getDisplayName(java.time.format.TextStyle.FULL, loc));
        TimeZoneSubTitleLabel.setText(nomeMes + ", " + mesAtual.getYear());

        painelCalendario.setMes(mesAtual);
        painelCalendario.setHoje(java.time.LocalDate.now());
        carregarEventos();
    }

    private void carregarEventos() {
        final java.time.LocalDate inicio = painelCalendario.inicioDaGrade();
        final java.time.LocalDate fim = painelCalendario.fimDaGrade();
        final int ticket = ++ticketCarga;

        new javax.swing.SwingWorker<java.util.List<Data.Evento>, Void>() {
            @Override
            protected java.util.List<Data.Evento> doInBackground() throws Exception {
                return Data.CTCONTAB.listarEventosPeriodo(inicio.toString(), fim.toString());
            }

            @Override
            protected void done() {
                if (ticket != ticketCarga) {
                    return;
                }
                try {
                    painelCalendario.setEventos(get());
                } catch (Exception e) {
                    System.err.println("Erro ao carregar eventos: " + e.getMessage());
                }
            }
        }.execute();
    }

    private class CalendarioPanel extends javax.swing.JPanel {

        private static final int MARGEM = 12;
        private static final int MARGEM_ESQUERDA = 0;
        private static final int ESPACO = 8;
        private static final int ALTURA_CABECALHO = 30;
        private static final int ALTURA_MAXIMA_CELULA = 90;
        private static final int ALTURA_EVENTO = 14;

        private java.time.LocalDate mes = java.time.LocalDate.now().withDayOfMonth(1);
        private java.time.LocalDate hoje = java.time.LocalDate.now();
        private java.util.Map<java.time.LocalDate, java.util.List<Data.Evento>> eventosPorDia = new java.util.HashMap<>();
        private int larguraCelula;
        private int alturaCelula;

        CalendarioPanel() {
            setBackground(COR_FUNDO_AREA);
            setOpaque(true);
            setToolTipText("");
        }

        void setMes(java.time.LocalDate novoMes) {
            this.mes = novoMes.withDayOfMonth(1);
            this.eventosPorDia = new java.util.HashMap<>();
            repaint();
        }

        void setHoje(java.time.LocalDate dia) {
            if (!dia.equals(this.hoje)) {
                this.hoje = dia;
                repaint();
            }
        }

        java.time.LocalDate inicioDaGrade() {
            return mes.minusDays(mes.getDayOfWeek().getValue() % 7);
        }

        int quantidadeDeLinhas() {
            int deslocamento = mes.getDayOfWeek().getValue() % 7;
            return (int) Math.ceil((deslocamento + mes.lengthOfMonth()) / 7.0);
        }

        java.time.LocalDate fimDaGrade() {
            return inicioDaGrade().plusDays(quantidadeDeLinhas() * 7L - 1);
        }

        void setEventos(java.util.List<Data.Evento> lista) {
            java.util.Map<java.time.LocalDate, java.util.List<Data.Evento>> mapa = new java.util.HashMap<>();
            java.time.LocalDate limiteInicio = inicioDaGrade();
            java.time.LocalDate limiteFim = fimDaGrade();

            for (Data.Evento evento : lista) {
                try {
                    java.time.LocalDate inicio = java.time.LocalDate.parse(evento.getDataInicio());
                    java.time.LocalDate fim = evento.getDataFinal() == null ? inicio : java.time.LocalDate.parse(evento.getDataFinal());
                    java.time.LocalDate dia = inicio.isBefore(limiteInicio) ? limiteInicio : inicio;
                    java.time.LocalDate ultimo = fim.isAfter(limiteFim) ? limiteFim : fim;
                    while (!dia.isAfter(ultimo)) {
                        mapa.computeIfAbsent(dia, k -> new java.util.ArrayList<>()).add(evento);
                        dia = dia.plusDays(1);
                    }
                } catch (Exception e) {
                    System.err.println("Evento com data inválida ignorado: " + evento.getEvento());
                }
            }
            this.eventosPorDia = mapa;
            repaint();
        }

        private void calcularGeometria() {
            larguraCelula = (getWidth() - MARGEM_ESQUERDA - MARGEM - 6 * ESPACO) / 7;
            int linhas = quantidadeDeLinhas();
            int alturaDisponivel = getHeight() - ALTURA_CABECALHO - MARGEM - (linhas - 1) * ESPACO;
            alturaCelula = Math.min(ALTURA_MAXIMA_CELULA, alturaDisponivel / linhas);
        }

        private int xDaColuna(int coluna) {
            return MARGEM_ESQUERDA + coluna * (larguraCelula + ESPACO);
        }

        private int yDaLinha(int linha) {
            return ALTURA_CABECALHO + linha * (alturaCelula + ESPACO);
        }

        private String textoDoEvento(Data.Evento evento) {
            String horario = evento.getHorarioInicial();
            return (horario == null || horario.isEmpty()) ? evento.getEvento() : evento.getEvento() + " - " + horario;
        }

        private String ajustarTexto(String texto, java.awt.FontMetrics fm, int larguraMaxima) {
            if (fm.stringWidth(texto) <= larguraMaxima) {
                return texto;
            }
            String reticencias = "...";
            int fim = texto.length();
            while (fim > 0 && fm.stringWidth(texto.substring(0, fim) + reticencias) > larguraMaxima) {
                fim--;
            }
            return texto.substring(0, fim) + reticencias;
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            calcularGeometria();
            Locale loc = I18nManager.getCurrentLocale();

            java.awt.Font fonteCabecalho = FonteUtils.carregarRobotoRegular(11f);
            java.awt.Font fonteNumero = FonteUtils.carregarRobotoRegular(11f);
            java.awt.Font fonteEvento = FonteUtils.carregarRobotoRegular(9.5f);

            g2.setFont(fonteCabecalho);
            g2.setColor(COR_TEXTO);
            java.awt.FontMetrics fmCabecalho = g2.getFontMetrics();
            java.time.DayOfWeek[] semana = {java.time.DayOfWeek.SUNDAY, java.time.DayOfWeek.MONDAY, java.time.DayOfWeek.TUESDAY,
                java.time.DayOfWeek.WEDNESDAY, java.time.DayOfWeek.THURSDAY, java.time.DayOfWeek.FRIDAY, java.time.DayOfWeek.SATURDAY};
            for (int coluna = 0; coluna < 7; coluna++) {
                String nome = capitalizar(semana[coluna].getDisplayName(java.time.format.TextStyle.FULL, loc));
                int x = xDaColuna(coluna) + (larguraCelula - fmCabecalho.stringWidth(nome)) / 2;
                g2.drawString(nome, x, ALTURA_CABECALHO - 12);
            }

            java.time.LocalDate dia = inicioDaGrade();
            int linhas = quantidadeDeLinhas();
            int capacidade = Math.max(0, (alturaCelula - 24) / (ALTURA_EVENTO + 2));

            for (int linha = 0; linha < linhas; linha++) {
                for (int coluna = 0; coluna < 7; coluna++) {
                    int x = xDaColuna(coluna);
                    int y = yDaLinha(linha);

                    g2.setColor(COR_CELULA);
                    g2.fillRoundRect(x, y, larguraCelula, alturaCelula, 10, 10);

                    g2.setFont(fonteNumero);
                    g2.setColor(COR_TEXTO);
                    g2.drawString(String.valueOf(dia.getDayOfMonth()), x + 8, y + 16);

                    java.util.List<Data.Evento> doDia = eventosPorDia.get(dia);
                    if (doDia != null && capacidade > 0) {
                        g2.setFont(fonteEvento);
                        java.awt.FontMetrics fmEvento = g2.getFontMetrics();
                        boolean excede = doDia.size() > capacidade;
                        int visiveis = excede ? capacidade - 1 : doDia.size();
                        int yEvento = y + 22;

                        for (int i = 0; i < visiveis; i++) {
                            g2.setColor(COR_EVENTO);
                            g2.fillRoundRect(x + 4, yEvento, larguraCelula - 8, ALTURA_EVENTO, 5, 5);
                            g2.setColor(COR_EVENTO_TEXTO);
                            String texto = ajustarTexto(textoDoEvento(doDia.get(i)), fmEvento, larguraCelula - 8 - 8);
                            double alturaLetraAlta = fonteEvento.createGlyphVector(g2.getFontRenderContext(), "Hl").getVisualBounds().getHeight();
                            int linhaBase = (int) Math.round(yEvento + ALTURA_EVENTO / 2.0 + alturaLetraAlta / 2.0);
                            g2.drawString(texto, x + 8, linhaBase);
                            yEvento += ALTURA_EVENTO + 2;
                        }

                        if (excede) {
                            g2.setColor(COR_TEXTO);
                            String mais = String.format(I18nManager.getString("auth.calendar.more_events"), doDia.size() - visiveis);
                            g2.drawString(mais, x + 8, yEvento + (ALTURA_EVENTO + fmEvento.getAscent()) / 2 - 2);
                        }
                    }

                    if (dia.getMonthValue() != mes.getMonthValue() || dia.getYear() != mes.getYear()) {
                        g2.setColor(new Color(0, 0, 0, 115));
                        g2.fillRoundRect(x, y, larguraCelula, alturaCelula, 10, 10);
                    }

                    if (dia.equals(hoje)) {
                        g2.setColor(COR_HOJE);
                        g2.setStroke(new java.awt.BasicStroke(1f));
                        g2.draw(new RoundRectangle2D.Float(x + 0.5f, y + 0.5f, larguraCelula - 1f, alturaCelula - 1f, 10, 10));
                    }

                    dia = dia.plusDays(1);
                }
            }
            g2.dispose();
        }

        @Override
        public String getToolTipText(java.awt.event.MouseEvent evt) {
            calcularGeometria();
            int linhas = quantidadeDeLinhas();
            for (int linha = 0; linha < linhas; linha++) {
                for (int coluna = 0; coluna < 7; coluna++) {
                    int x = xDaColuna(coluna);
                    int y = yDaLinha(linha);
                    if (evt.getX() >= x && evt.getX() <= x + larguraCelula && evt.getY() >= y && evt.getY() <= y + alturaCelula) {
                        java.time.LocalDate dia = inicioDaGrade().plusDays(linha * 7L + coluna);
                        java.util.List<Data.Evento> doDia = eventosPorDia.get(dia);
                        if (doDia == null || doDia.isEmpty()) {
                            return null;
                        }
                        StringBuilder html = new StringBuilder("<html><b>");
                        html.append(dia.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("</b>");
                        for (Data.Evento evento : doDia) {
                            html.append("<br>&bull; ").append(escaparHtml(textoDoEvento(evento)));
                            if (evento.getUsuario() != null && !evento.getUsuario().isEmpty()) {
                                html.append(" (").append(escaparHtml(evento.getUsuario())).append(")");
                            }
                        }
                        return html.append("</html>").toString();
                    }
                }
            }
            return null;
        }

        private String escaparHtml(String texto) {
            return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TipIconLabel2 = new javax.swing.JLabel();
        TipIconLabel1 = new javax.swing.JLabel();
        TimeZoneSubTitleLabel = new javax.swing.JLabel();
        EditInfoPanel1 = new javax.swing.JPanel();
        arrowBackButton = new javax.swing.JButton();
        arrowBackgroundButton = new RoundedPanel(30);
        jScrollPane1 = new javax.swing.JScrollPane();
        Separator = new javax.swing.JPanel();
        PlusBlackIconLabel = new javax.swing.JLabel();
        PlusIconLabel = new javax.swing.JLabel();
        lblTextoNovoCliente = new javax.swing.JLabel();
        NewClientButtonPanel = new RoundedPanel(30);
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

        TipIconLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel2);
        TipIconLabel2.setBounds(480, 105, 13, 13);

        TipIconLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel1);
        TipIconLabel1.setBounds(285, 105, 13, 13);

        TimeZoneSubTitleLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        TimeZoneSubTitleLabel.setForeground(new java.awt.Color(209, 213, 219));
        TimeZoneSubTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TimeZoneSubTitleLabel.setText("Outubro, 2026");
        TimeZoneSubTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TimeZoneSubTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TimeZoneSubTitleLabelMouseExited(evt);
            }
        });
        getContentPane().add(TimeZoneSubTitleLabel);
        TimeZoneSubTitleLabel.setBounds(276, 97, 227, 29);

        EditInfoPanel1.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(EditInfoPanel1);
        EditInfoPanel1.setBounds(276, 97, 227, 29);

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

        jScrollPane1.setFont(FonteUtils.carregarRobotoRegular(12f));

        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(0, 0));

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(276, 152, 976, 540);

        Separator.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(Separator);
        Separator.setBounds(240, 137, 1040, 1);

        PlusBlackIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(PlusBlackIconLabel);
        PlusBlackIconLabel.setBounds(1221, 61, 30, 30);

        PlusIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(PlusIconLabel);
        PlusIconLabel.setBounds(1221, 61, 30, 30);

        lblTextoNovoCliente.setFont(FonteUtils.carregarRobotoRegular(12f));
        lblTextoNovoCliente.setForeground(new java.awt.Color(0, 0, 0));
        lblTextoNovoCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextoNovoCliente.setText("Novo Evento");
        getContentPane().add(lblTextoNovoCliente);
        lblTextoNovoCliente.setBounds(1148, 69, 74, 14);

        NewClientButtonPanel.setBackground(new java.awt.Color(72, 79, 82));
        NewClientButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NewClientButtonPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NewClientButtonPanelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NewClientButtonPanelMousePressed(evt);
            }
        });
        getContentPane().add(NewClientButtonPanel);
        NewClientButtonPanel.setBounds(1221, 61, 30, 30);

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
        lblTitleBanner.setText("Calendário");
        getContentPane().add(lblTitleBanner);
        lblTitleBanner.setBounds(276, 69, 328, 14);

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
        timerVoltarHome.stop();
        pnlBarButtonCalendar.setVisible(true);
        pnlButtonCalendar.setVisible(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnCalendarMouseEntered

    private void btnCalendarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalendarMouseExited
        timerVoltarHome.start();
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
        gerenciarHover(pnlBarButtonConfig, pnlButtonConfig, true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnConfigMouseEntered

    private void btnConfigMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfigMouseExited
        gerenciarHover(pnlBarButtonConfig, pnlButtonConfig, false);
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

    private void NewClientButtonPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewClientButtonPanelMousePressed
        irParaNovoEvento();
    }//GEN-LAST:event_NewClientButtonPanelMousePressed

    private void arrowBackButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arrowBackButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        arrowBackgroundButton.setVisible(true);
    }//GEN-LAST:event_arrowBackButtonMouseEntered

    private void arrowBackButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arrowBackButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        arrowBackgroundButton.setVisible(false);
    }//GEN-LAST:event_arrowBackButtonMouseExited

    private void arrowBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrowBackButtonActionPerformed
        new Home(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_arrowBackButtonActionPerformed

    private void NewClientButtonPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewClientButtonPanelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_NewClientButtonPanelMouseEntered

    private void NewClientButtonPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewClientButtonPanelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_NewClientButtonPanelMouseExited

    private void TimeZoneSubTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TimeZoneSubTitleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TimeZoneSubTitleLabelMouseEntered

    private void TimeZoneSubTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TimeZoneSubTitleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TimeZoneSubTitleLabelMouseExited

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
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
    private javax.swing.JPanel EditInfoPanel1;
    private javax.swing.JPanel Ellipse1;
    private javax.swing.JPanel Ellipse2;
    private javax.swing.JPanel Ellipse3;
    private javax.swing.JPanel NewClientButtonPanel;
    private javax.swing.JLabel PlusBlackIconLabel;
    private javax.swing.JLabel PlusIconLabel;
    private javax.swing.JPanel Separator;
    private javax.swing.JLabel TimeZoneSubTitleLabel;
    private javax.swing.JLabel TipIconLabel1;
    private javax.swing.JLabel TipIconLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JLabel lblTextoNovoCliente;
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
