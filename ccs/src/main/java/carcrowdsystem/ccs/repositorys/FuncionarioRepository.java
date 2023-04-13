package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.Entitys.FuncionarioEntity;
import carcrowdsystem.ccs.abstracts.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {
    Optional<Funcionario> findByEmail(String username);
}
