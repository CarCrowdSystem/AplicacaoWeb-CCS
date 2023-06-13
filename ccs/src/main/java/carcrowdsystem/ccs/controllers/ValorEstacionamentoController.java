package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.valorEstacionamento.ValorEstacionamentoDto;
import carcrowdsystem.ccs.entitys.ValorEstacionamento;
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

    @GetMapping("/todos")
    public ResponseEntity<List<ValorEstacionamento>> getValores(){
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

    @GetMapping
    public ResponseEntity<ValorEstacionamento> getValorByEstacionamento(
        @RequestParam Integer id
    ){
        return ResponseEntity.status(200).body(valorEstacionamentoService.findByIdEstacionamento(id));
    }
}
