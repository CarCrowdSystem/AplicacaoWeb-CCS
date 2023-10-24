package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.funcionario.LoginDto;
import carcrowdsystem.ccs.entitys.Cliente;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.request.ClienteRequest;
import carcrowdsystem.ccs.request.dtos.ClienteUpdateRequest;
import carcrowdsystem.ccs.response.ClienteResponse;
import carcrowdsystem.ccs.response.LoginClienteResponse;
import carcrowdsystem.ccs.response.LoginResponse;
import carcrowdsystem.ccs.services.ClienteService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/clientes")
@Tag(name = "Funcionário", description = "Gerencia a entidade funcionário")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllCliente() throws MyException {
        return ResponseEntity.status(200).body(clienteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> getCliente(@PathVariable Integer id) throws MyException {
        return ResponseEntity.status(200).body(clienteService.getCliente(id));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente salvo"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar cliente", content = @Content(schema =
            @Schema(hidden = true)))
    })
    @PostMapping
    public ResponseEntity postCliente(@RequestBody ClienteRequest cliente) throws MyException {
        clienteService.postCliente(cliente);
        return ResponseEntity.status(201).build();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login bem sucedido"),
            @ApiResponse(responseCode = "400", description = "Erro ao logar", content = @Content(schema =
            @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema =
            @Schema(hidden = true)))
    })
    @PostMapping("/login")
    public ResponseEntity<LoginClienteResponse> login(@RequestBody LoginDto loginDto) throws MyException {
        return ResponseEntity.status(200).body(clienteService.autenticar(loginDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(
            @RequestBody ClienteUpdateRequest clienteUpdateRequest,
            @PathVariable Integer id
    ) throws MyException {
        clienteService.patch(clienteUpdateRequest, id);
        return ResponseEntity.status(200).build();
    }
}
