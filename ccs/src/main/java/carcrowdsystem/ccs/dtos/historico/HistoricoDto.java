package carcrowdsystem.ccs.dtos.historico;

import java.time.LocalDateTime;

public class HistoricoDto {
    private LocalDateTime momentoRegistro = LocalDateTime.now();
    private String statusRegistro;
    private Double valorPago;

    public HistoricoDto(String statusRegistro, Double valorPago) {
        this.statusRegistro = statusRegistro;
        this.valorPago = valorPago;
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
