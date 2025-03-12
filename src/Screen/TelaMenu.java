package screen;

import Data.CTCONTAB;
import Data.Cliente;
import Data.IconUtil;
import Data.Usuario;
import Data.PermissaoUtil;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

public class TelaMenu extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    private Cliente cliente;
    private Font fonteOriginal;

    public TelaMenu(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
        salvarFonteOriginal();
        exibirMensagemCarregando();
        carregarDadosSimultaneamente();
        PermissaoUtil.aplicarPermissao(usuarioLogado, btnAdministracao);
        IconUtil.setIcon(usuarioLogado, lblUserIcon);
        setIcon();
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo-icon.png")));
    }

    private void salvarFonteOriginal() {
        fonteOriginal = jlibVariavel1.getFont();
    }

    private void alterarFonteTemporaria() {
        Font fonteCarregando = fonteOriginal.deriveFont(Font.BOLD, 18);
        jlibVariavel1.setFont(fonteCarregando);
        jlibVariavel2.setFont(fonteCarregando);
        jlibVariavel3.setFont(fonteCarregando);
        jlibVariavel4.setFont(fonteCarregando);
        jlibVariavel5.setFont(fonteCarregando);
        jlibVariavel.setFont(fonteCarregando);
    }

    private void restaurarFonteOriginal() {
        jlibVariavel1.setFont(fonteOriginal);
        jlibVariavel2.setFont(fonteOriginal);
        jlibVariavel3.setFont(fonteOriginal);
        jlibVariavel4.setFont(fonteOriginal);
        jlibVariavel5.setFont(fonteOriginal);
        jlibVariavel.setFont(fonteOriginal);
    }

    private void exibirMensagemCarregando() {
        alterarFonteTemporaria();
        jlibVariavel1.setText("Carregando...");
        jlibVariavel2.setText("Carregando...");
        jlibVariavel3.setText("Carregando...");
        jlibVariavel4.setText("Carregando...");
        jlibVariavel5.setText("Carregando...");
        jlibVariavel.setText("Carregando...");
    }

    private void carregarDadosSimultaneamente() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                atualizarTotalClientes();
                return null;
            }
        }.execute();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                tarefaPendentes();
                return null;
            }
        }.execute();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                tarefasNaoRealizadas();
                return null;
            }
        }.execute();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                tarefasRealizadas();
                return null;
            }
        }.execute();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                totalRelatorios();
                return null;
            }
        }.execute();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                novosclientesdomes();
                return null;
            }
        }.execute();
    }

    private void atualizarTotalClientes() {
        try {
            int total = CTCONTAB.clienteTotalRegis();
            jlibVariavel1.setText(String.valueOf(total));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel1.setText("Erro");
        }
    }

    private void tarefaPendentes() {
        try {
            int pendentes = CTCONTAB.tarefaPendentes();
            jlibVariavel2.setText(String.valueOf(pendentes));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel2.setText("Erro");
        }
    }

    private void tarefasNaoRealizadas() {
        try {
            int andamento = CTCONTAB.serviçosNaoRealizados();
            jlibVariavel4.setText(String.valueOf(andamento));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel4.setText("Erro");
        }
    }

    private void tarefasRealizadas() {
        try {
            int concluido = CTCONTAB.serviçosRealizados();
            jlibVariavel5.setText(String.valueOf(concluido));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel5.setText("Erro");
        }
    }

    private void totalRelatorios() {
        try {
            int total = CTCONTAB.totalRelatorios();
            jlibVariavel3.setText(String.valueOf(total));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel3.setText("Erro");
        }
    }

    private void novosclientesdomes() {
        try {
            int total = CTCONTAB.novosclientesdomes();
            jlibVariavel.setText(String.valueOf(total));
            restaurarFonteOriginal();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TelaMenu.class.getName()).log(Level.SEVERE, null, ex);
            jlibVariavel.setText("Erro");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblContabilidade = new javax.swing.JLabel();
        lblCTCONTAB = new javax.swing.JLabel();
        lblUserIcon = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        JPanelTelaAcesso = new javax.swing.JPanel();
        btnClientes = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        btnTarefas = new javax.swing.JButton();
        btnConfiguracoes = new javax.swing.JButton();
        btnAdministracao = new javax.swing.JButton();
        jlibCadastrarNovo = new javax.swing.JLabel();
        jlibSeta = new javax.swing.JLabel();
        btnCadastrar = new javax.swing.JButton();
        jlibVariavel2 = new javax.swing.JLabel();
        jlibVariavel3 = new javax.swing.JLabel();
        jlibVariavel4 = new javax.swing.JLabel();
        jlibVariavel5 = new javax.swing.JLabel();
        jlibVariavel1 = new javax.swing.JLabel();
        jlibVariavel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlibLogo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblNovosClientesMes = new javax.swing.JLabel();
        lblTotalClientesRegistrados = new javax.swing.JLabel();
        lblTarefasPendentes = new javax.swing.JLabel();
        lblTotalVendas = new javax.swing.JLabel();
        lblServicosNaoRealizados = new javax.swing.JLabel();
        lblServicosFinalizados = new javax.swing.JLabel();
        jPanelBackground = new javax.swing.JPanel();
        jPanelBackground1 = new javax.swing.JPanel();
        jPanelBackground2 = new javax.swing.JPanel();
        jPanelBackground3 = new javax.swing.JPanel();
        jPanelBackground4 = new javax.swing.JPanel();
        jPanelBackground5 = new javax.swing.JPanel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home - CT CONTAB");
        getContentPane().setLayout(null);

        lblContabilidade.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        lblContabilidade.setForeground(new java.awt.Color(153, 153, 0));
        lblContabilidade.setText("Contabilidade & Consultoria");
        getContentPane().add(lblContabilidade);
        lblContabilidade.setBounds(90, 7, 205, 80);

        lblCTCONTAB.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCTCONTAB.setForeground(new java.awt.Color(204, 204, 204));
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

        JPanelTelaAcesso.setBackground(new java.awt.Color(194, 166, 40));
        getContentPane().add(JPanelTelaAcesso);
        JPanelTelaAcesso.setBounds(0, 90, 80, 70);

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

        jlibCadastrarNovo.setFont(new java.awt.Font("Segoe UI", 1, 23)); // NOI18N
        jlibCadastrarNovo.setForeground(new java.awt.Color(205, 168, 16));
        jlibCadastrarNovo.setText("CADASTRAR NOVOS CLIENTES");
        getContentPane().add(jlibCadastrarNovo);
        jlibCadastrarNovo.setBounds(890, 170, 340, 40);

        jlibSeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/seta-direita.png"))); // NOI18N
        getContentPane().add(jlibSeta);
        jlibSeta.setBounds(1190, 240, 50, 40);

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cadastrar-imagem.jpg"))); // NOI18N
        btnCadastrar.setContentAreaFilled(false);
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrar);
        btnCadastrar.setBounds(110, 170, 1130, 110);

        jlibVariavel2.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel2.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel2.setText("000");
        getContentPane().add(jlibVariavel2);
        jlibVariavel2.setBounds(680, 360, 280, 60);

        jlibVariavel3.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel3.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel3.setText("000");
        getContentPane().add(jlibVariavel3);
        jlibVariavel3.setBounds(970, 360, 270, 60);

        jlibVariavel4.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel4.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel4.setText("000");
        getContentPane().add(jlibVariavel4);
        jlibVariavel4.setBounds(390, 480, 280, 60);

        jlibVariavel5.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel5.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel5.setText("000");
        getContentPane().add(jlibVariavel5);
        jlibVariavel5.setBounds(680, 480, 280, 60);

        jlibVariavel1.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel1.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel1.setText("000");
        getContentPane().add(jlibVariavel1);
        jlibVariavel1.setBounds(390, 360, 280, 60);

        jlibVariavel.setFont(new java.awt.Font("Segoe UI", 1, 56)); // NOI18N
        jlibVariavel.setForeground(new java.awt.Color(255, 255, 255));
        jlibVariavel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlibVariavel.setText("000");
        getContentPane().add(jlibVariavel);
        jlibVariavel.setBounds(110, 360, 270, 60);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 640, 80, 60);

        jlibLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(jlibLogo2);
        jlibLogo2.setBounds(4, 10, 60, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/azul.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -50, 80, 750);

        lblNovosClientesMes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNovosClientesMes.setForeground(new java.awt.Color(186, 186, 186));
        lblNovosClientesMes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNovosClientesMes.setText("NOVOS CLIENTES NO MÊS");
        lblNovosClientesMes.setToolTipText("");
        getContentPane().add(lblNovosClientesMes);
        lblNovosClientesMes.setBounds(110, 330, 270, 20);

        lblTotalClientesRegistrados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalClientesRegistrados.setForeground(new java.awt.Color(186, 186, 186));
        lblTotalClientesRegistrados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalClientesRegistrados.setText("TOTAL DE CLIENTES REGISTRADOS");
        getContentPane().add(lblTotalClientesRegistrados);
        lblTotalClientesRegistrados.setBounds(390, 330, 280, 20);

        lblTarefasPendentes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTarefasPendentes.setForeground(new java.awt.Color(186, 186, 186));
        lblTarefasPendentes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTarefasPendentes.setText("TAREFAS PENDENTES");
        getContentPane().add(lblTarefasPendentes);
        lblTarefasPendentes.setBounds(680, 330, 280, 20);

        lblTotalVendas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalVendas.setForeground(new java.awt.Color(186, 186, 186));
        lblTotalVendas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalVendas.setText("TOTAL DE RELATÓRIOS");
        getContentPane().add(lblTotalVendas);
        lblTotalVendas.setBounds(970, 330, 270, 20);

        lblServicosNaoRealizados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblServicosNaoRealizados.setForeground(new java.awt.Color(186, 186, 186));
        lblServicosNaoRealizados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServicosNaoRealizados.setText("TAREFAS NÃO REALIZADAS");
        getContentPane().add(lblServicosNaoRealizados);
        lblServicosNaoRealizados.setBounds(390, 450, 280, 20);

        lblServicosFinalizados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblServicosFinalizados.setForeground(new java.awt.Color(186, 186, 186));
        lblServicosFinalizados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServicosFinalizados.setText("TAREFAS FINALIZADAS");
        getContentPane().add(lblServicosFinalizados);
        lblServicosFinalizados.setBounds(680, 450, 280, 20);

        jPanelBackground.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground);
        jPanelBackground.setBounds(110, 320, 270, 110);

        jPanelBackground1.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground1);
        jPanelBackground1.setBounds(390, 320, 280, 110);

        jPanelBackground2.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground2);
        jPanelBackground2.setBounds(680, 320, 280, 110);

        jPanelBackground3.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground3);
        jPanelBackground3.setBounds(970, 320, 270, 110);

        jPanelBackground4.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground4);
        jPanelBackground4.setBounds(390, 440, 280, 110);

        jPanelBackground5.setBackground(new java.awt.Color(255, 255, 255, 15));
        getContentPane().add(jPanelBackground5);
        jPanelBackground5.setBounds(680, 440, 280, 110);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background-contabil.png"))); // NOI18N
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1280, 711);

        setSize(new java.awt.Dimension(1294, 708));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        new TelaCliente(usuarioLogado, cliente).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCadastrarActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel JPanelTelaAcesso;
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
    private javax.swing.JPanel jPanelBackground;
    private javax.swing.JPanel jPanelBackground1;
    private javax.swing.JPanel jPanelBackground2;
    private javax.swing.JPanel jPanelBackground3;
    private javax.swing.JPanel jPanelBackground4;
    private javax.swing.JPanel jPanelBackground5;
    private javax.swing.JLabel jlibCadastrarNovo;
    private javax.swing.JLabel jlibLogo2;
    private javax.swing.JLabel jlibSeta;
    private javax.swing.JLabel jlibVariavel;
    private javax.swing.JLabel jlibVariavel1;
    private javax.swing.JLabel jlibVariavel2;
    private javax.swing.JLabel jlibVariavel3;
    private javax.swing.JLabel jlibVariavel4;
    private javax.swing.JLabel jlibVariavel5;
    private javax.swing.JLabel lblCTCONTAB;
    private javax.swing.JLabel lblContabilidade;
    private javax.swing.JLabel lblNovosClientesMes;
    private javax.swing.JLabel lblServicosFinalizados;
    private javax.swing.JLabel lblServicosNaoRealizados;
    private javax.swing.JLabel lblTarefasPendentes;
    private javax.swing.JLabel lblTotalClientesRegistrados;
    private javax.swing.JLabel lblTotalVendas;
    private javax.swing.JLabel lblUserIcon;
    // End of variables declaration//GEN-END:variables
}
