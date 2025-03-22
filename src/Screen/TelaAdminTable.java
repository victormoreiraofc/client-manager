package screen;

import Data.CTCONTAB;
import Data.Funcionario;
import Data.IconUtil;
import Data.Usuario;
import Screen.MensagemUtil;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

public class TelaAdminTable extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private List<Funcionario> listaFuncionarios;

    public TelaAdminTable(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        adicionarListenerDeBusca();
        exibirMensagemCarregando();
        carregarFuncionariosAssincrono();
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
        setIcon();
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
    }

    private void adicionarListenerDeBusca() {
        txtLogin.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarFuncionarios();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarFuncionarios();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarFuncionarios();
            }
        });
    }

    private void exibirMensagemCarregando() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{"Carregando...", "", "", "", "", ""});
    }

    private void carregarFuncionariosAssincrono() {
        new SwingWorker<List<Funcionario>, Void>() {
            @Override
            protected List<Funcionario> doInBackground() throws Exception {
                return CTCONTAB.listarFuncionarios();
            }

            @Override
            protected void done() {
                try {
                    listaFuncionarios = get();
                    atualizarTabela(listaFuncionarios);
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
        model.addRow(new Object[]{"Erro ao carregar dados.", "", "", "", "", ""});
    }

    private void atualizarTabela(List<Funcionario> funcionarios) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

        for (Funcionario funcionario : funcionarios) {
            String dataFormatada = "";

            try {
                Object createdAt = funcionario.getCreated_at();

                if (createdAt instanceof Date) {
                    dataFormatada = outputFormat.format((Date) createdAt);
                } else if (createdAt instanceof String) {
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date data = inputFormat.parse((String) createdAt);
                    dataFormatada = outputFormat.format(data);
                }
            } catch (Exception e) {
                dataFormatada = "Data inválida";
            }

            Object[] rowData = new Object[]{
                funcionario.getUsuario(),
                funcionario.getEmail(),
                funcionario.getPermissao(),
                dataFormatada,
                "1",
                "2",
                "3"
            };
            model.addRow(rowData);
        }
    }

    private void filtrarFuncionarios() {
        String filtro = txtLogin.getText().toLowerCase();
        List<Funcionario> funcionariosFiltrados = new ArrayList<>();

        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario.getUsuario().toLowerCase().contains(filtro)) {
                funcionariosFiltrados.add(funcionario);
            }
        }

        atualizarTabela(funcionariosFiltrados);
    }

    private void ajustarLarguraColunas() {
        jTable1.getColumnModel().getColumn(4).setMinWidth(62);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(62);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(62);
        jTable1.getColumnModel().getColumn(5).setMinWidth(62);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(62);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(62);
        jTable1.getColumnModel().getColumn(6).setMinWidth(62);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(62);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(62);
        jTable1.getColumnModel().getColumn(7).setMinWidth(20);
        jTable1.getColumnModel().getColumn(7).setMaxWidth(20);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(20);
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("");
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/seta-cima.png"));
            ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/seta-baixo.png"));
            ImageIcon icon3 = new ImageIcon(getClass().getResource("/images/delete-icon.png"));

            if (column == 4) {
                setIcon(icon);
                setBackground(new java.awt.Color(0, 74, 173));
            } else if (column == 5) {
                setIcon(icon2);
                setBackground(new java.awt.Color(255, 189, 89));
            } else if (column == 6) {
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
                if ("AlterarPermissao".equals(actionType)) {
                    alterarPermissaoFuncionario(selectedRow);
                } else if ("ReverterPermissao".equals(actionType)) {
                    reverterPermissaoFuncionario(selectedRow);
                } else if ("Excluir".equals(actionType)) {
                    excluirFuncionario(selectedRow);
                } else {
                }
                fireEditingStopped();
            });

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = value == null ? "" : value.toString();
            selectedRow = row;

            button.setText("");
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/seta-cima.png"));
            ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/seta-baixo.png"));
            ImageIcon icon3 = new ImageIcon(getClass().getResource("/images/delete-icon.png"));
            button.setIcon(icon);
            button.setIcon(icon2);
            button.setIcon(icon3);

            if (column == 4) {
                button.setBackground(new java.awt.Color(0, 74, 173));
            } else if (column == 5) {
                button.setBackground(new java.awt.Color(255, 189, 89));
            } else if (column == 6) {
                button.setBackground(new java.awt.Color(239, 65, 54));
            }

            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }

        private void alterarPermissaoFuncionario(int row) {
            try {
                Funcionario funcionario = listaFuncionarios.get(row);

                String novaPermissao = "ADM".equals(funcionario.getPermissao()) ? "NULL" : "ADM";

                CTCONTAB.alterarPermissao("usuarios", "id", funcionario.getId(), novaPermissao);

                funcionario.setPermissao(novaPermissao);
                atualizarTabela(listaFuncionarios);

                // JOptionPane.showMessageDialog(null, "Permissão alterada para: " + novaPermissao, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                MensagemUtil.exibirSucesso("Permissão alterada para: " + novaPermissao);
            } catch (Exception e) {
                // JOptionPane.showMessageDialog(null, "Erro ao alterar permissão: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                MensagemUtil.exibirErro("Erro ao alterar permissão!");
            }
        }

        private void reverterPermissaoFuncionario(int row) {
            try {
                Funcionario funcionario = listaFuncionarios.get(row);

                String novaPermissao = "NULL";

                CTCONTAB.alterarPermissao("usuarios", "id", funcionario.getId(), novaPermissao);

                funcionario.setPermissao(novaPermissao);
                atualizarTabela(listaFuncionarios);

                // JOptionPane.showMessageDialog(null, "Permissão revertida para: " + novaPermissao, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                MensagemUtil.exibirSucesso("Permissão revertida para: " + novaPermissao);
            } catch (Exception e) {
                // JOptionPane.showMessageDialog(null, "Erro ao reverter permissão: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                MensagemUtil.exibirErro("Erro ao reverter permissão!");
            }
        }

        private void excluirFuncionario(int row) {
            try {
                Funcionario funcionario = listaFuncionarios.get(row);

                CTCONTAB.excluirRegistro("usuarios", "id", funcionario.getId());

                listaFuncionarios.remove(row);
                atualizarTabela(listaFuncionarios);

                // JOptionPane.showMessageDialog(null, "Tarefa excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                MensagemUtil.exibirSucesso("Usuário excluído com sucesso!");
            } catch (Exception e) {
                // JOptionPane.showMessageDialog(null, "Erro ao excluir tarefa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                MensagemUtil.exibirErro("Erro ao excluir usuário!");
            }
        }

    }

    public TelaAdminTable() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblContabilidade = new javax.swing.JLabel();
        lblCTCONTAB = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNotificacoes = new javax.swing.JButton();
        lblUserIcon = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblTipoDePessoa = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblServico = new javax.swing.JLabel();
        lblTipoDePessoa4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnAdministracao = new javax.swing.JButton();
        JPanelTelaAcesso = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jlibLogo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administração - CT CONTAB");
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
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NOME", "EMAIL", "PERMISSÕES ", "DATA DE CRIAÇÃO", "AÇÃO 1", "AÇÃO 2", "AÇÃO 3", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(115, 115, 115));
        jTable1.setRowHeight(50);
        jTable1.getColumn("AÇÃO 1").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("AÇÃO 1").setCellEditor(new ButtonEditor(new JCheckBox(), "AlterarPermissao"));

        jTable1.getColumn("AÇÃO 2").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("AÇÃO 2").setCellEditor(new ButtonEditor(new JCheckBox(), "ReverterPermissao"));

        jTable1.getColumn("AÇÃO 3").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("AÇÃO 3").setCellEditor(new ButtonEditor(new JCheckBox(), "Excluir"));

        add(new JScrollPane(jTable1), BorderLayout.CENTER);

        ajustarLarguraColunas();
        jTable1.setShowHorizontalLines(true);
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

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minhatura-de-perfil.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1210, 15, 50, 50);

        lblNome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(186, 186, 186));
        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNome.setText("NOME");
        lblNome.setToolTipText("");
        getContentPane().add(lblNome);
        lblNome.setBounds(110, 190, 230, 30);

        lblTipoDePessoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTipoDePessoa.setForeground(new java.awt.Color(186, 186, 186));
        lblTipoDePessoa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoDePessoa.setText("EMAIL");
        lblTipoDePessoa.setToolTipText("");
        getContentPane().add(lblTipoDePessoa);
        lblTipoDePessoa.setBounds(340, 190, 220, 30);

        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(186, 186, 186));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("PERMISSÕES");
        lblStatus.setToolTipText("");
        getContentPane().add(lblStatus);
        lblStatus.setBounds(560, 190, 230, 30);

        lblServico.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblServico.setForeground(new java.awt.Color(186, 186, 186));
        lblServico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServico.setText("DATA DE CRIAÇÃO");
        lblServico.setToolTipText("");
        getContentPane().add(lblServico);
        lblServico.setBounds(790, 190, 220, 30);

        lblTipoDePessoa4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTipoDePessoa4.setForeground(new java.awt.Color(186, 186, 186));
        lblTipoDePessoa4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoDePessoa4.setText("AÇÕES");
        lblTipoDePessoa4.setToolTipText("");
        getContentPane().add(lblTipoDePessoa4);
        lblTipoDePessoa4.setBounds(1010, 190, 230, 30);

        jSeparator4.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator4.setForeground(new java.awt.Color(115, 115, 115));
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(110, 220, 1130, 80);

        jSeparator2.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator2.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(336, 190, 20, 450);

        jSeparator3.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator3.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(571, 190, 10, 450);

        jSeparator5.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator5.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(798, 190, 80, 450);

        jSeparator7.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator7.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator7);
        jSeparator7.setBounds(1011, 190, 60, 450);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(110, 190, 1130, 450);

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home-icon.png"))); // NOI18N
        btnHome.setContentAreaFilled(false);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnHome);
        btnHome.setBounds(0, 100, 80, 50);

        btnCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/event-icon.png"))); // NOI18N
        btnCalendario.setContentAreaFilled(false);
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalendario);
        btnCalendario.setBounds(5, 184, 70, 50);

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

        btnTarefas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/task-icon.png"))); // NOI18N
        btnTarefas.setContentAreaFilled(false);
        btnTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarefasActionPerformed(evt);
            }
        });
        getContentPane().add(btnTarefas);
        btnTarefas.setBounds(0, 420, 80, 50);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/config-icon.png"))); // NOI18N
        btnConfiguracoes.setContentAreaFilled(false);
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfiguracoes);
        btnConfiguracoes.setBounds(0, 500, 80, 50);

        btnAdministracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin-icon.png"))); // NOI18N
        btnAdministracao.setContentAreaFilled(false);
        btnAdministracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdministracao);
        btnAdministracao.setBounds(0, 570, 80, 60);

        JPanelTelaAcesso.setBackground(new java.awt.Color(194, 166, 40));
        getContentPane().add(JPanelTelaAcesso);
        JPanelTelaAcesso.setBounds(0, 565, 80, 70);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(120, 100, 20, 80);

        txtLogin.setBackground(new java.awt.Color(4, 21, 57));
        txtLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(115, 115, 115));
        txtLogin.setText("Escreva o nome do funcionário que deseja buscar.");
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
        btnLogin.setText("Auditoria");
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

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-contabil.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1280, 711);

        setSize(new java.awt.Dimension(1294, 708));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        new TelaAdmin(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

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
            java.util.logging.Logger.getLogger(TelaAdminTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAdminTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAdminTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAdminTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdminTable().setVisible(true);
            }
        });
    }

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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlibLogo2;
    private javax.swing.JLabel lblCTCONTAB;
    private javax.swing.JLabel lblContabilidade;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblServico;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTipoDePessoa;
    private javax.swing.JLabel lblTipoDePessoa4;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JTextField txtLogin;
    // End of variables declaration//GEN-END:variables
}
