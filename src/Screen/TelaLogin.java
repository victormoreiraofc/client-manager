package screen;

import Data.Usuario;
import Data.CTCONTAB;
import Screen.MensagemUtil;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
        carregarCredenciais();
        jlibErroLogin.setVisible(false);
        jlibErroLoginIcon.setVisible(false);
        setIcon();
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
    }

    private void salvarCredenciais(String usuario, String senha) {
        if (usuario == null || usuario.isEmpty()) {
            return;
        }

        String fileName = "login_" + usuario + ".properties";
        Properties props = new Properties();
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            props.setProperty("usuario", usuario);
            props.setProperty("senha", senha);
            props.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarCredenciais() {
        File dir = new File(".");
        File[] files = dir.listFiles((d, name) -> name.startsWith("login_") && name.endsWith(".properties"));

        if (files != null && files.length > 0) {
            for (File file : files) {
                Properties props = new Properties();
                try (FileInputStream in = new FileInputStream(file)) {
                    props.load(in);
                    String usuario = props.getProperty("usuario");
                    String senha = props.getProperty("senha");

                    if (usuario != null && senha != null) {
                        txtLogin.setText(usuario);
                        txtSenha.setText(senha);
                        chbLembre.setSelected(true);
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

        btnCopiar2 = new javax.swing.JButton();
        btnCopiar = new javax.swing.JButton();
        lblCopy = new javax.swing.JLabel();
        lblCopy2 = new javax.swing.JLabel();
        lblCTCONTAB = new javax.swing.JLabel();
        lblContabilidade = new javax.swing.JLabel();
        btnTermosServico = new javax.swing.JButton();
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
        setTitle("Login - CT CONTAB");
        getContentPane().setLayout(null);

        btnCopiar2.setBackground(new java.awt.Color(30, 30, 30));
        btnCopiar2.setContentAreaFilled(false);
        btnCopiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiar2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCopiar2);
        btnCopiar2.setBounds(570, 270, 30, 40);

        btnCopiar.setBackground(new java.awt.Color(30, 30, 30));
        btnCopiar.setContentAreaFilled(false);
        btnCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCopiar);
        btnCopiar.setBounds(570, 200, 30, 40);

        lblCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/copy-file.png"))); // NOI18N
        lblCopy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblCopyKeyPressed(evt);
            }
        });
        getContentPane().add(lblCopy);
        lblCopy.setBounds(570, 200, 30, 40);

        lblCopy2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/copy-file.png"))); // NOI18N
        getContentPane().add(lblCopy2);
        lblCopy2.setBounds(570, 270, 30, 40);

        lblCTCONTAB.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCTCONTAB.setForeground(new java.awt.Color(200, 200, 200));
        lblCTCONTAB.setText("CT CONTAB");
        getContentPane().add(lblCTCONTAB);
        lblCTCONTAB.setBounds(410, 95, 190, 40);

        lblContabilidade.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        lblContabilidade.setForeground(new java.awt.Color(153, 153, 0));
        lblContabilidade.setText("Contabilidade & Consultoria");
        getContentPane().add(lblContabilidade);
        lblContabilidade.setBounds(410, 95, 205, 80);

        btnTermosServico.setBackground(new java.awt.Color(30, 30, 30));
        btnTermosServico.setContentAreaFilled(false);
        btnTermosServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermosServicoActionPerformed(evt);
            }
        });
        getContentPane().add(btnTermosServico);
        btnTermosServico.setBounds(370, 480, 190, 20);

        jlibErroLoginIcon.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jlibErroLoginIcon.setForeground(new java.awt.Color(255, 0, 0));
        jlibErroLoginIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red-alert.png"))); // NOI18N
        getContentPane().add(jlibErroLoginIcon);
        jlibErroLoginIcon.setBounds(543, 200, 50, 40);

        jlibErroLogin.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jlibErroLogin.setForeground(new java.awt.Color(255, 0, 0));
        jlibErroLogin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlibErroLogin.setText("Usuário ou senha estão errados!");
        getContentPane().add(jlibErroLogin);
        jlibErroLogin.setBounds(416, 310, 180, 40);

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
        chbMostrarSenha.setBounds(540, 270, 30, 40);

        txtLogin.setBackground(new java.awt.Color(4, 21, 57));
        txtLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(115, 115, 115));
        txtLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        txtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoginKeyPressed(evt);
            }
        });
        getContentPane().add(txtLogin);
        txtLogin.setBounds(320, 200, 280, 40);
        addPlaceholder(txtLogin, "  seuemail@gmail.com");

        btnLogin.setBackground(new java.awt.Color(184, 135, 11));
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnLogin.setBorderPainted(false);
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
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLoginKeyPressed(evt);
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
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
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

        chbLembre.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        chbLembre.setForeground(new java.awt.Color(194, 166, 40));
        chbLembre.setText("Lembre-me");
        chbLembre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbLembreActionPerformed(evt);
            }
        });
        getContentPane().add(chbLembre);
        chbLembre.setBounds(320, 320, 100, 20);

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
        jilbRegistreSe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jilbRegistreSe.setText("  Registre-se");
        getContentPane().add(jilbRegistreSe);
        jilbRegistreSe.setBounds(470, 400, 90, 20);

        btnResgistrar.setBackground(new java.awt.Color(30, 30, 30));
        btnResgistrar.setContentAreaFilled(false);
        btnResgistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResgistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnResgistrar);
        btnResgistrar.setBounds(480, 400, 70, 20);

        jlibLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jlibLogo);
        jlibLogo.setBounds(340, 90, 60, 70);

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

            if (usuarioLogado != null) {
                if (chbLembre.isSelected()) {
                    salvarCredenciais(txtLogin.getText(), new String(txtSenha.getPassword()));
                }

                dispose();
                new TelaMenu(usuarioLogado).setVisible(true);
            } else {
                mostrarMensagemErro();
            }

        } catch (ClassNotFoundException | SQLException x) {
            MensagemUtil.exibirErro("Erro na conexão com o banco de dados!");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void mostrarMensagemErro() {
        jlibErroLoginIcon.setVisible(true);
        jlibErroLogin.setText("Usuário ou senha estão errados!");
        jlibErroLogin.setForeground(Color.RED);
        jlibErroLogin.setVisible(true);
    }

    private void mostrarMensagemCopiaComSucesso() {
        jlibErroLogin.setText("Cópia do login feita com sucesso!");
        jlibErroLogin.setForeground(Color.GREEN);
        jlibErroLogin.setVisible(true);
    }

    private void mostrarMensagemCopia2ComSucesso() {
        jlibErroLogin.setText("Cópia da senha feita com sucesso!");
        jlibErroLogin.setForeground(Color.GREEN);
        jlibErroLogin.setVisible(true);
    }

    private void mostrarMensagemErroNaCopia() {
        jlibErroLogin.setText("Erro na cópia, Tente Novamente!");
        jlibErroLogin.setForeground(Color.RED);
        jlibErroLogin.setVisible(true);
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

    private void btnLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLoginKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            btnLogin.doClick();
        }
    }//GEN-LAST:event_btnLoginKeyPressed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            btnLogin.doClick();
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            btnLogin.doClick();
        }
    }//GEN-LAST:event_txtLoginKeyPressed

    private void lblCopyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblCopyKeyPressed

    }//GEN-LAST:event_lblCopyKeyPressed

    private void btnCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarActionPerformed
        String texto = txtLogin.getText();
        StringSelection stringSelection = new StringSelection(texto);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        if (clipboard != null) {
            clipboard.setContents(stringSelection, null);
            mostrarMensagemCopiaComSucesso();
        } else {
            mostrarMensagemErroNaCopia();
        }
    }//GEN-LAST:event_btnCopiarActionPerformed

    private void btnCopiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiar2ActionPerformed
        String senha = txtSenha.getText();
        StringSelection stringSelection = new StringSelection(senha);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        if (clipboard != null) {
            clipboard.setContents(stringSelection, null);
            mostrarMensagemCopia2ComSucesso();
        } else {
            mostrarMensagemErroNaCopia();
        }
    }//GEN-LAST:event_btnCopiar2ActionPerformed

    public static void main(String args[]) {
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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton btnCopiar;
    private javax.swing.JButton btnCopiar2;
    private javax.swing.JButton btnEsqueceuSenha;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnResgistrar;
    private javax.swing.JButton btnTermosServico;
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
    private javax.swing.JLabel lblCTCONTAB;
    private javax.swing.JLabel lblContabilidade;
    private javax.swing.JLabel lblCopy;
    private javax.swing.JLabel lblCopy2;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
