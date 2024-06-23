package ru.otus.spring.psannikov.mvc.ajax.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.spring.psannikov.mvc.ajax.dto.BookDto;
import ru.otus.spring.psannikov.mvc.ajax.models.Author;
import ru.otus.spring.psannikov.mvc.ajax.models.Book;
import ru.otus.spring.psannikov.mvc.ajax.models.Comment;
import ru.otus.spring.psannikov.mvc.ajax.models.Genre;
import ru.otus.spring.psannikov.mvc.ajax.services.AuthorService;
import ru.otus.spring.psannikov.mvc.ajax.services.BookService;
import ru.otus.spring.psannikov.mvc.ajax.services.GenreService;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер для Book должен")
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookController bookController;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    private final static long ID = 1L;
    private final static String TITLE = "Title_1";
    private final static String AUTHOR = "Author_1";
    private final static String GENRE = "Genre_1";
    private final static String COMMENT = "Comment_1";
    private Book mockBook;
    private Author mockAuthor;
    private Genre mockGenre;
    private List<Book> mockBooks;
    private List<Author> mockAuthors;
    private List<Genre> mockGenres;
    private Comment mockComment;
    private List<Comment> mockComments;

    @BeforeEach
    void init() {
        mockAuthor = new Author(ID, AUTHOR);
        mockGenre = new Genre(ID, GENRE);
        mockComment = new Comment(ID, COMMENT);
        mockComments = List.of(mockComment);
        mockBook = new Book(ID, TITLE,
                mockAuthor,
                mockGenre,
                mockComments);
        mockBooks = List.of(mockBook);
        mockAuthors = List.of(mockAuthor);
        mockGenres = List.of(mockGenre);
    }

    @DisplayName("вернуть список книг")
    @Test
    void getAllBooksTest() throws Exception {
        when(bookService.findAll()).thenReturn(mockBooks);
        List<BookDto> expectedResult = mockBooks.stream()
                .map(BookDto::toDto).collect(Collectors.toList());
        mvc.perform(get("/api/v1/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
        verify(bookService, times(1)).findAll();
    }

    @DisplayName("редактировать книгу")
    @Test
    void editBookTest() throws Exception {
        //FIX
        when(bookService.findById(ID).get().getComments()).thenReturn(mockComments);
        when(bookService.update(ID, TITLE, ID, ID, mockComments)).thenReturn(mockBook);
        BookDto expectedResult = BookDto.toDto(mockBook);
        mvc.perform(post("/api/v1/book/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsString(expectedResult)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
        verify(bookService, times(1)).findById(ID);
        verify(bookService, times(1)).update(ID, TITLE, ID, ID, mockComments);
    }

    @DisplayName("создавать новую книгу")
    @Test
    void createBookTest() throws Exception {
        when(bookService.insert(TITLE, ID, ID)).thenReturn(mockBook);
        BookDto expectedResult = BookDto.toDto(mockBook);
        mvc.perform(post("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsString(expectedResult)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
        verify(bookService, times(1)).insert(TITLE, ID, ID);
    }

    @DisplayName("удалять книгу")
    @Test
    void deleteBookTest() throws Exception {
        mvc.perform(delete("/api/v1/book/{id}", ID))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(bookService, times(1)).deleteById(ID);
    }
}
