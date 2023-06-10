package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<EstacionamentoEntity, Integer> {
    Optional<EstacionamentoEntity> findByCnpj(String cnpj);

}
