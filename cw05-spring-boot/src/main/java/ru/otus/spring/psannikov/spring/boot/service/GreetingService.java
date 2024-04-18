package ru.otus.spring.psannikov.spring.boot.service;

import java.util.Map;

public interface GreetingService {
    Map<String, String> sayHello(String name);
}
