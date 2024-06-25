package ru.otus.spring.psannikov.data.nosql.exceptions;

public class DeleteErrorException extends RuntimeException {
    public DeleteErrorException(String message) {
        super(message);
    }
}
