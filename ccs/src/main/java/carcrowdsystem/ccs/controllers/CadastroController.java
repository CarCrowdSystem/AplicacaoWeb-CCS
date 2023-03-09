package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.entidades.GerenteEstacionamento;
import carcrowdsystem.ccs.entidades.abstracts.Funcionario;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ccs/funcionario")
public class CadastroController {

    @PostMapping("/{adm}")
    public String cadastraUsuario(
            @RequestBody GerenteEstacionamento gerenteEstacionamento,
            @PathVariable(required = false) String adm
    ) {
        if( adm == null ) {
            return "Começo de um sonho";
        }
        return "Deu bom";
    }
}
