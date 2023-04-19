package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.repositorys.EstacionamentoRepository;
import carcrowdsystem.ccs.services.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
