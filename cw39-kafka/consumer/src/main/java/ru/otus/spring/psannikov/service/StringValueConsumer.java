package ru.otus.spring.psannikov.service;

import java.util.List;
import ru.otus.spring.psannikov.model.StringValue;

public interface StringValueConsumer {

    void accept(List<StringValue> value);
}
