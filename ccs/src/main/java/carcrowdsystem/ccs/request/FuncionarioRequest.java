package carcrowdsystem.ccs.request;

import jakarta.validation.constraints.Email;

import java.sql.Blob;

public class FuncionarioRequest {
    private String nomeUsuario;
    private Blob foto = null;
    @Email
    private String emailUsuario;
    private String cpfUsuario;
    private String senha;
    private Boolean adm = false;
    private Integer idEstacionamento;

    public FuncionarioRequest(String nomeUsuario, String emailUsuario, String cpfUsuario, String senha, Boolean adm , Integer idEstacionamento) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.cpfUsuario = cpfUsuario;
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

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Blob getFoto() {
        return foto;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
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
