package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.Entitys.FuncionarioEntity;
import carcrowdsystem.ccs.dtos.FuncionarioDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {
    public List<FuncionarioDto> findAllToFuncionarioDto();
}
