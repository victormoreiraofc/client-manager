package telas;

import Dados.Usuario;
import Dados.CTCONTAB;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class TelaLogin extends javax.swing.JFrame {

    private static final String FILE_NAME = "login.properties";
    
    public TelaLogin() {
        initComponents();
        carregarCredenciais(); // Carrega as credenciais salvas
    }
    // Método para salvar as credenciais no arquivo
    private void salvarCredenciais(String usuario, String senha) {
        Properties props = new Properties();
        try (FileOutputStream out = new FileOutputStream(FILE_NAME)) {
            props.setProperty("usuario", usuario);
            props.setProperty("senha", senha);
            props.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar as credenciais do arquivo
    private void carregarCredenciais() {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(FILE_NAME)) {
            props.load(in);
            String usuario = props.getProperty("usuario");
            String senha = props.getProperty("senha");

            if (usuario != null && senha != null) {
                txtLogin.setText(usuario);
                txtSenha.setText(senha);
                chbLembre.setSelected(true); 
            }
        } catch (IOException e) {
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        txtLogin = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        chbLembre = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnResgistrar = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jCheckBox1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-aberto.png"))); // NOI18N
        jCheckBox1.setInheritsPopupMenu(true);
        jCheckBox1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-vermelho.png"))); // NOI18N
        jCheckBox1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-vermelho.png"))); // NOI18N
        jCheckBox1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-vermelho.png"))); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1);
        jCheckBox1.setBounds(510, 230, 30, 28);
        getContentPane().add(txtLogin);
        txtLogin.setBounds(270, 130, 280, 60);

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(300, 310, 210, 40);

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(270, 220, 280, 50);

        jLabel1.setForeground(new java.awt.Color(194, 166, 40));
        jLabel1.setText("Email ou Usuário:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(270, 110, 190, 16);

        jLabel2.setForeground(new java.awt.Color(194, 166, 40));
        jLabel2.setText("Senha:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(270, 200, 170, 16);

        chbLembre.setForeground(new java.awt.Color(194, 166, 40));
        chbLembre.setText("Lembre-me");
        chbLembre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbLembreActionPerformed(evt);
            }
        });
        getContentPane().add(chbLembre);
        chbLembre.setBounds(270, 280, 85, 20);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(115, 115, 115));
        jLabel4.setText("Ainda não tem conta?");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(310, 360, 190, 20);

        jLabel3.setForeground(new java.awt.Color(194, 166, 40));
        jLabel3.setText("Registre-se");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(430, 360, 80, 20);

        btnResgistrar.setBackground(new java.awt.Color(30, 30, 30));
        btnResgistrar.setContentAreaFilled(false);
        btnResgistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResgistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnResgistrar);
        btnResgistrar.setBounds(420, 360, 100, 20);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-contabil.png"))); // NOI18N
        Background.setText("jLabel3");
        getContentPane().add(Background);
        Background.setBounds(0, 0, 790, 510);

        setSize(new java.awt.Dimension(800, 516));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            Usuario usuarioLogado = CTCONTAB.fazerLoginU(txtLogin.getText(), new String(txtSenha.getPassword()));
        
            if (usuarioLogado != null) {
                if (chbLembre.isSelected()) {
                    salvarCredenciais(txtLogin.getText(), new String(txtSenha.getPassword()));
                } else {
                    salvarCredenciais("", "");
                }

                dispose();
                new TelaMenu(usuarioLogado).setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null,"Usuário, email e/ou senha inválidos");
            }

        } catch (ClassNotFoundException x) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado " + x.getMessage());
        } catch (SQLException x) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados " + x.getMessage());
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnResgistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResgistrarActionPerformed
       dispose();
       new TelaRegistrar().setVisible(true);
    }//GEN-LAST:event_btnResgistrarActionPerformed

    private void chbLembreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbLembreActionPerformed
        // Já implementado no Login
    }//GEN-LAST:event_chbLembreActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnResgistrar;
    private javax.swing.JCheckBox chbLembre;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
