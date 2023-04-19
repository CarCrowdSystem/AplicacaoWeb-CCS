package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionamentoRepository extends JpaRepository<EstacionamentoEntity, Integer> {
}
