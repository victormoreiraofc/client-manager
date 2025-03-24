package screen;

import Data.IconUtil;
import Data.CTCONTAB;
import Data.PermissaoUtil;
import Data.Usuario;
import Screen.MensagemUtil;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TelaConfiguracao extends javax.swing.JFrame {

    private Usuario usuarioLogado;

    public TelaConfiguracao(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
        carregarImagemUsuario(usuario);
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
        setIcon();
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

        btnHome = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnAdministracao = new javax.swing.JButton();
        JPanelTelaAcesso3 = new javax.swing.JPanel();
        lblContabilidade = new javax.swing.JLabel();
        lblCTCONTAB = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtNovaSenha = new javax.swing.JTextField();
        txtSeuEmail = new javax.swing.JTextField();
        txtNovoEmail = new javax.swing.JTextField();
        txtSenhaAtual = new javax.swing.JTextField();
        lblImagem = new javax.swing.JLabel();
        lblDataDeCadastro = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtNomeAtual = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtNomeNovo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlibLogo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configurações - CT CONTAB");
        getContentPane().setLayout(null);

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home-menu.png"))); // NOI18N
        btnHome.setContentAreaFilled(false);
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnHome);
        btnHome.setBounds(0, 120, 80, 50);

        btnCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/calendar-menu.png"))); // NOI18N
        btnCalendario.setContentAreaFilled(false);
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalendario);
        btnCalendario.setBounds(0, 190, 80, 50);

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/client-menu.png"))); // NOI18N
        btnClientes.setContentAreaFilled(false);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnClientes);
        btnClientes.setBounds(5, 260, 70, 50);

        btnRelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/relatory-menu.png"))); // NOI18N
        btnRelatorios.setContentAreaFilled(false);
        btnRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosActionPerformed(evt);
            }
        });
        getContentPane().add(btnRelatorios);
        btnRelatorios.setBounds(0, 330, 80, 50);

        btnTarefas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/task-menu.png"))); // NOI18N
        btnTarefas.setContentAreaFilled(false);
        btnTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarefasActionPerformed(evt);
            }
        });
        getContentPane().add(btnTarefas);
        btnTarefas.setBounds(0, 400, 80, 50);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/configuration-menu.png"))); // NOI18N
        btnConfiguracoes.setContentAreaFilled(false);
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfiguracoes);
        btnConfiguracoes.setBounds(0, 470, 80, 50);

        btnAdministracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin-menu.png"))); // NOI18N
        btnAdministracao.setContentAreaFilled(false);
        btnAdministracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministracaoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdministracao);
        btnAdministracao.setBounds(2, 530, 80, 70);

        JPanelTelaAcesso3.setBackground(new java.awt.Color(194, 166, 40));
        getContentPane().add(JPanelTelaAcesso3);
        JPanelTelaAcesso3.setBounds(0, 460, 80, 70);

        lblContabilidade.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        lblContabilidade.setForeground(new java.awt.Color(153, 153, 0));
        lblContabilidade.setText("Contabilidade & Consultoria");
        getContentPane().add(lblContabilidade);
        lblContabilidade.setBounds(90, 7, 205, 80);

        lblCTCONTAB.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCTCONTAB.setForeground(new java.awt.Color(200, 200, 200));
        lblCTCONTAB.setText("CT CONTAB");
        getContentPane().add(lblCTCONTAB);
        lblCTCONTAB.setBounds(90, 7, 190, 40);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minhatura-de-perfil.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1210, 15, 50, 50);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alert-bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1160, 10, 60, 60);

        jPanel1.setBackground(new java.awt.Color(5, 27, 74));
        jPanel1.setLayout(null);

        jButton2.setBackground(new java.awt.Color(84, 84, 84, 0));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(10, 460, 350, 30);

        jLabel4.setBackground(new java.awt.Color(153, 153, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(205, 168, 16));
        jLabel4.setText("Configurações");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 10, 250, 40);

        jSeparator1.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator1.setForeground(new java.awt.Color(115, 115, 115));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 60, 1140, 30);

        jButton1.setBackground(new java.awt.Color(84, 84, 84, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(970, 250, 140, 30);

        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Editar o email da conta não levará a perca nem a alteração de nenhum dado a não ser o email necessário para fazer o login no sistema.");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 100, 730, 16);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Seu Novo E-mail");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(310, 120, 140, 30);

        btnLogin.setBackground(new java.awt.Color(194, 166, 40));
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Atualizar");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin);
        btnLogin.setBounds(960, 490, 160, 40);

        txtNovaSenha.setBackground(new java.awt.Color(4, 21, 57));
        txtNovaSenha.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtNovaSenha.setForeground(new java.awt.Color(115, 115, 115));
        txtNovaSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtNovaSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNovaSenhaActionPerformed(evt);
            }
        });
        jPanel1.add(txtNovaSenha);
        txtNovaSenha.setBounds(310, 270, 280, 35);

        txtSeuEmail.setBackground(new java.awt.Color(4, 21, 57));
        txtSeuEmail.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtSeuEmail.setForeground(new java.awt.Color(115, 115, 115));
        txtSeuEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtSeuEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSeuEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtSeuEmail);
        txtSeuEmail.setBounds(20, 150, 280, 35);
        addPlaceholder(txtSeuEmail, "  Digite seu e-mail atual");
        addPlaceholder(txtNovoEmail, "  Digite seu novo e-mail");
        addPlaceholder(txtSenhaAtual, "  Digite sua senha atual");
        addPlaceholder(txtNovaSenha, "  Digite sua nova senha");
        addPlaceholder(txtNomeAtual, "  Digite seu nome de usuário atual");
        addPlaceholder(txtNomeNovo, "  Digite seu novo nome de usuário");

        txtNovoEmail.setBackground(new java.awt.Color(4, 21, 57));
        txtNovoEmail.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtNovoEmail.setForeground(new java.awt.Color(115, 115, 115));
        txtNovoEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtNovoEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNovoEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtNovoEmail);
        txtNovoEmail.setBounds(310, 150, 280, 35);

        txtSenhaAtual.setBackground(new java.awt.Color(4, 21, 57));
        txtSenhaAtual.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtSenhaAtual.setForeground(new java.awt.Color(115, 115, 115));
        txtSenhaAtual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtSenhaAtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaAtualActionPerformed(evt);
            }
        });
        jPanel1.add(txtSenhaAtual);
        txtSenhaAtual.setBounds(20, 270, 280, 35);

        lblImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/perfil-de-usuario.png"))); // NOI18N
        jPanel1.add(lblImagem);
        lblImagem.setBounds(960, 80, 160, 170);

        lblDataDeCadastro.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro.setText("<html><u>Alterar imagem</u></html>");
        lblDataDeCadastro.setToolTipText("");
        jPanel1.add(lblDataDeCadastro);
        lblDataDeCadastro.setBounds(960, 250, 160, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Seu Atual E-mail");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 120, 110, 30);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Editar o email da conta");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(20, 80, 170, 20);

        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("Editar a senha da conta não levará a perca nem a alteração de nenhum dado a não ser a senha necessária para fazer o login no sistema.");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(20, 220, 730, 16);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(82, 113, 255));
        jLabel12.setText("Exibir configurações do sistema operacional do CT CONTAB");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(20, 460, 440, 20);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Sua Senha Atual");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(20, 240, 110, 30);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Sua Nova Senha");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(310, 240, 140, 30);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Editar o nome de usuário da conta");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(20, 320, 270, 20);

        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText("Editar o usuário da conta não levará a perca nem a alteração de nenhum dado a não ser o usuário necessário para fazer o login no sistema.");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(20, 340, 750, 16);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Nome de Usuário atual");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(20, 360, 180, 30);

        txtNomeAtual.setBackground(new java.awt.Color(4, 21, 57));
        txtNomeAtual.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtNomeAtual.setForeground(new java.awt.Color(115, 115, 115));
        txtNomeAtual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtNomeAtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeAtualActionPerformed(evt);
            }
        });
        jPanel1.add(txtNomeAtual);
        txtNomeAtual.setBounds(20, 390, 280, 35);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Novo Nome de Usuário");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(310, 360, 140, 30);

        txtNomeNovo.setBackground(new java.awt.Color(4, 21, 57));
        txtNomeNovo.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtNomeNovo.setForeground(new java.awt.Color(115, 115, 115));
        txtNomeNovo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtNomeNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeNovoActionPerformed(evt);
            }
        });
        jPanel1.add(txtNomeNovo);
        txtNomeNovo.setBounds(310, 390, 280, 35);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Editar a senha da conta");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(20, 200, 170, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(110, 100, 1140, 540);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 640, 80, 60);

        jlibLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jlibLogo2);
        jlibLogo2.setBounds(4, 10, 60, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -50, 80, 750);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-contabil.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1280, 711);

        setSize(new java.awt.Dimension(1294, 708));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String emailAtual = txtSeuEmail.getText().trim();
        String emailNovo = txtNovoEmail.getText().trim();
        String senhaNova = txtNovaSenha.getText().trim();
        String nomeAtual = txtNomeAtual.getText().trim();
        String nomeNovo = txtNomeNovo.getText().trim();

        boolean alterouAlgo = false;

        try {
            if (!emailNovo.isEmpty() && !emailNovo.equals(emailAtual)) {
                CTCONTAB.atualizarEmailUsuario(emailAtual, emailNovo);
                usuarioLogado.setEmail(emailNovo);
                alterouAlgo = true;
            }

            if (!nomeNovo.isEmpty() && !nomeNovo.equals(nomeAtual)) {
                if (!nomeNovo.matches("^[a-zA-Z]+$")) {
                    MensagemUtil.exibirErro("O nome de usuário só pode conter letras!");
                    return;
                }
                CTCONTAB.atualizarNomeUsuario(nomeAtual, nomeNovo);
                usuarioLogado.setUsuario(nomeNovo);
                alterouAlgo = true;
            }

            if (!senhaNova.isEmpty()) {
                CTCONTAB.atualizarSenhaUsuario(emailNovo.isEmpty() ? emailAtual : emailNovo, senhaNova);
                alterouAlgo = true;
            }

            if (alterouAlgo) {
                MensagemUtil.exibirSucesso("Dados atualizados com sucesso!");
            } else {
                MensagemUtil.exibirErro("Nenhum dado foi alterado!");
            }

        } catch (SQLException e) {
            if (e.getMessage().contains("O e-mail já está sendo usado")) {
                MensagemUtil.exibirErro("Este e-mail já está cadastrado. Escolha outro.");
            } else if (e.getMessage().contains("O nome de usuário já está em uso")) {
                MensagemUtil.exibirErro("Este nome de usuário já está sendo usado. Escolha outro.");
            } else {
                MensagemUtil.exibirErro("" + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            MensagemUtil.exibirErro("Erro ao atualizar os dados.");
        }
    }

    private void txtNovaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNovaSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNovaSenhaActionPerformed

    private void txtSeuEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSeuEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeuEmailActionPerformed

    private void txtNovoEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNovoEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNovoEmailActionPerformed

    private void txtSenhaAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaAtualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaAtualActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser jfile = new JFileChooser();
        jfile.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "png");
        jfile.addChoosableFileFilter(filter);

        int result = jfile.showOpenDialog(null);
        File selectedFile = jfile.getSelectedFile();
        String filename = selectedFile.getName();
        if (filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.endsWith(".png") || filename.endsWith(".PNG")) {
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = selectedFile.getAbsolutePath();
                    ImageIcon myImage = new ImageIcon(path);
                    Image img = myImage.getImage();
                    Image newImage = img.getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImage);
                    lblImagem.setIcon(image);
                    FileInputStream fis = new FileInputStream(selectedFile);
                    CTCONTAB.registrarImagemUsuario(fis, usuarioLogado);

                    fis.close();
                    // JOptionPane.showMessageDialog(null, "Imagem salva com sucesso!");
                    MensagemUtil.exibirSucesso("Imagem salva com sucesso!");
                } catch (Exception e) {
                    e.printStackTrace();
                    // JOptionPane.showMessageDialog(null, "Erro ao salvar imagem: " + e.getMessage());
                    MensagemUtil.exibirErro("Erro ao salvar imagem!");
                }

            } else {
                // JOptionPane.showMessageDialog(null, "Erro, selecione o arquivo compatível (Png ou Jpg)");
                MensagemUtil.exibirErro("Erro, selecione o arquivo compatível (PNG ou JPG)");
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void txtNomeAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeAtualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeAtualActionPerformed

    private void txtNomeNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeNovoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeNovoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new TelaSOP(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void carregarImagemUsuario(Usuario usuario) {
        try {
            byte[] imagemByte = CTCONTAB.getImagemUsuario(usuario.getId());

            if (imagemByte != null && imagemByte.length > 0) {
                ImageIcon imagemIcon = new ImageIcon(imagemByte);
                Image img = imagemIcon.getImage();
                Image newImage = img.getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH);
                lblImagem.setIcon(new ImageIcon(newImage));
            } else {
                carregarIconePadrao();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            carregarIconePadrao();
        }
    }

    private void carregarIconePadrao() {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/perfil-de-usuario.png"));
            Image img = icon.getImage();
            Image newImage = img.getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH);

            lblImagem.setIcon(new ImageIcon(newImage));
        } catch (Exception e) {
            e.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Erro ao carregar ícone padrão: " + e.getMessage());
            MensagemUtil.exibirErro("Erro ao carregar ícone padrão!");
        }
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
            java.util.logging.Logger.getLogger(TelaConfiguracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaConfiguracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaConfiguracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaConfiguracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel JPanelTelaAcesso3;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlibLogo2;
    private javax.swing.JLabel lblCTCONTAB;
    private javax.swing.JLabel lblContabilidade;
    private javax.swing.JLabel lblDataDeCadastro;
    private javax.swing.JLabel lblImagem;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JTextField txtNomeAtual;
    private javax.swing.JTextField txtNomeNovo;
    private javax.swing.JTextField txtNovaSenha;
    private javax.swing.JTextField txtNovoEmail;
    private javax.swing.JTextField txtSenhaAtual;
    private javax.swing.JTextField txtSeuEmail;
    // End of variables declaration//GEN-END:variables
}
