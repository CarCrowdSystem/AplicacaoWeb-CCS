package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.configuration.security.jwt.GerenciadorTokenJwt;
import carcrowdsystem.ccs.dtos.cliente.ClienteHistoricoResponse;
import carcrowdsystem.ccs.dtos.cliente.ClienteTokenDto;
import carcrowdsystem.ccs.dtos.funcionario.LoginDto;
import carcrowdsystem.ccs.entitys.Cliente;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.models.EnderecoEstacionamento;
import carcrowdsystem.ccs.repositorys.ClienteRepository;
import carcrowdsystem.ccs.repositorys.FuncionarioRepository;
import carcrowdsystem.ccs.request.ClienteRequest;
import carcrowdsystem.ccs.request.dtos.ClienteUpdateRequest;
import carcrowdsystem.ccs.response.ClienteResponse;
import carcrowdsystem.ccs.response.LoginClienteResponse;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    public Cliente postCliente(ClienteRequest cliente) throws MyException {
        try {
            Cliente newCliente = new Cliente();
            newCliente.setNome(cliente.getNome());
            newCliente.setEmail(cliente.getEmail());
            newCliente.setSenha(passwordEncoder.encode(cliente.getSenha()));

            return clienteRepository.save(newCliente);
        } catch (Exception e) {
            throw new MyException(404, "Id não existente", "Cli001");
        }
    }

    public LoginClienteResponse autenticar(LoginDto loginDto) throws MyException {
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

        LoginClienteResponse response = new LoginClienteResponse();
        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
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

    public List<ClienteHistoricoResponse> getAllHistoricoByIdCliente(Integer id) throws IOException, ParseException {
        List<Object[]> list = clienteRepository.getAllHistoricoByIdCliente(id, id);
        List<Object[]> listReservas = clienteRepository.getAllReservasByIdCliente(id);
        List<ClienteHistoricoResponse> checkoutResponseList = new ArrayList<>();
        for (Object[] item : listReservas) {
            String data = item[2].toString().substring(0, 10);
            String hora = item[2].toString().substring(11, 19);
            ViaCepService viaCepService = new ViaCepService();
            EnderecoEstacionamento endereco = viaCepService.getEndereco(item[1].toString());
            String rua = endereco.logradouro + ", " + endereco.bairro + ", " + endereco.localidade + ", " + endereco.uf;
            checkoutResponseList.add(new ClienteHistoricoResponse(
                    item[0].toString(), rua, data.toString(), hora.toString(),
                    item[3].toString(), item[4].toString(), item[5].toString(), item[6].toString()
            ));
        }
        for (Object[] item : list) {
            String data = item[2].toString().substring(0, 10);
            String hora = item[2].toString().substring(11, 19);
            ViaCepService viaCepService = new ViaCepService();
            EnderecoEstacionamento endereco = viaCepService.getEndereco(item[1].toString());
            String rua = endereco.logradouro + ", " + endereco.bairro + ", " + endereco.localidade + ", " + endereco.uf;
            checkoutResponseList.add(new ClienteHistoricoResponse(
                    item[0].toString(), rua, data.toString(), hora.toString(),
                    item[3].toString(), item[4].toString(), item[5].toString(), "-1"
            ));
        }
        return checkoutResponseList;
    }

    public ResponseEntity alterarSenha(Integer id, String oldPass, String newPass) throws MyException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new MyException(404, "Não existe", "F-012")
        );
        if (passwordEncoder.matches(oldPass, cliente.getSenha())) {
            cliente.setSenha(passwordEncoder.encode(newPass));
            clienteRepository.save(cliente);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).body("Senha inválida");
    }
}
