package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Funcionario;
import carcrowdsystem.ccs.entitys.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByEmail(String username);

    @Query(nativeQuery = true,
            value = "SELECT f.nome_funcionario as nome, f.email as email, f.senha as senha\n" +
                    "FROM funcionario f\n" +
                    "WHERE f.email = :username " +
                    "UNION " +
                    "SELECT c.nome as nome, c.email as email, c.senha as senha\n" +
                    "FROM cliente c\n" +
                    "WHERE c.email = :username"
    )
    Optional<Login> findByEmailLogin(String username);

    @Query(nativeQuery = true,
            value = "select * from funcionario where fk_estacionamento = ? and login_habilitado = 1"
    )
    List<Funcionario> findAllByIdEstacionamento(Integer id);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM funcionario WHERE fk_estacionamento = :id " +
                    "AND login_habilitado = true AND nome_funcionario LIKE %:nome%"
    )
    List<Funcionario> pegarFuncPorLikeNome(@Param("id") Integer id, @Param("nome") String nome);

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM funcionario where login_habilitado = 1;"
    )
    List<Funcionario> findAllHabilitado();

//
//    @Override
//    @Query(value = "insert into funcionario " +
//            "(cpf, email, fk_estacionamento, foto, login_habilitado, nome_funcionario, senha, usuario_adm) " +
//            "values (?, ?, ?, ?, ?, ?, ?, ?)")
//    <S extends FuncionarioEntity> S save(S entity);
}
