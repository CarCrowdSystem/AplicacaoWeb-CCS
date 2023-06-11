package carcrowdsystem.ccs.models;

import carcrowdsystem.ccs.abstracts.FuncionarioAbstract;

public class FuncionarioAbstractEstacionamento extends FuncionarioAbstract {
    public FuncionarioAbstractEstacionamento(
        Integer id,
        String nome,
        String cpf,
        String email,
        String senha
    ) {
        super(id, nome, cpf, email, senha);
        super.setAdm(false);
    }
}
