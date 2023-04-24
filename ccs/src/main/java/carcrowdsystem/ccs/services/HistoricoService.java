package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.entitys.HistoricoEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.HistoricoRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    public List<HistoricoEntity> getAllHistorico(){
        return historicoRepository.findAll();
    }

    public static String gravaArquivoCsv(List<HistoricoEntity> lista) throws MyException {
        FileWriter arq = null;
        Formatter saida = null;
        String nome = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss")) + "-historico.csv";

        // Bloc try catch para abrir o arquivo
        try {
            arq = new FileWriter(nome);
            saida = new Formatter(arq);
        }
        catch (IOException erro){
            System.out.println("Erro ao abrir o arquivo.");
            throw new MyException(400, "Erro ao abrir o arquivo.", "G-003");
        }

        // Bloco try catch para gravar o arquivo
        try {
        saida.format("%-15S;%-10S;%-7S;%-7S;%5S;%-14S;%-10S;%7S;%7S;%10S\n",
                "CLIENTE","MODELO","PLACA","ANDAR","VAGA","TELEFONE","DATA","ENTRADA","SAIDA","VALOR");
            for (HistoricoEntity h: lista){
                saida.format("%-15S;%-10S;%-7S;%-7S;%5d;%-10S;%-10S;%7S;%7S;%10.2f\n",
                    h.getNomeCliente(), h.getModelo(), h.getPlaca(),
                    h.getAndar(), h.getVaga(), h.getTelefone(),
                    h.getData(), h.getEntrada(), h.getSaida(),
                    h.getValorPago());
            }
        }
        catch (FormatterClosedException erro){
            System.out.println("Erro ao gravar o arquivo");
            throw new MyException(400, "Erro ao gravar o arquivo", "G-004");
        }
        finally {
            saida.close();
            try {
                arq.close();
                return nome;
            }
            catch (IOException erro){
                System.out.println("Erro ao fechar o arquivo");
                throw new MyException(400, "Erro ao fechar o arquivo", "G-005");
            }
        }
    }
}
