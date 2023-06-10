package carcrowdsystem.ccs.request;

import jakarta.validation.constraints.Email;

import java.sql.Blob;

public class FuncionarioRequest {
    private String nome;
    private Blob foto = null;
    @Email
    private String email;
    private String cpf;
    private String senha;
    private Boolean adm = false;
    private Integer idEstacionamento;

    public FuncionarioRequest(String nome, String email, String cpf, String senha, Boolean adm , Integer idEstacionamento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.adm = adm;
        this.idEstacionamento = idEstacionamento;
    }

    public Boolean getAdm() {
        return adm;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdEstacionamento() {
        return idEstacionamento;
    }

    public void setIdEstacionamento(Integer idEstacionamento) {
        this.idEstacionamento = idEstacionamento;
    }
}
