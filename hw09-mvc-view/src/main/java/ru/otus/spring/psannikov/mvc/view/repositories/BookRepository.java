package ru.otus.spring.psannikov.mvc.view.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.psannikov.mvc.view.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genre", "comments"})
    Optional<Book> findById(long id);

    @EntityGraph(attributePaths = {"author", "genre", "comments"})
    List<Book> findAll();

    Book save(Book book);

    void deleteById(long id);
}
