package Dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CTCONTAB {

    static Connection conectado;
    
    private static Connection conectar() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conectado = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_ctcontab", "root", "Admin");
        return conectado;
    }
    
    public static Usuario fazerLoginU(String u, String s) throws ClassNotFoundException, SQLException {
    conectado = conectar();
    PreparedStatement st = conectado.prepareStatement("SELECT id, usuario, email FROM usuarios WHERE (usuario = ? OR email = ?) AND senha = ?");
    st.setString(1, u);
    st.setString(2, u);
    st.setString(3, s);
    ResultSet resultado = st.executeQuery();

    Usuario usuario = null;
    if (resultado.next()) {
        
        usuario = new Usuario();
        usuario.setId(resultado.getInt("id"));
        usuario.setUsuario(resultado.getString("usuario"));
        usuario.setEmail(resultado.getString("email"));
    }

    return usuario; // Retorna o objeto Usuario ou null se não encontrado
}
    public static void registrarUsuario (String u, String e, String s) throws ClassNotFoundException, SQLException {
        conectado = conectar();
        PreparedStatement st = conectado.prepareStatement("INSERT INTO usuarios (usuario, email, senha) VALUES(?,?,?)");
        st.setString(1, u);
        st.setString(2, e);
        st.setString(3, s);
        st.executeUpdate();
    }
    
}
