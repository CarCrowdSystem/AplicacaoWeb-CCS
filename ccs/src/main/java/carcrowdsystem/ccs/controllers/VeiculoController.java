package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.veiculo.VeiculoMobileRequest;
import carcrowdsystem.ccs.dtos.veiculo.VeiculoMobileResponse;
import carcrowdsystem.ccs.dtos.veiculo.VeiculoRequest;
import carcrowdsystem.ccs.entitys.Cliente;
import carcrowdsystem.ccs.entitys.Veiculo;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.request.ClienteRequest;
import carcrowdsystem.ccs.services.VeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/veiculo")
public class VeiculoController {
    private final VeiculoService service;
    private final ClienteController clienteController;

    public VeiculoController(VeiculoService service, ClienteController clienteController) {
        this.service = service;
        this.clienteController = clienteController;
    }

    @PostMapping
    public ResponseEntity postVeiculo(
            @RequestBody VeiculoRequest newVeiculo
    ) throws MyException {
        try {
            ClienteRequest newCliente = new ClienteRequest();
            newCliente.setNome(newVeiculo.getNomeCliente());
            newCliente.setEmail(newVeiculo.getEmail());
            newCliente.setSenha(newVeiculo.getSenha());
            Cliente cliente = clienteController.postCliente(newCliente).getBody();
            VeiculoMobileRequest veiculo = new VeiculoMobileRequest();
            veiculo.setId_cliente(cliente.getId());
            veiculo.setMarca(newVeiculo.getMarca());
            veiculo.setModelo(newVeiculo.getModelo());
            veiculo.setPlaca(newVeiculo.getPlaca());
            service.postVeiculoMobile(veiculo);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            throw new MyException(404, "Placa já cadastrada", "V-007");
        }
    }

    @PostMapping("/mobile")
    public ResponseEntity postVeiculoMobile(
            @RequestBody VeiculoMobileRequest newVeiculo
    ) throws MyException {
        try {
            return ResponseEntity.ok(service.postVeiculoMobile(newVeiculo));
        }catch (Exception e){
            throw new MyException(404, "Placa já cadastrada", "V-008");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<VeiculoMobileResponse>> getAllVeiculoById(
            @RequestParam Integer id
    ) throws MyException {
        return ResponseEntity.ok(service.findAllById(id));
    }

    @GetMapping
    public ResponseEntity<Veiculo> getVeiculoById(
        @RequestParam Integer idVeiculo
    ) throws MyException {
        return ResponseEntity.ok(service.findById(idVeiculo));
    }

    @DeleteMapping
    public ResponseEntity delVeiculoById(
            @RequestParam Integer id
    ) throws MyException {
        service.delVeiculo(id);
        return ResponseEntity.ok().build();
    }

    public Veiculo findByPlaca(String placa) {
        return service.findByPlaca(placa).get();
    }
}
