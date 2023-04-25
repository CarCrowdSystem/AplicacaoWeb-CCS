package carcrowdsystem.ccs.adapter;

import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionamentoAdapter implements DbAdapter<EstacionamentoDto, EstacionamentoEntity>{
    @Autowired
    private EstacionamentoService service;
    @Override
    public EstacionamentoDto create(EstacionamentoEntity entrada) throws MyException {
        return service.postEstacionamento(entrada);
    }

    @Override
    public void update(Integer id, EstacionamentoEntity entrada) throws MyException {
        service.patchEstacionamento(id, entrada);
    }

    @Override
    public Boolean delete(Integer id) throws MyException {
        return service.deleteEstacionamento(id);
    }

    public List<EstacionamentoDto> getAll() {
        return service.getAllEstacionamento();
    }
}
