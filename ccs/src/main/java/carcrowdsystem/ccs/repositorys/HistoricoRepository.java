package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.HistoricoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<HistoricoEntity, Integer> {
    @Query(
        nativeQuery = true,
        value = "SELECT distinct veiculo_id, * from HISTORICO_ENTITY" +
        " order by STATUS_REGISTRO  desc limit (SELECT count(distinct veiculo_id) from HISTORICO_ENTITY)"
    )
    List<HistoricoEntity> pegarMomento();

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM HISTORICO_ENTITY where veiculo_id = ? order by id desc limit 1"
    )
    HistoricoEntity pegarMomentoByIdVeiculo(Integer idVeiculo);
}
