package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.ValorEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ValorEstacionamentoRepository extends JpaRepository<ValorEstacionamento, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM valor_estacionamento " +
                    "WHERE fk_estacionamento = ? " +
                    "ORDER BY id_preco DESC " +
                    "LIMIT 1;"
    )
    ValorEstacionamento findByIdEstacionamento(Integer id);
}