package carcrowdsystem.ccs.entitys;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.models.FuncionarioEstacionamento;
import carcrowdsystem.ccs.models.GerenteEstacionamento;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name = "ID_INIT_100", sequenceName = "FUNCIONARIO_SEQ", initialValue = 100)
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ID_INIT_100")
    private Integer id;
    @NotBlank
    @Size(min = 3)
    private String nome;
    private String rg;
    private String cpf;
    @Email
    private String email;
    @Size(min = 9, max = 11)
    private String telefone;
    private String senha;
    private String cargo = "Funcionario";
//    private LocalDateTime dthInicio;
//    private LocalDateTime dthFinal;
    private Boolean logado = false;

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

    public Boolean getLogado() {
        return logado;
    }

    public void setLogado(Boolean logado) {
        this.logado = logado;
    }

//    public LocalDateTime getInicioExpediente() {
//        return dthInicio;
//    }
//
//    public LocalDateTime getFinalExpediente() {
//        return dthFinal;
//    }

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
