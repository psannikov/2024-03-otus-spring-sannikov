package ru.otus.spring.psannikov.mvc.view.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.psannikov.mvc.view.exceptions.EntityNotFoundException;
import ru.otus.spring.psannikov.mvc.view.models.Comment;
import ru.otus.spring.psannikov.mvc.view.repositories.BookRepository;
import ru.otus.spring.psannikov.mvc.view.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

//    @Transactional(readOnly = true)
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

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findAllByBookId(long id) {
        return bookRepository.findById(id).get().getComments();
    }

    private Comment save(long id, long bookId, String fullComment) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %d not found".formatted(bookId)));
        var comment = new Comment(id, fullComment);
        book.getComments().add(comment);
        return commentRepository.save(comment);
    }
}
