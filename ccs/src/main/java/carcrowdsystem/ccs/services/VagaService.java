package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.dtos.vaga.VagaDto;
import carcrowdsystem.ccs.entitys.Estacionamento;
import carcrowdsystem.ccs.entitys.Historico;
import carcrowdsystem.ccs.entitys.Vaga;
import carcrowdsystem.ccs.entitys.Veiculo;
import carcrowdsystem.ccs.enums.StatusVagaEnum;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.mapper.EstacionamentoMapper;
import carcrowdsystem.ccs.repositorys.HistoricoRepository;
import carcrowdsystem.ccs.repositorys.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {
    private final VagaRepository repository;
    private final HistoricoRepository repositoryHistorico;
    private final EstacionamentoService estacionamentoService;
    private final VeiculoService veiculoService;
    private final EstacionamentoMapper estacionamentoMapper = new EstacionamentoMapper();

    public VagaService(VagaRepository repository, HistoricoRepository repositoryHistorico, EstacionamentoService estacionamentoService, VeiculoService veiculoService) {
        this.repository = repository;
        this.repositoryHistorico = repositoryHistorico;
        this.estacionamentoService = estacionamentoService;
        this.veiculoService = veiculoService;
    }

    public Vaga postVaga(VagaDto novaVaga, Integer id) throws MyException {
        Estacionamento estacionamento =
                estacionamentoService.findById(id);
        Vaga vaga = new Vaga();
        vaga.setAndar(novaVaga.getAndar());
        vaga.setNumero(novaVaga.getNumero());
        vaga.setEstacionamento(
                estacionamento
        );
        vaga = repository.save(vaga);
        Veiculo veiculoFantasma = veiculoService.findById(4);
        Historico historico = new Historico();
        historico.setVaga(vaga);
        historico.setStatusRegistro(StatusVagaEnum.Saida);
        historico.setValorPago(00.00);
        historico.setVeiculo(veiculoFantasma);
        repositoryHistorico.save(historico);
        return vaga;
    }

    public List<Vaga> getVagas() {
        return repository.findAll();
    }

    public void deleteVaga(Integer id) {
        repository.deleteById(id);
    }

    public Vaga findById(Integer idVaga) throws MyException {
        return repository.findById(idVaga).orElseThrow(
            () -> new MyException(404, "Vaga não existe", "V-001")
        );
    }
}
