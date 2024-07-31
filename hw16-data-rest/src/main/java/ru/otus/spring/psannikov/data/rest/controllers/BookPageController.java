package ru.otus.spring.psannikov.data.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring.psannikov.data.rest.exceptions.EntityNotFoundException;
import ru.otus.spring.psannikov.data.rest.services.AuthorService;
import ru.otus.spring.psannikov.data.rest.services.BookService;
import ru.otus.spring.psannikov.data.rest.services.GenreService;


@Controller
@RequiredArgsConstructor
public class BookPageController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping("/")
    public String listBooksPage() {
        return "books";
    }

    @GetMapping("/edit/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        var book = bookService
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %d not found".formatted(id)));
        var authors = authorService.findAll();
        var genres = genreService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "editBook";
    }

    @GetMapping("/create")
    public String createBookPage(Model model) {
        var authors = authorService.findAll();
        var genres = genreService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "addBook";
    }

    @GetMapping("/delete/{id}")
    public String deleteBookPage(@PathVariable Long id, Model model) {
        var book = bookService
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id %d not found".formatted(id)));
        model.addAttribute("book", book);
        return "deleteBook";
    }

}
