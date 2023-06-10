package carcrowdsystem.ccs.entitys;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
//@SequenceGenerator(name = "ID_INIT_100", sequenceName = "FUNCIONARIO_SEQ", initialValue = 100)
// generator = "ID_INIT_100"
public class EstacionamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 14, max = 14)
    private String cnpj;
    @NotBlank
    @Size(min = 3)
    private String nomeEstacionamento;
    @NotBlank
    private String cep;
    @NotBlank
    private String numeroEndereco;
    private String telefone;
    @OneToMany
    private List<FuncionarioEntity> funcionarios;
    @OneToMany
    private List<VagaEntity> vagas;
    @OneToMany
    private List<ValorEstacionamentoEntity> valorEstacionamento;
    private Boolean statusEstacionamento = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeEstacionamento() {
        return nomeEstacionamento;
    }

    public void setNomeEstacionamento(String nomeEstacionamento) {
        this.nomeEstacionamento = nomeEstacionamento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<FuncionarioEntity> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<FuncionarioEntity> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<VagaEntity> getVagas() {
        return vagas;
    }

    public void setVagas(List<VagaEntity> vagas) {
        this.vagas = vagas;
    }

    public List<ValorEstacionamentoEntity> getValorEstacionamento() {
        return valorEstacionamento;
    }

    public void setValorEstacionamento(List<ValorEstacionamentoEntity> valorEstacionamento) {
        this.valorEstacionamento = valorEstacionamento;
    }

    public Boolean getStatusEstacionamento() {
        return statusEstacionamento;
    }

    public void setStatusEstacionamento(Boolean statusEstacionamento) {
        this.statusEstacionamento = statusEstacionamento;
    }
}
