package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.controllers.VagaController;
import carcrowdsystem.ccs.controllers.VeiculoController;
import carcrowdsystem.ccs.dtos.historico.HistoricoDto;
import carcrowdsystem.ccs.entitys.Historico;
import carcrowdsystem.ccs.entitys.Vaga;
import carcrowdsystem.ccs.entitys.Veiculo;
import carcrowdsystem.ccs.enums.StatusVagaEnum;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.HistoricoRepository;
import carcrowdsystem.ccs.response.HistoricoDadosResponse;
import carcrowdsystem.ccs.response.HistoricoResponse;
import carcrowdsystem.ccs.response.MomentoVagasResponse;
import carcrowdsystem.ccs.response.PegarCheckoutsResponse;
import carcrowdsystem.ccs.response.dtos.UltimoHistoricoVagaDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
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

    public List<HistoricoResponse> pegarMomento() {
        List<Historico> listH = repository.pegarMomento();
        List<HistoricoResponse> listResult = new ArrayList();
        for (Historico h: listH){
            listResult.add(toHistoricoResponse(h));
        }
        return listResult;
    }

    public HistoricoResponse pegarMomentoByIdVeiculo(Integer idVeiculo) {
        Historico h = repository.pegarMomentoByIdVeiculo(idVeiculo);
        return toHistoricoResponse(h);
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
                    momentoVagas.setIdVaga(historico.getVaga().getId());
                    momentoVagas.setNumero(historico.getVaga().getNumero());
                    momentoVagas.setAndar(historico.getVaga().getAndar());
                    momentoVagas.setStatusRegistro(historico.getStatusRegistro());
                    return momentoVagas;
                })
                .collect(Collectors.toList());
    }

    public List<PegarCheckoutsResponse> pegarCheckouts(Integer id) {
        List<Historico> checkouts = repository.pegarMomentoByIdEstacionamento(id);
        List<PegarCheckoutsResponse> listResponse = new ArrayList();

        for (Historico historico: checkouts) {
            if(historico.getStatusRegistro().equals(StatusVagaEnum.Entrada)) {
                PegarCheckoutsResponse response = new PegarCheckoutsResponse();
                response.setNome(historico.getVeiculo().getNomeCliente());
                response.setTelefone(historico.getVeiculo().getTelefoneCliente());
                response.setAndar(historico.getVaga().getAndar());
                response.setVaga(historico.getVaga().getNumero());
                response.setFkVaga(historico.getVaga().getId());
                response.setFkVeiculo(historico.getVeiculo().getId());
                response.setValor(repository.calculaPreco(historico.getId()));
                listResponse.add(response);
            }
        }

        return listResponse;
    }

    public Double calculaPreco(Integer id){
        return repository.calculaPreco(id);
    }

    public List<HistoricoDadosResponse> findByIdEstacionamentoPegaDados(Integer id) {
        List<Historico> historicos = repository.pegarDadosHistoricoByIdEstacionamento(id);
        List<HistoricoDadosResponse> listResult = new ArrayList();
        for (Historico h: historicos){
            listResult.add(toHistoricoDadosResponse(h));
        }
        return listResult;
    }
    private HistoricoResponse toHistoricoResponse(Historico h){
        HistoricoResponse response = new HistoricoResponse();
        response.setId(h.getId());
        response.setIdVeiculo(h.getVeiculo().getId());
        response.setMomentoRegistro(h.getMomentoRegistro());
        response.setIdVaga(h.getVaga().getId());
        response.setStatusRegistro(h.getStatusRegistro());
        response.setValorPago(h.getValorPago());
        return response;
    }

    private HistoricoDadosResponse toHistoricoDadosResponse(Historico h){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        HistoricoDadosResponse response = new HistoricoDadosResponse();
        response.setCliente(h.getVeiculo().getNomeCliente());
        response.setModelo(h.getVeiculo().getModelo());
        response.setPlaca(h.getVeiculo().getPlaca());
        response.setAndar(h.getVaga().getAndar());
        response.setVaga(h.getVaga().getNumero());
        response.setTelefone(h.getVeiculo().getTelefoneCliente());
        response.setData(h.getMomentoRegistro().toLocalDate().toString());
        response.setHora(h.getMomentoRegistro().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString());
        response.setStatus(h.getStatusRegistro().toString());
        response.setValor(decimalFormat.format(h.getValorPago()));
        return response;
    }

    public Veiculo pegarVeiculoPorPlaca(String placa) {
        return veiculoController.findByPlaca(placa);
    }
}
