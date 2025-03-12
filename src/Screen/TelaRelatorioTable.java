package screen;

import Data.CTCONTAB;
import Data.IconUtil;
import Data.PermissaoUtil;
import Data.Relatorio;
import Data.Usuario;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TelaRelatorioTable extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private List<Relatorio> listarRelatorios;
    private List<Relatorio> relatoriosFiltrados;
    private Relatorio relatorio;

    public TelaRelatorioTable(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        exibirMensagemCarregando();
        carregarRelatoriosAssincrono();
        configurarBusca();
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
        setIcon();
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
    }

    private void exibirMensagemCarregando() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{"Carregando...", "", "", ""});
    }

    private void carregarRelatoriosAssincrono() {
        new SwingWorker<List<Relatorio>, Void>() {
            @Override
            protected List<Relatorio> doInBackground() throws Exception {
                return CTCONTAB.listarRelatorios();
            }

            @Override
            protected void done() {
                try {
                    listarRelatorios = get();
                    relatoriosFiltrados = listarRelatorios;
                    atualizarTabela(relatoriosFiltrados);
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
        model.addRow(new Object[]{"Erro ao carregar dados.", "", "", ""});
    }

    private void atualizarTabela(List<Relatorio> relatorios) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Relatorio relatorio : relatorios) {
            String dataFormatada = "";
            try {
                Date data = inputFormat.parse(relatorio.getDataCadastro());
                dataFormatada = outputFormat.format(data);
            } catch (Exception e) {
                dataFormatada = "Data inválida";
            }

            model.addRow(new Object[]{
                relatorio.getNomeRelatorio(),
                relatorio.getStatusRelatorio(),
                dataFormatada,
                "Editar",
                "Excluir"
            });
        }
    }

    private void configurarBusca() {
        txtLogin.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarRelatorios();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarRelatorios();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarRelatorios();
            }
        });
    }

    private void filtrarRelatorios() {
        String busca = txtLogin.getText().toLowerCase();

        relatoriosFiltrados = listarRelatorios.stream()
                .filter(relatorio -> relatorio.getNomeRelatorio().toLowerCase().contains(busca))
                .collect(Collectors.toList());

        atualizarTabela(relatoriosFiltrados);
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("");
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/lupa-branca.png"));
            ImageIcon icon3 = new ImageIcon(getClass().getResource("/images/fechar.png"));

            if (column == 3) {
                setIcon(icon);
                setBackground(new java.awt.Color(82, 113, 255));
            } else if (column == 4) {
                setIcon(icon3);
                setBackground(new java.awt.Color(239, 65, 54));
            }

            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        private JButton button;
        private String label;
        private String actionType;
        private int selectedRow;

        public ButtonEditor(JCheckBox checkBox, String actionType) {
            super(checkBox);
            this.actionType = actionType;

            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(e -> {
                if ("Excluir".equals(actionType)) {
                    excluirRelatorio(selectedRow);
                } else if ("Editar".equals(actionType)) {
                    abrirTelaRelatorio(selectedRow);
                }
                fireEditingStopped();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = value == null ? "" : value.toString();
            selectedRow = row;

            button.setText("");
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/lupa-branca.png"));
            ImageIcon icon3 = new ImageIcon(getClass().getResource("/images/fechar.png"));
            button.setIcon(icon);
            button.setIcon(icon3);

            if (column == 3) {
                button.setBackground(new java.awt.Color(82, 113, 255));
            } else if (column == 4) {
                button.setBackground(new java.awt.Color(239, 65, 54));
            }

            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }

        private void abrirTelaRelatorio(int row) {
            Relatorio relatorioSelecionado = listarRelatorios.get(row);
            TelaRelatorio telaRelatorio = new TelaRelatorio(usuarioLogado, relatorioSelecionado);
            telaRelatorio.setVisible(true);
            TelaRelatorioTable.this.dispose();
        }
    }

    private void excluirRelatorio(int row) {
        try {
            Relatorio relatorio = listarRelatorios.get(row);

            CTCONTAB.excluirRegistro("relatorio", "ID", relatorio.getId());

            listarRelatorios.remove(row);
            atualizarTabela(listarRelatorios);

            JOptionPane.showMessageDialog(null, "Tarefa excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir tarefa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ajustarLarguraColunas() {
        jTable1.getColumnModel().getColumn(3).setMinWidth(62);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(62);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(62);
        jTable1.getColumnModel().getColumn(4).setMinWidth(62);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(62);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(62);
        jTable1.getColumnModel().getColumn(5).setMinWidth(70);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(70);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(70);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblContabilidade = new javax.swing.JLabel();
        lblCTCONTAB = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNotificacoes = new javax.swing.JButton();
        lblNome1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        lblStatus = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        lblTipoDePessoa4 = new javax.swing.JLabel();
        lblDataDeCadastro = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnAdministracao = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        JPanelTelaAcesso = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jlibLogo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Relatórios - CT CONTAB");
        getContentPane().setLayout(null);

        lblContabilidade.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        lblContabilidade.setForeground(new java.awt.Color(153, 153, 0));
        lblContabilidade.setText("Contabilidade & Consultoria");
        getContentPane().add(lblContabilidade);
        lblContabilidade.setBounds(90, 7, 205, 80);

        lblCTCONTAB.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCTCONTAB.setForeground(new java.awt.Color(204, 204, 204));
        lblCTCONTAB.setText("CT CONTAB");
        getContentPane().add(lblCTCONTAB);
        lblCTCONTAB.setBounds(90, 7, 190, 40);

        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NOME DO RELATÓRIO", "STATUS", "DATA DE CADASTRO", "AÇÃO 1", "AÇÃO 3", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(115, 115, 115));
        jTable1.setRowHeight(50);
        jTable1.setShowHorizontalLines(true);
        jTable1.getColumn("AÇÃO 1").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("AÇÃO 1").setCellEditor(new ButtonEditor(new JCheckBox(), "Editar"));

        jTable1.getColumn("AÇÃO 3").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("AÇÃO 3").setCellEditor(new ButtonEditor(new JCheckBox(), "Excluir"));

        // Adiciona a tabela no painel principal
        add(new JScrollPane(jTable1), BorderLayout.CENTER);
        ajustarLarguraColunas();
        jTable1.setTableHeader(null);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(110, 220, 1130, 420);
        jTable1.setOpaque(false);
        ((DefaultTableCellRenderer) jTable1.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alert-bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1160, 10, 60, 60);

        lblNome1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNome1.setForeground(new java.awt.Color(186, 186, 186));
        lblNome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNome1.setText("NOME DO RELATÓRIO");
        lblNome1.setToolTipText("");
        getContentPane().add(lblNome1);
        lblNome1.setBounds(110, 190, 310, 30);

        jSeparator8.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator8.setForeground(new java.awt.Color(115, 115, 115));
        getContentPane().add(jSeparator8);
        jSeparator8.setBounds(110, 220, 1130, 80);

        jSeparator10.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator10.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator10);
        jSeparator10.setBounds(422, 190, 80, 450);

        jSeparator9.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator9.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator9);
        jSeparator9.setBounds(955, 190, 120, 450);

        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(186, 186, 186));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("STATUS");
        lblStatus.setToolTipText("");
        getContentPane().add(lblStatus);
        lblStatus.setBounds(430, 190, 300, 30);

        jSeparator5.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator5.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(733, 190, 80, 450);

        lblTipoDePessoa4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTipoDePessoa4.setForeground(new java.awt.Color(186, 186, 186));
        lblTipoDePessoa4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoDePessoa4.setText("AÇÕES");
        lblTipoDePessoa4.setToolTipText("");
        getContentPane().add(lblTipoDePessoa4);
        lblTipoDePessoa4.setBounds(950, 190, 290, 30);

        lblDataDeCadastro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro.setText("DATA DE CADASTRO");
        lblDataDeCadastro.setToolTipText("");
        getContentPane().add(lblDataDeCadastro);
        lblDataDeCadastro.setBounds(740, 190, 210, 30);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(110, 190, 1130, 450);

        btnAdministracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin-icon.png"))); // NOI18N
        btnAdministracao.setContentAreaFilled(false);
        btnAdministracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdministracao);
        btnAdministracao.setBounds(0, 570, 80, 60);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/config-icon.png"))); // NOI18N
        btnConfiguracoes.setContentAreaFilled(false);
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfiguracoes);
        btnConfiguracoes.setBounds(0, 500, 80, 50);

        btnTarefas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/task-icon.png"))); // NOI18N
        btnTarefas.setContentAreaFilled(false);
        btnTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarefasActionPerformed(evt);
            }
        });
        getContentPane().add(btnTarefas);
        btnTarefas.setBounds(0, 420, 80, 50);

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/client-icon.png"))); // NOI18N
        btnClientes.setContentAreaFilled(false);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnClientes);
        btnClientes.setBounds(5, 260, 70, 50);

        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/relatory-icon.png"))); // NOI18N
        btnRelatorios.setContentAreaFilled(false);
        btnRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosActionPerformed(evt);
            }
        });
        getContentPane().add(btnRelatorios);
        btnRelatorios.setBounds(5, 340, 70, 50);

        btnCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/event-icon.png"))); // NOI18N
        btnCalendario.setContentAreaFilled(false);
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalendario);
        btnCalendario.setBounds(5, 184, 70, 50);

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home-icon.png"))); // NOI18N
        btnHome.setContentAreaFilled(false);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnHome);
        btnHome.setBounds(0, 100, 80, 50);

        JPanelTelaAcesso.setBackground(new java.awt.Color(194, 166, 40));
        getContentPane().add(JPanelTelaAcesso);
        JPanelTelaAcesso.setBounds(0, 330, 80, 70);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(120, 100, 20, 80);

        txtLogin.setBackground(new java.awt.Color(4, 21, 57));
        txtLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(115, 115, 115));
        txtLogin.setText("Escreva o nome do relatório que deseja buscar.");
        txtLogin.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3), 
            javax.swing.BorderFactory.createEmptyBorder(0, 30, 0, 0)
        ));
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        getContentPane().add(txtLogin);
        txtLogin.setBounds(110, 120, 950, 40);

        btnLogin.setBackground(new java.awt.Color(194, 166, 40));
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Cadastrar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(1070, 120, 170, 40);

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

        setSize(new java.awt.Dimension(1294, 708));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        new TelaRelatorio(usuarioLogado, relatorio).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

    private void btnAdministracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracaoActionPerformed
        new TelaAdminTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdministracaoActionPerformed

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        new TelaConfiguracao(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConfiguracoesActionPerformed

    private void btnTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarefasActionPerformed
        new TelaTarefaTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTarefasActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        new TelaClienteTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosActionPerformed
        new TelaRelatorioTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRelatoriosActionPerformed

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        new TelaEventoTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCalendarioActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        new TelaMenu(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel JPanelTelaAcesso;
    private javax.swing.JButton btnAdministracao;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracoes;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnTarefas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlibLogo2;
    private javax.swing.JLabel lblCTCONTAB;
    private javax.swing.JLabel lblContabilidade;
    private javax.swing.JLabel lblDataDeCadastro;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTipoDePessoa4;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JTextField txtLogin;
    // End of variables declaration//GEN-END:variables
}
