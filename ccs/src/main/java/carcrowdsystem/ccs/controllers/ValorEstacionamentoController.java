package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.valorEstacionamento.ValorEstacionamentoDto;
import carcrowdsystem.ccs.entitys.ValorEstacionamentoEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.ValorEstacionamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/valor")
public class ValorEstacionamentoController {
    private final ValorEstacionamentoService valorEstacionamentoService;

    public ValorEstacionamentoController(ValorEstacionamentoService valorEstacionamentoService) {
        this.valorEstacionamentoService = valorEstacionamentoService;
    }

    @GetMapping
    public ResponseEntity<List<ValorEstacionamentoEntity>> getValores(){
        return ResponseEntity.status(200).body(valorEstacionamentoService.findAll());
    }

    @PostMapping
    public ResponseEntity postValor(
        @RequestBody ValorEstacionamentoDto valorEstacionamentoDto,
        @RequestParam Integer idEstacionamento
    ) throws MyException {
        valorEstacionamentoService.postValor(valorEstacionamentoDto, idEstacionamento);
        return ResponseEntity.status(201).build();
    }
}