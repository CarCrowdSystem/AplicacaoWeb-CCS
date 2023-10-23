package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.configuration.security.jwt.GerenciadorTokenJwt;
import carcrowdsystem.ccs.dtos.cliente.ClienteTokenDto;
import carcrowdsystem.ccs.dtos.funcionario.LoginDto;
import carcrowdsystem.ccs.entitys.Cliente;
import carcrowdsystem.ccs.entitys.Funcionario;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.ClienteRepository;
import carcrowdsystem.ccs.repositorys.FuncionarioRepository;
import carcrowdsystem.ccs.request.ClienteRequest;
import carcrowdsystem.ccs.request.dtos.ClienteUpdateRequest;
import carcrowdsystem.ccs.response.ClienteResponse;
import carcrowdsystem.ccs.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    public void postCliente(ClienteRequest cliente) throws MyException {
        try {
            Cliente newCliente = new Cliente();
            cliente.setNome(cliente.getNome());
            cliente.setEmail(cliente.getEmail());
            cliente.setSenha(cliente.getSenha());

            clienteRepository.save(newCliente);
        } catch (Exception e) {
            throw new MyException(404, "Id não existente", "Cli001");
        }
    }

    public LoginResponse autenticar(LoginDto loginDto) throws MyException {
        final UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(), loginDto.getSenha()
                );

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Cliente cliente =
                clienteRepository.findByEmail(loginDto.getEmail())
                        .orElseThrow(
                            () -> new ResponseStatusException(404, "Email do cliente não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        ClienteTokenDto cli = new ClienteTokenDto();
        cli.setClienteId(cliente.getId());
        cli.setEmail(cliente.getEmail());
        cli.setSenha(cliente.getSenha());
        cli.setToken(token);

        LoginResponse response = new LoginResponse();
        response.setIdEstacionamento(cliente.getId());
        response.setNomeEstacionamento(cliente.getNome());
        response.setJwt(cli.getToken());

        return response;
    }

    public void patch(ClienteUpdateRequest clienteUpdateRequest, Integer id) throws MyException {
        Cliente clienteOld = findById(id);
        clienteRepository.save(updateCliente(clienteOld, clienteUpdateRequest));
    }
    private Cliente updateCliente(Cliente cli, ClienteUpdateRequest newCli) {
        if (newCli.getNome() != null) cli.setNome(newCli.getNome());
        if (newCli.getCpf() != null) cli.setCpf(newCli.getCpf());
        if (newCli.getTelefone() != null) cli.setTelefone(newCli.getTelefone());
        if (newCli.getEmail() != null) cli.setEmail(newCli.getEmail());
        return cli;
    }

    public Cliente findById(Integer id) throws MyException {
        return clienteRepository.findById(id).orElseThrow(
            () -> new MyException(404, "Cliente não existe", "Cli002")
        );
    }

    public ClienteResponse getCliente(Integer id) throws MyException {
        return new ClienteResponse(findById(id));
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }
}
