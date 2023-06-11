package carcrowdsystem.ccs.entitys;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "vaga")
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaga")
    private Integer id;
    private Integer numero;
    private String andar;
    @ManyToOne
    @JoinColumn(name = "fk_estacionamento")
    private Estacionamento estacionamento;
    @OneToMany
    private List<Historico> historico;

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

    public List<Historico> getHistoricoEntity() {
        return historico;
    }

    public void setHistoricoEntity(List<Historico> historico) {
        this.historico = historico;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }
}
