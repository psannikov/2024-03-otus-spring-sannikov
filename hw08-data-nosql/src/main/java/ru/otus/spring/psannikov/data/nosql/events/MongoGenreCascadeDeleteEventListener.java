package ru.otus.spring.psannikov.data.nosql.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.data.nosql.exceptions.DeleteErrorException;
import ru.otus.spring.psannikov.data.nosql.models.Genre;
import ru.otus.spring.psannikov.data.nosql.repositories.BookRepository;

@Component
@RequiredArgsConstructor
public class MongoGenreCascadeDeleteEventListener extends AbstractMongoEventListener<Genre> {
    private final BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) throws DeleteErrorException {
        super.onBeforeDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        var books = bookRepository.findByGenreId(id);
        if (!books.isEmpty()) {
            throw new DeleteErrorException("Нельзя удалять жанр если по нему написаны книги. " +
                    "Сначала выполните удаление всех книг данного жанра");
        }
    }
}