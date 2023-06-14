package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
    @Query(
        nativeQuery = true,
        value = "SELECT h.*\n" +
                "FROM (\n" +
                "    SELECT *,\n" +
                "           ROW_NUMBER() OVER (PARTITION BY fk_veiculo ORDER BY momento_registro DESC) AS rn\n" +
                "    FROM HISTORICO\n" +
                ") AS h\n" +
                "WHERE h.rn = 1;"
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


    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(id_historico)\n" +
                    "FROM historico\n" +
                    "WHERE fk_vaga IN (\n" +
                    "    SELECT id_vaga\n" +
                    "    FROM vaga\n" +
                    "    WHERE fk_estacionamento = ?\n" +
                    ")\n" +
                    "AND CONVERT(DATE, momento_registro) = CONVERT(DATE, DATEADD(HOUR, -3, GETDATE()))\n" +
                    "AND status_registro = '1';"
    )
    Integer pegarTotalCheckoutDiario(Integer idEstacionamento);

    @Query(
            nativeQuery = true,
            value = "SELECT SUM(valor_pago) AS lucro_do_dia\n" +
                    "FROM historico\n" +
                    "WHERE fk_vaga IN (\n" +
                    "    SELECT id_vaga\n" +
                    "    FROM vaga\n" +
                    "    WHERE fk_estacionamento = ?\n" +
                    ")\n" +
                    "AND CONVERT(DATE, momento_registro) = CONVERT(DATE, DATEADD(HOUR, -3, GETDATE()));"
    )
    Double pegarTotalFaturamentoDiario(Integer idEstacionamento);

    @Query(nativeQuery = true,
    value = "SELECT h.*\n" +
            "FROM historico h\n" +
            "INNER JOIN (\n" +
            "    SELECT fk_vaga, MAX(momento_registro) AS ultimo_registro\n" +
            "    FROM historico\n" +
            "    INNER JOIN vaga ON historico.fk_vaga = vaga.id_vaga\n" +
            "    WHERE vaga.fk_estacionamento = ?\n" +
            "    GROUP BY fk_vaga\n" +
            ") t ON h.fk_vaga = t.fk_vaga AND h.momento_registro = t.ultimo_registro;")
    List<Historico> pegarMomentoVagasByEstacionamento(Integer id);

    @Query(nativeQuery = true,
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
            ") t ON h.fk_veiculo = t.fk_veiculo AND h.momento_registro = t.ultimo_registro\n" +
            "WHERE h.status_registro = '0';")
    List<Historico> pegarCheckouts(Integer id);

    @Query(nativeQuery = true,
    value = "SELECT CASE\n" +
            "    WHEN horas_decorridas * valor_estacionamento.hora_adicional + valor_estacionamento.primeira_hora > valor_estacionamento.diaria THEN valor_estacionamento.diaria\n" +
            "    ELSE horas_decorridas * valor_estacionamento.hora_adicional + valor_estacionamento.primeira_hora\n" +
            "    END AS valor_2_horas\n" +
            "FROM (\n" +
            "    SELECT TOP 1 \n" +
            "        CEILING(ABS(DATEDIFF(MINUTE, h.momento_registro, GETDATE())) / 60.0) AS horas_decorridas\n" +
            "    FROM historico AS h\n" +
            "    WHERE h.id_historico = ?1\n" +
            "    ORDER BY h.id_historico DESC\n" +
            ") AS subquery\n" +
            "CROSS APPLY (\n" +
            "    SELECT TOP 1 ve.hora_adicional, ve.primeira_hora, ve.diaria\n" +
            "    FROM valor_estacionamento AS ve\n" +
            "    JOIN vaga AS v ON ve.fk_estacionamento = v.fk_estacionamento\n" +
            "    JOIN historico AS h ON v.id_vaga = h.fk_vaga\n" +
            "    WHERE h.id_historico = ?1\n" +
            "    ORDER BY ve.id_preco DESC\n" +
            ") AS valor_estacionamento")
    Double calculaPreco(Integer id);

    @Query(nativeQuery = true,
    value = "SELECT TOP 25 *\n" +
            "FROM historico\n" +
            "WHERE fk_vaga IN (\n" +
            "    SELECT id_vaga\n" +
            "    FROM vaga\n" +
            "    WHERE fk_estacionamento = ?\n" +
            ")\n" +
            "ORDER BY momento_registro DESC;")
    List<Historico> pegarDadosHistoricoByIdEstacionamento(Integer id);
}
