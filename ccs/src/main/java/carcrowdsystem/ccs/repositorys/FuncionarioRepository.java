package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {
    Optional<FuncionarioEntity> findByEmail(String username);
//
//    @Override
//    @Query(value = "insert into funcionario " +
//            "(cpf, email, fk_estacionamento, foto, login_habilitado, nome_funcionario, senha, usuario_adm) " +
//            "values (?, ?, ?, ?, ?, ?, ?, ?)")
//    <S extends FuncionarioEntity> S save(S entity);
}
