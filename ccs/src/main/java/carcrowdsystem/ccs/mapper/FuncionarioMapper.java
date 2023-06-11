package carcrowdsystem.ccs.mapper;

import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.abstracts.FuncionarioAbstract;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.models.FuncionarioAbstractEstacionamento;
import carcrowdsystem.ccs.models.GerenteEstacionamento;

public class FuncionarioMapper {
    public FuncionarioAbstractEstacionamento toFuncionario(FuncionarioEntity f){
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
    public GerenteEstacionamento toGerente(FuncionarioEntity f){
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
                f.getNome(),
                f.getCpf(),
                f.getAdm(),
                f.getEmail()
        );
    }
    public FuncionarioDto toFuncionarioDto(FuncionarioEntity f){
        return new FuncionarioDto(
                f.getNome(),
                f.getCpf(),
                f.getUsuarioAdm(),
                f.getEmail()
        );
    }

    public FuncionarioEntity toFuncionarioEntity(FuncionarioAbstract f){
        FuncionarioEntity func = new FuncionarioEntity();
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
    public FuncionarioTokenDto toFuncionarioTokenDto(FuncionarioEntity f, String token){
        FuncionarioTokenDto func = new FuncionarioTokenDto();
        func.setFuncId(f.getId());
        func.setEmail(f.getEmail());
        func.setSenha(f.getSenha());
        func.setToken(token);

        return func;
    }
}
