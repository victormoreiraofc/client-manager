package Screen; // coloque seu package correto

import java.awt.*;
import javax.swing.*;

public class RoundButton extends JButton {

    private Color fillColor = new Color(0x1C2E4A);
    private Color hoverColor = new Color(0x45A9E2);
    private Color borderColor = new Color(0x2A3E61);
    private boolean hovering = false;

    public RoundButton(Icon icon) {
        super(icon);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover listener
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hovering = true;
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hovering = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Cores
        g2.setColor(hovering ? hoverColor : fillColor);
        g2.fillOval(0, 0, getWidth()-1, getHeight()-1);

        // Borda
        g2.setStroke(new BasicStroke(2));
        g2.setColor(borderColor);
        g2.drawOval(1, 1, getWidth()-3, getHeight()-3);

        super.paintComponent(g);  
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        int radius = getWidth() / 2;
        int cx = radius;
        int cy = radius;
        return (Math.pow(x - cx, 2) + Math.pow(y - cy, 2)) <= Math.pow(radius, 2);
    }
}
