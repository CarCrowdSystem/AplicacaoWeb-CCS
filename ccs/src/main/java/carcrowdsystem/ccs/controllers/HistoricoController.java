package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.historico.CheckoutSemanalResponse;
import carcrowdsystem.ccs.dtos.historico.HistoricoDto;
import carcrowdsystem.ccs.dtos.reserva.reservaResponseDto;
import carcrowdsystem.ccs.entitys.Estacionamento;
import carcrowdsystem.ccs.entitys.Historico;
import carcrowdsystem.ccs.enums.StatusVagaEnum;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.response.*;
import carcrowdsystem.ccs.response.dtos.UltimoHistoricoVagaDtoResponse;
import carcrowdsystem.ccs.services.EstacionamentoService;
import carcrowdsystem.ccs.services.HistoricoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("${uri.dev}/historicos")
@Tag(name = "Histórico", description = "Gerencia a entidade histórico")
public class HistoricoController{
    @Autowired
    private EstacionamentoService estacionamentoService;
    @Autowired
    private HistoricoService service;

    private List<Historico> getAllHistorico() {
        return service.getAllHistorico();
    }

    @GetMapping
    public ResponseEntity<Historico> getHistoricoById(@RequestParam Integer id) throws MyException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/checkin")
    public ResponseEntity checkin(
            @RequestParam Integer idVeiculo,
            @RequestParam Integer idVaga
    ) throws MyException {
        HistoricoResponse ultimoHistorico = pegarMomentoByIdVeiculo(idVeiculo).getBody();
        if(ultimoHistorico.getStatusRegistro().equals(StatusVagaEnum.Saida)) {
            return service.postHistorico(
                    new HistoricoDto(StatusVagaEnum.Entrada, 0.0), idVeiculo, idVaga);
        } else {
            return ResponseEntity.status(400).body("O veiculo já está no estacionamento");
        }
    }

