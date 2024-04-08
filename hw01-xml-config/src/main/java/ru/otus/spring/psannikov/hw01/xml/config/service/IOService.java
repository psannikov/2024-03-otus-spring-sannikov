package ru.otus.spring.psannikov.hw01.xml.config.service;

public interface IOService {
    void printLine(String s);

    void printFormattedLine(String s, Object... args);
}
