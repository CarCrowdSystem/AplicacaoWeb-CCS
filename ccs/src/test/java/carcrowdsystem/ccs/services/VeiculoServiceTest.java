package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.dtos.veiculo.VeiculoRequest;
import carcrowdsystem.ccs.entitys.Veiculo;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.VeiculoRepository;
import carcrowdsystem.ccs.services.builders.VeiculoBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class VeiculoServiceTest {
    @Autowired
    VeiculoService service;
    @MockBean
    VeiculoRepository repository;
    @Captor
    ArgumentCaptor<Veiculo> veiculoCaptor;

    @Test
    @DisplayName("Deve salvar um novo veiculo com sucesso")
    void salvarComSucesso() throws MyException {
        VeiculoRequest veiculoRequest = VeiculoBuilder.criarVeiculoRequest();
        Veiculo veiculo = VeiculoBuilder.criarVeiculo();

        service.postVeiculo(veiculoRequest);

        Mockito.verify(repository).save(veiculoCaptor.capture());
        Veiculo veiculoSalvo = veiculoCaptor.getValue();

        Assertions.assertThat(veiculoSalvo.getModelo().equals(veiculo.getModelo()));
        Assertions.assertThat(veiculoSalvo.getNomeCliente().equals(veiculo.getNomeCliente()));
        Assertions.assertThat(veiculoSalvo.getPlaca().equals(veiculo.getPlaca()));
        Assertions.assertThat(veiculoSalvo.getTelefoneCliente().equals(veiculo.getTelefoneCliente()));
    }

    @Test
    @DisplayName("Deve dar erro ao tentar salvar um novo veiculo")
    void erroAoSalvarVeiculo() throws MyException {
        VeiculoRequest veiculoRequest = null;

        MyException myException = assertThrows(MyException.class, () -> {
            service.postVeiculo(veiculoRequest);
        });

        Assertions.assertThat(myException.message).isEqualTo("Body incorreto");
    }

}