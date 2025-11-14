package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import org.slf4j.MDC;
import Data.CTCONTAB;
import Data.Usuario;
import Screen.MensagemUtil;

public class TelaAlterarSenha extends JDialog {
    private JPasswordField txtSenhaAtual;
    private JPasswordField txtNovaSenha;
    private JPasswordField txtConfirmarSenha;
    private Usuario usuarioLogado;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAlterarSenha.class.getName());

    public TelaAlterarSenha(Window parent, Usuario usuarioLogado) {
        super(parent, "Alterando sua senha:", ModalityType.APPLICATION_MODAL);
        this.usuarioLogado = usuarioLogado;

        setSize(520, 340);
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
        JLabel lblTitulo = new JLabel("Alterando sua senha:");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo, gbc);

        // Senha atual
        gbc.gridy++;
        JLabel lblSenhaAtual = new JLabel("Senha Atual");
        lblSenhaAtual.setForeground(new Color(180, 200, 230));
        lblSenhaAtual.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblSenhaAtual, gbc);

        gbc.gridy++;
        txtSenhaAtual = criarCampoSenha();
        panel.add(txtSenhaAtual, gbc);

        // Nova senha
        gbc.gridy++;
        JLabel lblNovaSenha = new JLabel("Nova Senha");
        lblNovaSenha.setForeground(new Color(180, 200, 230));
        lblNovaSenha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblNovaSenha, gbc);

        gbc.gridy++;
        txtNovaSenha = criarCampoSenha();
        panel.add(txtNovaSenha, gbc);

        // Confirmar nova senha
        gbc.gridy++;
        JLabel lblConfirmarSenha = new JLabel("Confirmar Nova Senha");
        lblConfirmarSenha.setForeground(new Color(180, 200, 230));
        lblConfirmarSenha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblConfirmarSenha, gbc);

        gbc.gridy++;
        txtConfirmarSenha = criarCampoSenha();
        panel.add(txtConfirmarSenha, gbc);

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
        btnAtualizar.addActionListener(e -> atualizarSenha());

        // Fechar com ESC
        panel.registerKeyboardAction(e -> dispose(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private JPasswordField criarCampoSenha() {
        JPasswordField txt = new JPasswordField();
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

private void atualizarSenha() {
    MDC.put("usuario", usuarioLogado.getUsuario());

    String senhaAtual = new String(txtSenhaAtual.getPassword()).trim();
    String senhaNova = new String(txtNovaSenha.getPassword()).trim();
    String senhaConfirmar = new String(txtConfirmarSenha.getPassword()).trim();

    if (senhaAtual.isEmpty() || senhaNova.isEmpty() || senhaConfirmar.isEmpty()) {
        MensagemUtil.exibirErro("Preencha todos os campos!");
        return;
    }

    if (!senhaNova.equals(senhaConfirmar)) {
        MensagemUtil.exibirErro("As senhas não coincidem!");
        return;
    }

    if (senhaNova.length() < 4) {
        MensagemUtil.exibirErro("A nova senha deve ter pelo menos 4 caracteres!");
        return;
    }

    try {
        // 🔥 Busca a senha atual direto do banco
        String senhaBanco = CTCONTAB.buscarSenhaPorEmail(usuarioLogado.getEmail());

        if (!senhaAtual.equals(senhaBanco)) {
            MensagemUtil.exibirErro("A senha atual está incorreta!");
            return;
        }

        // Atualiza no banco
        CTCONTAB.atualizarSenhaUsuario(usuarioLogado.getEmail(), senhaNova);
        MensagemUtil.exibirSucesso("Senha alterada com sucesso!");
        logger.info("Usuário alterou a senha com sucesso: " + usuarioLogado.getUsuario());
        dispose();

    } catch (SQLException e) {
        MensagemUtil.exibirErro("Erro ao atualizar senha: " + e.getMessage());
    } catch (ClassNotFoundException e) {
        MensagemUtil.exibirErro("Erro ao acessar o banco de dados.");
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Usuario user = new Usuario();
            user.setUsuario("UsuarioAtual");
            user.setEmail("teste@gmail.com");
            user.setSenha("123456");

            TelaAlterarSenha dialog = new TelaAlterarSenha(null, user);
            dialog.setVisible(true);
        });
    }
}
