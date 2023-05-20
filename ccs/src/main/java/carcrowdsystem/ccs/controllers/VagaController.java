package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.VagaDto;
import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.VagaEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.VagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/vaga")
public class VagaController {
    private final VagaService service;

    public VagaController(VagaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity salvarVaga(
        @RequestBody VagaDto novaVaga,
        @RequestParam Integer idEstacionamento
    ) throws MyException {
        service.postVaga(novaVaga, idEstacionamento);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<VagaEntity>> getVagas(){
        return ResponseEntity.status(200).body(service.getVagas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<VagaEntity>> deleteVaga(@PathVariable Integer id){
        service.deleteVaga(id);
        return ResponseEntity.status(200).build();
    }
}
