package Data;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Map;
import javax.swing.JComponent;

public class LayoutManager {

    public static void aplicarLayout(String locale, Map<String, JComponent> componentes) {
        Properties props = new Properties();
        String path = "resources/translation/" + locale + "/layout_" + locale + ".dat";

        InputStream is = LayoutManager.class.getClassLoader().getResourceAsStream(path);
        if (is == null) {
            is = LayoutManager.class.getResourceAsStream("/" + path);
        }

        if (is != null) {
            try (InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                props.load(reader);
                
                for (String name : componentes.keySet()) {
                    String value = props.getProperty(name);
                    if (value != null) {
                        String[] b = value.split(",");
                        if (b.length == 4) {
                            componentes.get(name).setBounds(
                                Integer.parseInt(b[0].trim()),
                                Integer.parseInt(b[1].trim()),
                                Integer.parseInt(b[2].trim()),
                                Integer.parseInt(b[3].trim())
                            );
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("ERRO: Arquivo de layout não encontrado no caminho: " + path);
        }
    }
}