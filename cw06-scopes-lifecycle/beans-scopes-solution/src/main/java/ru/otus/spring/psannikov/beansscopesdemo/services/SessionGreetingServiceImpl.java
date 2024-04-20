package ru.otus.spring.psannikov.beansscopesdemo.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
@Service("sessionGreetingService")
public class SessionGreetingServiceImpl extends AbstractGreetingServiceImpl {
}
