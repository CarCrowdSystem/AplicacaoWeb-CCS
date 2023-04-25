package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.adapter.FuncionarioAdapter;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioLoginDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/funcionarios")
public class FuncionarioController {
    @Autowired
    FuncionarioAdapter funcionarioAdapter = new FuncionarioAdapter();
    @PostMapping("/{idEstacionamento}/{gerente}")
    public ResponseEntity<FuncionarioDto> postUsuario(
            @PathVariable Integer idEstacionamento,
            @RequestBody FuncionarioEntity funcionario,
            @PathVariable(required = false) String gerente
    ) throws MyException {
        funcionario.setIdEstacionamento(idEstacionamento);
        if(gerente != null) {
            if( gerente.equals("gerente") ) {
                funcionario.setCargo("gerente");
                return ResponseEntity.status(201).body(funcionarioAdapter.create(funcionario));
            }
            throw new MyException(404, "Uri incorreta '/"+gerente+"'", "G-001");
        }

        return ResponseEntity.status(201).body(funcionarioAdapter.create(funcionario));
    }

    @PostMapping("/login")
    public ResponseEntity<FuncionarioTokenDto> login(@RequestBody FuncionarioLoginDto funcionarioLoginDto) {
        return ResponseEntity.status(200).body(funcionarioAdapter.autenticar(funcionarioLoginDto));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> getFuncionarios() throws MyException {
        return ResponseEntity.status(200).body(funcionarioAdapter.getAllFuncs());
    }
}
