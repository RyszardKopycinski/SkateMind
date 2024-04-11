package pl.ackstudio.skatecloud.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import pl.ackstudio.skatecloud.domain.User;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
                                                                      Exception {
        http.authorizeHttpRequests(request -> request.requestMatchers("/myposts", "/user/**")
                                                     .hasAnyRole("USER", "ADMIN")
                                                     .requestMatchers("/**")
                                                     .permitAll()
                                                     .anyRequest()
                                                     .authenticated())
            .csrf(csrf -> csrf.ignoringRequestMatchers("/resources/**"))
            .formLogin(login -> login.loginPage("/login")
                                     .permitAll())
            .logout(logout -> logout.logoutUrl("/login/out")
                                    .logoutSuccessUrl("/login"))
            .headers(header -> header.frameOptions(option -> option.sameOrigin()))
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                                                 .maximumSessions(1)
                                                 .sessionRegistry(sessionRegistry()).expiredUrl("/"));
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
