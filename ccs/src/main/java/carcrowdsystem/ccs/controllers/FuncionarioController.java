package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioLoginDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/funcionarios")
public class FuncionarioController {
    @Autowired
    FuncionarioService funcionarioService;
    @PostMapping({"/{idEstacionamento}","/{idEstacionamento}/{gerente}"})
    public ResponseEntity<FuncionarioDto> postUsuario(
            @PathVariable Integer idEstacionamento,
            @RequestBody FuncionarioEntity funcionario,
            @PathVariable(required = false) String gerente
    ) throws MyException {
        if(gerente != null) {
            if( gerente.equals("gerente") ) {
                funcionario.setCargo("gerente");
                return ResponseEntity.status(201).body(funcionarioService.create(idEstacionamento, funcionario));
            }
            throw new MyException(404, "Uri incorreta '/"+gerente+"'", "G-001");
        }
        return ResponseEntity.status(201).body(funcionarioService.create(idEstacionamento, funcionario));
    }

    @PostMapping("/login")
    public ResponseEntity<FuncionarioTokenDto> login(@RequestBody FuncionarioLoginDto funcionarioLoginDto) {
        return ResponseEntity.status(200).body(funcionarioService.autenticar(funcionarioLoginDto));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> getFuncionarios() throws MyException {
        return ResponseEntity.status(200).body(funcionarioService.getAllFuncs());
    }
}
