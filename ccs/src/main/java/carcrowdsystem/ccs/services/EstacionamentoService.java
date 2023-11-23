package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.dtos.estacionamento.EstacionamentoDto;
import carcrowdsystem.ccs.entitys.Estacionamento;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.mapper.EstacionamentoMapper;
import carcrowdsystem.ccs.models.EnderecoEstacionamento;
import carcrowdsystem.ccs.repositorys.EstacionamentoRepository;
import carcrowdsystem.ccs.response.EstacionamentoAllMobileResponse;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionamentoService {
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    private EstacionamentoMapper estacionamentoMapper = new EstacionamentoMapper();

    public EstacionamentoDto postEstacionamento(Estacionamento estacionamento) throws MyException {
        try {
            estacionamentoRepository.save(estacionamento);
        } catch (Exception e){
            throw new MyException(
                400,
                "Nome deve ter pelo menos 3 caracteres; " +
                "Formato do Cep 00000-000; " +
                "O numeroEndereco não pode ser: null, '' ou ' '; " +
                "Formato do telefone 0000-0000",
                "E-002");
        }
        return estacionamentoMapper.toEstacionamentoDto(estacionamento);
    }

    public List<EstacionamentoDto> getAllEstacionamento() {
        return toListDto(estacionamentoRepository.findAll());
    }

    public void patchEstacionamento(Integer id, Estacionamento estacionamento) {
        Optional<Estacionamento> estacionamentoAntigo = estacionamentoRepository.findById(id);
        if (estacionamentoAntigo.isEmpty()){
            estacionamentoRepository.save(estacionamento);
            return;
        }
        if(estacionamento.getNomeEstacionamento() != null){
            estacionamentoAntigo.get().setNomeEstacionamento(estacionamento.getNomeEstacionamento());
        }
        if(estacionamento.getCep() != null){
            estacionamentoAntigo.get().setCep(estacionamento.getCep());
        }
        if(estacionamento.getTelefone() != null){
            estacionamentoAntigo.get().setTelefone(estacionamento.getTelefone());
        }
        if(estacionamento.getNumeroEndereco() != null){
            estacionamentoAntigo.get().setNumeroEndereco(estacionamento.getNumeroEndereco());
        }
        estacionamentoRepository.save(estacionamentoAntigo.get());
    }

    public Boolean deleteEstacionamento(Integer id) {
        Optional<Estacionamento> estacionamento = estacionamentoRepository.findById(id);
        if (estacionamento.isEmpty()){
            return false;
        }
        estacionamento.get().setStatusEstacionamento(false);
        estacionamentoRepository.save(estacionamento.get());
        return true;
    }

    private List<EstacionamentoDto> toListDto(List<Estacionamento> estacionamentos){
        List<EstacionamentoDto> estacionamentoDtos = new ArrayList<>();
        for (Estacionamento e: estacionamentos){
            estacionamentoDtos.add(estacionamentoMapper.toEstacionamentoDto(e));
        }
        return estacionamentoDtos;
    }

    public Estacionamento findById(Integer id) throws MyException {
        try {
            return estacionamentoRepository.findById(id).get();
        } catch (Exception e) {
            throw new MyException(404, "Id não existente", "E005");
        }
    }

    public Estacionamento findByCnpj(String cnpj) throws MyException {
        try {
            return estacionamentoRepository.findByCnpj(cnpj).get();
        } catch (Exception e) {
            throw new MyException(404, "Cnpj não existente", "E006");
        }
    }

    public Estacionamento findByTop() {
        return estacionamentoRepository.findTopByOrderByIdEstacionamentoDesc();
    }

    public List<EstacionamentoAllMobileResponse> getAllEstacionamentosMobile() throws IOException, ParseException {
        List<Object[]> list = estacionamentoRepository.getAllEstacionamentosMobile();
        List<EstacionamentoAllMobileResponse> listReturn = new ArrayList<>();
        for (Object[] item : list) {
            ViaCepService viaCepService = new ViaCepService();
            EnderecoEstacionamento endereco = viaCepService.getEndereco(item[2].toString());
            String rua = endereco.logradouro +" n"+ item[3].toString() +
                    ", " + endereco.bairro + ", " + endereco.localidade + ", " + endereco.uf;
            listReturn.add(new EstacionamentoAllMobileResponse(
                    item[0].toString(), item[1].toString(), rua,
                    item[2].toString(), item[4].toString(), item[5].toString(),
                    item[6].toString(), item[8].toString(), item[7].toString()
                ));
        }
        return listReturn;
    }
}
