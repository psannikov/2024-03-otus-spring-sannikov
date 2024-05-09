package ru.otus.spring.psannikov.spring.boot.shell.exceptions;

public class QuestionReadException extends RuntimeException {
    public QuestionReadException(String message, Throwable ex) {
        super(message, ex);
    }
}
