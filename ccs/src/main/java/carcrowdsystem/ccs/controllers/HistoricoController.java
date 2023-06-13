package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.historico.HistoricoDto;
import carcrowdsystem.ccs.entitys.Historico;
import carcrowdsystem.ccs.enums.StatusVagaEnum;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.response.dtos.UltimoHistoricoVagaDtoResponse;
import carcrowdsystem.ccs.services.HistoricoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/historicos")
@Tag(name = "Histórico", description = "Gerencia a entidade histórico")
public class HistoricoController {

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
            @RequestBody HistoricoDto newHistorico,
            @RequestParam Integer idVeiculo,
            @RequestParam Integer idVaga
    ) throws MyException {
        Historico ultimoHistorico = pegarMomentoByIdVeiculo(idVeiculo).getBody();
        if(ultimoHistorico.getStatusRegistro().equals(StatusVagaEnum.Saida)) {
            newHistorico.setStatusRegistro(StatusVagaEnum.Entrada);
            return service.postHistorico(newHistorico, idVeiculo, idVaga);
        } else {
            return ResponseEntity.status(400).body("O veiculo já está no estacionamento");
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(
            @RequestBody HistoricoDto newHistorico,
            @RequestParam Integer idVeiculo
    ) throws MyException {
        Historico ultimoHistorico = pegarMomentoByIdVeiculo(idVeiculo).getBody();
        if(ultimoHistorico.getStatusRegistro().equals(StatusVagaEnum.Entrada)) {
            newHistorico.setStatusRegistro(StatusVagaEnum.Saida);
            return service.postHistorico(newHistorico, idVeiculo, ultimoHistorico.getVaga().getId());
        } else {
            return ResponseEntity.status(400).body("O veiculo não está no estacionamento");
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arquivo criado")
    })
    @GetMapping("/gerar-csv")
    public ResponseEntity gerarCsv() throws MyException {
        List<Historico> listaHistorico = getAllHistorico();
        String nome = service.gravaArquivoCsv(listaHistorico);
        return ResponseEntity.status(200).body("Arquivo '"+nome+"' criado com sucesso");
    }

    @GetMapping("/pegar-momento")
    public ResponseEntity<List<Historico>> pegarMomento(){
        return ResponseEntity.ok(service.pegarMomento());
    }
    @GetMapping("/pegar-momento-idVeiculo")
    public ResponseEntity<Historico> pegarMomentoByIdVeiculo(
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
    public ResponseEntity<List<Historico>> pegarMomentoVagasByEstacionamento(@RequestParam Integer idEstacionamento){
        return ResponseEntity.ok().body(service.pegarMomentoVagasByEstacionamento(idEstacionamento));
    }
    @GetMapping("/total-checkout")
    public ResponseEntity<Integer> totalCheckoutDiario(
            @RequestParam Integer idEstacionamento
    ){
        return ResponseEntity.ok().body(service.pegarTotalCheckoutDiario(idEstacionamento));
    }

    @GetMapping("/total-faturamento")
    public ResponseEntity<Double> totalFaturamentoDiario(
            @RequestParam Integer idEstacionamento
    ){
        return ResponseEntity.ok().body(service.pegarTotalFaturamentoDiario(idEstacionamento));
    }
}

