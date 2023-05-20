package carcrowdsystem.ccs.dtos.valorEstacionamento;

public class ValorEstacionamentoDto {
    private Double primeiraHora;
    private Double horaAdicional;
    private Double diaria;

    public ValorEstacionamentoDto(Double primeiraHora, Double horaAdicional, Double diaria) {
        this.primeiraHora = primeiraHora;
        this.horaAdicional = horaAdicional;
        this.diaria = diaria;
    }

    public Double getPrimeiraHora() {
        return primeiraHora;
    }

    public void setPrimeiraHora(Double primeiraHora) {
        this.primeiraHora = primeiraHora;
    }

    public Double getHoraAdicional() {
        return horaAdicional;
    }

    public void setHoraAdicional(Double horaAdicional) {
        this.horaAdicional = horaAdicional;
    }

    public Double getDiaria() {
        return diaria;
    }

    public void setDiaria(Double diaria) {
        this.diaria = diaria;
    }
}
