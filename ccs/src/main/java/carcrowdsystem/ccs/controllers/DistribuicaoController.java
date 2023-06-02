package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.vaga.VagaDto;
import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.request.CadastroInicialRequest;
import carcrowdsystem.ccs.request.dtos.VagaDtoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${uri.dev}/distrubuicao")
public class DistribuicaoController {

    private final EstacionamentoController estacionamentoController;
    private final VagaController vagaController;
    private final FuncionarioController funcionarioController;

    public DistribuicaoController(EstacionamentoController estacionamentoController, VagaController vagaController, FuncionarioController funcionarioController) {
        this.estacionamentoController = estacionamentoController;
        this.vagaController = vagaController;
        this.funcionarioController = funcionarioController;
    }

    public ResponseEntity CadastroInicial (
            @RequestBody CadastroInicialRequest cad
    ) throws MyException {
        EstacionamentoEntity estacionamento = new EstacionamentoEntity();
        estacionamento.setCep(cad.getCepEmpresa());
        estacionamento.setCnpj(cad.getCnpjEmpresa());
        estacionamento.setNomeEstacionamento(cad.getNomeEmpresa());
        estacionamento.setNumeroEndereco(cad.getEnderecoEmpresa());
        estacionamento.setTelefone(cad.getTelefoneEmpresa());
        estacionamentoController.postEstacionamento(estacionamento);

        Integer idEstacionamento =
                estacionamentoController.getEstacionamentoPorCnpj(cad.getCnpjEmpresa()).getBody().getId();



        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setCpf(cad.getCpfUsuario());
        funcionario.setNome(cad.getNomeUsuario());
        funcionario.setEmail(cad.getEmailUsuario());
        funcionario.setSenha(cad.getSenha());
        funcionarioController.postUsuario(idEstacionamento, funcionario, true);



        for (int i = 0; i < cad.getVagas().size(); i++){
            VagaDtoRequest vaga = cad.getVagas().get(i);
            for (int j = 1; j <= vaga.getQtdVagas(); j++){
                VagaDto novaVaga = new VagaDto(j, vaga.getAndarVaga());

                vagaController.salvarVaga(novaVaga, idEstacionamento);
            }
        }

        return ResponseEntity.ok().build();

    }
}
