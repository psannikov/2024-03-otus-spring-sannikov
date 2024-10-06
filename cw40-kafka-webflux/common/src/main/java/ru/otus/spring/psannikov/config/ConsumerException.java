package ru.otus.spring.psannikov.config;

public class ConsumerException extends RuntimeException {
    public ConsumerException(String message, Throwable cause) {
        super(message, cause);
    }
}
