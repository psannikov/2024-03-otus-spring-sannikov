package ru.otus.spring.psannikov.data.nosql.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.psannikov.data.nosql.exceptions.EntityNotFoundException;
import ru.otus.spring.psannikov.data.nosql.models.Comment;
import ru.otus.spring.psannikov.data.nosql.repositories.BookRepository;
import ru.otus.spring.psannikov.data.nosql.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    @Transactional
    @Override
    public Comment insert(String bookId, String fullComment) {
        return save(null, bookId, fullComment);
    }

    @Transactional
    @Override
    public Comment update(String id, String bookId, String fullComment) {
        return save(id, bookId, fullComment);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAllByBookId(String id) {
        return bookRepository.findById(id).get().getComments();
    }

    private Comment save(String id, String bookId, String fullComment) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %s not found".formatted(bookId)));

        var comment = commentRepository.save(new Comment(id, fullComment));
        if (id == null) {
            book.getComments().add(comment);
        }
        bookRepository.save(book);
        return comment;
    }
}
