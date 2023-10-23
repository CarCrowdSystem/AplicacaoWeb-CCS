package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDetailsDto;
import carcrowdsystem.ccs.entitys.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByEmail(String username);

    @Query(nativeQuery = true,
            value = "SELECT f.nome_funcionario as nome, f.email, f.senha\n" +
                    "FROM funcionario f\n" +
                    "WHERE f.email = :username " +
                    "UNION " +
                    "SELECT c.nome, c.email, c.senha\n" +
                    "FROM cliente c\n" +
                    "WHERE c.email = :username"
    )
    Optional<FuncionarioDetailsDto> findByEmailLogin(String username);

    @Query(nativeQuery = true,
            value = "select * from funcionario where fk_estacionamento = ? and login_habilitado = 'True'"
    )
    List<Funcionario> findAllByIdEstacionamento(Integer id);

    List<Funcionario> findByNomeContainsIgnoreCase(String nome);

//
//    @Override
//    @Query(value = "insert into funcionario " +
//            "(cpf, email, fk_estacionamento, foto, login_habilitado, nome_funcionario, senha, usuario_adm) " +
//            "values (?, ?, ?, ?, ?, ?, ?, ?)")
//    <S extends FuncionarioEntity> S save(S entity);
}
