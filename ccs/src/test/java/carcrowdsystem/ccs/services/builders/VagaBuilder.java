package carcrowdsystem.ccs.services.builders;

import carcrowdsystem.ccs.dtos.veiculo.VeiculoRequest;
import carcrowdsystem.ccs.entitys.Vaga;
import carcrowdsystem.ccs.entitys.Veiculo;

public class VagaBuilder {
    public VagaBuilder(){
    }
    public static Vaga criarVagaRequest() {
        Vaga vaga = new Vaga();
        vaga.setAndar("1");
        vaga.setNumero(2);
        vaga.setId(1);
        return vaga;
    }
}
