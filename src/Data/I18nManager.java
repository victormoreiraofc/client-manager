package Data;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.List;

public class I18nManager {

    private static Locale currentLocale;
    private static ResourceBundle currentBundle;

    static {
        currentLocale = new Locale("pt", "BR");
        loadBundle();
    }

    public static void loadBundle() {
        try {
            currentBundle = ResourceBundle.getBundle("idioma", currentLocale, new DatControl());
        } catch (Exception e) {}
    }

    public static String getString(String key) {
        if (currentBundle == null) {
            return "[" + key + "]";
        }
        try {
            return currentBundle.getString(key);
        } catch (Exception e) {
            return "[" + key + "]";
        }
    }

    public static void setLocale(Locale newLocale) {
        if (newLocale != null && !newLocale.equals(currentLocale)) {
            currentLocale = newLocale;
            loadBundle();
        }
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    private static class DatControl extends Control {

        @Override
        public List<String> getFormats(String baseName) {
            return List.of("dat");
        }

        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws java.io.IOException {

            String folder = locale.toString();
            String path = "resources/translation/" + folder + "/client_manager_" + folder + ".dat";

            InputStream stream = loader.getResourceAsStream(path);

            if (stream == null) {
                stream = loader.getResourceAsStream("/" + path);
            }

            if (stream != null) {
                try {
                    return new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
                } finally {
                    stream.close();
                }
            } else {
                return null;
            }
        }
    }
}
