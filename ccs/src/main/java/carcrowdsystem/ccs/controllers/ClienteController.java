package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.request.ClienteRequest;
import carcrowdsystem.ccs.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("${uri.dev}/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public void postCliente(ClienteRequest cliente){
        clienteService.postCliente(cliente);
    }
}
