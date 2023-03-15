package carcrowdsystem.ccs.models;

import carcrowdsystem.ccs.abstracts.Funcionario;

public class GerenteEstacionamento extends Funcionario {
    public GerenteEstacionamento(
        String nome,
        String rg,
        String cpf,
        String email,
        String telefone,
        String senha
    ) {
        super(nome, rg, cpf, email, telefone, senha);
        super.setCargo("Gerente");
    }
}