package carcrowdsystem.ccs.repositorys;

import carcrowdsystem.ccs.entitys.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Query(
        nativeQuery = true,
        value = "SELECT *\n" +
                "FROM reserva\n" +
                "WHERE data_hora_reserva >= (NOW() - INTERVAL 3 HOUR - INTERVAL 5 MINUTE)\n" +
                "AND data_hora_reserva <= (NOW() - INTERVAL 3 HOUR)"
    )
    List<Reserva> pegarReservas();

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM CCS.reserva where fk_estacionamento = ? order by data_hora_reserva asc;"
    )
    List<Reserva> getReservasByIdEstacionamento(Integer idEstacionamento);
}
