package Screen;

import Data.Usuario;
import Data.CTCONTAB;
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
        jlibErroLogin.setVisible(false);
        jlibErroLoginIcon.setVisible(false);
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

        jlibErroLoginIcon = new javax.swing.JLabel();
        jlibErroLogin = new javax.swing.JLabel();
        jlibEsqueceuASenha = new javax.swing.JLabel();
        btnEsqueceuSenha = new javax.swing.JButton();
        chbMostrarSenha = new javax.swing.JCheckBox();
        txtLogin = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        jilbEmailOuUsuario = new javax.swing.JLabel();
        jilbSenha = new javax.swing.JLabel();
        chbLembre = new javax.swing.JCheckBox();
        jilbTermosDeServiço = new javax.swing.JLabel();
        jilbCreditos2 = new javax.swing.JLabel();
        jilbCreditos = new javax.swing.JLabel();
        jilbAindaNaoTemConta = new javax.swing.JLabel();
        jilbRegistreSe = new javax.swing.JLabel();
        btnResgistrar = new javax.swing.JButton();
        jlibLogo = new javax.swing.JLabel();
        jlibBlueSquad = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CT CONTAB Contabilidade & Consultaria");
        getContentPane().setLayout(null);

        jlibErroLoginIcon.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jlibErroLoginIcon.setForeground(new java.awt.Color(255, 0, 0));
        jlibErroLoginIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alert-icon.png"))); // NOI18N
        getContentPane().add(jlibErroLoginIcon);
        jlibErroLoginIcon.setBounds(570, 210, 30, 20);

        jlibErroLogin.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jlibErroLogin.setForeground(new java.awt.Color(255, 0, 0));
        jlibErroLogin.setText("Usuário ou senha estão errados!");
        getContentPane().add(jlibErroLogin);
        jlibErroLogin.setBounds(456, 310, 144, 40);

        jlibEsqueceuASenha.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        jlibEsqueceuASenha.setForeground(new java.awt.Color(194, 166, 40));
        jlibEsqueceuASenha.setText("<html><u>Esqueceu a senha?</u></html>");
        getContentPane().add(jlibEsqueceuASenha);
        jlibEsqueceuASenha.setBounds(510, 250, 100, 20);

        btnEsqueceuSenha.setBackground(new java.awt.Color(30, 30, 30));
        btnEsqueceuSenha.setContentAreaFilled(false);
        btnEsqueceuSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsqueceuSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(btnEsqueceuSenha);
        btnEsqueceuSenha.setBounds(500, 250, 110, 20);

        chbMostrarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-aberto.png"))); // NOI18N
        chbMostrarSenha.setInheritsPopupMenu(true);
        chbMostrarSenha.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-fechado.png"))); // NOI18N
        chbMostrarSenha.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-fechado.png"))); // NOI18N
        chbMostrarSenha.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-fechado.png"))); // NOI18N
        chbMostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMostrarSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(chbMostrarSenha);
        chbMostrarSenha.setBounds(560, 270, 30, 40);

        txtLogin.setBackground(new java.awt.Color(4, 21, 57));
        txtLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(115, 115, 115));
        txtLogin.setText("  seuemail@gmail.com");
        txtLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        getContentPane().add(txtLogin);
        txtLogin.setBounds(320, 200, 280, 40);

        btnLogin.setBackground(new java.awt.Color(194, 166, 40));
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(320, 360, 280, 40);

        txtSenha.setBackground(new java.awt.Color(4, 21, 57));
        txtSenha.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtSenha.setForeground(new java.awt.Color(115, 115, 115));
        txtSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(320, 270, 280, 40);

        jilbEmailOuUsuario.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jilbEmailOuUsuario.setForeground(new java.awt.Color(194, 166, 40));
        jilbEmailOuUsuario.setText("Email ou Usuário:");
        getContentPane().add(jilbEmailOuUsuario);
        jilbEmailOuUsuario.setBounds(320, 180, 190, 16);

        jilbSenha.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jilbSenha.setForeground(new java.awt.Color(194, 166, 40));
        jilbSenha.setText("Senha:");
        getContentPane().add(jilbSenha);
        jilbSenha.setBounds(320, 250, 170, 20);

        chbLembre.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        chbLembre.setForeground(new java.awt.Color(194, 166, 40));
        chbLembre.setText("Lembre-me");
        chbLembre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbLembreActionPerformed(evt);
            }
        });
        getContentPane().add(chbLembre);
        chbLembre.setBounds(320, 320, 100, 19);

        jilbTermosDeServiço.setBackground(new java.awt.Color(255, 255, 255));
        jilbTermosDeServiço.setFont(new java.awt.Font("SansSerif", 1, 8)); // NOI18N
        jilbTermosDeServiço.setForeground(new java.awt.Color(115, 115, 115));
        jilbTermosDeServiço.setText("<html><u>Termos de Serviço | Politica de Privacidade</u></html>");
        jilbTermosDeServiço.setToolTipText("");
        getContentPane().add(jilbTermosDeServiço);
        jilbTermosDeServiço.setBounds(380, 480, 180, 20);

        jilbCreditos2.setBackground(new java.awt.Color(255, 255, 255));
        jilbCreditos2.setFont(new java.awt.Font("SansSerif", 1, 8)); // NOI18N
        jilbCreditos2.setForeground(new java.awt.Color(115, 115, 115));
        jilbCreditos2.setText("18 do curso de Ciência da Computação da Uninove.");
        getContentPane().add(jilbCreditos2);
        jilbCreditos2.setBounds(360, 440, 210, 40);

        jilbCreditos.setBackground(new java.awt.Color(255, 255, 255));
        jilbCreditos.setFont(new java.awt.Font("SansSerif", 1, 8)); // NOI18N
        jilbCreditos.setForeground(new java.awt.Color(115, 115, 115));
        jilbCreditos.setText("© 2024 Uninove. CT CONTAB é um projeto desenvolvido pela Turma");
        getContentPane().add(jilbCreditos);
        jilbCreditos.setBounds(330, 440, 270, 20);

        jilbAindaNaoTemConta.setBackground(new java.awt.Color(255, 255, 255));
        jilbAindaNaoTemConta.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jilbAindaNaoTemConta.setForeground(new java.awt.Color(115, 115, 115));
        jilbAindaNaoTemConta.setText("Ainda não tem conta?");
        getContentPane().add(jilbAindaNaoTemConta);
        jilbAindaNaoTemConta.setBounds(380, 400, 130, 20);

        jilbRegistreSe.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jilbRegistreSe.setForeground(new java.awt.Color(194, 166, 40));
        jilbRegistreSe.setText("  Registre-se");
        getContentPane().add(jilbRegistreSe);
        jilbRegistreSe.setBounds(483, 400, 70, 20);

        btnResgistrar.setBackground(new java.awt.Color(30, 30, 30));
        btnResgistrar.setContentAreaFilled(false);
        btnResgistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResgistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnResgistrar);
        btnResgistrar.setBounds(490, 400, 60, 20);

        jlibLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jlibLogo);
        jlibLogo.setBounds(350, 100, 220, 50);

        jlibBlueSquad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/retangulo-azul.png"))); // NOI18N
        getContentPane().add(jlibBlueSquad);
        jlibBlueSquad.setBounds(300, 70, 320, 440);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-contabil.png"))); // NOI18N
        Background.setText("jLabel3");
        getContentPane().add(Background);
        Background.setBounds(0, -10, 930, 660);

        setSize(new java.awt.Dimension(919, 622));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            Usuario usuarioLogado = CTCONTAB.fazerLoginU(txtLogin.getText(), new String(txtSenha.getPassword()));

            // Se o login for bem-sucedido
            if (usuarioLogado != null) {
                if (chbLembre.isSelected()) {
                    salvarCredenciais(txtLogin.getText(), new String(txtSenha.getPassword()));
                } else {
                    salvarCredenciais("", "");
                }

                dispose();
                new TelaMenu(usuarioLogado).setVisible(true);
            } else {
                // Se o login falhar
                mostrarMensagemErro();
            }

        } catch (ClassNotFoundException x) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado " + x.getMessage());
        } catch (SQLException x) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados " + x.getMessage());
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void mostrarMensagemErro() {
        jlibErroLoginIcon.setVisible(true);
        jlibErroLogin.setText("Usuário ou senha estão errados!");
        jlibErroLogin.setVisible(true); // Mostra a mensagem de erro
    }

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

    private void chbMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMostrarSenhaActionPerformed
        if (chbMostrarSenha.isSelected()) {
            txtSenha.setEchoChar((char) 0);
        } else {
            txtSenha.setEchoChar('•');
        }
    }//GEN-LAST:event_chbMostrarSenhaActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

    private void btnEsqueceuSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsqueceuSenhaActionPerformed

    }//GEN-LAST:event_btnEsqueceuSenhaActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnEsqueceuSenha;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnResgistrar;
    private javax.swing.JCheckBox chbLembre;
    private javax.swing.JCheckBox chbMostrarSenha;
    private javax.swing.JLabel jilbAindaNaoTemConta;
    private javax.swing.JLabel jilbCreditos;
    private javax.swing.JLabel jilbCreditos2;
    private javax.swing.JLabel jilbEmailOuUsuario;
    private javax.swing.JLabel jilbRegistreSe;
    private javax.swing.JLabel jilbSenha;
    private javax.swing.JLabel jilbTermosDeServiço;
    private javax.swing.JLabel jlibBlueSquad;
    private javax.swing.JLabel jlibErroLogin;
    private javax.swing.JLabel jlibErroLoginIcon;
    private javax.swing.JLabel jlibEsqueceuASenha;
    private javax.swing.JLabel jlibLogo;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}