    @PostMapping("/checkin-placa")
    public ResponseEntity checkinPlaca(
            @RequestParam String placa,
            @RequestParam Integer idVaga
    ) throws MyException {
        Integer idVeiculo = service.pegarVeiculoPorPlaca(placa).getId();
        HistoricoResponse ultimoHistorico;
        try {
            ultimoHistorico = pegarMomentoByIdVeiculo(idVeiculo).getBody();
        } catch (Exception e){
            return service.postHistorico(
                    new HistoricoDto(StatusVagaEnum.Entrada, 0.0), idVeiculo, idVaga);
        }
        if(ultimoHistorico.getStatusRegistro().equals(StatusVagaEnum.Saida)) {
            return service.postHistorico(
                    new HistoricoDto(StatusVagaEnum.Entrada, 0.0), idVeiculo, idVaga);
        } else {
            return ResponseEntity.status(400).body("O veiculo já está no estacionamento");
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(
            @RequestParam Integer idVeiculo
    ) throws MyException {
        HistoricoResponse ultimoHistorico = pegarMomentoByIdVeiculo(idVeiculo).getBody();
        Integer idEstacionamento = service.findByIdEstacionamento(ultimoHistorico.getIdVaga());

        if(ultimoHistorico.getStatusRegistro().equals(StatusVagaEnum.Processando)) {
            HistoricoDto newHistorico =
                    new HistoricoDto(StatusVagaEnum.Saida, service.calculaPreco(ultimoHistorico.getIdVeiculo(), idEstacionamento));
            return service.postHistorico(newHistorico, idVeiculo, ultimoHistorico.getIdVaga());
        } else {
            return ResponseEntity.status(400).body("O veiculo não está no estacionamento");
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arquivo criado")
    })
    @GetMapping("/download-csv")
    public FileSystemResource gerarCsv(@RequestParam Integer id) throws MyException, IOException {
        List<HistoricoDadosResponse> listHistorico = pegaDadosHistorico(id).getBody();
        String filePath = service.gravaArquivoCsv(listHistorico);
        File file = new File(filePath);
        return new FileSystemResource(file);
    }

    @GetMapping("/pegar-momento")
    public ResponseEntity<List<HistoricoResponse>> pegarMomento(){
        return ResponseEntity.ok(service.pegarMomento());
    }
    @GetMapping("/pegar-momento-idVeiculo")
    public ResponseEntity<HistoricoResponse> pegarMomentoByIdVeiculo(
        @RequestParam Integer idVeiculo
    ){
        return ResponseEntity.ok().body(service.pegarMomentoByIdVeiculo(idVeiculo));
    }

    @GetMapping("/pegar-momento-idEstacionamento")
    public ResponseEntity<List<UltimoHistoricoVagaDtoResponse>> pegarMomentoByIdEstacionamento(
            @RequestParam Integer idEstacionamento
    ){
        return ResponseEntity.ok().body(service.pegarMomentoByIdEstacionamento(idEstacionamento));
    }

    public void gerarHistoricoInicial(
        Historico historicoInicial
    ){
        service.postHistoricoInicial(historicoInicial);
    }

    @GetMapping("/pegar-momento-vagas")
    public ResponseEntity<List<MomentoVagasResponse>> pegarMomentoVagasByEstacionamento(@RequestParam Integer idEstacionamento){
        return ResponseEntity.ok().body(service.pegarMomentoVagasByEstacionamento(idEstacionamento));
    }
    @GetMapping("/total-checkout")
    public ResponseEntity<Integer> totalCheckoutDiario(
            @RequestParam Integer idEstacionamento
    ){
        return ResponseEntity.ok().body(service.pegarTotalCheckoutDiario(idEstacionamento));
    }

    @GetMapping("/total-checkout-semanal")
    public ResponseEntity<List<CheckoutSemanalResponse>> totalCheckoutSemanal(
            @RequestParam Integer idEstacionamento
    ){
        return ResponseEntity.ok().body(service.pegarTotalCheckoutSemanal(idEstacionamento));
    }

    @GetMapping("/total-faturamento")
    public ResponseEntity<Double> totalFaturamentoDiario(
            @RequestParam Integer idEstacionamento
    ){
        return ResponseEntity.ok().body(service.pegarTotalFaturamentoDiario(idEstacionamento));
    }

    @GetMapping("/pegar-dados-dash")
    public ResponseEntity<DadosDashResponse> getDadosDash(
            @RequestParam Integer id
    ){
        Double faturamento = totalFaturamentoDiario(id).getBody();
        Integer checkout = totalCheckoutDiario(id).getBody();
        List<MomentoVagasResponse> momento = pegarMomentoVagasByEstacionamento(id).getBody();

        DadosDashResponse dados = new DadosDashResponse();
        dados.setMomentoVagas(momento);
        dados.setTotalFaturamento(faturamento);
        dados.setTotalCheckoutDiario(checkout);

        return ResponseEntity.ok().body(dados);
    }

    @GetMapping("/pegar-checkouts")
    public ResponseEntity<List<PegarCheckoutsResponse>> pegarCheckouts(
        @RequestParam Integer id
    ){
        return ResponseEntity.ok().body(service.pegarCheckouts(id));
    }

    @GetMapping("/dados")
    public ResponseEntity<List<HistoricoDadosResponse>> pegaDadosHistorico(
        @RequestParam Integer id
    ){
        return ResponseEntity.ok().body(service.findByIdEstacionamentoPegaDados(id));
    }

    @PostMapping("/processar")
    public ResponseEntity pedeRetiradaCarro(
        @RequestParam String placa
    ) throws MyException {
        Integer idVeiculo = service.pegarVeiculoPorPlaca(placa).getId();
        HistoricoResponse ultimoHistorico;
        try {
            ultimoHistorico = pegarMomentoByIdVeiculo(idVeiculo).getBody();
        } catch (Exception e){
            throw new MyException(404, "Esse veículo não está no estacionamento", "H-011");
        }
        if(ultimoHistorico.getStatusRegistro().equals(StatusVagaEnum.Entrada)) {
            return service.postHistorico(
                    new HistoricoDto(StatusVagaEnum.Processando, 0.0),
                    idVeiculo, ultimoHistorico.getIdVaga());
        } else {
            return ResponseEntity.status(400).body("Esse veículo não está no estacionamento");
        }
    }

    @PostMapping("/reserva")
    public ResponseEntity reserva(
            @RequestParam String placa,
            @RequestParam Integer idEstacionamento,
            @RequestParam String dataReserva,
            @RequestParam String horaReserva
    ) throws MyException {
        DateTimeFormatter parserDia = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        DateTimeFormatter parserHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime horaDataReserva = LocalDateTime.of(
            LocalDate.parse(dataReserva, parserDia),
            LocalTime.parse(horaReserva, parserHora)
        );
        Integer idVeiculo = service.pegarVeiculoPorPlaca(placa).getId();
        Estacionamento estacionamento = estacionamentoService.findById(idEstacionamento);
        HistoricoDto historicoDto = new HistoricoDto(StatusVagaEnum.Reservado, 0.0);
        return service.postReserva(historicoDto, idVeiculo, estacionamento, horaDataReserva);
    }

    @GetMapping("/reserva")
    public ResponseEntity<List<reservaResponseDto>> getReservasByIdEstacionamento(
            @RequestParam Integer idEstacionamento
    ){
        return ResponseEntity.ok(service.getReservasByIdEstacionamento(idEstacionamento));
    }

    @DeleteMapping("/reserva")
    public ResponseEntity delReservaById(
            @RequestParam Integer id
    ){
        service.delReservaById(id);
        return ResponseEntity.ok().build();
    }
}

