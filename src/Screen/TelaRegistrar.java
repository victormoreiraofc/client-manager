package Screen;

import Data.CTCONTAB;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Color;

public class TelaRegistrar extends javax.swing.JFrame {

    public TelaRegistrar() {
        initComponents();
        System. out. println("arbitrary text");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlibForcaSenha = new javax.swing.JLabel();
        jlibErroUsuario = new javax.swing.JLabel();
        jlibErroEmail = new javax.swing.JLabel();
        jProgressBar = new javax.swing.JProgressBar();
        jilbTermosDeServiço = new javax.swing.JLabel();
        jilbCreditos = new javax.swing.JLabel();
        jilbCreditos2 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        jlibLogo = new javax.swing.JLabel();
        jilbUsuario = new javax.swing.JLabel();
        jilbEmail = new javax.swing.JLabel();
        jilbSenha = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JTextField();
        jlibBlueSquad = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jlibForcaSenha.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlibForcaSenha.setForeground(new java.awt.Color(115, 115, 115));
        jlibForcaSenha.setText("Força da Senha:");
        getContentPane().add(jlibForcaSenha);
        jlibForcaSenha.setBounds(490, 360, 110, 14);

        jlibErroUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/error-icon.png"))); // NOI18N
        getContentPane().add(jlibErroUsuario);
        jlibErroUsuario.setBounds(585, 230, 15, 20);

        jlibErroEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/error-icon.png"))); // NOI18N
        getContentPane().add(jlibErroEmail);
        jlibErroEmail.setBounds(585, 160, 15, 20);

        jProgressBar.setBackground(new java.awt.Color(115, 115, 115));
        jProgressBar.setForeground(new java.awt.Color(115, 115, 115));
        jProgressBar.setBorder(null);
        getContentPane().add(jProgressBar);
        jProgressBar.setBounds(320, 364, 110, 10);

        jilbTermosDeServiço.setBackground(new java.awt.Color(255, 255, 255));
        jilbTermosDeServiço.setFont(new java.awt.Font("SansSerif", 1, 8)); // NOI18N
        jilbTermosDeServiço.setForeground(new java.awt.Color(115, 115, 115));
        jilbTermosDeServiço.setText("<html><u>Termos de Serviço | Politica de Privacidade</u></html>");
        jilbTermosDeServiço.setToolTipText("");
        getContentPane().add(jilbTermosDeServiço);
        jilbTermosDeServiço.setBounds(380, 480, 180, 20);

        jilbCreditos.setBackground(new java.awt.Color(255, 255, 255));
        jilbCreditos.setFont(new java.awt.Font("SansSerif", 1, 8)); // NOI18N
        jilbCreditos.setForeground(new java.awt.Color(115, 115, 115));
        jilbCreditos.setText("© 2024 Uninove. CT CONTAB é um projeto desenvolvido pela Turma");
        getContentPane().add(jilbCreditos);
        jilbCreditos.setBounds(330, 440, 270, 20);

        jilbCreditos2.setBackground(new java.awt.Color(255, 255, 255));
        jilbCreditos2.setFont(new java.awt.Font("SansSerif", 1, 8)); // NOI18N
        jilbCreditos2.setForeground(new java.awt.Color(115, 115, 115));
        jilbCreditos2.setText("18 do curso de Ciência da Computação da Uninove.");
        getContentPane().add(jilbCreditos2);
        jilbCreditos2.setBounds(360, 440, 210, 40);

        btnLogin.setBackground(new java.awt.Color(194, 166, 40));
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Registrar-se");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(320, 390, 280, 40);

        jlibLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jlibLogo);
        jlibLogo.setBounds(350, 90, 220, 50);

        jilbUsuario.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jilbUsuario.setForeground(new java.awt.Color(194, 166, 40));
        jilbUsuario.setText("Usuário:");
        getContentPane().add(jilbUsuario);
        jilbUsuario.setBounds(320, 230, 190, 16);

