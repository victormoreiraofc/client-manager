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
import screen.ClienteItemPanel;

public class Client extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private final Color COR_PADRAO = new Color(28, 37, 54);
    private final Color COR_HOVER_GERAL = new Color(50, 63, 89);
    private final Color COR_HOVER_FECHAR = new Color(196, 43, 28);
    private final Color COR_NORMAL_BTN = new Color(72, 79, 82);
    private final Color COR_HOVER_BTN = new Color(205, 168, 12);
    private javax.swing.Timer timerVoltarHome;
    private javax.swing.Timer timerExpandir;
    private final int LARGURA_MIN = 30;
    private final int LARGURA_MAX = 114;
    private ClienteItemPanel itemSelecionado = null;
    private java.util.List<Data.Cliente> cacheClientes = new java.util.ArrayList<>();

    public Client(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        setUndecorated(true);
        setIcon();
        aplicarImagensDeAltaQualidade();
        localizeAuthScreen();
        filtrarSidebar();
        configurarBotaoAnimado();
        carregarListaClientes();
        IconUtil.setIcon(usuarioLogado, lblUsuarioIcon, 30, 30);
        setBackground(new java.awt.Color(0, 0, 0, 0));
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        pnlButtonEllipse.setVisible(false);
        arrowBackgroundButton.setVisible(false);
        lblUsuarioName.setText(usuarioLogado.getUsuario());

        timerVoltarHome = new javax.swing.Timer(300, e -> {
            if (txtBuscar.getText().trim().isEmpty()) {
                pnlBarButtonClient.setVisible(true);
                pnlButtonClient.setVisible(true);
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

        try {
            this.cacheClientes = Data.CTCONTAB.listarClientes();
        } catch (Exception e) {
        }

        alternarAba("All");

        // Adicione os eventos de clique (Exemplo para os Labels ou Painéis de fundo)
        AllClientesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alternarAba("All");
            }
        });

        EndClientesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alternarAba("End");
            }
        });

        ProgressClientesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alternarAba("Progress");
            }
        });

        PendingClientesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alternarAba("Pending");
            }
        });
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

        setTitle(I18nManager.getString("auth.client.window_title"));
        lblButtonHome.setText(I18nManager.getString("auth.client.button.home"));
        lblButtonCalendar.setText(I18nManager.getString("auth.client.button.calendar"));
        lblButtonClient.setText(I18nManager.getString("auth.client.button.client"));
        lblButtonReport.setText(I18nManager.getString("auth.client.button.report"));
        lblButtonTask.setText(I18nManager.getString("auth.client.button.task"));
        lblButtonConfig.setText(I18nManager.getString("auth.client.button.config"));
        lblButtonAdmin.setText(I18nManager.getString("auth.client.button.admin"));
        lblTitleBanner.setText(I18nManager.getString("auth.client.title"));
        AllClientesLabel.setText(I18nManager.getString("auth.client.label.all"));
        EndClientesLabel.setText(I18nManager.getString("auth.client.label.finished"));
        ProgressClientesLabel.setText(I18nManager.getString("auth.client.label.in_progress"));
        PendingClientesLabel.setText(I18nManager.getString("auth.client.label.pending"));
        lblTextoNovoCliente.setText(I18nManager.getString("auth.client.label.new_client_text"));
        ColumnIDLabel.setText(I18nManager.getString("auth.client.column.id"));
        ColumnTypeLabel.setText(I18nManager.getString("auth.client.column.type"));
        ColumnStatsLabel.setText(I18nManager.getString("auth.client.column.status"));
        ColumnDateLabel.setText(I18nManager.getString("auth.client.column.date"));
        ColumnNameLabel.setText(I18nManager.getString("auth.client.column.name"));
        ((RoundedTextField)txtBuscar).setPlaceholder(I18nManager.getString("auth.client.button.search"));

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

                if (btn == btnClient && !estaBuscando) {
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

        // Ponto fixo da direita (X original + largura mínima)
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
                        // Move o X para a esquerda enquanto aumenta a largura
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
                        // Recua o X para a direita enquanto diminui a largura
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

    public void selecionarItem(ClienteItemPanel item) {
        if (itemSelecionado != null) {
            itemSelecionado.setSelecionado(false);
        }
        itemSelecionado = item;
        itemSelecionado.setSelecionado(true);
    }

    private void carregarListaClientes() {
        try {
            // Busca os clientes usando o método já existente no seu CTCONTAB.java 
            java.util.List<Data.Cliente> clientes = Data.CTCONTAB.listarClientes();

            if (clientes.isEmpty()) {
                jScrollPane1.setVisible(false);
                return;
            }

            // Painel que conterá a lista
            JPanel pnlLista = new JPanel();
            pnlLista.setBackground(new Color(37, 49, 71)); // Cor de fundo solicitada #253147
            pnlLista.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 7)); // Distância de 7px entre itens

            // Ajusta altura dinâmica do painel para o scroll funcionar
            int alturaTotal = clientes.size() * (50 + 7);
            pnlLista.setPreferredSize(new Dimension(976, alturaTotal));

            for (Data.Cliente c : clientes) {
                ClienteItemPanel item = new ClienteItemPanel(c, this);
                pnlLista.add(item);

                // Seleciona o primeiro item por padrão como solicitado
                if (itemSelecionado == null) {
                    selecionarItem(item);
                }
            }

            // Configura o ScrollPane
            jScrollPane1.setViewportView(pnlLista);
            jScrollPane1.setBorder(null);
            jScrollPane1.getViewport().setBackground(new Color(37, 49, 71));
            jScrollPane1.setVisible(true);

        } catch (Exception e) {
            System.err.println("Erro ao carregar lista: " + e.getMessage());
        }
    }

    private void alternarAba(String abaAtiva) {
        // Definição das Cores
        Color corAtiva = new Color(205, 168, 12);   // #CDA80C
        Color corInativa = new Color(209, 213, 219); // #D1D5DB

        // 1. Resetar todos para o estado inativo
        // All
        AllClientesLabel.setForeground(corInativa);
        AllClientesSeparator.setVisible(false);
        AllClientesButton.setVisible(false);

        // Finalizados (End)
        EndClientesLabel.setForeground(corInativa);
        EndClientesSeparator.setVisible(false);
        EndClientesButton.setVisible(false);

        // Em Andamento (Progress)
        ProgressClientesLabel.setForeground(corInativa);
        ProgressClientesSeparator.setVisible(false);
        ProgressClientesButton.setVisible(false);

        // Pendentes (Pending)
        PendingClientesLabel.setForeground(corInativa);
        PendingClientesSeparator.setVisible(false);
        PendingClientesButton.setVisible(false);

        // 2. Ativar apenas a selecionada
        switch (abaAtiva) {
            case "All":
                AllClientesLabel.setForeground(corAtiva);
                AllClientesSeparator.setVisible(true);
                AllClientesButton.setVisible(true);
                carregarListaClientesComFiltro("Todos");
                break;
            case "End":
                EndClientesLabel.setForeground(corAtiva);
                EndClientesSeparator.setVisible(true);
                EndClientesButton.setVisible(true);
                carregarListaClientesComFiltro("Concluido");
                break;
            case "Progress":
                ProgressClientesLabel.setForeground(corAtiva);
                ProgressClientesSeparator.setVisible(true);
                ProgressClientesButton.setVisible(true);
                carregarListaClientesComFiltro("Em andamento");
                break;
            case "Pending":
                PendingClientesLabel.setForeground(corAtiva);
                PendingClientesSeparator.setVisible(true);
                PendingClientesButton.setVisible(true);
                carregarListaClientesComFiltro("Pendente");
                break;
        }
    }

    private void carregarListaClientesComFiltro(String filtro) {
        // 1. Se o cache estiver vazio, busca do banco uma única vez
        if (cacheClientes.isEmpty()) {
            try {
                cacheClientes = Data.CTCONTAB.listarClientes();
            } catch (Exception e) {
                System.err.println("Erro ao buscar banco: " + e.getMessage());
            }
        }

        // 2. Ordenação Universal: Do último para o primeiro (ID decrescente)
        // Isso garante que todas as abas sigam a mesma regra
        cacheClientes.sort((c1, c2) -> Integer.compare(c2.getId(), c1.getId()));

        // 3. Filtragem ultra rápida em memória
        java.util.List<Data.Cliente> listaFiltrada = new java.util.ArrayList<>();
        for (Data.Cliente c : cacheClientes) {
            if (filtro.equals("Todos") || c.getSituacaoServico().equalsIgnoreCase(filtro)) {
                listaFiltrada.add(c);
            }
        }

        // 4. Renderização Direta
        renderizarLista(listaFiltrada);
    }

    private void renderizarLista(java.util.List<Data.Cliente> lista) {
        if (lista.isEmpty()) {
            jScrollPane1.setVisible(false);
            return;
        }

        JPanel pnlLista = new JPanel();
        pnlLista.setBackground(new Color(37, 49, 71));
        pnlLista.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 7));

        int alturaTotal = lista.size() * (50 + 7);
        pnlLista.setPreferredSize(new Dimension(976, Math.max(alturaTotal, jScrollPane1.getHeight())));

        // Reset de seleção
        itemSelecionado = null;

        for (Data.Cliente c : lista) {
            ClienteItemPanel item = new ClienteItemPanel(c, this);
            pnlLista.add(item);

            if (itemSelecionado == null) {
                selecionarItem(item);
            }
        }

        // Atualização atômica (troca tudo de uma vez para ser instantâneo)
        jScrollPane1.setViewportView(pnlLista);
        jScrollPane1.setVisible(true);
        jScrollPane1.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        arrowBackButton = new javax.swing.JButton();
        arrowBackgroundButton = new RoundedPanel(30);
        PendingClientesButton = new javax.swing.JButton();
        PendingClientesSeparator = new javax.swing.JPanel();
        PendingClientesLabel = new javax.swing.JLabel();
        ProgressClientesButton = new javax.swing.JButton();
        ProgressClientesSeparator = new javax.swing.JPanel();
        ProgressClientesLabel = new javax.swing.JLabel();
        EndClientesButton = new javax.swing.JButton();
        EndClientesSeparator = new javax.swing.JPanel();
        EndClientesLabel = new javax.swing.JLabel();
        AllClientesButton = new javax.swing.JButton();
        AllClientesSeparator = new javax.swing.JPanel();
        AllClientesLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Separator = new javax.swing.JPanel();
        ColumnDateLabel = new javax.swing.JLabel();
        ColumnTypeLabel = new javax.swing.JLabel();
        ColumnStatsLabel = new javax.swing.JLabel();
        ColumnNameLabel = new javax.swing.JLabel();
        ColumnIDLabel = new javax.swing.JLabel();
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

        PendingClientesButton.setBackground(new java.awt.Color(51, 65, 85));
        PendingClientesButton.setContentAreaFilled(false);
        PendingClientesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PendingClientesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PendingClientesButtonMouseExited(evt);
            }
        });
        PendingClientesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PendingClientesButtonActionPerformed(evt);
            }
        });
        getContentPane().add(PendingClientesButton);
        PendingClientesButton.setBounds(606, 108, 100, 30);

        PendingClientesSeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(PendingClientesSeparator);
        PendingClientesSeparator.setBounds(606, 137, 100, 1);

        PendingClientesLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        PendingClientesLabel.setForeground(new java.awt.Color(209, 213, 219));
        PendingClientesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PendingClientesLabel.setText("Pendentes");
        PendingClientesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PendingClientesLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PendingClientesLabelMouseExited(evt);
            }
        });
        getContentPane().add(PendingClientesLabel);
        PendingClientesLabel.setBounds(606, 112, 100, 14);

        ProgressClientesButton.setBackground(new java.awt.Color(51, 65, 85));
        ProgressClientesButton.setContentAreaFilled(false);
        ProgressClientesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProgressClientesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProgressClientesButtonMouseExited(evt);
            }
        });
        ProgressClientesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProgressClientesButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ProgressClientesButton);
        ProgressClientesButton.setBounds(496, 108, 100, 30);

        ProgressClientesSeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(ProgressClientesSeparator);
        ProgressClientesSeparator.setBounds(496, 137, 100, 1);

        ProgressClientesLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        ProgressClientesLabel.setForeground(new java.awt.Color(209, 213, 219));
        ProgressClientesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProgressClientesLabel.setText("Em andamento");
        ProgressClientesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ProgressClientesLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ProgressClientesLabelMouseExited(evt);
            }
        });
        getContentPane().add(ProgressClientesLabel);
        ProgressClientesLabel.setBounds(496, 112, 100, 14);

        EndClientesButton.setBackground(new java.awt.Color(51, 65, 85));
        EndClientesButton.setContentAreaFilled(false);
        EndClientesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EndClientesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EndClientesButtonMouseExited(evt);
            }
        });
        EndClientesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EndClientesButtonActionPerformed(evt);
            }
        });
        getContentPane().add(EndClientesButton);
        EndClientesButton.setBounds(386, 108, 100, 30);

        EndClientesSeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(EndClientesSeparator);
        EndClientesSeparator.setBounds(386, 137, 100, 1);

        EndClientesLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        EndClientesLabel.setForeground(new java.awt.Color(209, 213, 219));
        EndClientesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EndClientesLabel.setText("Finalizados");
        EndClientesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EndClientesLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EndClientesLabelMouseExited(evt);
            }
        });
        getContentPane().add(EndClientesLabel);
        EndClientesLabel.setBounds(386, 112, 100, 14);

        AllClientesButton.setBackground(new java.awt.Color(51, 65, 85));
        AllClientesButton.setContentAreaFilled(false);
        AllClientesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AllClientesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AllClientesButtonMouseExited(evt);
            }
        });
        AllClientesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllClientesButtonActionPerformed(evt);
            }
        });
        getContentPane().add(AllClientesButton);
        AllClientesButton.setBounds(276, 108, 100, 30);

        AllClientesSeparator.setBackground(new java.awt.Color(205, 168, 12));
        getContentPane().add(AllClientesSeparator);
        AllClientesSeparator.setBounds(276, 137, 100, 1);

        AllClientesLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        AllClientesLabel.setForeground(new java.awt.Color(209, 213, 219));
        AllClientesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AllClientesLabel.setText("Todos");
        AllClientesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AllClientesLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AllClientesLabelMouseExited(evt);
            }
        });
        getContentPane().add(AllClientesLabel);
        AllClientesLabel.setBounds(276, 112, 100, 14);

        jScrollPane1.setFont(FonteUtils.carregarRobotoRegular(12f));

        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(0, 0));

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(276, 187, 976, 505);

        Separator.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(Separator);
        Separator.setBounds(240, 137, 1040, 1);

        ColumnDateLabel.setFont(FonteUtils.carregarLato(12f));
        ColumnDateLabel.setForeground(new java.awt.Color(168, 178, 195));
        ColumnDateLabel.setText("DATA DE CADASTRO");
        getContentPane().add(ColumnDateLabel);
        ColumnDateLabel.setBounds(1040, 161, 121, 14);

        ColumnTypeLabel.setFont(FonteUtils.carregarLato(12f));
        ColumnTypeLabel.setForeground(new java.awt.Color(168, 178, 195));
        ColumnTypeLabel.setText("TIPO DE PESSOA");
        getContentPane().add(ColumnTypeLabel);
        ColumnTypeLabel.setBounds(827, 161, 121, 14);

        ColumnStatsLabel.setFont(FonteUtils.carregarLato(12f));
        ColumnStatsLabel.setForeground(new java.awt.Color(168, 178, 195));
        ColumnStatsLabel.setText("STATUS");
        getContentPane().add(ColumnStatsLabel);
        ColumnStatsLabel.setBounds(657, 161, 74, 14);

        ColumnNameLabel.setFont(FonteUtils.carregarLato(12f));
        ColumnNameLabel.setForeground(new java.awt.Color(168, 178, 195));
        ColumnNameLabel.setText("NOME");
        getContentPane().add(ColumnNameLabel);
        ColumnNameLabel.setBounds(362, 161, 74, 14);

        ColumnIDLabel.setFont(FonteUtils.carregarLato(12f));
        ColumnIDLabel.setForeground(new java.awt.Color(168, 178, 195));
        ColumnIDLabel.setText("ID");
        getContentPane().add(ColumnIDLabel);
        ColumnIDLabel.setBounds(288, 161, 74, 14);

        PlusBlackIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(PlusBlackIconLabel);
        PlusBlackIconLabel.setBounds(1221, 61, 30, 30);

        PlusIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(PlusIconLabel);
        PlusIconLabel.setBounds(1221, 61, 30, 30);

        lblTextoNovoCliente.setFont(FonteUtils.carregarRobotoRegular(12f));
        lblTextoNovoCliente.setForeground(new java.awt.Color(0, 0, 0));
        lblTextoNovoCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTextoNovoCliente.setText("Novo Cliente");
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
        lblTitleBanner.setText("Clientes");
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
        // TODO add your handling code here:
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCalendarActionPerformed

    private void btnClientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientMouseEntered
        timerVoltarHome.stop();
        pnlBarButtonClient.setVisible(true);
        pnlButtonClient.setVisible(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnClientMouseEntered

    private void btnClientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientMouseExited
        timerVoltarHome.start();
    }//GEN-LAST:event_btnClientMouseExited

    private void btnClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientActionPerformed
        // TODO add your handling code here:
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
        // TODO add your handling code here:
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
        // TODO add your handling code here:
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
        // TODO add your handling code here:
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
        // TODO add your handling code here:
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEllipseActionPerformed

    private void AllClientesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AllClientesButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_AllClientesButtonMouseEntered

    private void AllClientesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AllClientesButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_AllClientesButtonMouseExited

    private void AllClientesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllClientesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AllClientesButtonActionPerformed

    private void EndClientesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EndClientesButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_EndClientesButtonMouseEntered

    private void EndClientesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EndClientesButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_EndClientesButtonMouseExited

    private void EndClientesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EndClientesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EndClientesButtonActionPerformed

    private void PendingClientesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PendingClientesButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_PendingClientesButtonMouseEntered

    private void PendingClientesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PendingClientesButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_PendingClientesButtonMouseExited

    private void PendingClientesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PendingClientesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PendingClientesButtonActionPerformed

    private void ProgressClientesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProgressClientesButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_ProgressClientesButtonMouseEntered

    private void ProgressClientesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProgressClientesButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_ProgressClientesButtonMouseExited

    private void ProgressClientesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProgressClientesButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProgressClientesButtonActionPerformed

    private void NewClientButtonPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewClientButtonPanelMousePressed
        new NewClient(usuarioLogado).setVisible(true);
        this.dispose();
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

    private void AllClientesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AllClientesLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AllClientesLabel.setForeground(new java.awt.Color(205, 168, 12));
    }//GEN-LAST:event_AllClientesLabelMouseEntered

    private void AllClientesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AllClientesLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        AllClientesLabel.setForeground(new java.awt.Color(209, 213, 219));
    }//GEN-LAST:event_AllClientesLabelMouseExited

    private void EndClientesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EndClientesLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EndClientesLabel.setForeground(new java.awt.Color(205, 168, 12));
    }//GEN-LAST:event_EndClientesLabelMouseEntered

    private void EndClientesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EndClientesLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        EndClientesLabel.setForeground(new java.awt.Color(209, 213, 219));
    }//GEN-LAST:event_EndClientesLabelMouseExited

    private void PendingClientesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PendingClientesLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PendingClientesLabel.setForeground(new java.awt.Color(205, 168, 12));
    }//GEN-LAST:event_PendingClientesLabelMouseEntered

    private void PendingClientesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PendingClientesLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PendingClientesLabel.setForeground(new java.awt.Color(209, 213, 219));
    }//GEN-LAST:event_PendingClientesLabelMouseExited

    private void ProgressClientesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProgressClientesLabelMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProgressClientesLabel.setForeground(new java.awt.Color(205, 168, 12));
    }//GEN-LAST:event_ProgressClientesLabelMouseEntered

    private void ProgressClientesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProgressClientesLabelMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ProgressClientesLabel.setForeground(new java.awt.Color(209, 213, 219));
    }//GEN-LAST:event_ProgressClientesLabelMouseExited

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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
    private javax.swing.JButton AllClientesButton;
    private javax.swing.JLabel AllClientesLabel;
    private javax.swing.JPanel AllClientesSeparator;
    private javax.swing.JLabel ColumnDateLabel;
    private javax.swing.JLabel ColumnIDLabel;
    private javax.swing.JLabel ColumnNameLabel;
    private javax.swing.JLabel ColumnStatsLabel;
    private javax.swing.JLabel ColumnTypeLabel;
    private javax.swing.JPanel Ellipse1;
    private javax.swing.JPanel Ellipse2;
    private javax.swing.JPanel Ellipse3;
    private javax.swing.JButton EndClientesButton;
    private javax.swing.JLabel EndClientesLabel;
    private javax.swing.JPanel EndClientesSeparator;
    private javax.swing.JPanel NewClientButtonPanel;
    private javax.swing.JButton PendingClientesButton;
    private javax.swing.JLabel PendingClientesLabel;
    private javax.swing.JPanel PendingClientesSeparator;
    private javax.swing.JLabel PlusBlackIconLabel;
    private javax.swing.JLabel PlusIconLabel;
    private javax.swing.JButton ProgressClientesButton;
    private javax.swing.JLabel ProgressClientesLabel;
    private javax.swing.JPanel ProgressClientesSeparator;
    private javax.swing.JPanel Separator;
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
