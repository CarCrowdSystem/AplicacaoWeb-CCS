package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.dtos.veiculo.VeiculoRequest;
import carcrowdsystem.ccs.entitys.Veiculo;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeiculoService {
    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public VeiculoRequest postVeiculo(VeiculoRequest newVeiculo) throws MyException {
        try {
            Veiculo veiculo = new Veiculo();
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

    public Veiculo findById(Integer idVeiculo) throws MyException {
        return repository.findById(idVeiculo).orElseThrow(
            () -> new MyException(404, "Veiculo não existe", "VE-002")
        );
    }

    public Optional<Veiculo> findByPlaca(String placa){
        return repository.findByPlacaEqualsIgnoreCase(placa);
    }
}
