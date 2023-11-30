package carcrowdsystem.ccs.dtos.cliente;

public class ClienteHistoricoResponse {
    private String nome;
    private String rua;
    private String data;
    private String hora;
    private String valor;
    private String status;
    private String placa;
    private String idReserva;
    private Boolean isCheckinDone = true;

    public ClienteHistoricoResponse(
            String nome, String rua, String data, String hora,
            String valor, String status, String placa, String idReserva
    ) {
        this.nome = nome;
        this.rua = rua;
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.status = status;
        this.placa = placa;
        this.idReserva = idReserva;
    }

    public ClienteHistoricoResponse(
            String nome, String rua, String data,
            String hora, String valor, String status,
            String placa, String idReserva, Boolean isCheckinDone
    ) {
        this.nome = nome;
        this.rua = rua;
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.status = status;
        this.placa = placa;
        this.idReserva = idReserva;
        this.isCheckinDone = isCheckinDone;
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public Boolean getCheckinDone() {
        return isCheckinDone;
    }

    public void setCheckinDone(Boolean checkinDone) {
        isCheckinDone = checkinDone;
    }
}
