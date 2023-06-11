package carcrowdsystem.ccs.abstracts;

import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.models.FuncionarioAbstractEstacionamento;
import carcrowdsystem.ccs.models.GerenteEstacionamento;

import java.sql.Blob;

public abstract class FuncionarioAbstract {
    private Integer id;
    private String nome;
    private Blob foto;
    private String email;
    private String cpf;
    private String senha;
    private Boolean adm;

    public FuncionarioAbstract(Integer id, String nome, String cpf, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.adm = null;
    }

    public FuncionarioAbstractEstacionamento toFuncionario(){
        return new FuncionarioAbstractEstacionamento(
                id,
                nome,
                cpf,
                email,
                senha
        );
    }

    public GerenteEstacionamento toGerente(){
        return new GerenteEstacionamento(
                id,
                nome,
                cpf,
                email,
                senha
        );
    }

    public FuncionarioDto toFuncionarioDto(){
        return new FuncionarioDto(
                nome,
                cpf,
                adm,
                email
        );
    }

    public FuncionarioEntity toFuncionarioEntity(){
        FuncionarioEntity func = new FuncionarioEntity();
        func.setNome(nome);
        func.setCpf(cpf);
        func.setUsuarioAdm(adm);
        func.setEmail(email);
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
}
