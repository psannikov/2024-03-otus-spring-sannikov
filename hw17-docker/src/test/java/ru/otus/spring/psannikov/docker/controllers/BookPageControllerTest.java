package ru.otus.spring.psannikov.docker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.psannikov.docker.models.Author;
import ru.otus.spring.psannikov.docker.models.Book;
import ru.otus.spring.psannikov.docker.models.Genre;
import ru.otus.spring.psannikov.docker.services.AuthorService;
import ru.otus.spring.psannikov.docker.services.BookService;
import ru.otus.spring.psannikov.docker.services.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@DisplayName("Контроллер для BookPage должен")
@WebMvcTest(BookPageController.class)
public class BookPageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookPageController BookPageController;

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
    private Book mockBook;
    private Author mockAuthor;
    private Genre mockGenre;
    private List<Book> mockBooks;
    private List<Author> mockAuthors;
    private List<Genre> mockGenres;

    @BeforeEach
    void init() {
        mockBook = new Book(ID, TITLE,
                new Author(ID, AUTHOR),
                new Genre(ID, GENRE),
                new ArrayList<>());
        mockAuthor = new Author(ID, AUTHOR);
        mockGenre = new Genre(ID, GENRE);
        mockBooks = List.of(mockBook);
        mockAuthors = List.of(mockAuthor);
        mockGenres = List.of(mockGenre);
    }

    @DisplayName("вернуть страницу списка книг")
    @Test
    public void listBooksPageTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("books"));
    }

    @DisplayName("вернуть страницу редактирования книги")
    @Test
    void editBookPageTest() throws Exception {
        when(bookService.findById(ID)).thenReturn(Optional.ofNullable(mockBook));
        when(authorService.findAll()).thenReturn(mockAuthors);
        when(genreService.findAll()).thenReturn(mockGenres);
        mvc.perform(MockMvcRequestBuilders.get("/edit/{id}", ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("editBook"))
                .andExpect(MockMvcResultMatchers.model().attribute("book", mockBook))
                .andExpect(MockMvcResultMatchers.model().attribute("authors", mockAuthors))
                .andExpect(MockMvcResultMatchers.model().attribute("genres", mockGenres));
        verify(bookService, times(1)).findById(ID);
        verify(authorService, times(1)).findAll();
        verify(genreService, times(1)).findAll();
    }

    @DisplayName("вернуть страницу создания книги")
    @Test
    public void createBookPageTest() throws Exception {
        when(authorService.findAll()).thenReturn(mockAuthors);
        when(genreService.findAll()).thenReturn(mockGenres);
        mvc.perform(MockMvcRequestBuilders.get("/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("addBook"))
                .andExpect(MockMvcResultMatchers.model().attribute("authors", mockAuthors))
                .andExpect(MockMvcResultMatchers.model().attribute("genres", mockGenres));
        verify(authorService, times(1)).findAll();
        verify(genreService, times(1)).findAll();
    }

    @GetMapping("/delete/{id}")
    @DisplayName("вернуть страницу удаления книги")
    @Test
    public void deleteBookPageTest() throws Exception {
        when(bookService.findById(ID)).thenReturn(Optional.ofNullable(mockBook));
        mvc.perform(MockMvcRequestBuilders.get("/delete/{id}", ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("deleteBook"))
                .andExpect(MockMvcResultMatchers.model().attribute("book", mockBook));
        verify(bookService, times(1)).findById(ID);
    }
}
