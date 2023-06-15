package carcrowdsystem.ccs.services.builders;

import carcrowdsystem.ccs.dtos.veiculo.VeiculoRequest;
import carcrowdsystem.ccs.entitys.Veiculo;

public class VeiculoBuilder {
    public VeiculoBuilder(){
    }
    public static VeiculoRequest criarVeiculoRequest() {
        return new VeiculoRequest(
                "AWB2T23","Celta", "Fabiano", "11999222333"
        );
    }

    public static Veiculo criarVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo("Celta");
        veiculo.setPlaca("AWB2T23");
        veiculo.setNomeCliente("Fabiano");
        veiculo.setTelefoneCliente("11999222333");
        return veiculo;
    }

    public static Veiculo criarVeiculo2() {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(1);
        veiculo.setModelo("Civic");
        veiculo.setPlaca("AWB2T24");
        veiculo.setNomeCliente("Joao");
        veiculo.setTelefoneCliente("11999222111");
        return veiculo;
    }
}
