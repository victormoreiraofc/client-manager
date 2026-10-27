package Data;

public class AuditUtil {

    private static final String SEPARADOR = "|";

    public static void registrar(String usuario, String chave, String... argumentos) {
        try {
            StringBuilder acao = new StringBuilder(chave);
            for (String argumento : argumentos) {
                String valor = argumento == null ? "" : argumento.replace(SEPARADOR, "/");
                acao.append(SEPARADOR).append(valor);
            }
            CTCONTAB.registrarAuditoria(usuario, acao.toString());
        } catch (Exception e) {
            System.err.println("Falha ao registrar auditoria: " + e.getMessage());
        }
    }

    public static String montarMensagem(Auditoria registro) {
        String[] partes = registro.getAcao().split("\\|", -1);
        String chave = partes[0];
        Object[] argumentos = new Object[partes.length - 1];
        System.arraycopy(partes, 1, argumentos, 0, argumentos.length);

        String mensagem = registro.getAcao();
        try {
            String modelo = I18nManager.getString(chave);
            if (modelo != null && !modelo.isEmpty() && !modelo.contains(chave)) {
                mensagem = String.format(modelo, argumentos);
            }
        } catch (Exception e) {
            mensagem = registro.getAcao();
        }

        String horario;
        try {
            horario = String.format(I18nManager.getString("audit.time_suffix"), registro.getHora(), registro.getData());
        } catch (Exception e) {
            horario = registro.getHora() + " - " + registro.getData();
        }
        return mensagem + " " + horario;
    }
}
