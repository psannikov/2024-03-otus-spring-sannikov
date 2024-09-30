package ru.otus.spring.psannikov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import ru.otus.spring.psannikov.services.TaskService;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannelSpec<?, ?> memoChannel() {
        return MessageChannels.queue(10);
    }

    @Bean
    public MessageChannelSpec<?, ?> taskChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean
    public IntegrationFlow taskFlow(TaskService taskService) {
        return IntegrationFlow.from(memoChannel())
                .handle(taskService, "createTask")
                .handle(taskService, "takeTask")
                .handle(taskService, "finishTask")
                .channel(taskChannel())
                .get();
    }
}
