package carcrowdsystem.ccs.response.dtos;

import carcrowdsystem.ccs.enums.StatusVagaEnum;

public class UltimoHistoricoVagaDtoResponse {
    private Integer vaga;
    private String andar;
    private StatusVagaEnum status;

    public UltimoHistoricoVagaDtoResponse(Integer vaga, String andar, StatusVagaEnum status) {
        this.vaga = vaga;
        this.andar = andar;
        this.status = status;
    }

    public Integer getVaga() {
        return vaga;
    }

    public void setVaga(Integer vaga) {
        this.vaga = vaga;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public StatusVagaEnum getStatus() {
        return status;
    }

    public void setStatus(StatusVagaEnum status) {
        this.status = status;
    }
}
