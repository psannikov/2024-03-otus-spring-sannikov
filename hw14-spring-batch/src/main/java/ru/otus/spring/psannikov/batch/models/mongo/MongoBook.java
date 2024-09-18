package ru.otus.spring.psannikov.batch.models.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.psannikov.batch.models.jpa.Author;
import ru.otus.spring.psannikov.batch.models.jpa.Book;

import java.util.List;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
@EqualsAndHashCode(of = {"id"})
public class MongoBook {

    @Id
    private String id;

    private String title;

    private MongoAuthor author;

    private MongoGenre genre;

    private List<MongoComment> comments;

    public MongoBook(String title, MongoAuthor author, MongoGenre genre, List<MongoComment> comments) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }

    public static MongoBook convert(Book book) {
        return new MongoBook(book.getTitle(),
                MongoAuthor.convert(book.getAuthor()),
                MongoGenre.convert(book.getGenre()),
                book.getComments().stream().map(MongoComment::convert).toList());
    }
}
