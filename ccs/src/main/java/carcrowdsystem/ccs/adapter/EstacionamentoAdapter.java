package carcrowdsystem.ccs.adapter;

import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.Estacionamento;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.response.EstacionamentoAllMobileResponse;
import carcrowdsystem.ccs.services.EstacionamentoService;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EstacionamentoAdapter implements DbAdapter<EstacionamentoDto, Estacionamento>{
    @Autowired
    private EstacionamentoService service;
    @Override
    public EstacionamentoDto create(Estacionamento entrada) throws MyException {
        return service.postEstacionamento(entrada);
    }

    @Override
    public void update(Integer id, Estacionamento entrada) throws MyException {
        service.patchEstacionamento(id, entrada);
    }

    @Override
    public Boolean delete(Integer id) throws MyException {
        return service.deleteEstacionamento(id);
    }

    public Estacionamento getEstacionamentoPorId(Integer id) throws MyException {
        return service.findById(id);
    }

    public List<EstacionamentoDto> getAll() {
        return service.getAllEstacionamento();
    }

    public Estacionamento getEstacionamentoPorCnpj(String cnpj) throws MyException {
        return service.findByCnpj(cnpj);
    }

    public Estacionamento pegarUltimoEstacionamento() {
        return service.findByTop();
    }

    public List<EstacionamentoAllMobileResponse> getAllEstacionamentosMobile() throws IOException, ParseException {
        return service.getAllEstacionamentosMobile();
    }
}
