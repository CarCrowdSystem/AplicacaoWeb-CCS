package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.adapter.FuncionarioAdapter;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioLoginDto;
import carcrowdsystem.ccs.entitys.Funcionario;
import carcrowdsystem.ccs.entitys.Vaga;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.request.FuncionarioRequest;
import carcrowdsystem.ccs.response.LoginResponse;
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
    public ResponseEntity<LoginResponse> login(@RequestBody FuncionarioLoginDto funcionarioLoginDto) {
        return ResponseEntity.status(200).body(funcionarioAdapter.autenticar(funcionarioLoginDto));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de funcionários"),
            @ApiResponse(responseCode = "204", description = "Lista vazia", content = @Content(schema =
            @Schema(hidden = true)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<FuncionarioDto>> getFuncionariosEmpresa(
            @PathVariable Integer id
    ) throws MyException {
        return ResponseEntity.status(200).body(funcionarioAdapter.getAllFuncs(id));
    }

    @GetMapping("/buscar-nome-funcionario/{idEstacionamento}/{nome}")
    public ResponseEntity<List<FuncionarioDto>> getFuncionarioPorNome(
            @PathVariable String nome,
            @PathVariable Integer idEstacionamento
    ) throws MyException {
        return ResponseEntity.status(200).body(funcionarioAdapter.getFuncionariosPorNomeEIdEstacionamento(nome, idEstacionamento).getBody());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ordenação feita com sucesso"),
    })
    @GetMapping("/nome-ordenado-a/{id}")
    public ResponseEntity<FuncionarioDto[]> getFuncOrdenadoA(
            @PathVariable Integer id
    ) throws MyException {
        return ResponseEntity.status(200).body(funcionarioAdapter.getALLOrdenado(id));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ordenação feita com sucesso"),
    })
    @GetMapping("/nome-ordenado-z/{id}")
    public ResponseEntity<FuncionarioDto[]> getFuncOrdenadoZ(
            @PathVariable Integer id
    ) throws MyException {
        FuncionarioDto[] allOrdenadoA = funcionarioAdapter.getALLOrdenado(id);
        FuncionarioDto[] allOrdenadoZ = new FuncionarioDto[allOrdenadoA.length];
        int j = 0;
        for (int i = allOrdenadoA.length - 1; i >= 0; i--, j++){
            allOrdenadoZ[j] = allOrdenadoA[i];
        }
        return ResponseEntity.status(200).body(allOrdenadoZ);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nome encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nome não encontrado", content = @Content(schema =
            @Schema(hidden = true)))
    })
    @GetMapping("/busca-nome/{nome}/{id}")
    public ResponseEntity<FuncionarioDto> getFuncByName(@PathVariable String nome, @PathVariable Integer id) throws MyException {
        return funcionarioAdapter.binarySearch(nome, id);
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFuncionario(@PathVariable Integer id) throws MyException {
        funcionarioAdapter.delete(id);
        return ResponseEntity.noContent().build();
    }
}
