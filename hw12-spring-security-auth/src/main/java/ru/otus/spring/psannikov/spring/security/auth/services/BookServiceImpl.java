package ru.otus.spring.psannikov.spring.security.auth.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.spring.security.auth.exceptions.EntityNotFoundException;
import ru.otus.spring.psannikov.spring.security.auth.models.Book;
import ru.otus.spring.psannikov.spring.security.auth.models.Comment;
import ru.otus.spring.psannikov.spring.security.auth.repositories.AuthorRepository;
import ru.otus.spring.psannikov.spring.security.auth.repositories.BookRepository;
import ru.otus.spring.psannikov.spring.security.auth.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
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
