package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.ValorEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ValorEstacionamentoRepository extends JpaRepository<ValorEstacionamento, Integer> {
    @Query(nativeQuery = true,
    value = "SELECT TOP 1 * \n" +
            "FROM valor_estacionamento\n" +
            "WHERE fk_estacionamento = ?\n" +
            "ORDER BY id_preco DESC;"
    )
    ValorEstacionamento findByIdEstacionamento(Integer id);
}
