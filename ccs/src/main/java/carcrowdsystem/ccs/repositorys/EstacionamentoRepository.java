package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends JpaRepository<EstacionamentoEntity, Integer> {
}
