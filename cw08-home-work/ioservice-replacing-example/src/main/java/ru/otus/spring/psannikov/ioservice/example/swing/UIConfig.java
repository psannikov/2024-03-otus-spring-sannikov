package ru.otus.spring.psannikov.ioservice.example.swing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.psannikov.ioservice.example.poll.PollService;

import java.awt.*;

@ConditionalOnProperty(name = "use.console", havingValue = "false")
@Configuration
public class UIConfig {

    @Bean
    public CommandLineRunner starter(PollService pollService, MessageSystem ms) {
        return args -> {
            EventQueue.invokeLater(() -> {
                try {
                    PollMainForm mainForm = new PollMainForm(ms);
                    mainForm.init();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            pollService.poll();
        };
    }

}
