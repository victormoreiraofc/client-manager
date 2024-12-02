package Data;

public class ClienteInfo {

    private int id;
    private String nome;
    private String tipoPessoa;
    private String email;
    private String servico;
    private String situacaoServico;
    private String celular;
    private String telefone;
    private String observacoes;
    private String dataCadastro;
    private String usuario;

    public ClienteInfo(int id, String nome, String tipoPessoa, String email, String servico, String situacaoServico, String celular, String telefone, String observacoes, String dataCadastro, String usuario) {
        this.id = id;
        this.nome = nome;
        this.tipoPessoa = tipoPessoa;
        this.email = email;
        this.servico = servico;
        this.situacaoServico = situacaoServico;
        this.celular = celular;
        this.telefone = telefone;
        this.observacoes = observacoes;
        this.dataCadastro = dataCadastro;
        this.usuario = usuario;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getSituacaoServico() {
        return situacaoServico;
    }

    public void setSituacaoServico(String situacaoServico) {
        this.situacaoServico = situacaoServico;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getObservacoes() {
        return observacoes;
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
