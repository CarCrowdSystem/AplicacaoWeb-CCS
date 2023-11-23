package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.dtos.historico.CheckoutSemanalResponse;
import carcrowdsystem.ccs.entitys.Estacionamento;
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
                    "AND DATE(momento_registro) = DATE(DATE_SUB(NOW(), INTERVAL 3 HOUR)) " +
                    "AND status_registro = '1';"
    )
    Integer pegarTotalCheckoutDiario(Integer idEstacionamento);

    @Query(
        nativeQuery = true,
        value = "SELECT DATE(momento_registro) AS data,\n" +
                "       COUNT(id_historico) AS totalCheckouts\n" +
                "FROM historico\n" +
                "WHERE fk_vaga IN (\n" +
                "    SELECT id_vaga\n" +
                "    FROM vaga\n" +
                "    WHERE fk_estacionamento = ?\n" +
                ")\n" +
                "AND DATE(momento_registro) >= DATE(DATE_SUB(NOW(), INTERVAL 7 DAY))\n" +
                "AND DATE(momento_registro) <= DATE(NOW())\n" +
                "AND status_registro = '1'\n" +
                "GROUP BY DATE(momento_registro)\n" +
                "ORDER BY DATE(momento_registro) DESC limit 7;"
    )
    List<Object[]> pegarTotalCheckoutSemanal(Integer id);

    @Query(
            nativeQuery = true,
            value = "SELECT SUM(valor_pago) AS total_pago " +
                    "FROM historico " +
                    "WHERE fk_vaga IN (SELECT id_vaga FROM vaga WHERE fk_estacionamento = ?) " +
                    "AND DATE (momento_registro) = " +
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
            value = "SELECT \n" +
                    "    CAST(\n" +
                    "        IF(diferenca_horas = 1, ve.primeira_hora,\n" +
                    "           IF((diferenca_horas - 1) * ve.hora_adicional + ve.primeira_hora > ve.diaria, ve.diaria,\n" +
                    "              (diferenca_horas - 1) * ve.hora_adicional + ve.primeira_hora)\n" +
                    "        ) AS DECIMAL(10, 2)\n" +
                    "    ) AS valor_calculado\n" +
                    "FROM (\n" +
                    "    SELECT CEIL(TIMESTAMPDIFF(SECOND, h2.momento_registro, h1.momento_registro) / 3600) AS diferenca_horas\n" +
                    "    FROM (\n" +
                    "        SELECT *\n" +
                    "        FROM historico\n" +
                    "        WHERE fk_veiculo = ?\n" +
                    "        ORDER BY momento_registro DESC\n" +
                    "        LIMIT 2\n" +
                    "    ) AS h1\n" +
                    "    JOIN (\n" +
                    "        SELECT *\n" +
                    "        FROM historico\n" +
                    "        WHERE fk_veiculo = ?\n" +
                    "        ORDER BY momento_registro DESC\n" +
                    "        LIMIT 2\n" +
                    "    ) AS h2\n" +
                    "    ORDER BY h1.momento_registro DESC\n" +
                    "    LIMIT 1\n" +
                    ") AS diff\n" +
                    "JOIN valor_estacionamento AS ve\n" +
                    "ON ve.fk_estacionamento = ? limit 1;")
    Double calculaPreco(Integer id, Integer id2, Integer idEstacionamento);

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

    @Query(nativeQuery = true,
            value = "SELECT v.fk_estacionamento FROM historico h\n" +
                    "JOIN vaga v ON h.fk_vaga = v.id_vaga where v.id_vaga = ? limit 1;"
    )
    Integer getIdEstacionamento(Integer idVaga);

    @Query(
            nativeQuery = true,
            value = "select * from estacionamento where id_estacionamento = ?"
    )
    Estacionamento findEstacionamentoById(Integer idEstacionamento);
}