package carcrowdsystem.ccs.dtos.veiculo;

public class VeiculoRequest {
    private String placa;
    private String modelo;
    private String nomeCliente;
    private String telefoneCliente;

    public VeiculoRequest(String placa, String modelo, String nomeCliente, String telefoneCliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.nomeCliente = nomeCliente;
        this.telefoneCliente = telefoneCliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }
}
