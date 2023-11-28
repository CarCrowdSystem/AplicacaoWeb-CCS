package carcrowdsystem.ccs.dtos.veiculo;

public class VeiculoRequest {
    private String nomeCliente;
    private String email;
    private String senha;
    private String marca;
    private String modelo;
    private String placa;

    public VeiculoRequest(
            String nomeCliente, String email,
            String senha, String modelo, String marca, String placa
    ) {
        this.nomeCliente = nomeCliente;
        this.email = email;
        this.senha = senha;
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
