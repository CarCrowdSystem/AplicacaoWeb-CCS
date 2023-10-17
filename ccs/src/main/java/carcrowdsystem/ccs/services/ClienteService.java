package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.configuration.security.jwt.GerenciadorTokenJwt;
import carcrowdsystem.ccs.dtos.cliente.ClienteTokenDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioLoginDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.entitys.Cliente;
import carcrowdsystem.ccs.entitys.Estacionamento;
import carcrowdsystem.ccs.entitys.Funcionario;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.ClienteRepository;
import carcrowdsystem.ccs.repositorys.FuncionarioRepository;
import carcrowdsystem.ccs.request.ClienteRequest;
import carcrowdsystem.ccs.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new MyException(404, "Id não existente", "E005");
        }
    }

    public LoginResponse autenticar(FuncionarioLoginDto funcionarioLoginDto){
        final UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(
                        funcionarioLoginDto.getEmail(), funcionarioLoginDto.getSenha()
                );

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Funcionario funcionarioAutenticado =
                funcionarioRepository.findByEmail(funcionarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        ClienteTokenDto cli = new ClienteTokenDto();
        cli.setClienteId(funcionarioAutenticado.getId());
        cli.setEmail(funcionarioAutenticado.getEmail());
        cli.setSenha(funcionarioAutenticado.getSenha());
        cli.setToken(token);

        Cliente cliente = clienteRepository.findById(cli.getClienteId()).get();

        LoginResponse response = new LoginResponse();
        response.setIdEstacionamento(cli.getClienteId());
        response.setNomeEstacionamento(cliente.getNome());
        response.setJwt(cli.getToken());

        return response;
    }
}
