package screen;

import Data.IconUtil;
import Data.PermissaoUtil;
import Data.Usuario;
import com.google.api.services.calendar.model.Event;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TelaEventoTable extends javax.swing.JFrame {

    private final Usuario usuarioLogado;
    private JPanel calendarPanel;
    private JPanel miniCalendarPanel;
    private int anoAtual, mesAtual;

    public TelaEventoTable(Usuario usuario) {
        this.usuarioLogado = usuario;
        this.anoAtual = LocalDate.now().getYear();
        this.mesAtual = LocalDate.now().getMonthValue();
        initComponents();
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
        criarCalendario();
        criarMiniCalendario();
        atualizarLabelMes();
        atualizarLabelDataCalendario(LocalDate.now());
        configurarBotoes();
        setIcon();
        setResizable(false);
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
    }

    private void criarCalendario() {
        calendarPanel = new JPanel(new GridLayout(0, 7));
        calendarPanel.setOpaque(false);
        atualizarCalendario(anoAtual, mesAtual);
        jPanel1.setLayout(new BorderLayout());
        jPanel1.setOpaque(false);
        jPanel1.add(calendarPanel, BorderLayout.CENTER);
    }

    private void criarMiniCalendario() {
        miniCalendarPanel = new JPanel(new GridLayout(7, 7));
        miniCalendarPanel.setOpaque(false);
        atualizarMiniCalendario(anoAtual, mesAtual);
        jPanel2.setLayout(new BorderLayout());
        jPanel2.setOpaque(false);
        jPanel2.add(miniCalendarPanel, BorderLayout.CENTER);
    }

    private void atualizarCalendario(int ano, int mes) {
        calendarPanel.removeAll();

        String[] diasSemana = {"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"};
        for (String dia : diasSemana) {
            JLabel label = new JLabel(dia, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setForeground(Color.WHITE);
            calendarPanel.add(label);
        }

        LocalDate primeiroDiaDoMes = LocalDate.of(ano, mes, 1);
        int diasNoMes = YearMonth.of(ano, mes).lengthOfMonth();
        int primeiroDiaSemana = primeiroDiaDoMes.getDayOfWeek().getValue();
        primeiroDiaSemana = primeiroDiaSemana % 7;

        List<Event> eventos = GoogleCalendarEvents.getEvents();

        LocalDate ultimoDiaMesAnterior = primeiroDiaDoMes.minusDays(1);
        int diasDoMesAnterior = primeiroDiaSemana;

        for (int i = diasDoMesAnterior - 1; i >= 0; i--) {
            LocalDate diaAnterior = ultimoDiaMesAnterior.minusDays(i);
            JPanel diaPanel = criarDiaPanel(diaAnterior.getDayOfMonth(), diaAnterior, eventos);
            calendarPanel.add(diaPanel);
        }

        for (int dia = 1; dia <= diasNoMes; dia++) {
            LocalDate dataAtual = LocalDate.of(ano, mes, dia);
            JPanel diaPanel = criarDiaPanel(dia, dataAtual, eventos);
            calendarPanel.add(diaPanel);
        }

        int totalDias = primeiroDiaSemana + diasNoMes;
        int diasFaltando = (6 * 7) - totalDias;

        LocalDate diaFuturo = LocalDate.of(ano, mes, diasNoMes);
        for (int i = 1; i <= diasFaltando; i++) {
            diaFuturo = diaFuturo.plusDays(1);
            JPanel diaPanel = criarDiaPanel(diaFuturo.getDayOfMonth(), diaFuturo, eventos);
            calendarPanel.add(diaPanel);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
        atualizarLabelMes();
    }

    private void atualizarMiniCalendario(int ano, int mes) {
        miniCalendarPanel.removeAll();
        miniCalendarPanel.setLayout(new GridLayout(7, 7));
        preencherMiniCalendario(ano, mes, 10);
        miniCalendarPanel.revalidate();
        miniCalendarPanel.repaint();
    }

    private void preencherMiniCalendario(int ano, int mes, int fontSize) {
        miniCalendarPanel.removeAll();
        String[] diasSemana = {"S", "T", "Q", "Q", "S", "S", "D"};

        for (String dia : diasSemana) {
            JLabel label = new JLabel(dia, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, fontSize));
            label.setForeground(Color.WHITE);
            miniCalendarPanel.add(label);
        }

        LocalDate hoje = LocalDate.now();
        LocalDate primeiroDiaDoMes = LocalDate.of(ano, mes, 1);
        int diasNoMes = YearMonth.of(ano, mes).lengthOfMonth();
        int primeiroDiaSemana = (primeiroDiaDoMes.getDayOfWeek().getValue() + 6) % 7;

        LocalDate ultimoDiaMesAnterior = primeiroDiaDoMes.minusDays(1);
        int diasMesAnterior = YearMonth.of(ultimoDiaMesAnterior.getYear(), ultimoDiaMesAnterior.getMonthValue()).lengthOfMonth();
        for (int i = primeiroDiaSemana - 1; i >= 0; i--) {
            JLabel label = new JLabel(String.valueOf(diasMesAnterior - i), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, fontSize));
            label.setForeground(Color.GRAY);
            miniCalendarPanel.add(label);
        }

        for (int dia = 1; dia <= diasNoMes; dia++) {
            LocalDate dataAtual = LocalDate.of(ano, mes, dia);
            JLabel labelDia = new JLabel(String.valueOf(dia), SwingConstants.CENTER);
            labelDia.setFont(new Font("Arial", Font.BOLD, fontSize));

            if (dataAtual.equals(hoje)) {
                labelDia.setForeground(Color.YELLOW);
            } else {
                labelDia.setForeground(Color.WHITE);
            }

            miniCalendarPanel.add(labelDia);
        }

        int totalDiasMostrados = miniCalendarPanel.getComponentCount();
        int diasExtras = 49 - totalDiasMostrados;

        for (int i = 1; i <= diasExtras; i++) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, fontSize));
            label.setForeground(Color.GRAY);
            jSeparator1.setForeground(Color.LIGHT_GRAY);
            miniCalendarPanel.add(label);
        }

        miniCalendarPanel.revalidate();
        miniCalendarPanel.repaint();
    }

    private void atualizarLabelMes() {
        String nomeMes = LocalDate.of(anoAtual, mesAtual, 1).format(DateTimeFormatter.ofPattern("MMMM yyyy"));
        lblMes.setText(nomeMes.substring(0, 1).toUpperCase() + nomeMes.substring(1));
    }

    private void configurarBotoes() {
        btnSetaEsquerda.addActionListener(e -> {
            if (mesAtual == 1) {
                mesAtual = 12;
                anoAtual--;
            } else {
                mesAtual--;
            }
            atualizarCalendario(anoAtual, mesAtual);
            atualizarMiniCalendario(anoAtual, mesAtual);
        });

        btnSetaDireita.addActionListener(e -> {
            if (mesAtual == 12) {
                mesAtual = 1;
                anoAtual++;
            } else {
                mesAtual++;
            }
            atualizarCalendario(anoAtual, mesAtual);
            atualizarMiniCalendario(anoAtual, mesAtual);
        });
    }

    private JPanel criarDiaPanel(int dia, LocalDate dataAtual, List<Event> eventos) {
        JPanel diaPanel = new JPanel();
        diaPanel.setLayout(new BorderLayout());
        diaPanel.setOpaque(false);
        diaPanel.setBorder(BorderFactory.createMatteBorder(2, 1, 1, 2, Color.LIGHT_GRAY));

        JLabel labelDia = new JLabel(String.valueOf(dia));
        labelDia.setFont(new Font("Arial", Font.BOLD, 14));

        if (dataAtual.equals(LocalDate.now())) {
            labelDia.setForeground(Color.YELLOW);
        } else {
            labelDia.setForeground(Color.WHITE);
        }

        diaPanel.add(labelDia, BorderLayout.NORTH);

        JPanel eventosPanel = new JPanel();
        eventosPanel.setLayout(new BoxLayout(eventosPanel, BoxLayout.Y_AXIS));
        eventosPanel.setOpaque(false);

        for (Event event : eventos) {
            LocalDate dataEvento = null;
            String horario = "";

            if (event.getStart().getDate() != null) {
                dataEvento = LocalDate.parse(event.getStart().getDate().toString());
            } else if (event.getStart().getDateTime() != null) {
                Instant instant = Instant.ofEpochMilli(event.getStart().getDateTime().getValue());
                dataEvento = LocalDate.ofInstant(instant, ZoneId.systemDefault());
                horario = DateTimeFormatter.ofPattern("HH:mm").format(instant.atZone(ZoneId.systemDefault()).toLocalTime());
            }

            if (dataEvento != null && dataEvento.equals(dataAtual)) {
                JLabel eventLabel = new JLabel((horario.isEmpty() ? "" : horario + " - ") + event.getSummary());
                eventLabel.setForeground(new Color(255, 255, 70));
                eventLabel.setFont(new Font("Consolas", Font.PLAIN, 12));

                eventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        exibirDetalhesEvento(event);
                    }
                });

                eventosPanel.add(eventLabel);
            }
        }

        diaPanel.add(eventosPanel, BorderLayout.CENTER);
        return diaPanel;
    }

    private void exibirDetalhesEvento(Event event) {
        JPanel overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        overlayPanel.setOpaque(false);
        overlayPanel.setLayout(null);
        getLayeredPane().add(overlayPanel, JLayeredPane.POPUP_LAYER);

        SwingUtilities.invokeLater(() -> {
            overlayPanel.setBounds(0, 0, getWidth(), getHeight());
            overlayPanel.setVisible(true);
        });

        JDialog dialog = new JDialog(this, true);
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout());
        dialog.setBackground(new Color(0, 0, 0, 0));

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(5, 27, 74));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2d.dispose();
            }
        };
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500, 160));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));

        JLabel titleLabel = new JLabel(event.getSummary(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);

        JButton closeXButton = new JButton("X");
        closeXButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeXButton.setForeground(Color.GRAY);
        closeXButton.setContentAreaFilled(false);
        closeXButton.setBorderPainted(false);
        closeXButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeXButton.addActionListener(e -> {
            dialog.dispose();
            overlayPanel.setVisible(false);
        });

        topPanel.add(titleLabel, BorderLayout.CENTER);
        topPanel.add(closeXButton, BorderLayout.EAST);

        String descricao = event.getDescription() != null ? event.getDescription() : "Não especificado";
        String local = event.getLocation() != null ? event.getLocation() : "Não especificado";
        String data = event.getStart().getDateTime() != null
                ? DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(
                        Instant.ofEpochMilli(event.getStart().getDateTime().getValue()).atZone(ZoneId.systemDefault()))
                : "Não especificado";

        JLabel detailsLabel = new JLabel("<html>" + descricao + " | " + local + " | " + data + "</html>");
        detailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        detailsLabel.setForeground(Color.LIGHT_GRAY);
        detailsLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setOpaque(false);

        JButton deleteButton = new JButton("Deletar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(201, 53, 42));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteButton.setContentAreaFilled(false);
        deleteButton.setOpaque(false);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        deleteButton.addActionListener(e -> {
            dialog.dispose();
            overlayPanel.setVisible(false);
            getLayeredPane().remove(overlayPanel);
            repaint();
        });

        JButton closeButton = new JButton("Fechar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(80, 80, 80));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };
        closeButton.setFont(new Font("Arial", Font.BOLD, 12));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.setContentAreaFilled(false);
        closeButton.setOpaque(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        closeButton.addActionListener(e -> {
            dialog.dispose();
            overlayPanel.setVisible(false);
            getLayeredPane().remove(overlayPanel);
            repaint();
        });

        buttonPanel.add(deleteButton);
        buttonPanel.add(closeButton);

        SwingUtilities.invokeLater(() -> {
            dialog.pack();

            int preferredHeight = detailsLabel.getPreferredSize().height;

            int newHeight = Math.max(160, preferredHeight + 100);
            dialog.setSize(new Dimension(500, newHeight));

            Point parentLocation = this.getLocationOnScreen();
            Dimension parentSize = this.getSize();
            Dimension dialogSize = dialog.getSize();

            int newX = parentLocation.x + (parentSize.width - dialogSize.width) / 2;
            int newY = parentLocation.y + (parentSize.height - dialogSize.height) / 2 - 0;

            dialog.setLocation(newX, newY);
        });

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(detailsLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(mainPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

    }

    private void atualizarLabelDataCalendario(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
        String dataFormatada = data.format(formatter);
        dataFormatada = Character.toUpperCase(dataFormatada.charAt(0)) + dataFormatada.substring(1);
        lblDataCalendario.setText(dataFormatada);
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
        lblCTCONTAB = new javax.swing.JLabel();
        lblContabilidade = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        lblDataCalendario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCriarEvento = new javax.swing.JButton();
        lblMes = new javax.swing.JLabel();
        lblMesAnterior = new javax.swing.JLabel();
        lblProximoMes = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        Background = new javax.swing.JLabel();
        btnSetaDireita = new javax.swing.JButton();
        btnSetaEsquerda = new javax.swing.JButton();

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

        lblCTCONTAB.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCTCONTAB.setForeground(new java.awt.Color(200, 200, 200));
        lblCTCONTAB.setText("CT CONTAB");
        getContentPane().add(lblCTCONTAB);
        lblCTCONTAB.setBounds(90, 7, 190, 40);

        lblContabilidade.setBackground(new java.awt.Color(204, 204, 204));
        lblContabilidade.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        lblContabilidade.setForeground(new java.awt.Color(153, 153, 0));
        lblContabilidade.setText("Contabilidade & Consultoria");
        getContentPane().add(lblContabilidade);
        lblContabilidade.setBounds(90, 7, 205, 80);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(1236, 205, 2, 432);

        jSeparator2.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(311, 205, 3, 432);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255, 12));
        getContentPane().add(jPanel3);
        jPanel3.setBounds(310, 200, 930, 440);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanel4);
        jPanel4.setBounds(90, 210, 200, 250);

        jSeparator1.setBackground(java.awt.Color.lightGray);
        jSeparator1.setForeground(java.awt.Color.lightGray);
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray));
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(90, 243, 200, 220);

        lblDataCalendario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDataCalendario.setForeground(new java.awt.Color(153, 153, 153));
        lblDataCalendario.setText("Sexta Feira, Fevereiro 28, 2025");
        getContentPane().add(lblDataCalendario);
        lblDataCalendario.setBounds(90, 120, 510, 50);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Calendário");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(90, 100, 140, 40);

        jPanel2.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 3, true));
        jPanel2.setForeground(java.awt.Color.lightGray);
        getContentPane().add(jPanel2);
        jPanel2.setBounds(90, 205, 200, 260);

        btnCriarEvento.setBackground(new java.awt.Color(51, 51, 51));
        btnCriarEvento.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnCriarEvento.setForeground(new java.awt.Color(255, 255, 255));
        btnCriarEvento.setText("+ Novo Evento");
        btnCriarEvento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        btnCriarEvento.setBorderPainted(false);
        btnCriarEvento.setContentAreaFilled(false);
        btnCriarEvento.setFocusPainted(false);
        btnCriarEvento.setOpaque(true);
        btnCriarEvento.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCriarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarEventoActionPerformed(evt);
            }
        });
        getContentPane().add(btnCriarEvento);
        btnCriarEvento.setBounds(1100, 100, 140, 40);

        lblMes.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        lblMes.setForeground(new java.awt.Color(205, 168, 16));
        lblMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMes.setText("NOVEMBRO 2024");
        getContentPane().add(lblMes);
        lblMes.setBounds(400, 20, 570, 50);

        lblMesAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/seta-redondaE.png"))); // NOI18N
        getContentPane().add(lblMesAnterior);
        lblMesAnterior.setBounds(400, 10, 120, 70);

        lblProximoMes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/seta-redondaD.png"))); // NOI18N
        getContentPane().add(lblProximoMes);
        lblProximoMes.setBounds(920, 10, 120, 70);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(310, 130, 930, 510);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(4, 10, 60, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -50, 80, 750);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minhatura-de-perfil.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1210, 15, 50, 50);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 80, 750);

        btnNotificacoes.setForeground(new java.awt.Color(205, 168, 16));
        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alert-bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1160, 10, 60, 60);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-contabil.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1280, 711);
        Background.getAccessibleContext().setAccessibleName("");

        btnSetaDireita.setText("jButton1");
        getContentPane().add(btnSetaDireita);
        btnSetaDireita.setBounds(910, 10, 70, 70);

        btnSetaEsquerda.setText("jButton1");
        getContentPane().add(btnSetaEsquerda);
        btnSetaEsquerda.setBounds(390, 10, 70, 70);

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCriarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarEventoActionPerformed
        new TelaEvento(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCriarEventoActionPerformed

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

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaEventoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEventoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEventoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEventoTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel JPanelTelaAcesso1;
    private javax.swing.JButton btnAdministracao;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracoes;
    private javax.swing.JButton btnCriarEvento;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnSetaDireita;
    private javax.swing.JButton btnSetaEsquerda;
    private javax.swing.JButton btnTarefas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblCTCONTAB;
    private javax.swing.JLabel lblContabilidade;
    private javax.swing.JLabel lblDataCalendario;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMes;
    private javax.swing.JLabel lblMesAnterior;
    private javax.swing.JLabel lblProximoMes;
    private javax.swing.JLabel lblUserIcon;
    // End of variables declaration//GEN-END:variables
}
