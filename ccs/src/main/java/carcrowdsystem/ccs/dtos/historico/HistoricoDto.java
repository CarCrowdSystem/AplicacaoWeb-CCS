package carcrowdsystem.ccs.dtos.historico;

import carcrowdsystem.ccs.enums.StatusVagaEnum;

import java.time.LocalDateTime;

public class HistoricoDto {
    private LocalDateTime momentoRegistro = LocalDateTime.now().minusHours(3);
    private StatusVagaEnum statusRegistro;
    private Double valorPago;

    public HistoricoDto(StatusVagaEnum statusRegistro, Double valorPago) {
        this.statusRegistro = statusRegistro;
        this.valorPago = valorPago;
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
