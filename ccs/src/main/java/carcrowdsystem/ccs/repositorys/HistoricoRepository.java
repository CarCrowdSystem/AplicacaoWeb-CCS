package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
    @Query(
            nativeQuery = true,
            value = "SELECT h.* " +
                    "FROM ( " +
                    "    SELECT *, " +
                    "           ROW_NUMBER() OVER (PARTITION BY fk_veiculo ORDER BY momento_registro DESC) AS rn " +
                    "    FROM historico " +
                    ") AS h " +
                    "WHERE h.rn = 1;"
    )
    List<Historico> pegarMomento();

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM historico WHERE fk_veiculo = ? ORDER BY id_historico DESC LIMIT 1"
    )
    Historico pegarMomentoByIdVeiculo(Integer idVeiculo);

    @Query(
            nativeQuery = true,
            value = "SELECT h.* " +
                    "FROM historico h " +
                    "INNER JOIN ( " +
                    "    SELECT fk_veiculo, MAX(id_historico) AS ultimo_registro " +
                    "    FROM historico " +
                    "    WHERE fk_vaga IN ( " +
                    "        SELECT id_vaga " +
                    "        FROM vaga " +
                    "        WHERE fk_estacionamento = ? " +
                    "    ) " +
                    "    GROUP BY fk_veiculo " +
                    ") t ON h.fk_veiculo = t.fk_veiculo AND h.id_historico = t.ultimo_registro;"
    )
    List<Historico> pegarMomentoByIdEstacionamento(Integer idEstacionamento);

    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(id_historico) " +
                    "FROM historico " +
                    "WHERE fk_vaga IN ( " +
                    "    SELECT id_vaga " +
                    "    FROM vaga " +
                    "    WHERE fk_estacionamento = ? " +
                    ") " +
                    "AND DATE(momento_registro) = CURDATE() " +
                    "AND status_registro = '1';"
    )
    Integer pegarTotalCheckoutDiario(Integer idEstacionamento);

    @Query(
            nativeQuery = true,
            value = "SELECT SUM(valor_pago) AS total_pago\n" +
                    "FROM historico\n" +
                    "WHERE fk_vaga IN (SELECT id_vaga FROM vaga WHERE fk_estacionamento = ?)\n" +
                    "      AND DATE(CONVERT_TZ(momento_registro, '+00:00', '-03:00')) = " +
                    "DATE(DATE_SUB(NOW(), INTERVAL 3 HOUR));"
    )
    Double pegarTotalFaturamentoDiario(Integer idEstacionamento);

    @Query(nativeQuery = true,
            value = "SELECT h.* " +
                    "FROM historico h " +
                    "INNER JOIN ( " +
                    "    SELECT fk_vaga, MAX(momento_registro) AS ultimo_registro " +
                    "    FROM historico " +
                    "    INNER JOIN vaga ON historico.fk_vaga = vaga.id_vaga " +
                    "    WHERE vaga.fk_estacionamento = ? " +
                    "    GROUP BY fk_vaga " +
                    ") t ON h.fk_vaga = t.fk_vaga AND h.momento_registro = t.ultimo_registro;")
    List<Historico> pegarMomentoVagasByEstacionamento(Integer id);

    @Query(nativeQuery = true,
            value = "SELECT h.* " +
                    "FROM historico h " +
                    "INNER JOIN ( " +
                    "    SELECT fk_veiculo, MAX(momento_registro) AS ultimo_registro " +
                    "    FROM historico " +
                    "    WHERE fk_vaga IN ( " +
                    "        SELECT id_vaga " +
                    "        FROM vaga " +
                    "        WHERE fk_estacionamento = ? " +
                    "    ) " +
                    "    GROUP BY fk_veiculo " +
                    ") t ON h.fk_veiculo = t.fk_veiculo AND h.momento_registro = t.ultimo_registro " +
                    "WHERE h.status_registro = '0';")
    List<Historico> pegarCheckouts(Integer id);

    @Query(nativeQuery = true,
            value = "SELECT CASE " +
                    "    WHEN horas_decorridas * ve.hora_adicional + ve.primeira_hora > ve.diaria THEN ve.diaria " +
                    "    ELSE horas_decorridas * ve.hora_adicional + ve.primeira_hora " +
                    "    END AS valor_2_horas " +
                    "FROM ( " +
                    "    SELECT * " +
                    "    FROM ( " +
                    "        SELECT " +
                    "            CEILING(ABS(TIMESTAMPDIFF(MINUTE, h.momento_registro, NOW())) / 60.0) AS horas_decorridas " +
                    "        FROM historico AS h " +
                    "        WHERE h.id_historico = ?1 " +
                    "        ORDER BY h.id_historico DESC " +
                    "        LIMIT 1 " +
                    "    ) AS subquery, " +
                    "    ( " +
                    "        SELECT " +
                    "            ve.hora_adicional, ve.primeira_hora, ve.diaria " +
                    "        FROM valor_estacionamento AS ve " +
                    "        JOIN vaga AS v ON ve.fk_estacionamento = v.fk_estacionamento " +
                    "        JOIN historico AS h ON v.id_vaga = h.fk_vaga " +
                    "        WHERE h.id_historico = ?1 " +
                    "        ORDER BY ve.id_preco DESC " +
                    "        LIMIT 1 " +
                    "    ) AS ve " +
                    ") AS result;")
    Double calculaPreco(Integer id);

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM historico " +
                    "WHERE fk_vaga IN ( " +
                    "    SELECT id_vaga " +
                    "    FROM vaga " +
                    "    WHERE fk_estacionamento = ? " +
                    ") " +
                    "ORDER BY momento_registro DESC " +
                    "LIMIT 25;")
    List<Historico> pegarDadosHistoricoByIdEstacionamento(Integer id);
}