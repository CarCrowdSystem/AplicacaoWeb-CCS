package carcrowdsystem.ccs.services.builders;

import carcrowdsystem.ccs.dtos.historico.HistoricoDto;
import carcrowdsystem.ccs.entitys.Historico;
import carcrowdsystem.ccs.enums.StatusVagaEnum;

import java.time.LocalDateTime;


public class HistoricoBuilder {
    public HistoricoBuilder(){
    }
    public static Historico criarHistoricoRequest() {
        Historico historico = new Historico();
        historico.setStatusRegistro(StatusVagaEnum.Saida);
        historico.setVeiculo(VeiculoBuilder.criarVeiculo2());
        historico.setVaga(VagaBuilder.criarVagaRequest());
        historico.setId(1);
        historico.setValorPago(20.0);
        historico.setMomentoRegistro(LocalDateTime.now());
        return historico;
    }

    public static HistoricoDto criarHistoricoDtoRequest() {
        HistoricoDto historico = new HistoricoDto(
            StatusVagaEnum.Saida, 20.0
        );
        return historico;
    }
}
