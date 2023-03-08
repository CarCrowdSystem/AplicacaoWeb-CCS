package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.entidades.Funcionario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @PostMapping
    public String cadastraUsuario(@RequestBody Funcionario funcionario) {
        return "Começo de um sonho";
    }
}
