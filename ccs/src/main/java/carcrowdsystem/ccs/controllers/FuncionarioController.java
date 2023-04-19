package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioLoginDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.entitys.FuncionarioEntity;
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
    @PostMapping({"/{gerente}",""})
    public ResponseEntity<FuncionarioDto> postUsuario(
            @RequestBody FuncionarioEntity funcionario,
            @PathVariable(required = false) String gerente
    ) {
        if(gerente != null) {
            if( gerente.equals("gerente") ) {
                funcionario.setCargo("gerente");
                return ResponseEntity.status(201).body(funcionarioService.create(funcionario));
            }
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(201).body(funcionarioService.create(funcionario));
    }

    @PostMapping("/login")
    public ResponseEntity<FuncionarioTokenDto> login(@RequestBody FuncionarioLoginDto funcionarioLoginDto) {
        FuncionarioTokenDto funcionarioTokenDto = funcionarioService.autenticar(funcionarioLoginDto);
        if (funcionarioTokenDto != null){
            return ResponseEntity.status(200).body(funcionarioTokenDto);
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> getFuncionarios(){
        return ResponseEntity.status(200).body(funcionarioService.getAllFuncs());
    }
}
