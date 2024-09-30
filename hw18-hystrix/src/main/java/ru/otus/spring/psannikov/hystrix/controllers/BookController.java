package ru.otus.spring.psannikov.hystrix.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.psannikov.hystrix.dto.BookDto;
import ru.otus.spring.psannikov.hystrix.services.BookService;

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
