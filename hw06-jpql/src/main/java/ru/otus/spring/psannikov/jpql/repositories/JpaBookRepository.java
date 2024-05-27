package ru.otus.spring.psannikov.jpql.repositories;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import ru.otus.spring.psannikov.jpql.models.Book;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
public class JpaBookRepository implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    public JpaBookRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraphAuthors = em.getEntityGraph("books-authors-entity-graph");
        EntityGraph<?> entityGraphGenres = em.getEntityGraph("books-genres-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "left join fetch b.author " +
                "left join fetch b.genre", Book.class);
        query.setHint(FETCH.getKey(), entityGraphAuthors);
        query.setHint(FETCH.getKey(), entityGraphGenres);
        return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
