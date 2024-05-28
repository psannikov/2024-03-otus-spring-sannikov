package ru.otus.spring.psannikov.jpql.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.jpql.exceptions.EntityNotFoundException;
import ru.otus.spring.psannikov.jpql.models.Comment;
import ru.otus.spring.psannikov.jpql.repositories.BookRepository;
import ru.otus.spring.psannikov.jpql.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment insert(long bookId, String fullComment) {
        return save(0, bookId, fullComment);
    }

    @Override
    public Comment update(long id, long bookId, String fullComment) {
        return save(id, bookId, fullComment);
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findAllByBookId(long id) {
        return commentRepository.findAllByBookId(id);
    }

    private Comment save(long id, long bookId, String fullComment) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %d not found".formatted(bookId)));
        var comment = new Comment(id, book, fullComment);
        return commentRepository.save(comment);
    }
}
