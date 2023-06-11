package carcrowdsystem.ccs.adapter;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioLoginDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.request.FuncionarioRequest;
import carcrowdsystem.ccs.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioAdapter implements DbAdapter<FuncionarioDto, FuncionarioRequest>{
    @Autowired
    private FuncionarioService service;

    @Override
    public FuncionarioDto create(FuncionarioRequest entrada) throws MyException {
        return service.postFuncionario(entrada);
    }

    @Override
    public void update(Integer id, FuncionarioRequest entrada) {
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    public FuncionarioTokenDto autenticar(FuncionarioLoginDto func) {
        return service.autenticar(func);
    }

    public List<FuncionarioDto> getAllFuncs() throws MyException {
        return service.getAllFuncs();
    }

    public ResponseEntity<FuncionarioDto> binarySearch(String nome) throws MyException {
        return service.binarySearch(nome);
    }

    public FuncionarioDto[] getALLOrdenado() throws MyException {
        return service.getAllFuncsOrderByName();
    }

    public ResponseEntity alterarSenha(String email, String novaSenha) {
        return service.alterarSenha(email, novaSenha);
    }
}
