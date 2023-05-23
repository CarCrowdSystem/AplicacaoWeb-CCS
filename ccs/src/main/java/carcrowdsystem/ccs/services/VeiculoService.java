package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.controllers.HistoricoController;
import carcrowdsystem.ccs.dtos.veiculo.VeiculoRequest;
import carcrowdsystem.ccs.entitys.HistoricoEntity;
import carcrowdsystem.ccs.entitys.VeiculoEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.VeiculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {
    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public VeiculoRequest postVeiculo(VeiculoRequest newVeiculo) throws MyException {
        try {
            VeiculoEntity veiculo = new VeiculoEntity();
            veiculo.setModelo(newVeiculo.getModelo());
            veiculo.setPlaca(newVeiculo.getPlaca());
            veiculo.setNomeCliente(newVeiculo.getNomeCliente());
            veiculo.setTelefoneCliente(newVeiculo.getTelefoneCliente());
            repository.save(veiculo);
            return newVeiculo;
        } catch (Exception e) {
            throw new MyException(e.hashCode(), "Body incorreto", "VE-001");
        }
    }

    public VeiculoEntity findById(Integer idVeiculo) throws MyException {
        return repository.findById(idVeiculo).orElseThrow(
            () -> new MyException(404, "Ve  iculo não existe", "VE-002")
        );
    }
}
