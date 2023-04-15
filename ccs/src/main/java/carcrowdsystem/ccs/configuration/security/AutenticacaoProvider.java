package carcrowdsystem.ccs.configuration.security;

import carcrowdsystem.ccs.autenticacao.FuncionarioAutenticacao;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AutenticacaoProvider implements AuthenticationProvider {
    private final FuncionarioAutenticacao funcionarioAutenticacao;
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoProvider(
        FuncionarioAutenticacao funcionarioAutenticacao,
        PasswordEncoder passwordEncoder
    ) {
        this.funcionarioAutenticacao = funcionarioAutenticacao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(
        Authentication authentication
    ) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        UserDetails userDetails = funcionarioAutenticacao.loadUserByUsername(username);

        if (passwordEncoder.matches(password, userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
            );
        } else {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
