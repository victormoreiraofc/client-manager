package Data;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IconManager {

    private static final Map<String, ImageIcon> CACHE = new ConcurrentHashMap<>();

    private IconManager() {}

    public static ImageIcon get(String path, int width, int height) {
        String key = path + "_" + width + "x" + height;

        if (CACHE.containsKey(key)) {
            return CACHE.get(key);
        }

        URL resource = IconManager.class.getResource(path);
        if (resource == null) {
            throw new RuntimeException("Ícone não encontrado: " + path);
        }

        ImageIcon original = new ImageIcon(resource);

        BufferedImage resized = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2 = resized.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(original.getImage(), 0, 0, width, height, null);
        g2.dispose();

        ImageIcon icon = new ImageIcon(resized);
        CACHE.put(key, icon);
        return icon;
    }
}
