package Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class TelaUpdateLoadingOverview extends JFrame {

    private JPanel backgroundColor;
    private JLabel checkingUpdates;
    private RotatingLogo rotatingLogo;

    public TelaUpdateLoadingOverview() {
        // ----- WINDOW CONFIG -----
        setTitle("Loading");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(378, 441);
        setLocationRelativeTo(null);

        // ----- MAIN PANEL -----
        backgroundColor = new JPanel();
        backgroundColor.setBackground(new Color(11, 26, 53));
        backgroundColor.setLayout(new GridBagLayout()); // center all contents
        getContentPane().add(backgroundColor);

        // ----- COMPONENTS -----
        rotatingLogo = new RotatingLogo("/images/Logo Icon.png");
        checkingUpdates = new JLabel("Procurando atualizações...");
        checkingUpdates.setFont(FonteUtils.carregarSofiaSansBlack(14f));

        setupFontAndColors();

        // ----- LAYOUT (center vertically + horizontally) -----
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 0, 10, 0); // vertical spacing between elements

        // A wrapper panel allows vertical stacking centered
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the vertical stack
        rotatingLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkingUpdates.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(rotatingLogo);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(checkingUpdates);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(Box.createVerticalGlue());

        backgroundColor.add(contentPanel, gbc);

        setVisible(true);
    }

    private void setupFontAndColors() {
        try {
            // Configura JLabel
            checkingUpdates.setForeground(Color.decode("#AB8D10"));

            // Define a fonte desejada
            Font customFont = FonteUtils.carregarSofiaSansBold(12f);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // -------------------- ROTATING LOGO --------------------
    private static class RotatingLogo extends JPanel {

        private BufferedImage image;
        private double angle = 0;

        public RotatingLogo(String resourcePath) {
            setOpaque(false);
            try {
                image = ImageIO.read(getClass().getResource(resourcePath));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Start rotation
            Timer timer = new Timer(10, e -> {
                angle += 0.03;
                if (angle >= 2 * Math.PI) {
                    angle = 0;
                }
                repaint();
            });
            timer.start();

            setPreferredSize(new Dimension(70, 70)); // adjust as needed
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image == null) {
                return;
            }

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int cx = getWidth() / 2;
            int cy = getHeight() / 2;

            double scale = Math.min((double) (getWidth() - 20) / image.getWidth(),
                    (double) (getHeight() - 20) / image.getHeight());
            int iw = (int) (image.getWidth() * scale);
            int ih = (int) (image.getHeight() * scale);

            g2d.translate(cx, cy);
            g2d.rotate(angle);
            g2d.drawImage(image, -iw / 2, -ih / 2, iw, ih, null);
            g2d.dispose();
        }
    }

    // -------------------- MAIN --------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaUpdateLoadingOverview::new);
    }
}
