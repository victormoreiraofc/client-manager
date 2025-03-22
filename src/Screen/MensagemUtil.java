package Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MensagemUtil {

    static {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 10));
    }

    public static void exibirSucesso(String mensagem) {
        ImageIcon icon = new ImageIcon(MensagemUtil.class.getResource("/images/success-message.png"));
        exibirMensagem("Sucesso!", mensagem, new Color(0, 117, 2), icon, "OK", "Cancelar", null);
    }

    public static void exibirErro(String mensagem) {
        ImageIcon icon = new ImageIcon(MensagemUtil.class.getResource("/images/erro-mensage.png"));
        exibirMensagem("Erro", mensagem, new Color(220, 0, 0), icon, "OK", "Cancelar", null);
    }

    public static void exibirAlerta(String mensagem, ActionListener simAcao) {
        ImageIcon icon = new ImageIcon(MensagemUtil.class.getResource("/images/alert-mensage.png"));
        exibirMensagem("Alerta", mensagem, new Color(255, 165, 0), icon, "Sim, Delete!", "Cancelar", simAcao);
    }

    private static void exibirMensagem(String titulo, String mensagem, Color corTexto, ImageIcon icon, String botaoSimTexto, String botaoNaoTexto, ActionListener simAcao) {
        JDialog dialog = new JDialog();
        dialog.setTitle(titulo);
        dialog.setModal(true);
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        panel.setPreferredSize(new Dimension(450, 350));
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel iconLabel = new JLabel(icon);
        panel.add(iconLabel, gbc);

        gbc.gridy = 1;
        JLabel titleLabel = new JLabel(titulo);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(corTexto);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, gbc);

        gbc.gridy = 2;
        JLabel messageLabel = new JLabel("<html><div style='text-align: center; font-family: gidole; font-size: 14px; color: #5c5e5c;'>" + mensagem + "</div></html>");
        panel.add(messageLabel, gbc);

        gbc.gridy = 3;
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botoesPanel.setOpaque(false);

        JButton simButton = new JButton(botaoSimTexto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(66, 135, 245));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        simButton.setFont(new Font("Arial", Font.BOLD, 12));
        simButton.setForeground(Color.WHITE);
        simButton.setFocusPainted(false);
        simButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        simButton.setContentAreaFilled(false);
        simButton.setOpaque(false);
        simButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        simButton.addActionListener(e -> {
            dialog.dispose();
        });

        JButton naoButton = new JButton(botaoNaoTexto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(220, 0, 0));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        naoButton.setFont(new Font("Arial", Font.BOLD, 12));
        naoButton.setForeground(Color.WHITE);
        naoButton.setFocusPainted(false);
        naoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        naoButton.setContentAreaFilled(false);
        naoButton.setOpaque(false);
        naoButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        naoButton.addActionListener(e -> dialog.dispose());

        botoesPanel.add(simButton);
        botoesPanel.add(naoButton);

        panel.add(botoesPanel, gbc);

        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
