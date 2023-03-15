package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.abstracts.Funcionario;
import carcrowdsystem.ccs.dtos.FuncionarioDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {
    List<Funcionario> funcs;
    List<FuncionarioDto> funcsDto;
    public FuncionarioService() {
        this.funcs = new ArrayList<>();
        this.funcsDto = new ArrayList<>();
    }

    public FuncionarioDto create(Funcionario funcionario){
        funcs.add(funcionario);
        FuncionarioDto funcDto = funcionario.toFuncionarioDto();
        funcsDto.add(funcDto);
        return funcDto;
    }

    public List<FuncionarioDto> list(){
        return funcsDto;
    }

    public String login(String email, String senha) {
        for (Funcionario f: funcs){
            if (f.getEmail().equals(email) && f.getSenha().equals(senha)){
                f.setLogado(true);
                return "Usuário: " + f.getNome() + " foi logado com sucesso";
            }
        }
        return "Falha ao logar";
    }
}
