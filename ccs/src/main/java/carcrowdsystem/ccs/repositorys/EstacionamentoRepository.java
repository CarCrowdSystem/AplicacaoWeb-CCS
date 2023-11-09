package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Integer> {
    Optional<Estacionamento> findByCnpj(String cnpj);

    @Query(
        nativeQuery = true,
        value = "SELECT top(1) * FROM estacionamento order by id_estacionamento desc"
    )
    Estacionamento findTopByOrderByIdEstacionamentoDesc();

    @Query(
        nativeQuery = true,
        value = "SELECT \n" +
                "    e.id_estacionamento,\n" +
                "    e.nome_estacionamento,\n" +
                "    e.cep,\n" +
                "    COALESCE(COUNT(v.id_vaga), 0) AS total_vagas,\n" +
                "    COALESCE(SUM(CASE WHEN h.status_registro = 0 THEN 1 ELSE 0 END), 0) AS vagas_cheias,\n" +
                "    COALESCE(ve.diaria, 0) AS diaria,\n" +
                "    COALESCE(ve.hora_adicional, 0) AS hora_adicional,\n" +
                "    COALESCE(ve.primeira_hora, 0) AS primeira_hora\n" +
                "FROM estacionamento e\n" +
                "LEFT JOIN vaga v ON e.id_estacionamento = v.fk_estacionamento\n" +
                "LEFT JOIN historico h ON v.id_vaga = h.fk_vaga\n" +
                "LEFT JOIN valor_estacionamento ve ON e.id_estacionamento = ve.fk_estacionamento\n" +
                "WHERE h.id_historico = (SELECT MAX(id_historico) FROM historico WHERE fk_vaga = v.id_vaga)\n" +
                "GROUP BY e.id_estacionamento, e.nome_estacionamento, ve.diaria, ve.hora_adicional, ve.primeira_hora;"
    )
    List<Object[]> getAllEstacionamentosMobile();
}
