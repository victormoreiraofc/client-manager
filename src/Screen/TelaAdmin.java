package screen;

import Data.IconUtil;
import Data.Usuario;
import java.awt.Color;
import java.awt.Toolkit;
import Screen.FonteUtils;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Data.CTCONTAB;
import java.awt.Insets;
import java.awt.event.MouseListener;

public class TelaAdmin extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private int mouseX, mouseY;

    public TelaAdmin(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        carregarAuditoriaDoBanco();

        addHoverLabel(btnDashboard, "Dashboard");
        addHoverLabel(btnCalendario, "Calendário");
        addHoverLabel(btnClientes, "Clientes");
        addHoverLabel(btnRelatorios, "Relatórios");
        addHoverLabel(btnTarefas, "Tarefas");
        addHoverLabel(btnConfiguracoes, "Configuração");
        addHoverLabel(btnAdministracao, "Administração");
        addHoverLabel(btnNotificacoes, "Notificações");
        addHoverLabel(btnInfo, "Ajuda");
        addHoverLabel(btnUserIcon, usuarioLogado.getUsuario());

        try {
            java.net.URL url = getClass().getResource("/images/Close Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Close Icon.png ou src/images/Close Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(11, 11, java.awt.Image.SCALE_SMOOTH);
                btnFecharTela.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            btnFecharTela.setText("X"); // fallback
        }

        try {
            java.net.URL url = getClass().getResource("/images/Maximize Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Maximize Icon.png ou src/images/Maximize Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(11, 11, java.awt.Image.SCALE_SMOOTH);
                btnMaximizarTela.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            btnMaximizarTela.setText("[]"); // fallback
        }

        try {
            java.net.URL url = getClass().getResource("/images/Minimize Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Minimize Icon.png ou src/images/Minimize Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(11, 2, java.awt.Image.SCALE_SMOOTH);
                btnMinimizarTela.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            btnMinimizarTela.setText("-"); // fallback
        }

        try {
            java.net.URL url = getClass().getResource("/images/Divider Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Divider Icon.png ou src/images/Divider Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(2, 11, java.awt.Image.SCALE_SMOOTH);
                lblDivisorTela.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblDivisorTela.setText("|"); // fallback
        }

        try {
            java.net.URL url = getClass().getResource("/images/Information Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Information Icon.png ou src/images/Information Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(13, 13, java.awt.Image.SCALE_SMOOTH);
                btnInfo.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            btnInfo.setText("?"); // fallback
        }

        try {
            java.net.URL url = getClass().getResource("/images/Logo Icon.png");
            if (url == null) {
                System.err
                        .println("Imagem não encontrada. Verifique: /images/Logo Icon.png ou src/images/Logo Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
                lblLogo.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblLogo.setText("LOGO");
        }

        try {
            java.net.URL url = getClass().getResource("/images/Dashboard Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Dashboard Icon.png ou src/images/Dashboard Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnDashboard.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnDashboard, "/images/Dashboard Icon.png", "/images/Dashboard Icon Hover.png", 22,
                        22);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Calendar Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Calendar Icon.png ou src/images/Calendar Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnCalendario.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnCalendario, "/images/Calendar Icon.png", "/images/Calendar Icon Hover.png", 22, 22);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Client Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Client Icon.png ou src/images/Client Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnClientes.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnClientes, "/images/Client Icon.png", "/images/Client Icon Hover.png", 22, 22);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Tasks Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Tasks Icon.png ou src/images/Tasks Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnTarefas.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnTarefas, "/images/Tasks Icon.png", "/images/Tasks Icon Hover.png", 22, 22);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Settings Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Settings Icon.png ou src/images/Settings Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnConfiguracoes.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnConfiguracoes, "/images/Settings Icon.png", "/images/Settings Icon Hover.png", 22,
                        22);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Report Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Report Icon.png ou src/images/Report Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnRelatorios.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnRelatorios, "/images/Report Icon.png", "/images/Report Icon  Hover.png", 22, 22);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Administrative Icon Active.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Administrative Icon Active.png ou src/images/Administrative Icon Active.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnAdministracao.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnAdministracao, "/images/Administrative Icon Active.png",
                        "/images/Administrative Icon Hover.png", 22, 22);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Notification Bell.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Notification Bell.png ou src/images/Notification Bell.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnNotificacoes.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnNotificacoes, "/images/Notification Bell.png",
                        "/images/Notification Bell Hover.png", 22, 22);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setUndecorated(true); // Remove a barra superior.
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
        setIcon();
        setResizable(false); // Impede o redimencionamento da tela.
    }

    private void addHoverLabel(JButton botao, String texto) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(7, 30, 70)); // fundo azul escuro
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // cantos arredondados
                super.paintComponent(g2);
                g2.dispose();
            }
        };

        label.setOpaque(false); // importante pra deixar o fundo transparente pro desenho funcionar
        label.setForeground(Color.WHITE);
        label.setFont(FonteUtils.carregarSofiaSansBlack(13f));
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVisible(false);

        botao.getParent().add(label, 0);

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setSize(label.getPreferredSize());
                label.setVisible(true);
                botao.getParent().repaint();

                if (botao == btnUserIcon) {
                    label.setLocation(
                            botao.getParent().getWidth() - label.getWidth() - 10,
                            botao.getY() + botao.getHeight() + 5);
                } else if (botao == btnNotificacoes || botao == btnInfo) {
                    label.setLocation(
                            botao.getX() + (botao.getWidth() - label.getWidth()) / 2,
                            botao.getY() + botao.getHeight() + 5);
                } else {
                    label.setLocation(
                            botao.getX() + botao.getWidth() + 30,
                            botao.getY() + (botao.getHeight() - label.getHeight()) / 2);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setVisible(false);
            }
        });

    }

    private void aplicarHoverIcon(javax.swing.JComponent componente,
            String caminhoNormal,
            String caminhoHover,
            int largura,
            int altura) {
        try {
            // Ícone normal
            java.net.URL urlNormal = getClass().getResource(caminhoNormal);
            java.awt.Image imgNormal = javax.imageio.ImageIO.read(urlNormal)
                    .getScaledInstance(largura, altura, java.awt.Image.SCALE_SMOOTH);
            javax.swing.ImageIcon iconNormal = new javax.swing.ImageIcon(imgNormal);

            // Ícone de hover
            java.net.URL urlHover = getClass().getResource(caminhoHover);
            java.awt.Image imgHover = javax.imageio.ImageIO.read(urlHover)
                    .getScaledInstance(largura, altura, java.awt.Image.SCALE_SMOOTH);
            javax.swing.ImageIcon iconHover = new javax.swing.ImageIcon(imgHover);

            if (componente instanceof JLabel lbl) {
                lbl.setIcon(iconNormal);

                lbl.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        lbl.setIcon(iconHover);
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        lbl.setIcon(iconNormal);
                    }
                });

            } else if (componente instanceof JButton btn) {
                btn.setIcon(iconNormal);

                btn.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        btn.setIcon(iconHover);
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        btn.setIcon(iconNormal);
                    }
                });
            }
        } catch (Exception ex) {
            System.err.println("Erro ao carregar ícones: " + caminhoNormal + " / " + caminhoHover);
            ex.printStackTrace();
        }
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
    }

    private void carregarAuditoriaDoBanco() {
        jTextArea1.setText("");

        String sql = "SELECT usuario, acao, data_hora FROM auditoria ORDER BY id DESC";

        try {
            Connection conn = CTCONTAB.conectar();
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String linha = rs.getString("usuario")
                        + " — " + rs.getString("acao")
                        + " (" + rs.getString("data_hora") + ")\n";

                jTextArea1.append(linha);
            }

            rs.close();
            st.close();
            conn.close();

            jTextArea1.setCaretPosition(0);

        } catch (Exception e) {
            jTextArea1.setText("Erro ao carregar auditoria.");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnUserIcon = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();
        btnMinimizarTela = new javax.swing.JButton();
        btnMaximizarTela = new javax.swing.JButton();
        btnFecharTela = new javax.swing.JButton();
        lblLogoTexto = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblTituloPagina = new javax.swing.JLabel();
        lblDivisorTela = new javax.swing.JLabel();
        lblBarraSuperior = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnDashboard = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnAdministracao = new javax.swing.JButton();
        lblBarraLateral = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CT Contab Manager");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        btnCancelar = new javax.swing.JButton();

        btnCancelar = new JButton("Cancelar") {

            private final int radius = 12;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // fundo
                g2.setColor(new Color(28, 46, 74)); // #1C2E4A
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // borda (#2A3E61)
                g2.setColor(new Color(42, 62, 97));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

                g2.dispose();
            }
        };

        btnCancelar.setUI(new BasicButtonUI());

        btnCancelar.setOpaque(false);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setRolloverEnabled(false);

        btnCancelar.setFont(FonteUtils.carregarRalewayMedium(13f));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setMargin(new Insets(10, 20, 10, 20));

        btnCancelar.addActionListener(e -> {
            new TelaMenu(usuarioLogado).setVisible(true);
            this.dispose();
        });

        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(1110, 660, 120, 35);

        btnVoltar = new JButton("Voltar") {

            private final int radius = 12;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(0x2D9CDB));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        btnVoltar.setUI(new BasicButtonUI());

        btnVoltar.setOpaque(false);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setRolloverEnabled(false);

        btnVoltar.setFont(FonteUtils.carregarRalewayMedium(13f));
        btnVoltar.setForeground(Color.BLACK);

        btnVoltar.setMargin(new Insets(10, 20, 10, 20));

        btnVoltar.addActionListener(e -> {
            new TelaAdminTable(usuarioLogado).setVisible(true);
            this.dispose();
        });
        getContentPane().add(btnVoltar);
        btnVoltar.setBounds(1240, 660, 160, 35);

        btnUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Administrative Icon.png"))); // NOI18N
        btnUserIcon.setContentAreaFilled(false);
        btnUserIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserIconActionPerformed(evt);
            }
        });
        getContentPane().add(btnUserIcon);
        btnUserIcon.setBounds(1390, 30, 50, 50);

        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Information Icon.png"))); // NOI18N
        btnInfo.setContentAreaFilled(false);
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });
        getContentPane().add(btnInfo);
        btnInfo.setBounds(1305, 0, 15, 25);

        btnMinimizarTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minimize Icon.png"))); // NOI18N
        btnMinimizarTela.setContentAreaFilled(false);
        btnMinimizarTela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizarTelaMouseClicked(evt);
            }
        });
        btnMinimizarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnMinimizarTela);
        btnMinimizarTela.setBounds(1355, 0, 15, 25);

        btnMaximizarTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Maximize Icon.png"))); // NOI18N
        btnMaximizarTela.setContentAreaFilled(false);
        btnMaximizarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizarTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnMaximizarTela);
        btnMaximizarTela.setBounds(1390, 0, 15, 25);

        btnFecharTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        btnFecharTela.setContentAreaFilled(false);
        btnFecharTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnFecharTela);
        btnFecharTela.setBounds(1425, 0, 15, 25);

        lblLogoTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Text Icon.png"))); // NOI18N
        getContentPane().add(lblLogoTexto);
        lblLogoTexto.setBounds(80, 35, 176, 46);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Icon.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(15, 35, 40, 40);

        lblTituloPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPagina.setText("AUDITORIA");
        lblTituloPagina.setFont(FonteUtils.carregarRoboto(13f));
        getContentPane().add(lblTituloPagina);
        lblTituloPagina.setBounds(720, 3, 120, 20);

        lblDivisorTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        lblDivisorTela.setPreferredSize(new java.awt.Dimension(13, 13));
        getContentPane().add(lblDivisorTela);
        lblDivisorTela.setBounds(1335, 0, 15, 25);

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

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255, 15));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255, 15));
        jScrollPane1.setDoubleBuffered(true);

        jTextArea1.setBackground(new java.awt.Color(22, 40, 66));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255, 15));
        jTextArea1.setRows(5);
        jTextArea1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 62, 97), 5, true));
        jTextArea1.setCaretColor(new java.awt.Color(42, 62, 97));
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.setEditable(false);
        jTextArea1.setFocusable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setFont(FonteUtils.carregarRaleway(13f));
        jTextArea1.setForeground(Color.WHITE);
        jTextArea1.setOpaque(false);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(0, 0)); // Oculta a barra de rolagem
        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(42, 62, 97), 5, true), // borda arredondada e fixa
                javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0)));

        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(98, 100, 1320, 610);

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

        lblBarraLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DEEP-OCEAN Color.png"))); // NOI18N
        getContentPane().add(lblBarraLateral);
        lblBarraLateral.setBounds(0, 0, 70, 750);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1390, 30, 512, 50);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Notification Bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1340, 35, 40, 40);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Background.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1450, 750);

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDashboardActionPerformed
        new TelaMenu(usuarioLogado).setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnDashboardActionPerformed

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCalendarioActionPerformed
        new TelaEventoTable(usuarioLogado).setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnCalendarioActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnClientesActionPerformed
        new TelaClienteTable(usuarioLogado).setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnClientesActionPerformed

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRelatoriosActionPerformed
        new TelaRelatorioTable(usuarioLogado).setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnRelatoriosActionPerformed

    private void btnTarefasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnTarefasActionPerformed
        new TelaTarefaTable(usuarioLogado).setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnTarefasActionPerformed

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnConfiguracoesActionPerformed
        new TelaConfiguracao(usuarioLogado).setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnConfiguracoesActionPerformed

    private void btnAdministracaoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAdministracaoActionPerformed
        new TelaAdminTable(usuarioLogado).setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnAdministracaoActionPerformed

    private void btnFecharTelaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFecharTelaActionPerformed
        System.exit(0);
    }// GEN-LAST:event_btnFecharTelaActionPerformed

    private void btnMaximizarTelaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMaximizarTelaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnMaximizarTelaActionPerformed

    private void btnMinimizarTelaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMinimizarTelaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnMinimizarTelaActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnInfoActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseClicked

    }// GEN-LAST:event_formMouseClicked

    private void btnMinimizarTelaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnMinimizarTelaMouseClicked
        setState(javax.swing.JFrame.ICONIFIED);
    }// GEN-LAST:event_btnMinimizarTelaMouseClicked

    private void lblBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblBarraSuperiorMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }// GEN-LAST:event_lblBarraSuperiorMousePressed

    private void lblBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblBarraSuperiorMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - mouseX, y - mouseY);
    }// GEN-LAST:event_lblBarraSuperiorMouseDragged

    private void btnUserIconActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnUserIconActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnUserIconActionPerformed

    private void btnVoltarMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnVoltarMouseEntered
        btnVoltar.setBackground(new java.awt.Color(20, 190, 115));
    }// GEN-LAST:event_btnVoltarMouseEntered

    private void btnVoltarMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnVoltarMouseExited
        btnVoltar.setBackground(new java.awt.Color(17, 168, 100));
    }// GEN-LAST:event_btnVoltarMouseExited

    private void btnVoltarMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnVoltarMousePressed
        btnVoltar.setBackground(new java.awt.Color(14, 140, 85));
    }// GEN-LAST:event_btnVoltarMousePressed

    private void btnVoltarMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnVoltarMouseReleased
        btnVoltar.setBackground(new java.awt.Color(17, 168, 100));
    }// GEN-LAST:event_btnVoltarMouseReleased

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnVoltarActionPerformed

    }// GEN-LAST:event_btnVoltarActionPerformed

    private void btnVoltarKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_btnVoltarKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            btnVoltar.doClick();
        }
    }// GEN-LAST:event_btnVoltarKeyPressed

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnCancelarMouseEntered
        // TODO add your handling code here:
    }// GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnCancelarMouseExited
        // TODO add your handling code here:
    }// GEN-LAST:event_btnCancelarMouseExited

    private void btnCancelarMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnCancelarMousePressed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnCancelarMousePressed

    private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnCancelarMouseReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_btnCancelarMouseReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_btnCancelarKeyPressed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnCancelarKeyPressed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnAdministracao;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracoes;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnFecharTela;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnMaximizarTela;
    private javax.swing.JButton btnMinimizarTela;
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnTarefas;
    private javax.swing.JButton btnUserIcon;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblBarraLateral;
    private javax.swing.JLabel lblBarraSuperior;
    private javax.swing.JLabel lblDivisorTela;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoTexto;
    private javax.swing.JLabel lblTituloPagina;
    private javax.swing.JLabel lblUserIcon;
    // End of variables declaration//GEN-END:variables
}
