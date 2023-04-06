package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.Entitys.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {
//    public List<FuncionarioDto> findAllToFuncionarioDto();
}
