package carcrowdsystem.ccs.entitys;

import carcrowdsystem.ccs.enums.StatusVagaEnum;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico")
    private Integer id;
    @Column(name = "momento_registro")
    private LocalDateTime momentoRegistro = LocalDateTime.now().minusHours(3);
    @Column(name = "status_registro")
    private StatusVagaEnum statusRegistro;
    @Column(name = "valor_pago")
    private Double valorPago = 0.0;
    @ManyToOne
    @JoinColumn(name = "fk_veiculo")
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn(name = "fk_vaga")
    private Vaga vaga;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
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


