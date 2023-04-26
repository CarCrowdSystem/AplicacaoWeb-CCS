package carcrowdsystem.ccs;

import carcrowdsystem.ccs.models.EnderecoEstacionamento;
import carcrowdsystem.ccs.services.ViaCepService;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.core5.http.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/*
Post func
{
    "nome": "nome",
    "rg": "rg",
    "cpf": "111.222.333-44",
    "email": "email@gmail.com",
    "telefone": "telefonea",
    "senha": "senha"
}

Post estacionamento
{
	"nomeEstacionamento": "estacionamento",
	"cep": "00000-000",
	"numeroEndereco": "9",
	"telefone": "2222-2222"
}
*/

@SpringBootApplication
public class CcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcsApplication.class, args);
	}
}
