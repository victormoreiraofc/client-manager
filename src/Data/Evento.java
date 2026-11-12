package Data;

public class Evento {

    private int id;
    private String evento;
    private String dataInicio;
    private String dataFinal;
    private String horarioInicial;
    private String horarioFinal;
    private String usuario;

    public Evento(String evento, String dataInicio) {
        this.evento = evento;
        this.dataInicio = dataInicio;
    }

    public Evento(int id, String evento, String dataInicio, String dataFinal, String horarioInicial, String horarioFinal, String usuario) {
        this.id = id;
        this.evento = evento;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public String getUsuario() {
        return usuario;
    }
}
