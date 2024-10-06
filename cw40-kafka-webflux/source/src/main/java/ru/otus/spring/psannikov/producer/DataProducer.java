package ru.otus.spring.psannikov.producer;

public interface DataProducer<T> {

    T produce(long seed);
}
