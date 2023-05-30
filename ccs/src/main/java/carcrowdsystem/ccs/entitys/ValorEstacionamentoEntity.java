package carcrowdsystem.ccs.entitys;

import javax.persistence.*;

@Entity
public class ValorEstacionamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private EstacionamentoEntity estacionamento;
    private Double primeiraHora;
    private Double horaAdicional;
    private Double diaria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstacionamentoEntity getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(EstacionamentoEntity estacionamento) {
        this.estacionamento = estacionamento;
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
