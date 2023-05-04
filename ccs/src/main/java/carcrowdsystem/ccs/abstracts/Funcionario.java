package carcrowdsystem.ccs.abstracts;

import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.models.FuncionarioEstacionamento;
import carcrowdsystem.ccs.models.GerenteEstacionamento;

public abstract class Funcionario {
    private Integer id;
    private String nome;
    private String email;
    private String rg;
    private String cpf;
    private String cargo;
    private String senha;
    private String telefone;

    public Funcionario(Integer id, String nome, String rg, String cpf, String email, String telefone, String senha) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cargo = null;
    }

    public FuncionarioEstacionamento toFuncionario(){
        return new FuncionarioEstacionamento(
                id,
                nome,
                rg,
                cpf,
                email,
                telefone,
                senha
        );
    }

    public GerenteEstacionamento toGerente(){
        return new GerenteEstacionamento(
                id,
                nome,
                rg,
                cpf,
                email,
                telefone,
                senha
        );
    }

    public FuncionarioDto toFuncionarioDto(){
        return new FuncionarioDto(
                nome,
                rg,
                cpf,
                cargo,
                email,
                telefone
        );
    }

    public FuncionarioEntity toFuncionarioEntity(){
        FuncionarioEntity func = new FuncionarioEntity();
        func.setNome(nome);
        func.setRg(rg);
        func.setCpf(cpf);
        func.setAdm(cargo);
        func.setEmail(email);
        func.setTelefone(telefone);
        func.setSenha(senha);

        return func;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
