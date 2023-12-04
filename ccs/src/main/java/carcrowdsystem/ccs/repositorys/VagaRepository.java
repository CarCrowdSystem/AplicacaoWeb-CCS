package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Integer> {
    @Query(
        nativeQuery = true,
        value = "WITH HistoricoNumerado AS (\n" +
                "  SELECT\n" +
                "    h.*,\n" +
                "    ROW_NUMBER() OVER (PARTITION BY h.fk_vaga ORDER BY h.momento_registro DESC) AS NumeroLinha\n" +
                "  FROM historico h\n" +
                "  WHERE h.fk_vaga IN (SELECT id_vaga FROM vaga WHERE fk_estacionamento = ? AND status_registro != 3)\n" +
                ")\n" +
                "SELECT\n" +
                "  h.fk_vaga\n" +
                "FROM HistoricoNumerado h\n" +
                "WHERE h.NumeroLinha = 1 AND h.status_registro = 1\n" +
                "LIMIT 1;"
    )
    Optional<Integer> pegarIdVagaLivreByIdEstacionamento(Integer idEstacionamento);
}
