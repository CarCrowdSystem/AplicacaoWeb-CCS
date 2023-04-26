package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.EstacionamentoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/estacionamentos")
@Tag(name = "Estacionamento", description = "Gerencia a entidade estacionamento")
public class EstacionamentoController {
    @Autowired
    private EstacionamentoService estacionamentoService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Estacionamento cadastrado")
    })
    @PostMapping
    public ResponseEntity<EstacionamentoDto> postEstacionamento(
            @RequestBody EstacionamentoEntity estacionamento
    ) throws MyException {
        return ResponseEntity.status(201).body(estacionamentoService.create(estacionamento));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de estacionamentos")
    })
    @GetMapping
    public ResponseEntity<List<EstacionamentoDto>> getAllEstacionamento(){
        return ResponseEntity.status(200).body(estacionamentoService.getAllEstacionamento());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Estacionamento alterado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity patchEstacionamento(
        @PathVariable Integer id,
        @RequestBody EstacionamentoEntity estacionamento
    ){
        estacionamentoService.patchEstacionamento(id, estacionamento);
        return ResponseEntity.status(201).build();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estacionamento excluido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEstacionamento(@PathVariable Integer id){
        if (estacionamentoService.deleteEstacionamento(id)){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
