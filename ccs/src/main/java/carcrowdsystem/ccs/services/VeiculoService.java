package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.dtos.veiculo.VeiculoMobileRequest;
import carcrowdsystem.ccs.dtos.veiculo.VeiculoMobileResponse;
import carcrowdsystem.ccs.dtos.veiculo.VeiculoRequest;
import carcrowdsystem.ccs.entitys.Cliente;
import carcrowdsystem.ccs.entitys.Veiculo;
import carcrowdsystem.ccs.enums.StatusVeiculo;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.repositorys.ClienteRepository;
import carcrowdsystem.ccs.repositorys.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    private final VeiculoRepository repository;
    private final ClienteRepository cienteRepository;

    public VeiculoService(VeiculoRepository repository, ClienteRepository cienteRepository) {
        this.repository = repository;
        this.cienteRepository = cienteRepository;
    }

    public VeiculoRequest postVeiculo(VeiculoRequest newVeiculo) throws MyException {
        try {
            Veiculo veiculo = new Veiculo();
            veiculo.setModelo(newVeiculo.getModelo());
            veiculo.setPlaca(newVeiculo.getPlaca());
            veiculo.setMarca(newVeiculo.getMarca());
            repository.save(veiculo);
            return newVeiculo;
        } catch (Exception e) {
            throw new MyException(e.hashCode(), "Body incorreto", "VE-001");
        }
    }
    public VeiculoMobileRequest postVeiculoMobile(VeiculoMobileRequest newVeiculo) throws MyException {
        try {
            Cliente cliente = cienteRepository.findById(newVeiculo.getId_cliente()).orElseThrow(
                    () -> new MyException(404, "Cliente não encontrado", "VEM-001")
            );
            Veiculo veiculo = new Veiculo();
            veiculo.setModelo(newVeiculo.getModelo());
            veiculo.setPlaca(newVeiculo.getPlaca());
            veiculo.setMarca(newVeiculo.getMarca());
            veiculo.setCliente(cliente);
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

    public List<VeiculoMobileResponse> findAllById(Integer idCliente) {
        List<Veiculo> list = repository.findAllByIdCliente(idCliente);
        List<VeiculoMobileResponse> listResponse = new ArrayList<>();
        for(Veiculo veiculo: list){
            listResponse.add(new VeiculoMobileResponse(
                veiculo.getPlaca(), veiculo.getMarca(), veiculo.getModelo(), veiculo.getId()
            ));
        };
        return listResponse;
    }

    public void delVeiculo(Integer id) throws MyException {
        Veiculo veiculo = repository.findById(id).orElseThrow(
                () -> new MyException(404, "Veiculo não existe", "VE-003")
        );
        veiculo.setAtivo(StatusVeiculo.NaoAtivo);
        repository.save(veiculo);
    }
}
