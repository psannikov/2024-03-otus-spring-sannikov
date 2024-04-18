package ru.otus.spring.psannikov.spring.boot.service;

public interface LocalizedMessagesService {
    String getMessage(String code, Object ...args);
}
