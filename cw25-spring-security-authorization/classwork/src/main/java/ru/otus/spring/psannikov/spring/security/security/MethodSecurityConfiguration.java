package ru.otus.spring.psannikov.spring.security.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(
    securedEnabled = true,
    prePostEnabled = true
)
public class MethodSecurityConfiguration {
}
