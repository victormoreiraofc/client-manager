package Data;

public class Cliente {

    private int id;
    private String nome;
    private String tipoPessoa;
    private String situacaoServico;
    private String servico;
    private String dataCadastro;

    public Cliente(int id, String nome, String tipoPessoa, String situacaoServico, String servico, String dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
        this.situacaoServico = situacaoServico;
        this.servico = servico;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getSituacaoServico() {
        return situacaoServico;
    }

    public void setSituacaoServico(String situacaoServico) {
        this.situacaoServico = situacaoServico;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
