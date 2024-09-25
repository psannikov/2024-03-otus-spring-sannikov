package ru.otus.spring.psannikov.spring.security.acl.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.spring.psannikov.spring.security.acl.dto.BookDto;
import ru.otus.spring.psannikov.spring.security.acl.models.Author;
import ru.otus.spring.psannikov.spring.security.acl.models.Book;
import ru.otus.spring.psannikov.spring.security.acl.models.Comment;
import ru.otus.spring.psannikov.spring.security.acl.models.Genre;
import ru.otus.spring.psannikov.spring.security.acl.services.AuthorService;
import ru.otus.spring.psannikov.spring.security.acl.services.BookService;
import ru.otus.spring.psannikov.spring.security.acl.services.GenreService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер для Book с авторизацией должен")
@WebMvcTest(controllers = BookController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private AuthorService authorService;

    private final static long ID = 1L;
    private final static String TITLE = "Title_1";
    private final static String AUTHOR = "Author_1";
    private final static String GENRE = "Genre_1";
    private final static String COMMENT = "Comment_1";
    private Book mockBook;
    private List<Book> mockBooks;
    private List<Comment> mockComments;

    @BeforeEach
    void init() {
        Author mockAuthor = new Author(ID, AUTHOR);
        Genre mockGenre = new Genre(ID, GENRE);
        Comment mockComment = new Comment(ID, COMMENT);
        mockComments = List.of(mockComment);
        mockBook = new Book(ID, TITLE,
                mockAuthor,
                mockGenre,
                mockComments);
        mockBooks = List.of(mockBook);
    }

    @DisplayName("вернуть список книг")
    @Test
    void getAllBooksTest() throws Exception {
        when(bookService.findAll()).thenReturn(mockBooks);
        List<BookDto> expectedResult = mockBooks.stream()
                .map(BookDto::toDto).collect(Collectors.toList());
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(expectedResult)));
        verify(bookService, times(1)).findAll();
    }

    @DisplayName("редактировать книгу")
    @Test
    void editBookTest() throws Exception {
        when(bookService.findById(ID)).thenReturn(mockBook);
        when(bookService.update(ID, TITLE, ID, ID, mockComments)).thenReturn(mockBook);
        BookDto expectedResult = BookDto.toDto(mockBook);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/book/{id}", ID)
                        .content(asJsonString(expectedResult))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(expectedResult)));
        verify(bookService, times(1)).findById(ID);
        verify(bookService, times(1)).update(ID, TITLE, ID, ID, mockComments);
    }

    @DisplayName("создавать новую книгу")
    @Test
    void createBookTest() throws Exception {
        when(bookService.insert(TITLE, ID, ID)).thenReturn(mockBook);
        BookDto expectedResult = BookDto.toDto(mockBook);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(expectedResult)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(expectedResult)));
        verify(bookService, times(1)).insert(TITLE, ID, ID);
    }

    @DisplayName("удалять книгу")
    @Test
    void deleteBookTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/book/{id}", ID))
                .andExpect(status().isOk());
        verify(bookService, times(1)).deleteById(ID);
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}