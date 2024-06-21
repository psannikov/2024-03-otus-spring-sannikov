package ru.otus.spring.psannikov.mvc.view.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.psannikov.mvc.view.dto.BookDto;
import ru.otus.spring.psannikov.mvc.view.dto.BookInsertDto;
import ru.otus.spring.psannikov.mvc.view.services.AuthorService;
import ru.otus.spring.psannikov.mvc.view.services.BookService;
import ru.otus.spring.psannikov.mvc.view.services.GenreService;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping("/")
    public String books(Model model) {
        var books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam("id") long id, Model model) {
        var book = bookService.findById(id).orElseThrow(EntityNotFoundException::new);
        var authors = authorService.findAll();
        var genres = genreService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "editBook";
    }

    @PostMapping("/edit")
    public String editBook(BookDto bookDto) {
        var comments = bookService.findById(bookDto.getId()).get().getComments();
        bookService.update(bookDto.getId(), bookDto.getTitle(), bookDto.getAuthorId(), bookDto.getGenreId(), comments);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String createBookPage(Model model) {
        var authors = authorService.findAll();
        var genres = genreService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "addBook";
    }

    @PostMapping("/create")
    public String createBook(BookInsertDto bookDto) {
        bookService.insert(bookDto.getTitle(), bookDto.getAuthorId(), bookDto.getGenreId());
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteBookPage(@RequestParam("id") long id, Model model) {
        var book = bookService.findById(id).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("book", book);
        return "deleteBook";
    }

    @PostMapping("/delete")
    public String deleteBook(BookDto bookDto) {
        bookService.deleteById(bookDto.getId());
        return "redirect:/";
    }

}
