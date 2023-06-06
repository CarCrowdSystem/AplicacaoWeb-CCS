package carcrowdsystem.ccs.entitys;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class VagaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private EstacionamentoEntity estacionamento;
    @OneToMany
    private List<HistoricoEntity> historicoEntity;
    private Integer numero;
    private String andar;

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

    public List<HistoricoEntity> getHistoricoEntity() {
        return historicoEntity;
    }

    public void setHistoricoEntity(List<HistoricoEntity> historicoEntity) {
        this.historicoEntity = historicoEntity;
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
