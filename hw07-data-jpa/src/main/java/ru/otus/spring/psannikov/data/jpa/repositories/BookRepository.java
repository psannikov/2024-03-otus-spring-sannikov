package ru.otus.spring.psannikov.data.jpa.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import ru.otus.spring.psannikov.data.jpa.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genre"})
    Optional<Book> findById(long id);

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAll();

    @Modifying
    Book save (Book book);

    @Modifying
    void deleteById(long id);
}
