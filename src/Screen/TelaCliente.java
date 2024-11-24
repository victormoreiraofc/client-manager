package Screen;

import Data.CTCONTAB;
import Data.Usuario;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class TelaCliente extends javax.swing.JFrame {

    private Usuario usuarioLogado;

    public TelaCliente(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
    }

    public TelaCliente() {
        initComponents();
    }

    private void salvarEventoNoBanco() {
        try {
            ZonedDateTime dataBrasilia = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataCadastro = dataBrasilia.format(formatter);

            String nome = txtNome.getText();
            String tipoPessoa = txtTipoPessoa.getSelectedItem().toString();
            String email = txtEmail.getText();
            String servico = txtServico.getText();
            String situacaoServico = txtSituacaoServico.getSelectedItem().toString();
            String celular = txtCelular.getText();
            String telefone = txtTelefone.getText();
            String observacoes = txtObservacoes.getText();

            if (usuarioLogado != null) {
                String nomeUsuario = usuarioLogado.getUsuario();
                CTCONTAB.registrarCliente(dataCadastro, nome, tipoPessoa, email, servico, situacaoServico, celular, telefone, observacoes, nomeUsuario);
                JOptionPane.showMessageDialog(this, "Evento salvo com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum usuário logado. Não foi possível salvar o evento.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar evento: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNotificacoes = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtSituacaoServico = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtObservacoes = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtServico = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        lblDataDeCadastro = new javax.swing.JLabel();
        lblDataDeCadastro1 = new javax.swing.JLabel();
        lblDataDeCadastro2 = new javax.swing.JLabel();
        lblDataDeCadastro3 = new javax.swing.JLabel();
        txtTipoPessoa = new javax.swing.JComboBox<>();
        btnHome = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnAdministracao = new javax.swing.JButton();
        JPanelTelaAcesso = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jlibLogo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        btnNotificacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alert-bell.png"))); // NOI18N
        btnNotificacoes.setContentAreaFilled(false);
        getContentPane().add(btnNotificacoes);
        btnNotificacoes.setBounds(1160, 10, 60, 60);

        jPanel1.setBackground(new java.awt.Color(5, 27, 74));
        jPanel1.setLayout(null);

        txtSituacaoServico.setBackground(new java.awt.Color(102, 102, 102));
        txtSituacaoServico.setForeground(new java.awt.Color(255, 255, 255));
        txtSituacaoServico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendente", "Em andamento", "Concluido" }));
        txtSituacaoServico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        jPanel1.add(txtSituacaoServico);
        txtSituacaoServico.setBounds(260, 420, 220, 35);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save-icon.png"))); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(1050, 10, 40, 40);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete-icon.png"))); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(1010, 10, 40, 40);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/perfil-de-usuario.png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 70, 160, 170);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fechar.png"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(1090, 10, 40, 40);

        jButton3.setBackground(new java.awt.Color(84, 84, 84));
        jPanel1.add(jButton3);
        jButton3.setBounds(1010, 10, 40, 40);

        jButton2.setBackground(new java.awt.Color(126, 217, 87));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(1050, 10, 40, 40);

        jLabel4.setBackground(new java.awt.Color(153, 153, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(205, 168, 16));
        jLabel4.setText("Dados Pessoais");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 280, 250, 40);

        jSeparator1.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator1.setForeground(new java.awt.Color(115, 115, 115));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 60, 1140, 30);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Observações");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(760, 320, 100, 30);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nome");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(20, 320, 100, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tipo de Pessoa");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(20, 390, 120, 30);

        txtObservacoes.setBackground(new java.awt.Color(4, 21, 57));
        txtObservacoes.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtObservacoes.setForeground(new java.awt.Color(115, 115, 115));
        txtObservacoes.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtObservacoes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtObservacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacoesActionPerformed(evt);
            }
        });
        jPanel1.add(txtObservacoes);
        txtObservacoes.setBounds(760, 350, 350, 170);

        txtNome.setBackground(new java.awt.Color(4, 21, 57));
        txtNome.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtNome.setForeground(new java.awt.Color(115, 115, 115));
        txtNome.setText("  Nome e Sobrenome");
        txtNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        jPanel1.add(txtNome);
        txtNome.setBounds(20, 350, 220, 35);

        txtEmail.setBackground(new java.awt.Color(4, 21, 57));
        txtEmail.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(115, 115, 115));
        txtEmail.setText("  seuemail@gmail.com");
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmail);
        txtEmail.setBounds(20, 490, 220, 35);

        jButton1.setBackground(new java.awt.Color(239, 65, 54));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(1090, 10, 40, 40);

        jSeparator2.setBackground(new java.awt.Color(115, 115, 115));
        jSeparator2.setForeground(new java.awt.Color(115, 115, 115));
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(0, 270, 1140, 30);

        jLabel6.setBackground(new java.awt.Color(153, 153, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(205, 168, 16));
        jLabel6.setText("Nome Indefinido");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 10, 250, 40);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Celular");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(260, 460, 120, 30);

        txtCelular.setBackground(new java.awt.Color(4, 21, 57));
        txtCelular.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtCelular.setForeground(new java.awt.Color(115, 115, 115));
        txtCelular.setText("  (11) 912345678");
        txtCelular.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularActionPerformed(evt);
            }
        });
        jPanel1.add(txtCelular);
        txtCelular.setBounds(260, 490, 220, 35);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Situação do Serviço");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(260, 390, 200, 30);

        txtServico.setBackground(new java.awt.Color(4, 21, 57));
        txtServico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtServico.setForeground(new java.awt.Color(115, 115, 115));
        txtServico.setText("  Abertura de Empresa");
        txtServico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServicoActionPerformed(evt);
            }
        });
        jPanel1.add(txtServico);
        txtServico.setBounds(260, 350, 220, 35);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Serviço");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(260, 320, 100, 30);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Email");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(20, 460, 120, 30);

        txtTelefone.setBackground(new java.awt.Color(4, 21, 57));
        txtTelefone.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTelefone.setForeground(new java.awt.Color(115, 115, 115));
        txtTelefone.setText("  (11) 23456789");
        txtTelefone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        txtTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneActionPerformed(evt);
            }
        });
        jPanel1.add(txtTelefone);
        txtTelefone.setBounds(500, 350, 220, 35);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Telefone");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(500, 320, 100, 30);

        lblDataDeCadastro.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblDataDeCadastro.setForeground(new java.awt.Color(186, 186, 186));
        lblDataDeCadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataDeCadastro.setText("Alterar imagem");
        lblDataDeCadastro.setToolTipText("");
        jPanel1.add(lblDataDeCadastro);
        lblDataDeCadastro.setBounds(20, 230, 160, 40);

        lblDataDeCadastro1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro1.setForeground(new java.awt.Color(255, 255, 255));
        lblDataDeCadastro1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDataDeCadastro1.setText("Funcionario Cadastrante: ");
        lblDataDeCadastro1.setToolTipText("");
        jPanel1.add(lblDataDeCadastro1);
        lblDataDeCadastro1.setBounds(200, 170, 190, 30);

        lblDataDeCadastro2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro2.setForeground(new java.awt.Color(255, 255, 255));
        lblDataDeCadastro2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDataDeCadastro2.setText("Codigo:");
        lblDataDeCadastro2.setToolTipText("");
        jPanel1.add(lblDataDeCadastro2);
        lblDataDeCadastro2.setBounds(200, 110, 70, 30);

        lblDataDeCadastro3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDataDeCadastro3.setForeground(new java.awt.Color(255, 255, 255));
        lblDataDeCadastro3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDataDeCadastro3.setText("Data de Cadastro: ");
        lblDataDeCadastro3.setToolTipText("");
        jPanel1.add(lblDataDeCadastro3);
        lblDataDeCadastro3.setBounds(200, 140, 140, 30);

        txtTipoPessoa.setBackground(new java.awt.Color(102, 102, 102));
        txtTipoPessoa.setForeground(new java.awt.Color(255, 255, 255));
        txtTipoPessoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fisica", "Juridica", "NI" }));
        txtTipoPessoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 84, 84), 3));
        jPanel1.add(txtTipoPessoa);
        txtTipoPessoa.setBounds(20, 420, 220, 35);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(110, 100, 1140, 540);

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
        JPanelTelaAcesso.setBounds(0, 250, 80, 70);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 640, 80, 60);

        jlibLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jlibLogo2);
        jlibLogo2.setBounds(4, 10, 60, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -50, 80, 750);

        lblUserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minhatura-de-perfil.png"))); // NOI18N
        getContentPane().add(lblUserIcon);
        lblUserIcon.setBounds(1210, 15, 50, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo-semfundo.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 290, 70);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-contabil.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1280, 711);

        setSize(new java.awt.Dimension(1294, 708));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        new TelaMenu(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        new TelaConfiguracao(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConfiguracoesActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        new TelaClienteTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnAdministracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministracaoActionPerformed
        new TelaAdminTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAdministracaoActionPerformed

    private void txtObservacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacoesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacoesActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        new TelaEventoTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCalendarioActionPerformed

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosActionPerformed
        new TelaRelatorioTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRelatoriosActionPerformed

    private void btnTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarefasActionPerformed
        new TelaTarefaTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTarefasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new TelaClienteTable(usuarioLogado).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularActionPerformed

    private void txtServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServicoActionPerformed

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        salvarEventoNoBanco();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel JPanelTelaAcesso;
    private javax.swing.JButton btnAdministracao;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnConfiguracoes;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnTarefas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jlibLogo2;
    private javax.swing.JLabel lblDataDeCadastro;
    private javax.swing.JLabel lblDataDeCadastro1;
    private javax.swing.JLabel lblDataDeCadastro2;
    private javax.swing.JLabel lblDataDeCadastro3;
    private javax.swing.JLabel lblUserIcon;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtObservacoes;
    private javax.swing.JTextField txtServico;
    private javax.swing.JComboBox<String> txtSituacaoServico;
    private javax.swing.JTextField txtTelefone;
    private javax.swing.JComboBox<String> txtTipoPessoa;
    // End of variables declaration//GEN-END:variables
}
