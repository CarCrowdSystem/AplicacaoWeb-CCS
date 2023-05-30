package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.historico.HistoricoDto;
import carcrowdsystem.ccs.entitys.HistoricoEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.HistoricoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/historicos")
@Tag(name = "Histórico", description = "Gerencia a entidade histórico")
public class HistoricoController {

    @Autowired
    private HistoricoService service;

    private List<HistoricoEntity> getAllHistorico() {
        return service.getAllHistorico();
    }

    @GetMapping
    public ResponseEntity<HistoricoEntity> getHistoricoById(@RequestParam Integer id) throws MyException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/checkin")
    public ResponseEntity checkin(
            @RequestBody HistoricoDto newHistorico,
            @RequestParam Integer idVeiculo,
            @RequestParam Integer idVaga
    ) throws MyException {
        newHistorico.setStatusRegistro("Entrada");
        return service.postHistorico(newHistorico, idVeiculo, idVaga);
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(
            @RequestBody HistoricoDto newHistorico,
            @RequestParam Integer idVeiculo
    ) throws MyException {
        HttpStatus momento = pegarMomentoByIdVeiculo(idVeiculo).getStatusCode();
        if(momento.value() == 200) {
            newHistorico.setStatusRegistro("Saída");
            return service.postHistorico(newHistorico, idVeiculo, 1);
        }
        return ResponseEntity.badRequest().build();
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arquivo criado")
    })
    @GetMapping("/gerar-csv")
    public ResponseEntity gerarCsv() throws MyException {
        List<HistoricoEntity> listaHistorico = getAllHistorico();
        String nome = service.gravaArquivoCsv(listaHistorico);
        return ResponseEntity.status(200).body("Arquivo '"+nome+"' criado com sucesso");
    }

    @GetMapping("/pegar-momento")
    public ResponseEntity<List<HistoricoEntity>> pegarMomento(){
        return ResponseEntity.ok(service.pegarMomento());
    }
    @GetMapping("/pegar-momento-id")
    public ResponseEntity<HistoricoEntity> pegarMomentoByIdVeiculo(
        @RequestParam Integer idVeiculo
    ){
        return ResponseEntity.ok().body(service.pegarMomentoByIdVeiculo(idVeiculo));
    }
}

