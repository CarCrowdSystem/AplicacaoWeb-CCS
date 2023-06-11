package carcrowdsystem.ccs.mapper;

import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.Estacionamento;
import carcrowdsystem.ccs.exception.MyException;

public class EstacionamentoMapper {
    public EstacionamentoDto toEstacionamentoDto(Estacionamento estacionamento){
        EstacionamentoDto estacionamentoDto = new EstacionamentoDto();
        estacionamentoDto.setNomeEstacionamento(estacionamento.getNomeEstacionamento());
        estacionamentoDto.setTelefone(estacionamento.getTelefone());
        estacionamentoDto.setCep(estacionamento.getCep());
        estacionamentoDto.setNumeroEndereco(estacionamento.getNumeroEndereco());
        estacionamentoDto.setCnpj(estacionamento.getNumeroEndereco());

        return estacionamentoDto;
    }

    public Estacionamento toEstacionamentoEntity(EstacionamentoDto estacionamento) throws MyException {
        try{
            Estacionamento estacionamentoEntity = new Estacionamento();
            estacionamentoEntity.setNomeEstacionamento(estacionamento.getNomeEstacionamento());
            estacionamentoEntity.setTelefone(estacionamento.getTelefone());
            estacionamentoEntity.setCep(estacionamento.getCep());
            estacionamentoEntity.setNumeroEndereco(estacionamento.getNumeroEndereco());

            return estacionamentoEntity;
        }catch (Exception e){
            throw new MyException(e.hashCode(), e.getMessage(), "EM001");
        }
    }
}
