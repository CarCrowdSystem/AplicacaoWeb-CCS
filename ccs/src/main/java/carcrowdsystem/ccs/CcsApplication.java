package carcrowdsystem.ccs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
{
    "nome": "nome",
    "rg": "rg",
    "cpf": "111.222.333-44",
    "email": "email@gmail.com",
    "telefone": "telefonea",
    "senha": "senha"
}
*/

@SpringBootApplication
public class CcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcsApplication.class, args);
	}

}
