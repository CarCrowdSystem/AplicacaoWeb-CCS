package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.HistoricoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<HistoricoEntity, Integer> {
    @Query(
        nativeQuery = true,
        value = "SELECT distinct fk_veiculo, * from HISTORICO" +
        " order by status_registro  desc limit (SELECT count(distinct fk_veiculo) from HISTORICO)"
    )
    List<HistoricoEntity> pegarMomento();

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM HISTORICO_ENTITY where fk_veiculo = ? order by id desc limit 1"
    )
    HistoricoEntity pegarMomentoByIdVeiculo(Integer idVeiculo);

    @Query(
            nativeQuery = true,
            value = "SELECT DISTINCT fk_veiculo, h.*\n" +
                    "FROM (\n" +
                    "    SELECT h.id_historico, h.momento_registro, h.status_registro, h.valor_pago, h.fk_vaga, h.fk_veiculo\n" +
                    "    FROM historico AS h\n" +
                    "    INNER JOIN vaga AS v ON h.fk_vaga = v.id_vaga\n" +
                    "    WHERE v.fk_estacionamento = 3\n" +
                    ") AS h\n" +
                    "ORDER BY momento_registro DESC\n" +
                    "OFFSET 0 ROWS\n" +
                    "FETCH NEXT (\n" +
                    "    SELECT COUNT(DISTINCT fk_veiculo + fk_vaga)\n" +
                    "    FROM (\n" +
                    "        SELECT h2.momento_registro, h2.status_registro, h2.valor_pago, h2.fk_vaga, h2.fk_veiculo\n" +
                    "        FROM historico AS h2\n" +
                    "        INNER JOIN vaga AS v2 ON h2.fk_vaga = v2.id_vaga\n" +
                    "        WHERE v2.fk_estacionamento = 3\n" +
                    "    ) AS subquery\n" +
                    ") ROWS ONLY"
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