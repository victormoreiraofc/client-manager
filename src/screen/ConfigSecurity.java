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

public class ConfigSecurity extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private final Color COR_PADRAO = new Color(28, 37, 54);
    private final Color COR_HOVER_GERAL = new Color(50, 63, 89);
    private final Color COR_HOVER_FECHAR = new Color(196, 43, 28);
    private javax.swing.Timer timerVoltarHome;
    private final Color COR_ABA_ATIVA = new Color(205, 168, 12);
    private final Color COR_ABA_INATIVA = new Color(209, 213, 219);
    private final Color COR_BARRA_VAZIA = new Color(37, 49, 71);
    private final Color COR_FORCA_FRACA = new Color(220, 68, 80);
    private final Color COR_FORCA_FORTE = new Color(45, 128, 71);
    private final Color COR_FORCA_MEDIA = new Color(230, 126, 34);
    private final Color COR_FORCA_BOA = new Color(163, 190, 40);
    private static final int TAMANHO_MAX_SENHA = 50;

    public ConfigSecurity(Usuario usuario) {
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

        configurarCampos();
        configurarAbas();
        arredondarPaineis();
        atualizarForcaSenha();
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
            BufferedImage TipIcon1 = ImageIO.read(getClass().getResource("/images/Green Check Icon.png"));
            BufferedImage TipIcon2 = ImageIO.read(getClass().getResource("/images/Green Check Icon.png"));
            BufferedImage TipIcon3 = ImageIO.read(getClass().getResource("/images/Green Check Icon.png"));
            BufferedImage TipIcon4 = ImageIO.read(getClass().getResource("/images/Green Check Icon.png"));

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
            TipIconLabel1.setIcon(gerarIconePerfeito(TipIcon1, 20, 20));
            TipIconLabel2.setIcon(gerarIconePerfeito(TipIcon2, 20, 20));
            TipIconLabel3.setIcon(gerarIconePerfeito(TipIcon3, 20, 20));
            TipIconLabel4.setIcon(gerarIconePerfeito(TipIcon4, 20, 20));

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
        ProfileIconNameLabel.setText(I18nManager.getString("auth.security.title"));
        ActualPasswordLabel.setText(I18nManager.getString("auth.security.field.current"));
        NewPasswordLabel.setText(I18nManager.getString("auth.security.field.new"));
        ConfirmNewPasswordLabel.setText(I18nManager.getString("auth.security.field.confirm"));
        ForcePasswordLabel.setText(I18nManager.getString("auth.security.strength.title"));
        TitleTipLabel.setText(I18nManager.getString("auth.security.tips.title"));
        TipLabel1.setText(I18nManager.getString("auth.security.tips.length"));
        TipLabel2.setText(I18nManager.getString("auth.security.tips.case"));
        TipLabel3.setText(I18nManager.getString("auth.security.tips.numbers_symbols"));
        TipLabel4.setText(I18nManager.getString("auth.security.tips.personal"));
        registerButtonLabel.setText(I18nManager.getString("auth.config.button.save"));
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
        SecurityLabel.setForeground(COR_ABA_ATIVA);
        SecuritySeparator.setVisible(true);

        ProfileLabel.setForeground(COR_ABA_INATIVA);
        ProfileSeparator.setVisible(false);
        PreferencesLabel.setForeground(COR_ABA_INATIVA);
        PreferencesSeparator.setVisible(false);
        AccountLabel.setForeground(COR_ABA_INATIVA);
        AccountSeparator.setVisible(false);

        ProfileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                irParaPerfil();
            }
        });

        java.awt.event.MouseAdapter cliqueParaHome = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                irParaHome();
            }
        };
        PreferencesLabel.addMouseListener(cliqueParaHome);
        AccountLabel.addMouseListener(cliqueParaHome);
    }

    private void configurarCampos() {
        javax.swing.JTextField[] campos = {ActualPasswordField, NewPasswordField, ConfirmNewPasswordField};
        for (javax.swing.JTextField campo : campos) {
            campo.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                    campo.getBorder(),
                    javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        }

        NewPasswordField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                atualizarForcaSenha();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                atualizarForcaSenha();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                atualizarForcaSenha();
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
        EditInfoPanel = arredondarPainel(EditInfoPanel, 15);
        EditInfoPanel1 = arredondarPainel(EditInfoPanel1, 15);

        PasswordForcePanel1 = arredondarPainel(PasswordForcePanel1, 10);
        PasswordForcePanel2 = arredondarPainel(PasswordForcePanel2, 10);
        PasswordForcePanel3 = arredondarPainel(PasswordForcePanel3, 10);
        PasswordForcePanel4 = arredondarPainel(PasswordForcePanel4, 10);

        PasswordForceTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PasswordForceTitleLabel.setBounds(856, 521, 50, 14);

        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
    }

    private boolean[] avaliarCriterios(String senha) {
        boolean tamanho = senha.length() >= 8;
        boolean minuscula = false;
        boolean maiuscula = false;
        boolean numero = false;
        boolean simbolo = false;
        for (char ch : senha.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                minuscula = true;
            } else if (Character.isUpperCase(ch)) {
                maiuscula = true;
            } else if (Character.isDigit(ch)) {
                numero = true;
            } else if (!Character.isWhitespace(ch)) {
                simbolo = true;
            }
        }
        boolean pessoal = !senha.isEmpty() && !contemDadosPessoais(senha);
        return new boolean[]{tamanho, minuscula && maiuscula, numero && simbolo, pessoal};
    }

    private boolean contemDadosPessoais(String senha) {
        String minuscula = senha.toLowerCase();
        String usuario = usuarioLogado.getUsuario() == null ? "" : usuarioLogado.getUsuario().toLowerCase();
        String email = usuarioLogado.getEmail() == null ? "" : usuarioLogado.getEmail().toLowerCase();
        String parteEmail = email.contains("@") ? email.substring(0, email.indexOf('@')) : email;

        return (usuario.length() >= 3 && minuscula.contains(usuario))
                || (parteEmail.length() >= 3 && minuscula.contains(parteEmail));
    }

    private String textoForca(String chave, String padrao) {
        try {
            String texto = I18nManager.getString(chave);
            if (texto != null && !texto.trim().isEmpty() && !texto.equals(chave) && !texto.contains(chave)) {
                return texto;
            }
        } catch (Exception ignored) {
        }
        return padrao;
    }

    private void atualizarForcaSenha() {
        boolean[] criterios = avaliarCriterios(NewPasswordField.getText());
        int atendidos = 0;
        for (boolean criterio : criterios) {
            if (criterio) {
                atendidos++;
            }
        }

        // Nivel 1 (Fraco) a 4 (Forte). Senha vazia ou com 0/1 criterio = Fraco.
        int nivel = Math.max(1, atendidos);

        Color[] coresNivel = {COR_FORCA_FRACA, COR_FORCA_MEDIA, COR_FORCA_BOA, COR_FORCA_FORTE};
        String[] textosNivel = {
            textoForca("auth.security.strength.weak", "Fraco"),
            textoForca("auth.security.strength.medium", "Médio"),
            textoForca("auth.security.strength.good", "Bom"),
            textoForca("auth.security.strength.strong", "Forte")
        };

        Color corAtual = coresNivel[nivel - 1];
        javax.swing.JPanel[] barras = {PasswordForcePanel1, PasswordForcePanel2, PasswordForcePanel3, PasswordForcePanel4};

        for (int i = 0; i < barras.length; i++) {
            barras[i].setBackground(i < nivel ? corAtual : COR_BARRA_VAZIA);
            barras[i].repaint();
        }

        PasswordForceTitleLabel.setText(textosNivel[nivel - 1]);
        PasswordForceTitleLabel.setForeground(corAtual);
        PasswordForceTitleLabel.repaint();
    }

    private void limparCampos() {
        ActualPasswordField.setText("");
        NewPasswordField.setText("");
        ConfirmNewPasswordField.setText("");
        atualizarForcaSenha();
    }

    private void handlePasswordSave() {
        String atual = ActualPasswordField.getText();
        String nova = NewPasswordField.getText();
        String confirmacao = ConfirmNewPasswordField.getText();

        if (atual.isEmpty() || nova.isEmpty() || confirmacao.isEmpty()) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.security.error.empty_fields"));
            return;
        }
        if (nova.matches(".*\\s.*")) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.security.error.whitespace"));
            return;
        }
        if (nova.length() > TAMANHO_MAX_SENHA) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.security.error.too_long"));
            return;
        }
        if (!nova.equals(confirmacao)) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.security.error.mismatch"));
            return;
        }
        if (nova.equals(atual)) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.security.error.same_as_current"));
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            if (!CTCONTAB.verificarSenhaAtual(usuarioLogado.getUsuario(), atual)) {
                MensagemUtil.exibirErro(I18nManager.getString("auth.security.error.wrong_current"));
                return;
            }

            if (CTCONTAB.atualizarSenha(usuarioLogado.getUsuario(), nova)) {
                usuarioLogado.setSenha(nova);
                AuditUtil.registrar(usuarioLogado.getUsuario(), "audit.profile_password_changed", usuarioLogado.getUsuario());
                limparCampos();
                MensagemUtil.exibirSucesso(I18nManager.getString("auth.security.save_success"));
            }
        } catch (Exception ex) {
            MensagemUtil.exibirErro(I18nManager.getString("auth.security.save_error"));
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registerButton = new javax.swing.JButton();
        registerButtonLabel = new javax.swing.JLabel();
        registerButtonPanel = new RoundedPanel(40);
        PasswordForceTitleLabel = new javax.swing.JLabel();
        PasswordForcePanel4 = new javax.swing.JPanel();
        PasswordForcePanel3 = new javax.swing.JPanel();
        PasswordForcePanel2 = new javax.swing.JPanel();
        PasswordForcePanel1 = new javax.swing.JPanel();
        TipIconLabel4 = new javax.swing.JLabel();
        TipIconLabel3 = new javax.swing.JLabel();
        TipIconLabel2 = new javax.swing.JLabel();
        TipIconLabel1 = new javax.swing.JLabel();
        TipLabel4 = new javax.swing.JLabel();
        TipLabel3 = new javax.swing.JLabel();
        TipLabel2 = new javax.swing.JLabel();
        TipLabel1 = new javax.swing.JLabel();
        arrowBackButton = new javax.swing.JButton();
        arrowBackgroundButton = new RoundedPanel(30);
        TitleTipLabel = new javax.swing.JLabel();
        ConfirmNewPasswordField = new RoundedTextField();
        NewPasswordField = new RoundedTextField();
        ActualPasswordField = new RoundedTextField();
        ForcePasswordLabel = new javax.swing.JLabel();
        ConfirmNewPasswordLabel = new javax.swing.JLabel();
        NewPasswordLabel = new javax.swing.JLabel();
        ActualPasswordLabel = new javax.swing.JLabel();
        ProfileIconNameLabel = new javax.swing.JLabel();
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

        PasswordForceTitleLabel.setFont(FonteUtils.carregarRobotoRegular(13f));
        PasswordForceTitleLabel.setForeground(new java.awt.Color(45, 128, 71));
        PasswordForceTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        PasswordForceTitleLabel.setText("Forte");
        PasswordForceTitleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PasswordForceTitleLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PasswordForceTitleLabelMouseExited(evt);
            }
        });
        getContentPane().add(PasswordForceTitleLabel);
        PasswordForceTitleLabel.setBounds(850, 521, 40, 14);

        PasswordForcePanel4.setBackground(new java.awt.Color(37, 49, 71));
        getContentPane().add(PasswordForcePanel4);
        PasswordForcePanel4.setBounds(716, 524, 131, 10);

        PasswordForcePanel3.setBackground(new java.awt.Color(45, 128, 71));
        getContentPane().add(PasswordForcePanel3);
        PasswordForcePanel3.setBounds(575, 524, 131, 10);

        PasswordForcePanel2.setBackground(new java.awt.Color(45, 128, 71));
        getContentPane().add(PasswordForcePanel2);
        PasswordForcePanel2.setBounds(434, 524, 131, 10);

        PasswordForcePanel1.setBackground(new java.awt.Color(45, 128, 71));
        getContentPane().add(PasswordForcePanel1);
        PasswordForcePanel1.setBounds(293, 524, 131, 10);

        TipIconLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel4);
        TipIconLabel4.setBounds(944, 431, 20, 20);

        TipIconLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel3);
        TipIconLabel3.setBounds(944, 395, 20, 20);

        TipIconLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel2);
        TipIconLabel2.setBounds(944, 359, 20, 20);

        TipIconLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(TipIconLabel1);
        TipIconLabel1.setBounds(944, 323, 20, 20);

        TipLabel4.setFont(FonteUtils.carregarRobotoRegular(12f));
        TipLabel4.setForeground(new java.awt.Color(209, 213, 219));
        TipLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TipLabel4.setText("Evite informações pessoais");
        TipLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TipLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TipLabel4MouseExited(evt);
            }
        });
        getContentPane().add(TipLabel4);
        TipLabel4.setBounds(975, 434, 260, 14);

        TipLabel3.setFont(FonteUtils.carregarRobotoRegular(12f));
        TipLabel3.setForeground(new java.awt.Color(209, 213, 219));
        TipLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TipLabel3.setText("Inclua números e simbolos");
        TipLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TipLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TipLabel3MouseExited(evt);
            }
        });
        getContentPane().add(TipLabel3);
        TipLabel3.setBounds(975, 398, 260, 14);

        TipLabel2.setFont(FonteUtils.carregarRobotoRegular(12f));
        TipLabel2.setForeground(new java.awt.Color(209, 213, 219));
        TipLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TipLabel2.setText("Inclua letras maiúsculas e minúsculas");
        TipLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TipLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TipLabel2MouseExited(evt);
            }
        });
        getContentPane().add(TipLabel2);
        TipLabel2.setBounds(975, 362, 260, 14);

        TipLabel1.setFont(FonteUtils.carregarRobotoRegular(12f));
        TipLabel1.setForeground(new java.awt.Color(209, 213, 219));
        TipLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TipLabel1.setText("Use pelo menos 8 caracteristicas");
        TipLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TipLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TipLabel1MouseExited(evt);
            }
        });
        getContentPane().add(TipLabel1);
        TipLabel1.setBounds(975, 326, 260, 14);

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

        TitleTipLabel.setFont(FonteUtils.carregarRobotoSemiBold(15f));
        TitleTipLabel.setForeground(new java.awt.Color(209, 213, 219));
        TitleTipLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TitleTipLabel.setText("Dicas para uma senha segura");
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
        TitleTipLabel.setBounds(944, 284, 290, 20);

        ConfirmNewPasswordField.setBackground(new java.awt.Color(25, 25, 25));
        ConfirmNewPasswordField.setFont(FonteUtils.carregarRobotoBold(13f));
        ConfirmNewPasswordField.setForeground(new java.awt.Color(209, 213, 219));
        ConfirmNewPasswordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        ConfirmNewPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ConfirmNewPasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ConfirmNewPasswordFieldFocusLost(evt);
            }
        });
        ConfirmNewPasswordField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ConfirmNewPasswordFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ConfirmNewPasswordFieldMousePressed(evt);
            }
        });
        ConfirmNewPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmNewPasswordFieldActionPerformed(evt);
            }
        });
        ConfirmNewPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ConfirmNewPasswordFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ConfirmNewPasswordFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ConfirmNewPasswordFieldKeyTyped(evt);
            }
        });
        getContentPane().add(ConfirmNewPasswordField);
        ConfirmNewPasswordField.setBounds(293, 425, 600, 35);

        NewPasswordField.setBackground(new java.awt.Color(25, 25, 25));
        NewPasswordField.setFont(FonteUtils.carregarRobotoBold(13f));
        NewPasswordField.setForeground(new java.awt.Color(209, 213, 219));
        NewPasswordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        NewPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NewPasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                NewPasswordFieldFocusLost(evt);
            }
        });
        NewPasswordField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NewPasswordFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NewPasswordFieldMousePressed(evt);
            }
        });
        NewPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPasswordFieldActionPerformed(evt);
            }
        });
        NewPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NewPasswordFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NewPasswordFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NewPasswordFieldKeyTyped(evt);
            }
        });
        getContentPane().add(NewPasswordField);
        NewPasswordField.setBounds(293, 342, 600, 35);

        ActualPasswordField.setBackground(new java.awt.Color(25, 25, 25));
        ActualPasswordField.setFont(FonteUtils.carregarRobotoBold(13f));
        ActualPasswordField.setForeground(new java.awt.Color(209, 213, 219));
        ActualPasswordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 37, 54)));
        ActualPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ActualPasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ActualPasswordFieldFocusLost(evt);
            }
        });
        ActualPasswordField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ActualPasswordFieldMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ActualPasswordFieldMousePressed(evt);
            }
        });
        ActualPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualPasswordFieldActionPerformed(evt);
            }
        });
        ActualPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ActualPasswordFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ActualPasswordFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ActualPasswordFieldKeyTyped(evt);
            }
        });
        getContentPane().add(ActualPasswordField);
        ActualPasswordField.setBounds(293, 259, 600, 35);

        ForcePasswordLabel.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        ForcePasswordLabel.setForeground(new java.awt.Color(209, 213, 219));
        ForcePasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ForcePasswordLabel.setText("Força da Senha");
        ForcePasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ForcePasswordLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ForcePasswordLabelMouseExited(evt);
            }
        });
        getContentPane().add(ForcePasswordLabel);
        ForcePasswordLabel.setBounds(293, 500, 330, 14);

        ConfirmNewPasswordLabel.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        ConfirmNewPasswordLabel.setForeground(new java.awt.Color(209, 213, 219));
        ConfirmNewPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ConfirmNewPasswordLabel.setText("Confirmar Nova Senha");
        ConfirmNewPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ConfirmNewPasswordLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ConfirmNewPasswordLabelMouseExited(evt);
            }
        });
        getContentPane().add(ConfirmNewPasswordLabel);
        ConfirmNewPasswordLabel.setBounds(293, 407, 330, 14);

        NewPasswordLabel.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        NewPasswordLabel.setForeground(new java.awt.Color(209, 213, 219));
        NewPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NewPasswordLabel.setText("Nova Senha");
        NewPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NewPasswordLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NewPasswordLabelMouseExited(evt);
            }
        });
        getContentPane().add(NewPasswordLabel);
        NewPasswordLabel.setBounds(293, 324, 330, 14);

        ActualPasswordLabel.setFont(FonteUtils.carregarRobotoSemiBold(13f));
        ActualPasswordLabel.setForeground(new java.awt.Color(209, 213, 219));
        ActualPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ActualPasswordLabel.setText("Senha Atual");
        ActualPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ActualPasswordLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ActualPasswordLabelMouseExited(evt);
            }
        });
        getContentPane().add(ActualPasswordLabel);
        ActualPasswordLabel.setBounds(293, 241, 330, 14);

        ProfileIconNameLabel.setFont(FonteUtils.carregarRobotoSemiBold(15f));
        ProfileIconNameLabel.setForeground(new java.awt.Color(209, 213, 219));
        ProfileIconNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ProfileIconNameLabel.setText("Alterar Senha");
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
        ProfileIconNameLabel.setBounds(293, 193, 330, 20);

        EditInfoPanel1.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(EditInfoPanel1);
        EditInfoPanel1.setBounds(931, 269, 317, 204);

        EditInfoPanel.setBackground(new java.awt.Color(28, 37, 54));
        getContentPane().add(EditInfoPanel);
        EditInfoPanel.setBounds(276, 175, 633, 392);

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
    }//GEN-LAST:event_SecurityButtonMouseEntered

    private void SecurityButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SecurityButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        SecurityLabel.setForeground(COR_ABA_ATIVA);
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

    private void ProfileIconNameLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileIconNameLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileIconNameLabelMouseEntered

    private void ProfileIconNameLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProfileIconNameLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ProfileIconNameLabelMouseExited

    private void ActualPasswordLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualPasswordLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ActualPasswordLabelMouseEntered

    private void ActualPasswordLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualPasswordLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ActualPasswordLabelMouseExited

    private void NewPasswordLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewPasswordLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordLabelMouseEntered

    private void NewPasswordLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewPasswordLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordLabelMouseExited

    private void ConfirmNewPasswordLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfirmNewPasswordLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmNewPasswordLabelMouseEntered

    private void ConfirmNewPasswordLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfirmNewPasswordLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmNewPasswordLabelMouseExited

    private void ActualPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ActualPasswordFieldFocusGained

    }//GEN-LAST:event_ActualPasswordFieldFocusGained

    private void ActualPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ActualPasswordFieldFocusLost

    }//GEN-LAST:event_ActualPasswordFieldFocusLost

    private void ActualPasswordFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualPasswordFieldMouseExited

    }//GEN-LAST:event_ActualPasswordFieldMouseExited

    private void ActualPasswordFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualPasswordFieldMousePressed

    }//GEN-LAST:event_ActualPasswordFieldMousePressed

    private void ActualPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualPasswordFieldActionPerformed

    }//GEN-LAST:event_ActualPasswordFieldActionPerformed

    private void ActualPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ActualPasswordFieldKeyPressed

    }//GEN-LAST:event_ActualPasswordFieldKeyPressed

    private void ActualPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ActualPasswordFieldKeyReleased

    }//GEN-LAST:event_ActualPasswordFieldKeyReleased

    private void ActualPasswordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ActualPasswordFieldKeyTyped

    }//GEN-LAST:event_ActualPasswordFieldKeyTyped

    private void NewPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NewPasswordFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordFieldFocusGained

    private void NewPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NewPasswordFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordFieldFocusLost

    private void NewPasswordFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewPasswordFieldMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordFieldMouseExited

    private void NewPasswordFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewPasswordFieldMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordFieldMousePressed

    private void NewPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordFieldActionPerformed

    private void NewPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NewPasswordFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordFieldKeyPressed

    private void NewPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NewPasswordFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordFieldKeyReleased

    private void NewPasswordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NewPasswordFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordFieldKeyTyped

    private void ConfirmNewPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ConfirmNewPasswordFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmNewPasswordFieldFocusGained

    private void ConfirmNewPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ConfirmNewPasswordFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmNewPasswordFieldFocusLost

    private void ConfirmNewPasswordFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfirmNewPasswordFieldMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmNewPasswordFieldMouseExited

    private void ConfirmNewPasswordFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConfirmNewPasswordFieldMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmNewPasswordFieldMousePressed

    private void ConfirmNewPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmNewPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmNewPasswordFieldActionPerformed

    private void ConfirmNewPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConfirmNewPasswordFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmNewPasswordFieldKeyPressed

    private void ConfirmNewPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConfirmNewPasswordFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmNewPasswordFieldKeyReleased

    private void ConfirmNewPasswordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConfirmNewPasswordFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmNewPasswordFieldKeyTyped

    private void ForcePasswordLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ForcePasswordLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ForcePasswordLabelMouseEntered

    private void ForcePasswordLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ForcePasswordLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ForcePasswordLabelMouseExited

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

    private void TipLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipLabel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TipLabel2MouseEntered

    private void TipLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipLabel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TipLabel2MouseExited

    private void TipLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipLabel3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TipLabel3MouseEntered

    private void TipLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipLabel3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TipLabel3MouseExited

    private void TipLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipLabel4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TipLabel4MouseEntered

    private void TipLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipLabel4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TipLabel4MouseExited

    private void PasswordForceTitleLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordForceTitleLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordForceTitleLabelMouseEntered

    private void PasswordForceTitleLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordForceTitleLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordForceTitleLabelMouseExited

    private void registerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseEntered

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_registerButtonMouseEntered

    private void registerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseExited

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_registerButtonMouseExited

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        handlePasswordSave();
    }//GEN-LAST:event_registerButtonActionPerformed

    private void registerButtonPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonPanelMouseEntered

    }//GEN-LAST:event_registerButtonPanelMouseEntered

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
            java.util.logging.Logger.getLogger(ConfigSecurity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigSecurity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigSecurity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigSecurity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JTextField ActualPasswordField;
    private javax.swing.JLabel ActualPasswordLabel;
    private javax.swing.JTextField ConfirmNewPasswordField;
    private javax.swing.JLabel ConfirmNewPasswordLabel;
    private javax.swing.JPanel EditInfoPanel;
    private javax.swing.JPanel EditInfoPanel1;
    private javax.swing.JPanel Ellipse1;
    private javax.swing.JPanel Ellipse2;
    private javax.swing.JPanel Ellipse3;
    private javax.swing.JLabel ForcePasswordLabel;
    private javax.swing.JTextField NewPasswordField;
    private javax.swing.JLabel NewPasswordLabel;
    private javax.swing.JPanel PasswordForcePanel1;
    private javax.swing.JPanel PasswordForcePanel2;
    private javax.swing.JPanel PasswordForcePanel3;
    private javax.swing.JPanel PasswordForcePanel4;
    private javax.swing.JLabel PasswordForceTitleLabel;
    private javax.swing.JButton PreferencesButton;
    private javax.swing.JLabel PreferencesLabel;
    private javax.swing.JPanel PreferencesSeparator;
    private javax.swing.JButton ProfileButton;
    private javax.swing.JLabel ProfileIconNameLabel;
    private javax.swing.JLabel ProfileLabel;
    private javax.swing.JPanel ProfileSeparator;
    private javax.swing.JButton SecurityButton;
    private javax.swing.JLabel SecurityLabel;
    private javax.swing.JPanel SecuritySeparator;
    private javax.swing.JPanel Separator;
    private javax.swing.JLabel TipIconLabel1;
    private javax.swing.JLabel TipIconLabel2;
    private javax.swing.JLabel TipIconLabel3;
    private javax.swing.JLabel TipIconLabel4;
    private javax.swing.JLabel TipLabel1;
    private javax.swing.JLabel TipLabel2;
    private javax.swing.JLabel TipLabel3;
    private javax.swing.JLabel TipLabel4;
    private javax.swing.JLabel TitleTipLabel;
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
    private javax.swing.JLabel registerButtonLabel;
    private javax.swing.JPanel registerButtonPanel;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
