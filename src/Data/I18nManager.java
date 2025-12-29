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

    private static final String BUNDLE_BASE_NAME = "translation"; 
    private static final String FILE_PREFIX = "client_manager";
    private static Locale currentLocale;
    private static ResourceBundle currentBundle;

    static {
        currentLocale = new Locale("pt", "BR");
        loadBundle();
    }

    private static void loadBundle() {
        try {
            String fullPath = BUNDLE_BASE_NAME + "." + currentLocale.toString() + "." + FILE_PREFIX;
            currentBundle = ResourceBundle.getBundle(fullPath, currentLocale, new DatControl());
        } catch (Exception e) {
            System.err.println("Erro ao carregar tradução: " + e.getMessage());
        }
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static String getString(String key) {
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

    private static class DatControl extends Control {
        @Override
        public List<String> getFormats(String baseName) { return List.of("dat"); }

        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) 
                throws java.io.IOException {
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "dat");
            try (InputStream stream = loader.getResourceAsStream(resourceName)) {
                if (stream != null) {
                    return new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
                }
            }
            return null;
        }
    }
}