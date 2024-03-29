package carcrowdsystem.ccs.services;

import carcrowdsystem.ccs.entitys.Estacionamento;
import carcrowdsystem.ccs.entitys.Funcionario;
import carcrowdsystem.ccs.configuration.security.jwt.GerenciadorTokenJwt;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioDto;
import carcrowdsystem.ccs.dtos.funcionario.LoginDto;
import carcrowdsystem.ccs.dtos.funcionario.FuncionarioTokenDto;
import carcrowdsystem.ccs.exception.MyException;
import carcrowdsystem.ccs.mapper.FuncionarioMapper;
import carcrowdsystem.ccs.repositorys.FuncionarioRepository;
import carcrowdsystem.ccs.request.FuncionarioRequest;
import carcrowdsystem.ccs.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
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


@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private EstacionamentoService estacionamentoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;
    private FuncionarioMapper funcionarioMapper = new FuncionarioMapper();

    public FuncionarioDto postFuncionario(FuncionarioRequest newFunc) throws MyException {
        Funcionario funcionario = new Funcionario();
        try {
            funcionario.setNome(newFunc.getNomeUsuario());
            funcionario.setCpf(newFunc.getCpfUsuario());
            funcionario.setEmail(newFunc.getEmailUsuario());
            funcionario.setUsuarioAdm(newFunc.getAdm());
            funcionario.setSenha(passwordEncoder.encode(newFunc.getSenha()));
            Estacionamento estacionamento =
                    estacionamentoService.findById(newFunc.getIdEstacionamento());
            funcionario.setEstacionamento(estacionamento);

            funcionarioRepository.save(funcionario);
            return funcionarioMapper.toFuncionarioDto(funcionario);
        } catch (NoSuchElementException e){
            throw new MyException(404, "Id '"+newFunc.getIdEstacionamento()+"' do estacionamento não existe", "E-001");
        }
    }

    public LoginResponse autenticar(LoginDto loginDto) throws MyException {
        final UsernamePasswordAuthenticationToken credentials =
            new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(), loginDto.getSenha()
            );

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Funcionario funcionarioAutenticado =
            funcionarioRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(
                        () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                );

        if (!funcionarioAutenticado.getLoginHabilitado()) throw new MyException(401, "Funcionario desabilitado.", "DES001");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        FuncionarioTokenDto funcionarioTokenDto =
            funcionarioMapper.toFuncionarioTokenDto(funcionarioAutenticado, token);


        Estacionamento estacionamento = funcionarioRepository.findById(funcionarioTokenDto.getFuncId()).get().getEstacionamento();

        LoginResponse response = new LoginResponse();
        response.setIdEstacionamento(estacionamento.getId());
        response.setNomeEstacionamento(estacionamento.getNomeEstacionamento());
        response.setJwt(funcionarioTokenDto.getToken());
        response.setAdm(funcionarioAutenticado.getUsuarioAdm());

        return response;
    }

    public List<FuncionarioDto> getAllFuncs(Integer id) throws MyException {
        if (funcionarioRepository.findAllHabilitado().isEmpty()){
            throw new MyException(404, "Não existe nada no Banco de Dados", "G-002");
        }
        return listFuncToListFuncDto(funcionarioRepository.findAllByIdEstacionamento(id));
    }

    // Pega uma lista de funcionarioEntity e transforma em lista de funcionarioDto
    private List<FuncionarioDto> listFuncToListFuncDto(List<Funcionario> funcs){
        List<FuncionarioDto> funcsDto = new ArrayList<>();
        for(Funcionario f: funcs){
            funcsDto.add(funcionarioMapper.toFuncionarioDto(f));
        }
        return funcsDto;
    }

    public FuncionarioDto[] getAllFuncsOrderByName(Integer id) throws MyException {
        FuncionarioDto[] funcionarios = getAllFuncs(id).toArray(new FuncionarioDto[0]);

         // Imprime antes de ordenar
         System.out.println("Antes de ordenar: \n");
        for (FuncionarioDto func : funcionarios){
            System.out.printf("\nNome: %s - CPF: %s - cargo: %s - Email: %s", func.getNome(), func.getCpf(), func.getAdm(), func.getEmail());
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
            System.out.printf("\nNome: %s - CPF: %s - cargo: %s - Email: %s", func.getNome(),func.getCpf(), func.getAdm(), func.getEmail());
        }
        return funcionarios;
    }

    public ResponseEntity<FuncionarioDto> binarySearch(String name, Integer id) throws MyException {
        FuncionarioDto[] funcArray = getAllFuncsOrderByName(id);
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

    public Boolean deleteFunc(Integer id) throws MyException {
        Funcionario func = funcionarioRepository.findById(id).orElseThrow(
            () -> new MyException(404, "Id não existe", "F-010")
        );
        func.setLoginHabilitado(false);
        funcionarioRepository.save(func);
        return true;
    }


    public ResponseEntity<List<FuncionarioDto>> getFuncionariosPorNome(Integer idEstacionamento, String nome) throws MyException {
        List<FuncionarioDto> funcionarioDtos = listFuncToListFuncDto(
                funcionarioRepository.pegarFuncPorLikeNome(idEstacionamento, nome)
        );
        return ResponseEntity.status(200).body(funcionarioDtos);
    }
}
