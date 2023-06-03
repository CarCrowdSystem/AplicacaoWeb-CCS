package carcrowdsystem.ccs.dtos.vaga;

public class VagaDto {
    private Integer numero;
    private String andar;

    public VagaDto(Integer numero, String andar) {
        this.numero = numero;
        this.andar = andar;
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
}
