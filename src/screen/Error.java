package screen;

import Data.I18nManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Error extends javax.swing.JFrame {

    private final Color DEFAULT_COLOR = new Color(25, 25, 25);
    private final Color GENERAL_HOVER_COLOR = new Color(45, 45, 45);
    private final Color CLOSE_HOVER_COLOR = new Color(196, 43, 28);

    public Error() {
        initComponents();
        setUndecorated(true);
        setIcon();
        setImages();
        localizeAuthScreen();
        populateLanguageSelector();
        setBackground(new java.awt.Color(0, 0, 0, 0));
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
        ((RoundedPanel) pnlButtonRestart).setCustomBorder(new Color(28, 37, 54), 2);
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/Logo Icon.png")));
    }

    private void setImages() {
        try {
            BufferedImage LogoIcon = ImageIO.read(getClass().getResource("/images/Logo Icon Black White.png"));
            BufferedImage CloseDarkWhiteIcon = ImageIO.read(getClass().getResource("/images/Close Dark White Icon.png"));
            BufferedImage MaximizeDarkWhiteIcon = ImageIO.read(getClass().getResource("/images/Maximize Dark White Icon.png"));
            BufferedImage MinimizeWhiteDarkIcon = ImageIO.read(getClass().getResource("/images/Minimize White Dark Icon.png"));

            ControlButton(btnClose, CloseDarkWhiteIcon, CloseDarkWhiteIcon, 12, 12, CLOSE_HOVER_COLOR);
            ControlButton(btnMaximize, MaximizeDarkWhiteIcon, MaximizeDarkWhiteIcon, 12, 12, GENERAL_HOVER_COLOR);
            ControlButton(btnMinimize, MinimizeWhiteDarkIcon, MinimizeWhiteDarkIcon, 12, 2, GENERAL_HOVER_COLOR);

            lblLogo.setIcon(HighQualityImage(LogoIcon, 30, 30));

        } catch (IOException e) {
            System.err.println("Erro ao carregar recursos: " + e.getMessage());
        }
    }

    private void ControlButton(javax.swing.JButton botao, BufferedImage normal, BufferedImage hover, int w, int h, Color corHover) {
        javax.swing.Icon iconNormal = HighQualityImage(normal, w, h);
        javax.swing.Icon iconHover = HighQualityImage(hover, w, h);

        botao.setIcon(iconNormal);
        botao.setRolloverIcon(iconHover);
        botao.setBackground(DEFAULT_COLOR);

        botao.setBorder(null);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setMargin(new java.awt.Insets(0, 0, 0, 0));
        botao.setContentAreaFilled(false);

        botao.setOpaque(true);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setText("");

        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(corHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(DEFAULT_COLOR);
            }
        });
    }

    private javax.swing.Icon HighQualityImage(BufferedImage img, int targetW, int targetH) {
        int scaleFactor = 2;
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage lastImg = img;

        int wAlvoRenderizacao = targetW * scaleFactor;
        int hAlvoRenderizacao = targetH * scaleFactor;

        while (w > wAlvoRenderizacao || h > hAlvoRenderizacao) {
            w = Math.max(w / 2, wAlvoRenderizacao);
            h = Math.max(h / 2, hAlvoRenderizacao);

            BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(lastImg, 0, 0, w, h, null);
            g2.dispose();
            lastImg = tmp;
        }

        return new SoftIcon(lastImg, targetW, targetH);
    }
    
    private void applyRegionalTypography(java.awt.Container container, boolean isAsiatico) {
        for (java.awt.Component c : container.getComponents()) {

            Font fonteAtual = c.getFont();
            int tamanho = fonteAtual.getSize();
            int estilo = fonteAtual.getStyle();

            if (isAsiatico) {
                c.setFont(new Font("SansSerif", estilo, tamanho));
            } else {

            }

            if (c instanceof java.awt.Container) {
                applyRegionalTypography((java.awt.Container) c, isAsiatico);
            }
        }
    }

    private void localizeAuthScreen() {
        Locale loc = I18nManager.getCurrentLocale();
        String langTag = loc.toString();
        boolean isAsiatico = loc.getLanguage().equals("ja") || loc.getLanguage().equals("ko") || loc.getLanguage().equals("zh");

        applyRegionalTypography(this.getContentPane(), isAsiatico);

        setTitle(I18nManager.getString("auth.error.window_title"));
        lblErrorTitle.setText(I18nManager.getString("auth.error.header.title"));
        lblDescriptionError.setText(I18nManager.getString("auth.error.description"));
        lblUnderstandTitle.setText(I18nManager.getString("auth.error.confirm"));
        lblRestartTitle.setText(I18nManager.getString("auth.error.restart"));

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
    
        private void populateLanguageSelector() {
        languageComboBox.removeAllItems();
        languageComboBox.addItem("Português:pt_BR");
        languageComboBox.addItem("English:en_US");
        languageComboBox.addItem("Español:es_ES");
        languageComboBox.addItem("Deutsch:de_DE");
        languageComboBox.addItem("Français:fr_FR");
        languageComboBox.addItem("Italiano:it_IT");
        languageComboBox.addItem("日本語:ja_JP");
        languageComboBox.addItem("한국어:ko_KR");
        languageComboBox.addItem("中文:zh_CN");

        Locale currentLocale = I18nManager.getCurrentLocale();
        String currentLocaleTag = currentLocale.getLanguage() + "_" + currentLocale.getCountry();

        for (int i = 0; i < languageComboBox.getItemCount(); i++) {
            if (languageComboBox.getItemAt(i).contains(":" + currentLocaleTag)) {
                languageComboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    private void customizeLanguageSelectorUI() {
        Locale loc = I18nManager.getCurrentLocale();
        boolean isAsiatico = loc.getLanguage().equals("ja")
                || loc.getLanguage().equals("ko")
                || loc.getLanguage().equals("zh");

        UIManager.put("ComboBox.background", new Color(25, 25, 25));
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", new Color(45, 45, 45));
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);

        languageComboBox.setRenderer(new javax.swing.ListCellRenderer<String>() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList<? extends String> list, String value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.BorderLayout());
                panel.setBackground(isSelected ? new Color(45, 45, 45) : new Color(25, 25, 25));

                if (value == null) {
                    return panel;
                }

                String[] parts = value.split(":");
                String nomeExibicao = parts[0];
                String localeTag = parts.length > 1 ? parts[1] : "";

                JLabel labelIcon = new JLabel();
                try {
                    java.net.URL imgUrl = getClass().getResource("/images/flags/" + localeTag + ".png");
                    if (imgUrl != null) {
                        java.awt.image.BufferedImage flagImg = javax.imageio.ImageIO.read(imgUrl);
                        labelIcon.setIcon(HighQualityImage(flagImg, 18, 12));
                    }
                } catch (Exception e) {
                }

                labelIcon.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 5));
                panel.add(labelIcon, java.awt.BorderLayout.WEST);

                JLabel labelTexto = new JLabel(nomeExibicao, SwingConstants.CENTER);
                labelTexto.setForeground(Color.WHITE);

                if (value.matches(".*[\\u4e00-\\u9fa5\\u3040-\\u309f\\uac00-\\ud7af].*") || isAsiatico) {
                    labelTexto.setFont(new Font("SansSerif", Font.PLAIN, 12));
                } else {
                    labelTexto.setFont(FonteUtils.carregarLato(12f));
                }

                panel.add(labelTexto, java.awt.BorderLayout.CENTER);
                panel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
                //carregarCredenciais();
                return panel;
            }
        });

        languageComboBox.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected javax.swing.plaf.basic.ComboPopup createPopup() {
                return new javax.swing.plaf.basic.BasicComboPopup(comboBox) {
                    @Override
                    protected javax.swing.JScrollPane createScroller() {
                        javax.swing.JScrollPane scroller = new javax.swing.JScrollPane(list);
                        scroller.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                        scroller.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(0, 0));
                        scroller.setBorder(null);
                        return scroller;
                    }
                };
            }

            @Override
            protected javax.swing.JButton createArrowButton() {
                javax.swing.JButton button = new javax.swing.JButton();
                try {
                    java.net.URL url = getClass().getResource("/images/Angle Down Icon.png");
                    if (url != null) {
                        java.awt.image.BufferedImage angleImg = javax.imageio.ImageIO.read(url);
                        button.setIcon(HighQualityImage(angleImg, 10, 10));
                    }
                } catch (Exception e) {
                }
                button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
                button.setContentAreaFilled(false);
                return button;
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        languageComboBox = new javax.swing.JComboBox<>();
        restartButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        lblRestartTitle = new javax.swing.JLabel();
        pnlButtonRestart = new RoundedPanel(40);
        lblUnderstandTitle = new javax.swing.JLabel();
        pnlButtonUnderstand = new RoundedPanel(40);
        lblDescriptionError = new javax.swing.JLabel();
        lblErrorTitle = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        btnMinimize = new javax.swing.JButton();
        btnMaximize = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        pnlMainBackground = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        languageComboBox.setBackground(new java.awt.Color(25, 25, 25));
        languageComboBox.setFont(FonteUtils.carregarLato(10f));
        languageComboBox.setForeground(new java.awt.Color(255, 255, 255));
        languageComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Português" }));
        languageComboBox.setBorder(null);
        languageComboBox.setOpaque(false);
        languageComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                languageComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(languageComboBox);
        languageComboBox.setBounds(1134, 50, 130, 25);

        restartButton.setBackground(new java.awt.Color(25, 25, 25));
        restartButton.setBorder(null);
        restartButton.setContentAreaFilled(false);
        restartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                restartButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                restartButtonMouseExited(evt);
            }
        });
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartButtonActionPerformed(evt);
            }
        });
        getContentPane().add(restartButton);
        restartButton.setBounds(506, 606, 268, 36);

        submitButton.setBackground(new java.awt.Color(237, 241, 242));
        submitButton.setContentAreaFilled(false);
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButtonMouseExited(evt);
            }
        });
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(submitButton);
        submitButton.setBounds(506, 563, 268, 36);

        lblRestartTitle.setFont(FonteUtils.carregarRobotoBold(15f));
        lblRestartTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblRestartTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRestartTitle.setText("Reiniciar");
        getContentPane().add(lblRestartTitle);
        lblRestartTitle.setBounds(549, 617, 182, 14);

        pnlButtonRestart.setBackground(new java.awt.Color(25, 25, 25));
        getContentPane().add(pnlButtonRestart);
        pnlButtonRestart.setBounds(506, 606, 268, 36);

        lblUnderstandTitle.setFont(FonteUtils.carregarRobotoBold(15f));
        lblUnderstandTitle.setForeground(new java.awt.Color(9, 10, 12));
        lblUnderstandTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUnderstandTitle.setText("Entendido");
        getContentPane().add(lblUnderstandTitle);
        lblUnderstandTitle.setBounds(549, 576, 182, 14);

        pnlButtonUnderstand.setBackground(new java.awt.Color(237, 241, 242));
        pnlButtonUnderstand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlButtonUnderstandMouseEntered(evt);
            }
        });
        getContentPane().add(pnlButtonUnderstand);
        pnlButtonUnderstand.setBounds(506, 563, 268, 36);

        lblDescriptionError.setFont(FonteUtils.carregarRobotoMedium(12f));
        lblDescriptionError.setForeground(new java.awt.Color(209, 213, 219));
        lblDescriptionError.setText("<html>Ops! Algo não saiu como esperado. Estamos com instabilidade no sistema no momento. Nossa equipe já foi notificada e está trabalhando para corrigir. Por favor, tente novamente em alguns minutos.</html>");
        lblDescriptionError.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblDescriptionError.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblDescriptionError);
        lblDescriptionError.setBounds(403, 337, 478, 94);

        lblErrorTitle.setFont(FonteUtils.carregarRobotoBold(30f));
        lblErrorTitle.setForeground(new java.awt.Color(209, 213, 219));
        lblErrorTitle.setText("Erro");
        getContentPane().add(lblErrorTitle);
        lblErrorTitle.setBounds(403, 282, 476, 25);
        getContentPane().add(lblLogo);
        lblLogo.setBounds(624, 75, 30, 30);

        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Minimize White Dark Icon.png"))); // NOI18N
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });
        getContentPane().add(btnMinimize);
        btnMinimize.setBounds(1160, 0, 40, 40);

        btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Maximize Dark White Icon.png"))); // NOI18N
        btnMaximize.setContentAreaFilled(false);
        btnMaximize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizeActionPerformed(evt);
            }
        });
        getContentPane().add(btnMaximize);
        btnMaximize.setBounds(1200, 0, 40, 40);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close Dark White Icon.png"))); // NOI18N
        btnClose.setContentAreaFilled(false);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose);
        btnClose.setBounds(1240, 0, 40, 40);

        pnlMainBackground.setBackground(new java.awt.Color(25, 25, 25));
        getContentPane().add(pnlMainBackground);
        pnlMainBackground.setBounds(0, 0, 1280, 720);

        setSize(new java.awt.Dimension(1280, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void btnMaximizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaximizeActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void pnlButtonUnderstandMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlButtonUnderstandMouseEntered

    }//GEN-LAST:event_pnlButtonUnderstandMouseEntered

    private void submitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseEntered
        pnlButtonUnderstand.setBackground(new java.awt.Color(190, 190, 190));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_submitButtonMouseEntered

    private void submitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseExited
        pnlButtonUnderstand.setBackground(new java.awt.Color(237, 241, 242));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_submitButtonMouseExited

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_submitButtonActionPerformed

    private void restartButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restartButtonMouseEntered
        pnlButtonRestart.setBackground(new java.awt.Color(50, 50, 50));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_restartButtonMouseEntered

    private void restartButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restartButtonMouseExited
        pnlButtonRestart.setBackground(new java.awt.Color(25, 25, 25));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_restartButtonMouseExited

    private void restartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartButtonActionPerformed

    }//GEN-LAST:event_restartButtonActionPerformed

    private void languageComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languageComboBoxActionPerformed
        Object selected = languageComboBox.getSelectedItem();
        if (selected != null) {
            String item = selected.toString();
            if (item.contains(":")) {
                String localeTag = item.split(":")[1];
                String[] parts = localeTag.split("_");
                I18nManager.setLocale(new Locale(parts[0], parts[1]));
                localizeAuthScreen();
                customizeLanguageSelectorUI();
            }
        }
    }//GEN-LAST:event_languageComboBoxActionPerformed

    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Error.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Error.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Error.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Error.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Error().setVisible(true);
            }
        });
    }

    class RoundedPanel extends javax.swing.JPanel {

        private int radius;
        private Color borderColor = null;
        private int borderThickness = 0;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        public void setCustomBorder(Color color, int thickness) {
            this.borderColor = color;
            this.borderThickness = thickness;
            repaint();
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

            if (borderColor != null && borderThickness > 0) {
                g2.setColor(borderColor);
                g2.setStroke(new java.awt.BasicStroke(borderThickness));

                g2.drawRoundRect(borderThickness / 2, borderThickness / 2,
                        getWidth() - borderThickness, getHeight() - borderThickness,
                        radius, radius);
            }

            g2.dispose();
            super.paintComponent(g);
        }
    }

    class SoftIcon implements javax.swing.Icon {

        private final Image imagem;
        private final int larguraExibicao;
        private final int alturaExibicao;

        public SoftIcon(Image imagem, int larguraExibicao, int alturaExibicao) {
            this.imagem = imagem;
            this.larguraExibicao = larguraExibicao;
            this.alturaExibicao = alturaExibicao;
        }

        @Override
        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(imagem, x, y, larguraExibicao, alturaExibicao, null);
            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return larguraExibicao;
        }

        @Override
        public int getIconHeight() {
            return alturaExibicao;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMaximize;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JComboBox<String> languageComboBox;
    private javax.swing.JLabel lblDescriptionError;
    private javax.swing.JLabel lblErrorTitle;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblRestartTitle;
    private javax.swing.JLabel lblUnderstandTitle;
    private javax.swing.JPanel pnlButtonRestart;
    private javax.swing.JPanel pnlButtonUnderstand;
    private javax.swing.JPanel pnlMainBackground;
    private javax.swing.JButton restartButton;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
