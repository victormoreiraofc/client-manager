package screen;

import Data.IconUtil;
import java.awt.*;
import javax.swing.*;
import Data.PermissaoUtil;
import Data.Usuario;
import Screen.FonteUtils;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class TelaConfiguracao extends javax.swing.JFrame {

    private static final Logger logger = LoggerFactory.getLogger(TelaConfiguracao.class);

    private Usuario usuarioLogado;
    private int mouseX, mouseY;

    public TelaConfiguracao(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();

        addHoverLabel(btnDashboard, "Dashboard");
        addHoverLabel(btnCalendario, "Calendário");
        addHoverLabel(btnClientes, "Clientes");
        addHoverLabel(btnRelatorios, "Relatórios");
        addHoverLabel(btnTarefas, "Tarefas");
        addHoverLabel(btnConfiguracoes, "Configuração");
        addHoverLabel(btnAdministracao, "Administração");

        try {
            java.net.URL url = getClass().getResource("/images/Close Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Close Icon.png ou src/images/Close Icon.png");
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
                System.err.println("Imagem não encontrada. Verifique: /images/Maximize Icon.png ou src/images/Maximize Icon.png");
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
                System.err.println("Imagem não encontrada. Verifique: /images/Minimize Icon.png ou src/images/Minimize Icon.png");
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
                System.err.println("Imagem não encontrada. Verifique: /images/Divider Icon.png ou src/images/Divider Icon.png");
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
                System.err.println("Imagem não encontrada. Verifique: /images/Information Icon.png ou src/images/Information Icon.png");
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
                System.err.println("Imagem não encontrada. Verifique: /images/Logo Icon.png ou src/images/Logo Icon.png");
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
                System.err.println("Imagem não encontrada. Verifique: /images/Dashboard Icon.png ou src/images/Dashboard Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnDashboard.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnDashboard, "/images/Dashboard Icon.png", "/images/Dashboard Icon Hover.png", 22, 22);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Calendar Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Calendar Icon.png ou src/images/Calendar Icon.png");
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
                System.err.println("Imagem não encontrada. Verifique: /images/Client Icon.png ou src/images/Client Icon.png");
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
                System.err.println("Imagem não encontrada. Verifique: /images/Tasks Icon.png ou src/images/Tasks Icon.png");
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
            java.net.URL url = getClass().getResource("/images/Settings Icon Active.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Settings Icon.png ou src/images/Settings Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnConfiguracoes.setIcon(new javax.swing.ImageIcon(
                        javax.imageio.ImageIO.read(getClass().getResource("/images/Settings Icon Active.png"))
                                .getScaledInstance(22, 22, Image.SCALE_SMOOTH)
                ));
                aplicarHoverIcon(btnConfiguracoes, "/images/Settings Icon Active.png", "/images/Settings Icon Hover.png", 22, 22);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Report Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Report Icon.png ou src/images/Report Icon.png");
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
            java.net.URL url = getClass().getResource("/images/Administrative Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Administrative Icon Active.png ou src/images/Administrative Icon Active.png");
            } else {
                url = getClass().getResource("/images/Administrative Icon.png");
                btnAdministracao.setIcon(new javax.swing.ImageIcon(
                        javax.imageio.ImageIO.read(url).getScaledInstance(22, 22, Image.SCALE_SMOOTH)
                ));
                aplicarHoverIcon(btnAdministracao, "/images/Administrative Icon.png", "/images/Administrative Icon Hover.png", 22, 22);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Notification Bell.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Notification Bell.png ou src/images/Notification Bell.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnNotificacoes.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnNotificacoes, "/images/Notification Bell.png", "/images/Notification Bell Hover.png", 22, 22);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setUndecorated(true); // Remove a barra superior.
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
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
                label.setLocation(botao.getX() + botao.getWidth() + 30, botao.getY() + (botao.getHeight() - label.getHeight()) / 2);
                label.setVisible(true);
                botao.getParent().repaint();
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

        jLabel2 = new javax.swing.JLabel();
        btnInfo = new javax.swing.JButton();
        btnMinimizarTela = new javax.swing.JButton();
        btnFecharTela = new javax.swing.JButton();
        btnMaximizarTela = new javax.swing.JButton();
        lblLogoTexto = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblTituloPagina = new javax.swing.JLabel();
        lblDivisorTela = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnAdministracao = new javax.swing.JButton();
        lblUserIcon = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        btnEditarSenha = new javax.swing.JButton();
        btnEditarNome = new javax.swing.JButton();
        btnAtualizarEmail = new javax.swing.JButton();
        lblBarraSuperior = new javax.swing.JLabel();
        lblBarraLateral = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configurações - CT CONTAB");
        getContentPane().setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 640, 80, 60);

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

        btnFecharTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        btnFecharTela.setContentAreaFilled(false);
        btnFecharTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnFecharTela);
        btnFecharTela.setBounds(1425, 0, 15, 25);

        btnMaximizarTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Maximize Icon.png"))); // NOI18N
        btnMaximizarTela.setContentAreaFilled(false);
        btnMaximizarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizarTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnMaximizarTela);
        btnMaximizarTela.setBounds(1390, 0, 15, 25);

        lblLogoTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Text Icon.png"))); // NOI18N
        getContentPane().add(lblLogoTexto);
        lblLogoTexto.setBounds(80, 35, 176, 46);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Icon.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(15, 35, 40, 40);

        lblTituloPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPagina.setText("Configuração");
        lblTituloPagina.setFont(FonteUtils.carregarRoboto(13f));
        getContentPane().add(lblTituloPagina);
        lblTituloPagina.setBounds(720, 3, 120, 20);

        lblDivisorTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        lblDivisorTela.setPreferredSize(new java.awt.Dimension(13, 13));
        getContentPane().add(lblDivisorTela);
        lblDivisorTela.setBounds(1335, 0, 15, 25);

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

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1390, 30, 512, 50);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Notification Bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1340, 35, 40, 40);

        btnEditarSenha.setBackground(new java.awt.Color(15, 25, 45));
        btnEditarSenha.setForeground(new java.awt.Color(153, 153, 0));
        btnEditarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Group 19.png"))); // NOI18N
        btnEditarSenha.setText("jButton1");
        btnEditarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditarSenha);
        btnEditarSenha.setBounds(990, 230, 270, 270);

        btnEditarNome.setBackground(new java.awt.Color(15, 25, 45));
        btnEditarNome.setForeground(new java.awt.Color(153, 153, 0));
        btnEditarNome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Group 18.png"))); // NOI18N
        btnEditarNome.setText("jButton1");
        btnEditarNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarNomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditarNome);
        btnEditarNome.setBounds(610, 230, 270, 270);

        btnAtualizarEmail.setBackground(new java.awt.Color(15, 25, 45));
        btnAtualizarEmail.setForeground(new java.awt.Color(153, 153, 0));
        btnAtualizarEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Frame 36.png"))); // NOI18N
        btnAtualizarEmail.setText("jButton1");
        btnAtualizarEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarEmailActionPerformed(evt);
            }
        });
        getContentPane().add(btnAtualizarEmail);
        btnAtualizarEmail.setBounds(230, 230, 270, 270);

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

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Background.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1450, 750);

        setSize(new java.awt.Dimension(1452, 708));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnEditarNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarNomeActionPerformed
        TelaAlterarUsuario popup = new TelaAlterarUsuario(this, usuarioLogado);
        popup.setVisible(true);
    }//GEN-LAST:event_btnEditarNomeActionPerformed

    private void btnAtualizarEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarEmailActionPerformed
        TelaAlterarEmail popup = new TelaAlterarEmail(this, usuarioLogado);
        popup.setVisible(true);
    }//GEN-LAST:event_btnAtualizarEmailActionPerformed

    private void btnEditarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarSenhaActionPerformed
        TelaAlterarSenha popup = new TelaAlterarSenha(this, usuarioLogado);
        popup.setVisible(true);
    }//GEN-LAST:event_btnEditarSenhaActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInfoActionPerformed

    private void btnMinimizarTelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarTelaMouseClicked
        setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarTelaMouseClicked

    private void btnMinimizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMinimizarTelaActionPerformed

    private void btnFecharTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharTelaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnFecharTelaActionPerformed

    private void btnMaximizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaximizarTelaActionPerformed

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
        new TelaMenu(usuarioLogado).setVisible(true);
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

    private JPanel criarCard(String iconePath, String titulo, String subtitulo) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(true);
        card.setBackground(new Color(255, 255, 255, 40)); // Transparente
        card.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel icon = new JLabel(new ImageIcon(getClass().getResource(iconePath)));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel lblSub = new JLabel(subtitulo);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSub.setForeground(new Color(220, 220, 220));
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        card.add(icon);
        card.add(Box.createVerticalStrut(15));
        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(5));
        card.add(lblSub);

        return card;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnAdministracao;
    private javax.swing.JButton btnAtualizarEmail;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracoes;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnEditarNome;
    private javax.swing.JButton btnEditarSenha;
    private javax.swing.JButton btnFecharTela;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnMaximizarTela;
    private javax.swing.JButton btnMinimizarTela;
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnTarefas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblBarraLateral;
    private javax.swing.JLabel lblBarraSuperior;
    private javax.swing.JLabel lblDivisorTela;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoTexto;
    private javax.swing.JLabel lblTituloPagina;
    private javax.swing.JLabel lblUserIcon;
    // End of variables declaration//GEN-END:variables
}
