package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Integer> {
    Optional<Estacionamento> findByCnpj(String cnpj);

    @Query( nativeQuery = true,
            value = "SELECT top(1) * FROM estacionamento order by id_estacionamento desc")
    Estacionamento findTopByOrderByIdEstacionamentoDesc();
}
