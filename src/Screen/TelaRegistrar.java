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
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import java.awt.Color;

public class TelaRegistrar extends javax.swing.JFrame {
    
    private static final Logger logger = LoggerFactory.getLogger(TelaRegistrar.class);

    private javax.swing.border.Border bordaPadrao;
    private javax.swing.border.Border bordaErro;
    
    public TelaRegistrar() {
        initComponents();
        
        try {
            java.net.URL url = getClass().getResource("/images/Close Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Close Icon.png ou src/images/Close Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(11, 11, java.awt.Image.SCALE_SMOOTH);
                btnFecharTela.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            btnFecharTela.setText("X"); // fallback
        }

        try {
            java.net.URL url = getClass().getResource("/images/Maximize Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Maximize Icon.png ou src/images/Maximize Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(11, 11, java.awt.Image.SCALE_SMOOTH);
                btnMaximizarTela.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            btnMaximizarTela.setText("[]"); // fallback
        }

        try {
            java.net.URL url = getClass().getResource("/images/Minimize Icon.png");
            if (url == null) {
                System.err.println("Imagem não encontrada. Verifique: /images/Minimize Icon.png ou src/images/Minimize Icon.png");
            } else {
                java.awt.Image img = javax.imageio.ImageIO.read(url)
                        .getScaledInstance(11, 2, java.awt.Image.SCALE_SMOOTH);
                btnMinimizarTela.setIcon(new javax.swing.ImageIcon(img));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            btnMinimizarTela.setText("-"); // fallback
        }
        
        setUndecorated(true);
        estilizarComboLinguagem();
        setIcon();
        setResizable(false);
        
        bordaPadrao = javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 1, true);
        bordaErro = javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 1, true);

        txtUsuario.setBorder(bordaPadrao);
        txtEmail.setBorder(bordaPadrao);
        txtSenha.setBorder(bordaPadrao);
        txtSenha2.setBorder(bordaPadrao);
        
        Color corCampo = new Color(0, 0, 0, 0);
        txtUsuario.setBackground(corCampo);
        txtUsuario.setOpaque(false);
        txtUsuario.setCaretColor(Color.WHITE);
        txtUsuario.setForeground(Color.WHITE);

        txtEmail.setBackground(corCampo);
        txtEmail.setOpaque(false);
        txtEmail.setCaretColor(Color.WHITE);
        
        txtSenha.setBackground(corCampo);
        txtSenha.setOpaque(false);
        txtSenha.setCaretColor(Color.WHITE);
        txtSenha.setForeground(Color.WHITE);
        
        txtSenha2.setBackground(corCampo);
        txtSenha2.setOpaque(false);
        txtSenha2.setCaretColor(Color.WHITE);
        txtSenha2.setForeground(Color.WHITE);
        
        addPlaceholder(txtEmail, "  email@exemplo.com");
        
        jlibErroRegistro.setVisible(false);
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

        btnFecharTela = new javax.swing.JButton();
        btnMaximizarTela = new javax.swing.JButton();
        btnMinimizarTela = new javax.swing.JButton();
        btnResgistrar = new javax.swing.JButton();
        jilbAindaNaoTemConta = new javax.swing.JLabel();
        jilbRegistreSe = new javax.swing.JLabel();
        btnTermosServico = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jilbUsuario = new javax.swing.JLabel();
        jilbEmail = new javax.swing.JLabel();
        jilbSenha = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jlibLogo1 = new javax.swing.JLabel();
        jilRegistre = new javax.swing.JLabel();
        jilbLinha = new javax.swing.JLabel();
        jilbTexto = new javax.swing.JLabel();
        jilbTexto2 = new javax.swing.JLabel();
        jilbSenha2 = new javax.swing.JLabel();
        jilbCreditos1 = new javax.swing.JLabel();
        chbLembre = new javax.swing.JCheckBox();
        jlibErroRegistro = new javax.swing.JLabel();
        chbMostrarSenha = new javax.swing.JCheckBox();
        txtSenha2 = new javax.swing.JPasswordField();
        txtSenha = new javax.swing.JPasswordField();
        jilbGlobo = new javax.swing.JLabel();
        cmbLinguagens = new javax.swing.JComboBox<>();
        jlibBlueSquad = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CT Contab Manager");
        getContentPane().setLayout(null);

        btnFecharTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Icon.png"))); // NOI18N
        btnFecharTela.setContentAreaFilled(false);
        btnFecharTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnFecharTela);
        btnFecharTela.setBounds(1425, 0, 15, 25);

        btnMaximizarTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Maximize Icon.png"))); // NOI18N
        btnMaximizarTela.setContentAreaFilled(false);
        btnMaximizarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizarTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnMaximizarTela);
        btnMaximizarTela.setBounds(1390, 0, 15, 25);

        btnMinimizarTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minimize Icon.png"))); // NOI18N
        btnMinimizarTela.setContentAreaFilled(false);
        btnMinimizarTela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizarTelaMouseClicked(evt);
            }
        });
        btnMinimizarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarTelaActionPerformed(evt);
            }
        });
        getContentPane().add(btnMinimizarTela);
        btnMinimizarTela.setBounds(1355, 0, 15, 25);

        btnResgistrar.setBackground(new java.awt.Color(30, 30, 30));
        btnResgistrar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnResgistrar.setForeground(new java.awt.Color(16, 165, 103));
        btnResgistrar.setContentAreaFilled(false);
        btnResgistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResgistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnResgistrar);
        btnResgistrar.setBounds(140, 580, 50, 20);

        jilbAindaNaoTemConta.setBackground(new java.awt.Color(255, 255, 255));
        jilbAindaNaoTemConta.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jilbAindaNaoTemConta.setForeground(new java.awt.Color(255, 255, 255));
        jilbAindaNaoTemConta.setText("Você já tem conta?");
        getContentPane().add(jilbAindaNaoTemConta);
        jilbAindaNaoTemConta.setBounds(40, 580, 130, 20);

        jilbRegistreSe.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jilbRegistreSe.setForeground(new java.awt.Color(16, 165, 103));
        jilbRegistreSe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jilbRegistreSe.setText("  Login");
        getContentPane().add(jilbRegistreSe);
        jilbRegistreSe.setBounds(120, 580, 80, 20);

        btnTermosServico.setBackground(new java.awt.Color(30, 30, 30));
        btnTermosServico.setContentAreaFilled(false);
        btnTermosServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermosServicoActionPerformed(evt);
            }
        });
        getContentPane().add(btnTermosServico);
        btnTermosServico.setBounds(120, 680, 190, 20);

        btnLogin.setBackground(new java.awt.Color(17, 168, 100));
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Registrar");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
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
        btnLogin.setBounds(40, 540, 360, 40);

        jilbUsuario.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jilbUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jilbUsuario.setText("Usuário");
        getContentPane().add(jilbUsuario);
        jilbUsuario.setBounds(40, 220, 190, 16);

        jilbEmail.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jilbEmail.setForeground(new java.awt.Color(255, 255, 255));
        jilbEmail.setText("Email");
        getContentPane().add(jilbEmail);
        jilbEmail.setBounds(40, 290, 190, 16);

        jilbSenha.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jilbSenha.setForeground(new java.awt.Color(255, 255, 255));
        jilbSenha.setText("Senha");
        getContentPane().add(jilbSenha);
        jilbSenha.setBounds(40, 360, 170, 20);

        txtEmail.setBackground(new java.awt.Color(4, 21, 57));
        txtEmail.setForeground(new java.awt.Color(115, 115, 115));
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
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
        txtEmail.setBounds(40, 310, 360, 40);
        addPlaceholder(txtEmail, "  email@exemplo.com");

        txtUsuario.setBackground(new java.awt.Color(4, 21, 57));
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
        txtUsuario.setCaretColor(new java.awt.Color(255, 255, 255));
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(40, 240, 360, 40);

        jlibLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logoctcontab.png"))); // NOI18N
        jlibLogo1.setPreferredSize(new java.awt.Dimension(40, 59));
        getContentPane().add(jlibLogo1);
        jlibLogo1.setBounds(10, 0, 200, 80);

        jilRegistre.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jilRegistre.setForeground(new java.awt.Color(255, 255, 255));
        jilRegistre.setText("Registre-se");
        jilRegistre.setPreferredSize(new java.awt.Dimension(100, 100));
        getContentPane().add(jilRegistre);
        jilRegistre.setBounds(40, 100, 170, 50);

        jilbLinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line 1.png"))); // NOI18N
        jilbLinha.setText("jLabel1");
        getContentPane().add(jilbLinha);
        jilbLinha.setBounds(0, 0, 440, 160);

        jilbTexto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jilbTexto.setForeground(new java.awt.Color(255, 255, 255));
        jilbTexto.setText("Toda a organização da sua contabilidade em um só lugar,");
        getContentPane().add(jilbTexto);
        jilbTexto.setBounds(40, 150, 360, 30);

        jilbTexto2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jilbTexto2.setForeground(new java.awt.Color(255, 255, 255));
        jilbTexto2.setText("totalmente digital.");
        getContentPane().add(jilbTexto2);
        jilbTexto2.setBounds(40, 170, 120, 30);

        jilbSenha2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jilbSenha2.setForeground(new java.awt.Color(255, 255, 255));
        jilbSenha2.setText("Confirme a Senha");
        getContentPane().add(jilbSenha2);
        jilbSenha2.setBounds(40, 430, 170, 20);

        jilbCreditos1.setBackground(new java.awt.Color(255, 255, 255));
        jilbCreditos1.setForeground(new java.awt.Color(255, 255, 255));
        jilbCreditos1.setText("  © 2025 CT Contab. Todos os direitos reservados.");
        getContentPane().add(jilbCreditos1);
        jilbCreditos1.setBounds(10, 710, 350, 30);

        chbLembre.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        chbLembre.setForeground(new java.awt.Color(255, 255, 255));
        chbLembre.setText("Lembre me?");
        chbLembre.setIcon(createCheckboxIcon(false));
        chbLembre.setSelectedIcon(createCheckboxIcon(true));
        chbLembre.setRolloverIcon(createCheckboxIcon(false));
        chbLembre.setRolloverSelectedIcon(createCheckboxIcon(true));
        chbLembre.setContentAreaFilled(false);
        chbLembre.setBorderPainted(false);
        chbLembre.setFocusPainted(false);
        chbLembre.setOpaque(false);
        getContentPane().add(chbLembre);
        chbLembre.setBounds(40, 490, 110, 30);

        jlibErroRegistro.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jlibErroRegistro.setForeground(new java.awt.Color(255, 0, 0));
        jlibErroRegistro.setText("Seu e-mail, senha ou usuário estão incorretos.");
        getContentPane().add(jlibErroRegistro);
        jlibErroRegistro.setBounds(140, 490, 260, 30);

        chbMostrarSenha.setContentAreaFilled(false);
        chbMostrarSenha.setFocusPainted(false);
        chbMostrarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-aberto.png"))); // NOI18N
        chbMostrarSenha.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/olho-fechado.png"))); // NOI18N
        chbMostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMostrarSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(chbMostrarSenha);
        chbMostrarSenha.setBounds(360, 380, 30, 40);

        txtSenha2.setBackground(new java.awt.Color(4, 21, 57));
        txtSenha2.setForeground(new java.awt.Color(115, 115, 115));
        txtSenha2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
        getContentPane().add(txtSenha2);
        txtSenha2.setBounds(40, 450, 360, 40);

        txtSenha.setBackground(new java.awt.Color(4, 21, 57));
        txtSenha.setForeground(new java.awt.Color(255, 255, 255));
        txtSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(txtSenha);
        txtSenha.setBounds(40, 380, 360, 40);

        jilbGlobo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Globo.png"))); // NOI18N
        getContentPane().add(jilbGlobo);
        jilbGlobo.setBounds(300, 20, 30, 40);

        cmbLinguagens.setBackground(new java.awt.Color(10, 25, 47));
        cmbLinguagens.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbLinguagens.setForeground(new java.awt.Color(255, 255, 255));
        cmbLinguagens.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "  Português" }));
        cmbLinguagens.setBorder(null);
        cmbLinguagens.setOpaque(false);
        cmbLinguagens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLinguagensActionPerformed(evt);
            }
        });
        getContentPane().add(cmbLinguagens);
        cmbLinguagens.setBounds(300, 20, 120, 40);

        jlibBlueSquad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Overlay.png"))); // NOI18N
        getContentPane().add(jlibBlueSquad);
        jlibBlueSquad.setBounds(0, 0, 440, 750);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background Login.png"))); // NOI18N
        Background.setText("jLabel3");
        getContentPane().add(Background);
        Background.setBounds(40, 0, 1420, 760);

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
      
        String usuario = txtUsuario.getText().trim();
        String email = txtEmail.getText().trim();
        String senha = txtSenha.getText().trim();
        String confirmarSenha = txtSenha2.getText().trim();

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        boolean emailValido = email.matches(emailRegex);

        boolean usuarioValido = usuario.length() >= 5;

        boolean senhasIguais = senha.equals(confirmarSenha);

        if (!emailValido || !usuarioValido || !senhasIguais) {
            mostrarMensagemErro();
            return; 
    }        
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
                MensagemUtil.exibirErro("Este Usuário já está cadastrado");
            } else {
                MensagemUtil.exibirErro("Erro na conexão com o banco de dados!");
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed
    
    private ImageIcon createCheckboxIcon(boolean checked) {
        int size = 18;
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (checked) {
            g.setColor(new java.awt.Color(16, 165, 103));
            g.fillRoundRect(0, 0, size, size, 4, 4);
            g.setColor(java.awt.Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 14));
            g.drawString("✓", 3, size - 4);
        } else {
            g.setColor(new java.awt.Color(84, 84, 84));
            g.drawRoundRect(0, 0, size - 1, size - 1, 4, 4);
        }

        g.dispose();
        return new ImageIcon(img);
}

    
    private void mostrarMensagemErro() {
        jlibErroRegistro.setText("Seu e-mail, senha ou usuário estão incorretos.");
        jlibErroRegistro.setForeground(Color.RED);
        jlibErroRegistro.setVisible(true);

        txtUsuario.setBorder(bordaErro);
        txtEmail.setBorder(bordaErro);
        txtSenha.setBorder(bordaErro);
        txtSenha2.setBorder(bordaErro);

        new Thread(() -> {
            try {
                Thread.sleep(2000);

                for (int i = 0; i <= 20; i++) {
                    int r = 255 - (int) ((255 - 84) * (i / 20.0));
                    int g = 0 + (int) ((84 - 0) * (i / 20.0));
                    int b = 0 + (int) ((84 - 0) * (i / 20.0));
                    Color corSuave = new Color(r, g, b);

                    javax.swing.SwingUtilities.invokeLater(() -> {
                        txtUsuario.setBorder(BorderFactory.createLineBorder(corSuave, 1, true));
                        txtEmail.setBorder(BorderFactory.createLineBorder(corSuave, 1, true));
                        txtSenha.setBorder(BorderFactory.createLineBorder(corSuave, 1, true));
                        txtSenha2.setBorder(BorderFactory.createLineBorder(corSuave, 1, true));
                    });

                    Thread.sleep(30);
                }

                javax.swing.SwingUtilities.invokeLater(() -> {
                    txtUsuario.setBorder(bordaPadrao);
                    txtEmail.setBorder(bordaPadrao);
                    txtSenha.setBorder(bordaPadrao);
                    txtSenha2.setBorder(bordaPadrao);            
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    private void estilizarComboLinguagem() {
 
        UIManager.put("ComboBox.background", new Color(10, 25, 47));
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", new Color(30, 50, 80));
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("ComboBox.buttonBackground", new Color(10, 25, 47));
        UIManager.put("ComboBox.border", BorderFactory.createEmptyBorder());

        cmbLinguagens.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cmbLinguagens.setForeground(Color.WHITE);
        cmbLinguagens.setBackground(new Color(10, 25, 47));
        cmbLinguagens.setOpaque(false);
        cmbLinguagens.setBorder(null);
        cmbLinguagens.setFocusable(false);

        cmbLinguagens.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index,
                                                                   boolean isSelected, boolean cellHasFocus) {
                java.awt.Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBackground(isSelected ? new Color(30, 50, 80) : new Color(10, 25, 47));
                setForeground(Color.WHITE);
                setFont(new Font("Segoe UI", Font.PLAIN, 16));
                setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
                return c;
            }
        });

        cmbLinguagens.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
        @Override
        protected javax.swing.JButton createArrowButton() {
            javax.swing.JButton button = new javax.swing.JButton("▼");
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(10, 25, 47));
            button.setFocusable(false);
            button.setContentAreaFilled(false);
            button.setOpaque(false);
            return button;
        }
    });
   }
    
    
    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void btnTermosServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTermosServicoActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/victormoreiraofc/client-manager/blob/main/CODE_OF_CONDUCT.md"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnTermosServicoActionPerformed

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        btnLogin.setBackground(new java.awt.Color(20, 190, 115));
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        btnLogin.setBackground(new java.awt.Color(17, 168, 100));
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnLoginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMousePressed
        btnLogin.setBackground(new java.awt.Color(14, 140, 85));
    }//GEN-LAST:event_btnLoginMousePressed

    private void btnLoginMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseReleased
        btnLogin.setBackground(new java.awt.Color(17, 168, 100));
    }//GEN-LAST:event_btnLoginMouseReleased

    private void btnResgistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResgistrarActionPerformed
        dispose();
        new TelaLogin().setVisible(true);
    }//GEN-LAST:event_btnResgistrarActionPerformed

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginMouseClicked

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void chbMostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMostrarSenhaActionPerformed
        if (chbMostrarSenha.isSelected()) {
        txtSenha.setEchoChar((char) 0);
        txtSenha2.setEchoChar((char) 0);
    } else {
        txtSenha.setEchoChar('•');
        txtSenha2.setEchoChar('•');
    }

    }//GEN-LAST:event_chbMostrarSenhaActionPerformed

    private void cmbLinguagensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLinguagensActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLinguagensActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnFecharTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharTelaActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFecharTelaActionPerformed

    private void btnMaximizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaximizarTelaActionPerformed

    private void btnMinimizarTelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarTelaMouseClicked
        setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarTelaMouseClicked

    private void btnMinimizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarTelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMinimizarTelaActionPerformed

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
    private javax.swing.JButton btnFecharTela;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnMaximizarTela;
    private javax.swing.JButton btnMinimizarTela;
    private javax.swing.JButton btnResgistrar;
    private javax.swing.JButton btnTermosServico;
    private javax.swing.JCheckBox chbLembre;
    private javax.swing.JCheckBox chbMostrarSenha;
    private javax.swing.JComboBox<String> cmbLinguagens;
    private javax.swing.JLabel jilRegistre;
    private javax.swing.JLabel jilbAindaNaoTemConta;
    private javax.swing.JLabel jilbCreditos1;
    private javax.swing.JLabel jilbEmail;
    private javax.swing.JLabel jilbGlobo;
    private javax.swing.JLabel jilbLinha;
    private javax.swing.JLabel jilbRegistreSe;
    private javax.swing.JLabel jilbSenha;
    private javax.swing.JLabel jilbSenha2;
    private javax.swing.JLabel jilbTexto;
    private javax.swing.JLabel jilbTexto2;
    private javax.swing.JLabel jilbUsuario;
    private javax.swing.JLabel jlibBlueSquad;
    private javax.swing.JLabel jlibErroRegistro;
    private javax.swing.JLabel jlibLogo1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JPasswordField txtSenha2;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
