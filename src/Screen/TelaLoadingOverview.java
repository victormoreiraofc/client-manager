/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screen;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 *
 * @author mateu
 */
public class TelaLoadingOverview extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaLoadingOverview.class.getName());

    /**
     * Creates new form TelaLoginOverview
     */
    public TelaLoadingOverview() {
        initComponents();
        //chama o método que ajusta o tamanho do png da logo junto com o label 
        resizeLogoToLabel();
        //chama o método da fonte 
        applyCustomFont();

        addRotatingLogo();
    }

    //método que ajusta o tamanho do png
    private void resizeLogoToLabel() {
        try {
            // Load original image 
            BufferedImage originalImage = ImageIO.read(getClass().getResource("/images/Logo Icon.png"));
            // Get label dimensions
            int labelWidth = Logo.getWidth();
            int labelHeight = Logo.getHeight();
            // If the label hasn't been laid out yet, try later
            if (labelWidth == 0 || labelHeight == 0) {
                SwingUtilities.invokeLater(this::resizeLogoToLabel);
                return;
            }

            // Calculate scaling while keeping aspect ratio
            double widthRatio = (double) labelWidth / originalImage.getWidth();
            double heightRatio = (double) labelHeight / originalImage.getHeight();
            double scale = Math.min(widthRatio, heightRatio); // fit inside the label

            int newWidth = (int) (originalImage.getWidth() * scale);
            int newHeight = (int) (originalImage.getHeight() * scale);

            // Scale the image
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Set the scaled icon
            Logo.setIcon(new ImageIcon(scaledImage));

            //Center the image in the label
            Logo.setHorizontalAlignment(SwingConstants.CENTER);
            Logo.setVerticalAlignment(SwingConstants.CENTER);
        } catch (IOException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Failed to load or resize logo image.", ex);
        }
    }

    //método da fonte nova (SofiaSans)
    private void applyCustomFont() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Load custom font
                InputStream is = getClass().getResourceAsStream("/resources/fonts/SofiaSans.ttf");
                if (is == null) {
                    throw new IOException("Font file not found!");
                }

                Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
                // Different sizes for each component
                Font labelFont = baseFont.deriveFont(Font.BOLD, 12f); // JLabel font size
                Font textPaneFont = baseFont.deriveFont(Font.PLAIN, 16f); // JTextPane font size

                // Apply to label
                didYouKnow.setFont(labelFont);
                didYouKnow.setForeground(Color.decode("#AB8D10"));

                // Apply to text pane
                loadingText.setFont(textPaneFont);
                loadingText.setForeground(Color.decode("#AB8D10"));
                loadingText.setOpaque(false);
                loadingText.setBorder(null);
                loadingText.setEditable(false);

                //Override the NetBeans GUI shit (this took me 4 hours alone)
                StyledDocument doc = loadingText.getStyledDocument();
                SimpleAttributeSet attrs = new SimpleAttributeSet();
                StyleConstants.setFontFamily(attrs, baseFont.getFamily());
                StyleConstants.setFontSize(attrs, 20);
                StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), attrs, false);

            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    // -------------------- ROTATING LOGO --------------------
    private void addRotatingLogo() {
        RotatingLogo rotatingLogo = new RotatingLogo("/images/Logo Icon.png");

        // Original image size
        int imgWidth = 60;
        int imgHeight = 60;
        
        // Diagonal of the image (maximum extent during rotation)
        int diagonal = (int) Math.sqrt(Math.pow(imgWidth, 2) + Math.pow(imgHeight, 2));

        // Add extra padding to avoid clipping
        int padding = 10;
        int panelSize = diagonal + padding;

         // Center the rotating panel inside backgroundColor
        int panelX = (backgroundColor.getWidth() - panelSize) / 2;
        int panelY = (backgroundColor.getHeight() - panelSize) / 2;

        rotatingLogo.setBounds(panelX, panelY, panelSize, panelSize);
        backgroundColor.add(rotatingLogo);
        backgroundColor.repaint();

        // Hide original JLabel
        Logo.setVisible(false);
    }

    public class RotatingLogo extends JPanel {

        private BufferedImage image;
        private double angle = 0; // rotation in radians

        public RotatingLogo(String resourcePath) {
            setOpaque(false); // transparent background
            try {
                image = ImageIO.read(getClass().getResource(resourcePath));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Timer to rotate the image
            Timer timer = new Timer(10, e -> {
                angle += 0.03; // rotation speed
                if (angle >= 2 * Math.PI) {
                    angle = 0;
                }
                repaint();
            });
            timer.start();
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

            // Scale image to fit inside panel with padding
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
    //---------------------------------------------------

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundColor = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        didYouKnow = new javax.swing.JLabel();
        loadingTextScroll = new javax.swing.JScrollPane();
        loadingText = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        backgroundColor.setBackground(new java.awt.Color(11, 26, 53));
        backgroundColor.setForeground(new java.awt.Color(11, 26, 53));
        backgroundColor.setMaximumSize(new java.awt.Dimension(1450, 750));
        backgroundColor.setLayout(null);

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo Icon.png"))); // NOI18N
        backgroundColor.add(Logo);
        Logo.setBounds(670, 320, 100, 100);

        didYouKnow.setText("VOCÊ SABIA QUE");
        backgroundColor.add(didYouKnow);
        didYouKnow.setBounds(715, 440, 120, 40);

        loadingTextScroll.setBorder(null);
        loadingTextScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        loadingTextScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        loadingText.setEditable(false);
        loadingText.setBackground(new java.awt.Color(11, 26, 53));
        loadingText.setForeground(new java.awt.Color(255, 255, 255));
        loadingText.setText("Clicando no banner na página principal você consegue adicionar novos clientes.");
        loadingText.setAutoscrolls(false);
        loadingText.setFocusable(false);
        loadingText.setOpaque(false);
        loadingTextScroll.setViewportView(loadingText);

        backgroundColor.add(loadingTextScroll);
        loadingTextScroll.setBounds(560, 480, 390, 70);

        getContentPane().add(backgroundColor);
        backgroundColor.setBounds(-20, -20, 1530, 780);

        setSize(new java.awt.Dimension(1450, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaLoadingOverview().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JPanel backgroundColor;
    private javax.swing.JLabel didYouKnow;
    private javax.swing.JTextPane loadingText;
    private javax.swing.JScrollPane loadingTextScroll;
    // End of variables declaration//GEN-END:variables
}
