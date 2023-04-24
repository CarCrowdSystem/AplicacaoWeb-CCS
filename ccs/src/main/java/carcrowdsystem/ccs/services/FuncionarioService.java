package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.configuration.security.jwt.GerenciadorTokenJwt;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioLoginDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.mapper.FuncionarioMapper;
import carcrowdsystem.ccs.repositorys.EstacionamentoRepository;
import carcrowdsystem.ccs.repositorys.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;
    private FuncionarioMapper funcionarioMapper = new FuncionarioMapper();

    public FuncionarioDto create(Integer idEstacionamento, FuncionarioEntity newFunc) throws MyException {
        try {
            EstacionamentoEntity estacionamento =
                estacionamentoRepository.findById(idEstacionamento).get();
            newFunc.setSenha(passwordEncoder.encode(newFunc.getSenha()));
            newFunc.setEstacionamento(estacionamento);
            funcionarioRepository.save(newFunc);
            return funcionarioMapper.toFuncionarioDto(newFunc);
        } catch (NoSuchElementException e){
            throw new MyException(404, "Id '"+idEstacionamento+"' do estacionamento não existe", "E-001");
        }
    }

    public FuncionarioTokenDto autenticar(FuncionarioLoginDto funcionarioLoginDto){
        final UsernamePasswordAuthenticationToken credentials =
            new UsernamePasswordAuthenticationToken(
                    funcionarioLoginDto.getEmail(), funcionarioLoginDto.getSenha()
            );

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        FuncionarioEntity funcionarioAutenticado =
            funcionarioRepository.findByEmail(funcionarioLoginDto.getEmail())
                .orElseThrow(
                        () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return funcionarioMapper.toFuncionarioTokenDto(funcionarioAutenticado, token);
    }

    public List<FuncionarioDto> getAllFuncs() throws MyException {
        if (funcionarioRepository.findAll().isEmpty()){
            throw new MyException(404, "Não existe nada no Banco de Dados", "G-002");
        }
        return listFuncToListFuncDto(funcionarioRepository.findAll());
    }

    // Pega uma lista de funcionarioEntity e transforma em lista de funcionarioDto
    private List<FuncionarioDto> listFuncToListFuncDto(List<FuncionarioEntity> funcs){
        List<FuncionarioDto> funcsDto = new ArrayList<>();
        for(FuncionarioEntity f: funcs){
            funcsDto.add(funcionarioMapper.toFuncionarioDto(f));
        }
        return funcsDto;
    }
}
