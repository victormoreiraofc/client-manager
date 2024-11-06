package telas;

import Dados.Usuario;

public class TelaMenu extends javax.swing.JFrame {

  private Usuario usuarioLogado; // Variável para armazenar o usuário logado

    public TelaMenu(Usuario usuario) {
        this.usuarioLogado = usuario; // Armazena o usuário passado no construtor
        initComponents();
        
    }

    public TelaMenu() {
        initComponents();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        btnCadastrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jlibLogo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        lblNovosClientesMes = new javax.swing.JLabel();
        lblDarkerBackground1 = new javax.swing.JLabel();
        lblTotalClientesRegistrados = new javax.swing.JLabel();
        lblDarkerBackground2 = new javax.swing.JLabel();
        lblTarefasPendentes = new javax.swing.JLabel();
        lblDarkerBackground3 = new javax.swing.JLabel();
        lblServicosNaoRealizados = new javax.swing.JLabel();
        lblDarkerBackground5 = new javax.swing.JLabel();
        lblServicosFinalizados = new javax.swing.JLabel();
        lblDarkerBackground6 = new javax.swing.JLabel();
        lblTotalVendas = new javax.swing.JLabel();
        lblDarkerBackground4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_icon.png"))); // NOI18N
        btnHome.setContentAreaFilled(false);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnHome);
        btnHome.setBounds(5, 100, 70, 50);

        btnCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_calendar.png"))); // NOI18N
        btnCalendario.setContentAreaFilled(false);
        getContentPane().add(btnCalendario);
        btnCalendario.setBounds(5, 184, 70, 50);

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/client_icon.png"))); // NOI18N
        btnClientes.setContentAreaFilled(false);
        getContentPane().add(btnClientes);
        btnClientes.setBounds(5, 260, 70, 50);

        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon_report.png"))); // NOI18N
        btnRelatorios.setContentAreaFilled(false);
        getContentPane().add(btnRelatorios);
        btnRelatorios.setBounds(5, 340, 70, 50);

        btnTarefas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/todolist_icon.png"))); // NOI18N
        btnTarefas.setContentAreaFilled(false);
        getContentPane().add(btnTarefas);
        btnTarefas.setBounds(0, 420, 70, 50);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/config_icon.png"))); // NOI18N
        btnConfiguracoes.setContentAreaFilled(false);
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfiguracoes);
        btnConfiguracoes.setBounds(4, 500, 70, 50);

        btnAdministracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin_icon.png"))); // NOI18N
        btnAdministracao.setContentAreaFilled(false);
        getContentPane().add(btnAdministracao);
        btnAdministracao.setBounds(4, 580, 70, 60);

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_image.png"))); // NOI18N
        btnCadastrar.setContentAreaFilled(false);
        getContentPane().add(btnCadastrar);
        btnCadastrar.setBounds(150, 150, 1030, 110);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 640, 80, 60);

        jlibLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jlibLogo2);
        jlibLogo2.setBounds(4, 10, 60, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -50, 80, 750);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_icon.jpg"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1210, 15, 50, 50);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bell_icon.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1130, 10, 56, 60);

        lblNovosClientesMes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNovosClientesMes.setText("NOVOS CLIENTES NO MÊS");
        getContentPane().add(lblNovosClientesMes);
        lblNovosClientesMes.setBounds(180, 310, 180, 20);

        lblDarkerBackground1.setBackground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground1.setForeground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        lblDarkerBackground1.setText("jLabel3");
        getContentPane().add(lblDarkerBackground1);
        lblDarkerBackground1.setBounds(150, 300, 230, 100);

        lblTotalClientesRegistrados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalClientesRegistrados.setText("TOTAL DE CLIENTES REGISTRADOS");
        getContentPane().add(lblTotalClientesRegistrados);
        lblTotalClientesRegistrados.setBounds(440, 310, 240, 20);

        lblDarkerBackground2.setBackground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground2.setForeground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        lblDarkerBackground2.setText("jLabel3");
        getContentPane().add(lblDarkerBackground2);
        lblDarkerBackground2.setBounds(410, 300, 300, 100);

        lblTarefasPendentes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarefasPendentes.setText("TAREFAS PENDENTES");
        getContentPane().add(lblTarefasPendentes);
        lblTarefasPendentes.setBounds(750, 310, 160, 20);

        lblDarkerBackground3.setBackground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground3.setForeground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        lblDarkerBackground3.setText("jLabel3");
        getContentPane().add(lblDarkerBackground3);
        lblDarkerBackground3.setBounds(740, 300, 160, 100);

        lblServicosNaoRealizados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblServicosNaoRealizados.setText("SERVIÇOS NÃO REALIZADOS");
        getContentPane().add(lblServicosNaoRealizados);
        lblServicosNaoRealizados.setBounds(400, 430, 200, 20);

        lblDarkerBackground5.setBackground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground5.setForeground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        lblDarkerBackground5.setText("jLabel3");
        getContentPane().add(lblDarkerBackground5);
        lblDarkerBackground5.setBounds(350, 420, 300, 140);

        lblServicosFinalizados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblServicosFinalizados.setText("SERVIÇOS FINALIZADOS");
        getContentPane().add(lblServicosFinalizados);
        lblServicosFinalizados.setBounds(750, 430, 180, 20);

        lblDarkerBackground6.setBackground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground6.setForeground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        lblDarkerBackground6.setText("jLabel3");
        getContentPane().add(lblDarkerBackground6);
        lblDarkerBackground6.setBounds(690, 420, 300, 140);

        lblTotalVendas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalVendas.setText("TOTAL DE VENDAS");
        getContentPane().add(lblTotalVendas);
        lblTotalVendas.setBounds(990, 310, 130, 20);

        lblDarkerBackground4.setBackground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground4.setForeground(new java.awt.Color(5, 27, 74));
        lblDarkerBackground4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        lblDarkerBackground4.setText("jLabel3");
        getContentPane().add(lblDarkerBackground4);
        lblDarkerBackground4.setBounds(930, 300, 250, 100);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(23, 0, 220, 67);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-contabil.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1280, 711);

        setSize(new java.awt.Dimension(1294, 708));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfiguracoesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnAdministracao;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracoes;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnTarefas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jlibLogo2;
    private javax.swing.JLabel lblDarkerBackground1;
    private javax.swing.JLabel lblDarkerBackground2;
    private javax.swing.JLabel lblDarkerBackground3;
    private javax.swing.JLabel lblDarkerBackground4;
    private javax.swing.JLabel lblDarkerBackground5;
    private javax.swing.JLabel lblDarkerBackground6;
    private javax.swing.JLabel lblNovosClientesMes;
    private javax.swing.JLabel lblServicosFinalizados;
    private javax.swing.JLabel lblServicosNaoRealizados;
    private javax.swing.JLabel lblTarefasPendentes;
    private javax.swing.JLabel lblTotalClientesRegistrados;
    private javax.swing.JLabel lblTotalVendas;
    private javax.swing.JLabel lblUserIcon;
    // End of variables declaration//GEN-END:variables
}
