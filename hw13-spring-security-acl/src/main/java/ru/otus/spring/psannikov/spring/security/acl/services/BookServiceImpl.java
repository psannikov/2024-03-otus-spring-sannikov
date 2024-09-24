package ru.otus.spring.psannikov.spring.security.acl.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.spring.security.acl.exceptions.EntityNotFoundException;
import ru.otus.spring.psannikov.spring.security.acl.models.Book;
import ru.otus.spring.psannikov.spring.security.acl.models.Comment;
import ru.otus.spring.psannikov.spring.security.acl.repositories.AuthorRepository;
import ru.otus.spring.psannikov.spring.security.acl.repositories.BookRepository;
import ru.otus.spring.psannikov.spring.security.acl.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    @Override
    @PostAuthorize("hasPermission(returnObject, 'READ')")
    public Book findById(long id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %d not found".formatted(id)));
    }

    @Override
    @PostFilter("hasPermission(filterObject, 'READ')")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @PreAuthorize("hasPermission(#book, 'WRITE')")
    public Book insert(String title, long authorId, long genreId) {
        return save(0, title, authorId, genreId, new ArrayList<>());
    }

    @Transactional
    @Override
    @PreAuthorize("hasPermission(#book, 'WRITE')")
    public Book update(long id, String title, long authorId, long genreId, List<Comment> comments) {
        return save(id, title, authorId, genreId, comments);
    }

//    @Transactional
    @Override
    @PostAuthorize("hasPermission(#book, 'DELETE')")
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
