package ru.otus.spring.psannikov.data.nosql.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.data.nosql.exceptions.DeleteErrorException;
import ru.otus.spring.psannikov.data.nosql.models.Author;
import ru.otus.spring.psannikov.data.nosql.repositories.BookRepository;

@Component
@RequiredArgsConstructor
public class MongoAuthorCascadeDeleteEventListener extends AbstractMongoEventListener<Author> {
    private final BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) throws DeleteErrorException {
        super.onBeforeDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        var books = bookRepository.findByAuthorId(id);
        if (!books.isEmpty()) {
            throw new DeleteErrorException("Нельзя удалять автора имеющего книги. " +
                    "Сначала выполните удаление всех книг автора");
        }
    }
}