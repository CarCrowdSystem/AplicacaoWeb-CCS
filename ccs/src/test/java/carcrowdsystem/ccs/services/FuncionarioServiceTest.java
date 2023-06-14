package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.entitys.Funcionario;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.FuncionarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class FuncionarioServiceTest {
    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Test
    @DisplayName("Buscar todos quando acionados deve retornar todos funcionários")
    void buscarTodosQuandoAcionadoDeveRetornarFuncionarios() throws MyException {
        Integer id = 1;

        List<Funcionario> funcionarios = FuncionarioBuilder.criarListaFuncionario();
        List<FuncionarioDto> funcionariosDto = FuncionarioBuilder.criarListaFuncionarioDto();

        Mockito.when(this.funcionarioRepository.findAllByIdEstacionamento(id)).thenReturn(funcionarios);
        Mockito.when(this.funcionarioRepository.findAll()).thenReturn(funcionarios);
        List<FuncionarioDto> listar = this.funcionarioService.getAllFuncs(id);
        Assertions.assertEquals(funcionariosDto.size(), listar.size());
    }

    @Test
    @DisplayName("Buscar todos quando acionados deve retornar tofod funcionários")
    void buscarTodosPorOrdemAlfabetica() throws MyException {
        Integer id = 1;

        Funcionario[] funcionarios = FuncionarioBuilder.criarListaFuncionario();
        List<FuncionarioDto> funcionariosDto = FuncionarioBuilder.criarListaFuncionarioDto();

        Mockito.when(funcionarioService.getAllFuncs(id).toArray(new FuncionarioDto[0])).thenReturn(funcionarios);
        Mockito.when(this.funcionarioRepository.findAll()).thenReturn(funcionarios);
        FuncionarioDto[] listar = this.funcionarioService.getAllFuncsOrderByName(id);
        Assertions.assertEquals(funcionariosDto.size(), listar.size());
    }


}