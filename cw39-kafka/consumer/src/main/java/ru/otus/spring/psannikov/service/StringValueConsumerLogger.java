package ru.otus.spring.psannikov.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.spring.psannikov.model.StringValue;

public class StringValueConsumerLogger implements StringValueConsumer {
    private static final Logger log = LoggerFactory.getLogger(StringValueConsumerLogger.class);

    @Override
    public void accept(List<StringValue> values) {
        for (var value : values) {
            log.info("log:{}", value);
        }
    }
}
