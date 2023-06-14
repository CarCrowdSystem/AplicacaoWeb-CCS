package carcrowdsystem.ccs.response;

import carcrowdsystem.ccs.enums.StatusVagaEnum;

public class MomentoVagasResponse {
    private Integer idVaga;
    private Integer numero;
    private String andar;
    private StatusVagaEnum statusRegistro;

    public Integer getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Integer idVaga) {
        this.idVaga = idVaga;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public StatusVagaEnum getStatusRegistro() {
        return statusRegistro;
    }

    public void setStatusRegistro(StatusVagaEnum statusRegistro) {
        this.statusRegistro = statusRegistro;
    }
}
