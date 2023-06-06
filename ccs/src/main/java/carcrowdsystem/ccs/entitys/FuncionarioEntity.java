package carcrowdsystem.ccs.entitys;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.models.FuncionarioEstacionamento;
import carcrowdsystem.ccs.models.GerenteEstacionamento;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.sql.Blob;

@Entity
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Integer id;
    @NotBlank
    @Size(min = 3)
    @Column(name = "nome_funcionario")
    private String nome;
    private Blob foto = null;
    @Email
    private String email;
    private String cpf;
    private String senha;
    @Column(name = "login_Habilitado")
    private Boolean loginHabilitado = false;
    @Column(name = "usuario_adm")
    private Boolean adm = false;
    @ManyToOne
    private EstacionamentoEntity estacionamento;

    public FuncionarioEstacionamento toFuncionario(){
        return new FuncionarioEstacionamento(
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

    public Boolean getLoginHabilitado() {
        return loginHabilitado;
    }

    public void setLoginHabilitado(Boolean loginHabilitado) {
        this.loginHabilitado = loginHabilitado;
    }

    public Boolean getAdm() {
        return adm;
    }

    public void setAdm(Boolean adm) {
        this.adm = adm;
    }

    public EstacionamentoEntity getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(EstacionamentoEntity estacionamento) {
        this.estacionamento = estacionamento;
    }

}
