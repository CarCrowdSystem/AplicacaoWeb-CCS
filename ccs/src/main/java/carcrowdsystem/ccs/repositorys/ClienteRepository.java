package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String username);
}
