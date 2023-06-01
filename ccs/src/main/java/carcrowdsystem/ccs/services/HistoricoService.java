package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.controllers.VagaController;
import carcrowdsystem.ccs.controllers.VeiculoController;
import carcrowdsystem.ccs.dtos.historico.HistoricoDto;
import carcrowdsystem.ccs.entitys.HistoricoEntity;
import carcrowdsystem.ccs.entitys.VagaEntity;
import carcrowdsystem.ccs.entitys.VeiculoEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.HistoricoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@Service
public class HistoricoService {
    private final HistoricoRepository repository;
    private final VeiculoController veiculoController;
    private final VagaController vagaController;

    public HistoricoService(
        HistoricoRepository repository,
        VeiculoController veiculoController,
        VagaController vagaController
    ) {
        this.repository = repository;
        this.veiculoController = veiculoController;
        this.vagaController = vagaController;
    }

    public List<HistoricoEntity> getAllHistorico(){
        return repository.findAll();
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
            throw new MyException(400, "Erro ao abrir o arquivo.", "H-001");
        }

        // Bloco try catch para gravar o arquivo
        try {
            saida.format("%-15S;%-10S;%-7S;%-7S;%5S;%-14S;%-10S;%7S;%7S;%10S\n",
                    "CLIENTE","MODELO","PLACA","ANDAR","VAGA","TELEFONE","DATA","HORA","STATUS","VALOR");
            for (HistoricoEntity h: lista){
                VeiculoEntity veiculo = h.getVeiculo();
                VagaEntity vaga = h.getVaga();
                LocalDate data = h.getMomentoRegistro().toLocalDate();
                LocalTime hora = h.getMomentoRegistro().toLocalTime();
                saida.format("%-15S;%-10S;%-7S;%-7S;%5d;%-10S;%-10S;%7S;%7S;%10.2f\n",
                veiculo.getNomeCliente(), veiculo.getModelo(), veiculo.getPlaca(),
                vaga.getAndar(), vaga.getNumero(), veiculo.getTelefoneCliente(),
                data, hora, h.getStatusRegistro(),
                h.getValorPago());
            }
        }
        catch (FormatterClosedException erro){
            System.out.println("Erro ao gravar o arquivo");
            throw new MyException(400, "Erro ao gravar o arquivo", "H-002");
        }
        finally {
            saida.close();
            try {
                arq.close();
                return nome;
            }
            catch (IOException erro){
                System.out.println("Erro ao fechar o arquivo");
                throw new MyException(400, "Erro ao fechar o arquivo", "H-003");
            }
        }
    }

    public HistoricoEntity findById(Integer id) throws MyException {
        return repository.findById(id).orElseThrow(
            () -> new MyException(404, "Historico não existe", "H-004")
        );
    }

    public ResponseEntity postHistorico(
        HistoricoDto newHistorico,
        Integer idVeiculo,
        Integer idVaga
    ) throws MyException {
        try {
            VagaEntity vaga = vagaController.getVagaById(idVaga).getBody();
            VeiculoEntity veiculo = veiculoController.getVeiculoById(idVeiculo).getBody();
            HistoricoEntity historico = new HistoricoEntity();
            historico.setVaga(vaga);
            historico.setVeiculo(veiculo);
            historico.setValorPago(newHistorico.getValorPago());
            historico.setStatusRegistro(newHistorico.getStatusRegistro());
            historico.setMomentoRegistro(newHistorico.getMomentoRegistro());
            repository.save(historico);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            throw new MyException(e.hashCode(), e.getMessage(), "H-005");
        }
    }

    public List<HistoricoEntity> pegarMomento() {
        return repository.pegarMomento();
    }

    public HistoricoEntity pegarMomentoByIdVeiculo(Integer idVeiculo) {
        return repository.pegarMomentoByIdVeiculo(idVeiculo);
    }
}
