package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.entitys.Cliente;
import carcrowdsystem.ccs.repositorys.ClienteRepository;
import carcrowdsystem.ccs.request.ClienteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public void postCliente(ClienteRequest cliente){
        Cliente newCliente = new Cliente();
        cliente.setNome(cliente.getNome());
        cliente.setEmail(cliente.getEmail());
        cliente.setSenha(cliente.getSenha());

        clienteRepository.save(newCliente);
    }
}
