package ru.otus.spring.psannikov.data.jpa.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.psannikov.data.jpa.exceptions.EntityNotFoundException;
import ru.otus.spring.psannikov.data.jpa.models.Comment;
import ru.otus.spring.psannikov.data.jpa.repositories.BookRepository;
import ru.otus.spring.psannikov.data.jpa.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Transactional
    @Override
    public Comment insert(long bookId, String fullComment) {
        return save(0, bookId, fullComment);
    }

    @Transactional
    @Override
    public Comment update(long id, long bookId, String fullComment) {
        return save(id, bookId, fullComment);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
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
