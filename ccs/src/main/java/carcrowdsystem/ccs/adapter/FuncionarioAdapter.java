package carcrowdsystem.ccs.adapter;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioLoginDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FuncionarioAdapter implements DbAdapter<FuncionarioDto, FuncionarioEntity>{
    @Autowired
    private FuncionarioService service;

    @Override
    public FuncionarioDto create(FuncionarioEntity entrada) throws MyException {
        return service.postFuncionario(entrada);
    }

    @Override
    public void update(Integer id, FuncionarioEntity entrada) {
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
