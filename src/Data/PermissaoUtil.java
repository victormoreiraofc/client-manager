package Data;
import javax.swing.JButton;

public class PermissaoUtil {
    public static void aplicarPermissao(Usuario usuario, JButton botao) {
        
        if ("NULL".equals(usuario.getPermissao())) {
            botao.setVisible(false);
        } else {
            botao.setVisible(true); 
        }
    }
}
