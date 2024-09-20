package ru.otus.spring.psannikov.spring.security.acl.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.spring.security.acl.models.Role;
import ru.otus.spring.psannikov.spring.security.acl.models.User;
import ru.otus.spring.psannikov.spring.security.acl.repositories.UserRepository;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by UserName: %s."
                        .formatted(username)));
        var roles = user
                .getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet())
                .toArray(new String[0]);

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
