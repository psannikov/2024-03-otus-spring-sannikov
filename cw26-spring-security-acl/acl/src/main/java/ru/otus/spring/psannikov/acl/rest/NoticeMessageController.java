package ru.otus.spring.psannikov.acl.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.psannikov.acl.model.NoticeMessage;
import ru.otus.spring.psannikov.acl.repository.NoticeMessageRepository;

import java.util.List;

@RestController
public class NoticeMessageController {

    private final NoticeMessageRepository repository;

    public NoticeMessageController(NoticeMessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/message")
    public List<NoticeMessage> getAll() {
        return repository.findAll();
    }

    @GetMapping("/message/{id}")
    public NoticeMessage getById(@PathVariable("id") Integer id) {
        var result = repository.getById( id );
        return result;
    }

    @PutMapping("/message")
    public NoticeMessage getById(NoticeMessage message) {
        return repository.save(message);
    }
}
