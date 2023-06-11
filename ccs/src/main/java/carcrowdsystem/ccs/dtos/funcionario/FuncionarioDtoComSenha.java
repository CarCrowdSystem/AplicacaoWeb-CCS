package carcrowdsystem.ccs.dtos.funcionario;

import carcrowdsystem.ccs.entitys.FuncionarioEntity;

public class FuncionarioDtoComSenha{
    private String nome;
    private String rg;
    private String cpf;
    private Boolean adm;
    private String email;
    private String senha;
    private String telefone;

    public FuncionarioEntity toFuncionarioEntity(){
        FuncionarioEntity func = new FuncionarioEntity();
        func.setNome(nome);
        func.setCpf(cpf);
        func.setUsuarioAdm(adm);
        func.setEmail(email);
        func.setSenha(senha);

        return func;
    }

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

    public Boolean getAdm() {
        return adm;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
