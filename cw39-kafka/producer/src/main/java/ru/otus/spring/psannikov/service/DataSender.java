package ru.otus.spring.psannikov.service;

import ru.otus.spring.psannikov.model.StringValue;

public interface DataSender {
    void send(StringValue value);
}
