package carcrowdsystem.ccs.abstracts;

import java.time.LocalDateTime;

public abstract class Funcionario {
    private String nome;
    private String rg;
    private String cpf;
    private String cargo;
    private String email;
    private String telefone;
    private String senha;
    private LocalDateTime dthInicio = null;
    private LocalDateTime dthFinal = null;

    public Funcionario(String nome, String rg, String cpf, String cargo, String email, String telefone, String senha) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.cargo = cargo;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public void iniciarExpediente() {
        dthInicio = LocalDateTime.now();
        System.out.println("Começou o expediente as " + dthInicio);
    }

    public void finalizarExpediente() {
        dthFinal = LocalDateTime.now();
        System.out.println(
            "Começou o expediente as " + dthInicio +
            "\nE terminou as " + dthFinal
        );
        dthInicio = null;
        dthFinal = null;
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
