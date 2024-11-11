package Data;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String usuario;
    private String email;   
    
    public Usuario() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
}
