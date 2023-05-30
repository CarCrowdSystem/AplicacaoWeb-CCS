package carcrowdsystem.ccs.entitys;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.models.FuncionarioEstacionamento;
import carcrowdsystem.ccs.models.GerenteEstacionamento;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Blob;

@Entity
@SequenceGenerator(name = "ID_INIT_100", sequenceName = "FUNCIONARIO_SEQ", initialValue = 100)
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ID_INIT_100")
    private Integer id;
    @NotBlank
    @Size(min = 3)
    private String nome;
    private Blob foto;
    @Email
    private String email;
    private String rg;
    private String cpf;
    private String senha;
    @Size(min = 9, max = 11)
    private String telefone;
    private Boolean loginHabilitado = false;
    private Boolean adm = false;
    @ManyToOne
    private EstacionamentoEntity estacionamento;

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
                adm,
                email,
                telefone
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
