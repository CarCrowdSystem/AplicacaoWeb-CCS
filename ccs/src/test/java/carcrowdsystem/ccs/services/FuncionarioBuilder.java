package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.entitys.Funcionario;

import java.util.List;

public class FuncionarioBuilder {

    public FuncionarioBuilder(){

    }

    public static List<FuncionarioDto> criarListaFuncionarioDto() {
        return  List.of(new FuncionarioDto(1, "Teste", "22233344499", false,
                "teste1@gmail.com"), new FuncionarioDto(2, "Teste2", "33344455588",
                false, "teste2@gmail.com"));
    }

    public static List<Funcionario> criarListaFuncionario(){
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setNome("Func1");
        funcionario1.setUsuarioAdm(false);
        Funcionario funcionario2 = new Funcionario();
        funcionario1.setNome("Func2");
        funcionario2.setUsuarioAdm(false);
        return List.of(new Funcionario(), new Funcionario());
    }

    public static List<Funcionario> criarArrayFuncionario(){
        FuncionarioDto[] funcionarios = new FuncionarioDto[
                {new FuncionarioDto(1, "Teste", "22233344499", false,
                        "teste1@gmail.com")}, {new FuncionarioDto(2, "Teste2", "33344455588",
                        false, "teste2@gmail.com")}
        ];

        return List.of(new Funcionario(), new Funcionario());
    }


}
