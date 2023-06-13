package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.controllers.VagaController;
import carcrowdsystem.ccs.controllers.VeiculoController;
import carcrowdsystem.ccs.dtos.historico.HistoricoDto;
import carcrowdsystem.ccs.entitys.Historico;
import carcrowdsystem.ccs.entitys.Vaga;
import carcrowdsystem.ccs.entitys.Veiculo;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.HistoricoRepository;
import carcrowdsystem.ccs.response.MomentoVagasResponse;
import carcrowdsystem.ccs.response.PegarCheckoutsResponse;
import carcrowdsystem.ccs.response.dtos.UltimoHistoricoVagaDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<Historico> getAllHistorico(){
        return repository.findAll();
    }

    public static String gravaArquivoCsv(List<Historico> lista) throws MyException {
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
            for (Historico h: lista){
                Veiculo veiculo = h.getVeiculo();
                Vaga vaga = h.getVaga();
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

    public Historico findById(Integer id) throws MyException {
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
            Vaga vaga = vagaController.getVagaById(idVaga).getBody();
            Veiculo veiculo = veiculoController.getVeiculoById(idVeiculo).getBody();
            Historico historico = new Historico();
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

    public void postHistoricoInicial(
        Historico historico
    ){
        repository.save(historico);
    }

    public List<Historico> pegarMomento() {
        return repository.pegarMomento();
    }

    public Historico pegarMomentoByIdVeiculo(Integer idVeiculo) {
        return repository.pegarMomentoByIdVeiculo(idVeiculo);
    }

    public List<UltimoHistoricoVagaDtoResponse> pegarMomentoByIdEstacionamento(Integer idEstacionamento) {
        List<Historico> listHistorico =
                repository.pegarMomentoByIdEstacionamento(idEstacionamento);
        List<UltimoHistoricoVagaDtoResponse> listHistoricoDto = new ArrayList();
        for (Historico h: listHistorico){
            listHistoricoDto.add(new UltimoHistoricoVagaDtoResponse(
                h.getVaga().getNumero(),
                h.getVaga().getAndar(),
                h.getStatusRegistro()
            ));
        }
        return listHistoricoDto;
    }

    public List<MomentoVagasResponse> pegarMomentoVagasByEstacionamento(Integer id){
        return converter(repository.pegarMomentoVagasByEstacionamento(id));
    }

    public Integer pegarTotalCheckoutDiario(Integer idEstacionamento){
        return repository.pegarTotalCheckoutDiario(idEstacionamento);
    }

    public Double pegarTotalFaturamentoDiario(Integer idEstacionamento){
        return repository.pegarTotalFaturamentoDiario(idEstacionamento);
    }

    public static List<MomentoVagasResponse> converter(List<Historico> historicos) {
        return historicos.stream()
                .map(historico -> {
                    MomentoVagasResponse momentoVagas = new MomentoVagasResponse();
                    momentoVagas.setNumero(historico.getVaga().getNumero());
                    momentoVagas.setAndar(historico.getVaga().getAndar());
                    momentoVagas.setStatusRegistro(historico.getStatusRegistro());
                    return momentoVagas;
                })
                .collect(Collectors.toList());
    }

    public List<PegarCheckoutsResponse> pegarCheckouts(Integer id) {
        List<Historico> checkouts = repository.pegarCheckouts(id);
        List<PegarCheckoutsResponse> listResponse = new ArrayList();

        for (Historico historico: checkouts) {
            PegarCheckoutsResponse response = new PegarCheckoutsResponse();
            response.setNome(historico.getVeiculo().getNomeCliente());
            response.setTelefone(historico.getVeiculo().getTelefoneCliente());
            response.setAndar(historico.getVaga().getAndar());
            response.setVaga(historico.getVaga().getNumero());
            response.setValor(repository.calculaPreco(historico.getId()));
            listResponse.add(response);
        }

        return listResponse;
    }
}
