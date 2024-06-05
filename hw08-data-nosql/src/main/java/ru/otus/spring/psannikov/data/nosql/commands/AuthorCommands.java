package ru.otus.spring.psannikov.data.nosql.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.psannikov.data.nosql.converters.AuthorConverter;
import ru.otus.spring.psannikov.data.nosql.services.AuthorService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class AuthorCommands {

    private final AuthorService authorService;

    private final AuthorConverter authorConverter;

    @ShellMethod(value = "Find all authors", key = "aa")
    public String findAllAuthors() {
        return authorService.findAll().stream()
                .map(authorConverter::authorToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Delete author by id", key = "adel")
    public void deleteAuthor(String id) {
        authorService.deleteById(id);
    }
}
