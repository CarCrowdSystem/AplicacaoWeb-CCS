package carcrowdsystem.ccs.mapper;

import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.EstacionamentoEntity;

public class EstacionamentoMapper {
    public EstacionamentoDto toEstacionamentoDto(EstacionamentoEntity estacionamento){
        EstacionamentoDto estacionamentoDto = new EstacionamentoDto();
        estacionamentoDto.setNomeEstacionamento(estacionamento.getNomeEstacionamento());
        estacionamentoDto.setTelefone(estacionamento.getTelefone());
        estacionamentoDto.setCep(estacionamento.getCep());
        estacionamentoDto.setNumeroEndereco(estacionamento.getNumeroEndereco());

        return estacionamentoDto;
    }

    public EstacionamentoEntity toEstacionamentoEntity(EstacionamentoDto estacionamento){
        EstacionamentoEntity estacionamentoEntity = new EstacionamentoEntity();
        estacionamentoEntity.setNomeEstacionamento(estacionamento.getNomeEstacionamento());
        estacionamentoEntity.setTelefone(estacionamento.getTelefone());
        estacionamentoEntity.setCep(estacionamento.getCep());
        estacionamentoEntity.setNumeroEndereco(estacionamento.getNumeroEndereco());

        return estacionamentoEntity;
    }
}
