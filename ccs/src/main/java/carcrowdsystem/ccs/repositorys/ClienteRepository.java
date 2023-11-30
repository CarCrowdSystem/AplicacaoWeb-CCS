package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String username);

    @Query(
            nativeQuery = true,
            value = """
                    (SELECT e.nome_estacionamento nome, e.cep, h.momento_registro, h.valor_pago valor,
                                h.status_registro status, ve.placa
                    FROM historico h
                        JOIN vaga v ON h.fk_vaga = v.id_vaga
                        JOIN estacionamento e ON v.fk_estacionamento = e.id_estacionamento
                        JOIN veiculo ve ON h.fk_veiculo = ve.id_veiculo
                        JOIN cliente c ON ve.fk_cliente = c.id_cliente
                            WHERE c.id_cliente = ?
                            AND h.id_historico = (SELECT MAX(id_historico) FROM historico WHERE fk_veiculo = ve.id_veiculo)
                            AND h.status_registro = 2)
                        UNION ALL
                    (SELECT e.nome_estacionamento nome, e.cep, h.momento_registro, h.valor_pago valor,
                                h.status_registro status, ve.placa
                    FROM historico h
                        JOIN vaga v ON h.fk_vaga = v.id_vaga
                        JOIN estacionamento e ON v.fk_estacionamento = e.id_estacionamento
                        JOIN veiculo ve ON h.fk_veiculo = ve.id_veiculo
                        JOIN cliente c ON ve.fk_cliente = c.id_cliente
                            WHERE c.id_cliente = ?
                            AND h.status_registro != 3
                            AND h.status_registro != 2
                        ORDER BY momento_registro DESC)
                        ORDER BY momento_registro DESC
                        LIMIT 10;
                    """
    )
    List<Object[]> getAllHistoricoByIdCliente(Integer id, Integer id2);

    @Query(
        nativeQuery = true,
        value = """
                SELECT
                    e.nome_estacionamento AS nome,
                    e.cep,
                    h.momento_registro,
                    h.valor_pago AS valor,
                    h.status_registro AS status,
                    ve.placa,
                    r.id_reserva
                FROM
                    historico h
                    JOIN vaga v ON h.fk_vaga = v.id_vaga
                    JOIN estacionamento e ON v.fk_estacionamento = e.id_estacionamento
                    JOIN veiculo ve ON h.fk_veiculo = ve.id_veiculo
                    JOIN cliente c ON ve.fk_cliente = c.id_cliente
                    JOIN reserva r ON h.id_historico = r.fk_historico
                WHERE
                    c.id_cliente = ?
                ORDER BY
                    momento_registro DESC;
                """
    )
        List<Object[]> getAllReservasByIdCliente(Integer id);
}
