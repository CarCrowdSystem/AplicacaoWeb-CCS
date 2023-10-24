package carcrowdsystem.ccs.autenticacao;

import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDetailsDto;
import carcrowdsystem.ccs.entitys.Login;
import carcrowdsystem.ccs.repositorys.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioAutenticacao implements UserDetailsService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = funcionarioRepository.findByEmailLogin(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Usuario: %s nao encontrado", username))
        );
        return new FuncionarioDetailsDto(login);
    }
}
