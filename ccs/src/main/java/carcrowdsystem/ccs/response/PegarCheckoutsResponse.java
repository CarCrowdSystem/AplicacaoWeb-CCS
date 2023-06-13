package carcrowdsystem.ccs.response;

public class PegarCheckoutsResponse {
    private String nome;
    private String telefone;
    private String andar;
    private Integer vaga;
    private Double valor;
    private Integer fkVaga;
    private Integer fkVeiculo;

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

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public Integer getVaga() {
        return vaga;
    }

    public void setVaga(Integer vaga) {
        this.vaga = vaga;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getFkVaga() {
        return fkVaga;
    }

    public void setFkVaga(Integer fkVaga) {
        this.fkVaga = fkVaga;
    }

    public Integer getFkVeiculo() {
        return fkVeiculo;
    }

    public void setFkVeiculo(Integer fkVeiculo) {
        this.fkVeiculo = fkVeiculo;
    }
}
