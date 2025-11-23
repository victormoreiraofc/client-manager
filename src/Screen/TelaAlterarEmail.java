package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import org.slf4j.MDC;
import Data.CTCONTAB;
import Data.Usuario;
import Screen.MensagemUtil;

public class TelaAlterarEmail extends JDialog {
    private JTextField txtEmailAtual;
    private JTextField txtNovoEmail;
    private Usuario usuarioLogado;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAlterarEmail.class.getName());

    public TelaAlterarEmail(Window parent, Usuario usuarioLogado) {
    super(parent, "Alterando seu e-mail atual:", ModalityType.APPLICATION_MODAL);
    this.usuarioLogado = usuarioLogado;

        setSize(520, 280);
        setLocationRelativeTo(parent);
        setUndecorated(true);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(15, 25, 45));
        panel.setBorder(BorderFactory.createLineBorder(new Color(40, 55, 85), 1, true));
        panel.setLayout(new GridBagLayout());
        getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 20, 8, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;

        // Título
        gbc.gridy = 0;
        JLabel lblTitulo = new JLabel("Alterando seu email atual:");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo, gbc);

        // E-mail atual
        gbc.gridy++;
        JLabel lblEmailAtual = new JLabel("E-mail Atual");
        lblEmailAtual.setForeground(new Color(180, 200, 230));
        lblEmailAtual.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblEmailAtual, gbc);

        gbc.gridy++;
        txtEmailAtual = criarCampoTexto();
        txtEmailAtual.setText(usuarioLogado.getEmail());
        panel.add(txtEmailAtual, gbc);

        // Novo e-mail
        gbc.gridy++;
        JLabel lblNovoEmail = new JLabel("Novo E-mail");
        lblNovoEmail.setForeground(new Color(180, 200, 230));
        lblNovoEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblNovoEmail, gbc);

        gbc.gridy++;
        txtNovoEmail = criarCampoTexto();
        panel.add(txtNovoEmail, gbc);

        // Painel de botões
        gbc.gridy++;
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        botoes.setOpaque(false);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(25, 40, 65));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBackground(new Color(0, 122, 255));
        btnAtualizar.setForeground(Color.WHITE);
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));

        botoes.add(btnCancelar);
        botoes.add(btnAtualizar);
        panel.add(botoes, gbc);

        // Ações dos botões
        btnCancelar.addActionListener(e -> dispose());

        btnAtualizar.addActionListener(e -> atualizarEmail());

        // Fechar com ESC
        panel.registerKeyboardAction(e -> dispose(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private JTextField criarCampoTexto() {
        JTextField txt = new JTextField();
        txt.setBackground(new Color(25, 40, 65));
        txt.setForeground(Color.WHITE);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setCaretColor(Color.WHITE);
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 70, 100)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return txt;
    }

    private void atualizarEmail() {
        MDC.put("usuario", usuarioLogado.getUsuario());

        String emailAtual = txtEmailAtual.getText().trim();
        String emailNovo = txtNovoEmail.getText().trim();

        if (emailNovo.isEmpty()) {
            MensagemUtil.exibirErro("Digite o novo e-mail!");
            return;
        }

        try {
            if (!emailNovo.equals(emailAtual)) {
                CTCONTAB.atualizarEmailUsuario(emailAtual, emailNovo);
                usuarioLogado.setEmail(emailNovo);
                MensagemUtil.exibirSucesso("E-mail atualizado com sucesso!");
                logger.info("Usuário alterou seu e-mail de " + emailAtual + " para " + emailNovo);
                dispose();
            } else {
                MensagemUtil.exibirErro("O novo e-mail deve ser diferente do atual!");
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("O e-mail já está sendo usado")) {
                MensagemUtil.exibirErro("Este e-mail já está cadastrado. Escolha outro.");
            } else {
                MensagemUtil.exibirErro("Erro: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            MensagemUtil.exibirErro("Erro ao atualizar o e-mail.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Usuario user = new Usuario();
            user.setUsuario("teste");
            user.setEmail("seuemail@gmail.com");

            TelaAlterarEmail dialog = new TelaAlterarEmail(null, user);
            dialog.setVisible(true);
        });
    }
}