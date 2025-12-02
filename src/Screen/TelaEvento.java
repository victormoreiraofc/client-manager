package screen;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import javax.swing.JOptionPane;


import Data.CTCONTAB;
import Data.IconUtil;
import Data.PermissaoUtil;
import Data.Usuario;
import Screen.MensagemUtil;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class TelaEvento extends javax.swing.JFrame {

    private static final Logger logger = LoggerFactory.getLogger(TelaEvento.class);

    private Usuario usuarioLogado;
    private String sessionId;

    public TelaEvento(Usuario usuario) {
        this.usuarioLogado = usuario;
        this.sessionId = gerarSessionId(usuario.getUsuario());
        initComponents();
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
        setIcon();
        setResizable(false);
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
    }

    private String gerarSessionId(String usuario) {
        try {
            String input = usuario + "_session_fixed";
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.substring(0, 32);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        }
    }

    private void salvarEventoNoBanco() {
        MDC.put("usuario", usuarioLogado.getUsuario());

        try {
            String evento = txtTarefa.getText();
            String dataInicio = txtDataInicial.getText();
            String dataFinal = txtDataFinal.getText();
            String horarioInicial = txtHorarioInicial.getText();
            String horarioFinal = txtHorarioFinal.getText();
            String descricao = txtDescricaoEvento.getText();
            String local = txtLocalEvento.getText();

            if (usuarioLogado != null) {
                String nomeUsuario = usuarioLogado.getUsuario();

                String dataInicioFormatada = formatarDataParaBanco(dataInicio);
                String dataFinalFormatada = formatarDataParaBanco(dataFinal);

                CTCONTAB.registrarEvento(evento, dataInicioFormatada, dataFinalFormatada, horarioInicial, horarioFinal, nomeUsuario);
                MensagemUtil.exibirSucesso("Evento salvo com sucesso!");
                logger.info("criou um novo evento");
                enviarEventoParaN8N(evento, dataInicioFormatada, dataFinalFormatada, horarioInicial, horarioFinal, descricao, local);
            } else {
                MensagemUtil.exibirErro("Nenhum usuário logado. Não foi possível salvar o evento.");
            }
        } catch (Exception e) {
            MensagemUtil.exibirErro("Erro ao salvar evento!");
        }
    }

    private String formatarDataParaBanco(String data) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = inputFormat.parse(data);
        return outputFormat.format(date);
    }

    private void enviarEventoParaN8N(String titulo, String dataInicio, String dataFinal, String horarioInicial, String horarioFinal, String descricao, String local) {
        try {
            String chatInput = "Crie o evento '" + titulo + "' no dia " + dataInicio + " até " + dataFinal + " das " + horarioInicial + " às " + horarioFinal + ", no local: " + local + ". Descrição: " + descricao;

            String webhookUrl = "http://localhost:5678/webhook-test/71b9c7a6-3252-49e9-a503-6f640d930498";

            String jsonInputString = "{"
                    + "\"sessionId\": \"" + sessionId + "\","
                    + "\"action\": \"sendMessage\","
                    + "\"chatInput\": \"" + chatInput + "\""
                    + "}";

            URL url = new URL(webhookUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Evento enviado com sucesso!");
            } else {
                System.out.println("Erro ao enviar evento: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHome = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnAdministracao = new javax.swing.JButton();
        JPanelTelaAcesso1 = new javax.swing.JPanel();
        lblContabilidade = new javax.swing.JLabel();
        lblCTCONTAB = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtTarefa = new javax.swing.JTextField();
        btnLogin1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtDataFinal = new javax.swing.JTextField();
        txtHorarioFinal = new javax.swing.JTextField();
        lblAte = new javax.swing.JLabel();
        txtDataInicial = new javax.swing.JTextField();
        txtHorarioInicial = new javax.swing.JTextField();
        txtDescricaoEvento = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtLocalEvento = new javax.swing.JTextField();
        lblEventoEReuniao = new javax.swing.JLabel();
        lblTarefa = new javax.swing.JLabel();
        txtTitulo1 = new javax.swing.JTextField();
        checkboxInicio = new java.awt.Checkbox();
        checkbox2 = new java.awt.Checkbox();
        lblAte1 = new javax.swing.JLabel();
        lblAte2 = new javax.swing.JLabel();
        lblAte3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlibLogo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CT Contab Manager");
        getContentPane().setLayout(null);

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home-menu.png"))); // NOI18N
        btnHome.setContentAreaFilled(false);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnHome);
        btnHome.setBounds(0, 120, 80, 50);

        btnCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calendar-menu.png"))); // NOI18N
        btnCalendario.setContentAreaFilled(false);
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalendario);
        btnCalendario.setBounds(0, 190, 80, 50);

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/client-menu.png"))); // NOI18N
        btnClientes.setContentAreaFilled(false);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnClientes);
        btnClientes.setBounds(5, 260, 70, 50);

        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/relatory-menu.png"))); // NOI18N
        btnRelatorios.setContentAreaFilled(false);
        btnRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosActionPerformed(evt);
            }
        });
        getContentPane().add(btnRelatorios);
        btnRelatorios.setBounds(0, 330, 80, 50);

        btnTarefas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/task-menu.png"))); // NOI18N
        btnTarefas.setContentAreaFilled(false);
        btnTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarefasActionPerformed(evt);
            }
        });
        getContentPane().add(btnTarefas);
        btnTarefas.setBounds(0, 400, 80, 50);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/configuration-menu.png"))); // NOI18N
        btnConfiguracoes.setContentAreaFilled(false);
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfiguracoes);
        btnConfiguracoes.setBounds(0, 470, 80, 50);

        btnAdministracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin-menu.png"))); // NOI18N
        btnAdministracao.setContentAreaFilled(false);
        btnAdministracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdministracao);
        btnAdministracao.setBounds(2, 530, 80, 70);

        JPanelTelaAcesso1.setBackground(new java.awt.Color(194, 166, 40));
        getContentPane().add(JPanelTelaAcesso1);
        JPanelTelaAcesso1.setBounds(0, 180, 80, 70);

        lblContabilidade.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        lblContabilidade.setForeground(new java.awt.Color(153, 153, 0));
        lblContabilidade.setText("Contabilidade & Consultoria");
        getContentPane().add(lblContabilidade);
        lblContabilidade.setBounds(90, 7, 205, 80);

        lblCTCONTAB.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCTCONTAB.setForeground(new java.awt.Color(200, 200, 200));
        lblCTCONTAB.setText("CT CONTAB");
        getContentPane().add(lblCTCONTAB);
        lblCTCONTAB.setBounds(90, 7, 190, 40);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alert-bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1160, 10, 60, 60);

        jPanel1.setBackground(new java.awt.Color(5, 27, 74));
        jPanel1.setLayout(null);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete-icon.png"))); // NOI18N
        jPanel1.add(jLabel13);
        jLabel13.setBounds(1020, 10, 30, 30);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save-icon.png"))); // NOI18N
        jPanel1.add(jLabel14);
        jLabel14.setBounds(1060, 10, 30, 30);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fechar.png"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(1100, 10, 30, 30);

        jButton3.setBackground(new java.awt.Color(84, 84, 84));
        jPanel1.add(jButton3);
        jButton3.setBounds(1020, 10, 30, 30);

        jButton2.setBackground(new java.awt.Color(126, 217, 87));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(1060, 10, 30, 30);

        jLabel4.setBackground(new java.awt.Color(153, 153, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(205, 168, 16));
        jLabel4.setText("Criar um novo evento");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 10, 240, 30);

        btnLogin.setBackground(new java.awt.Color(0, 0, 102));
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Cancelar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin);
        btnLogin.setBounds(770, 550, 170, 40);

        txtTarefa.setBackground(new java.awt.Color(4, 21, 57));
        txtTarefa.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTarefa.setForeground(new java.awt.Color(115, 115, 115));
        txtTarefa.setText("O que irão fazer durante o evento ?");
        txtTarefa.setToolTipText("RARARARAR");
        txtTarefa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTarefaActionPerformed(evt);
            }
        });
        jPanel1.add(txtTarefa);
        txtTarefa.setBounds(30, 130, 560, 30);

        btnLogin1.setBackground(new java.awt.Color(0, 102, 153));
        btnLogin1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogin1.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin1.setText("Criar Evento");
        btnLogin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogin1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin1);
        btnLogin1.setBounds(950, 550, 170, 40);

        jButton1.setBackground(new java.awt.Color(239, 65, 54));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(1100, 10, 30, 30);

        txtDataFinal.setBackground(new java.awt.Color(4, 21, 57));
        txtDataFinal.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtDataFinal.setForeground(new java.awt.Color(115, 115, 115));
        txtDataFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataFinal.setText("  __/__/____                  ");
        txtDataFinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtDataFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataFinalActionPerformed(evt);
            }
        });
        jPanel1.add(txtDataFinal);
        txtDataFinal.setBounds(250, 200, 170, 30);

        txtHorarioFinal.setBackground(new java.awt.Color(4, 21, 57));
        txtHorarioFinal.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtHorarioFinal.setForeground(new java.awt.Color(115, 115, 115));
        txtHorarioFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHorarioFinal.setText("__:__                     ");
        txtHorarioFinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtHorarioFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHorarioFinalActionPerformed(evt);
            }
        });
        jPanel1.add(txtHorarioFinal);
        txtHorarioFinal.setBounds(710, 200, 150, 30);

        lblAte.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAte.setForeground(new java.awt.Color(255, 255, 255));
        lblAte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAte.setText("Hora de encerramento*");
        jPanel1.add(lblAte);
        lblAte.setBounds(700, 170, 150, 30);

        txtDataInicial.setBackground(new java.awt.Color(4, 21, 57));
        txtDataInicial.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtDataInicial.setForeground(new java.awt.Color(115, 115, 115));
        txtDataInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataInicial.setText("          __/__/____                         ");
        txtDataInicial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtDataInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataInicialActionPerformed(evt);
            }
        });
        jPanel1.add(txtDataInicial);
        txtDataInicial.setBounds(30, 200, 170, 30);

        txtHorarioInicial.setBackground(new java.awt.Color(4, 21, 57));
        txtHorarioInicial.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtHorarioInicial.setForeground(new java.awt.Color(115, 115, 115));
        txtHorarioInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHorarioInicial.setText("__:__                ");
        txtHorarioInicial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtHorarioInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHorarioInicialActionPerformed(evt);
            }
        });
        jPanel1.add(txtHorarioInicial);
        txtHorarioInicial.setBounds(500, 200, 130, 30);

        txtDescricaoEvento.setBackground(new java.awt.Color(4, 21, 57));
        txtDescricaoEvento.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtDescricaoEvento.setForeground(new java.awt.Color(115, 115, 115));
        txtDescricaoEvento.setText("Adicione uma descrição. Descreva o evento ou a reunião que vaia contecer. \n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // NOI18N
        txtDescricaoEvento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtDescricaoEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoEventoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDescricaoEvento);
        txtDescricaoEvento.setBounds(30, 390, 1090, 150);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Descrição do Evento");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(30, 350, 150, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Local ");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(30, 270, 120, 30);

        txtLocalEvento.setBackground(new java.awt.Color(4, 21, 57));
        txtLocalEvento.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtLocalEvento.setForeground(new java.awt.Color(115, 115, 115));
        txtLocalEvento.setText("Local do evento");
        txtLocalEvento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtLocalEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLocalEventoActionPerformed(evt);
            }
        });
        jPanel1.add(txtLocalEvento);
        txtLocalEvento.setBounds(30, 300, 450, 40);

        lblEventoEReuniao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEventoEReuniao.setForeground(new java.awt.Color(255, 255, 255));
        lblEventoEReuniao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEventoEReuniao.setText("Evento ou Reunião *");
        jPanel1.add(lblEventoEReuniao);
        lblEventoEReuniao.setBounds(30, 40, 120, 30);

        lblTarefa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTarefa.setForeground(new java.awt.Color(255, 255, 255));
        lblTarefa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTarefa.setText("Tarefa*");
        jPanel1.add(lblTarefa);
        lblTarefa.setBounds(20, 100, 60, 30);

        txtTitulo1.setBackground(new java.awt.Color(4, 21, 57));
        txtTitulo1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTitulo1.setForeground(new java.awt.Color(115, 115, 115));
        txtTitulo1.setText("Nome do Evento ou da reunião");
        txtTitulo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtTitulo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTitulo1ActionPerformed(evt);
            }
        });
        jPanel1.add(txtTitulo1);
        txtTitulo1.setBounds(30, 70, 560, 30);
        addPlaceholder(txtTarefa, "  Digite o título do evento");
        addPlaceholder(txtDataInicial, " 01/01/2025");
        addPlaceholder(txtDataFinal, " 01/01/2025");
        addPlaceholder(txtHorarioInicial, " 00:00");
        addPlaceholder(txtHorarioFinal, " 00:00");
        addPlaceholder(txtDescricaoEvento, "  O que será feito no evento?");
        addPlaceholder(txtLocalEvento, "  Onde será o evento?");

        checkboxInicio.setForeground(new java.awt.Color(255, 255, 255));
        checkboxInicio.setLabel("     Informar o Inicio*");
        jPanel1.add(checkboxInicio);
        checkboxInicio.setBounds(30, 240, 140, 20);

        checkbox2.setForeground(new java.awt.Color(255, 255, 255));
        checkbox2.setLabel("    Informar Término*");
        jPanel1.add(checkbox2);
        checkbox2.setBounds(250, 240, 140, 20);

        lblAte1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAte1.setForeground(new java.awt.Color(255, 255, 255));
        lblAte1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAte1.setText("Data inicio*");
        jPanel1.add(lblAte1);
        lblAte1.setBounds(0, 170, 120, 30);

        lblAte2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAte2.setForeground(new java.awt.Color(255, 255, 255));
        lblAte2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAte2.setText("Data limite*");
        jPanel1.add(lblAte2);
        lblAte2.setBounds(220, 170, 120, 30);

        lblAte3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAte3.setForeground(new java.awt.Color(255, 255, 255));
        lblAte3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAte3.setText("Hora*");
        jPanel1.add(lblAte3);
        lblAte3.setBounds(460, 170, 120, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(110, 70, 1140, 600);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 640, 80, 60);

        jlibLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jlibLogo2);
        jlibLogo2.setBounds(4, 10, 60, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -50, 80, 750);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minhatura-de-perfil.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1210, 15, 50, 50);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-contabil.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1280, 711);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Evento");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(430, 330, 100, 30);

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        new TelaEventoTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtHorarioInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHorarioInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHorarioInicialActionPerformed

    private void txtTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTarefaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarefaActionPerformed

    private void txtDataFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataFinalActionPerformed

    private void txtDataInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataInicialActionPerformed

    private void txtHorarioFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHorarioFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHorarioFinalActionPerformed

    private void btnLogin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin1ActionPerformed
        salvarEventoNoBanco();

        try {

            LocalDate dataInicial = LocalDate.parse(txtDataInicial.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dataFinal = LocalDate.parse(txtDataFinal.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            TelaEventoTable telaCalendario = new TelaEventoTable(usuarioLogado);
            telaCalendario.setVisible(true);

            telaCalendario.pintarDiasComEvento(dataInicial, dataFinal);

            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar calendário: " + e.getMessage());
        }

        
    }//GEN-LAST:event_btnLogin1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new TelaEventoTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        salvarEventoNoBanco();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtDescricaoEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoEventoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoEventoActionPerformed

    private void txtLocalEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLocalEventoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLocalEventoActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        new TelaMenu(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

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

    private void txtTitulo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTitulo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTitulo1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEvento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel JPanelTelaAcesso1;
    private javax.swing.JButton btnAdministracao;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracoes;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogin1;
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnTarefas;
    private java.awt.Checkbox checkbox2;
    private java.awt.Checkbox checkboxInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlibLogo2;
    private javax.swing.JLabel lblAte;
    private javax.swing.JLabel lblAte1;
    private javax.swing.JLabel lblAte2;
    private javax.swing.JLabel lblAte3;
    private javax.swing.JLabel lblCTCONTAB;
    private javax.swing.JLabel lblContabilidade;
    private javax.swing.JLabel lblEventoEReuniao;
    private javax.swing.JLabel lblTarefa;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JTextField txtDataFinal;
    private javax.swing.JTextField txtDataInicial;
    private javax.swing.JTextField txtDescricaoEvento;
    private javax.swing.JTextField txtHorarioFinal;
    private javax.swing.JTextField txtHorarioInicial;
    private javax.swing.JTextField txtLocalEvento;
    private javax.swing.JTextField txtTarefa;
    private javax.swing.JTextField txtTitulo1;
    // End of variables declaration//GEN-END:variables
}
