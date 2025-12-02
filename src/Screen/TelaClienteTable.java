package screen;

import Data.Cliente;
import Data.CTCONTAB;
import Data.IconUtil;
import Data.PermissaoUtil;
import Data.Usuario;
import Screen.FonteUtils;
import Screen.MensagemUtil;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.SwingUtilities;
import Screen.TelaVisualizarCliente; // Exemplo caso o pacote varie
import Screen.TelaEditarCliente;
import Screen.PopupExclusao;

public class TelaClienteTable extends javax.swing.JFrame {

    private static final Logger logger = LoggerFactory.getLogger(TelaClienteTable.class);

    private Usuario usuarioLogado;
    private List<Cliente> listaClientes;
    private Cliente cliente;
    private int mouseX, mouseY;
    private int hoveredRow = -1;
    private int hoveredColumn = -1;

    public TelaClienteTable(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        adicionarListenerDeBusca();
        exibirMensagemCarregando();
        carregarClientesAssincrono();
        aplicarPaddingNasColunas();
        setUndecorated(true);
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
        setIcon();
        setResizable(false);
        addHoverLabel(btnDashboard, "Dashboard");
        addHoverLabel(btnCalendario, "Calendário");
        addHoverLabel(btnClientes, "Clientes");
        addHoverLabel(btnRelatorios, "Relatórios");
        addHoverLabel(btnTarefas, "Tarefas");
        addHoverLabel(btnConfiguracoes, "Configuração");
        addHoverLabel(btnAdministracao, "Administração");
        addHoverLabel(btnNotificacoes, "Notificações");
        addHoverLabel(btnCadastrar, "Novo Cliente");
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
            btnFecharTela.setText("X");
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
            btnMaximizarTela.setText("[]");
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
            btnMinimizarTela.setText("-");
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
            lblDivisorTela.setText("|");
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
            btnInfo.setText("?");
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
            java.net.URL url = getClass().getResource("/images/Client Icon Active.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Client Icon Active.png ou src/images/Client Icon Active.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnClientes.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnClientes, "/images/Client Icon Active.png", "/images/Client Icon Hover.png", 22,
                        22);
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
            java.net.URL url = getClass().getResource("/images/Administrative Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Administrative Icon.png ou src/images/Administrative Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                btnAdministracao.setIcon(new javax.swing.ImageIcon(img));
                aplicarHoverIcon(btnAdministracao, "/images/Administrative Icon.png",
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

        // Icones dos Quadrados
        try {
            java.net.URL url = getClass().getResource("/images/New Report Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/New Report Icon.png ou src/images/New Report Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                lblIconeRelatorioNovo.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Report Pendent Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Report Pendent Icon.png ou src/images/Report Pendent Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                lblIconeRelatorioPendente.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Report Sucess Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Report Sucess Icon.png ou src/images/Report Sucess Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                lblIconeRelatorioConcluido.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Registered Employee of the Month Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Registered Employee of the Month Icon.png ou src/images/Registered Employee of the Month Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
                lblIconeFuncionarioDoMes.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/procurar.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/procurar.png ou src/images/procurar.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(12, 12, java.awt.Image.SCALE_SMOOTH);
                lblPesquisarIcone.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            java.net.URL url = getClass().getResource("/images/Plus Icon.png");
            if (url == null) {
                System.err.println(
                        "Imagem não encontrada. Verifique: /images/Plus Icon.png ou src/images/Plus Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(12, 12, java.awt.Image.SCALE_SMOOTH);
                btnCadastrar.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        jTable1.addMouseMotionListener(
                new java.awt.event.MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(java.awt.event.MouseEvent e) {
                        int row = jTable1.rowAtPoint(e.getPoint());
                        int col = jTable1.columnAtPoint(e.getPoint());

                        if (row != hoveredRow || col != hoveredColumn) {
                            hoveredRow = row;
                            hoveredColumn = col;
                            jTable1.repaint();
                        }
                    }
                });

        jTable1.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        hoveredRow = -1;
                        hoveredColumn = -1;
                        jTable1.repaint();
                    }
                });
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
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
                } else if (botao == btnCadastrar) {
                    label.setLocation(
                            botao.getX() + botao.getWidth() + 8,
                            botao.getY() + (botao.getHeight() - label.getHeight()) / 2);
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

    private void adicionarListenerDeBusca() {
        txtLogin.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarClientes();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarClientes();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarClientes();
            }
        });
    }

