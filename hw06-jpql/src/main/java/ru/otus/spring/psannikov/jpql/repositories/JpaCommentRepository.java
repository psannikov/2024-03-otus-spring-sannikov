package ru.otus.spring.psannikov.jpql.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.jpql.models.Comment;

import java.util.List;
import java.util.Optional;

@Component
public class JpaCommentRepository implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    public JpaCommentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Comment> findById(long id) {
        TypedQuery<Comment> query = em.createQuery("select distinct c from Comment c " +
                "LEFT JOIN FETCH c.book b " +
                "LEFT JOIN FETCH b.author " +
                "LEFT JOIN FETCH b.genre " +
                "where c.id = :id", Comment.class);
        query.setParameter("id", id);
        return Optional.of(query.getSingleResult());
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
        var comment = findById(id).get();
        em.remove(comment);
    }

    @Override
    public List<Comment> findAllByBookId(long id) {
        TypedQuery<Comment> query = em.createQuery("select distinct c from Comment c " +
                "LEFT JOIN FETCH c.book b " +
                "LEFT JOIN FETCH b.author " +
                "LEFT JOIN FETCH b.genre " +
                "where b.id = :id", Comment.class);
        query.setParameter("id", id);
        return query.getResultList();
    }


}
