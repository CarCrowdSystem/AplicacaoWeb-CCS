package carcrowdsystem.ccs.configuration.security;

import carcrowdsystem.ccs.autenticacao.FuncionarioAutenticacao;
import carcrowdsystem.ccs.configuration.security.jwt.GerenciadorTokenJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguracao {
    private static final String ORIGENS_PERMITIDAS = "*";
    @Autowired
    private FuncionarioAutenticacao funcionarioAutenticacao;
    @Autowired
    private AutenticacaoEntryPoint autenticacaoJwtEntryPoint;

    private static final AntPathRequestMatcher[] URLS_PERMITIDAS = {
            new AntPathRequestMatcher("/ccs-dev/**"),
            new AntPathRequestMatcher("/webjars/**"),
            new AntPathRequestMatcher("/actuator/*"),
            new AntPathRequestMatcher("/arquivos/**"),
            new AntPathRequestMatcher("/swagger-ui**"),
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/h2-console/**"),
            new AntPathRequestMatcher("/api/public/**"),
            new AntPathRequestMatcher("/ccs-dev/vagas"),
            new AntPathRequestMatcher("/ccs-dev/veiculo"),
            new AntPathRequestMatcher("/ccs-dev/veiculo**"),
            new AntPathRequestMatcher("/ccs-dev/veiculo/**"),
            new AntPathRequestMatcher("/ccs-dev/valores"),
            new AntPathRequestMatcher("/ccs-dev/valores**"),
            new AntPathRequestMatcher("/ccs-dev/valores/**"),
            new AntPathRequestMatcher("/ccs-dev/clientes"),
            new AntPathRequestMatcher("/ccs-dev/clientes**"),
            new AntPathRequestMatcher("/ccs-dev/clientes/**"),
            new AntPathRequestMatcher("/v3/api-docs/**"),
            new AntPathRequestMatcher("/ccs-dev/vagas**"),
            new AntPathRequestMatcher("/ccs-dev/vagas/**"),
            new AntPathRequestMatcher("/configuration/ui"),
            new AntPathRequestMatcher("/swagger-resources"),
            new AntPathRequestMatcher("/ccs-dev/funcionarios"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/ccs-dev/historicos/**"),
            new AntPathRequestMatcher("/ccs-dev/funcionarios**"),
            new AntPathRequestMatcher("/configuration/security"),
            new AntPathRequestMatcher("/ccs-dev/estacionamentos"),
            new AntPathRequestMatcher("/api/public/authenticate"),
            new AntPathRequestMatcher("/ccs-dev/funcionarios/**"),
            new AntPathRequestMatcher("/ccs-dev/estacionamentos/**"),
            new AntPathRequestMatcher("/ccs-dev/checkin/mensagem/**")
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers()
                .frameOptions().disable()
                .and()
                .cors()
                .configurationSource(request -> buildCorsConfiguration())
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(URLS_PERMITIDAS)
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                )
                .exceptionHandling()
                .authenticationEntryPoint(autenticacaoJwtEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(
            new AutenticacaoProvider(funcionarioAutenticacao, passwordEncoder())
        );
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AutenticacaoEntryPoint jwtAuthenticationEntryPointBean() {
        return new AutenticacaoEntryPoint();
    }

    @Bean
    public AutenticacaoFilter jwtAuthenticationFilterBean() {
        return new AutenticacaoFilter(funcionarioAutenticacao, jwtAuthenticationUtilBean());
    }

    @Bean
    public GerenciadorTokenJwt jwtAuthenticationUtilBean() {
        return new GerenciadorTokenJwt();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private CorsConfiguration buildCorsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Collections.singletonList(ORIGENS_PERMITIDAS));
        configuration.setAllowedMethods(
                Arrays.asList(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name())
        );

        configuration.setAllowedHeaders(Arrays.asList(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION));
        return configuration;
    }
}
