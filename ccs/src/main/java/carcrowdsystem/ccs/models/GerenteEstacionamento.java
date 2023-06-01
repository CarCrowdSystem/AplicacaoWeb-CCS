package carcrowdsystem.ccs.models;

import carcrowdsystem.ccs.abstracts.Funcionario;

public class GerenteEstacionamento extends Funcionario {
    public GerenteEstacionamento(
        Integer id,
        String nome,
        String cpf,
        String email,
        String senha
    ) {
        super(id, nome,cpf, email, senha);
        super.setAdm(true);
    }
}