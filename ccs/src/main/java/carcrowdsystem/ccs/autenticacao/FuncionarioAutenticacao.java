package carcrowdsystem.ccs.autenticacao;

import carcrowdsystem.ccs.abstracts.Funcionario;
import carcrowdsystem.ccs.dtos.FuncionarioDetailsDto;
import carcrowdsystem.ccs.repositorys.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioAutenticacao implements UserDetailsService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Funcionario> funcOpt = funcionarioRepository.findByEmail(username);
        if (funcOpt.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuario: %s nao encontrado", username));
        }
        return new FuncionarioDetailsDto(funcOpt.get());
    }
}