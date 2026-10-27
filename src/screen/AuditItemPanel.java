package screen;

import java.awt.*;
import javax.swing.*;

public class AuditItemPanel extends JPanel {

    private static final int MARGEM = 12;

    private final String mensagem;

    public AuditItemPanel(String mensagem) {
        this.mensagem = mensagem == null ? "" : mensagem;
        setPreferredSize(new Dimension(976, 18));
        setOpaque(false);
        setToolTipText(this.mensagem);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont(FonteUtils.carregarRobotoRegular(12f));
        g2.setColor(new Color(209, 213, 219));
        FontMetrics fm = g2.getFontMetrics();
        int y = (getHeight() + fm.getAscent()) / 2 - 2;

        g2.drawString(ajustarTexto(mensagem, fm, getWidth() - (MARGEM * 2)), MARGEM, y);
        g2.dispose();
    }

    private String ajustarTexto(String texto, FontMetrics fm, int larguraMax) {
        if (fm.stringWidth(texto) <= larguraMax) {
            return texto;
        }
        String reticencias = "...";
        int fim = texto.length();
        while (fim > 0 && fm.stringWidth(texto.substring(0, fim) + reticencias) > larguraMax) {
            fim--;
        }
        return texto.substring(0, fim) + reticencias;
    }
}
