/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Classe utilitária para gerenciamento centralizado de Internacionalização (I18n).
 * Permite que todas as telas acessem as strings traduzidas com base na Locale atual.
 */
public class I18nManager {

    private static final String BUNDLE_NAME = "Messages"; // Nome base dos seus arquivos de propriedades
    private static Locale currentLocale;
    private static ResourceBundle currentBundle;

    // Inicializa a Locale e o ResourceBundle. 
    // Por padrão, usa a Locale do sistema ou 'pt_BR' como fallback.
    static {
        currentLocale = new Locale("pt", "BR"); // Define o Português (Brasil) como padrão inicial
        loadBundle();
    }
    
    /**
     * Carrega o ResourceBundle correspondente à Locale atual.
     */
    private static void loadBundle() {
        try {
            // Tenta carregar o ResourceBundle (ex: Messages_pt_BR.properties)
            currentBundle = ResourceBundle.getBundle(BUNDLE_NAME, currentLocale);
        } catch (Exception e) {
            System.err.println("Erro ao carregar ResourceBundle para Locale: " + currentLocale);
            e.printStackTrace();
            // Fallback para Português, se o arquivo específico não for encontrado
            currentLocale = new Locale("pt", "BR");
            currentBundle = ResourceBundle.getBundle(BUNDLE_NAME, currentLocale);
        }
    }

    /**
     * Obtém o texto traduzido para a chave especificada.
     * @param key A chave no arquivo .properties (ex: "login_titulo").
     * @return O texto traduzido ou a chave entre colchetes se não for encontrada.
     */
    public static String getString(String key) {
        try {
            return currentBundle.getString(key);
        } catch (Exception e) {
            return "[" + key + "]";
        }
    }

    /**
     * Altera a Locale global e recarrega o ResourceBundle.
     * @param newLocale A nova Locale a ser utilizada.
     */
    public static void setLocale(Locale newLocale) {
        if (!newLocale.equals(currentLocale)) {
            currentLocale = newLocale;
            loadBundle();
            // Nota: Se outras janelas estiverem abertas, elas precisarão de um mecanismo
            // de Listener/Observer para serem notificadas a chamar 'atualizarTextos()'.
            // Para janelas recém-abertas, basta que elas chamem 'atualizarTextos()' no construtor.
        }
    }

    /**
     * Obtém a Locale atualmente em uso.
     */
    public static Locale getCurrentLocale() {
        return currentLocale;
    }
}