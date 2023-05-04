package carcrowdsystem.ccs.mapper;

import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.abstracts.Funcionario;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.models.FuncionarioEstacionamento;
import carcrowdsystem.ccs.models.GerenteEstacionamento;

public class FuncionarioMapper {
    public FuncionarioEstacionamento toFuncionario(FuncionarioEntity f){
        return new FuncionarioEstacionamento(
                f.getId(),
                f.getNome(),
                f.getRg(),
                f.getCpf(),
                f.getEmail(),
                f.getTelefone(),
                f.getSenha()
        );
    }

    public GerenteEstacionamento toGerente(Funcionario f){
        return new GerenteEstacionamento(
                f.getId(),
                f.getNome(),
                f.getRg(),
                f.getCpf(),
                f.getEmail(),
                f.getTelefone(),
                f.getSenha()
        );
    }
    public GerenteEstacionamento toGerente(FuncionarioEntity f){
        return new GerenteEstacionamento(
                f.getId(),
                f.getNome(),
                f.getRg(),
                f.getCpf(),
                f.getEmail(),
                f.getTelefone(),
                f.getSenha()
        );
    }

    public FuncionarioDto toFuncionarioDto(Funcionario f){
        return new FuncionarioDto(
                f.getNome(),
                f.getRg(),
                f.getCpf(),
                f.getCargo(),
                f.getEmail(),
                f.getTelefone()
        );
    }
    public FuncionarioDto toFuncionarioDto(FuncionarioEntity f){
        return new FuncionarioDto(
                f.getNome(),
                f.getRg(),
                f.getCpf(),
                f.getAdm(),
                f.getEmail(),
                f.getTelefone()
        );
    }

    public FuncionarioEntity toFuncionarioEntity(Funcionario f){
        FuncionarioEntity func = new FuncionarioEntity();
        func.setNome(f.getNome());
        func.setRg(f.getRg());
        func.setCpf(f.getCpf());
        func.setAdm(f.getCargo());
        func.setEmail(f.getEmail());
        func.setTelefone(f.getTelefone());
        func.setSenha(f.getSenha());

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
    public FuncionarioTokenDto toFuncionarioTokenDto(FuncionarioEntity f, String token){
        FuncionarioTokenDto func = new FuncionarioTokenDto();
        func.setFuncId(f.getId());
        func.setEmail(f.getEmail());
        func.setSenha(f.getSenha());
        func.setToken(token);

        return func;
    }
}
