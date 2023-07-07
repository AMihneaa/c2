package com.mihnea.springboot3.c2.Security;


import com.mihnea.springboot3.c2.User.UserAccount;
import com.mihnea.springboot3.c2.User.UserManagementRepository;
import com.mihnea.springboot3.c2.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    CommandLineRunner initUsers(UserManagementRepository repository) {
        return args -> {
            repository.save(new UserAccount("mihnea", "1234", "ROLE_USER"));
            repository.save(new UserAccount("stefan", "1234", "ROLE_USER"));
            repository.save(new UserAccount("user", "password", "ROLE_USER"));
            repository.save(new UserAccount("admin", "password", "ROLE_ADMIN"));
        };
    }

    @Bean
    UserDetailsService userService(UserRepository repo) {
        return userName -> repo.findByUserName(userName).asUser();
    }

    @Bean
    SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers("/login").permitAll();
        http.authorizeHttpRequests().requestMatchers("/", "/search").authenticated();

        http.authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/api/**").authenticated()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/new-video", "/api/**").hasRole("USER")
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .and()
                .httpBasic().and().csrf().disable();
        return http.build();
    }


}