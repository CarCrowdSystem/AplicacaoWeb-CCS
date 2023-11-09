package carcrowdsystem.ccs.response;

public class EstacionamentoAllMobileResponse {
    private String id;
    private String nome;
    private String endereco;
    private String cep;
    private String total_vaga;
    private String vagas_cheias;
    private String diaria;
    private String primeira_hora;
    private String hora_adicional;

    public EstacionamentoAllMobileResponse(
        String id, String nome, String endereco, String cep, String total_vaga,
        String vagas_cheias, String diaria, String primeira_hora, String hora_adicional
    ) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.total_vaga = total_vaga;
        this.vagas_cheias = vagas_cheias;
        this.diaria = diaria;
        this.primeira_hora = primeira_hora;
        this.hora_adicional = hora_adicional;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTotal_vaga() {
        return total_vaga;
    }

    public void setTotal_vaga(String total_vaga) {
        this.total_vaga = total_vaga;
    }

    public String getVagas_cheias() {
        return vagas_cheias;
    }

    public void setVagas_cheias(String vagas_cheias) {
        this.vagas_cheias = vagas_cheias;
    }

    public String getDiaria() {
        return diaria;
    }

    public void setDiaria(String diaria) {
        this.diaria = diaria;
    }

    public String getPrimeira_hora() {
        return primeira_hora;
    }

    public void setPrimeira_hora(String primeira_hora) {
        this.primeira_hora = primeira_hora;
    }

    public String getHora_adicional() {
        return hora_adicional;
    }

    public void setHora_adicional(String hora_adicional) {
        this.hora_adicional = hora_adicional;
    }
}