        jilbEmail.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jilbEmail.setForeground(new java.awt.Color(194, 166, 40));
        jilbEmail.setText("Email:");
        getContentPane().add(jilbEmail);
        jilbEmail.setBounds(320, 160, 190, 16);

        jilbSenha.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jilbSenha.setForeground(new java.awt.Color(194, 166, 40));
        jilbSenha.setText("Senha:");
        getContentPane().add(jilbSenha);
        jilbSenha.setBounds(320, 300, 170, 20);

        txtEmail.setBackground(new java.awt.Color(4, 21, 57));
        txtEmail.setForeground(new java.awt.Color(115, 115, 115));
        txtEmail.setText("  seuemail@gmail.com");
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        getContentPane().add(txtEmail);
        txtEmail.setBounds(320, 180, 280, 40);

        txtUsuario.setBackground(new java.awt.Color(4, 21, 57));
        txtUsuario.setForeground(new java.awt.Color(115, 115, 115));
        txtUsuario.setText("  desenvolvedoradmin123");
        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(320, 250, 280, 40);

        txtSenha.setBackground(new java.awt.Color(4, 21, 57));
        txtSenha.setForeground(new java.awt.Color(115, 115, 115));
        txtSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSenhaKeyReleased(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(320, 320, 280, 40);

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

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            CTCONTAB.registrarUsuario(txtUsuario.getText(), txtEmail.getText(), txtSenha.getText());
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
            dispose();
            new TelaLogin().setVisible(true);
        } catch (ClassNotFoundException x) {
            JOptionPane.showMessageDialog(null, "Driver JDBC não encontrado " + x.getMessage());
        } catch (SQLException x) {
            if (x.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Este CPF já está cadastrado ");
            } else {
                JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados " + x.getMessage());
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        String email = txtEmail.getText();
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            jlibErroEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/correct-icon.png")));
        } else {
            jlibErroEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/error-icon.png")));
        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        String usuario = txtUsuario.getText();

        if (usuario.length() >= 5) {
            jlibErroUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/correct-icon.png")));
        } else {
            jlibErroUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/error-icon.png")));
        }
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void txtSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyReleased
        verificarForcaSenha();
    }//GEN-LAST:event_txtSenhaKeyReleased

    private void verificarForcaSenha() {
        String senha = new String(txtSenha.getText());
        int forca = calcularForcaSenha(senha);
        jProgressBar.setValue(forca);
        if (forca < 40) {
            jlibForcaSenha.setText("Força da Senha: Fraca");
            jlibForcaSenha.setForeground(Color.RED);
        } else if (forca < 70) {
            jlibForcaSenha.setText("Força da Senha: Média");
            jlibForcaSenha.setForeground(Color.ORANGE);
        } else {
            jlibForcaSenha.setText("Força da Senha: Forte");
            jlibForcaSenha.setForeground(Color.GREEN);
        }
    }

    private int calcularForcaSenha(String senha) {
        int forca = 0;

        if (senha.length() >= 8) {
            forca += 20;
        }
        if (senha.matches(".*[0-9].*")) {
            forca += 20;
        }
        if (senha.matches(".*[a-z].*")) {
            forca += 20;
        }
        if (senha.matches(".*[A-Z].*")) {
            forca += 20;
        }
        if (senha.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            forca += 20;
        }
        return forca;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaRegistrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRegistrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnLogin;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JLabel jilbCreditos;
    private javax.swing.JLabel jilbCreditos2;
    private javax.swing.JLabel jilbEmail;
    private javax.swing.JLabel jilbSenha;
    private javax.swing.JLabel jilbTermosDeServiço;
    private javax.swing.JLabel jilbUsuario;
    private javax.swing.JLabel jlibBlueSquad;
    private javax.swing.JLabel jlibErroEmail;
    private javax.swing.JLabel jlibErroUsuario;
    private javax.swing.JLabel jlibForcaSenha;
    private javax.swing.JLabel jlibLogo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
