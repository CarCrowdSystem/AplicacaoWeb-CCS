package carcrowdsystem.ccs.entitys;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@SequenceGenerator(name = "ID_INIT_100", sequenceName = "FUNCIONARIO_SEQ", initialValue = 100)
public class EstacionamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ID_INIT_100")
    private Integer id;
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

    public Boolean getStatusEstacionamento() {
        return statusEstacionamento;
    }

    public void setStatusEstacionamento(Boolean statusEstacionamento) {
        this.statusEstacionamento = statusEstacionamento;
    }
}
