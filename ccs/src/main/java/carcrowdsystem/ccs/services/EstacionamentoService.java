package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
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
//    @Autowired
//    private EstacionamentoUpdateMapper mapper;
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    private EstacionamentoMapper estacionamentoMapper = new EstacionamentoMapper();

    public EstacionamentoDto create(EstacionamentoEntity estacionamento) {
        estacionamentoRepository.save(estacionamento);
        return estacionamentoMapper.toEstacionamentoDto(estacionamento);
    }

    public List<EstacionamentoDto> getAllEstacionamento() {
        return toListDto(estacionamentoRepository.findAll());
    }

    public void patchEstacionamento(int id, EstacionamentoEntity estacionamento) {
        Optional<EstacionamentoEntity> estacionamentoAntigo = estacionamentoRepository.findById(id);
        if (estacionamentoAntigo.isEmpty()){
            estacionamentoRepository.save(estacionamento);
        }
        if(estacionamento.getNomeEstacionamento() != null){
            estacionamentoAntigo.get().setNomeEstacionamento(estacionamento.getNomeEstacionamento());
        } else if(estacionamento.getCep() != null){
            estacionamentoAntigo.get().setCep(estacionamento.getCep());
        } else if(estacionamento.getTelefone() != null){
            estacionamentoAntigo.get().setTelefone(estacionamento.getTelefone());
        } else if(estacionamento.getNumeroEndereco() != null){
            estacionamentoAntigo.get().setNumeroEndereco(estacionamento.getNumeroEndereco());
        }
        estacionamentoRepository.save(estacionamentoAntigo.get());
    }

    private List<EstacionamentoDto> toListDto(List<EstacionamentoEntity> estacionamentos){
        List<EstacionamentoDto> estacionamentoDtos = new ArrayList<>();
        for (EstacionamentoEntity e: estacionamentos){
            estacionamentoDtos.add(estacionamentoMapper.toEstacionamentoDto(e));
        }
        return estacionamentoDtos;
    }
}
