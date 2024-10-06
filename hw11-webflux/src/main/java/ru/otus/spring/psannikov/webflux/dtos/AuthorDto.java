package ru.otus.spring.psannikov.webflux.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ru.otus.spring.psannikov.webflux.models.Author;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private String id;

    private String fullName;

    public static AuthorDto entityToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        BeanUtils.copyProperties(author, authorDto);
        return authorDto;
    }

    public static Author dtoToEntity(AuthorDto authorDto) {
        Author author = new Author();
        BeanUtils.copyProperties(authorDto, author);
        return author;
    }
}
