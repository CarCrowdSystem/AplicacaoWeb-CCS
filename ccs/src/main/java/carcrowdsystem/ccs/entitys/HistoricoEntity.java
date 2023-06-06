package carcrowdsystem.ccs.entitys;

import carcrowdsystem.ccs.enums.StatusVagaEnum;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico")
public class HistoricoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico")
    private Integer id;
    @Column(name = "momento_registro")
    private LocalDateTime momentoRegistro = LocalDateTime.now();
    @Column(name = "status_registro")
    private StatusVagaEnum statusRegistro;
    @Column(name = "valor_pago")
    private Double valorPago;
    @ManyToOne
    private VeiculoEntity veiculo;
    @ManyToOne
    private VagaEntity vaga;

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


