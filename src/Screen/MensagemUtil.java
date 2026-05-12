package screen;

import javax.swing.*;
import java.awt.*;

public class MensagemUtil {

    static {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 10));
    }

    public static void exibirSucesso(String mensagem) {
        exibirMensagem("Sucesso!", mensagem);
    }

    public static void exibirErro(String mensagem) {
        exibirMensagem("Erro", mensagem);
    }

    private static void exibirMensagem(String titulo, String mensagem) {
        JDialog overlay = new JDialog();
        overlay.setUndecorated(true);
        overlay.setSize(1280, 720);
        overlay.setLocationRelativeTo(null);
        overlay.setBackground(new Color(25, 25, 25, 127));
        JDialog dialog = new JDialog(overlay); 
        dialog.setTitle(titulo);
        dialog.setModal(true);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0, 0, 0, 0));

        int larguraPanel = 600;
        int alturaPanel = 98;

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(25, 25, 25));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(237, 241, 242));
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }
        };

        panel.setPreferredSize(new Dimension(larguraPanel, alturaPanel));
        panel.setOpaque(false);
        panel.setLayout(null);

        JLabel messageLabel = new JLabel("<html><div style='text-align: justify; color: #D1D5DB;'>" + mensagem + "</div></html>");
        messageLabel.setFont(FonteUtils.carregarRobotoMedium(12f));
        messageLabel.setBounds(10, 10, larguraPanel - 20, 30);
        panel.add(messageLabel);

        JButton okButton = new JButton("OK") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(237, 241, 242));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {}
        };

        okButton.setFont(FonteUtils.carregarRobotoBold(13f));
        okButton.setForeground(Color.BLACK);
        okButton.setFocusPainted(false);
        okButton.setContentAreaFilled(false);
        okButton.setBorderPainted(false);
        okButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        okButton.setBounds(385, 61, 200, 22);
        
        okButton.addActionListener(e -> {
            dialog.dispose();
            overlay.dispose();
        });
        
        panel.add(okButton);

        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        overlay.setVisible(true);
        dialog.setVisible(true);
    }
}