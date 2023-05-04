package carcrowdsystem.ccs.entitys;

import javax.persistence.*;

@Entity
public class VagaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private EstacionamentoEntity estacionamento;
    @OneToMany
    private HistoricoEntity historicoEntity;
    private Integer numero;
    private Integer andar;

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

    public HistoricoEntity getHistoricoEntity() {
        return historicoEntity;
    }

    public void setHistoricoEntity(HistoricoEntity historicoEntity) {
        this.historicoEntity = historicoEntity;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }
}
