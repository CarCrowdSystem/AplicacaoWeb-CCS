package carcrowdsystem.ccs.models;

import carcrowdsystem.ccs.abstracts.Funcionario;

public class FuncionarioEstacionamento extends Funcionario {
    public FuncionarioEstacionamento(
        String nome,
        String rg,
        String cpf,
        String email,
        String telefone,
        String senha
    ) {
        super(nome, rg, cpf, email, telefone, senha);
        super.setCargo("Funcionário");
    }
}
