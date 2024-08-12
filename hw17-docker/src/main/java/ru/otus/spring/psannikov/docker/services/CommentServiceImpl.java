package ru.otus.spring.psannikov.docker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.psannikov.docker.exceptions.EntityNotFoundException;
import ru.otus.spring.psannikov.docker.models.Comment;
import ru.otus.spring.psannikov.docker.repositories.BookRepository;
import ru.otus.spring.psannikov.docker.repositories.CommentRepository;

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
