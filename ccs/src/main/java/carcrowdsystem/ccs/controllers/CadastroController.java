package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.Entitys.FuncionarioEntity;
import carcrowdsystem.ccs.dtos.FuncionarioDto;
import carcrowdsystem.ccs.dtos.FuncionarioLoginDto;
import carcrowdsystem.ccs.dtos.FuncionarioTokenDto;
import carcrowdsystem.ccs.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ccs/funcionarios")
public class CadastroController {
    @Autowired
    FuncionarioService funcionarioService;
    @PostMapping({"/{gerente}",""})
    public FuncionarioDto postUsuario(
            @RequestBody FuncionarioEntity funcionario,
            @PathVariable(required = false) String gerente
    ) {
        if(gerente != null) {
            if( gerente.equals("gerente") ) {
                funcionario.setCargo("gerente");
                return funcionarioService.create(funcionario);
            }
            return null;
        }
        return funcionarioService.create(funcionario);
    }

    @PostMapping("/login")
    public ResponseEntity<FuncionarioTokenDto> login(@RequestBody FuncionarioLoginDto funcionarioLoginDto) {
        FuncionarioTokenDto funcionarioTokenDto = funcionarioService.autenticar(funcionarioLoginDto);

        return ResponseEntity.status(200).body(funcionarioTokenDto);
    }

    @GetMapping
    public List<FuncionarioDto> getFuncionarios(){
        return funcionarioService.getAllFuncs();
    }

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
