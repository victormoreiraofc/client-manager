package screen;

import Data.CTCONTAB;
import Data.Cliente;
import Data.I18nManager;
import Data.IconUtil;
import Data.Usuario;
import Data.PermissaoUtil;
import Screen.FonteUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class TelaMenu extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private int mouseX, mouseY;
    private Cliente cliente;
    private Font fonteOriginal;

    public TelaMenu(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        atualizarTextos();
        ChartPanel g1 = criarMiniGrafico(new Color(0, 200, 255), "Clientes Mensais");
        g1.setOpaque(false);
        jPanelBackground.add(g1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 40, 172, 86));

        ChartPanel g2 = criarMiniGrafico(new Color(0, 150, 255), "Total de Clientes");
        g2.setOpaque(false);
        jPanelBackground1.add(g2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 40, 172, 86));

        ChartPanel g3 = criarMiniGrafico(new Color(255, 180, 0), "Tarefas Pendentes");
        g3.setOpaque(false);
        jPanelBackground2.add(g3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 40, 172, 86));

        ChartPanel g4 = criarMiniGrafico(new Color(180, 100, 255), "Total de Relatórios");
        g4.setOpaque(false);
        jPanelBackground3.add(g4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 40, 172, 86));

        ChartPanel g5 = criarMiniGrafico(new Color(255, 80, 80), "Tarefas não Realizadas");
        g5.setOpaque(false);
        jPanelBackground4.add(g5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 40, 172, 86));

        ChartPanel g6 = criarMiniGrafico(new Color(80, 255, 160), "Tarefas Finalizadas");
        g6.setOpaque(false);
        jPanelBackground5.add(g6, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 40, 172, 86));

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
                System.err.println("Imagem não encontrada. Verifique: /images/Close Icon.png ou src/images/Close Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(11, 11, java.awt.Image.SCALE_SMOOTH);
                btnFecharTela.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            btnFecharTela.setText("X");
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
            btnMaximizarTela.setText("[]");
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
            btnMinimizarTela.setText("-");
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
            lblDivisorTela.setText("|");
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
            btnInfo.setText("?");
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

        try {
            java.net.URL url = getClass().getResource("/images/Monthly Clients Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Monthly Clients Icon.png ou src/images/Monthly Clients Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
                lblIconDashboard_1.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblIconDashboard_1.setText("LOGO");
        }

        try {
            java.net.URL url = getClass().getResource("/images/Total Clients Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Total Clients Icon.png ou src/images/Total Clients Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
                lblIconDashboard_2.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblIconDashboard_2.setText("LOGO");
        }

        try {
            java.net.URL url = getClass().getResource("/images/Pending Tasks Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Pending Tasks Icon.png ou src/images/Pending Tasks Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
                lblIconDashboard_3.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblIconDashboard_3.setText("LOGO");
        }

        try {
            java.net.URL url = getClass().getResource("/images/Total Reports Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Total Reports Icon.png ou src/images/Total Reports Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
                lblIconDashboard_4.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblIconDashboard_4.setText("LOGO");
        }

        try {
            java.net.URL url = getClass().getResource("/images/Unfulfilled Tasks Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Unfulfilled Tasks Icon.png ou src/images/Unfulfilled Tasks Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
                lblIconDashboard_5.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblIconDashboard_5.setText("LOGO");
        }

        try {
            java.net.URL url = getClass().getResource("/images/Report Completed Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Report Completed Icon.png ou src/images/Report Completed Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
                lblIconDashboard_6.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            lblIconDashboard_6.setText("LOGO");
        }

        salvarFonteOriginal();
        carregarDadosSimultaneamente();
        exibirMensagemCarregando();
        setUndecorated(true);
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
        setIcon();
        setResizable(false);
    }

    private ChartPanel criarMiniGrafico(Color cor, String titulo) {

        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ds.addValue(10, titulo, "Jan");
        ds.addValue(14, titulo, "Fev");
        ds.addValue(8, titulo, "Mar");
        ds.addValue(18, titulo, "Abr");
        ds.addValue(15, titulo, "Mai");

        JFreeChart chart = ChartFactory.createLineChart(
                null, null, null, ds,
                PlotOrientation.VERTICAL,
                false, false, false
        );

        chart.setBackgroundPaint(new Color(0, 0, 0, 0));

        CategoryPlot p = chart.getCategoryPlot();
        p.setBackgroundPaint(new Color(0, 0, 0, 0));
        p.setOutlineVisible(false);

        p.getRangeAxis().setVisible(false);
        p.getDomainAxis().setVisible(false);
        p.setRangeGridlinesVisible(false);
        p.setDomainGridlinesVisible(false);

        LineAndShapeRenderer r = (LineAndShapeRenderer) p.getRenderer();
        r.setSeriesPaint(0, cor);
        r.setSeriesStroke(0, new BasicStroke(2f));
        r.setDefaultShapesVisible(false);

        ChartPanel panel = new ChartPanel(chart);
        panel.setOpaque(false);
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setMouseWheelEnabled(false);
        panel.setPopupMenu(null);

        return panel;
    }

    private void addHoverLabel(JButton botao, String texto) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(7, 30, 70));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g2);
                g2.dispose();
            }
        };

        label.setOpaque(false);
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
            java.net.URL urlNormal = getClass().getResource(caminhoNormal);
            java.awt.Image imgNormal = javax.imageio.ImageIO.read(urlNormal)
                    .getScaledInstance(largura, altura, java.awt.Image.SCALE_SMOOTH);
            javax.swing.ImageIcon iconNormal = new javax.swing.ImageIcon(imgNormal);

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

    private String formatarValorDashboard(int valor) {
        if (valor > 99999) {
            valor = 99999;
        }

        return String.format("%03d", valor);
    }

    private void atualizarTotalClientes() {
        try {
            int total = CTCONTAB.clienteTotalRegis();
            jlibVariavel1.setText(formatarValorDashboard(total));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel1.setText("000");
        }
    }

    private void tarefaPendentes() {
        try {
            int pendentes = CTCONTAB.tarefaPendentes();
            jlibVariavel2.setText(formatarValorDashboard(pendentes));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel2.setText("000");
        }
    }

    private void tarefasNaoRealizadas() {
        try {
            int andamento = CTCONTAB.serviçosNaoRealizados();
            jlibVariavel4.setText(formatarValorDashboard(andamento));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel4.setText("000");
        }
    }

    private void tarefasRealizadas() {
        try {
            int concluido = CTCONTAB.serviçosRealizados();
            jlibVariavel5.setText(formatarValorDashboard(concluido));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel5.setText("000");
        }
    }

    private void totalRelatorios() {
        try {
            int total = CTCONTAB.totalRelatorios();
            jlibVariavel3.setText(formatarValorDashboard(total));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel3.setText("000");
        }
    }

    private void novosclientesdomes() {
        try {
            int total = CTCONTAB.novosclientesdomes();
            jlibVariavel.setText(formatarValorDashboard(total));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel.setText("000");
        }
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
        jlibCadastrarNovo.setText(I18nManager.getString("dashboard.banner.action.register"));
        lblNovosClientesMes.setText(I18nManager.getString("dashboard.card.new_clients.header"));
        jLabel2.setText(I18nManager.getString("dashboard.card.new_clients.title"));
        jLabel1.setText(I18nManager.getString("dashboard.card.new_clients.subtitle"));
        lbltextinho1.setText(I18nManager.getString("dashboard.card.new_clients.description"));
        lblTotalClientes.setText(I18nManager.getString("dashboard.card.total_clients.header"));
        jLabel4.setText(I18nManager.getString("dashboard.card.total_clients.title"));
        jLabel6.setText(I18nManager.getString("dashboard.card.total_clients.subtitle"));
        lbltextinho2.setText(I18nManager.getString("dashboard.card.total_clients.description"));
        lblTarefasPendentes.setText(I18nManager.getString("dashboard.card.pending_tasks.header"));
        jLabel5.setText(I18nManager.getString("dashboard.card.pending_tasks.title"));
        jLabel7.setText(I18nManager.getString("dashboard.card.pending_tasks.subtitle"));
        lbltextinho3.setText(I18nManager.getString("dashboard.card.pending_tasks.description"));
        lblTotalVendas.setText(I18nManager.getString("dashboard.card.total_reports.header"));
        jLabel8.setText(I18nManager.getString("dashboard.card.total_reports.title"));
        jLabel9.setText(I18nManager.getString("dashboard.card.total_reports.subtitle"));
        lbltextinho4.setText(I18nManager.getString("dashboard.card.total_reports.description"));
        lblServicosNaoRealizados.setText(I18nManager.getString("dashboard.card.unfinished_tasks.header"));
        jLabel10.setText(I18nManager.getString("dashboard.card.unfinished_tasks.title"));
        jLabel11.setText(I18nManager.getString("dashboard.card.unfinished_tasks.subtitle"));
        lbltextinho5.setText(I18nManager.getString("dashboard.card.unfinished_tasks.description"));
        lblServicosFinalizados.setText(I18nManager.getString("dashboard.card.completed_tasks.header"));
        jLabel12.setText(I18nManager.getString("dashboard.card.completed_tasks.title"));
        jLabel13.setText(I18nManager.getString("dashboard.card.completed_tasks.subtitle"));
        lbltextinho6.setText(I18nManager.getString("dashboard.card.completed_tasks.description"));

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlibCadastrarNovo = new javax.swing.JLabel();
        jlibSeta = new javax.swing.JLabel();
        btnCadastrar = new javax.swing.JButton();
        jPanelBackground = new javax.swing.JPanel();
        lblNovosClientesMes = new javax.swing.JLabel();
        lblIconPorcent1 = new javax.swing.JLabel();
        lblIconPorcent = new javax.swing.JLabel();
        jlibVariavelPorcent1 = new javax.swing.JLabel();
        jlibVariavelPorcent = new javax.swing.JLabel();
        lbltextinho1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblIconDashboard_1 = new javax.swing.JLabel();
        JPanelIconBackground_1 = new javax.swing.JPanel();
        jlibVariavel = new javax.swing.JLabel();
        jPanelBackground1 = new javax.swing.JPanel();
        jlibVariavel1 = new javax.swing.JLabel();
        lblTotalClientes = new javax.swing.JLabel();
        lbltextinho2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblIconDashboard_2 = new javax.swing.JLabel();
        JPanelIconBackground_2 = new javax.swing.JPanel();
        lblIconPorcent2 = new javax.swing.JLabel();
        lblIconPorcent3 = new javax.swing.JLabel();
        jlibVariavelPorcent2 = new javax.swing.JLabel();
        jlibVariavelPorcent3 = new javax.swing.JLabel();
        jPanelBackground2 = new javax.swing.JPanel();
        lblTarefasPendentes = new javax.swing.JLabel();
        jlibVariavel2 = new javax.swing.JLabel();
        lbltextinho3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblIconDashboard_3 = new javax.swing.JLabel();
        JPanelIconBackground_3 = new javax.swing.JPanel();
        lblIconPorcent4 = new javax.swing.JLabel();
        lblIconPorcent5 = new javax.swing.JLabel();
        jlibVariavelPorcent4 = new javax.swing.JLabel();
        jlibVariavelPorcent5 = new javax.swing.JLabel();
        jPanelBackground3 = new javax.swing.JPanel();
        lblTotalVendas = new javax.swing.JLabel();
        jlibVariavel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbltextinho4 = new javax.swing.JLabel();
        lblIconDashboard_4 = new javax.swing.JLabel();
        JPanelIconBackground_4 = new javax.swing.JPanel();
        lblIconPorcent6 = new javax.swing.JLabel();
        lblIconPorcent7 = new javax.swing.JLabel();
        jlibVariavelPorcent6 = new javax.swing.JLabel();
        jlibVariavelPorcent7 = new javax.swing.JLabel();
        jPanelBackground4 = new javax.swing.JPanel();
        lblServicosNaoRealizados = new javax.swing.JLabel();
        jlibVariavel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbltextinho5 = new javax.swing.JLabel();
        lblIconDashboard_5 = new javax.swing.JLabel();
        JPanelIconBackground_5 = new javax.swing.JPanel();
        lblIconPorcent8 = new javax.swing.JLabel();
        lblIconPorcent9 = new javax.swing.JLabel();
        jlibVariavelPorcent8 = new javax.swing.JLabel();
        jlibVariavelPorcent9 = new javax.swing.JLabel();
        jPanelBackground5 = new javax.swing.JPanel();
        jlibVariavel5 = new javax.swing.JLabel();
        lblServicosFinalizados = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbltextinho6 = new javax.swing.JLabel();
        lblIconDashboard_6 = new javax.swing.JLabel();
        JPanelIconBackground_6 = new javax.swing.JPanel();
        lblIconPorcent10 = new javax.swing.JLabel();
        lblIconPorcent11 = new javax.swing.JLabel();
        jlibVariavelPorcent10 = new javax.swing.JLabel();
        jlibVariavelPorcent11 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnFecharTela = new javax.swing.JButton();
        lblUserIcon = new javax.swing.JLabel();
        btnAdministracao = new javax.swing.JButton();
        lblDivisorTela = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblLogoTexto = new javax.swing.JLabel();
        lblTituloPagina = new javax.swing.JLabel();
        btnUserIcon = new javax.swing.JButton();
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

        jlibCadastrarNovo.setFont(FonteUtils.carregarRoboto(22f));
        jlibCadastrarNovo.setForeground(new java.awt.Color(255, 255, 255));
        jlibCadastrarNovo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlibCadastrarNovo.setText("CADASTRAR NOVOS CLIENTES");
        getContentPane().add(jlibCadastrarNovo);
        jlibCadastrarNovo.setBounds(790, 150, 610, 40);

        jlibSeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/seta-direita.png"))); // NOI18N
        getContentPane().add(jlibSeta);
        jlibSeta.setBounds(1370, 230, 50, 40);

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastrar-imagem.jpg"))); // NOI18N
        btnCadastrar.setContentAreaFilled(false);
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrar);
        btnCadastrar.setBounds(110, 148, 1303, 130);

        jPanelBackground.setBackground(new java.awt.Color(255, 255, 255, 15));
        jPanelBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNovosClientesMes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNovosClientesMes.setForeground(new java.awt.Color(255, 255, 255));
        lblNovosClientesMes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNovosClientesMes.setText("Clientes Mensais");
        lblNovosClientesMes.setToolTipText("");
        jPanelBackground.add(lblNovosClientesMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 200, -1));
        jPanelBackground.add(lblIconPorcent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 185, 20, 20));
        jPanelBackground.add(lblIconPorcent, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 65, 20, 20));

        jlibVariavelPorcent1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jlibVariavelPorcent1.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent1.setText("0.000 %");
        jlibVariavelPorcent1.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground.add(jlibVariavelPorcent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 190, 50, 10));

        jlibVariavelPorcent.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlibVariavelPorcent.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent.setText("00%");
        jlibVariavelPorcent.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground.add(jlibVariavelPorcent, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 50, 50));

        lbltextinho1.setFont(FonteUtils.carregarInterSemiBold(11f));
        lbltextinho1.setForeground(new java.awt.Color(156, 163, 175));
        lbltextinho1.setText("<html> Este painel mostra a quantidade total<br>  de novos clientes que adquirimos no<br> mês, permitindo acompanhar o crescimento do nosso público.  </html>");
        lbltextinho1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbltextinho1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanelBackground.add(lbltextinho1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 225, 195, 90));

        jLabel1.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel1.setForeground(new java.awt.Color(199, 199, 199));
        jLabel1.setText("Número bruto de novos clientes.");
        jPanelBackground.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 210, -1, -1));

        jLabel2.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel2.setForeground(new java.awt.Color(199, 199, 199));
        jLabel2.setText("Novos clientes nesse mês");
        jPanelBackground.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 126, 200, 20));
        jPanelBackground.add(lblIconDashboard_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 155, 40, 40));

        JPanelIconBackground_1.setBackground(new java.awt.Color(0, 217, 255));
        jPanelBackground.add(JPanelIconBackground_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 150, 50, 50));

        jlibVariavel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlibVariavel.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavel.setText("000");
        jlibVariavel.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground.add(jlibVariavel, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 140, 150, 50));

        getContentPane().add(jPanelBackground);
        jPanelBackground.setBounds(110, 320, 208, 303);

        jPanelBackground1.setBackground(new java.awt.Color(255, 255, 255, 15));
        jPanelBackground1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlibVariavel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlibVariavel1.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavel1.setText("000");
        jPanelBackground1.add(jlibVariavel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 140, 150, 50));

        lblTotalClientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalClientes.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotalClientes.setText("Total de Clientes");
        jPanelBackground1.add(lblTotalClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 5, 200, -1));

        lbltextinho2.setFont(FonteUtils.carregarInterSemiBold(11f));
        lbltextinho2.setForeground(new java.awt.Color(156, 163, 175));
        lbltextinho2.setText("<html>Este painel mostra o total acumulado<br> de clientes cadastrados, permitindo acompanhar o crescimento da<br>  base ao longo do tempo. </html>");
        lbltextinho2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbltextinho2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanelBackground1.add(lbltextinho2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 225, 195, 90));

        jLabel4.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel4.setForeground(new java.awt.Color(199, 199, 199));
        jLabel4.setText("Todos os clientes registrados");
        jPanelBackground1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 130, 200, -1));

        jLabel6.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel6.setForeground(new java.awt.Color(199, 199, 199));
        jLabel6.setText("Número bruto de clientes registrados");
        jPanelBackground1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 210, -1, -1));
        jPanelBackground1.add(lblIconDashboard_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 155, 40, 40));

        JPanelIconBackground_2.setBackground(new java.awt.Color(79, 172, 255));
        jPanelBackground1.add(JPanelIconBackground_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 150, 50, 50));
        jPanelBackground1.add(lblIconPorcent2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 185, 20, 20));
        jPanelBackground1.add(lblIconPorcent3, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 65, 20, 20));

        jlibVariavelPorcent2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlibVariavelPorcent2.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent2.setText("00%");
        jlibVariavelPorcent2.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground1.add(jlibVariavelPorcent2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 50, 50));

        jlibVariavelPorcent3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jlibVariavelPorcent3.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent3.setText("0.000 %");
        jlibVariavelPorcent3.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground1.add(jlibVariavelPorcent3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 190, 50, 10));

        getContentPane().add(jPanelBackground1);
        jPanelBackground1.setBounds(330, 320, 208, 303);

        jPanelBackground2.setBackground(new java.awt.Color(255, 255, 255, 15));
        jPanelBackground2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTarefasPendentes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarefasPendentes.setForeground(new java.awt.Color(255, 255, 255));
        lblTarefasPendentes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTarefasPendentes.setText("Tarefas Pendentes");
        jPanelBackground2.add(lblTarefasPendentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 200, -1));

        jlibVariavel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlibVariavel2.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavel2.setText("000");
        jPanelBackground2.add(jlibVariavel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 140, 150, 50));

        lbltextinho3.setFont(FonteUtils.carregarInterSemiBold(11f));
        lbltextinho3.setForeground(new java.awt.Color(156, 163, 175));
        lbltextinho3.setText("<html>Este painel indica todas as tarefas que<br>  ainda estão pendentes, facilitando<br> o controle das demandas que<br>  precisam ser concluídas. </html>");
        lbltextinho3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbltextinho3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanelBackground2.add(lbltextinho3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 225, 195, 90));

        jLabel5.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel5.setForeground(new java.awt.Color(199, 199, 199));
        jLabel5.setText("Todas as tarefas pendentes");
        jPanelBackground2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 130, 200, -1));

        jLabel7.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel7.setForeground(new java.awt.Color(199, 199, 199));
        jLabel7.setText("Todas as tarefas pendentes");
        jPanelBackground2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 210, -1, -1));
        jPanelBackground2.add(lblIconDashboard_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 155, 40, 40));

        JPanelIconBackground_3.setBackground(new java.awt.Color(255, 183, 77));
        jPanelBackground2.add(JPanelIconBackground_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 150, 50, 50));
        jPanelBackground2.add(lblIconPorcent4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 185, 20, 20));
        jPanelBackground2.add(lblIconPorcent5, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 65, 20, 20));

        jlibVariavelPorcent4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlibVariavelPorcent4.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent4.setText("00%");
        jlibVariavelPorcent4.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground2.add(jlibVariavelPorcent4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 50, 50));

        jlibVariavelPorcent5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jlibVariavelPorcent5.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent5.setText("0.000 %");
        jlibVariavelPorcent5.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground2.add(jlibVariavelPorcent5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 190, 50, 10));

        getContentPane().add(jPanelBackground2);
        jPanelBackground2.setBounds(550, 320, 208, 303);

        jPanelBackground3.setBackground(new java.awt.Color(255, 255, 255, 15));
        jPanelBackground3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalVendas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalVendas.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalVendas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotalVendas.setText("Total de Relatórios");
        jPanelBackground3.add(lblTotalVendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 200, -1));

        jlibVariavel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlibVariavel3.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavel3.setText("000");
        jPanelBackground3.add(jlibVariavel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 140, 150, 50));

        jLabel8.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel8.setForeground(new java.awt.Color(199, 199, 199));
        jLabel8.setText("Todas os relatórios feitos");
        jPanelBackground3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 130, 200, -1));

        jLabel9.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel9.setForeground(new java.awt.Color(199, 199, 199));
        jLabel9.setText("Número bruto de relatórios gerados");
        jPanelBackground3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 210, -1, -1));

        lbltextinho4.setFont(FonteUtils.carregarInterSemiBold(11f));
        lbltextinho4.setForeground(new java.awt.Color(156, 163, 175));
        lbltextinho4.setText("<html>Aqui você acompanha quantos <br> relatórios foram emitidos,<br>  permitindo analisar o volume de<br>  registros e atividades documentadas. </html>");
        lbltextinho4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbltextinho4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanelBackground3.add(lbltextinho4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 225, 195, 90));
        jPanelBackground3.add(lblIconDashboard_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 155, 40, 40));

        JPanelIconBackground_4.setBackground(new java.awt.Color(186, 104, 200));
        jPanelBackground3.add(JPanelIconBackground_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 150, 50, 50));
        jPanelBackground3.add(lblIconPorcent6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 185, 20, 20));
        jPanelBackground3.add(lblIconPorcent7, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 65, 20, 20));

        jlibVariavelPorcent6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlibVariavelPorcent6.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent6.setText("00%");
        jlibVariavelPorcent6.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground3.add(jlibVariavelPorcent6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 50, 50));

        jlibVariavelPorcent7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jlibVariavelPorcent7.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent7.setText("0.000 %");
        jlibVariavelPorcent7.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground3.add(jlibVariavelPorcent7, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 190, 50, 10));

        getContentPane().add(jPanelBackground3);
        jPanelBackground3.setBounds(770, 320, 208, 303);

        jPanelBackground4.setBackground(new java.awt.Color(255, 255, 255, 15));
        jPanelBackground4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblServicosNaoRealizados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblServicosNaoRealizados.setForeground(new java.awt.Color(255, 255, 255));
        lblServicosNaoRealizados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblServicosNaoRealizados.setText("Tarefas não Realizadas");
        jPanelBackground4.add(lblServicosNaoRealizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 200, -1));

        jlibVariavel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlibVariavel4.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavel4.setText("000");
        jPanelBackground4.add(jlibVariavel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 140, 150, 50));

        jLabel10.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel10.setForeground(new java.awt.Color(199, 199, 199));
        jLabel10.setText("Tarefas não realizadas");
        jPanelBackground4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 130, 200, -1));

        jLabel11.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel11.setForeground(new java.awt.Color(199, 199, 199));
        jLabel11.setText("Tarefas não realizadas");
        jPanelBackground4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 210, -1, -1));

        lbltextinho5.setFont(FonteUtils.carregarInterSemiBold(11f));
        lbltextinho5.setForeground(new java.awt.Color(156, 163, 175));
        lbltextinho5.setText("<html>Este painel mostra as tarefas<br>  que foram iniciadas mas não<br>  finalizadas, ajudando a identificar<br>  gargalos e priorizar ações. </html>");
        lbltextinho5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbltextinho5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanelBackground4.add(lbltextinho5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 225, 195, 90));
        jPanelBackground4.add(lblIconDashboard_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 155, 40, 40));

        JPanelIconBackground_5.setBackground(new java.awt.Color(255, 82, 82));
        jPanelBackground4.add(JPanelIconBackground_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 150, 50, 50));
        jPanelBackground4.add(lblIconPorcent8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 185, 20, 20));
        jPanelBackground4.add(lblIconPorcent9, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 65, 20, 20));

        jlibVariavelPorcent8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlibVariavelPorcent8.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent8.setText("00%");
        jlibVariavelPorcent8.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground4.add(jlibVariavelPorcent8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 50, 50));

        jlibVariavelPorcent9.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jlibVariavelPorcent9.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent9.setText("0.000 %");
        jlibVariavelPorcent9.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground4.add(jlibVariavelPorcent9, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 190, 50, 10));

        getContentPane().add(jPanelBackground4);
        jPanelBackground4.setBounds(990, 320, 208, 303);

        jPanelBackground5.setBackground(new java.awt.Color(255, 255, 255, 15));
        jPanelBackground5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlibVariavel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlibVariavel5.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavel5.setText("000");
        jPanelBackground5.add(jlibVariavel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 140, 150, 50));

        lblServicosFinalizados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblServicosFinalizados.setForeground(new java.awt.Color(255, 255, 255));
        lblServicosFinalizados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblServicosFinalizados.setText("Tarefas Finalizadas");
        jPanelBackground5.add(lblServicosFinalizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 200, -1));

        jLabel12.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel12.setForeground(new java.awt.Color(199, 199, 199));
        jLabel12.setText("Tarefas já realizadas");
        jPanelBackground5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 130, 200, -1));

        jLabel13.setFont(FonteUtils.carregarInterSemiBold(11f));
        jLabel13.setForeground(new java.awt.Color(199, 199, 199));
        jLabel13.setText("Numero bruto de tarefas concluídas");
        jPanelBackground5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 210, 200, -1));

        lbltextinho6.setFont(FonteUtils.carregarInterSemiBold(11f));
        lbltextinho6.setForeground(new java.awt.Color(156, 163, 175));
        lbltextinho6.setText("<html>Indica todas as tarefas já finalizadas,<br> servindo como um termômetro da <br> produtividade e do progresso das atividades. </html>");
        lbltextinho6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbltextinho6.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanelBackground5.add(lbltextinho6, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 225, 195, 90));
        jPanelBackground5.add(lblIconDashboard_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 155, 40, 40));

        JPanelIconBackground_6.setBackground(new java.awt.Color(58, 255, 92));
        jPanelBackground5.add(JPanelIconBackground_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 50, 50));
        jPanelBackground5.add(lblIconPorcent10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 185, 20, 20));
        jPanelBackground5.add(lblIconPorcent11, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 65, 20, 20));

        jlibVariavelPorcent10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlibVariavelPorcent10.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent10.setText("00%");
        jlibVariavelPorcent10.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground5.add(jlibVariavelPorcent10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 50, 50));

        jlibVariavelPorcent11.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jlibVariavelPorcent11.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavelPorcent11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlibVariavelPorcent11.setText("0.000 %");
        jlibVariavelPorcent11.setPreferredSize(new java.awt.Dimension(55, 20));
        jPanelBackground5.add(jlibVariavelPorcent11, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 190, 50, 10));

        getContentPane().add(jPanelBackground5);
        jPanelBackground5.setBounds(1210, 320, 203, 303);

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
        TelaAdicionarCliente popup = new TelaAdicionarCliente(this, usuarioLogado);
        popup.setVisible(true);
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

    private void btnUserIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserIconActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUserIconActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel JPanelIconBackground_1;
    private javax.swing.JPanel JPanelIconBackground_2;
    private javax.swing.JPanel JPanelIconBackground_3;
    private javax.swing.JPanel JPanelIconBackground_4;
    private javax.swing.JPanel JPanelIconBackground_5;
    private javax.swing.JPanel JPanelIconBackground_6;
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
    private javax.swing.JButton btnUserIcon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JLabel jlibVariavelPorcent;
    private javax.swing.JLabel jlibVariavelPorcent1;
    private javax.swing.JLabel jlibVariavelPorcent10;
    private javax.swing.JLabel jlibVariavelPorcent11;
    private javax.swing.JLabel jlibVariavelPorcent2;
    private javax.swing.JLabel jlibVariavelPorcent3;
    private javax.swing.JLabel jlibVariavelPorcent4;
    private javax.swing.JLabel jlibVariavelPorcent5;
    private javax.swing.JLabel jlibVariavelPorcent6;
    private javax.swing.JLabel jlibVariavelPorcent7;
    private javax.swing.JLabel jlibVariavelPorcent8;
    private javax.swing.JLabel jlibVariavelPorcent9;
    private javax.swing.JLabel lblBarraLateral;
    private javax.swing.JLabel lblBarraSuperior;
    private javax.swing.JLabel lblDivisorTela;
    private javax.swing.JLabel lblIconDashboard_1;
    private javax.swing.JLabel lblIconDashboard_2;
    private javax.swing.JLabel lblIconDashboard_3;
    private javax.swing.JLabel lblIconDashboard_4;
    private javax.swing.JLabel lblIconDashboard_5;
    private javax.swing.JLabel lblIconDashboard_6;
    private javax.swing.JLabel lblIconPorcent;
    private javax.swing.JLabel lblIconPorcent1;
    private javax.swing.JLabel lblIconPorcent10;
    private javax.swing.JLabel lblIconPorcent11;
    private javax.swing.JLabel lblIconPorcent2;
    private javax.swing.JLabel lblIconPorcent3;
    private javax.swing.JLabel lblIconPorcent4;
    private javax.swing.JLabel lblIconPorcent5;
    private javax.swing.JLabel lblIconPorcent6;
    private javax.swing.JLabel lblIconPorcent7;
    private javax.swing.JLabel lblIconPorcent8;
    private javax.swing.JLabel lblIconPorcent9;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoTexto;
    private javax.swing.JLabel lblNovosClientesMes;
    private javax.swing.JLabel lblServicosFinalizados;
    private javax.swing.JLabel lblServicosNaoRealizados;
    private javax.swing.JLabel lblTarefasPendentes;
    private javax.swing.JLabel lblTituloPagina;
    private javax.swing.JLabel lblTotalClientes;
    private javax.swing.JLabel lblTotalVendas;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JLabel lbltextinho1;
    private javax.swing.JLabel lbltextinho2;
    private javax.swing.JLabel lbltextinho3;
    private javax.swing.JLabel lbltextinho4;
    private javax.swing.JLabel lbltextinho5;
    private javax.swing.JLabel lbltextinho6;
    // End of variables declaration//GEN-END:variables
}
