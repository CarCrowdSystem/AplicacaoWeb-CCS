package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.vaga.VagaDto;
import carcrowdsystem.ccs.entitys.Historico;
import carcrowdsystem.ccs.entitys.Vaga;
import carcrowdsystem.ccs.entitys.Veiculo;
import carcrowdsystem.ccs.enums.StatusVagaEnum;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.request.CadastroInicialRequest;
import carcrowdsystem.ccs.request.EstacionamentoRequest;
import carcrowdsystem.ccs.request.FuncionarioRequest;
import carcrowdsystem.ccs.request.dtos.VagaDtoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${uri.dev}/distrubuicao")
public class DistribuicaoController {

    private final EstacionamentoController estacionamentoController;
    private final VagaController vagaController;
    private final HistoricoController historicoController;
    private final FuncionarioController funcionarioController;
    private final VeiculoController veiculoController;

    public DistribuicaoController(EstacionamentoController estacionamentoController, VagaController vagaController, HistoricoController historicoController, FuncionarioController funcionarioController, VeiculoController veiculoController) {
        this.estacionamentoController = estacionamentoController;
        this.vagaController = vagaController;
        this.historicoController = historicoController;
        this.funcionarioController = funcionarioController;
        this.veiculoController = veiculoController;
    }

    @PostMapping
    public ResponseEntity CadastroInicial (
            @RequestBody CadastroInicialRequest cad
    ) throws MyException {
        try {
            EstacionamentoRequest estacionamento = new EstacionamentoRequest();
            estacionamento.setCepEmpresa(cad.getCepEmpresa());
            estacionamento.setCnpjEmpresa(cad.getCnpjEmpresa());
            estacionamento.setNomeEmpresa(cad.getNomeEmpresa());
            estacionamento.setEnderecoEmpresa(cad.getEnderecoEmpresa());
            estacionamento.setTelefoneEmpresa(cad.getTelefoneEmpresa());
            estacionamentoController.postEstacionamento(estacionamento);
        } catch (Exception e){
            throw new MyException(404, "Erro ao cadastrar Estacionamento", "D-001");
        }
        Integer idEstacionamento =
                estacionamentoController.pegarUltimoEstacionamento().getId();
        try {
            FuncionarioRequest funcionario = new FuncionarioRequest(
                cad.getNomeUsuario(),
                cad.getEmailUsuario(),
                cad.getCpfUsuario(),
                cad.getSenha(),
                true,
                idEstacionamento
            );
            funcionarioController.postUsuario(funcionario);
        } catch (Exception e){
            throw new MyException(404, "Erro ao cadastrar Funcionario", "D-002");
        }
        try {
            Veiculo veiculoFantasma = veiculoController.getVeiculoById(4).getBody();

            for (int i = 0; i < cad.getVagas().size(); i++){
                VagaDtoRequest vaga = cad.getVagas().get(i);
                for (int j = 1; j <= vaga.getQtdVagas(); j++){
                    VagaDto novaVaga = new VagaDto(j, vaga.getAndarVaga());

                    Vaga vagaSave = vagaController.postVaga(novaVaga, idEstacionamento).getBody();

                    Historico historico = new Historico();
                    historico.setVaga(vagaSave);
                    historico.setStatusRegistro(StatusVagaEnum.Saida);
                    historico.setValorPago(00.00);
                    historico.setVeiculo(veiculoFantasma);
                    historicoController.gerarHistoricoInicial(historico);
                }
            }
        } catch (Exception e){
            throw new MyException(404, "Erro ao cadastrar Vaga", "D-003");
        }

        return ResponseEntity.ok().build();
    }
}
