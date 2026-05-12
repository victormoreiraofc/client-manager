package Data;

import javax.swing.JLabel;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

public class IconUtil {

    public static void setIcon(Usuario usuario, JLabel label, int largura, int altura) {
        try {
            byte[] imagemByte = usuario.getImagem();

            if (imagemByte != null && imagemByte.length > 0) {
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imagemByte));

                if (img != null) {
                    label.setIcon(gerarIconePerfeito(img, largura, altura));
                } else {
                    setIconPadrao(label, largura, altura);
                }
            } else {
                setIconPadrao(label, largura, altura);
            }
        } catch (Exception e) {
            e.printStackTrace();
            setIconPadrao(label, largura, altura);
        }
    }

    public static void setIcon(Usuario usuario, JLabel label) {
        int w = label.getWidth() > 0 ? label.getWidth() : 40;
        int h = label.getHeight() > 0 ? label.getHeight() : 40;
        setIcon(usuario, label, w, h);
    }

    private static void setIconPadrao(JLabel label, int largura, int altura) {
        try {
            java.net.URL recurso = IconUtil.class.getResource("/images/Jonh Doe Icon.png");
            if (recurso == null) {
                label.setIcon(null);
            } else {
                BufferedImage img = ImageIO.read(recurso);
                label.setIcon(gerarIconePerfeito(img, largura, altura));
            }
        } catch (Exception e) {
            e.printStackTrace();
            label.setIcon(null);
        }
    }

    private static javax.swing.Icon gerarIconePerfeito(BufferedImage img, int targetW, int targetH) {
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

        return new IconeSuave(lastImg, targetW, targetH);
    }

    private static class IconeSuave implements javax.swing.Icon {

        private final Image imagem;
        private final int larguraExibicao;
        private final int alturaExibicao;

        public IconeSuave(Image imagem, int larguraExibicao, int alturaExibicao) {
            this.imagem = imagem;
            this.larguraExibicao = larguraExibicao;
            this.alturaExibicao = alturaExibicao;
        }

        @Override
        public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
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
}
