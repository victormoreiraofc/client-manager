package screen;

import Data.CTCONTAB;
import static Data.CTCONTAB.excluirRegistro;
import Data.IconUtil;
import Data.PermissaoUtil;
import Data.Tarefa;
import Data.Usuario;
import Screen.MensagemUtil;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.JTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class TelaTarefa extends javax.swing.JFrame {

    private static final Logger logger = LoggerFactory.getLogger(TelaTarefa.class);

    private Usuario usuarioLogado;
    private Tarefa tarefa;

    public TelaTarefa(Usuario usuario, Tarefa tarefa) {
        this.usuarioLogado = usuario;
        initComponents();
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
        IconUtil.setIcon(usuarioLogado, lblUserIcon);

        if (tarefa != null) {
            this.tarefa = tarefa;
            preencherCampos(tarefa);
        } else {
            this.tarefa = new Tarefa();
            preencherCamposNovoTarefa();
        }
        setIcon();
        setResizable(false);
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
    }

    private void preencherCamposNovoTarefa() {
        txtStatusTarefa.setSelectedItem("Pendente");
        jLabel6.setText("0");
        jLabel7.setText(usuarioLogado.getUsuario());
    }

    private void preencherCampos(Tarefa tarefa) {
        txtNomeTarefa.setText(tarefa.getNomeTarefa());
        txtDescricao.setText(tarefa.getDescricao());
        txtStatusTarefa.setSelectedItem(tarefa.getStatusTarefa());
        txtDataVencimento.setText(tarefa.getDataVencimento());
        txtPrioridade.setText(tarefa.getPrioridade());
        jLabel6.setText(String.valueOf(tarefa.getId()));
        jLabel7.setText(tarefa.getResponsavel());
    }

    private void salvarAlteracoes(Tarefa tarefa) {
        MDC.put("usuario", usuarioLogado.getUsuario());
        try {
            tarefa.setNomeTarefa(txtNomeTarefa.getText());
            tarefa.setDescricao(txtDescricao.getText());
            tarefa.setStatusTarefa(txtStatusTarefa.getSelectedItem().toString());
            tarefa.setDataVencimento(txtDataVencimento.getText());
            tarefa.setPrioridade(txtPrioridade.getText());
            tarefa.setResponsavel(jLabel7.getText());

            String codigoText = jLabel6.getText();
            if (codigoText != null && !codigoText.trim().isEmpty() && !codigoText.equals("0")) {
                tarefa.setId(Integer.parseInt(codigoText));
            } else {
                tarefa.setId(0);
            }

            if (tarefa.getId() == 0) {
                CTCONTAB.registrarTarefa(tarefa);
                MensagemUtil.exibirSucesso("Nova tarefa cadastrado com sucesso!");
                logger.info("cadastrou uma nova tarefa");
            } else {
                CTCONTAB.atualizarTarefa(tarefa);
                MensagemUtil.exibirSucesso("Tarefa atualizada com sucesso!");
                logger.info("atualizou uma tarefa");
            }
        } catch (Exception e) {
            MensagemUtil.exibirErro("Erro ao salvar alterações!");
            e.printStackTrace();
        }
    }

    private void excluirTarefa() {
        MDC.put("usuario", usuarioLogado.getUsuario());
        try {
            String codigoText = jLabel6.getText();
            if (codigoText != null && !codigoText.trim().isEmpty() && !codigoText.equals("0")) {
                int idTarefa = Integer.parseInt(codigoText);

                int resposta = JOptionPane.showConfirmDialog(this,
                        "Tem certeza de que deseja excluir esta tarefa?",
                        "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (resposta == JOptionPane.YES_OPTION) {
                    excluirRegistro("tarefa", "id", idTarefa);

                    MensagemUtil.exibirSucesso("Tarefa excluída com sucesso!");
                    logger.info("excluiu uma tarefa");
                    new TelaTarefaTable(usuarioLogado).setVisible(true);
                    this.dispose();
                }
            } else {
                MensagemUtil.exibirErro("Nenhuma tarefa selecionada para exclusão!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            MensagemUtil.exibirErro("Erro ao excluir tarefa: " + e.getMessage());
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
        JPanelTelaAcesso5 = new javax.swing.JPanel();
        lblContabilidade = new javax.swing.JLabel();
        lblCTCONTAB = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnLogin = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtNomeTarefa = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        txtDataVencimento = new javax.swing.JTextField();
        btnLogin1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtPrioridade = new javax.swing.JTextField();
        txtStatusTarefa = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlibLogo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nova Tarefa - CT CONTAB");
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

        JPanelTelaAcesso5.setBackground(new java.awt.Color(194, 166, 40));
        getContentPane().add(JPanelTelaAcesso5);
        JPanelTelaAcesso5.setBounds(0, 390, 80, 70);

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

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete-icon.png"))); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(1010, 10, 40, 40);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save-icon.png"))); // NOI18N
        jPanel1.add(jLabel14);
        jLabel14.setBounds(1050, 10, 40, 40);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fechar.png"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(1090, 10, 40, 40);

        jButton3.setBackground(new java.awt.Color(84, 84, 84));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(1010, 10, 40, 40);

        jButton2.setBackground(new java.awt.Color(126, 217, 87));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(1050, 10, 40, 40);

        jLabel4.setBackground(new java.awt.Color(153, 153, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(205, 168, 16));
        jLabel4.setText("Cadastrar Tarefa");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 10, 250, 40);

        jSeparator1.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator1.setForeground(new java.awt.Color(115, 115, 115));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 60, 1140, 30);

        btnLogin.setBackground(new java.awt.Color(84, 84, 84));
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Cancelar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin);
        btnLogin.setBounds(780, 490, 170, 40);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nome da Tarefa");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 70, 140, 30);

        txtNomeTarefa.setBackground(new java.awt.Color(4, 21, 57));
        txtNomeTarefa.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtNomeTarefa.setForeground(new java.awt.Color(115, 115, 115));
        txtNomeTarefa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtNomeTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeTarefaActionPerformed(evt);
            }
        });
        jPanel1.add(txtNomeTarefa);
        txtNomeTarefa.setBounds(20, 120, 510, 40);
        addPlaceholder(txtNomeTarefa, "  Digite o nome da tarefa");
        addPlaceholder(txtDescricao, "  Digite sobre a tarefa");
        addPlaceholder(txtDataVencimento, "  Digite o Ano-Mês-Dia");
        addPlaceholder(txtPrioridade, "  Digite qual o nível da prioridade");

        txtDescricao.setBackground(new java.awt.Color(4, 21, 57));
        txtDescricao.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtDescricao.setForeground(new java.awt.Color(115, 115, 115));
        txtDescricao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDescricao);
        txtDescricao.setBounds(20, 220, 510, 40);

        txtDataVencimento.setBackground(new java.awt.Color(4, 21, 57));
        txtDataVencimento.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtDataVencimento.setForeground(new java.awt.Color(115, 115, 115));
        txtDataVencimento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtDataVencimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataVencimentoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDataVencimento);
        txtDataVencimento.setBounds(20, 420, 510, 40);

        btnLogin1.setBackground(new java.awt.Color(194, 166, 40));
        btnLogin1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogin1.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin1.setText("Criar");
        btnLogin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogin1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin1);
        btnLogin1.setBounds(950, 490, 170, 40);

        jButton1.setBackground(new java.awt.Color(239, 65, 54));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(1090, 10, 40, 40);

        txtPrioridade.setBackground(new java.awt.Color(4, 21, 57));
        txtPrioridade.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtPrioridade.setForeground(new java.awt.Color(115, 115, 115));
        txtPrioridade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtPrioridade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrioridadeActionPerformed(evt);
            }
        });
        jPanel1.add(txtPrioridade);
        txtPrioridade.setBounds(580, 120, 530, 40);

        txtStatusTarefa.setBackground(new java.awt.Color(102, 102, 102));
        txtStatusTarefa.setForeground(new java.awt.Color(255, 255, 255));
        txtStatusTarefa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendente", "Em andamento", "Concluido" }));
        txtStatusTarefa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtStatusTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStatusTarefaActionPerformed(evt);
            }
        });
        jPanel1.add(txtStatusTarefa);
        txtStatusTarefa.setBounds(20, 320, 510, 35);

        jLabel6.setVisible(false);
        jPanel1.add(jLabel6);
        jLabel6.setBounds(530, 280, 60, 40);

        jLabel7.setVisible(false);
        jPanel1.add(jLabel7);
        jLabel7.setBounds(600, 120, 80, 70);

        jLabel28.setForeground(new java.awt.Color(153, 153, 153));
        jLabel28.setText("De um titulo para tarefa que deseja que seja feita, seja breve e objetivo.");
        jPanel1.add(jLabel28);
        jLabel28.setBounds(20, 100, 730, 16);

        jLabel29.setForeground(new java.awt.Color(153, 153, 153));
        jLabel29.setText("Forneça detalhes sobre a tarefa, incluindo o objetivo, as etapas necessárias e quaisquer informações relevantes.");
        jPanel1.add(jLabel29);
        jLabel29.setBounds(20, 200, 730, 16);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Descrição");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(20, 170, 140, 30);

        jLabel30.setForeground(new java.awt.Color(153, 153, 153));
        jLabel30.setText("Selecione o status apropriado da tarefa para acompanhar o progresso.");
        jPanel1.add(jLabel30);
        jLabel30.setBounds(20, 300, 730, 16);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Status");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(20, 270, 140, 30);

        jLabel31.setForeground(new java.awt.Color(153, 153, 153));
        jLabel31.setText("Escolha a data de vencimento da tarefa para garantir que ela seja concluída dentro do prazo.");
        jPanel1.add(jLabel31);
        jLabel31.setBounds(20, 400, 730, 16);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Data de Vencimento");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(20, 370, 140, 30);

        jLabel32.setForeground(new java.awt.Color(153, 153, 153));
        jLabel32.setText("Atribua um nível de prioridade à tarefa (por exemplo, alta, média, baixa).");
        jPanel1.add(jLabel32);
        jLabel32.setBounds(580, 100, 730, 16);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Prioridade");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(580, 70, 140, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(110, 100, 1140, 540);

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
        new TelaTarefaTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtNomeTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeTarefaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeTarefaActionPerformed

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed

    private void txtDataVencimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataVencimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataVencimentoActionPerformed

    private void btnLogin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin1ActionPerformed
        btnLogin1.addActionListener(e -> salvarAlteracoes(tarefa));
    }//GEN-LAST:event_btnLogin1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new TelaTarefaTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtPrioridadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrioridadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrioridadeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton2.addActionListener(e -> salvarAlteracoes(tarefa));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        excluirTarefa();
    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void txtStatusTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStatusTarefaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStatusTarefaActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel JPanelTelaAcesso5;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlibLogo2;
    private javax.swing.JLabel lblCTCONTAB;
    private javax.swing.JLabel lblContabilidade;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JTextField txtDataVencimento;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtNomeTarefa;
    private javax.swing.JTextField txtPrioridade;
    private javax.swing.JComboBox<String> txtStatusTarefa;
    // End of variables declaration//GEN-END:variables
}
