package carcrowdsystem.ccs.entitys;

import carcrowdsystem.ccs.enums.StatusVagaEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HistoricoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private VeiculoEntity veiculo;
    @ManyToOne
    private VagaEntity vaga;
    private LocalDateTime momentoRegistro = LocalDateTime.now();
    private StatusVagaEnum statusRegistro;
    private Double valorPago;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VeiculoEntity getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoEntity veiculo) {
        this.veiculo = veiculo;
    }

    public VagaEntity getVaga() {
        return vaga;
    }

    public void setVaga(VagaEntity vaga) {
        this.vaga = vaga;
    }

    public LocalDateTime getMomentoRegistro() {
        return momentoRegistro;
    }

    public void setMomentoRegistro(LocalDateTime momentoRegistro) {
        this.momentoRegistro = momentoRegistro;
    }

    public StatusVagaEnum getStatusRegistro() {
        return statusRegistro;
    }

    public void setStatusRegistro(StatusVagaEnum statusRegistro) {
        this.statusRegistro = statusRegistro;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }
}


