package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.controllers.VagaController;
import carcrowdsystem.ccs.controllers.VeiculoController;
import carcrowdsystem.ccs.dtos.historico.HistoricoDto;
import carcrowdsystem.ccs.entitys.Historico;
import carcrowdsystem.ccs.entitys.Vaga;
import carcrowdsystem.ccs.entitys.Veiculo;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.HistoricoRepository;
import carcrowdsystem.ccs.services.builders.HistoricoBuilder;
import carcrowdsystem.ccs.services.builders.VagaBuilder;
import carcrowdsystem.ccs.services.builders.VeiculoBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class HistoricoServiceTest {
    @Autowired
    HistoricoService service;
    @MockBean
    HistoricoRepository repository;
    @MockBean
    VeiculoController veiculoController;
    @MockBean
    VagaController vagaController;
    @Captor
    ArgumentCaptor<Historico> historicoCaptor;

    @Test
    @DisplayName("Deve salvar um novo historico com sucesso")
    void salvarComSucesso() throws MyException {
        HistoricoDto historico = HistoricoBuilder.criarHistoricoDtoRequest();
        Vaga vagaMock = VagaBuilder.criarVagaRequest();
        Veiculo veiculoMock = VeiculoBuilder.criarVeiculo2();
        Mockito.when(vagaController.getVagaById(1)).thenReturn(ResponseEntity.ok(VagaBuilder.criarVagaRequest()));
        Mockito.when(veiculoController.getVeiculoById(1)).thenReturn(ResponseEntity.ok(VeiculoBuilder.criarVeiculo2()));

        service.postHistorico(historico, 1, 1);

        Mockito.verify(repository).save(historicoCaptor.capture());
        Historico historicoSalvo = historicoCaptor.getValue();

        Assertions.assertThat(historicoSalvo.getValorPago())
                .isEqualTo(historico.getValorPago());
        Assertions.assertThat(historicoSalvo.getMomentoRegistro())
                .isEqualTo(historico.getMomentoRegistro());
        Assertions.assertThat(historicoSalvo.getStatusRegistro())
                .isEqualTo(historico.getStatusRegistro());
    }
}