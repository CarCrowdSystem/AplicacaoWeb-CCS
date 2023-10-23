package carcrowdsystem.ccs.response;

import carcrowdsystem.ccs.entitys.Cliente;

public class ClienteResponse {
    private Integer id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;

    public ClienteResponse(Cliente cli) {
        id = cli.getId();
        nome = cli.getNome();
        email = cli.getEmail();
        telefone = cli.getTelefone();
        cpf = cli.getCpf();
    }

    public void clienteToClienteResponse(Cliente cli) {
        id = cli.getId();
        nome = cli.getNome();
        email = cli.getEmail();
        telefone = cli.getTelefone();
        cpf = cli.getCpf();
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
