package carcrowdsystem.ccs.entitys;

import carcrowdsystem.ccs.enums.StatusVeiculo;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Integer id;
    private String placa;
    private String marca;
    private String modelo;
    private StatusVeiculo ativo = StatusVeiculo.Ativo;
    @OneToMany
    private List<Historico> historico;
    @OneToMany
    private List<Reserva> reservas;
    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Historico> getHistorico() {
        return historico;
    }

    public void setHistorico(List<Historico> historico) {
        this.historico = historico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusVeiculo getAtivo() {
        return ativo;
    }

    public void setAtivo(StatusVeiculo ativo) {
        this.ativo = ativo;
    }
}
