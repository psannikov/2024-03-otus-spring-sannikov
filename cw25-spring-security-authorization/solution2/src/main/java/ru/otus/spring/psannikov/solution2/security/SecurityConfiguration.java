package ru.otus.spring.psannikov.solution2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
        http
                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS)
//                .and()
                .authorizeHttpRequests( ( authorize ) -> authorize
                        .antMatchers( "/public", "/" ).permitAll()
                        .antMatchers( "/authenticated", "/success" ).authenticated()
                        .antMatchers( "/manager" ).hasAnyRole( "MANAGER" )
                        .antMatchers( "/user" ).hasAnyRole( "ADMIN", "USER" )
                        .anyRequest().denyAll()
                )
                .formLogin()

        ;
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var users = new ArrayList<UserDetails>();
        users.add( User
                .withDefaultPasswordEncoder().username( "admin" ).password( "password" ).roles( "ADMIN" )
                .build() );
        users.add( User
                .withDefaultPasswordEncoder().username( "user" ).password( "password" ).roles( "USER" )
                .build() );
        users.add( User
                .withDefaultPasswordEncoder().username( "manager" ).password( "password" ).roles( "MANAGER", "USER" )
                .build() );
        return new InMemoryUserDetailsManager( users );

    }
}
