package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.FuncionarioDto;
import carcrowdsystem.ccs.abstracts.Funcionario;
import carcrowdsystem.ccs.models.FuncionarioEstacionamento;
import carcrowdsystem.ccs.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ccs/funcionarios")
public class CadastroController {
    @Autowired
    FuncionarioService funcionarioService;
    @PostMapping({"/{gerente}",""})
    public FuncionarioDto postUsuario(
            @RequestBody FuncionarioEstacionamento funcionario,
            @PathVariable(required = false) String gerente
    ) {
        if(gerente != null) {
            if( gerente.equals("gerente") ) {
                return funcionarioService.create(funcionario.toGerente());
            }
            return null;
        }
        return funcionarioService.create(funcionario);
    }

//    @GetMapping
//    public List<FuncionarioDto> getFuncionarios(){
//        return funcionarioService.list();
//    }

//    @GetMapping("/{email}/{senha}")
//    public String loginUsuario(@PathVariable String email, @PathVariable String senha) {
//        return funcionarioService.login(email, senha);
//    }
//
//    @GetMapping("/{email}")
//    public String logoffUsuario(@PathVariable String email) {
//        return funcionarioService.logoff(email);
//    }
}
