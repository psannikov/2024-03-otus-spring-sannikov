package ru.otus.spring.psannikov.batch.models.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import ru.otus.spring.psannikov.batch.models.postgres.Author;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"fullName"})
public class MongoAuthor {

    @Id
    private String id;

    private String fullName;

    public MongoAuthor(String fullName) {
        this.fullName = fullName;
    }

    public static MongoAuthor convert(Author author) {
        return new MongoAuthor(author.getFullName());
    }
}
