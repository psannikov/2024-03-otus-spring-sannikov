package ru.otus.spring.psannikov.hystrix.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.hystrix.exceptions.EntityNotFoundException;
import ru.otus.spring.psannikov.hystrix.models.Book;
import ru.otus.spring.psannikov.hystrix.models.Comment;
import ru.otus.spring.psannikov.hystrix.repositories.AuthorRepository;
import ru.otus.spring.psannikov.hystrix.repositories.BookRepository;
import ru.otus.spring.psannikov.hystrix.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    @Retry(name = "findByIdRetry")
    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @CircuitBreaker(name = "findAllCircuitBreaker", fallbackMethod = "findAllRecoverMethod")
    @Override
    public List<Book> findAll() {
        ToolsService.sleepRandomly();
        return bookRepository.findAll();
    }

    //Имитирую запрос в другой источник обновляя исходные данные того же источника
    public List<Book> findAllRecoverMethod(Exception ex) {
        var books = bookRepository.findAll();
        books.forEach(book -> book.setTitle(book.getTitle().toUpperCase()));
        return books;
    }

    @Override
    public Book insert(String title, long authorId, long genreId) {
        return save(0, title, authorId, genreId, new ArrayList<>());
    }

    @Transactional
    @Override
    public Book update(long id, String title, long authorId, long genreId, List<Comment> comments) {
        return save(id, title, authorId, genreId, comments);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    private Book save(long id, String title, long authorId, long genreId, List<Comment> comments) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre with id %d not found".formatted(genreId)));
        var book = new Book(id, title, author, genre, comments);
        return bookRepository.save(book);
    }
}
