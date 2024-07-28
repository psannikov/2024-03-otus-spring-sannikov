package ru.otus.spring.psannikov.spring.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @SuppressWarnings("checkstyle:ParenPad")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/public", "/").permitAll()
                        .requestMatchers("/authenticated", "/success").authenticated()
                        .requestMatchers("/manager").hasAnyRole("MANAGER")
                        .requestMatchers("/user").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().denyAll())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var users = new ArrayList<UserDetails>();
        users.add(User.withDefaultPasswordEncoder().username("admin").password("password").roles("ADMIN","MANAGER").build());
        users.add(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
        users.add(User.withDefaultPasswordEncoder().username("manager").password("password").roles("MANAGER").build());
        return new InMemoryUserDetailsManager(users);
    }
}
