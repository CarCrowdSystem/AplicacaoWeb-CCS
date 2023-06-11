package carcrowdsystem.ccs.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.*;

@Entity
@Table(name = "valor_estacionamento")
public class ValorEstacionamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preco")
    private Integer id;
    @Column(name = "primeira_hora")
    private Double primeiraHora;
    @Column(name = "hora_adicional")
    private Double horaAdicional;
    private Double diaria;
    @ManyToOne
    @JoinColumn(name = "fk_estacionamento")
    private Estacionamento estacionamento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
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
