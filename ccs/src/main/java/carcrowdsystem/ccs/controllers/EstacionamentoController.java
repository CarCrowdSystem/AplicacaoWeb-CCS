package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.adapter.EstacionamentoAdapter;
import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.Estacionamento;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.mapper.EstacionamentoMapper;
import carcrowdsystem.ccs.models.EnderecoEstacionamento;
import carcrowdsystem.ccs.request.EstacionamentoRequest;
import carcrowdsystem.ccs.response.EstacionamentoAllMobileResponse;
import carcrowdsystem.ccs.services.EstacionamentoService;
import carcrowdsystem.ccs.services.ViaCepService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${uri.dev}/estacionamentos")
@Tag(name = "Estacionamento", description = "Gerencia a entidade estacionamento")
public class EstacionamentoController {

    @Autowired
    private final EstacionamentoAdapter estacionamentoAdapter = new EstacionamentoAdapter();

    @Autowired
    private final EstacionamentoService estacionamentoService = new EstacionamentoService();

    private final EstacionamentoMapper estacionamentoMapper = new EstacionamentoMapper();

    @GetMapping("/{id}")
    public ResponseEntity<EstacionamentoDto> getEstacionamentoPorId(
            @PathVariable Integer id
    ) throws MyException {
        return ResponseEntity.status(201).body(
                estacionamentoMapper.toEstacionamentoDto(estacionamentoAdapter.getEstacionamentoPorId(id))
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<EstacionamentoAllMobileResponse>>
        getEstacionamentoPorId() throws IOException, ParseException {
        return ResponseEntity.status(201).body(
                estacionamentoAdapter.getAllEstacionamentosMobile()
        );
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Estacionamento> getEstacionamentoPorCnpj(
            @PathVariable String cnpj
    ) throws MyException {
        return ResponseEntity.status(201).body(
                estacionamentoAdapter.getEstacionamentoPorCnpj(cnpj)
        );
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Estacionamento cadastrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar estacionamento",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping
    public ResponseEntity<EstacionamentoDto> postEstacionamento(
            @RequestBody EstacionamentoRequest estacionamento
    ) throws MyException {
        Estacionamento newEstacionamento = new Estacionamento();
        newEstacionamento.setCnpj(estacionamento.getCnpjEmpresa());
        newEstacionamento.setCep(estacionamento.getCepEmpresa());
        newEstacionamento.setNomeEstacionamento(estacionamento.getNomeEmpresa());
        newEstacionamento.setTelefone(estacionamento.getTelefoneEmpresa());
        newEstacionamento.setNumeroEndereco(estacionamento.getEnderecoEmpresa());
        return ResponseEntity.status(201).body(estacionamentoAdapter.create(newEstacionamento));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de estacionamentos"),
            @ApiResponse(responseCode = "204", description = "Lista vazia",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping
    public ResponseEntity<List<EstacionamentoDto>> getAllEstacionamento(){
        return ResponseEntity.status(200).body(estacionamentoAdapter.getAll());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Estacionamento alterado"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar estacionamento",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @PutMapping("/{id}")
    public ResponseEntity patchEstacionamento(
            @PathVariable Integer id,
            @RequestBody EstacionamentoRequest estacionamento
    ) throws MyException {
        estacionamentoService.patchEstacionamento(id, estacionamento);
        return ResponseEntity.status(201).build();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estacionamento excluido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEstacionamento(@PathVariable Integer id) throws MyException {
        if (estacionamentoAdapter.delete(id)){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca endereço por cep"),
            @ApiResponse(responseCode = "404", description = "CEP não encontrado",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/buscar/{cep}")
    public ResponseEntity<EnderecoEstacionamento> buscarEndereco(@PathVariable String cep){
        ViaCepService viaCepService = new ViaCepService();

        try {
            EnderecoEstacionamento endereco = viaCepService.getEndereco(cep);
            return ResponseEntity.status(200).body(endereco);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Estacionamento pegarUltimoEstacionamento(){
        return estacionamentoAdapter.pegarUltimoEstacionamento();
    }
}
