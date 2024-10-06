package ru.otus.spring.psannikov.model;

public interface DataForSending<T> {
    long id();

    T data();
}
