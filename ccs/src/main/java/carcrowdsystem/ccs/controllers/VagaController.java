package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.vaga.VagaDto;
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
    public ResponseEntity<VagaEntity> postVaga(
        @RequestBody VagaDto novaVaga,
        @RequestParam Integer idEstacionamento
    ) throws MyException {
        ;
        return ResponseEntity.status(201).body(service.postVaga(novaVaga, idEstacionamento));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VagaEntity>> getVagas(){
        return ResponseEntity.ok(service.getVagas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<VagaEntity>> deleteVaga(@PathVariable Integer id){
        service.deleteVaga(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<VagaEntity> getVagaById(@RequestParam Integer idVaga) throws MyException {
        return ResponseEntity.ok(service.findById(idVaga));
    }
}
