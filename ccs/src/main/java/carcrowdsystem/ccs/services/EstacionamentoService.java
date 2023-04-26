package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.mapper.EstacionamentoMapper;
//import carcrowdsystem.ccs.mapper.EstacionamentoUpdateMapper;
import carcrowdsystem.ccs.repositorys.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionamentoService {
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    private EstacionamentoMapper estacionamentoMapper = new EstacionamentoMapper();

    public EstacionamentoDto postEstacionamento(EstacionamentoEntity estacionamento) throws MyException {
        try {
            estacionamentoRepository.save(estacionamento);
        } catch (Exception e){
            throw new MyException(
                400,
                "Nome deve ter pelo menos 3 caracteres; " +
                "Formato do Cep 00000-000; " +
                "O numeroEndereco não pode ser: null, '' ou ' '; " +
                "Formato do telefone 0000-0000",
                "E-002");
        }
        return estacionamentoMapper.toEstacionamentoDto(estacionamento);
    }

    public List<EstacionamentoDto> getAllEstacionamento() {
        return toListDto(estacionamentoRepository.findAll());
    }

    public void patchEstacionamento(Integer id, EstacionamentoEntity estacionamento) {
        Optional<EstacionamentoEntity> estacionamentoAntigo = estacionamentoRepository.findById(id);
        if (estacionamentoAntigo.isEmpty()){
            estacionamentoRepository.save(estacionamento);
            return;
        }
        if(estacionamento.getNomeEstacionamento() != null){
            estacionamentoAntigo.get().setNomeEstacionamento(estacionamento.getNomeEstacionamento());
        }
        if(estacionamento.getCep() != null){
            estacionamentoAntigo.get().setCep(estacionamento.getCep());
        }
        if(estacionamento.getTelefone() != null){
            estacionamentoAntigo.get().setTelefone(estacionamento.getTelefone());
        }
        if(estacionamento.getNumeroEndereco() != null){
            estacionamentoAntigo.get().setNumeroEndereco(estacionamento.getNumeroEndereco());
        }
        estacionamentoRepository.save(estacionamentoAntigo.get());
    }

    public Boolean deleteEstacionamento(Integer id) {
        Optional<EstacionamentoEntity> estacionamento = estacionamentoRepository.findById(id);
        if (estacionamento.isEmpty()){
            return false;
        }
        estacionamento.get().setStatusEstacionamento(false);
        estacionamentoRepository.save(estacionamento.get());
        return true;
    }

    private List<EstacionamentoDto> toListDto(List<EstacionamentoEntity> estacionamentos){
        List<EstacionamentoDto> estacionamentoDtos = new ArrayList<>();
        for (EstacionamentoEntity e: estacionamentos){
            estacionamentoDtos.add(estacionamentoMapper.toEstacionamentoDto(e));
        }
        return estacionamentoDtos;
    }
}
