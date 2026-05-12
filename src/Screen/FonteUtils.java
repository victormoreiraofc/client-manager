package screen;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class FonteUtils {

    private static Font carregarFonte(String nomeArquivo, float tamanho, String fallbackName, int fallbackStyle) {
        String caminho = "/resources/fonts/" + nomeArquivo;
        
        try {
            InputStream is = FonteUtils.class.getResourceAsStream(caminho);
            
            if (is == null) {
                is = FonteUtils.class.getResourceAsStream("/fonts/" + nomeArquivo);
            }

            if (is == null) {
                return new Font(fallbackName, fallbackStyle, (int) tamanho);
            }

            Font fonte = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fonte);
            return fonte.deriveFont(tamanho);

        } catch (IOException | FontFormatException e) {
            return new Font(fallbackName, fallbackStyle, (int) tamanho);
        }
    }

    public static Font carregarRoboto(float tamanho) {
        return carregarFonte("Roboto-Black.ttf", tamanho, "SansSerif", Font.BOLD);
    }

    public static Font carregarRobotoSemiBold(float tamanho) {
        return carregarFonte("Roboto-SemiBold.ttf", tamanho, "SansSerif", Font.PLAIN);
    }
    
    public static Font carregarRobotoBold(float tamanho) {
        return carregarFonte("Roboto-Bold.ttf", tamanho, "SansSerif", Font.PLAIN);
    }

    public static Font carregarRobotoExtraBold(float tamanho) {
        return carregarFonte("Roboto-ExtraBold.ttf", tamanho, "SansSerif", Font.BOLD);
    }

    public static Font carregarRobotoMedium(float tamanho) {
        return carregarFonte("Roboto-Medium.ttf", tamanho, "SansSerif", Font.PLAIN);
    }
    
    public static Font carregarRobotoRegular(float tamanho) {
        return carregarFonte("Roboto-Regular.ttf", tamanho, "SansSerif", Font.PLAIN);
    }

    public static Font carregarRaleway(float tamanho) {
        return carregarFonte("Raleway-Regular.ttf", tamanho, "SansSerif", Font.PLAIN);
    }

    public static Font carregarRalewayMedium(float tamanho) {
        return carregarFonte("Raleway-Medium.ttf", tamanho, "SansSerif", Font.PLAIN);
    }

    public static Font carregarSofiaSans(float tamanho) {
        return carregarFonte("SofiaSans.ttf", tamanho, "Dialog", Font.PLAIN);
    }

    public static Font carregarSofiaSansBlack(float tamanho) {
        return carregarFonte("SofiaSans-Black.ttf", tamanho, "SansSerif", Font.BOLD);
    }

    public static Font carregarSofiaSansBold(float tamanho) {
        return carregarFonte("SofiaSans-Bold.ttf", tamanho, "Dialog", Font.BOLD);
    }

    public static Font carregarInterExtraBold(float tamanho) {
        return carregarFonte("Inter_28pt-ExtraBold.ttf", tamanho, "SansSerif", Font.BOLD);
    }

    public static Font carregarLato(float tamanho) {
        return carregarFonte("Lato-Regular.ttf", tamanho, "SansSerif", Font.PLAIN);
    }

    public static Font carregarLatoBlack(float tamanho) {
        return carregarFonte("Lato-Black.ttf", tamanho, "SansSerif", Font.BOLD);
    }

    public static Font carregarLatoBold(float tamanho) {
        return carregarFonte("Lato-Bold.ttf", tamanho, "SansSerif", Font.BOLD);
    }

    public static Font carregarInterSemiBold(float tamanho) {
        return carregarFonte("Inter_28pt-SemiBold.ttf", tamanho, "SansSerif", Font.PLAIN);
    }
    
    public static Font carregarInterBold(float tamanho) {
        return carregarFonte("Inter_28pt-Bold.ttf", tamanho, "SansSerif", Font.PLAIN);
    }
}