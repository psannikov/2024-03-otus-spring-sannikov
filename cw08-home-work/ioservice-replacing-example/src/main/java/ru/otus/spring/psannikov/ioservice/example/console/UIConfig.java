package ru.otus.spring.psannikov.ioservice.example.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.psannikov.ioservice.example.poll.PollService;

@ConditionalOnProperty(name = "use.console", havingValue = "true")
@Configuration
public class UIConfig {

    @Bean
    public CommandLineRunner starter (PollService pollService) {
        return args -> pollService.poll();
    }

}
