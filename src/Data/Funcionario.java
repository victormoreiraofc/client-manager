package Data;

import java.util.Date;

public class Funcionario {

    private int id;
    private String usuario;
    private String cargo;
    private String email;
    private String senha;
    private byte[] imagem;
    private String permissao;
    private Date created_at; 

    public Funcionario(int id, String usuario, String cargo, String email, String senha, byte[] imagem, String permissao, Date created_at) {
        this.id = id;
        this.usuario = usuario;
        this.cargo = cargo;
        this.email = email;
        this.senha = senha;
        this.imagem = imagem;
        this.permissao = permissao;
        this.created_at = created_at; 
    }

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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
    
