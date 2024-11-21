package Screen;

import Data.Usuario;

public class TelaAdminTable extends javax.swing.JFrame {

  private Usuario usuarioLogado;

    public TelaAdminTable(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents(); 
    }

    public TelaAdminTable() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        lblUserIcon = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        jLabel3 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/seta-cima.png"))); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(1090, 230, 33, 33);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alert-bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1160, 10, 60, 60);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minhatura-de-perfil.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1210, 15, 50, 50);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/seta-baixo.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(1130, 230, 33, 33);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete-icon.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(1170, 230, 33, 33);

        jButton1.setBackground(new java.awt.Color(239, 65, 54));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(1170, 230, 33, 33);

        jButton2.setBackground(new java.awt.Color(255, 189, 89));
        getContentPane().add(jButton2);
        jButton2.setBounds(1130, 230, 33, 33);

        jButton3.setBackground(new java.awt.Color(0, 74, 173));
        jButton3.setMargin(new java.awt.Insets(8, 15, 8, 15));
        jButton3.setMaximumSize(new java.awt.Dimension(50, 22));
        getContentPane().add(jButton3);
        jButton3.setBounds(1090, 230, 33, 33);

        lblNome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNome.setForeground(new java.awt.Color(186, 186, 186));
        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNome.setText("NOME");
        lblNome.setToolTipText("");
        getContentPane().add(lblNome);
        lblNome.setBounds(110, 190, 180, 30);

        lblTipoDePessoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTipoDePessoa.setForeground(new java.awt.Color(186, 186, 186));
        lblTipoDePessoa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoDePessoa.setText("EMAIL");
        lblTipoDePessoa.setToolTipText("");
        getContentPane().add(lblTipoDePessoa);
        lblTipoDePessoa.setBounds(290, 190, 350, 30);

        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(186, 186, 186));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("PERMISSÕES");
        lblStatus.setToolTipText("");
        getContentPane().add(lblStatus);
        lblStatus.setBounds(640, 190, 200, 30);

        lblServico.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblServico.setForeground(new java.awt.Color(186, 186, 186));
        lblServico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServico.setText("DATA DE CRIAÇÃO");
        lblServico.setToolTipText("");
        getContentPane().add(lblServico);
        lblServico.setBounds(840, 190, 200, 30);

        lblTipoDePessoa4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTipoDePessoa4.setForeground(new java.awt.Color(186, 186, 186));
        lblTipoDePessoa4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoDePessoa4.setText("AÇÕES");
        lblTipoDePessoa4.setToolTipText("");
        getContentPane().add(lblTipoDePessoa4);
        lblTipoDePessoa4.setBounds(1040, 190, 200, 30);

        jSeparator4.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator4.setForeground(new java.awt.Color(115, 115, 115));
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(110, 220, 1130, 80);

        jSeparator2.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator2.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(290, 190, 10, 450);

        jSeparator3.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator3.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(640, 190, 50, 450);

        jSeparator5.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator5.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(840, 190, 30, 450);

        jSeparator7.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator7.setForeground(new java.awt.Color(115, 115, 115));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator7);
        jSeparator7.setBounds(1040, 190, 30, 450);

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
        txtLogin.setText("          Escreva o nome do funcionário que deseja buscar.");
        txtLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo-semfundo.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 290, 70);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-contabil.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1280, 711);

        setSize(new java.awt.Dimension(1294, 708));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        new TelaAdmin().setVisible(true);
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new TelaClienteTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel jlibLogo2;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblServico;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTipoDePessoa;
    private javax.swing.JLabel lblTipoDePessoa4;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JTextField txtLogin;
    // End of variables declaration//GEN-END:variables
}
