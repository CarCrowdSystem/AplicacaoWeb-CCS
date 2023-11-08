package carcrowdsystem.ccs.dtos.cliente;

public class ClienteHistoricoResponse {
    private String nome;
    private String rua;
    private String data;
    private String hora;
    private String valor;
    private String status;

    public ClienteHistoricoResponse(String nome, String rua, String data, String hora, String valor, String status) {
        this.nome = nome;
        this.rua = rua;
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
