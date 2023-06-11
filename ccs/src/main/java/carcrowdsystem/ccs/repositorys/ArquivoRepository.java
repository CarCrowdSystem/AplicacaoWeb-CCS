package carcrowdsystem.ccs.repositorys;


import carcrowdsystem.ccs.entitys.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {
}
