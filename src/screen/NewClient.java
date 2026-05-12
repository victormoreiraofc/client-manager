package screen;

import Data.CTCONTAB;
import Data.I18nManager;
import Data.IconUtil;
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
import java.util.Locale;
import javax.imageio.ImageIO;

public class NewClient extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private final Color COR_PADRAO = new Color(28, 37, 54);
    private final Color COR_HOVER_GERAL = new Color(50, 63, 89);
    private final Color COR_HOVER_FECHAR = new Color(196, 43, 28);
    private javax.swing.Timer timerVoltarHome;
    private static final int MAX_LENGTH = 50;

    public NewClient(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        setUndecorated(true);
        setIcon();
        aplicarImagensDeAltaQualidade();
        localizeAuthScreen();
        filtrarSidebar();
        IconUtil.setIcon(usuarioLogado, userNameIcon, 30, 30);
        setBackground(new java.awt.Color(0, 0, 0, 0));
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
        setupInputField((RoundedTextField) clientnameField, "Nome Completo*", 50);
        setupInputField((RoundedTextField) serviceField, "Serviço*", 50);
        setupInputField((RoundedTextField) typeofpersonField, "Tipo de Pessoa*", 50);
        setupInputField((RoundedTextField) typeofserviceField, "Tipo de Serviços*", 50);
        setupInputField((RoundedTextField) cellphoneField, "Celular", 50);
        setupInputField((RoundedTextField) emailField, "Email*", 50);
        setupInputField((RoundedTextField) telephoneField, "Telefone", 50);
        setupInputField((RoundedTextField) observationField, "Observações", 50);
        observationField.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 10, 130, 15));
        ((RoundedPanel) cancelButtonPanel).setShowBorder(true);

        defaultClientNameLabel.setVisible(true);
        defaultServiceLabel.setVisible(true);
        defaultTypeOfPersonLabel.setVisible(true);
        defaultTypeOfServiceLabel.setVisible(true);
        defaultEmailLabel.setVisible(true);
        defaultTelephoneLabel.setVisible(true);
        defaultCellphoneLabel.setVisible(true);
        defaultObservationLabel.setVisible(true);
        hoverCellphoneLabel.setVisible(false);
        containCellphoneLabel.setVisible(false);
        containTelephoneLabel.setVisible(false);
        hoverTelephoneLabel.setVisible(false);
        hoverTypeOfServiceLabel.setVisible(false);
        containTypeOfServiceLabel.setVisible(false);
        hoverTypeOfPersonLabel.setVisible(false);
        containTypeOfPersonLabel.setVisible(false);
        hoverObservationLabel.setVisible(false);
        containObservationLabel.setVisible(false);
        hoverEmailLabel.setVisible(false);
        containEmailLabel.setVisible(false);
        hoverServiceLabel.setVisible(false);
        containServiceLabel.setVisible(false);
        containClientNameLabel.setVisible(false);
        hoverClientNameLabel.setVisible(false);

        pnlButtonEllipse.setVisible(false);
        arrowBackgroundButton.setVisible(false);
        userNameLabel.setText(usuarioLogado.getUsuario());

        timerVoltarHome = new javax.swing.Timer(300, e -> {
            if (searchField.getText().trim().isEmpty()) {
                pnlBarButtonClient.setVisible(true);
                pnlButtonClient.setVisible(true);
            }
        });
        timerVoltarHome.setRepeats(false);

        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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

        searchField.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(62, 76, 89), 2),
                javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));

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
            BufferedImage CloseFormIcon = ImageIO.read(getClass().getResource("/images/Close Form Icon.png"));
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
            userNameIcon.setIcon(gerarIconePerfeito(JohnDoe, 30, 30));
            searchFieldIcon.setIcon(gerarIconePerfeito(MagnifierIcon, 13, 13));
            closeButtonIcon.setIcon(gerarIconePerfeito(CloseFormIcon, 12, 12));
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

        setTitle(I18nManager.getString("auth.new_clients.window_title"));
        lblButtonHome.setText(I18nManager.getString("auth.new_clients.button.home"));
        lblButtonCalendar.setText(I18nManager.getString("auth.new_clients.button.calendar"));
        lblButtonClient.setText(I18nManager.getString("auth.new_clients.button.client"));
        lblButtonReport.setText(I18nManager.getString("auth.new_clients.button.report"));
        lblButtonTask.setText(I18nManager.getString("auth.new_clients.button.task"));
        lblButtonConfig.setText(I18nManager.getString("auth.new_clients.button.config"));
        lblButtonAdmin.setText(I18nManager.getString("auth.new_clients.button.admin"));
        titleLabel.setText(I18nManager.getString("auth.new_clients.label.title"));
        descriptionLabel.setText(I18nManager.getString("auth.new_clients.label.description"));
        defaultClientNameLabel.setText(I18nManager.getString("auth.new_clients.label.default.name"));
        defaultServiceLabel.setText(I18nManager.getString("auth.new_clients.label.default.service"));
        defaultTypeOfPersonLabel.setText(I18nManager.getString("auth.new_clients.label.default.type_person"));
        defaultTypeOfServiceLabel.setText(I18nManager.getString("auth.new_clients.label.default.type_service"));
        defaultEmailLabel.setText(I18nManager.getString("auth.new_clients.label.default.email"));
        defaultTelephoneLabel.setText(I18nManager.getString("auth.new_clients.label.default.telephone"));
        defaultCellphoneLabel.setText(I18nManager.getString("auth.new_clients.label.default.cellphone"));
        defaultObservationLabel.setText(I18nManager.getString("auth.new_clients.label.default.observation"));
        hoverClientNameLabel.setText(I18nManager.getString("auth.new_clients.label.hover.name"));
        hoverServiceLabel.setText(I18nManager.getString("auth.new_clients.label.hover.service"));
        hoverTypeOfPersonLabel.setText(I18nManager.getString("auth.new_clients.label.hover.type_person"));
        hoverTypeOfServiceLabel.setText(I18nManager.getString("auth.new_clients.label.hover.type_service"));
        hoverEmailLabel.setText(I18nManager.getString("auth.new_clients.label.hover.email"));
        hoverTelephoneLabel.setText(I18nManager.getString("auth.new_clients.label.hover.telephone"));
        hoverCellphoneLabel.setText(I18nManager.getString("auth.new_clients.label.hover.cellphone"));
        hoverObservationLabel.setText(I18nManager.getString("auth.new_clients.label.hover.observation"));
        cancelButtonLabel.setText(I18nManager.getString("auth.new_clients.label.button.cancel"));
        registerButtonLabel.setText(I18nManager.getString("auth.new_clients.label.button.register"));
        ((RoundedTextFieldSearch)searchField).setPlaceholder(I18nManager.getString("auth.new_clients.button.search"));

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    private void filtrarSidebar() {
        String busca = searchField.getText().toLowerCase().trim();
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
        getContentPane().setComponentZOrder(userNameIcon, 0);
        getContentPane().setComponentZOrder(userNameLabel, 0);
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

    private void handleFormNavigation(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() != java.awt.event.KeyEvent.VK_ENTER) {
            return;
        }

        if (isFieldEmpty(clientnameField)) {
            clientnameField.requestFocusInWindow();
        } else if (isFieldEmpty(serviceField)) {
            serviceField.requestFocusInWindow();
        } else if (isFieldEmpty(typeofpersonField)) {
            typeofpersonField.requestFocusInWindow();
        } else if (isFieldEmpty(typeofserviceField)) {
            typeofserviceField.requestFocusInWindow();
        } else if (isFieldEmpty(emailField)) {
            emailField.requestFocusInWindow();
        } else if (isFieldEmpty(telephoneField)) {
            telephoneField.requestFocusInWindow();
        } else if (isFieldEmpty(cellphoneField)) {
            cellphoneField.requestFocusInWindow();
        } else if (isFieldEmpty(observationField)) {
            observationField.requestFocusInWindow();
        } else {
            registerButton.doClick();
        }
    }

    private boolean isFieldEmpty(javax.swing.JTextField field) {
        return field.getText().trim().isEmpty();
    }

    private void updateFieldCounter(javax.swing.JTextField textField, javax.swing.JLabel feedbackLabel) {
        int currentLength = textField.getText().length();
        feedbackLabel.setText(currentLength + " / " + MAX_LENGTH);
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

    private String translateToPortuguese(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }
        try {
            String urlStr = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl=pt&dt=t&q="
                    + java.net.URLEncoder.encode(text, "UTF-8");

            java.net.URL url = new java.net.URL(urlStr);
            java.net.HttpURLConnection con = (java.net.HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String json = response.toString();
            return json.substring(json.indexOf("\"") + 1, json.indexOf("\"", json.indexOf("\"") + 1));
        } catch (Exception e) {
            System.err.println("Translation Error: " + e.getMessage());
            return text;
        }
    }

    private String normalizeValue(String input, String category) {
        if (input == null) {
            return null;
        }
        String cleanInput = input.toLowerCase().trim();

        // Dicionário Multi-idioma (PT, EN, ES, DE, FR, IT, JA, KO, ZH)
        if (category.equals("PERSON_TYPE")) {
            if (cleanInput.matches(".*(fisica|natural|física|natürliche|physique|fisica|個人|개인|自然人).*")) {
                return "Fisica";
            }
            if (cleanInput.matches(".*(jurídica|legal|jurídica|juristische|morale|giuridica|法人|법인|法人).*")) {
                return "Juridica";
            }
            if (cleanInput.matches(".*(ni|nao informado|not informed|no informado|nicht angegeben|non renseigné|non informato|未指定|미지정|未告知).*")) {
                return "NI";
            }
        }

        if (category.equals("SERVICE_STATUS")) {
            if (cleanInput.matches(".*(pendente|pending|pendiente|ausstehend|attente|sospeso|保留中|대기 중|待处理).*")) {
                return "Pendente";
            }
            if (cleanInput.matches(".*(andamento|progress|curso|bearbeitung|cours|corso|進行中|진행 중|进行中).*")) {
                return "Em andamento";
            }
            if (cleanInput.matches(".*(concluído|completed|completado|abgeschlossen|terminé|completato|完了|완료됨|已完成).*")) {
                return "Concluido";
            }
        }
        return null;
    }

    private void applyTemporaryErrorToLabel(javax.swing.JLabel label) {
        java.awt.Color originalColor = label.getForeground();
        java.awt.Color darkRed = new java.awt.Color(180, 60, 60);
        label.setForeground(darkRed);

        new javax.swing.Timer(4000, e -> {
            label.setForeground(originalColor);
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();
    }

    private void handleClientRegistration() {
        boolean validationError = false;

        // Verificação Individual para os 5 campos principais
        if (clientnameField.getText().trim().isEmpty()) {
            applyTemporaryErrorToLabel(defaultClientNameLabel);
            validationError = true;
        }
        if (serviceField.getText().trim().isEmpty()) {
            applyTemporaryErrorToLabel(defaultServiceLabel);
            validationError = true;
        }
        if (typeofpersonField.getText().trim().isEmpty()) {
            applyTemporaryErrorToLabel(defaultTypeOfPersonLabel);
            validationError = true;
        }
        if (typeofserviceField.getText().trim().isEmpty()) {
            applyTemporaryErrorToLabel(defaultTypeOfServiceLabel);
            validationError = true;
        }
        if (emailField.getText().trim().isEmpty()) {
            applyTemporaryErrorToLabel(defaultEmailLabel);
            validationError = true;
        }

        if (validationError) {
            return;
        }

        // 2. Normalização de Enums
        String personType = normalizeValue(typeofpersonField.getText(), "PERSON_TYPE");
        String serviceStatus = normalizeValue(typeofserviceField.getText(), "SERVICE_STATUS");

        if (personType == null || serviceStatus == null) {
            if (personType == null) {
                applyTemporaryErrorToLabel(defaultTypeOfPersonLabel);
            }
            if (serviceStatus == null) {
                applyTemporaryErrorToLabel(defaultTypeOfServiceLabel);
            }
            javax.swing.JOptionPane.showMessageDialog(this, "Erro: Tipo de Pessoa ou Status inválido.");
            return;
        }

        // 3. Auto-Translation for Free Text (Professional Background Process)
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // Cursor de carregamento

        // Traduzindo campos (Exceto Nome e Email como pedido)
        String translatedService = translateToPortuguese(serviceField.getText());
        String translatedObservations = translateToPortuguese(observationField.getText());

        // 4. Persistence
        try {
            String currentDate = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
            String user = (usuarioLogado != null) ? usuarioLogado.getUsuario() : "System";

            boolean success = CTCONTAB.saveNewClient(
                    currentDate,
                    clientnameField.getText().trim(), // Nome (Mantém original/Russo)
                    personType, // Normalizado para BD
                    emailField.getText().trim(), // Email (Mantém original)
                    translatedService, // Traduzido para PT
                    serviceStatus, // Normalizado para BD
                    cellphoneField.getText().trim(),
                    telephoneField.getText().trim(),
                    translatedObservations, // Traduzido para PT
                    user
            );

            if (success) {
                MensagemUtil.exibirErro("Registro de cliente finalizado com sucesso. O histórico já está disponivel para a consulta.");
                new Client(usuarioLogado).setVisible(true);
                this.dispose();
            }
        } catch (Exception ex) {
            MensagemUtil.exibirErro("Não conseguimos conectar ao banco de dados no momento. Por favor, verifique sua internet ou tente novamente mais tarde.");
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        cancelButtonLabel = new javax.swing.JLabel();
        cancelButtonPanel = new RoundedPanel(40);
        registerButton = new javax.swing.JButton();
        registerButtonLabel = new javax.swing.JLabel();
        registerButtonPanel = new RoundedPanel(40);
        hoverCellphoneLabel = new javax.swing.JLabel();
        defaultCellphoneLabel = new javax.swing.JLabel();
        containCellphoneLabel = new javax.swing.JLabel();
        containTelephoneLabel = new javax.swing.JLabel();
        hoverTelephoneLabel = new javax.swing.JLabel();
        defaultTelephoneLabel = new javax.swing.JLabel();
        defaultTypeOfServiceLabel = new javax.swing.JLabel();
        hoverTypeOfServiceLabel = new javax.swing.JLabel();
        containTypeOfServiceLabel = new javax.swing.JLabel();
        hoverTypeOfPersonLabel = new javax.swing.JLabel();
        defaultTypeOfPersonLabel = new javax.swing.JLabel();
        containTypeOfPersonLabel = new javax.swing.JLabel();
        hoverObservationLabel = new javax.swing.JLabel();
        containObservationLabel = new javax.swing.JLabel();
        defaultObservationLabel = new javax.swing.JLabel();
        hoverEmailLabel = new javax.swing.JLabel();
        defaultEmailLabel = new javax.swing.JLabel();
        containEmailLabel = new javax.swing.JLabel();
        defaultServiceLabel = new javax.swing.JLabel();
        hoverServiceLabel = new javax.swing.JLabel();
        containServiceLabel = new javax.swing.JLabel();
        containClientNameLabel = new javax.swing.JLabel();
        defaultClientNameLabel = new javax.swing.JLabel();
        hoverClientNameLabel = new javax.swing.JLabel();
        observationField = new RoundedTextField(10);
        telephoneField = new RoundedTextField(10);
        cellphoneField = new RoundedTextField(10);
        typeofserviceField = new RoundedTextField(10);
        typeofpersonField = new RoundedTextField(10);
        emailField = new RoundedTextField(10);
        serviceField = new RoundedTextField(10);
        clientnameField = new RoundedTextField(10);
        descriptionLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        closeButtonIcon = new javax.swing.JLabel();
        closeButtonPanel = new RoundedPanel(30);
        searchFieldIcon = new javax.swing.JLabel();
        searchField = new RoundedTextFieldSearch();
        userNameLabel = new javax.swing.JLabel();
        userNameIcon = new javax.swing.JLabel();
        horizontalDivider = new javax.swing.JPanel();
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
        arrowBackButton = new javax.swing.JButton();
        arrowBackgroundButton = new RoundedPanel(30);
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

        cancelButton.setBackground(new java.awt.Color(37, 49, 71));
        cancelButton.setBorder(null);
        cancelButton.setContentAreaFilled(false);
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButtonMouseExited(evt);
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton);
        cancelButton.setBounds(899, 632, 138, 36);

        cancelButtonLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        cancelButtonLabel.setForeground(new java.awt.Color(255, 255, 255));
        cancelButtonLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cancelButtonLabel.setText("Cancelar");
        getContentPane().add(cancelButtonLabel);
        cancelButtonLabel.setBounds(899, 643, 138, 14);

        cancelButtonPanel.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(cancelButtonPanel);
        cancelButtonPanel.setBounds(899, 632, 138, 36);

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
        registerButton.setBounds(1045, 632, 191, 36);

        registerButtonLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        registerButtonLabel.setForeground(new java.awt.Color(9, 10, 12));
        registerButtonLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerButtonLabel.setText("Cadastrar");
        getContentPane().add(registerButtonLabel);
        registerButtonLabel.setBounds(1045, 643, 191, 14);

        registerButtonPanel.setBackground(new java.awt.Color(205, 168, 12));
        registerButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonPanelMouseEntered(evt);
            }
        });
        getContentPane().add(registerButtonPanel);
        registerButtonPanel.setBounds(1045, 632, 191, 36);

        hoverCellphoneLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        hoverCellphoneLabel.setForeground(new java.awt.Color(40, 127, 206));
        hoverCellphoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hoverCellphoneLabel.setText("Celular");
        getContentPane().add(hoverCellphoneLabel);
        hoverCellphoneLabel.setBounds(777, 398, 380, 14);

        defaultCellphoneLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        defaultCellphoneLabel.setForeground(new java.awt.Color(94, 99, 103));
        defaultCellphoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        defaultCellphoneLabel.setText("Celular");
        getContentPane().add(defaultCellphoneLabel);
        defaultCellphoneLabel.setBounds(777, 400, 390, 25);

        containCellphoneLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        containCellphoneLabel.setForeground(new java.awt.Color(102, 107, 111));
        containCellphoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        containCellphoneLabel.setText("0 / 50");
        getContentPane().add(containCellphoneLabel);
        containCellphoneLabel.setBounds(1190, 398, 35, 14);

        containTelephoneLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        containTelephoneLabel.setForeground(new java.awt.Color(102, 107, 111));
        containTelephoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        containTelephoneLabel.setText("0 / 50");
        getContentPane().add(containTelephoneLabel);
        containTelephoneLabel.setBounds(710, 398, 35, 14);

        hoverTelephoneLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        hoverTelephoneLabel.setForeground(new java.awt.Color(40, 127, 206));
        hoverTelephoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hoverTelephoneLabel.setText("Telefone");
        getContentPane().add(hoverTelephoneLabel);
        hoverTelephoneLabel.setBounds(294, 398, 380, 14);

        defaultTelephoneLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        defaultTelephoneLabel.setForeground(new java.awt.Color(94, 99, 103));
        defaultTelephoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        defaultTelephoneLabel.setText("Telefone");
        getContentPane().add(defaultTelephoneLabel);
        defaultTelephoneLabel.setBounds(294, 400, 390, 25);

        defaultTypeOfServiceLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        defaultTypeOfServiceLabel.setForeground(new java.awt.Color(94, 99, 103));
        defaultTypeOfServiceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        defaultTypeOfServiceLabel.setText("Serviço: Pendente, Em Andamento ou Concluído?*");
        getContentPane().add(defaultTypeOfServiceLabel);
        defaultTypeOfServiceLabel.setBounds(777, 296, 390, 25);

        hoverTypeOfServiceLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        hoverTypeOfServiceLabel.setForeground(new java.awt.Color(40, 127, 206));
        hoverTypeOfServiceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hoverTypeOfServiceLabel.setText("Serviço: Pendente, Em Andamento ou Concluído?*");
        getContentPane().add(hoverTypeOfServiceLabel);
        hoverTypeOfServiceLabel.setBounds(777, 294, 380, 14);

        containTypeOfServiceLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        containTypeOfServiceLabel.setForeground(new java.awt.Color(102, 107, 111));
        containTypeOfServiceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        containTypeOfServiceLabel.setText("0 / 50");
        getContentPane().add(containTypeOfServiceLabel);
        containTypeOfServiceLabel.setBounds(1190, 294, 35, 14);

        hoverTypeOfPersonLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        hoverTypeOfPersonLabel.setForeground(new java.awt.Color(40, 127, 206));
        hoverTypeOfPersonLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hoverTypeOfPersonLabel.setText("Pessoa: Fisica, Juridica ou NI?*");
        getContentPane().add(hoverTypeOfPersonLabel);
        hoverTypeOfPersonLabel.setBounds(294, 294, 380, 14);

        defaultTypeOfPersonLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        defaultTypeOfPersonLabel.setForeground(new java.awt.Color(94, 99, 103));
        defaultTypeOfPersonLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        defaultTypeOfPersonLabel.setText("Pessoa: Fisica, Juridica ou NI?*");
        getContentPane().add(defaultTypeOfPersonLabel);
        defaultTypeOfPersonLabel.setBounds(294, 296, 390, 25);

        containTypeOfPersonLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        containTypeOfPersonLabel.setForeground(new java.awt.Color(102, 107, 111));
        containTypeOfPersonLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        containTypeOfPersonLabel.setText("0 / 50");
        getContentPane().add(containTypeOfPersonLabel);
        containTypeOfPersonLabel.setBounds(710, 294, 35, 14);

        hoverObservationLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        hoverObservationLabel.setForeground(new java.awt.Color(40, 127, 206));
        hoverObservationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hoverObservationLabel.setText("Observações");
        getContentPane().add(hoverObservationLabel);
        hoverObservationLabel.setBounds(294, 447, 810, 14);

        containObservationLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        containObservationLabel.setForeground(new java.awt.Color(102, 107, 111));
        containObservationLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        containObservationLabel.setText("0 / 50");
        getContentPane().add(containObservationLabel);
        containObservationLabel.setBounds(1175, 447, 50, 14);

        defaultObservationLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        defaultObservationLabel.setForeground(new java.awt.Color(94, 99, 103));
        defaultObservationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        defaultObservationLabel.setText("Observações");
        getContentPane().add(defaultObservationLabel);
        defaultObservationLabel.setBounds(294, 448, 920, 25);

        hoverEmailLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        hoverEmailLabel.setForeground(new java.awt.Color(40, 127, 206));
        hoverEmailLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hoverEmailLabel.setText("Email*");
        getContentPane().add(hoverEmailLabel);
        hoverEmailLabel.setBounds(294, 345, 810, 14);

        defaultEmailLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        defaultEmailLabel.setForeground(new java.awt.Color(94, 99, 103));
        defaultEmailLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        defaultEmailLabel.setText("Email*");
        getContentPane().add(defaultEmailLabel);
        defaultEmailLabel.setBounds(294, 347, 920, 25);

        containEmailLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        containEmailLabel.setForeground(new java.awt.Color(102, 107, 111));
        containEmailLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        containEmailLabel.setText("0 / 50");
        getContentPane().add(containEmailLabel);
        containEmailLabel.setBounds(1190, 345, 35, 14);

        defaultServiceLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        defaultServiceLabel.setForeground(new java.awt.Color(94, 99, 103));
        defaultServiceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        defaultServiceLabel.setText("Serviço*");
        getContentPane().add(defaultServiceLabel);
        defaultServiceLabel.setBounds(294, 243, 920, 25);

        hoverServiceLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        hoverServiceLabel.setForeground(new java.awt.Color(40, 127, 206));
        hoverServiceLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hoverServiceLabel.setText("Serviço*");
        getContentPane().add(hoverServiceLabel);
        hoverServiceLabel.setBounds(294, 241, 810, 14);

        containServiceLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        containServiceLabel.setForeground(new java.awt.Color(102, 107, 111));
        containServiceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        containServiceLabel.setText("0 / 50");
        getContentPane().add(containServiceLabel);
        containServiceLabel.setBounds(1190, 241, 35, 14);

        containClientNameLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        containClientNameLabel.setForeground(new java.awt.Color(102, 107, 111));
        containClientNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        containClientNameLabel.setText("0 / 50");
        getContentPane().add(containClientNameLabel);
        containClientNameLabel.setBounds(1190, 190, 35, 14);

        defaultClientNameLabel.setFont(FonteUtils.carregarRobotoBold(13f));
        defaultClientNameLabel.setForeground(new java.awt.Color(94, 99, 103));
        defaultClientNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        defaultClientNameLabel.setText("Nome Completo*");
        getContentPane().add(defaultClientNameLabel);
        defaultClientNameLabel.setBounds(294, 192, 920, 25);

        hoverClientNameLabel.setFont(FonteUtils.carregarRobotoBold(11f));
        hoverClientNameLabel.setForeground(new java.awt.Color(40, 127, 206));
        hoverClientNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hoverClientNameLabel.setText("Nome Completo*");
        getContentPane().add(hoverClientNameLabel);
        hoverClientNameLabel.setBounds(294, 190, 810, 14);

        observationField.setBackground(new java.awt.Color(25, 25, 25));
        observationField.setFont(FonteUtils.carregarRobotoBold(13f));
        observationField.setForeground(new java.awt.Color(255, 255, 255));
        observationField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        observationField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                observationFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                observationFieldFocusLost(evt);
            }
        });
        observationField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                observationFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                observationFieldMousePressed(evt);
            }
        });
        observationField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                observationFieldActionPerformed(evt);
            }
        });
        observationField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                observationFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                observationFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                observationFieldKeyTyped(evt);
            }
        });
        getContentPane().add(observationField);
        observationField.setBounds(284, 440, 952, 172);

        telephoneField.setBackground(new java.awt.Color(25, 25, 25));
        telephoneField.setFont(FonteUtils.carregarRobotoBold(13f));
        telephoneField.setForeground(new java.awt.Color(255, 255, 255));
        telephoneField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        telephoneField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                telephoneFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                telephoneFieldFocusLost(evt);
            }
        });
        telephoneField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                telephoneFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                telephoneFieldMousePressed(evt);
            }
        });
        telephoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telephoneFieldActionPerformed(evt);
            }
        });
        telephoneField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                telephoneFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telephoneFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telephoneFieldKeyTyped(evt);
            }
        });
        getContentPane().add(telephoneField);
        telephoneField.setBounds(284, 393, 469, 40);

        cellphoneField.setBackground(new java.awt.Color(25, 25, 25));
        cellphoneField.setFont(FonteUtils.carregarRobotoBold(13f));
        cellphoneField.setForeground(new java.awt.Color(255, 255, 255));
        cellphoneField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        cellphoneField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cellphoneFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cellphoneFieldFocusLost(evt);
            }
        });
        cellphoneField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cellphoneFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cellphoneFieldMousePressed(evt);
            }
        });
        cellphoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cellphoneFieldActionPerformed(evt);
            }
        });
        cellphoneField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cellphoneFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cellphoneFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cellphoneFieldKeyTyped(evt);
            }
        });
        getContentPane().add(cellphoneField);
        cellphoneField.setBounds(766, 393, 469, 40);

        typeofserviceField.setBackground(new java.awt.Color(25, 25, 25));
        typeofserviceField.setFont(FonteUtils.carregarRobotoBold(13f));
        typeofserviceField.setForeground(new java.awt.Color(255, 255, 255));
        typeofserviceField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        typeofserviceField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                typeofserviceFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                typeofserviceFieldFocusLost(evt);
            }
        });
        typeofserviceField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                typeofserviceFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                typeofserviceFieldMousePressed(evt);
            }
        });
        typeofserviceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeofserviceFieldActionPerformed(evt);
            }
        });
        typeofserviceField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                typeofserviceFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                typeofserviceFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                typeofserviceFieldKeyTyped(evt);
            }
        });
        getContentPane().add(typeofserviceField);
        typeofserviceField.setBounds(766, 289, 469, 40);

        typeofpersonField.setBackground(new java.awt.Color(25, 25, 25));
        typeofpersonField.setFont(FonteUtils.carregarRobotoBold(13f));
        typeofpersonField.setForeground(new java.awt.Color(255, 255, 255));
        typeofpersonField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        typeofpersonField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                typeofpersonFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                typeofpersonFieldFocusLost(evt);
            }
        });
        typeofpersonField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                typeofpersonFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                typeofpersonFieldMousePressed(evt);
            }
        });
        typeofpersonField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeofpersonFieldActionPerformed(evt);
            }
        });
        typeofpersonField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                typeofpersonFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                typeofpersonFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                typeofpersonFieldKeyTyped(evt);
            }
        });
        getContentPane().add(typeofpersonField);
        typeofpersonField.setBounds(284, 289, 469, 40);

        emailField.setBackground(new java.awt.Color(25, 25, 25));
        emailField.setFont(FonteUtils.carregarRobotoBold(13f));
        emailField.setForeground(new java.awt.Color(255, 255, 255));
        emailField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFieldFocusLost(evt);
            }
        });
        emailField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                emailFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                emailFieldMousePressed(evt);
            }
        });
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });
        emailField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailFieldKeyTyped(evt);
            }
        });
        getContentPane().add(emailField);
        emailField.setBounds(284, 340, 952, 40);

        serviceField.setBackground(new java.awt.Color(25, 25, 25));
        serviceField.setFont(FonteUtils.carregarRobotoBold(13f));
        serviceField.setForeground(new java.awt.Color(255, 255, 255));
        serviceField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        serviceField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                serviceFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                serviceFieldFocusLost(evt);
            }
        });
        serviceField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                serviceFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                serviceFieldMousePressed(evt);
            }
        });
        serviceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceFieldActionPerformed(evt);
            }
        });
        serviceField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                serviceFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                serviceFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                serviceFieldKeyTyped(evt);
            }
        });
        getContentPane().add(serviceField);
        serviceField.setBounds(284, 236, 952, 40);

        clientnameField.setBackground(new java.awt.Color(25, 25, 25));
        clientnameField.setFont(FonteUtils.carregarRobotoBold(13f));
        clientnameField.setForeground(new java.awt.Color(255, 255, 255));
        clientnameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        clientnameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                clientnameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                clientnameFieldFocusLost(evt);
            }
        });
        clientnameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clientnameFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clientnameFieldMousePressed(evt);
            }
        });
        clientnameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientnameFieldActionPerformed(evt);
            }
        });
        clientnameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                clientnameFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                clientnameFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clientnameFieldKeyTyped(evt);
            }
        });
        getContentPane().add(clientnameField);
        clientnameField.setBounds(284, 185, 952, 40);

        descriptionLabel.setFont(FonteUtils.carregarRobotoRegular(12f));
        descriptionLabel.setForeground(new java.awt.Color(237, 247, 248));
        descriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionLabel.setText("<html><p align=\"center\">Mantenha nossa base atualizada cadastrando as informações do seu cliente.</p></html>");
        getContentPane().add(descriptionLabel);
        descriptionLabel.setBounds(562, 133, 361, 30);

        titleLabel.setFont(FonteUtils.carregarRobotoBold(18f));
        titleLabel.setForeground(new java.awt.Color(237, 247, 248));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Cadastre um novo cliente");
        getContentPane().add(titleLabel);
        titleLabel.setBounds(468, 98, 549, 20);

        closeButton.setBackground(new java.awt.Color(37, 49, 71));
        closeButton.setBorder(null);
        closeButton.setContentAreaFilled(false);
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButtonMouseExited(evt);
            }
        });
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeButton);
        closeButton.setBounds(1221, 61, 30, 30);

        closeButtonIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(closeButtonIcon);
        closeButtonIcon.setBounds(1221, 61, 30, 30);

        closeButtonPanel.setBackground(new java.awt.Color(72, 79, 82));
        closeButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButtonPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButtonPanelMouseExited(evt);
            }
        });
        closeButtonPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                closeButtonPanelKeyPressed(evt);
            }
        });
        getContentPane().add(closeButtonPanel);
        closeButtonPanel.setBounds(1221, 61, 30, 30);

        searchFieldIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Magnifier Icon.png"))); // NOI18N
        getContentPane().add(searchFieldIcon);
        searchFieldIcon.setBounds(210, 55, 13, 13);

        searchField.setBackground(new java.awt.Color(45, 55, 72));
        searchField.setFont(FonteUtils.carregarLato(12f));
        searchField.setForeground(new java.awt.Color(255, 255, 255));
        searchField.setBorder(null);
        getContentPane().add(searchField);
        searchField.setBounds(7, 50, 225, 26);

        userNameLabel.setFont(FonteUtils.carregarRobotoRegular(14f));
        userNameLabel.setForeground(new java.awt.Color(209, 213, 219));
        userNameLabel.setText("John Doe");
        getContentPane().add(userNameLabel);
        userNameLabel.setBounds(55, 680, 160, 30);

        userNameIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(userNameIcon);
        userNameIcon.setBounds(13, 680, 30, 30);

        horizontalDivider.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(horizontalDivider);
        horizontalDivider.setBounds(0, 665, 240, 2);

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
        btnEllipse.setBounds(202, 7, 28, 28);

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
        // TODO add your handling code here:
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
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

    private void clientnameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_clientnameFieldFocusGained
        defaultClientNameLabel.setVisible(false);
        hoverClientNameLabel.setVisible(true);
        containClientNameLabel.setVisible(true);
    }//GEN-LAST:event_clientnameFieldFocusGained

    private void clientnameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_clientnameFieldFocusLost
        if (clientnameField.getText().isEmpty()) {
            defaultClientNameLabel.setVisible(true);
            hoverClientNameLabel.setVisible(false);
            containClientNameLabel.setVisible(false);
        } else {
            defaultClientNameLabel.setVisible(false);
        }
    }//GEN-LAST:event_clientnameFieldFocusLost

    private void clientnameFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientnameFieldMouseExited

    }//GEN-LAST:event_clientnameFieldMouseExited

    private void clientnameFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientnameFieldMousePressed

    }//GEN-LAST:event_clientnameFieldMousePressed

    private void clientnameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientnameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientnameFieldActionPerformed

    private void clientnameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clientnameFieldKeyPressed
        handleFormNavigation(evt);
    }//GEN-LAST:event_clientnameFieldKeyPressed

    private void clientnameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clientnameFieldKeyReleased
        updateFieldCounter(clientnameField, containClientNameLabel);
    }//GEN-LAST:event_clientnameFieldKeyReleased

    private void clientnameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clientnameFieldKeyTyped
        if (clientnameField.getText().length() >= MAX_LENGTH) {
            evt.consume();
        }
    }//GEN-LAST:event_clientnameFieldKeyTyped

    private void serviceFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_serviceFieldFocusGained
        defaultServiceLabel.setVisible(false);
        hoverServiceLabel.setVisible(true);
        containServiceLabel.setVisible(true);
    }//GEN-LAST:event_serviceFieldFocusGained

    private void serviceFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_serviceFieldFocusLost
        if (serviceField.getText().isEmpty()) {
            defaultServiceLabel.setVisible(true);
            hoverServiceLabel.setVisible(false);
            containServiceLabel.setVisible(false);
        } else {
            defaultServiceLabel.setVisible(false);
        }
    }//GEN-LAST:event_serviceFieldFocusLost

    private void serviceFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serviceFieldMouseExited

    }//GEN-LAST:event_serviceFieldMouseExited

    private void serviceFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serviceFieldMousePressed

    }//GEN-LAST:event_serviceFieldMousePressed

    private void serviceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceFieldActionPerformed

    private void serviceFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serviceFieldKeyPressed
        handleFormNavigation(evt);
    }//GEN-LAST:event_serviceFieldKeyPressed

    private void serviceFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serviceFieldKeyReleased
        updateFieldCounter(serviceField, containServiceLabel);
    }//GEN-LAST:event_serviceFieldKeyReleased

    private void serviceFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serviceFieldKeyTyped
        if (serviceField.getText().length() >= MAX_LENGTH) {
            evt.consume();
        }
    }//GEN-LAST:event_serviceFieldKeyTyped

    private void emailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusGained
        defaultEmailLabel.setVisible(false);
        hoverEmailLabel.setVisible(true);
        containEmailLabel.setVisible(true);
    }//GEN-LAST:event_emailFieldFocusGained

    private void emailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusLost
        if (emailField.getText().isEmpty()) {
            defaultEmailLabel.setVisible(true);
            hoverEmailLabel.setVisible(false);
            containEmailLabel.setVisible(false);
        } else {
            defaultEmailLabel.setVisible(false);
        }
    }//GEN-LAST:event_emailFieldFocusLost

    private void emailFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailFieldMouseExited

    }//GEN-LAST:event_emailFieldMouseExited

    private void emailFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailFieldMousePressed

    }//GEN-LAST:event_emailFieldMousePressed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void emailFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailFieldKeyPressed
        handleFormNavigation(evt);
    }//GEN-LAST:event_emailFieldKeyPressed

    private void emailFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailFieldKeyReleased
        updateFieldCounter(emailField, containEmailLabel);
    }//GEN-LAST:event_emailFieldKeyReleased

    private void emailFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailFieldKeyTyped
        if (emailField.getText().length() >= MAX_LENGTH) {
            evt.consume();
        }
    }//GEN-LAST:event_emailFieldKeyTyped

    private void typeofpersonFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_typeofpersonFieldFocusGained
        defaultTypeOfPersonLabel.setVisible(false);
        hoverTypeOfPersonLabel.setVisible(true);
        containTypeOfPersonLabel.setVisible(true);
    }//GEN-LAST:event_typeofpersonFieldFocusGained

    private void typeofpersonFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_typeofpersonFieldFocusLost
        if (typeofpersonField.getText().isEmpty()) {
            defaultTypeOfPersonLabel.setVisible(true);
            hoverTypeOfPersonLabel.setVisible(false);
            containTypeOfPersonLabel.setVisible(false);
        } else {
            defaultTypeOfPersonLabel.setVisible(false);
        }
    }//GEN-LAST:event_typeofpersonFieldFocusLost

    private void typeofpersonFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeofpersonFieldMouseExited

    }//GEN-LAST:event_typeofpersonFieldMouseExited

    private void typeofpersonFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeofpersonFieldMousePressed

    }//GEN-LAST:event_typeofpersonFieldMousePressed

    private void typeofpersonFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeofpersonFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeofpersonFieldActionPerformed

    private void typeofpersonFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeofpersonFieldKeyPressed
        handleFormNavigation(evt);
    }//GEN-LAST:event_typeofpersonFieldKeyPressed

    private void typeofpersonFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeofpersonFieldKeyReleased
        updateFieldCounter(typeofpersonField, containTypeOfPersonLabel);
    }//GEN-LAST:event_typeofpersonFieldKeyReleased

    private void typeofpersonFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeofpersonFieldKeyTyped
        if (typeofpersonField.getText().length() >= MAX_LENGTH) {
            evt.consume();
        }
    }//GEN-LAST:event_typeofpersonFieldKeyTyped

    private void typeofserviceFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_typeofserviceFieldFocusGained
        defaultTypeOfServiceLabel.setVisible(false);
        hoverTypeOfServiceLabel.setVisible(true);
        containTypeOfServiceLabel.setVisible(true);
    }//GEN-LAST:event_typeofserviceFieldFocusGained

    private void typeofserviceFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_typeofserviceFieldFocusLost
        if (typeofserviceField.getText().isEmpty()) {
            defaultTypeOfServiceLabel.setVisible(true);
            hoverTypeOfServiceLabel.setVisible(false);
            containTypeOfServiceLabel.setVisible(false);
        } else {
            defaultTypeOfServiceLabel.setVisible(false);
        }
    }//GEN-LAST:event_typeofserviceFieldFocusLost

    private void typeofserviceFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeofserviceFieldMouseExited

    }//GEN-LAST:event_typeofserviceFieldMouseExited

    private void typeofserviceFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeofserviceFieldMousePressed

    }//GEN-LAST:event_typeofserviceFieldMousePressed

    private void typeofserviceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeofserviceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeofserviceFieldActionPerformed

    private void typeofserviceFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeofserviceFieldKeyPressed
        handleFormNavigation(evt);
    }//GEN-LAST:event_typeofserviceFieldKeyPressed

    private void typeofserviceFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeofserviceFieldKeyReleased
        updateFieldCounter(typeofserviceField, containTypeOfServiceLabel);
    }//GEN-LAST:event_typeofserviceFieldKeyReleased

    private void typeofserviceFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeofserviceFieldKeyTyped
        if (typeofserviceField.getText().length() >= MAX_LENGTH) {
            evt.consume();
        }
    }//GEN-LAST:event_typeofserviceFieldKeyTyped

    private void cellphoneFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cellphoneFieldFocusGained
        defaultCellphoneLabel.setVisible(false);
        hoverCellphoneLabel.setVisible(true);
        containCellphoneLabel.setVisible(true);
    }//GEN-LAST:event_cellphoneFieldFocusGained

    private void cellphoneFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cellphoneFieldFocusLost
        if (cellphoneField.getText().isEmpty()) {
            defaultCellphoneLabel.setVisible(true);
            hoverCellphoneLabel.setVisible(false);
            containCellphoneLabel.setVisible(false);
        } else {
            defaultCellphoneLabel.setVisible(false);
        }
    }//GEN-LAST:event_cellphoneFieldFocusLost

    private void cellphoneFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cellphoneFieldMouseExited

    }//GEN-LAST:event_cellphoneFieldMouseExited

    private void cellphoneFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cellphoneFieldMousePressed

    }//GEN-LAST:event_cellphoneFieldMousePressed

    private void cellphoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cellphoneFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cellphoneFieldActionPerformed

    private void cellphoneFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cellphoneFieldKeyPressed
        handleFormNavigation(evt);
    }//GEN-LAST:event_cellphoneFieldKeyPressed

    private void cellphoneFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cellphoneFieldKeyReleased
        updateFieldCounter(cellphoneField, containCellphoneLabel);
    }//GEN-LAST:event_cellphoneFieldKeyReleased

    private void cellphoneFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cellphoneFieldKeyTyped
        if (cellphoneField.getText().length() >= MAX_LENGTH) {
            evt.consume();
        }
    }//GEN-LAST:event_cellphoneFieldKeyTyped

    private void telephoneFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telephoneFieldFocusGained
        defaultTelephoneLabel.setVisible(false);
        hoverTelephoneLabel.setVisible(true);
        containTelephoneLabel.setVisible(true);
    }//GEN-LAST:event_telephoneFieldFocusGained

    private void telephoneFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telephoneFieldFocusLost
        if (telephoneField.getText().isEmpty()) {
            defaultTelephoneLabel.setVisible(true);
            hoverTelephoneLabel.setVisible(false);
            containTelephoneLabel.setVisible(false);
        } else {
            defaultTelephoneLabel.setVisible(false);
        }
    }//GEN-LAST:event_telephoneFieldFocusLost

    private void telephoneFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_telephoneFieldMouseExited

    }//GEN-LAST:event_telephoneFieldMouseExited

    private void telephoneFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_telephoneFieldMousePressed

    }//GEN-LAST:event_telephoneFieldMousePressed

    private void telephoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telephoneFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telephoneFieldActionPerformed

    private void telephoneFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telephoneFieldKeyPressed
        handleFormNavigation(evt);
    }//GEN-LAST:event_telephoneFieldKeyPressed

    private void telephoneFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telephoneFieldKeyReleased
        updateFieldCounter(telephoneField, containTelephoneLabel);
    }//GEN-LAST:event_telephoneFieldKeyReleased

    private void telephoneFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telephoneFieldKeyTyped
        if (telephoneField.getText().length() >= MAX_LENGTH) {
            evt.consume();
        }
    }//GEN-LAST:event_telephoneFieldKeyTyped

    private void observationFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_observationFieldFocusGained
        defaultObservationLabel.setVisible(false);
        hoverObservationLabel.setVisible(true);
        containObservationLabel.setVisible(true);
    }//GEN-LAST:event_observationFieldFocusGained

    private void observationFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_observationFieldFocusLost
        if (observationField.getText().isEmpty()) {
            defaultObservationLabel.setVisible(true);
            hoverObservationLabel.setVisible(false);
            containObservationLabel.setVisible(false);
        } else {
            defaultObservationLabel.setVisible(false);
        }
    }//GEN-LAST:event_observationFieldFocusLost

    private void observationFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_observationFieldMouseExited

    }//GEN-LAST:event_observationFieldMouseExited

    private void observationFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_observationFieldMousePressed

    }//GEN-LAST:event_observationFieldMousePressed

    private void observationFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_observationFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_observationFieldActionPerformed

    private void observationFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_observationFieldKeyPressed
        handleFormNavigation(evt);
    }//GEN-LAST:event_observationFieldKeyPressed

    private void observationFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_observationFieldKeyReleased
        updateFieldCounter(observationField, containObservationLabel);
    }//GEN-LAST:event_observationFieldKeyReleased

    private void observationFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_observationFieldKeyTyped
        if (observationField.getText().length() >= MAX_LENGTH) {
            evt.consume();
        }
    }//GEN-LAST:event_observationFieldKeyTyped

    private void registerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseEntered
        registerButtonPanel.setBackground(new java.awt.Color(184, 150, 10));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_registerButtonMouseEntered

    private void registerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseExited
        registerButtonPanel.setBackground(new java.awt.Color(205, 168, 12));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_registerButtonMouseExited

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        handleClientRegistration();
    }//GEN-LAST:event_registerButtonActionPerformed

    private void registerButtonPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonPanelMouseEntered

    }//GEN-LAST:event_registerButtonPanelMouseEntered

    private void cancelButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseEntered
        cancelButtonPanel.setBackground(new java.awt.Color(74, 98, 142));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_cancelButtonMouseEntered

    private void cancelButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseExited
        cancelButtonPanel.setBackground(new java.awt.Color(37, 49, 71));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_cancelButtonMouseExited

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        new Client(usuarioLogado).setVisible(true);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void closeButtonPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonPanelMouseEntered

    }//GEN-LAST:event_closeButtonPanelMouseEntered

    private void closeButtonPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonPanelMouseExited

    }//GEN-LAST:event_closeButtonPanelMouseExited

    private void closeButtonPanelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_closeButtonPanelKeyPressed

    }//GEN-LAST:event_closeButtonPanelKeyPressed

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered
        closeButtonPanel.setBackground(new java.awt.Color(74, 98, 142));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseExited
        closeButtonPanel.setBackground(new java.awt.Color(72, 79, 82));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_closeButtonMouseExited

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        new Client(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void arrowBackButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arrowBackButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        arrowBackgroundButton.setVisible(true);
    }//GEN-LAST:event_arrowBackButtonMouseEntered

    private void arrowBackButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arrowBackButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        arrowBackgroundButton.setVisible(false);
    }//GEN-LAST:event_arrowBackButtonMouseExited

    private void arrowBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrowBackButtonActionPerformed
        new Client(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_arrowBackButtonActionPerformed

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
            java.util.logging.Logger.getLogger(NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        private boolean showBorder = false; // Por padrão, não mostra borda
        private Color borderColor = new Color(105, 105, 105);

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        // Método para ativar a borda apenas quando necessário
        public void setShowBorder(boolean mostrar) {
            this.showBorder = mostrar;
            repaint();
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            // Pinta o fundo
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

            // Pinta a borda apenas se estiver ativado
            if (showBorder) {
                g2.setColor(borderColor);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            }

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

     class RoundedTextFieldSearch extends javax.swing.JTextField {
        private String placeholderText = "";

        public RoundedTextFieldSearch() {
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
    private javax.swing.JPanel Ellipse1;
    private javax.swing.JPanel Ellipse2;
    private javax.swing.JPanel Ellipse3;
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
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel cancelButtonLabel;
    private javax.swing.JPanel cancelButtonPanel;
    private javax.swing.JTextField cellphoneField;
    private javax.swing.JTextField clientnameField;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel closeButtonIcon;
    private javax.swing.JPanel closeButtonPanel;
    private javax.swing.JLabel containCellphoneLabel;
    private javax.swing.JLabel containClientNameLabel;
    private javax.swing.JLabel containEmailLabel;
    private javax.swing.JLabel containObservationLabel;
    private javax.swing.JLabel containServiceLabel;
    private javax.swing.JLabel containTelephoneLabel;
    private javax.swing.JLabel containTypeOfPersonLabel;
    private javax.swing.JLabel containTypeOfServiceLabel;
    private javax.swing.JLabel defaultCellphoneLabel;
    private javax.swing.JLabel defaultClientNameLabel;
    private javax.swing.JLabel defaultEmailLabel;
    private javax.swing.JLabel defaultObservationLabel;
    private javax.swing.JLabel defaultServiceLabel;
    private javax.swing.JLabel defaultTelephoneLabel;
    private javax.swing.JLabel defaultTypeOfPersonLabel;
    private javax.swing.JLabel defaultTypeOfServiceLabel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JPanel horizontalDivider;
    private javax.swing.JLabel hoverCellphoneLabel;
    private javax.swing.JLabel hoverClientNameLabel;
    private javax.swing.JLabel hoverEmailLabel;
    private javax.swing.JLabel hoverObservationLabel;
    private javax.swing.JLabel hoverServiceLabel;
    private javax.swing.JLabel hoverTelephoneLabel;
    private javax.swing.JLabel hoverTypeOfPersonLabel;
    private javax.swing.JLabel hoverTypeOfServiceLabel;
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
    private javax.swing.JLabel lblIconTask;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoText;
    private javax.swing.JTextField observationField;
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
    private javax.swing.JLabel registerButtonLabel;
    private javax.swing.JPanel registerButtonPanel;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel searchFieldIcon;
    private javax.swing.JTextField serviceField;
    private javax.swing.JTextField telephoneField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField typeofpersonField;
    private javax.swing.JTextField typeofserviceField;
    private javax.swing.JLabel userNameIcon;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}
