package Data;

public class Auditoria {

    private int id;
    private String usuario;
    private String acao;
    private String hora;
    private String data;

    public Auditoria(int id, String usuario, String acao, String hora, String data) {
        this.id = id;
        this.usuario = usuario;
        this.acao = acao;
        this.hora = hora;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getAcao() {
        return acao;
    }

    public String getHora() {
        return hora;
    }

    public String getData() {
        return data;
    }
}
