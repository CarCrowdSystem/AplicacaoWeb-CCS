package carcrowdsystem.ccs.response;

import carcrowdsystem.ccs.entitys.Vaga;
import carcrowdsystem.ccs.entitys.Veiculo;
import carcrowdsystem.ccs.enums.StatusVagaEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class HistoricoResponse {
    private Integer id;
    private LocalDateTime momentoRegistro;
    private StatusVagaEnum statusRegistro;
    private Double valorPago;
    private Integer idVeiculo;
    private Integer idVaga;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Integer getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Integer idVaga) {
        this.idVaga = idVaga;
    }
}
