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

    @Query(
            nativeQuery = true,
            value = "SELECT distinct veiculo_id, * FROM " +
                    "(SELECT h.ID, h.MOMENTO_REGISTRO, h.STATUS_REGISTRO, h.VALOR_PAGO, h.VAGA_ID, h.VEICULO_ID FROM HISTORICO_ENTITY as h " +
                    "INNER JOIN VAGA_ENTITY as v ON " +
                    "h.VAGA_ID = v.ID " +
                    "WHERE v.ESTACIONAMENTO_ID = ?1) " +
                    "order by MOMENTO_REGISTRO desc limit (SELECT count(distinct (veiculo_id, vaga_id)) from (SELECT h2.MOMENTO_REGISTRO, h2.STATUS_REGISTRO, h2.VALOR_PAGO, h2.VAGA_ID, h2.VEICULO_ID FROM HISTORICO_ENTITY as h2 " +
                    "INNER JOIN VAGA_ENTITY as v2 ON " +
                    "h2.VAGA_ID = v2.ID " +
                    "WHERE v2.ESTACIONAMENTO_ID = ?1))"
    )
    List<HistoricoEntity> pegarMomentoByIdEstacionamento(Integer idEstacionamento);
}

// SELECT h.MOMENTO_REGISTRO, STATUS_REGISTRO, VALOR_PAGO, VAGA_ID, VEICULO_ID FROM HISTORICO_ENTITY h
//INNER JOIN VAGA_ENTITY v ON
//h.VAGA_ID = v.ID

// SELECT h.MOMENTO_REGISTRO, STATUS_REGISTRO, VALOR_PAGO, VAGA_ID, VEICULO_ID FROM HISTORICO_ENTITY h
//INNER JOIN VAGA_ENTITY v ON
//h.VAGA_ID = v.ID
//WHERE v.ESTACIONAMENTO_ID = 2