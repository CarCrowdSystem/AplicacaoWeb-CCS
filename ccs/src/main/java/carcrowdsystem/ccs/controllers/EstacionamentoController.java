package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import carcrowdsystem.ccs.services.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/estacionamento")
public class EstacionamentoController {
    @Autowired
    private EstacionamentoService estacionamentoService;

    @PostMapping
    public ResponseEntity<EstacionamentoDto> postEstacionamento(
            @RequestBody EstacionamentoEntity estacionamento
    ) {
        return ResponseEntity.status(201).body(estacionamentoService.create(estacionamento));
    }

    @GetMapping
    public ResponseEntity<List<EstacionamentoDto>> getAllEstacionamento(){
        return ResponseEntity.status(200).body(estacionamentoService.getAllEstacionamento());
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchEstacionamento(
        @PathVariable int id,
        @RequestBody EstacionamentoEntity estacionamento
    ){
        estacionamentoService.patchEstacionamento(id, estacionamento);
        return ResponseEntity.status(201).build();
    }
}
