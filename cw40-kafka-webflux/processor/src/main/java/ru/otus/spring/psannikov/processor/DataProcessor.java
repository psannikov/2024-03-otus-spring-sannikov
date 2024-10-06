package ru.otus.spring.psannikov.processor;

public interface DataProcessor<T> {

    T process(T data);
}
