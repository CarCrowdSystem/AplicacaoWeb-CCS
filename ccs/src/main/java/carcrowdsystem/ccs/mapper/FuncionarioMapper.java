package carcrowdsystem.ccs.mapper;

import carcrowdsystem.ccs.entitys.Funcionario;
import carcrowdsystem.ccs.abstracts.FuncionarioAbstract;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.models.FuncionarioAbstractEstacionamento;
import carcrowdsystem.ccs.models.GerenteEstacionamento;

public class FuncionarioMapper {
    public FuncionarioAbstractEstacionamento toFuncionario(Funcionario f){
        return new FuncionarioAbstractEstacionamento(
                f.getId(),
                f.getNome(),
                f.getCpf(),
                f.getEmail(),
                f.getSenha()
        );
    }

    public GerenteEstacionamento toGerente(FuncionarioAbstract f){
        return new GerenteEstacionamento(
                f.getId(),
                f.getNome(),
                f.getCpf(),
                f.getEmail(),
                f.getSenha()
        );
    }
    public GerenteEstacionamento toGerente(Funcionario f){
        return new GerenteEstacionamento(
                f.getId(),
                f.getNome(),
                f.getCpf(),
                f.getEmail(),
                f.getSenha()
        );
    }

    public FuncionarioDto toFuncionarioDto(FuncionarioAbstract f){
        return new FuncionarioDto(
                f.getId(),
                f.getNome(),
                f.getCpf(),
                f.getAdm(),
                f.getEmail()
        );
    }
    public FuncionarioDto toFuncionarioDto(Funcionario f){
        return new FuncionarioDto(
                f.getId(),
                f.getNome(),
                f.getCpf(),
                f.getUsuarioAdm(),
                f.getEmail()
        );
    }

    public Funcionario toFuncionarioEntity(FuncionarioAbstract f){
        Funcionario func = new Funcionario();
        func.setNome(f.getNome());
        func.setCpf(f.getCpf());
        func.setUsuarioAdm(f.getAdm());
        func.setEmail(f.getEmail());
        func.setSenha(f.getSenha());

        return func;
    }

    public FuncionarioTokenDto toFuncionarioTokenDto(FuncionarioAbstract f, String token){
        FuncionarioTokenDto func = new FuncionarioTokenDto();
        func.setFuncId(f.getId());
        func.setEmail(f.getEmail());
        func.setSenha(f.getSenha());
        func.setToken(token);

        return func;
    }
    public FuncionarioTokenDto toFuncionarioTokenDto(Funcionario f, String token){
        FuncionarioTokenDto func = new FuncionarioTokenDto();
        func.setFuncId(f.getId());
        func.setEmail(f.getEmail());
        func.setSenha(f.getSenha());
        func.setToken(token);

        return func;
    }
}
