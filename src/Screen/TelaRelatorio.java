package screen;

import Data.CTCONTAB;
import static Data.CTCONTAB.excluirRegistro;
import Data.IconUtil;
import Data.PermissaoUtil;
import Data.Relatorio;
import Data.Usuario;
import Screen.MensagemUtil;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class TelaRelatorio extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private Relatorio relatorio;

    public TelaRelatorio(Usuario usuario, Relatorio relatorio) {
        this.usuarioLogado = usuario;
        initComponents();
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
        IconUtil.setIcon(usuarioLogado, lblUserIcon);

        if (relatorio != null) {
            this.relatorio = relatorio;
            preencherCampos(relatorio);
        } else {
            this.relatorio = new Relatorio();
            preencherCamposNovoRelatorio();
        }
        setIcon();
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
    }

    private void preencherCamposNovoRelatorio() {
        txtNomeRelatorio.setText("  Nome do Relatório.");
        txtDescricao.setText("  Descreva o Relatório.");
        txtStatusRelatorio.setSelectedItem("Pendente");
        txtDataCadastro.setText(" Data de Cadastro.");
        jLabel6.setText("0");
    }

    private void preencherCampos(Relatorio relatorio) {
        txtNomeRelatorio.setText(relatorio.getNomeRelatorio());
        txtDescricao.setText(relatorio.getDescricao());
        txtStatusRelatorio.setSelectedItem(relatorio.getStatusRelatorio());
        txtDataCadastro.setText(relatorio.getDataCadastro());
        jLabel6.setText(String.valueOf(relatorio.getId()));
    }

    private void salvarAlteracoes(Relatorio relatorio) {
        try {
            relatorio.setNomeRelatorio(txtNomeRelatorio.getText());
            relatorio.setDescricao(txtDescricao.getText());
            relatorio.setStatusRelatorio(txtStatusRelatorio.getSelectedItem().toString());
            relatorio.setDataCadastro(txtDataCadastro.getText());

            String codigoText = jLabel6.getText();
            if (codigoText != null && !codigoText.trim().isEmpty() && !codigoText.equals("0")) {
                relatorio.setId(Integer.parseInt(codigoText));
            } else {
                relatorio.setId(0);
            }

            if (relatorio.getId() == 0) {
                CTCONTAB.registrarRelatorio(relatorio);
                // JOptionPane.showMessageDialog(this, "Novo relatorio cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                MensagemUtil.exibirSucesso("Novo relatório cadastrado com sucesso!");
            } else {
                CTCONTAB.atualizarRelatorio(relatorio);
                // JOptionPane.showMessageDialog(this, "Relatorio atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                MensagemUtil.exibirSucesso("Relatorio atualizado com sucesso!");
            }
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(this, "Erro ao salvar alterações: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            MensagemUtil.exibirErro("Erro ao salvar alterações!");
            e.printStackTrace();
        }
    }

    private void excluirRelatorio() {
        try {
            String codigoText = jLabel6.getText();
            if (codigoText != null && !codigoText.trim().isEmpty() && !codigoText.equals("0")) {
                int idRelatorio = Integer.parseInt(codigoText);

                int resposta = JOptionPane.showConfirmDialog(this,
                        "Tem certeza de que deseja excluir este relatório?",
                        "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (resposta == JOptionPane.YES_OPTION) {
                    excluirRegistro("relatorio", "id", idRelatorio);

                    //JOptionPane.showMessageDialog(this, "Relatório excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    MensagemUtil.exibirSucesso("Relatório excluído com sucesso!");
                    new TelaRelatorioTable(usuarioLogado).setVisible(true);
                    this.dispose();
                }
            } else {
                // JOptionPane.showMessageDialog(this, "Nenhum relatório selecionado para exclusão!", "Erro", JOptionPane.ERROR_MESSAGE);
                MensagemUtil.exibirErro("Nenhum relatório selecionado para exclusão!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // JOptionPane.showMessageDialog(this, "Erro ao excluir relatório: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            MensagemUtil.exibirErro("Erro ao excluir relatório!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblContabilidade = new javax.swing.JLabel();
        lblCTCONTAB = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnLogin = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNomeRelatorio = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        txtDataCadastro = new javax.swing.JTextField();
        btnLogin1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtStatusRelatorio = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnAdministracao = new javax.swing.JButton();
        JPanelTelaAcesso = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jlibLogo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Novo Relatório - CT CONTAB");
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

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save-icon.png"))); // NOI18N
        jPanel1.add(jLabel13);
        jLabel13.setBounds(1050, 10, 40, 40);

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
        jLabel4.setText("Cadastrar Relatório");
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
        jLabel9.setText("Nome do Relatório");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 70, 140, 30);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Descrição");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(20, 140, 100, 30);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Status");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(20, 210, 100, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Data de Cadastro");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(20, 280, 120, 30);

        txtNomeRelatorio.setBackground(new java.awt.Color(4, 21, 57));
        txtNomeRelatorio.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtNomeRelatorio.setForeground(new java.awt.Color(115, 115, 115));
        txtNomeRelatorio.setText("  Nome do Relatório.");
        txtNomeRelatorio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtNomeRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeRelatorioActionPerformed(evt);
            }
        });
        jPanel1.add(txtNomeRelatorio);
        txtNomeRelatorio.setBounds(20, 100, 250, 40);

        txtDescricao.setBackground(new java.awt.Color(4, 21, 57));
        txtDescricao.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtDescricao.setForeground(new java.awt.Color(115, 115, 115));
        txtDescricao.setText("  Descreva o Relatório.");
        txtDescricao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescricaoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDescricao);
        txtDescricao.setBounds(20, 170, 430, 40);

        txtDataCadastro.setBackground(new java.awt.Color(4, 21, 57));
        txtDataCadastro.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtDataCadastro.setForeground(new java.awt.Color(115, 115, 115));
        txtDataCadastro.setText(" Data de Cadastro.");
        txtDataCadastro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtDataCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataCadastroActionPerformed(evt);
            }
        });
        jPanel1.add(txtDataCadastro);
        txtDataCadastro.setBounds(20, 310, 250, 40);

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

        txtStatusRelatorio.setBackground(new java.awt.Color(102, 102, 102));
        txtStatusRelatorio.setForeground(new java.awt.Color(255, 255, 255));
        txtStatusRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendente", "Em andamento", "Concluido" }));
        txtStatusRelatorio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        jPanel1.add(txtStatusRelatorio);
        txtStatusRelatorio.setBounds(20, 240, 250, 35);

        jLabel6.setVisible(false);
        jPanel1.add(jLabel6);
        jLabel6.setBounds(390, 330, 70, 0);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(110, 100, 1140, 540);

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
        JPanelTelaAcesso.setBounds(0, 330, 80, 70);

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

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        new TelaMenu(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        new TelaConfiguracao(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConfiguracoesActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        new TelaClienteTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnAdministracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracaoActionPerformed
        new TelaAdminTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdministracaoActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        new TelaRelatorioTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtNomeRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeRelatorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeRelatorioActionPerformed

    private void txtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescricaoActionPerformed

    private void txtDataCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataCadastroActionPerformed

    private void btnLogin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin1ActionPerformed
        btnLogin1.addActionListener(e -> salvarAlteracoes(relatorio));
    }//GEN-LAST:event_btnLogin1ActionPerformed

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        new TelaEventoTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCalendarioActionPerformed

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosActionPerformed
        new TelaRelatorioTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRelatoriosActionPerformed

    private void btnTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarefasActionPerformed
        new TelaTarefaTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTarefasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new TelaRelatorioTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton2.addActionListener(e -> salvarAlteracoes(relatorio));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        excluirRelatorio();
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

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
    private javax.swing.JButton btnLogin1;
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnTarefas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlibLogo2;
    private javax.swing.JLabel lblCTCONTAB;
    private javax.swing.JLabel lblContabilidade;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JTextField txtDataCadastro;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtNomeRelatorio;
    private javax.swing.JComboBox<String> txtStatusRelatorio;
    // End of variables declaration//GEN-END:variables
}
