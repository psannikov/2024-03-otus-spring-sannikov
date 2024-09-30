package ru.otus.spring.psannikov.batch.models.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import ru.otus.spring.psannikov.batch.models.jpa.Author;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"fullName"})
public class MongoAuthor {

    private String fullName;

    public static MongoAuthor convert(Author author) {
        return new MongoAuthor(author.getFullName());
    }
}
