package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.dtos.vaga.VagaDto;
import carcrowdsystem.ccs.entitys.Estacionamento;
import carcrowdsystem.ccs.entitys.Vaga;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.mapper.EstacionamentoMapper;
import carcrowdsystem.ccs.repositorys.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {
    private final VagaRepository repository;
    private final EstacionamentoService estacionamentoService;
    private final EstacionamentoMapper estacionamentoMapper = new EstacionamentoMapper();

    public VagaService(VagaRepository repository, EstacionamentoService estacionamentoService) {
        this.repository = repository;
        this.estacionamentoService = estacionamentoService;
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
        return repository.save(vaga);
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
