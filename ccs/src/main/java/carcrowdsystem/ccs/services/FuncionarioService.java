package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.abstracts.Funcionario;
import carcrowdsystem.ccs.dtos.FuncionarioDto;
import carcrowdsystem.ccs.repositorys.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioDto create(Funcionario funcionario){
        funcionarioRepository.save(funcionario);
        FuncionarioDto funcDto = funcionario.toFuncionarioDto();
        return funcDto;
    }

    public List<FuncionarioDto> list(){
        return funcionarioRepository.findAllToFuncionarioDto();
    }

//    public String login(String email, String senha) {
//        for (Funcionario f: funcs){
//            if (f.getEmail().equals(email) && f.getSenha().equals(senha)){
//                f.setLogado(true);
//                return "Usuário: " + f.getNome() + " foi logado com sucesso";
//            }
//        }
//        return "Falha ao logar";
//    }
//
//    public String logoff(String email) {
//        for (Funcionario f: funcs){
//            if (f.getEmail().equals(email) && f.getLogado()){
//                f.setLogado(false);
//                return "Usuário: " + f.getNome() + " foi deslogado com sucesso";
//            }
//        }
//        return "Usuário não está logado";
//    }
}
