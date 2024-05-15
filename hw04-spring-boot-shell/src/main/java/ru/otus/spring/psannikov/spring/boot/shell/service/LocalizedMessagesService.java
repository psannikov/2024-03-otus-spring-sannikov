package ru.otus.spring.psannikov.spring.boot.shell.service;

public interface LocalizedMessagesService {
    String getMessage(String code, Object... args);
}
