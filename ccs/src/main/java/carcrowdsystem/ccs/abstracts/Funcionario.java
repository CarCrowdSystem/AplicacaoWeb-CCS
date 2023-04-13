package carcrowdsystem.ccs.abstracts;

import carcrowdsystem.ccs.Entitys.FuncionarioEntity;
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

public abstract class Funcionario {
    private Integer id;
    private String nome;
    private String email;
    private String rg;
    private String cpf;
    private String cargo;
    private String senha;
    private String telefone;
//    private LocalDateTime dthInicio;
//    private LocalDateTime dthFinal;
//    private Boolean logado;


    public Funcionario(String nome, String rg, String cpf, String email, String telefone, String senha) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cargo = null;
//        this.dthInicio = null;
//        this.dthFinal = null;
//        this.logado = false;
    }
//    public void iniciarExpediente() {
//        dthInicio = LocalDateTime.now();
//        System.out.println("Começou o expediente as " + dthInicio);
//    }
//
//    public void finalizarExpediente() {
//        dthFinal = LocalDateTime.now();
//        System.out.println(
//            "Começou o expediente as " + dthInicio +
//            "\nE terminou as " + dthFinal
//        );
//        dthInicio = null;
//        dthFinal = null;
//    }

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

//    public Boolean getLogado() {
//        return logado;
//    }
//
//    public void setLogado(Boolean logado) {
//        this.logado = logado;
//    }
//
//    public LocalDateTime getInicioExpediente() {
//        return dthInicio;
//    }
//
//    public LocalDateTime getFinalExpediente() {
//        return dthFinal;
//    }

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