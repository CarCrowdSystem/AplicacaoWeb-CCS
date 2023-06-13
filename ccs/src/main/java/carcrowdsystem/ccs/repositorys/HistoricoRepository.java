package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
    @Query(
        nativeQuery = true,
        value = "SELECT distinct fk_veiculo, * from HISTORICO" +
        " order by status_registro  desc limit (SELECT count(distinct fk_veiculo) from HISTORICO)"
    )
    List<Historico> pegarMomento();

    @Query(
        nativeQuery = true,
        value = "SELECT top(1) * from historico where fk_veiculo = ? order by id_historico desc"
    )
    Historico pegarMomentoByIdVeiculo(Integer idVeiculo);

    @Query(
            nativeQuery = true,
            value = "SELECT h.*\n" +
                    "FROM historico h\n" +
                    "INNER JOIN (\n" +
                    "    SELECT fk_veiculo, MAX(momento_registro) AS ultimo_registro\n" +
                    "    FROM historico\n" +
                    "    WHERE fk_vaga IN (\n" +
                    "        SELECT id_vaga\n" +
                    "        FROM vaga\n" +
                    "        WHERE fk_estacionamento = ?\n" +
                    "    )\n" +
                    "    GROUP BY fk_veiculo\n" +
                    ") t ON h.fk_veiculo = t.fk_veiculo AND h.momento_registro = t.ultimo_registro;"
    )
    List<Historico> pegarMomentoByIdEstacionamento(Integer idEstacionamento);
}

// SELECT h.MOMENTO_REGISTRO, STATUS_REGISTRO, VALOR_PAGO, VAGA_ID, VEICULO_ID FROM HISTORICO_ENTITY h
//INNER JOIN VAGA_ENTITY v ON
//h.VAGA_ID = v.ID

// SELECT h.MOMENTO_REGISTRO, STATUS_REGISTRO, VALOR_PAGO, VAGA_ID, VEICULO_ID FROM HISTORICO_ENTITY h
//INNER JOIN VAGA_ENTITY v ON
//h.VAGA_ID = v.ID
//WHERE v.ESTACIONAMENTO_ID = 2
