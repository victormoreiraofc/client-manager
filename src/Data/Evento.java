package Data;

public class Evento {
    private String evento;
    private String dataInicio;

    // Construtor
    public Evento(String evento, String dataInicio) {
        this.evento = evento;
        this.dataInicio = dataInicio;
    }

    // Getters e Setters
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
}

