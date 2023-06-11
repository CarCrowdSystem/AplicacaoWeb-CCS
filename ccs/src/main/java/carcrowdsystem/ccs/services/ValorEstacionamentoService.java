package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.dtos.valorEstacionamento.ValorEstacionamentoDto;
import carcrowdsystem.ccs.entitys.Estacionamento;
import carcrowdsystem.ccs.entitys.ValorEstacionamento;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.ValorEstacionamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValorEstacionamentoService {
    private final ValorEstacionamentoRepository repository;
    private final EstacionamentoService estacionamentoService;

    public ValorEstacionamentoService(ValorEstacionamentoRepository valorEstacionamentoRepository, EstacionamentoService estacionamentoService) {
        this.repository= valorEstacionamentoRepository;
        this.estacionamentoService = estacionamentoService;
    }

    public List<ValorEstacionamento> findAll() {
        return repository.findAll();
    }

    public void postValor(
        ValorEstacionamentoDto valorEstacionamentoDto,
        Integer idEstacionamento
    ) throws MyException {
        Estacionamento estacionamento =
                estacionamentoService.findById(idEstacionamento);
        ValorEstacionamento valor = new ValorEstacionamento();
        valor.setDiaria(valorEstacionamentoDto.getDiaria());
        valor.setHoraAdicional(valorEstacionamentoDto.getHoraAdicional());
        valor.setPrimeiraHora(valorEstacionamentoDto.getPrimeiraHora());
        valor.setEstacionamento(estacionamento);

        repository.save(valor);
    }
}
