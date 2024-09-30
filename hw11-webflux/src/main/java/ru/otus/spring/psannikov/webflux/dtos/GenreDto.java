package ru.otus.spring.psannikov.webflux.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import ru.otus.spring.psannikov.webflux.models.Genre;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {

    private String id;

    private String name;

    public static GenreDto entityToDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        BeanUtils.copyProperties(genre, genreDto);
        return genreDto;
    }

    public static Genre dtoToEntity(GenreDto genreDto) {
        Genre genre = new Genre();
        BeanUtils.copyProperties(genreDto, genre);
        return genre;
    }
}
