package carcrowdsystem.ccs.dtos;

public class VagaDto {
    private Integer numero;
    private Integer andar;

    public VagaDto(Integer numero, Integer andar) {
        this.numero = numero;
        this.andar = andar;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }
}
