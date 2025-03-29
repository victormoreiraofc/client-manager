package screen;

import Data.CTCONTAB;
import Screen.MensagemUtil;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.URI;
import javax.swing.JTextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelaRegistrar extends javax.swing.JFrame {

    private static final Logger logger = LoggerFactory.getLogger(TelaRegistrar.class);

    public TelaRegistrar() {
        initComponents();
        setIcon();
        setResizable(false);
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
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

        btnResgistrar = new javax.swing.JButton();
        jilbAindaNaoTemConta = new javax.swing.JLabel();
        jilbRegistreSe = new javax.swing.JLabel();
        lblCTCONTAB = new javax.swing.JLabel();
        lblContabilidade = new javax.swing.JLabel();
        jlibLogo = new javax.swing.JLabel();
        btnTermosServico = new javax.swing.JButton();
        jlibForcaSenha = new javax.swing.JLabel();
        jlibErroUsuario = new javax.swing.JLabel();
        jlibErroEmail = new javax.swing.JLabel();
        jProgressBar = new javax.swing.JProgressBar();
        jilbTermosDeServiço = new javax.swing.JLabel();
        jilbCreditos = new javax.swing.JLabel();
        jilbCreditos2 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        jilbUsuario = new javax.swing.JLabel();
        jilbEmail = new javax.swing.JLabel();
        jilbSenha = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JTextField();
        jlibBlueSquad = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar - CT CONTAB");
        getContentPane().setLayout(null);

        btnResgistrar.setBackground(new java.awt.Color(30, 30, 30));
        btnResgistrar.setContentAreaFilled(false);
        btnResgistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResgistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnResgistrar);
        btnResgistrar.setBounds(470, 420, 60, 20);

        jilbAindaNaoTemConta.setBackground(new java.awt.Color(255, 255, 255));
        jilbAindaNaoTemConta.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jilbAindaNaoTemConta.setForeground(new java.awt.Color(115, 115, 115));
        jilbAindaNaoTemConta.setText("Você já tem conta?");
        getContentPane().add(jilbAindaNaoTemConta);
        jilbAindaNaoTemConta.setBounds(380, 420, 130, 20);

        jilbRegistreSe.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jilbRegistreSe.setForeground(new java.awt.Color(194, 166, 40));
        jilbRegistreSe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jilbRegistreSe.setText("   Logar-me");
        getContentPane().add(jilbRegistreSe);
        jilbRegistreSe.setBounds(450, 420, 90, 20);

        lblCTCONTAB.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCTCONTAB.setForeground(new java.awt.Color(200, 200, 200));
        lblCTCONTAB.setText("CT CONTAB");
        getContentPane().add(lblCTCONTAB);
        lblCTCONTAB.setBounds(410, 85, 190, 40);

        lblContabilidade.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        lblContabilidade.setForeground(new java.awt.Color(153, 153, 0));
        lblContabilidade.setText("Contabilidade & Consultoria");
        getContentPane().add(lblContabilidade);
        lblContabilidade.setBounds(410, 85, 205, 80);

        jlibLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jlibLogo);
        jlibLogo.setBounds(340, 80, 60, 70);

        btnTermosServico.setBackground(new java.awt.Color(30, 30, 30));
        btnTermosServico.setContentAreaFilled(false);
        btnTermosServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermosServicoActionPerformed(evt);
            }
        });
        getContentPane().add(btnTermosServico);
        btnTermosServico.setBounds(370, 480, 190, 20);

        jlibForcaSenha.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jlibForcaSenha.setForeground(new java.awt.Color(115, 115, 115));
        jlibForcaSenha.setText("Força da Senha:");
        getContentPane().add(jlibForcaSenha);
        jlibForcaSenha.setBounds(490, 350, 110, 14);

        jlibErroUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/error-icon.png"))); // NOI18N
        getContentPane().add(jlibErroUsuario);
        jlibErroUsuario.setBounds(590, 220, 15, 20);

        jlibErroEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/error-icon.png"))); // NOI18N
        getContentPane().add(jlibErroEmail);
        jlibErroEmail.setBounds(590, 150, 15, 20);

        jProgressBar.setBackground(new java.awt.Color(115, 115, 115));
        jProgressBar.setForeground(new java.awt.Color(115, 115, 115));
        jProgressBar.setBorder(null);
        getContentPane().add(jProgressBar);
        jProgressBar.setBounds(320, 360, 110, 10);

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
        jilbCreditos.setBounds(330, 450, 270, 20);

        jilbCreditos2.setBackground(new java.awt.Color(255, 255, 255));
        jilbCreditos2.setFont(new java.awt.Font("SansSerif", 1, 8)); // NOI18N
        jilbCreditos2.setForeground(new java.awt.Color(115, 115, 115));
        jilbCreditos2.setText("18 do curso de Ciência da Computação da Uninove.");
        getContentPane().add(jilbCreditos2);
        jilbCreditos2.setBounds(360, 450, 210, 40);

        btnLogin.setBackground(new java.awt.Color(184, 135, 11));
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Registrar-se");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLoginMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLoginMouseReleased(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(320, 380, 280, 40);

        jilbUsuario.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jilbUsuario.setForeground(new java.awt.Color(194, 166, 40));
        jilbUsuario.setText("Usuário:");
        getContentPane().add(jilbUsuario);
        jilbUsuario.setBounds(320, 220, 190, 16);

        jilbEmail.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jilbEmail.setForeground(new java.awt.Color(194, 166, 40));
        jilbEmail.setText("Email:");
        getContentPane().add(jilbEmail);
        jilbEmail.setBounds(320, 150, 190, 16);

        jilbSenha.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jilbSenha.setForeground(new java.awt.Color(194, 166, 40));
        jilbSenha.setText("Senha:");
        getContentPane().add(jilbSenha);
        jilbSenha.setBounds(320, 290, 170, 20);

        txtEmail.setBackground(new java.awt.Color(4, 21, 57));
        txtEmail.setForeground(new java.awt.Color(115, 115, 115));
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
        txtEmail.setBounds(320, 170, 280, 40);
        addPlaceholder(txtEmail, "  seuemail@gmail.com");
        addPlaceholder(txtUsuario, "  Digite um nome de usuário");
        addPlaceholder(txtSenha, "  Digite sua senha");

        txtUsuario.setBackground(new java.awt.Color(4, 21, 57));
        txtUsuario.setForeground(new java.awt.Color(115, 115, 115));
        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(320, 240, 280, 40);

        txtSenha.setBackground(new java.awt.Color(4, 21, 57));
        txtSenha.setForeground(new java.awt.Color(115, 115, 115));
        txtSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSenhaKeyReleased(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(320, 310, 280, 40);

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
            MensagemUtil.exibirSucesso("Usuário cadastrado com sucesso");
            logger.info("[{}] criou uma nova conta", txtUsuario.getText());
            dispose();
            new TelaLogin().setVisible(true);
        } catch (ClassNotFoundException x) {
            MensagemUtil.exibirErro("Driver JDBC não encontrado!");
        } catch (SQLException x) {
            if (x.getMessage().contains("Duplicate entry")) {
                MensagemUtil.exibirErro("Este CPF já está cadastrado");
            } else {
                MensagemUtil.exibirErro("Erro na conexão com o banco de dados!");
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

    private void btnTermosServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTermosServicoActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/victormoreiraofc/client-manager/blob/main/CODE_OF_CONDUCT.md"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnTermosServicoActionPerformed

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        btnLogin.setBackground(new java.awt.Color(189, 135, 2));
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        btnLogin.setBackground(new java.awt.Color(184, 135, 11));
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnLoginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMousePressed
        btnLogin.setBackground(new java.awt.Color(139, 101, 8));
    }//GEN-LAST:event_btnLoginMousePressed

    private void btnLoginMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseReleased
        btnLogin.setBackground(new java.awt.Color(189, 135, 2));
    }//GEN-LAST:event_btnLoginMouseReleased

    private void btnResgistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResgistrarActionPerformed
        dispose();
        new TelaLogin().setVisible(true);
    }//GEN-LAST:event_btnResgistrarActionPerformed

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
    private javax.swing.JButton btnResgistrar;
    private javax.swing.JButton btnTermosServico;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JLabel jilbAindaNaoTemConta;
    private javax.swing.JLabel jilbCreditos;
    private javax.swing.JLabel jilbCreditos2;
    private javax.swing.JLabel jilbEmail;
    private javax.swing.JLabel jilbRegistreSe;
    private javax.swing.JLabel jilbSenha;
    private javax.swing.JLabel jilbTermosDeServiço;
    private javax.swing.JLabel jilbUsuario;
    private javax.swing.JLabel jlibBlueSquad;
    private javax.swing.JLabel jlibErroEmail;
    private javax.swing.JLabel jlibErroUsuario;
    private javax.swing.JLabel jlibForcaSenha;
    private javax.swing.JLabel jlibLogo;
    private javax.swing.JLabel lblCTCONTAB;
    private javax.swing.JLabel lblContabilidade;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
