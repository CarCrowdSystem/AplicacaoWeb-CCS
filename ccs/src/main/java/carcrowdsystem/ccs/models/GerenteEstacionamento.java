package carcrowdsystem.ccs.models;

import carcrowdsystem.ccs.abstracts.Funcionario;

public class GerenteEstacionamento extends Funcionario {
    public GerenteEstacionamento(
        Integer id,
        String nome,
        String rg,
        String cpf,
        String email,
        String telefone,
        String senha
    ) {
        super(id, nome, rg, cpf, email, telefone, senha);
        super.setCargo("Gerente");
    }
}