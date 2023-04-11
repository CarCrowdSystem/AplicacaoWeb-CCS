package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.Entitys.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {

}
