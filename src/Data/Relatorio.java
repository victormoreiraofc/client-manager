package Data;

public class Relatorio {

    private int id;
    private String nomeRelatorio;
    private String usuario;
    private String descricao;
    private String statusRelatorio;
    private String dataCadastro;

    public Relatorio(int id, String nomeRelatorio, String descricao, String statusRelatorio, String dataCadastro) {
        this.id = id;
        this.usuario = usuario;
        this.nomeRelatorio = nomeRelatorio;
        this.descricao = descricao;
        this.statusRelatorio = statusRelatorio;
        this.dataCadastro = dataCadastro;
    }

    public Relatorio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeRelatorio() {
        return nomeRelatorio;
    }

    public void setNomeRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatusRelatorio() {
        return statusRelatorio;
    }

    public void setStatusRelatorio(String statusRelatorio) {
        this.statusRelatorio = statusRelatorio;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
