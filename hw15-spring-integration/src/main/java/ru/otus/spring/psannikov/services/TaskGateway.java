package ru.otus.spring.psannikov.services;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.psannikov.domain.Memo;
import ru.otus.spring.psannikov.domain.Task;

@MessagingGateway
public interface TaskGateway {

    @Gateway(requestChannel = "memoChannel", replyChannel = "taskChannel")
    Task process(Memo memo);
}
