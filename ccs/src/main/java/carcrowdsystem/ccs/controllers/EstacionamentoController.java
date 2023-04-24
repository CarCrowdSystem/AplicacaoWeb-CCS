package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/estacionamentos")
public class EstacionamentoController {
    @Autowired
    private EstacionamentoService estacionamentoService;

    @PostMapping
    public ResponseEntity<EstacionamentoDto> postEstacionamento(
            @RequestBody EstacionamentoEntity estacionamento
    ) throws MyException {
        return ResponseEntity.status(201).body(estacionamentoService.create(estacionamento));
    }

    @GetMapping
    public ResponseEntity<List<EstacionamentoDto>> getAllEstacionamento(){
        return ResponseEntity.status(200).body(estacionamentoService.getAllEstacionamento());
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchEstacionamento(
        @PathVariable Integer id,
        @RequestBody EstacionamentoEntity estacionamento
    ){
        estacionamentoService.patchEstacionamento(id, estacionamento);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEstacionamento(@PathVariable Integer id){
        if (estacionamentoService.deleteEstacionamento(id)){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
