package ru.otus.spring.psannikov.lite.task.tracker.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
