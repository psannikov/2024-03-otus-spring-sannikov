package ru.otus.spring.psannikov.spring.security.auth.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.psannikov.spring.security.auth.config.SecurityConfiguration;
import ru.otus.spring.psannikov.spring.security.auth.models.Author;
import ru.otus.spring.psannikov.spring.security.auth.models.Book;
import ru.otus.spring.psannikov.spring.security.auth.models.Genre;
import ru.otus.spring.psannikov.spring.security.auth.services.AuthorService;
import ru.otus.spring.psannikov.spring.security.auth.services.BookService;
import ru.otus.spring.psannikov.spring.security.auth.services.GenreService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@Import(SecurityConfiguration.class)
class BookControllerWithoutAuthTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private AuthorService authorService;

    private static final List<Genre> genres = new ArrayList<>();

    private static final List<Author> authors = new ArrayList<>();

    private static final List<Book> books = new ArrayList<>();

    private static UserDetails userDetails;

    @BeforeAll
    static void init() {
        genres.add(new Genre(1L, "Genre1"));
        genres.add(new Genre(2L, "Genre2"));
        authors.add(new Author(1L, "Author1"));
        authors.add(new Author(2L, "Author2"));
        books.add(new Book(1L, "Book1", authors.get(0), genres.get(0), new ArrayList<>()));
        books.add(new Book(2L, "Book2", authors.get(1), genres.get(1), new ArrayList<>()));
        books.add(new Book(3L, "Book3", authors.get(1), genres.get(1), new ArrayList<>()));

        userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username("UnknownUser")
                .password("PassUnknowUser")
                .roles("USER")
                .build();
    }

    @DisplayName("Должен проверить что идет запрос логина и пароля при открытии страницы списка книг")
    @Test
    void listBooksPage() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Должен проверить что идет запрос логина и пароля при открытии страницы добавления книги")
    @Test
    void createBookPage() throws Exception {
        mockMvc.perform(get("/create/book"))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Должен проверить что идет запрос логина и пароля при открытии страницы редактирования книги")
    @Test
    void editPage() throws Exception {
        final Book book = books.get(0);

        mockMvc.perform(get(String.format("/edit/book/%d", book.getId())))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("Должен проверить что идет запрос логина и пароля при открытии страницы удаления книги")
    @Test
    void deletePage() throws Exception {
        final Book book = books.get(0);

        mockMvc.perform(get(String.format("/delete/book/%d", book.getId())))
                .andExpect(status().is3xxRedirection());
    }

//    @DisplayName("Должен проверить что запрос изменение книги без авторизации не проходит.")
//    @Test
//    void update() throws Exception {
//        final Book book = books.get(0);
//        final BookUpdateDto bookUpdateDto = getBookUpdateDtoByBookDto(bookDto);
//
//        mockMvc.perform(post(String.format("/edit/book/%d", bookUpdateDto.getId()))
//                .param("id", bookUpdateDto.getId().toString())
//                .param("title", bookUpdateDto.getTitle())
//                .param("authorId", bookUpdateDto.getAuthorId().toString())
//                .param("genreId", bookUpdateDto.getGenreId().toString())
//        )
//                .andExpect(status().is3xxRedirection());
//    }

    @DisplayName("Должен проверить что запрос удаление книги без авторизации не проходит.")
    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(post(String.format("/delete/book/%d", books.get(0).getId())))
                .andExpect(status().is3xxRedirection());
    }

//    @DisplayName("Должен проверить что запрос добавления книги без авторизации не проходит.")
//    @Test
//    void createBook() throws Exception {
//        final BookDto bookDto = books.get(0);
//        final BookCreateDto bookCreateDto = getBookCreateDtoByBookDto(bookDto);
//
//        mockMvc.perform(post("/create/book")
//                .param("title", bookCreateDto.getTitle())
//                .param("authorId", bookCreateDto.getAuthorId().toString())
//                .param("genreId", bookCreateDto.getGenreId().toString())
//        )
//                .andExpect(status().is3xxRedirection());
//    }

//    private BookCreateDto getBookCreateDtoByBookDto(BookDto bookdto) {
//        final BookCreateDto bookCreateDto = new BookCreateDto();
//        bookCreateDto.setTitle(bookdto.getTitle());
//        bookCreateDto.setAuthorId(bookdto.getAuthorDto().getId());
//        bookCreateDto.setGenreId(bookdto.getGenreDto().getId());
//        return bookCreateDto;
//    }
//
//    private BookUpdateDto getBookUpdateDtoByBookDto(BookDto bookDto) {
//        final BookUpdateDto bookUpdateDto = new BookUpdateDto();
//        bookUpdateDto.setId(bookDto.getId());
//        bookUpdateDto.setTitle(bookDto.getTitle());
//        bookUpdateDto.setAuthorId(bookDto.getAuthorDto().getId());
//        bookUpdateDto.setGenreId(bookDto.getGenreDto().getId());
//        return bookUpdateDto;
//    }
}