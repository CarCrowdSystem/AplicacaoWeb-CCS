package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.dtos.veiculo.VeiculoMobileResponse;
import carcrowdsystem.ccs.entitys.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    Optional<Veiculo> findByPlacaEqualsIgnoreCase(String placa);

    @Query(
            nativeQuery = true,
            value = "Select placa, marca, modelo, id_veiculo from veiculo where fk_cliente = ?"
    )
    List<VeiculoMobileResponse> findAllByIdCliente(Integer idCliente);
}
