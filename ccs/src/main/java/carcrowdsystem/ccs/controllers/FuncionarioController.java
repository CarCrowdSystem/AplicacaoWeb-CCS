package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.adapter.FuncionarioAdapter;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioLoginDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.request.FuncionarioRequest;
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
@RequestMapping("${uri.dev}/funcionarios")
@Tag(name = "Funcionário", description = "Gerencia a entidade funcionário")
public class FuncionarioController {
    @Autowired
    FuncionarioAdapter funcionarioAdapter = new FuncionarioAdapter();

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário salvo"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar usuário", content = @Content(schema =
            @Schema(hidden = true)))
    })

    @PostMapping
    public ResponseEntity<FuncionarioDto> postUsuario(
            @RequestBody FuncionarioRequest funcionario
    ) throws MyException {
        return ResponseEntity.status(201).body(funcionarioAdapter.create(funcionario));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login bem sucedido"),
            @ApiResponse(responseCode = "400", description = "Erro ao logar", content = @Content(schema =
            @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema =
            @Schema(hidden = true)))
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody FuncionarioLoginDto funcionarioLoginDto) {
        return ResponseEntity.status(200).body(funcionarioAdapter.autenticar(funcionarioLoginDto).getToken());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de funcionários"),
            @ApiResponse(responseCode = "204", description = "Lista vazia", content = @Content(schema =
            @Schema(hidden = true)))
    })
    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> getFuncionarios() throws MyException {
        return ResponseEntity.status(200).body(funcionarioAdapter.getAllFuncs());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ordenação feita com sucesso"),
    })
    @GetMapping("/nome-ordenado")
    public ResponseEntity<FuncionarioDto[]> getFuncOrdenado() throws MyException {
        return ResponseEntity.status(200).body(funcionarioAdapter.getALLOrdenado());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nome encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nome não encontrado", content = @Content(schema =
            @Schema(hidden = true)))
    })
    @GetMapping("/busca-nome/{nome}")
    public ResponseEntity<FuncionarioDto> getFuncByName(@PathVariable String nome) throws MyException {
        return funcionarioAdapter.binarySearch(nome);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Alteração de senha bem sucedida"),
            @ApiResponse(responseCode = "404", description = "E-mail não encontrado", content = @Content(schema =
            @Schema(hidden = true)))
    })
    @PatchMapping("/alterar-senha/{email}/{novaSenha}")
    public ResponseEntity patchSenha(@RequestParam String email, @RequestParam String novaSenha){
        return funcionarioAdapter.alterarSenha(email, novaSenha);
    }
}
