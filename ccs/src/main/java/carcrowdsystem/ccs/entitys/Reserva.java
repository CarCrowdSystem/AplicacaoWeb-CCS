package carcrowdsystem.ccs.entitys;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Integer id;
    @Column(name = "data_hora_reserva")
    private LocalDateTime dataHoraReserva;
    @ManyToOne
    @JoinColumn(name = "fk_estacionamento")
    private Estacionamento estacionamento;
    @ManyToOne
    @JoinColumn(name = "fk_veiculo")
    private Veiculo veiculo;

    public Reserva(LocalDateTime dataHoraReserva, Estacionamento estacionamento, Veiculo veiculo) {
        this.dataHoraReserva = dataHoraReserva;
        this.estacionamento = estacionamento;
        this.veiculo = veiculo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraReserva() {
        return dataHoraReserva;
    }

    public void setDataHoraReserva(LocalDateTime dataHoraReserva) {
        this.dataHoraReserva = dataHoraReserva;
    }

    public carcrowdsystem.ccs.entitys.Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
