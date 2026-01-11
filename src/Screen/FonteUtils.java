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
            Font Roboto = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            Roboto = Roboto.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Roboto);
            return Roboto;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }

    public static Font carregarRobotoSemiBold(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Roboto-SemiBold.ttf");
            Font RobotoSemiBold = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            RobotoSemiBold = RobotoSemiBold.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(RobotoSemiBold);
            return RobotoSemiBold;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }

    public static Font carregarRobotoExtraBold(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Roboto-ExtraBold.ttf");
            Font RobotoExtraBold = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            RobotoExtraBold = RobotoExtraBold.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(RobotoExtraBold);
            return RobotoExtraBold;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }
    
        public static Font carregarRobotoMedium(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Roboto-Medium.ttf");
            Font RobotoMedium = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            RobotoMedium = RobotoMedium.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(RobotoMedium);
            return RobotoMedium;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }

    public static Font carregarRaleway(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Raleway-Regular.ttf");
            Font Raleway = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            Raleway = Raleway.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Raleway);
            return Raleway;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }

    public static Font carregarRalewayMedium(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Raleway-Medium.ttf");
            Font RalewayMedium = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            RalewayMedium = RalewayMedium.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(RalewayMedium);
            return RalewayMedium;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }

    public static Font carregarSofiaSans(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/SofiaSans.ttf");
            Font SofiaSans = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            SofiaSans = SofiaSans.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(SofiaSans);
            return SofiaSans;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SofiaSans", Font.PLAIN, (int) tamanho);
        }
    }

    public static Font carregarSofiaSansBlack(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/SofiaSans-Black.ttf");
            Font SofiaSansBlack = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            SofiaSansBlack = SofiaSansBlack.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(SofiaSansBlack);
            return SofiaSansBlack;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }

    public static Font carregarSofiaSansBold(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/SofiaSans-Bold.ttf");
            Font SofiaSansBold = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            SofiaSansBold = SofiaSansBold.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(SofiaSansBold);
            return SofiaSansBold;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SofiaSans-Bold", Font.BOLD, (int) tamanho);
        }
    }
    
    public static Font carregarInterExtraBold(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Inter_28pt-ExtraBold.ttf");
            Font InterExtraBold = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            InterExtraBold = InterExtraBold.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(InterExtraBold);
            return InterExtraBold;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }

    public static Font carregarLato(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Lato-Regular.ttf");
            Font Lato = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            Lato = Lato.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Lato);
            return Lato;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }
    
    public static Font carregarLatoBlack(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Lato-Black.ttf");
            Font LatoBlack = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            LatoBlack = LatoBlack.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(LatoBlack);
            return LatoBlack;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }
    
    public static Font carregarLatoBold(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Lato-Bold.ttf");
            Font LatoBold = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            LatoBold = LatoBold.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(LatoBold);
            return LatoBold;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }


    public static Font carregarInterSemiBold(float tamanho) {
        try {
            File arquivoFonte = new File("src/resources/fonts/Inter_28pt-SemiBold.ttf");
            Font InterSemiBold = Font.createFont(Font.TRUETYPE_FONT, arquivoFonte);
            InterSemiBold = InterSemiBold.deriveFont(tamanho);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(InterSemiBold);
            return InterSemiBold;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamanho);
        }
    }

}
