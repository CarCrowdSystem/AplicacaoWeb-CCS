package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    Optional<Veiculo> findByPlacaEqualsIgnoreCase(String placa);
}
