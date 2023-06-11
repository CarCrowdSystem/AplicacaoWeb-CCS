package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.vaga.VagaDto;
import carcrowdsystem.ccs.entitys.Vaga;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.VagaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/vagas")
public class VagaController {
    private final VagaService service;

    public VagaController(VagaService service) {
        this.service = service;
    }

    @PostMapping("/{idEstacionamento}")
    public ResponseEntity postVaga(
        @RequestBody List<VagaDto> novasVagas,
        @PathVariable Integer idEstacionamento
    ) throws MyException {

        for (VagaDto v: novasVagas) {
            service.postVaga(v, idEstacionamento);
        }
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vaga>> getVagas(){
        return ResponseEntity.ok(service.getVagas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Vaga>> deleteVaga(@PathVariable Integer id){
        service.deleteVaga(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Vaga> getVagaById(@RequestParam Integer idVaga) throws MyException {
        return ResponseEntity.ok(service.findById(idVaga));
    }
}