    private void exibirMensagemCarregando() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.addRow(new Object[] { "Carregando...", "", "", "", "", "" });
    }

    private void carregarClientesAssincrono() {
        new SwingWorker<List<Cliente>, Void>() {
            @Override
            protected List<Cliente> doInBackground() throws Exception {
                return CTCONTAB.listarClientes();
            }

            @Override
            protected void done() {
                try {
                    listaClientes = get();
                    atualizarTabela(listaClientes);
                } catch (Exception e) {
                    e.printStackTrace();
                    exibirMensagemErro();
                }
            }
        }.execute();
    }

    private void exibirMensagemErro() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.addRow(new Object[] { "Erro ao carregar dados.", "", "", "", "", "" });
    }

    public class TableRender extends DefaultTableCellRenderer {

        private int padding;
        private int alignment;

        // Variáveis de estado para o desenho
        private int currentRow;
        private boolean isFirstColumn;
        private boolean isLastColumn;

        public TableRender(int padding, int alignment) {
            this.padding = padding;
            this.alignment = alignment;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            // 1. Configuração básica do texto
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // 2. CAPTURA O ESTADO DA CÉLULA
            this.currentRow = row;

            // Verifica se é a primeira coluna visível
            this.isFirstColumn = (column == 0);

            // Verifica se é a última coluna visível (IMPORTANTE: A coluna de ações deve ser
            // a última)
            this.isLastColumn = (column == table.getColumnCount() - 1);

            // 3. Configurações visuais
            setBorder(BorderFactory.createEmptyBorder(0, padding, 0, 0));
            setHorizontalAlignment(alignment);
            setOpaque(false); // Transparente para desenharmos manualmente

            // 4. Cores do Texto (Sua lógica original)
            if (!isSelected) {
                int modelColumn = table.convertColumnIndexToModel(column);
                String texto = (value != null) ? value.toString() : "";

                if (modelColumn == 0 || modelColumn == 4) {
                    setForeground(Color.decode("#A8B2C3"));
                } else if (modelColumn == 2) {
                    switch (texto) {
                        case "Pendente":
                            setForeground(Color.decode("#EB5757"));
                            break;
                        case "Concluido":
                        case "Concluído":
                            setForeground(Color.decode("#27AE60"));
                            break;
                        case "Em andamento":
                            setForeground(Color.decode("#F2C94C"));
                            break;
                        default:
                            setForeground(Color.WHITE);
                    }
                } else {
                    setForeground(Color.WHITE);
                }
            } else {
                setForeground(Color.WHITE);
            }

            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // --- DEFINIÇÃO DAS CORES ---
            Color corFundo = (currentRow % 2 == 0) ? Color.decode("#162842") : Color.decode("#1C2E4A");
            Color corBorda = Color.decode("#2D9CDB");

            // --- GEOMETRIA DO DESENHO (O TRUQUE DA CONTINUIDADE) ---
            int w = getWidth();
            int h = getHeight();
            int arc = 25; // Raio do arredondamento (Aumentei para ficar mais visível)
            int gapY = 6; // Espaço vertical entre as linhas (o "respiro")

            // A altura do desenho é a altura da célula menos o espaço
            int drawH = h - gapY;
            int drawY = gapY / 2; // Centraliza verticalmente

            // Lógica de "Extensão":
            // Esticamos o retângulo para fora da célula nas direções que não queremos borda
            // arredondada.
            int xDraw = 0;
            int wDraw = w;

            if (isFirstColumn && isLastColumn) {
                // Se só tiver 1 coluna, desenha o cartão inteiro
                xDraw = 1;
                wDraw = w - 2;
            } else if (isFirstColumn) {
                // Primeira coluna: Começa normal (1), estica muito para a direita (w + arc)
                xDraw = 1;
                wDraw = w + arc;
            } else if (isLastColumn) {
                // Última coluna: Começa muito antes da esquerda (-arc), termina normal (w - 1)
                xDraw = -arc;
                wDraw = w + arc;
            } else {
                // Colunas do meio: Começa antes (-arc) e termina depois (w + arc)
                // Isso esconde as bordas laterais
                xDraw = -arc;
                wDraw = w + (arc * 2);
            }

            // 1. Pinta o Fundo
            g2.setColor(corFundo);
            g2.fillRoundRect(xDraw, drawY, wDraw, drawH, arc, arc);

            // 2. Pinta a Borda
            g2.setColor(corBorda);
            g2.setStroke(new BasicStroke(1f)); // Borda fina
            g2.drawRoundRect(xDraw, drawY, wDraw, drawH, arc, arc);

            g2.dispose();

            // 3. Desenha o texto por cima de tudo
            super.paintComponent(g);
        }
    }

    private void aplicarPaddingNasColunas() {
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        int xDoTituloID = 20; // Exemplo: Onde começa o label "ID"
        int xDoTituloNome = 100; // Exemplo: Onde começa o label "NOME"
        int xDoTituloStatus = 330; // Exemplo: Onde começa o label "STATUS"
        int xDoTituloTipo = 480; // Exemplo: Onde começa o label "TIPO DE PESSOA"
        int xDoTituloData = 680; // Exemplo: Onde começa o label "DATA DE CADASTRO"
        int xDoTituloAcoes = 1030;

        int larguraId = xDoTituloNome - xDoTituloID;
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(larguraId);

        int larguraNome = xDoTituloStatus - xDoTituloNome;
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(larguraNome);

        int larguraStatus = xDoTituloTipo - xDoTituloStatus;
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(larguraStatus);

        int larguraTipo = xDoTituloData - xDoTituloTipo;
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(larguraTipo);

        int larguraData = xDoTituloAcoes - xDoTituloData;
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(larguraData);

        if (jTable1.getColumnCount() > 5) {
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        int paddingPadrao = 10;

        setPadding(0, paddingPadrao); // ID
        setPadding(1, paddingPadrao); // NOME
        setPadding(2, paddingPadrao); // STATUS
        setPadding(3, paddingPadrao); // TIPO
        setPadding(4, paddingPadrao); // DATA

    }

    private void setPadding(int colIndex, int padding) {
        TableRender renderer = new TableRender(padding, SwingConstants.LEFT);
        jTable1.getColumnModel().getColumn(colIndex).setCellRenderer(renderer);
    }

    private void atualizarTabela(List<Cliente> clientes) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        aplicarPaddingNasColunas();
        model.setRowCount(0);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy 'às' HH:mm");

        for (Cliente cliente : clientes) {
            String dataFormatada = "Data Inválida";

            try {
                Date data = inputFormat.parse(cliente.getDataCadastro().toString());
                dataFormatada = outputFormat.format(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            model.addRow(new Object[] {
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getSituacaoServico(),
                    cliente.getTipoPessoa(),
                    dataFormatada,
                    "Visualizar",
                    "Editar",
                    "Excluir"
            });
        }
    }

    private void filtrarClientes() {
        String filtro = txtLogin.getText().toLowerCase();
        List<Cliente> clientesFiltrados = new ArrayList<>();

        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().toLowerCase().contains(filtro)) {
                clientesFiltrados.add(cliente);
            }
        }

        atualizarTabela(clientesFiltrados);
    }

    public class CircleButton extends JButton {

        private Color colorNormal;
        private Color colorHover;
        private Color currentColor;
        private Image image; // Recebe a imagem já carregada

        // Construtor agora recebe a IMAGE, não o caminho String
        public CircleButton(Image img, Color normal, Color hover) {
            this.image = img;
            this.colorNormal = normal;
            this.colorHover = hover;
            this.currentColor = normal;

            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);

            setPreferredSize(new Dimension(32, 32));
        }

        public void setHover(boolean isHover) {
            this.currentColor = isHover ? colorHover : colorNormal;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int d = Math.min(w, h); // Diâmetro
            int x = (w - d) / 2;
            int y = (h - d) / 2;

            // 1. Desenha o fundo circular
            g2.setColor(currentColor);
            g2.fillOval(x, y, d, d);

            // 2. Desenha a imagem centralizada
            if (image != null) {
                // Centraliza a imagem 16x16 no centro do botão
                int imgX = x + (d - 16) / 2;
                int imgY = y + (d - 16) / 2;
                g2.drawImage(image, imgX, imgY, 16, 16, this);
            }

            g2.dispose();
        }
    }

    class ButtonRenderer extends javax.swing.JPanel implements TableCellRenderer {

        // Cache das imagens para não carregar toda hora
        private Image imgVisualizar;
        private Image imgEditar;
        private Image imgExcluir;

        private CircleButton button;
        private int currentRow;
        private boolean isLastColumn;

        public ButtonRenderer() {
            setLayout(new GridBagLayout());
            setOpaque(false);

            // --- CARREGAMENTO ÚNICO DAS IMAGENS ---
            imgVisualizar = carregarImagem("/images/olho.png");
            imgEditar = carregarImagem("/images/lapis.png");
            imgExcluir = carregarImagem("/images/lixo.png");
        }

        // Método auxiliar seguro para carregar imagem
        private Image carregarImagem(String path) {
            try {
                // Tenta carregar com o caminho exato
                java.net.URL url = getClass().getResource(path);
                if (url == null && path.startsWith("/")) {
                    // Tenta sem a barra inicial se falhar
                    url = getClass().getResource(path.substring(1));
                }
                if (url != null) {
                    return new ImageIcon(url).getImage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null; // Retorna null se não achar (o botão ficará vazio mas sem erro)
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            removeAll();
            this.currentRow = row;
            // Verifica se é a última coluna visível
            this.isLastColumn = (column == table.getColumnCount() - 1);

            Color bgNormal, bgHover;
            Image iconeAtual;

            // Lógica de seleção do botão baseada na coluna
            if (column == 5) { // Visualizar
                bgNormal = Color.decode("#0E2A3A");
                bgHover = Color.decode("#123446");
                iconeAtual = imgVisualizar;
            } else if (column == 6) { // Editar
                bgNormal = Color.decode("#3A300E");
                bgHover = Color.decode("#4A3B11");
                iconeAtual = imgEditar;
            } else { // Excluir (Assume que é o resto)
                bgNormal = Color.decode("#3C1414");
                bgHover = Color.decode("#4A1A1A");
                iconeAtual = imgExcluir;
            }

            // Passa a IMAGEM JÁ CARREGADA para o botão
            button = new CircleButton(iconeAtual, bgNormal, bgHover);

            // Verifica hover (se as variáveis globais existirem na sua classe principal)
            // button.setHover(row == hoveredRow && column == hoveredColumn);
            // Força tamanho quadrado
            button.setPreferredSize(new Dimension(32, 32));

            add(button);
            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Cores
            Color corFundo = (currentRow % 2 == 0) ? Color.decode("#162842") : Color.decode("#1C2E4A");
            Color corBorda = Color.decode("#2D9CDB");

            int w = getWidth(); // Largura total disponível na célula
            int h = getHeight();
            int arc = 20;
            int gapY = 4;
            int drawH = h - gapY;
            int drawY = gapY / 2;

            int xDraw = -arc; // Começa escondido na esquerda
            int wDraw;

            if (isLastColumn) {
                // --- CORREÇÃO DO PREENCHIMENTO ---
                // Se for a última coluna, estica até a largura total (w) + a sobra da esquerda
                // (arc)
                // O -1 é para garantir que a borda fique dentro do pixel de pintura
                wDraw = w + arc - 1;
            } else {
                // Colunas do meio esticam para os dois lados
                wDraw = w + (arc * 2);
            }

            // 1. Fundo
            g2.setColor(corFundo);
            g2.fillRoundRect(xDraw, drawY, wDraw, drawH, arc, arc);

            // 2. Borda
            g2.setColor(corBorda);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(xDraw, drawY, wDraw, drawH, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    private void ajustarLarguraColunas() {
        jTable1.getColumnModel().getColumn(5).setMinWidth(62);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(62);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(62);
        jTable1.getColumnModel().getColumn(6).setMinWidth(62);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(62);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(62);
        jTable1.getColumnModel().getColumn(7).setMinWidth(62);
        jTable1.getColumnModel().getColumn(7).setMaxWidth(62);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(62);
    }

    public class ButtonEditor extends DefaultCellEditor {

        private String tipo; // "Visualizar", "Editar" ou "Excluir"
        private CircleButton button;
        private javax.swing.JPanel panel;

        // Variáveis de estado para o desenho do fundo
        private int currentRow;
        private boolean isLastColumn;

        // Cache de imagens
        private Image imgIcone;

        public ButtonEditor(JCheckBox checkBox, String tipo) {
            super(checkBox);
            this.tipo = tipo;

            String iconPath = "";
            if (tipo.equalsIgnoreCase("Visualizar")) {
                iconPath = "/images/olho.png";
            } else if (tipo.equalsIgnoreCase("Editar")) {
                iconPath = "/images/lapis.png";
            } else { // Excluir
                iconPath = "/images/lixo.png";
            }
            this.imgIcone = carregarImagem(iconPath);

            panel = new javax.swing.JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    Color corFundo = (currentRow % 2 == 0) ? Color.decode("#162842") : Color.decode("#1C2E4A");
                    Color corBorda = Color.decode("#2D9CDB");

                    int w = getWidth();
                    int h = getHeight();
                    int arc = 20;
                    int gapY = 4;
                    int drawH = h - gapY;
                    int drawY = gapY / 2;

                    int xDraw = -arc;
                    int wDraw = isLastColumn ? (w + arc - 1) : (w + (arc * 2));

                    g2.setColor(corFundo);
                    g2.fillRoundRect(xDraw, drawY, wDraw, drawH, arc, arc);
                    g2.setColor(corBorda);
                    g2.setStroke(new BasicStroke(1f));
                    g2.drawRoundRect(xDraw, drawY, wDraw, drawH, arc, arc);
                    g2.dispose();
                    super.paintComponent(g);
                }
            };
            panel.setLayout(new GridBagLayout());
            panel.setOpaque(false);

            button = new CircleButton(imgIcone, getCorNormal(tipo), getCorHover(tipo));
            button.setPreferredSize(new Dimension(32, 32));
            
            // --- AQUI ESTÁ A LÓGICA DE CLIQUE ATUALIZADA ---
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Para a edição para liberar a tabela
                    fireEditingStopped(); 
                    
                    // Recupera o ID da linha selecionada (Coluna 0 é o ID)
                    // Atenção: currentRow é atualizado no getTableCellEditorComponent
                    Object idObj = jTable1.getValueAt(currentRow, 0); 
                    int idCliente = Integer.parseInt(idObj.toString());

                    // Encontra o objeto Cliente na lista baseado no ID
                    Cliente clienteSelecionado = null;
                    if (listaClientes != null) {
                        for (Cliente c : listaClientes) {
                            if (c.getId() == idCliente) {
                                clienteSelecionado = c;
                                break;
                            }
                        }
                    }

                    if (clienteSelecionado == null) return;

                    // Redireciona para a ação correta
                    if (tipo.equalsIgnoreCase("Visualizar")) {
                        new TelaVisualizarCliente(TelaClienteTable.this, usuarioLogado, clienteSelecionado).setVisible(true);
                        
                    } else if (tipo.equalsIgnoreCase("Editar")) {
                        TelaEditarCliente telaEdit = new TelaEditarCliente(TelaClienteTable.this, usuarioLogado, clienteSelecionado);
                        telaEdit.setVisible(true);
                        // Se salvou, recarrega a tabela
                        if (telaEdit.isSalvou()) {
                            carregarClientesAssincrono();
                        }
                        
                    } else if (tipo.equalsIgnoreCase("Excluir")) {
                        PopupExclusao popup = new PopupExclusao(TelaClienteTable.this, usuarioLogado, clienteSelecionado);
                        popup.setVisible(true);
                        // Se excluiu, recarrega a tabela
                        if (popup.isExcluiu()) {
                            carregarClientesAssincrono();
                        }
                    }
                }
            });

            panel.add(button);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.currentRow = row;
            this.isLastColumn = (column == table.getColumnCount() - 1);
            return panel;
        }

        private Color getCorNormal(String tipo) {
            if (tipo.equalsIgnoreCase("Visualizar")) return Color.decode("#0E2A3A");
            if (tipo.equalsIgnoreCase("Editar")) return Color.decode("#3A300E");
            return Color.decode("#3C1414");
        }

        private Color getCorHover(String tipo) {
            if (tipo.equalsIgnoreCase("Visualizar")) return Color.decode("#123446");
            if (tipo.equalsIgnoreCase("Editar")) return Color.decode("#4A3B11");
            return Color.decode("#4A1A1A");
        }

        private Image carregarImagem(String path) {
            try {
                URL url = getClass().getResource(path);
                if (url == null && path.startsWith("/")) url = getClass().getResource(path.substring(1));
                if (url != null) return new ImageIcon(url).getImage();
            } catch (Exception e) {}
            return null;
        }
    
        // ... Mantenha seus métodos abrirTelaCliente e excluirCliente aqui dentro ...
        private void abrirTelaCliente(int row) {
            try {
                if (row >= 0 && row < listaClientes.size()) {
                    Cliente clienteSelecionado = listaClientes.get(row);
                    TelaCliente telaCliente = new TelaCliente(usuarioLogado, clienteSelecionado);
                    telaCliente.setVisible(true);
                    TelaClienteTable.this.dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void excluirCliente(int row) {
            if (row < 0 || row >= listaClientes.size()) {
                return;
            }
            MDC.put("usuario", usuarioLogado.getUsuario());
            try {
                Cliente cliente = listaClientes.get(row);
                CTCONTAB.excluirRegistro("cliente", "ID", cliente.getId());
                listaClientes.remove(row);
                atualizarTabela(listaClientes);
                MensagemUtil.exibirSucesso("Cliente excluído com sucesso!");
                logger.info("excluiu o cliente [{}]", cliente.getNome());
            } catch (Exception e) {
                MensagemUtil.exibirErro("Erro ao excluir cliente!");
            }
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

    private class PanelArredondadoComBorda extends javax.swing.JPanel {

        private int cornerRadius;
        private java.awt.Color backgroundColor;
        private java.awt.Color borderColor;
        private int borderThickness;

        public PanelArredondadoComBorda(int radius, java.awt.Color bgColor, java.awt.Color bdColor, int bdThickness) {
            super();
            this.cornerRadius = radius;
            this.backgroundColor = bgColor;
            this.borderColor = bdColor;
            this.borderThickness = bdThickness;
            setOpaque(false); // Permite ver a forma arredondada
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int thickness = borderThickness;
            int radius = cornerRadius;

            // 1. Preenche o fundo (interno)
            g2.setColor(backgroundColor);
            g2.fillRoundRect(thickness, thickness, width - (thickness * 2), height - (thickness * 2), radius, radius);

            // 2. Desenha a borda (externa)
            g2.setStroke(new java.awt.BasicStroke(thickness));
            g2.setColor(borderColor);
            // Ajusta a área de desenho da borda para que o traço fique centrado na linha
            g2.drawRoundRect(thickness / 2, thickness / 2, width - thickness, height - thickness, radius, radius);

            g2.dispose();

            // Desenha os componentes filhos por cima
            super.paintComponent(g);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFuncionarioDoMesNome = new javax.swing.JLabel();
        lblRelatorioConcluidoDescricao = new javax.swing.JLabel();
        lblRelatorioConcluidoNumero = new javax.swing.JLabel();
        lblRelatorioPendenteNumero = new javax.swing.JLabel();
        lblRelatorioPendenteDescricao = new javax.swing.JLabel();
        lblRelatorioNovoDescricao = new javax.swing.JLabel();
        lblRelatorioNovoNumero = new javax.swing.JLabel();
        btnCadastrar = new Screen.RoundButton(
                new javax.swing.ImageIcon(getClass().getResource("/images/Plus Icon.png")));
        lblRelatorioNovoTitulo1 = new javax.swing.JLabel();
        lblIconeFuncionarioDoMes = new javax.swing.JLabel();
        lblIconeRelatorioConcluido = new javax.swing.JLabel();
        lblIconeRelatorioPendente = new javax.swing.JLabel();
        lblIconeRelatorioNovo = new javax.swing.JLabel();
        lblFuncionarioDoMesTitulo = new javax.swing.JLabel();
        lblRelatorioConcluidoTitulo = new javax.swing.JLabel();
        lblRelatorioPendenteTitulo = new javax.swing.JLabel();
        lblRelatorioNovoTitulo = new javax.swing.JLabel();
        jPanelFuncionarioDoMesIconeQuadrado = new javax.swing.JPanel();
        jPanelRelatorioConcluidoIconeQuadrado = new javax.swing.JPanel();
        jPanelRelatorioPendenteIconeQuadrado = new javax.swing.JPanel();
        jPanelRelatorioNovoIconeQuadrado = new javax.swing.JPanel();
        JPanelFuncionarioDoMes = new javax.swing.JPanel();
        JPanelRelatorioConcluido = new javax.swing.JPanel();
        JPanelRelatorioPendente = new javax.swing.JPanel();
        JPanelRelatorioNovo = new javax.swing.JPanel();
        btnInfo = new javax.swing.JButton();
        btnNotificacoes = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnMinimizarTela = new javax.swing.JButton();
        btnMaximizarTela = new javax.swing.JButton();
        btnUserIcon = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnAdministracao = new javax.swing.JButton();
        btnFecharTela = new javax.swing.JButton();
        btnDashboard = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        lblTituloPagina = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        lblDivisorTela = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblLogoTexto = new javax.swing.JLabel();
        lblBarraSuperior = new javax.swing.JLabel();
        lblBarraLateral = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblAcao = new javax.swing.JLabel();
        lblDataDeCadastro = new javax.swing.JLabel();
        lblTipoDePessoa = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        java.awt.Color COR_FUNDO = java.awt.Color.decode("#162842"); // Fundo: #162842
        java.awt.Color COR_BORDA = java.awt.Color.decode("#2A3E61"); // Borda: #2A3E61
        int RAIO_BORDA = 30; // Raio dos cantos (ajuste se precisar)
        int ESPESSURA_BORDA = 3; // Espessura da borda em pixels

        JPanelBackground = new PanelArredondadoComBorda(RAIO_BORDA, COR_FUNDO, COR_BORDA, ESPESSURA_BORDA);
        lblPesquisarIcone = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CT Contab Manager");
        getContentPane().setLayout(null);

        lblFuncionarioDoMesNome.setFont(FonteUtils.carregarLatoBlack(16f));
        lblFuncionarioDoMesNome.setForeground(new java.awt.Color(255, 255, 255));
        lblFuncionarioDoMesNome.setText("UsuarioName");
        getContentPane().add(lblFuncionarioDoMesNome);
        lblFuncionarioDoMesNome.setBounds(1200, 165, 210, 25);

        lblRelatorioConcluidoDescricao.setFont(FonteUtils.carregarLato(11f));
        lblRelatorioConcluidoDescricao.setForeground(new java.awt.Color(168, 178, 195));
        lblRelatorioConcluidoDescricao.setText("Nesse mês");
        getContentPane().add(lblRelatorioConcluidoDescricao);
        lblRelatorioConcluidoDescricao.setBounds(900, 170, 160, 20);

        lblRelatorioConcluidoNumero.setFont(FonteUtils.carregarLatoBlack(18f));
        lblRelatorioConcluidoNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblRelatorioConcluidoNumero.setText("0,000");
        getContentPane().add(lblRelatorioConcluidoNumero);
        lblRelatorioConcluidoNumero.setBounds(850, 165, 210, 25);

        lblRelatorioPendenteNumero.setFont(FonteUtils.carregarLatoBlack(18f));
        lblRelatorioPendenteNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblRelatorioPendenteNumero.setText("0,000");
        getContentPane().add(lblRelatorioPendenteNumero);
        lblRelatorioPendenteNumero.setBounds(510, 165, 210, 25);

        lblRelatorioPendenteDescricao.setFont(FonteUtils.carregarLato(11f));
        lblRelatorioPendenteDescricao.setForeground(new java.awt.Color(168, 178, 195));
        lblRelatorioPendenteDescricao.setText("Nesse mês");
        getContentPane().add(lblRelatorioPendenteDescricao);
        lblRelatorioPendenteDescricao.setBounds(560, 170, 160, 20);

        lblRelatorioNovoDescricao.setFont(FonteUtils.carregarLato(11f));
        lblRelatorioNovoDescricao.setForeground(new java.awt.Color(168, 178, 195));
        lblRelatorioNovoDescricao.setText("Nesse mês");
        getContentPane().add(lblRelatorioNovoDescricao);
        lblRelatorioNovoDescricao.setBounds(220, 170, 160, 20);

        lblRelatorioNovoNumero.setFont(FonteUtils.carregarLatoBlack(18f));
        lblRelatorioNovoNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblRelatorioNovoNumero.setText("0,000");
        getContentPane().add(lblRelatorioNovoNumero);
        lblRelatorioNovoNumero.setBounds(170, 165, 210, 25);

        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrar);
        btnCadastrar.setBounds(570, 213, 35, 35);

        lblRelatorioNovoTitulo1.setFont(FonteUtils.carregarRalewayMedium(20f));
        lblRelatorioNovoTitulo1.setForeground(new java.awt.Color(255, 255, 255));
        lblRelatorioNovoTitulo1.setText("Performance");
        getContentPane().add(lblRelatorioNovoTitulo1);
        lblRelatorioNovoTitulo1.setBounds(100, 100, 210, 20);

        lblIconeFuncionarioDoMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconeFuncionarioDoMes.setIcon(
                new javax.swing.ImageIcon(getClass().getResource("/images/Registered Employee of the Month Icon.png"))); // NOI18N
        getContentPane().add(lblIconeFuncionarioDoMes);
        lblIconeFuncionarioDoMes.setBounds(1155, 155, 20, 20);

        lblIconeRelatorioConcluido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconeRelatorioConcluido
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Report Sucess Icon.png"))); // NOI18N
        getContentPane().add(lblIconeRelatorioConcluido);
        lblIconeRelatorioConcluido.setBounds(805, 155, 20, 20);

        lblIconeRelatorioPendente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconeRelatorioPendente
                .setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Report Pendent Icon.png"))); // NOI18N
        getContentPane().add(lblIconeRelatorioPendente);
        lblIconeRelatorioPendente.setBounds(466, 155, 20, 20);

        lblIconeRelatorioNovo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconeRelatorioNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/New Report Icon.png"))); // NOI18N
        getContentPane().add(lblIconeRelatorioNovo);
        lblIconeRelatorioNovo.setBounds(125, 155, 20, 20);

        lblFuncionarioDoMesTitulo.setFont(FonteUtils.carregarRalewayMedium(12f));
        lblFuncionarioDoMesTitulo.setForeground(new java.awt.Color(168, 178, 195));
        lblFuncionarioDoMesTitulo.setText("Funcionário Relator do Mês");
        getContentPane().add(lblFuncionarioDoMesTitulo);
        lblFuncionarioDoMesTitulo.setBounds(1200, 140, 210, 20);

        lblRelatorioConcluidoTitulo.setFont(FonteUtils.carregarRalewayMedium(12f));
        lblRelatorioConcluidoTitulo.setForeground(new java.awt.Color(168, 178, 195));
        lblRelatorioConcluidoTitulo.setText("Total de Relatórios Concluidos");
        getContentPane().add(lblRelatorioConcluidoTitulo);
        lblRelatorioConcluidoTitulo.setBounds(850, 140, 210, 20);

        lblRelatorioPendenteTitulo.setFont(FonteUtils.carregarRalewayMedium(12f));
        lblRelatorioPendenteTitulo.setForeground(new java.awt.Color(168, 178, 195));
        lblRelatorioPendenteTitulo.setText("Total de Relatórios Pendentes");
        getContentPane().add(lblRelatorioPendenteTitulo);
        lblRelatorioPendenteTitulo.setBounds(510, 140, 210, 20);

        lblRelatorioNovoTitulo.setFont(FonteUtils.carregarRalewayMedium(12f));
        lblRelatorioNovoTitulo.setForeground(new java.awt.Color(168, 178, 195));
        lblRelatorioNovoTitulo.setText("Total de Relatórios Novos");
        getContentPane().add(lblRelatorioNovoTitulo);
        lblRelatorioNovoTitulo.setBounds(170, 140, 210, 20);

        jPanelFuncionarioDoMesIconeQuadrado.setBackground(new java.awt.Color(155, 81, 224, 41));
        getContentPane().add(jPanelFuncionarioDoMesIconeQuadrado);
        jPanelFuncionarioDoMesIconeQuadrado.setBounds(1143, 143, 45, 45);

        jPanelRelatorioConcluidoIconeQuadrado.setBackground(new java.awt.Color(39, 174, 96, 41));
        getContentPane().add(jPanelRelatorioConcluidoIconeQuadrado);
        jPanelRelatorioConcluidoIconeQuadrado.setBounds(793, 143, 45, 45);

        jPanelRelatorioPendenteIconeQuadrado.setBackground(new java.awt.Color(242, 201, 76, 41));
        getContentPane().add(jPanelRelatorioPendenteIconeQuadrado);
        jPanelRelatorioPendenteIconeQuadrado.setBounds(453, 143, 45, 45);

        jPanelRelatorioNovoIconeQuadrado.setBackground(new java.awt.Color(45, 156, 219, 41));
        getContentPane().add(jPanelRelatorioNovoIconeQuadrado);
        jPanelRelatorioNovoIconeQuadrado.setBounds(113, 143, 45, 45);

        JPanelFuncionarioDoMes.setBackground(new java.awt.Color(28, 46, 74));
        JPanelFuncionarioDoMes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 62, 97), 3, true));
        getContentPane().add(JPanelFuncionarioDoMes);
        JPanelFuncionarioDoMes.setBounds(1130, 130, 280, 70);

        JPanelRelatorioConcluido.setBackground(new java.awt.Color(28, 46, 74));
        JPanelRelatorioConcluido.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 62, 97), 3, true));
        getContentPane().add(JPanelRelatorioConcluido);
        JPanelRelatorioConcluido.setBounds(780, 130, 280, 70);

        JPanelRelatorioPendente.setBackground(new java.awt.Color(28, 46, 74));
        JPanelRelatorioPendente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 62, 97), 3, true));
        getContentPane().add(JPanelRelatorioPendente);
        JPanelRelatorioPendente.setBounds(440, 130, 280, 70);

        JPanelRelatorioNovo.setBackground(new java.awt.Color(28, 46, 74));
        JPanelRelatorioNovo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 62, 97), 3, true));
        getContentPane().add(JPanelRelatorioNovo);
        JPanelRelatorioNovo.setBounds(100, 130, 280, 70);

        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Information Icon.png"))); // NOI18N
        btnInfo.setContentAreaFilled(false);
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });
        getContentPane().add(btnInfo);
        btnInfo.setBounds(1305, 0, 15, 25);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Notification Bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1340, 35, 40, 40);

        btnCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Calendar Icon.png"))); // NOI18N
        btnCalendario.setContentAreaFilled(false);
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalendario);
        btnCalendario.setBounds(19, 280, 30, 30);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Settings Icon.png"))); // NOI18N
        btnConfiguracoes.setContentAreaFilled(false);
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfiguracoes);
        btnConfiguracoes.setBounds(19, 440, 30, 30);

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

        btnUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Administrative Icon.png"))); // NOI18N
        btnUserIcon.setContentAreaFilled(false);
        btnUserIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserIconActionPerformed(evt);
            }
        });
        getContentPane().add(btnUserIcon);
        btnUserIcon.setBounds(1390, 30, 50, 50);

        btnTarefas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Tasks Icon.png"))); // NOI18N
        btnTarefas.setContentAreaFilled(false);
        btnTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarefasActionPerformed(evt);
            }
        });
        getContentPane().add(btnTarefas);
        btnTarefas.setBounds(19, 400, 30, 30);

        btnAdministracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Administrative Icon.png"))); // NOI18N
        btnAdministracao.setContentAreaFilled(false);
        btnAdministracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdministracao);
        btnAdministracao.setBounds(19, 480, 30, 30);

        btnFecharTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        btnFecharTela.setContentAreaFilled(false);
        btnFecharTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnFecharTela);
        btnFecharTela.setBounds(1425, 0, 15, 25);

        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Icon.png"))); // NOI18N
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        getContentPane().add(btnDashboard);
        btnDashboard.setBounds(19, 240, 30, 30);

        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Report Icon.png"))); // NOI18N
        btnRelatorios.setContentAreaFilled(false);
        btnRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosActionPerformed(evt);
            }
        });
        getContentPane().add(btnRelatorios);
        btnRelatorios.setBounds(19, 360, 30, 30);

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Client Icon.png"))); // NOI18N
        btnClientes.setContentAreaFilled(false);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnClientes);
        btnClientes.setBounds(19, 320, 30, 30);

        lblTituloPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPagina.setText("CLIENTES");
        lblTituloPagina.setFont(FonteUtils.carregarRoboto(13f));
        getContentPane().add(lblTituloPagina);
        lblTituloPagina.setBounds(0, 3, 1440, 20);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jonh Doe Icon.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1390, 30, 512, 50);

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

        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(FonteUtils.carregarLatoBold(12f)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null }
                },
                new String[] {
                        "ID", "NOME", "STATUS", "TIPO DE PESSOA", "DATA DE CADASTRO", "AÇÃO 1", "AÇÃO 2", "AÇÃO 3"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                int row = jTable1.rowAtPoint(evt.getPoint());
                int col = jTable1.columnAtPoint(evt.getPoint());
                if (row != hoveredRow || col != hoveredColumn) {

                    hoveredRow = row;

                    hoveredColumn = col;

                    jTable1.repaint();

                }
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hoveredRow = -1;
                hoveredColumn = -1;
                jTable1.repaint();
            }
        });
        jTable1.setRowHeight(50);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setFocusable(false);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        jTable1.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        jTable1.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("AÇÃO 1").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("AÇÃO 1").setCellEditor(new ButtonEditor(new JCheckBox(), "Visualizar"));

        jTable1.getColumn("AÇÃO 2").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("AÇÃO 2").setCellEditor(new ButtonEditor(new JCheckBox(), "Editar"));

        jTable1.getColumn("AÇÃO 3").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("AÇÃO 3").setCellEditor(new ButtonEditor(new JCheckBox(), "Excluir"));

        add(new JScrollPane(jTable1), BorderLayout.CENTER);

        ajustarLarguraColunas();
        jTable1.setRowSelectionAllowed(false);
        jTable1.setTableHeader(null);
        jTable1.setBorder(null);
        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportBorder(null);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(130, 300, 1250, 380);
        jTable1.setOpaque(false);
        ((DefaultTableCellRenderer) jTable1.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        lblAcao.setFont(FonteUtils.carregarLato(12f));
        lblAcao.setForeground(new java.awt.Color(168, 178, 195));
        lblAcao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAcao.setText("AÇÕES");
        lblAcao.setToolTipText("");
        getContentPane().add(lblAcao);
        lblAcao.setBounds(1150, 280, 110, 16);

        lblDataDeCadastro.setFont(FonteUtils.carregarLato(12f));
        lblDataDeCadastro.setForeground(new java.awt.Color(168, 178, 195));
        lblDataDeCadastro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDataDeCadastro.setText("DATA DE CADASTRO");
        lblDataDeCadastro.setToolTipText("");
        getContentPane().add(lblDataDeCadastro);
        lblDataDeCadastro.setBounds(800, 280, 170, 16);

        lblTipoDePessoa.setFont(FonteUtils.carregarLato(12f));
        lblTipoDePessoa.setForeground(new java.awt.Color(168, 178, 195));
        lblTipoDePessoa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTipoDePessoa.setText("TIPO DE PESSOA");
        lblTipoDePessoa.setToolTipText("");
        getContentPane().add(lblTipoDePessoa);
        lblTipoDePessoa.setBounds(600, 280, 110, 16);

        lblStatus.setFont(FonteUtils.carregarLato(12f));
        lblStatus.setForeground(new java.awt.Color(168, 178, 195));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblStatus.setText("STATUS");
        lblStatus.setToolTipText("");
        getContentPane().add(lblStatus);
        lblStatus.setBounds(450, 280, 110, 16);

        lblNome.setFont(FonteUtils.carregarLato(12f));
        lblNome.setForeground(new java.awt.Color(168, 178, 195));
        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNome.setText("NOME");
        lblNome.setToolTipText("");
        getContentPane().add(lblNome);
        lblNome.setBounds(220, 280, 110, 16);

        lblID.setFont(FonteUtils.carregarLato(12f));
        lblID.setForeground(new java.awt.Color(168, 178, 195));
        lblID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblID.setText("ID");
        lblID.setToolTipText("");
        getContentPane().add(lblID);
        lblID.setBounds(140, 280, 110, 16);

        getContentPane().add(JPanelBackground);
        JPanelBackground.setBounds(100, 260, 1310, 450);

        lblPesquisarIcone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPesquisarIcone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/procurar.png"))); // NOI18N
        getContentPane().add(lblPesquisarIcone);
        lblPesquisarIcone.setBounds(113, 224, 12, 12);

        txtLogin.setBackground(new java.awt.Color(19, 35, 59));
        txtLogin.setFont(FonteUtils.carregarLato(14f));
        txtLogin.setForeground(new java.awt.Color(142, 162, 189));
        txtLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 62, 97), 3),
                javax.swing.BorderFactory.createEmptyBorder(0, 30, 0, 0)));
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        getContentPane().add(txtLogin);
        txtLogin.setBounds(100, 210, 460, 40);
        addPlaceholder(txtLogin, "Buscar");

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Background.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1470, 750);

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCadastrarActionPerformed
        TelaAdicionarCliente popup = new TelaAdicionarCliente(this, usuarioLogado);
        popup.setVisible(true);
    }// GEN-LAST:event_btnCadastrarActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLoginActionPerformed
        new TelaCliente(usuarioLogado, cliente).setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnLoginActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtLoginActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnInfoActionPerformed

    private void btnMaximizarTelaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMaximizarTelaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnMaximizarTelaActionPerformed

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

    private void btnMinimizarTelaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMinimizarTelaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnMinimizarTelaActionPerformed

    private void btnFecharTelaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFecharTelaActionPerformed
        System.exit(0);
    }// GEN-LAST:event_btnFecharTelaActionPerformed

    private void btnUserIconActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnUserIconActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnUserIconActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel JPanelBackground;
    private javax.swing.JPanel JPanelFuncionarioDoMes;
    private javax.swing.JPanel JPanelRelatorioConcluido;
    private javax.swing.JPanel JPanelRelatorioNovo;
    private javax.swing.JPanel JPanelRelatorioPendente;
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
    private javax.swing.JPanel jPanelFuncionarioDoMesIconeQuadrado;
    private javax.swing.JPanel jPanelRelatorioConcluidoIconeQuadrado;
    private javax.swing.JPanel jPanelRelatorioNovoIconeQuadrado;
    private javax.swing.JPanel jPanelRelatorioPendenteIconeQuadrado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAcao;
    private javax.swing.JLabel lblBarraLateral;
    private javax.swing.JLabel lblBarraSuperior;
    private javax.swing.JLabel lblDataDeCadastro;
    private javax.swing.JLabel lblDivisorTela;
    private javax.swing.JLabel lblFuncionarioDoMesNome;
    private javax.swing.JLabel lblFuncionarioDoMesTitulo;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblIconeFuncionarioDoMes;
    private javax.swing.JLabel lblIconeRelatorioConcluido;
    private javax.swing.JLabel lblIconeRelatorioNovo;
    private javax.swing.JLabel lblIconeRelatorioPendente;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoTexto;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPesquisarIcone;
    private javax.swing.JLabel lblRelatorioConcluidoDescricao;
    private javax.swing.JLabel lblRelatorioConcluidoNumero;
    private javax.swing.JLabel lblRelatorioConcluidoTitulo;
    private javax.swing.JLabel lblRelatorioNovoDescricao;
    private javax.swing.JLabel lblRelatorioNovoNumero;
    private javax.swing.JLabel lblRelatorioNovoTitulo;
    private javax.swing.JLabel lblRelatorioNovoTitulo1;
    private javax.swing.JLabel lblRelatorioPendenteDescricao;
    private javax.swing.JLabel lblRelatorioPendenteNumero;
    private javax.swing.JLabel lblRelatorioPendenteTitulo;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTipoDePessoa;
    private javax.swing.JLabel lblTituloPagina;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JTextField txtLogin;
    // End of variables declaration//GEN-END:variables
}
