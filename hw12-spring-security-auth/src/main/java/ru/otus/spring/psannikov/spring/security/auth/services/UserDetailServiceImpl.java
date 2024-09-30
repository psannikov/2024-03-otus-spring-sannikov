package ru.otus.spring.psannikov.spring.security.auth.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.spring.security.auth.models.User;
import ru.otus.spring.psannikov.spring.security.auth.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by UserName: %s.".formatted(username)));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
