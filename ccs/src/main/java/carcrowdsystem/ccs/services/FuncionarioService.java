package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.Entitys.FuncionarioEntity;
import carcrowdsystem.ccs.abstracts.Funcionario;
import carcrowdsystem.ccs.configuration.security.jwt.GerenciadorTokenJwt;
import carcrowdsystem.ccs.dtos.FuncionarioDto;
import carcrowdsystem.ccs.dtos.FuncionarioDtoComSenha;
import carcrowdsystem.ccs.dtos.FuncionarioLoginDto;
import carcrowdsystem.ccs.dtos.FuncionarioTokenDto;
import carcrowdsystem.ccs.mapper.FuncionarioMapper;
import carcrowdsystem.ccs.repositorys.FuncionarioRepository;
import net.bytebuddy.implementation.ExceptionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;


@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    private FuncionarioMapper funcionarioMapper = new FuncionarioMapper();
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;

    public FuncionarioDto create(FuncionarioEntity newFunc){
        newFunc.setSenha(passwordEncoder.encode(newFunc.getSenha()));
        funcionarioRepository.save(newFunc);
        return funcionarioMapper.toFuncionarioDto(newFunc);
    }

    public FuncionarioTokenDto autenticar(FuncionarioLoginDto funcionarioLoginDto){
        final UsernamePasswordAuthenticationToken credentials =
            new UsernamePasswordAuthenticationToken(
                    funcionarioLoginDto.getEmail(), funcionarioLoginDto.getSenha()
            );

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        FuncionarioEntity funcionarioEntity =
                funcionarioMapper.toFuncionarioEntity(
                    funcionarioRepository.findByEmail(funcionarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return funcionarioMapper.toFuncionarioTokenDto(funcionarioEntity, token);
    }

    public List<FuncionarioDto> getAllFuncs() {
        if (funcionarioRepository.findAll().isEmpty()){
            throw new RuntimeException("Não tem nada no banco");
        }
        return listFuncToListFuncDto(funcionarioRepository.findAll());
    }

//    public List<FuncionarioDto> list(){
//        return funcionarioRepository.findAllToFuncionarioDto();
//    }

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

    private List<FuncionarioDto> listFuncToListFuncDto(List<FuncionarioEntity> funcs){
        List<FuncionarioDto> funcsDto = new ArrayList<>();
        for(FuncionarioEntity f: funcs){
            funcsDto.add(funcionarioMapper.toFuncionarioDto(f));
        }
        return funcsDto;
    }
}
