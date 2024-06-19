package ru.otus.spring.psannikov.data.nosql.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.psannikov.data.nosql.models.Author;
import ru.otus.spring.psannikov.data.nosql.models.Book;
import ru.otus.spring.psannikov.data.nosql.models.Comment;
import ru.otus.spring.psannikov.data.nosql.models.Genre;
import ru.otus.spring.psannikov.data.nosql.repositories.AuthorRepository;
import ru.otus.spring.psannikov.data.nosql.repositories.BookRepository;
import ru.otus.spring.psannikov.data.nosql.repositories.CommentRepository;
import ru.otus.spring.psannikov.data.nosql.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog(order = "001")
public class DatabaseInit {

    private Author author1;

    private Author author2;

    private Genre genre1;

    private Genre genre2;

    private Comment comment1;

    private Comment comment2;

    private Comment comment3;

    @ChangeSet(order = "001", id = "dropDb", author = "psannikov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "psannikov")
    public void insertAuthors (AuthorRepository repository) {
        author1 = repository.save(new Author("Author_1"));
        author2 = repository.save(new Author("Author_2"));
        repository.save(new Author("Author_3"));
    }

    @ChangeSet(order = "003", id = "insertGenre", author = "psannikov")
    public void insertGenres (GenreRepository repository) {
        genre1 = repository.save(new Genre("Genre_1"));
        genre2 = repository.save(new Genre("Genre_2"));
        repository.save(new Genre("Genre_3"));
    }

    @ChangeSet(order = "004", id = "insertComment", author = "psannikov")
    public void insertComments (CommentRepository repository) {
        comment1 = repository.save(new Comment("Book1_Comment1"));
        comment2 = repository.save(new Comment("Book1_Comment2"));
        comment3 = repository.save(new Comment("Book1_Comment3"));
    }

    @ChangeSet(order = "005", id = "insertBook", author = "psannikov")
    public void insertBooks (BookRepository repository) {
        repository.save(new Book("BookTitle_1", author1, genre1, List.of(comment1, comment2)));
        repository.save(new Book("BookTitle_2", author2, genre2,List.of(comment3)));
        repository.save(new Book("BookTitle_3", author1, genre2, new ArrayList<>()));
    }


}
