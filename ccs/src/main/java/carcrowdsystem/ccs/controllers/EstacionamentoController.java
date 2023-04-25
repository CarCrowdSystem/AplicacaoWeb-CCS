package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.adapter.EstacionamentoAdapter;
import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import carcrowdsystem.ccs.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/estacionamentos")
public class EstacionamentoController {
    @Autowired
    private EstacionamentoAdapter estacionamentoAdapter = new EstacionamentoAdapter();

    @PostMapping
    public ResponseEntity<EstacionamentoDto> postEstacionamento(
            @RequestBody EstacionamentoEntity estacionamento
    ) throws MyException {
        return ResponseEntity.status(201).body(estacionamentoAdapter.create(estacionamento));
    }

    @GetMapping
    public ResponseEntity<List<EstacionamentoDto>> getAllEstacionamento(){
        return ResponseEntity.status(200).body(estacionamentoAdapter.getAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchEstacionamento(
        @PathVariable Integer id,
        @RequestBody EstacionamentoEntity estacionamento
    ) throws MyException {
        estacionamentoAdapter.update(id, estacionamento);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEstacionamento(@PathVariable Integer id) throws MyException {
        if (estacionamentoAdapter.delete(id)){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
