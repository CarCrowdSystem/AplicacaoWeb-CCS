package carcrowdsystem.ccs.dtos;

import carcrowdsystem.ccs.models.FuncionarioEstacionamento;
import carcrowdsystem.ccs.models.GerenteEstacionamento;

import java.time.LocalDateTime;

public class FuncionarioDto {
    private String nome;
    private String rg;
    private String cpf;
    private String cargo;
    private String email;
    private String telefone;
    private String senha;
    private LocalDateTime dthInicio = null;
    private LocalDateTime dthFinal = null;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public GerenteEstacionamento toGerente(){
        return new GerenteEstacionamento(
                nome,
                rg,
                cpf,
                "Gerente",
                email,
                telefone,
                senha
        );
    }

    public FuncionarioEstacionamento toFuncionario(){
        return new FuncionarioEstacionamento(
                nome,
                rg,
                cpf,
                "Func",
                email,
                telefone,
                senha
        );
    }
}
