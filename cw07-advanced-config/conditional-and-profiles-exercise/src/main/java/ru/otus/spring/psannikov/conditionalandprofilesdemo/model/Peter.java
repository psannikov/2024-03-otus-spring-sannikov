package ru.otus.spring.psannikov.conditionalandprofilesdemo.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.conditionalandprofilesdemo.model.base.Friend;

@Profile("Peter")
@Component
public class Peter extends Friend {

    @Override
    public String getName() {
        return "Петр";
    }
}
