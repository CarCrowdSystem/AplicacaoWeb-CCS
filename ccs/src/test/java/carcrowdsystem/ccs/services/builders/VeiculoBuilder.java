package carcrowdsystem.ccs.services.builders;

import carcrowdsystem.ccs.dtos.veiculo.VeiculoRequest;
import carcrowdsystem.ccs.entitys.Veiculo;

public class VeiculoBuilder {
    public VeiculoBuilder(){
    }
    public static VeiculoRequest criarVeiculoRequest() {
        return new VeiculoRequest(
                "AWB2T23","Uno", "Fiat"
        );
    }

    public static Veiculo criarVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setModelo("Uno");
        veiculo.setPlaca("AWB2T23");
        veiculo.setMarca("Fiat");
        return veiculo;
    }

    public static Veiculo criarVeiculo2() {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(1);
        veiculo.setModelo("Civic");
        veiculo.setPlaca("AWB2T24");
        veiculo.setMarca("Honda");
        return veiculo;
    }
}
