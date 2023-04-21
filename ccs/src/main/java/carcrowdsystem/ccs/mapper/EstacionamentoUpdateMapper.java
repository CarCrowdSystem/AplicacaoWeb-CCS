//package carcrowdsystem.ccs.mapper;
//
//import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
//import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
//import org.mapstruct.BeanMapping;
//import org.mapstruct.Mapper;
//import org.mapstruct.MappingTarget;
//import org.mapstruct.NullValuePropertyMappingStrategy;
//
//@Mapper(componentModel = "spring")
//public interface EstacionamentoUpdateMapper {
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    public void updateEstacionamentoEntityFromEntity(
//            EstacionamentoEntity novoEstacionamento,
//        @MappingTarget EstacionamentoEntity antigoEstacionamento
//    );
//}
