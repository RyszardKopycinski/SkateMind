package pl.ackstudio.skatecloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.ackstudio.skatecloud.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserRepository userRepo;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
                                                                      Exception {
        http.authenticationProvider(authenticationProvider())
            .authorizeHttpRequests(request -> request.requestMatchers("/", "/about", "/login", "/login/**", "/register")
                                                     .permitAll()
                                                     .requestMatchers("/user/info", "/user/edit")
                                                     .authenticated()
                                                     .requestMatchers("/user/info/**")
                                                     .hasAnyRole("ADMIN")
                                                     .anyRequest()
                                                     .denyAll())
            .csrf(csrf -> csrf.ignoringRequestMatchers("/resources/**"))
            .formLogin(login -> login.loginPage("/login")
                                     .permitAll())
            .logout(logout -> logout.logoutUrl("/login/out")
                                    .permitAll()
                                    .logoutSuccessUrl("/"))
            .headers(header -> header.frameOptions(option -> option.sameOrigin()))
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                                                 .maximumSessions(1)
                                                 .sessionRegistry(sessionRegistry())
                                                 .expiredUrl("/"));
        return http.build();
    }

    @Bean
    WebSecurityCustomizer configureWebSecurity() {
        return web -> web.ignoring()
                         .requestMatchers("/images/**", "/js/**", "/css/**");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(encoder());
        authProvider.setUserDetailsService(new UserRepositoryService(userRepo));
        return authProvider;
    }
}
