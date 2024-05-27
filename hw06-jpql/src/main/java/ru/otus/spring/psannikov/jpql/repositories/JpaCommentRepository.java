package ru.otus.spring.psannikov.jpql.repositories;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import ru.otus.spring.psannikov.jpql.models.Comment;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
public class JpaCommentRepository implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    public JpaCommentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Comment> findAllByBookId(long id) {
        EntityGraph<?> entityGraphBook = em.getEntityGraph("books-books-entity-graph");
        TypedQuery<Comment> query = em.createQuery("select c from Comment c " +
                "left join fetch c.book " +
                "where c.book_id = :id", Comment.class);
        query.setHint(FETCH.getKey(), entityGraphBook);
        return query.getResultList();
    }


}
