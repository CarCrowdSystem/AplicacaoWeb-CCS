package carcrowdsystem.ccs;

import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Component
public class CcsApplication implements CommandLineRunner {
	private final HistoricoService historicoService;

	@Autowired
	public CcsApplication(HistoricoService historicoService) {
		this.historicoService = historicoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CcsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		Runnable rotina = () -> {
			System.out.println(
					"------------------------------ "
					+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
					+" ------------------------------");
			System.out.println("Rotina sendo executada a cada 5 minutos");
			try {
				historicoService.autorizarReservas();
			} catch (MyException e) {
				throw new RuntimeException(e);
			}
		};

		// Inicia a rotina a cada 5 minutos com um atraso inicial de 0 segundos
		scheduler.scheduleAtFixedRate(rotina, 0, 5, TimeUnit.MINUTES);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Desligando a aplicação. Parando o executor service.");
			scheduler.shutdown();
		}));
	}
}