package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioLoginDto;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.request.ClienteRequest;
import carcrowdsystem.ccs.response.LoginResponse;
import carcrowdsystem.ccs.services.ClienteService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("${uri.dev}/cliente")
@Tag(name = "Funcionário", description = "Gerencia a entidade funcionário")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente salvo"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar cliente", content = @Content(schema =
            @Schema(hidden = true)))
    })
    @PostMapping
    public ResponseEntity postCliente(ClienteRequest cliente) throws MyException {
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
    public ResponseEntity<LoginResponse> login(@RequestBody FuncionarioLoginDto funcionarioLoginDto) {
        return ResponseEntity.status(200).body(clienteService.autenticar(funcionarioLoginDto));
    }
}
