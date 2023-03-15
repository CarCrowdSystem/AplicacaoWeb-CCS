package carcrowdsystem.ccs.models;

import carcrowdsystem.ccs.abstracts.Funcionario;

public class GerenteEstacionamento extends Funcionario {
    public GerenteEstacionamento(
        String nome,
        String rg,
        String cpf,
        String cargo,
        String email,
        String telefone,
        String senha
    ) {
        super(nome, rg, cpf, cargo, email, telefone, senha);
    }
}