package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.FuncionarioDto;
import carcrowdsystem.ccs.abstracts.Funcionario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ccs/funcionario")
public class CadastroController {
    List<Funcionario> funcionarios = new ArrayList<>();

    @PostMapping({"/{gerente}",""})
    public FuncionarioDto postUsuario(
            @RequestBody FuncionarioDto funcionario,
            @PathVariable(required = false) String gerente
    ) {
        if(gerente != null) {
            if( gerente.equals("gerente") ) {
                funcionarios.add(funcionario.toGerente());
                return funcionario;
            }
            return null;
        }
        funcionarios.add(funcionario.toFuncionario());
        return funcionario;
    }

    @GetMapping
    public List<Funcionario> getUsuarios() {
        return funcionarios;
    }
}
