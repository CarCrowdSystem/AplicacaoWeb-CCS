package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.entitys.EstacionamentoEntity;
import carcrowdsystem.ccs.entitys.FuncionarioEntity;
import carcrowdsystem.ccs.configuration.security.jwt.GerenciadorTokenJwt;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioLoginDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.mapper.FuncionarioMapper;
import carcrowdsystem.ccs.repositorys.EstacionamentoRepository;
import carcrowdsystem.ccs.repositorys.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
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
import java.util.Optional;


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

    public FuncionarioDto create(Integer idEstacionamento, FuncionarioEntity newFunc){
        try {
            EstacionamentoEntity estacionamento =
                estacionamentoRepository.findById(idEstacionamento).get();
            newFunc.setSenha(passwordEncoder.encode(newFunc.getSenha()));
            newFunc.setEstacionamento(estacionamento);
            funcionarioRepository.save(newFunc);
            return funcionarioMapper.toFuncionarioDto(newFunc);
        } catch (NoSuchElementException e) {
            throw e;
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

    public List<FuncionarioDto> getAllFuncs() {
        if (funcionarioRepository.findAll().isEmpty()){
            throw new RuntimeException("Não tem nada no banco");
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

    public FuncionarioDto[] getAllFuncsOrderByName(){

      // FuncionarioDto[] funcionarios = {new FuncionarioDto("Rogério", "7824836483", "93847878574", "Funcionario", "fnrunfurn@gmail.com", "1193823938"),
         //       new FuncionarioDto("Carlos", "7824836483", "93847878574", "Funcionario", "fnrunfurn@gmail.com", "1193823938")};

        //Transforma em Array
        FuncionarioDto[] funcionarios = getAllFuncs().toArray(new FuncionarioDto[0]);

         // Imprime antes de ordenar
         System.out.println("Antes de ordenar: \n");
        for (FuncionarioDto func : funcionarios){
            System.out.printf("\nNome: %s - RG: %s - CPF: %s - cargo: %s - Email: %s - Telefone: %s", func.getNome(), func.getRg(), func.getCpf(), func.getCargo(), func.getEmail(), func.getTelefone());
        }

        // Algoritmo de oredenação
        for (int i = 0; i < funcionarios.length - 1; i++){
            int indMenor = i;
            for (int j = i + 1; j < funcionarios.length; j++){
                if (funcionarios[j].getNome().compareToIgnoreCase(funcionarios[indMenor].getNome()) < 0) {
                    indMenor = j;
                }
            }
            if (indMenor != i) {
                FuncionarioDto temp = funcionarios[i];
                funcionarios[i] =funcionarios[indMenor];
                funcionarios[indMenor] = temp;
            }
        }

        // Imprime resultado
        System.out.println("Exibindo lista ordenada:");
        for (FuncionarioDto func : funcionarios){
            System.out.printf("\nNome: %s - RG: %s - CPF: %s - cargo: %s - Email: %s - Telefone: %s", func.getNome(), func.getRg(), func.getCpf(), func.getCargo(), func.getEmail(), func.getTelefone());
        }
        return funcionarios;
    }

    public ResponseEntity<FuncionarioDto> binarySearch(String name) {
        FuncionarioDto[] funcArray = getAllFuncsOrderByName();
        int inicio = 0;
        int fim = funcArray.length - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            String nome = funcArray[meio].getNome();
            if (nome.equals(name)) {
                return ResponseEntity.status(200).body(funcArray[meio]);
            } else if (nome.compareToIgnoreCase(name) < 0) {
                inicio = meio + 1;
            } else {
                fim = fim == 1 ? 0 : meio - 1;
            }
        }

        return ResponseEntity.status(404).build(); // Funcionário não encontrado
    }
}
