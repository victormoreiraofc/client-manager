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
            Font raleway = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            raleway = raleway.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(raleway);
            return raleway;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }

    public static Font carregarSofiaSans(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/SofiaSans.ttf");
            Font sofiasans = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            sofiasans = sofiasans.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(sofiasans);
            return sofiasans;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SofiaSans", Font.PLAIN, (int) tamanho);
        }
    }
    
    public static Font carregarSofiaSansBlack(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/SofiaSans-Black.ttf");
            Font sofiasansblack = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            sofiasansblack = sofiasansblack.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(sofiasansblack);
            return sofiasansblack;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SofiaSans-Black", Font.PLAIN, (int) tamanho);
        }
    }
    
    public static Font carregarSofiaSansBold(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/SofiaSans-Bold.ttf");
            Font sofiasansbold = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            sofiasansbold = sofiasansbold.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(sofiasansbold);
            return sofiasansbold;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SofiaSans-Bold", Font.BOLD, (int) tamanho);
        }
    }

}
