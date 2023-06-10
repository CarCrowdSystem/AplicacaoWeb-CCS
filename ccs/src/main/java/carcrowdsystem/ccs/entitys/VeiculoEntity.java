package carcrowdsystem.ccs.entitys;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "checkin_veiculo")
public class VeiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_checkin")
    private Integer id;
    private String placa;
    private String modelo;
    @Column(name = "nome_cliente")
    private String nomeCliente;
    @Column(name = "telefone_cliente")
    private String telefoneCliente;
    @OneToMany
    private List<HistoricoEntity> historico;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<HistoricoEntity> getHistorico() {
        return historico;
    }

    public void setHistorico(List<HistoricoEntity> historico) {
        this.historico = historico;
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
