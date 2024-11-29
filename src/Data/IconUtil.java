package Data;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;

public class IconUtil {

    public static void setIcon(Usuario usuario, JLabel label) {
        try {
            byte[] imagemByte = usuario.getImagem();

            if (imagemByte != null && imagemByte.length > 0) {
                ImageIcon imagemIcon = new ImageIcon(imagemByte);
                Image img = imagemIcon.getImage();
                Image imagemRedimensionada = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

                label.setIcon(new ImageIcon(imagemRedimensionada));
            } else {
                setIconPadrao(label);
            }
        } catch (Exception e) {
            e.printStackTrace();
            setIconPadrao(label);
        }
    }

    private static void setIconPadrao(JLabel label) {
        try {
            java.net.URL recurso = IconUtil.class.getResource("/images/minhatura-de-perfil.png");
            if (recurso == null) {
                System.out.println("Recurso não encontrado: /images/minhatura-de-perfil.png");
                label.setIcon(null);
            } else {
                ImageIcon icon = new ImageIcon(recurso);
                Image img = icon.getImage();
                Image imagemRedimensionada = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

                label.setIcon(new ImageIcon(imagemRedimensionada));
            }
        } catch (Exception e) {
            e.printStackTrace();
            label.setIcon(null);
        }
    }
}
