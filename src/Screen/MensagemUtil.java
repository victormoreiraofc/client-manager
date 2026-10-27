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

        JButton okButton = criarBotao("OK", new Color(237, 241, 242), Color.BLACK);
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

    public static boolean exibirConfirmacao(String mensagem) {
        return exibirConfirmacao("Confirmação", mensagem, "Sim", "Não");
    }

    public static boolean exibirConfirmacao(String titulo, String mensagem) {
        return exibirConfirmacao(titulo, mensagem, "Sim", "Não");
    }

    public static boolean exibirConfirmacao(String titulo, String mensagem, String textoSim, String textoNao) {
        final boolean[] resultado = {false};

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

        int larguraBotao = 95;
        int espacamento = 10;
        int xBase = larguraPanel - (larguraBotao * 2) - espacamento - 15;

        JButton naoButton = criarBotao(textoNao, new Color(60, 63, 68), new Color(237, 241, 242));
        naoButton.setBounds(xBase, 61, larguraBotao, 22);

        JButton simButton = criarBotao(textoSim, new Color(237, 241, 242), Color.BLACK);
        simButton.setBounds(xBase + larguraBotao + espacamento, 61, larguraBotao, 22);

        naoButton.addActionListener(e -> {
            resultado[0] = false;
            dialog.dispose();
            overlay.dispose();
        });

        simButton.addActionListener(e -> {
            resultado[0] = true;
            dialog.dispose();
            overlay.dispose();
        });

        panel.add(naoButton);
        panel.add(simButton);

        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        overlay.setVisible(true);
        dialog.setVisible(true);

        return resultado[0];
    }

    private static JButton criarBotao(String texto, Color corFundo, Color corTexto) {
        JButton botao = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(corFundo);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {}
        };

        botao.setFont(FonteUtils.carregarRobotoBold(13f));
        botao.setForeground(corTexto);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }
}