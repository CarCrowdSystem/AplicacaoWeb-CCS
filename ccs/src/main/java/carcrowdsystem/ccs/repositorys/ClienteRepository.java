package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
