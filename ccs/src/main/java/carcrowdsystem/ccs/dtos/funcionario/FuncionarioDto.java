package carcrowdsystem.ccs.dtos.funcionario;

public class FuncionarioDto {
    private Integer idFuncionario;
    private String nome;
    private String cpf;
    private Boolean adm;
    private String email;

    public FuncionarioDto(
        Integer id,
        String nome,
        String cpf,
        Boolean adm,
        String email
    ) {
        this.idFuncionario = id;
        this.nome = nome;
        this.cpf = cpf;
        this.adm = adm;
        this.email = email;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
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
