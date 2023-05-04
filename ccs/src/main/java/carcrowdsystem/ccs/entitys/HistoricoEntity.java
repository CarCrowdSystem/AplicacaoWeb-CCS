package carcrowdsystem.ccs.entitys;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class HistoricoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorico;
    @ManyToOne
    private VeiculoEntity veiculo;
    @OneToMany
    private VagaEntity vaga;
    private LocalDateTime momentoRegistro;
    private String statusRegistro;
    private Double valorPago;

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Integer idHistorico) {
        this.idHistorico = idHistorico;
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

    public String getStatusRegistro() {
        return statusRegistro;
    }

    public void setStatusRegistro(String statusRegistro) {
        this.statusRegistro = statusRegistro;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }
}


