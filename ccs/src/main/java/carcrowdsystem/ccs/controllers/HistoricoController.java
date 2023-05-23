package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.historico.HistoricoDto;
import carcrowdsystem.ccs.entitys.HistoricoEntity;
import carcrowdsystem.ccs.exception.MyException;
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

    private List<HistoricoEntity> getAllHistorico() {
        return service.getAllHistorico();
    }

    @GetMapping
    public ResponseEntity<HistoricoEntity> getHistoricoById(@RequestParam Integer id) throws MyException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity postHistorico(
            @RequestBody HistoricoDto newHistorico,
            @RequestParam Integer idVeiculo,
            @RequestParam Integer idVaga
        ) throws MyException {
        return service.postHistorico(newHistorico, idVeiculo, idVaga);
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
}

