package carcrowdsystem.ccs.dtos.funcionario;

public class FuncionarioDto {
    private String nome;
    private String cpf;
    private Boolean adm;
    private String email;

    public FuncionarioDto(
        String nome,
        String cpf,
        Boolean adm,
        String email
    ) {
        this.nome = nome;
        this.cpf = cpf;
        this.adm = adm;
        this.email = email;
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
}
