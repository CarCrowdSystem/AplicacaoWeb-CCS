package carcrowdsystem.ccs.response;

import carcrowdsystem.ccs.entitys.Historico;

import java.util.List;

public class DadosDashResponse {
    private Integer totalCheckoutDiario;
    private Double totalFaturamento;
    private List<MomentoVagasResponse> momentoVagas;

    public Integer getTotalCheckoutDiario() {
        return totalCheckoutDiario;
    }

    public void setTotalCheckoutDiario(Integer totalCheckoutDiario) {
        this.totalCheckoutDiario = totalCheckoutDiario;
    }

    public Double getTotalFaturamento() {
        return totalFaturamento;
    }

    public void setTotalFaturamento(Double totalFaturamento) {
        this.totalFaturamento = totalFaturamento;
    }

    public List<MomentoVagasResponse> getMomentoVagas() {
        return momentoVagas;
    }

    public void setMomentoVagas(List<MomentoVagasResponse> momentoVagas) {
        this.momentoVagas = momentoVagas;
    }
}
