package carcrowdsystem.ccs.dtos.veiculo;

public class VeiculoMobileResponse {
    private String placa;
    private String marca;
    private String modelo;
    private Integer id_veiculo;

    public VeiculoMobileResponse(String placa, String marca, String modelo, Integer id_veiculo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.id_veiculo = id_veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(Integer id_veiculo) {
        this.id_veiculo = id_veiculo;
    }
}
