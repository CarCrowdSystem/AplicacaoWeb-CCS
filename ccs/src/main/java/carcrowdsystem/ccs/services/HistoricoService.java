package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.entitys.HistoricoEntity;
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

    public static void gravaArquivoCsv(List<HistoricoEntity> lista){
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        String nome = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss")) + "-historico.csv";

        // Bloc try catch para abrir o arquivo

        try {
            arq = new FileWriter(nome);
            saida = new Formatter(arq);
        }
        catch (IOException erro){
            System.out.println("Erro ao abrir o arquivo.");
            System.out.println(1);
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
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if(deuRuim){
                System.out.println(1);
            }
        }
    }
}
