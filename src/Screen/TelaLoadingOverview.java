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

public class TelaLoadingOverview extends JFrame {

    private JPanel backgroundColor;
    private JLabel didYouKnow;
    private JTextPane loadingText;
    private RotatingLogo rotatingLogo;

    public TelaLoadingOverview() {
        // ----- WINDOW CONFIG -----
        setTitle("Loading");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(1450, 750);
        setLocationRelativeTo(null);

        // ----- MAIN PANEL -----
        backgroundColor = new JPanel();
        backgroundColor.setBackground(new Color(11, 26, 53));
        backgroundColor.setLayout(new GridBagLayout()); // center all contents
        getContentPane().add(backgroundColor);

        // ----- COMPONENTS -----
        rotatingLogo = new RotatingLogo("/images/Logo Icon.png");
        didYouKnow = new JLabel("VOCÊ SABIA QUE");
        loadingText = new JTextPane();

        setupFontAndColors();
        setupLoadingText();

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
        didYouKnow.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadingText.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(rotatingLogo);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(didYouKnow);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(loadingText);
        contentPanel.add(Box.createVerticalGlue());

        backgroundColor.add(contentPanel, gbc);

        setVisible(true);
    }

    private void setupFontAndColors() {
        try {
            // Load custom font
            InputStream is = getClass().getResourceAsStream("/resources/fonts/SofiaSans.ttf");
            Font baseFont = (is != null)
                    ? Font.createFont(Font.TRUETYPE_FONT, is)
                    : new Font("SansSerif", Font.PLAIN, 14);

            // Derive two styles
            Font titleFont = baseFont.deriveFont(Font.BOLD, 20f);
            Font textFont = baseFont.deriveFont(Font.BOLD, 18f);

            // ---- JLabel setup ----
            didYouKnow.setFont(titleFont);
            didYouKnow.setForeground(Color.decode("#AB8D10"));

            // ---- JTextPane setup ----
            loadingText.setFont(textFont);
            loadingText.setOpaque(false);
            loadingText.setBorder(null);
            loadingText.setEditable(false);

            // Force apply color and font through the document
            StyledDocument doc = loadingText.getStyledDocument();
            SimpleAttributeSet attrs = new SimpleAttributeSet();
            StyleConstants.setFontFamily(attrs, textFont.getFamily());
            StyleConstants.setFontSize(attrs, textFont.getSize());
            StyleConstants.setForeground(attrs, Color.decode("#AB8D10"));
            StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
            StyleConstants.setBold(attrs, true);  
            doc.setParagraphAttributes(0, doc.getLength(), attrs, true);
            doc.setCharacterAttributes(0, doc.getLength(), attrs, true);

            loadingText.setStyledDocument(doc); // force refresh
            loadingText.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupLoadingText() {
        loadingText.setText("Clicando no banner na página principal você\nconsegue adicionar novos clientes.");
        loadingText.setOpaque(false);
        loadingText.setBackground(new Color(0, 0, 0, 0));
        loadingText.setBorder(null);
        loadingText.setEditable(false);
        loadingText.setFocusable(false);
        loadingText.setMaximumSize(new Dimension(600, 80)); // limit width for centering
        setupFontAndColors();
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

            setPreferredSize(new Dimension(100, 100)); // adjust as needed
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
        SwingUtilities.invokeLater(TelaLoadingOverview::new);
    }
}
