package ru.otus.spring.psannikov.spring.security.acl.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.psannikov.spring.security.acl.dto.BookDto;
import ru.otus.spring.psannikov.spring.security.acl.services.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/v1/book")
    public List<BookDto> getAllBooks() {
        return bookService.findAll().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/v1/book/{id}")
    public BookDto editBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        var comments = bookService.findById(id).get().getComments();
        var book = bookService.update(id, bookDto.getTitle(), bookDto.getAuthorId(), bookDto.getGenreId(), comments);
        return BookDto.toDto(book);
    }

    @PostMapping("/api/v1/book")
    public BookDto createBook(@RequestBody BookDto bookDto) {
        var book = bookService.insert(bookDto.getTitle(), bookDto.getAuthorId(), bookDto.getGenreId());
        return BookDto.toDto(book);
    }

    @DeleteMapping("/api/v1/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }

}
