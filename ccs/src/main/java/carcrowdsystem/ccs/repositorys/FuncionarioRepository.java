package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByEmail(String username);

    @Query(nativeQuery = true,
            value = "select * from funcionario where fk_estacionamento = ? and login_habilitado = 'True'"
    )
    List<Funcionario> findAllByIdEstacionamento(Integer id);
//
//    @Override
//    @Query(value = "insert into funcionario " +
//            "(cpf, email, fk_estacionamento, foto, login_habilitado, nome_funcionario, senha, usuario_adm) " +
//            "values (?, ?, ?, ?, ?, ?, ?, ?)")
//    <S extends FuncionarioEntity> S save(S entity);
}
