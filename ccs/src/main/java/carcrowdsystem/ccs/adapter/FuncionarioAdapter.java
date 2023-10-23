package carcrowdsystem.ccs.adapter;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.LoginDto;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.request.FuncionarioRequest;
import carcrowdsystem.ccs.response.LoginResponse;
import carcrowdsystem.ccs.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioAdapter implements DbAdapter<FuncionarioDto, FuncionarioRequest> {
    public ResponseEntity<List<FuncionarioDto>> getFuncionariosPorNome(String nome) throws MyException {
        return service.getFuncionariosPorNome(nome);
    }

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
    public Boolean delete(Integer id) throws MyException {
        return service.deleteFunc(id);
    }

    public LoginResponse autenticar(LoginDto func) {
        return service.autenticar(func);
    }

    public List<FuncionarioDto> getAllFuncs(Integer id) throws MyException {
        return service.getAllFuncs(id);
    }

    public ResponseEntity<FuncionarioDto> binarySearch(String nome, Integer id) throws MyException {
        return service.binarySearch(nome, id);
    }

    public FuncionarioDto[] getALLOrdenado(Integer id) throws MyException {
        return service.getAllFuncsOrderByName(id);
    }

    public ResponseEntity alterarSenha(String email, String novaSenha) {
        return service.alterarSenha(email, novaSenha);
    }
}