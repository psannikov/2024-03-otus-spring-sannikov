package ru.otus.spring.psannikov.applicationeventsdemo.security;

public interface LoginContext {
    void login(String userName);
    boolean isUserLoggedIn();
}
