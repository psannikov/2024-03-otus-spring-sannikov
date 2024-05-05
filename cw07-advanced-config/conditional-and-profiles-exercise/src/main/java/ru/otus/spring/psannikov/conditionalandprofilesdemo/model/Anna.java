package ru.otus.spring.psannikov.conditionalandprofilesdemo.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.conditionalandprofilesdemo.model.base.Friend;

@ConditionalOnBean(Alexey.class)
@Component
public class Anna extends Friend {
    @Override
    public String getName() {
        return "Аня";
    }
}
