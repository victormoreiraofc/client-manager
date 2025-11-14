package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import org.slf4j.MDC;
import Data.CTCONTAB;
import Data.Usuario;
import Screen.MensagemUtil;

public class TelaAlterarUsuario extends JDialog {
    private JTextField txtNomeAtual;
    private JTextField txtNovoNome;
    private Usuario usuarioLogado;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaAlterarUsuario.class.getName());

    public TelaAlterarUsuario(Window parent, Usuario usuarioLogado) {
        super(parent, "Alterando seu usuário atual:", ModalityType.APPLICATION_MODAL);
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
        JLabel lblTitulo = new JLabel("Alterando seu usuário atual:");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo, gbc);

        // Nome atual
        gbc.gridy++;
        JLabel lblNomeAtual = new JLabel("Nome Atual");
        lblNomeAtual.setForeground(new Color(180, 200, 230));
        lblNomeAtual.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblNomeAtual, gbc);

        gbc.gridy++;
        txtNomeAtual = criarCampoTexto();
        txtNomeAtual.setText(usuarioLogado.getUsuario());
        txtNomeAtual.setEditable(false);
        panel.add(txtNomeAtual, gbc);

        // Novo nome
        gbc.gridy++;
        JLabel lblNovoNome = new JLabel("Novo Nome");
        lblNovoNome.setForeground(new Color(180, 200, 230));
        lblNovoNome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lblNovoNome, gbc);

        gbc.gridy++;
        txtNovoNome = criarCampoTexto();
        panel.add(txtNovoNome, gbc);

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
        btnAtualizar.addActionListener(e -> atualizarUsuario());

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

    private void atualizarUsuario() {
        MDC.put("usuario", usuarioLogado.getUsuario());

        String nomeAtual = txtNomeAtual.getText().trim();
        String nomeNovo = txtNovoNome.getText().trim();

        if (nomeNovo.isEmpty()) {
            MensagemUtil.exibirErro("Digite o novo nome de usuário!");
            return;
        }

        if (!nomeNovo.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            MensagemUtil.exibirErro("O nome de usuário só pode conter letras!");
            return;
        }

        if (nomeNovo.equals(nomeAtual)) {
            MensagemUtil.exibirErro("O novo nome deve ser diferente do atual!");
            return;
        }

        try {
            CTCONTAB.atualizarNomeUsuario(nomeAtual, nomeNovo);
            usuarioLogado.setUsuario(nomeNovo);
            MensagemUtil.exibirSucesso("Nome de usuário atualizado com sucesso!");
            logger.info("Usuário alterou o nome de " + nomeAtual + " para " + nomeNovo);
            dispose();
        } catch (SQLException e) {
            if (e.getMessage().contains("O nome de usuário já está em uso")) {
                MensagemUtil.exibirErro("Este nome de usuário já está sendo usado. Escolha outro.");
            } else {
                MensagemUtil.exibirErro("Erro: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            MensagemUtil.exibirErro("Erro ao atualizar o nome de usuário.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Usuario user = new Usuario();
            user.setUsuario("UsuarioAtual");
            user.setEmail("teste@gmail.com");

            TelaAlterarUsuario dialog = new TelaAlterarUsuario(null, user);
            dialog.setVisible(true);
        });
    }
}
