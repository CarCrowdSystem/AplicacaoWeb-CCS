package carcrowdsystem.ccs.dtos.funcionario;

import carcrowdsystem.ccs.entitys.FuncionarioEntity;

public class FuncionarioDtoComSenha{
    private String nome;
    private String rg;
    private String cpf;
    private String cargo;
    private String email;
    private String senha;
    private String telefone;

    public FuncionarioEntity toFuncionarioEntity(){
        FuncionarioEntity func = new FuncionarioEntity();
        func.setNome(nome);
        func.setRg(rg);
        func.setCpf(cpf);
        func.setCargo(cargo);
        func.setEmail(email);
        func.setTelefone(telefone);
        func.setSenha(senha);

        return func;
    }
    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
}
