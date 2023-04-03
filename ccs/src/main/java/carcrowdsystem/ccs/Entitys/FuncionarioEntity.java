package carcrowdsystem.ccs.Entitys;

import carcrowdsystem.ccs.abstracts.Funcionario;
import carcrowdsystem.ccs.dtos.FuncionarioDto;
import carcrowdsystem.ccs.models.FuncionarioEstacionamento;
import carcrowdsystem.ccs.models.GerenteEstacionamento;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class FuncionarioEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @Size(min = 3)
    private String nome;
    private String rg;
    @CPF
    private String cpf;
    @Email
    private String email;
    @Size(min = 9, max = 11)
    private String telefone;
    private String senha;
    private String cargo;
    private LocalDateTime dthInicio;
    private LocalDateTime dthFinal;
    private Boolean logado;

    public FuncionarioEntity(Funcionario fun) {
        nome = fun.getNome();
        rg = fun.getSenha();
        cpf = fun.getCpf();
        email = fun.getEmail();
        telefone = fun.getTelefone();
        senha = fun.getSenha();
        cargo = fun.getCargo();
        dthInicio = fun.getInicioExpediente();
        dthFinal = fun.getFinalExpediente();
        logado = fun.getLogado();
    }

    public FuncionarioEstacionamento toFuncionario(){
        return new FuncionarioEstacionamento(
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

    public Boolean getLogado() {
        return logado;
    }

    public void setLogado(Boolean logado) {
        this.logado = logado;
    }

    public LocalDateTime getInicioExpediente() {
        return dthInicio;
    }

    public LocalDateTime getFinalExpediente() {
        return dthFinal;
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
