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
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import java.awt.Color;

public class TelaLogin extends javax.swing.JFrame {

    private javax.swing.border.Border bordaPadrao;
    private javax.swing.border.Border bordaErro;
    
    public TelaLogin() {
        initComponents();
        estilizarComboLinguagem();
        carregarCredenciais();
        jlibErroLogin.setVisible(false);
        setIcon();
        setResizable(false);
        
        bordaPadrao = javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 1, true);
        bordaErro = javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 1, true);

        txtLogin.setBorder(bordaPadrao);
        txtSenha.setBorder(bordaPadrao);

        Color corCampo = new Color(0, 0, 0, 0);
        txtLogin.setBackground(corCampo);
        txtLogin.setOpaque(false);
        txtLogin.setCaretColor(Color.WHITE);

        txtSenha.setBackground(corCampo);
        txtSenha.setOpaque(false);
        txtSenha.setCaretColor(Color.WHITE);
        txtSenha.setForeground(Color.WHITE);

        addPlaceholder(txtLogin, "  email@exemplo.com");

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

        btnTermosServico = new javax.swing.JButton();
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
        jilbCreditos = new javax.swing.JLabel();
        jilbAindaNaoTemConta = new javax.swing.JLabel();
        jilbRegistreSe = new javax.swing.JLabel();
        btnResgistrar = new javax.swing.JButton();
        jlibLogo = new javax.swing.JLabel();
        jilbLogo2 = new javax.swing.JLabel();
        jilbTitulo = new javax.swing.JLabel();
        jilbTexto = new javax.swing.JLabel();
        jilbTexto2 = new javax.swing.JLabel();
        jilbLinha = new javax.swing.JLabel();
        jilbLinha3 = new javax.swing.JLabel();
        jilbTexto3 = new javax.swing.JLabel();
        jilbGlobo = new javax.swing.JLabel();
        cmbLinguagens = new javax.swing.JComboBox<>();
        jilbLinha2 = new javax.swing.JLabel();
        jilbGoogleIcon = new javax.swing.JLabel();
        btnGoogle = new javax.swing.JButton();
        jlibBlueSquad = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CT Contab Manager");
        getContentPane().setLayout(null);

        btnTermosServico.setBackground(new java.awt.Color(30, 30, 30));
        btnTermosServico.setContentAreaFilled(false);
        btnTermosServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermosServicoActionPerformed(evt);
            }
        });
        getContentPane().add(btnTermosServico);
        btnTermosServico.setBounds(120, 680, 190, 20);

        jlibErroLogin.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jlibErroLogin.setForeground(new java.awt.Color(255, 0, 0));
        jlibErroLogin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlibErroLogin.setText("Seu e-mail ou senha estão incorretos.");
        getContentPane().add(jlibErroLogin);
        jlibErroLogin.setBounds(190, 300, 220, 30);

        jlibEsqueceuASenha.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jlibEsqueceuASenha.setForeground(new java.awt.Color(16, 168, 105));
        jlibEsqueceuASenha.setText("Esqueceu a senha?");
        getContentPane().add(jlibEsqueceuASenha);
        jlibEsqueceuASenha.setBounds(300, 450, 130, 30);

        btnEsqueceuSenha.setBackground(new java.awt.Color(30, 30, 30));
        btnEsqueceuSenha.setContentAreaFilled(false);
        btnEsqueceuSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsqueceuSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(btnEsqueceuSenha);
        btnEsqueceuSenha.setBounds(210, 500, 110, 20);

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
        chbMostrarSenha.setBounds(370, 410, 30, 40);

        txtLogin.setBackground(new java.awt.Color(4, 21, 57));
        txtLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(115, 115, 115));
        txtLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
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
        txtLogin.setBounds(40, 330, 370, 40);
        addPlaceholder(txtLogin, "  email@exemplo.com");

        btnLogin.setBackground(new java.awt.Color(17, 168, 100));
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
        btnLogin.setBounds(40, 500, 370, 40);

        txtSenha.setBackground(new java.awt.Color(4, 21, 57));
        txtSenha.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtSenha.setForeground(new java.awt.Color(115, 115, 115));
        txtSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84)));
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
        txtSenha.setBounds(40, 410, 370, 40);

        jilbEmailOuUsuario.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jilbEmailOuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jilbEmailOuUsuario.setText("Email");
        getContentPane().add(jilbEmailOuUsuario);
        jilbEmailOuUsuario.setBounds(40, 310, 190, 20);

        jilbSenha.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jilbSenha.setForeground(new java.awt.Color(255, 255, 255));
        jilbSenha.setText("Senha");
        getContentPane().add(jilbSenha);
        jilbSenha.setBounds(40, 390, 170, 20);

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
        chbLembre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbLembreActionPerformed(evt);
            }
        });
        getContentPane().add(chbLembre);
        chbLembre.setBounds(40, 450, 100, 30);

        jilbCreditos.setBackground(new java.awt.Color(255, 255, 255));
        jilbCreditos.setForeground(new java.awt.Color(255, 255, 255));
        jilbCreditos.setText("© 2025 CT Contab. Todos os direitos reservados.");
        getContentPane().add(jilbCreditos);
        jilbCreditos.setBounds(20, 680, 350, 20);

        jilbAindaNaoTemConta.setBackground(new java.awt.Color(255, 255, 255));
        jilbAindaNaoTemConta.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jilbAindaNaoTemConta.setForeground(new java.awt.Color(255, 255, 255));
        jilbAindaNaoTemConta.setText("Não registrado ainda?");
        getContentPane().add(jilbAindaNaoTemConta);
        jilbAindaNaoTemConta.setBounds(40, 540, 140, 20);

        jilbRegistreSe.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jilbRegistreSe.setForeground(new java.awt.Color(16, 168, 105));
        jilbRegistreSe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jilbRegistreSe.setText("Crie uma conta");
        getContentPane().add(jilbRegistreSe);
        jilbRegistreSe.setBounds(160, 540, 100, 20);

        btnResgistrar.setBackground(new java.awt.Color(30, 30, 30));
        btnResgistrar.setContentAreaFilled(false);
        btnResgistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResgistrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnResgistrar);
        btnResgistrar.setBounds(170, 540, 80, 20);

        jlibLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Text Icon.png"))); // NOI18N
        jlibLogo.setPreferredSize(new java.awt.Dimension(40, 59));
        getContentPane().add(jlibLogo);
        jlibLogo.setBounds(60, 10, 180, 60);

        jilbLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Icon_1.png"))); // NOI18N
        getContentPane().add(jilbLogo2);
        jilbLogo2.setBounds(20, 10, 50, 50);

        jilbTitulo.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jilbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jilbTitulo.setText("Login");
        jilbTitulo.setPreferredSize(new java.awt.Dimension(100, 100));
        getContentPane().add(jilbTitulo);
        jilbTitulo.setBounds(40, 100, 100, 50);

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

        jilbLinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line 1.png"))); // NOI18N
        jilbLinha.setText("jLabel1");
        getContentPane().add(jilbLinha);
        jilbLinha.setBounds(0, 0, 450, 160);

        jilbLinha3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line 1.png"))); // NOI18N
        jilbLinha3.setText("jLabel6");
        getContentPane().add(jilbLinha3);
        jilbLinha3.setBounds(40, 270, 80, 16);

        jilbTexto3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jilbTexto3.setForeground(new java.awt.Color(255, 255, 255));
        jilbTexto3.setText("ou faça login com o email");
        getContentPane().add(jilbTexto3);
        jilbTexto3.setBounds(130, 260, 156, 30);

        jilbGlobo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Globo.png"))); // NOI18N
        getContentPane().add(jilbGlobo);
        jilbGlobo.setBounds(310, 20, 30, 40);

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
        cmbLinguagens.setBounds(310, 20, 120, 40);

        jilbLinha2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line 1.png"))); // NOI18N
        jilbLinha2.setText("jLabel8");
        getContentPane().add(jilbLinha2);
        jilbLinha2.setBounds(300, 270, 90, 16);

        jilbGoogleIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/image 1.png"))); // NOI18N
        getContentPane().add(jilbGoogleIcon);
        jilbGoogleIcon.setBounds(140, 210, 60, 40);

        btnGoogle.setBackground(new Color(0, 0, 0, 0));
        btnGoogle.setForeground(new java.awt.Color(255, 255, 255));
        btnGoogle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btnGoogle.setText("Sing in with Google");
        btnGoogle.setFocusPainted(false);
        btnGoogle.setContentAreaFilled(false);
        btnGoogle.setBorderPainted(true);
        btnGoogle.setOpaque(false);
        btnGoogle.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1, true));
        btnGoogle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoogle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoogleActionPerformed(evt);
            }
        });
        getContentPane().add(btnGoogle);
        btnGoogle.setBounds(40, 210, 370, 40);

        jlibBlueSquad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Dashboard Overlay.png"))); // NOI18N
        getContentPane().add(jlibBlueSquad);
        jlibBlueSquad.setBounds(0, 0, 450, 750);

        Background.setForeground(new java.awt.Color(255, 255, 255));
        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Background Login.png"))); // NOI18N
        Background.setText("jLabel3");
        getContentPane().add(Background);
        Background.setBounds(20, 0, 1410, 750);

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            Usuario usuarioLogado = CTCONTAB.fazerLoginU(txtLogin.getText(), new String(txtSenha.getPassword()));

            if (usuarioLogado != null) {
                
                txtLogin.setBorder(bordaPadrao);
                txtSenha.setBorder(bordaPadrao);
                
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
        jlibErroLogin.setText("Seu e-mail ou senha estão incorretos.");
        jlibErroLogin.setForeground(Color.RED);
        jlibErroLogin.setVisible(true);

        txtLogin.setBorder(bordaErro);
        txtSenha.setBorder(bordaErro);

        new Thread(() -> {
            try {
                Thread.sleep(2000);

            for (int i = 0; i <= 20; i++) {
                int r = 255 - (int) ((255 - 84) * (i / 20.0));
                int g = 0 + (int) ((84 - 0) * (i / 20.0));
                int b = 0 + (int) ((84 - 0) * (i / 20.0));
                Color corSuave = new Color(r, g, b);

                javax.swing.SwingUtilities.invokeLater(() -> {
                    txtLogin.setBorder(BorderFactory.createLineBorder(corSuave, 1, true));
                    txtSenha.setBorder(BorderFactory.createLineBorder(corSuave, 1, true));
                });

                Thread.sleep(30);
            }

            javax.swing.SwingUtilities.invokeLater(() -> {
                txtLogin.setBorder(bordaPadrao);
                txtSenha.setBorder(bordaPadrao);
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

    private void btnGoogleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoogleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGoogleActionPerformed

    private void cmbLinguagensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLinguagensActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLinguagensActionPerformed

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
    private javax.swing.JButton btnEsqueceuSenha;
    private javax.swing.JButton btnGoogle;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnResgistrar;
    private javax.swing.JButton btnTermosServico;
    private javax.swing.JCheckBox chbLembre;
    private javax.swing.JCheckBox chbMostrarSenha;
    private javax.swing.JComboBox<String> cmbLinguagens;
    private javax.swing.JLabel jilbAindaNaoTemConta;
    private javax.swing.JLabel jilbCreditos;
    private javax.swing.JLabel jilbEmailOuUsuario;
    private javax.swing.JLabel jilbGlobo;
    private javax.swing.JLabel jilbGoogleIcon;
    private javax.swing.JLabel jilbLinha;
    private javax.swing.JLabel jilbLinha2;
    private javax.swing.JLabel jilbLinha3;
    private javax.swing.JLabel jilbLogo2;
    private javax.swing.JLabel jilbRegistreSe;
    private javax.swing.JLabel jilbSenha;
    private javax.swing.JLabel jilbTexto;
    private javax.swing.JLabel jilbTexto2;
    private javax.swing.JLabel jilbTexto3;
    private javax.swing.JLabel jilbTitulo;
    private javax.swing.JLabel jlibBlueSquad;
    private javax.swing.JLabel jlibErroLogin;
    private javax.swing.JLabel jlibEsqueceuASenha;
    private javax.swing.JLabel jlibLogo;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}