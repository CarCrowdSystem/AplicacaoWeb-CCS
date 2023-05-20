package carcrowdsystem.ccs.dtos.funcionario;

public class FuncionarioDto {
    private String nome;
    private String rg;
    private String cpf;
    private Boolean adm;
    private String email;
    private String telefone;

    public FuncionarioDto(
        String nome,
        String rg,
        String cpf,
        Boolean adm,
        String email,
        String telefone
    ) {
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.adm = adm;
        this.email = email;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
