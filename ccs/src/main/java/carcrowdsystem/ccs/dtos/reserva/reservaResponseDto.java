package carcrowdsystem.ccs.dtos.reserva;

public class reservaResponseDto {
    private Integer id;
    private String nome;
    private String data;
    private String hora;
    private String telefone;
    private String modelo;
    private String marca;

    public reservaResponseDto(Integer id, String nome, String data, String hora, String telefone, String modelo, String marca) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.telefone = telefone;
        this.modelo = modelo;
        this.marca = marca;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
}
