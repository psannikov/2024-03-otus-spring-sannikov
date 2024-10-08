package ru.otus.spring.psannikov.spring.security.acl.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.spring.psannikov.spring.security.acl.services.AuthorService;
import ru.otus.spring.psannikov.spring.security.acl.services.BookService;
import ru.otus.spring.psannikov.spring.security.acl.services.GenreService;


@Controller
@RequiredArgsConstructor
public class BookPageController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping("/books")
    public String listBooksPage() {
        return "books";
    }

    @GetMapping("/edit/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        var book = bookService.findById(id);
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
        var book = bookService.findById(id);
        model.addAttribute("book", book);
        return "deleteBook";
    }

}
