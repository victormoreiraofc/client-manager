package screen;

import Data.CTCONTAB;
import Data.Cliente;
import Data.IconUtil;
import Data.Usuario;
import Data.PermissaoUtil;
import Screen.FonteUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

public class TelaMenu extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private int mouseX, mouseY;
    private Cliente cliente;
    private Font fonteOriginal;

    public TelaMenu(Usuario usuario) {
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
            java.net.URL url = getClass().getResource("/images/Dashboard Icon Active.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Dashboard Icon Active.png ou src/images/Dashboard Icon Active.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnDashboard.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnDashboard, "/images/Dashboard Icon Active.png", "/images/Dashboard Icon Hover.png", 22, 22);
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
            java.net.URL url = getClass().getResource("/images/Settings Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Settings Icon.png ou src/images/Settings Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnConfiguracoes.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnConfiguracoes, "/images/Settings Icon.png", "/images/Settings Icon Hover.png", 22, 22);
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
        salvarFonteOriginal();
        carregarDadosSimultaneamente();
        exibirMensagemCarregando();
        setUndecorated(true); // Remove a barra superior.
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
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

    private void salvarFonteOriginal() {
        fonteOriginal = jlibVariavel1.getFont();
    }

    private void alterarFonteTemporaria() {
        Font fonteCarregando = fonteOriginal.deriveFont(Font.BOLD, 18);
        jlibVariavel1.setFont(fonteCarregando);
        jlibVariavel2.setFont(fonteCarregando);
        jlibVariavel3.setFont(fonteCarregando);
        jlibVariavel4.setFont(fonteCarregando);
        jlibVariavel5.setFont(fonteCarregando);
        jlibVariavel.setFont(fonteCarregando);
    }

    private void restaurarFonteOriginal() {
        jlibVariavel1.setFont(fonteOriginal);
        jlibVariavel2.setFont(fonteOriginal);
        jlibVariavel3.setFont(fonteOriginal);
        jlibVariavel4.setFont(fonteOriginal);
        jlibVariavel5.setFont(fonteOriginal);
        jlibVariavel.setFont(fonteOriginal);
    }

    private void exibirMensagemCarregando() {
        alterarFonteTemporaria();
        jlibVariavel1.setText("Carregando...");
        jlibVariavel2.setText("Carregando...");
        jlibVariavel3.setText("Carregando...");
        jlibVariavel4.setText("Carregando...");
        jlibVariavel5.setText("Carregando...");
        jlibVariavel.setText("Carregando...");
    }

    private void carregarDadosSimultaneamente() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                atualizarTotalClientes();
                return null;
            }
        }.execute();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                tarefaPendentes();
                return null;
            }
        }.execute();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                tarefasNaoRealizadas();
                return null;
            }
        }.execute();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                tarefasRealizadas();
                return null;
            }
        }.execute();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                totalRelatorios();
                return null;
            }
        }.execute();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                novosclientesdomes();
                return null;
            }
        }.execute();
    }

    private void atualizarTotalClientes() {
        try {
            int total = CTCONTAB.clienteTotalRegis();
            jlibVariavel1.setText(String.valueOf(total));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel1.setText("Erro");
        }
    }

    private void tarefaPendentes() {
        try {
            int pendentes = CTCONTAB.tarefaPendentes();
            jlibVariavel2.setText(String.valueOf(pendentes));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel2.setText("Erro");
        }
    }

    private void tarefasNaoRealizadas() {
        try {
            int andamento = CTCONTAB.serviçosNaoRealizados();
            jlibVariavel4.setText(String.valueOf(andamento));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel4.setText("Erro");
        }
    }

    private void tarefasRealizadas() {
        try {
            int concluido = CTCONTAB.serviçosRealizados();
            jlibVariavel5.setText(String.valueOf(concluido));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel5.setText("Erro");
        }
    }

    private void totalRelatorios() {
        try {
            int total = CTCONTAB.totalRelatorios();
            jlibVariavel3.setText(String.valueOf(total));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel3.setText("Erro");
        }
    }

    private void novosclientesdomes() {
        try {
            int total = CTCONTAB.novosclientesdomes();
            jlibVariavel.setText(String.valueOf(total));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel.setText("Erro");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlibCadastrarNovo = new javax.swing.JLabel();
        jlibSeta = new javax.swing.JLabel();
        btnCadastrar = new javax.swing.JButton();
        jlibVariavel2 = new javax.swing.JLabel();
        jlibVariavel3 = new javax.swing.JLabel();
        jlibVariavel4 = new javax.swing.JLabel();
        jlibVariavel5 = new javax.swing.JLabel();
        jlibVariavel1 = new javax.swing.JLabel();
        jlibVariavel = new javax.swing.JLabel();
        lblNovosClientesMes = new javax.swing.JLabel();
        lblTotalClientesRegistrados = new javax.swing.JLabel();
        lblTarefasPendentes = new javax.swing.JLabel();
        lblTotalVendas = new javax.swing.JLabel();
        lblServicosNaoRealizados = new javax.swing.JLabel();
        lblServicosFinalizados = new javax.swing.JLabel();
        jPanelBackground = new javax.swing.JPanel();
        jPanelBackground1 = new javax.swing.JPanel();
        jPanelBackground2 = new javax.swing.JPanel();
        jPanelBackground3 = new javax.swing.JPanel();
        jPanelBackground4 = new javax.swing.JPanel();
        jPanelBackground5 = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JButton();
        btnFecharTela = new javax.swing.JButton();
        lblUserIcon = new javax.swing.JLabel();
        btnAdministracao = new javax.swing.JButton();
        lblDivisorTela = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblLogoTexto = new javax.swing.JLabel();
        lblTituloPagina = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnMinimizarTela = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnMaximizarTela = new javax.swing.JButton();
        lblBarraSuperior = new javax.swing.JLabel();
        lblBarraLateral = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CT Contab Manager");
        getContentPane().setLayout(null);

        jlibCadastrarNovo.setFont(new java.awt.Font("Segoe UI", 1, 23)); // NOI18N
        jlibCadastrarNovo.setForeground(new java.awt.Color(205, 168, 16));
        jlibCadastrarNovo.setText("CADASTRAR NOVOS CLIENTES");
        getContentPane().add(jlibCadastrarNovo);
        jlibCadastrarNovo.setBounds(890, 170, 340, 40);

        jlibSeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/seta-direita.png"))); // NOI18N
        getContentPane().add(jlibSeta);
        jlibSeta.setBounds(1190, 240, 50, 40);

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastrar-imagem.jpg"))); // NOI18N
        btnCadastrar.setContentAreaFilled(false);
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrar);
        btnCadastrar.setBounds(110, 170, 1130, 110);

        jlibVariavel2.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel2.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel2.setText("000");
        getContentPane().add(jlibVariavel2);
        jlibVariavel2.setBounds(680, 360, 280, 60);

        jlibVariavel3.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel3.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel3.setText("000");
        getContentPane().add(jlibVariavel3);
        jlibVariavel3.setBounds(970, 360, 270, 60);

        jlibVariavel4.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel4.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel4.setText("000");
        getContentPane().add(jlibVariavel4);
        jlibVariavel4.setBounds(390, 480, 280, 60);

        jlibVariavel5.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel5.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel5.setText("000");
        getContentPane().add(jlibVariavel5);
        jlibVariavel5.setBounds(680, 480, 280, 60);

        jlibVariavel1.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel1.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel1.setText("000");
        getContentPane().add(jlibVariavel1);
        jlibVariavel1.setBounds(390, 360, 280, 60);

        jlibVariavel.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel.setText("000");
        getContentPane().add(jlibVariavel);
        jlibVariavel.setBounds(110, 360, 270, 60);

        lblNovosClientesMes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNovosClientesMes.setForeground(new java.awt.Color(186, 186, 186));
        lblNovosClientesMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNovosClientesMes.setText("NOVOS CLIENTES NO MÊS");
        lblNovosClientesMes.setToolTipText("");
        getContentPane().add(lblNovosClientesMes);
        lblNovosClientesMes.setBounds(110, 330, 270, 20);

        lblTotalClientesRegistrados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalClientesRegistrados.setForeground(new java.awt.Color(186, 186, 186));
        lblTotalClientesRegistrados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalClientesRegistrados.setText("TOTAL DE CLIENTES REGISTRADOS");
        getContentPane().add(lblTotalClientesRegistrados);
        lblTotalClientesRegistrados.setBounds(390, 330, 280, 20);

        lblTarefasPendentes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarefasPendentes.setForeground(new java.awt.Color(186, 186, 186));
        lblTarefasPendentes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTarefasPendentes.setText("TAREFAS PENDENTES");
        getContentPane().add(lblTarefasPendentes);
        lblTarefasPendentes.setBounds(680, 330, 280, 20);

        lblTotalVendas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalVendas.setForeground(new java.awt.Color(186, 186, 186));
        lblTotalVendas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalVendas.setText("TOTAL DE RELATÓRIOS");
        getContentPane().add(lblTotalVendas);
        lblTotalVendas.setBounds(970, 330, 270, 20);

        lblServicosNaoRealizados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblServicosNaoRealizados.setForeground(new java.awt.Color(186, 186, 186));
        lblServicosNaoRealizados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServicosNaoRealizados.setText("TAREFAS NÃO REALIZADAS");
        getContentPane().add(lblServicosNaoRealizados);
        lblServicosNaoRealizados.setBounds(390, 450, 280, 20);

        lblServicosFinalizados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblServicosFinalizados.setForeground(new java.awt.Color(186, 186, 186));
        lblServicosFinalizados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServicosFinalizados.setText("TAREFAS FINALIZADAS");
        getContentPane().add(lblServicosFinalizados);
        lblServicosFinalizados.setBounds(680, 450, 280, 20);

        jPanelBackground.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground);
        jPanelBackground.setBounds(110, 320, 270, 110);

        jPanelBackground1.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground1);
        jPanelBackground1.setBounds(390, 320, 280, 110);

        jPanelBackground2.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground2);
        jPanelBackground2.setBounds(680, 320, 280, 110);

        jPanelBackground3.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground3);
        jPanelBackground3.setBounds(970, 320, 270, 110);

        jPanelBackground4.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground4);
        jPanelBackground4.setBounds(390, 440, 280, 110);

        jPanelBackground5.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground5);
        jPanelBackground5.setBounds(680, 440, 280, 110);

        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Icon.png"))); // NOI18N
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        getContentPane().add(btnDashboard);
        btnDashboard.setBounds(19, 240, 30, 30);

        btnFecharTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        btnFecharTela.setContentAreaFilled(false);
        btnFecharTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnFecharTela);
        btnFecharTela.setBounds(1425, 0, 15, 25);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1390, 30, 512, 50);

        btnAdministracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Administrative Icon.png"))); // NOI18N
        btnAdministracao.setContentAreaFilled(false);
        btnAdministracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdministracao);
        btnAdministracao.setBounds(19, 480, 30, 30);

        lblDivisorTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        lblDivisorTela.setPreferredSize(new java.awt.Dimension(13, 13));
        getContentPane().add(lblDivisorTela);
        lblDivisorTela.setBounds(1335, 0, 15, 25);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Icon.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(15, 35, 40, 40);

        lblLogoTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Text Icon.png"))); // NOI18N
        getContentPane().add(lblLogoTexto);
        lblLogoTexto.setBounds(80, 35, 176, 46);

        lblTituloPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPagina.setText("Dashboard");
        lblTituloPagina.setFont(FonteUtils.carregarRoboto(13f));
        getContentPane().add(lblTituloPagina);
        lblTituloPagina.setBounds(720, 3, 120, 20);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Notification Bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1340, 35, 40, 40);

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Client Icon.png"))); // NOI18N
        btnClientes.setContentAreaFilled(false);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnClientes);
        btnClientes.setBounds(19, 320, 30, 30);

        btnCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar Icon.png"))); // NOI18N
        btnCalendario.setContentAreaFilled(false);
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalendario);
        btnCalendario.setBounds(19, 280, 30, 30);

        btnTarefas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Tasks Icon.png"))); // NOI18N
        btnTarefas.setContentAreaFilled(false);
        btnTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarefasActionPerformed(evt);
            }
        });
        getContentPane().add(btnTarefas);
        btnTarefas.setBounds(19, 400, 30, 30);

        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Report Icon.png"))); // NOI18N
        btnRelatorios.setContentAreaFilled(false);
        btnRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosActionPerformed(evt);
            }
        });
        getContentPane().add(btnRelatorios);
        btnRelatorios.setBounds(19, 360, 30, 30);

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

        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Information Icon.png"))); // NOI18N
        btnInfo.setContentAreaFilled(false);
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });
        getContentPane().add(btnInfo);
        btnInfo.setBounds(1305, 0, 15, 25);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Settings Icon.png"))); // NOI18N
        btnConfiguracoes.setContentAreaFilled(false);
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfiguracoes);
        btnConfiguracoes.setBounds(19, 440, 30, 30);

        btnMaximizarTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Maximize Icon.png"))); // NOI18N
        btnMaximizarTela.setContentAreaFilled(false);
        btnMaximizarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizarTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnMaximizarTela);
        btnMaximizarTela.setBounds(1390, 0, 15, 25);

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

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        new TelaCliente(usuarioLogado, cliente).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        new TelaMenu(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnFecharTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharTelaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnFecharTelaActionPerformed

    private void btnAdministracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracaoActionPerformed
        new TelaAdminTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdministracaoActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        new TelaClienteTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        new TelaEventoTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCalendarioActionPerformed

    private void btnTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarefasActionPerformed
        new TelaTarefaTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTarefasActionPerformed

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosActionPerformed
        new TelaRelatorioTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRelatoriosActionPerformed

    private void lblBarraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBarraSuperiorMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - mouseX, y - mouseY);
    }//GEN-LAST:event_lblBarraSuperiorMouseDragged

    private void lblBarraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBarraSuperiorMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_lblBarraSuperiorMousePressed

    private void btnMinimizarTelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarTelaMouseClicked
        setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarTelaMouseClicked

    private void btnMinimizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMinimizarTelaActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInfoActionPerformed

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        new TelaConfiguracao(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConfiguracoesActionPerformed

    private void btnMaximizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaximizarTelaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnAdministracao;
    private javax.swing.JButton btnCadastrar;
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
    private javax.swing.JPanel jPanelBackground;
    private javax.swing.JPanel jPanelBackground1;
    private javax.swing.JPanel jPanelBackground2;
    private javax.swing.JPanel jPanelBackground3;
    private javax.swing.JPanel jPanelBackground4;
    private javax.swing.JPanel jPanelBackground5;
    private javax.swing.JLabel jlibCadastrarNovo;
    private javax.swing.JLabel jlibSeta;
    private javax.swing.JLabel jlibVariavel;
    private javax.swing.JLabel jlibVariavel1;
    private javax.swing.JLabel jlibVariavel2;
    private javax.swing.JLabel jlibVariavel3;
    private javax.swing.JLabel jlibVariavel4;
    private javax.swing.JLabel jlibVariavel5;
    private javax.swing.JLabel lblBarraLateral;
    private javax.swing.JLabel lblBarraSuperior;
    private javax.swing.JLabel lblDivisorTela;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoTexto;
    private javax.swing.JLabel lblNovosClientesMes;
    private javax.swing.JLabel lblServicosFinalizados;
    private javax.swing.JLabel lblServicosNaoRealizados;
    private javax.swing.JLabel lblTarefasPendentes;
    private javax.swing.JLabel lblTituloPagina;
    private javax.swing.JLabel lblTotalClientesRegistrados;
    private javax.swing.JLabel lblTotalVendas;
    private javax.swing.JLabel lblUserIcon;
    // End of variables declaration//GEN-END:variables
}
