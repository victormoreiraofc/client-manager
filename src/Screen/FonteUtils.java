package Screen;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class FonteUtils {
    
 public static Font carregarRoboto(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Roboto-Black.ttf");
            Font roboto = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            roboto = roboto.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(roboto);
            return roboto;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }
 
 public static Font carregarRaleway(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Raleway-Regular.ttf");
            Font roboto = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            roboto = roboto.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(roboto);
            return roboto;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }
}