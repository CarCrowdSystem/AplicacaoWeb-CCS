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
            value = "(SELECT e.nome_estacionamento nome, e.cep, h.momento_registro, h.valor_pago valor, h.status_registro  status\n" +
                    "FROM historico h\n" +
                    "JOIN vaga v ON h.fk_vaga = v.id_vaga\n" +
                    "JOIN estacionamento e ON v.fk_estacionamento = e.id_estacionamento\n" +
                    "JOIN veiculo ve ON h.fk_veiculo = ve.id_veiculo\n" +
                    "JOIN cliente c ON ve.fk_cliente = c.id_cliente\n" +
                    "WHERE c.id_cliente = ?\n" +
                    "  AND h.id_historico = (SELECT MAX(id_historico) FROM historico WHERE fk_veiculo = ve.id_veiculo)\n" +
                    "  AND h.status_registro = 2)\n" +
                    "UNION ALL\n" +
                    "(SELECT e.nome_estacionamento nome, e.cep, h.momento_registro, h.valor_pago valor, h.status_registro status\n" +
                    "FROM historico h\n" +
                    "JOIN vaga v ON h.fk_vaga = v.id_vaga\n" +
                    "JOIN estacionamento e ON v.fk_estacionamento = e.id_estacionamento\n" +
                    "JOIN veiculo ve ON h.fk_veiculo = ve.id_veiculo\n" +
                    "JOIN cliente c ON ve.fk_cliente = c.id_cliente\n" +
                    "WHERE c.id_cliente = ? AND h.status_registro = 1\n" +
                    "ORDER BY momento_registro DESC)\n" +
                    "ORDER BY momento_registro DESC\n" +
                    "Limit 10;"
    )
    List<Object[]> getAllHistoricoByIdCliente(Integer id, Integer id2);
}
