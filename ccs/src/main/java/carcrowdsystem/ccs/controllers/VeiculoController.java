package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.dtos.veiculo.VeiculoMobileRequest;
import carcrowdsystem.ccs.dtos.veiculo.VeiculoMobileResponse;
import carcrowdsystem.ccs.dtos.veiculo.VeiculoRequest;
import carcrowdsystem.ccs.entitys.Veiculo;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.VeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${uri.dev}/veiculo")
public class VeiculoController {
    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity postVeiculo(
            @RequestBody VeiculoRequest newVeiculo
    ) throws MyException {
        try {
            return ResponseEntity.ok(service.postVeiculo(newVeiculo));
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
            @RequestParam Integer idVeiculo
    ) throws MyException {
        return ResponseEntity.ok(service.findAllById(idVeiculo));
    }

    @GetMapping
    public ResponseEntity<Veiculo> getVeiculoById(
        @RequestParam Integer idVeiculo
    ) throws MyException {
        return ResponseEntity.ok(service.findById(idVeiculo));
    }

    public Veiculo findByPlaca(String placa) {
        return service.findByPlaca(placa).get();
    }
}
