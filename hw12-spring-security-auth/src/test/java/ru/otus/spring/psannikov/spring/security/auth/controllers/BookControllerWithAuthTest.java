package ru.otus.spring.psannikov.spring.security.auth.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.psannikov.spring.security.auth.config.SecurityConfiguration;
import ru.otus.spring.psannikov.spring.security.auth.dto.BookDto;
import ru.otus.spring.psannikov.spring.security.auth.models.Author;
import ru.otus.spring.psannikov.spring.security.auth.models.Book;
import ru.otus.spring.psannikov.spring.security.auth.models.Comment;
import ru.otus.spring.psannikov.spring.security.auth.models.Genre;
import ru.otus.spring.psannikov.spring.security.auth.services.AuthorService;
import ru.otus.spring.psannikov.spring.security.auth.services.BookService;
import ru.otus.spring.psannikov.spring.security.auth.services.GenreService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@Import(SecurityConfiguration.class)
class BookControllerWithAuthTest {

    @Autowired
    private MockMvc mockMvc;

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
    private Author mockAuthor;
    private Genre mockGenre;
    private List<Book> mockBooks;
    private Comment mockComment;
    private List<Comment> mockComments;
    private static UserDetails userDetails;

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

//        genres.add(new Genre(1L, "Genre1"));
//        genres.add(new Genre(2L, "Genre2"));
//        authors.add(new Author(1L, "Author1"));
//        authors.add(new Author(2L, "Author2"));
//        books.add(new BookDto(1L, "Book1", authors.get(0), genres.get(0)));
//        books.add(new BookDto(2L, "Book2", authors.get(1), genres.get(1)));
//        books.add(new BookDto(3L, "Book3", authors.get(1), genres.get(1)));

        userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username("User1")
                .password("Pass1")
                .roles("USER")
                .build();
    }

//    private void mock() {
//        when(bookService.findAll()).thenReturn(books);
//        when(genreService.findAll()).thenReturn(genres);
//        when(authorService.findAll()).thenReturn(authors);
//        when(bookService.findById(1L)).thenReturn(books.get(0));
//    }
//
    @DisplayName("Должен вернуть страницу списка книг")
    @Test
    void listBooksPage() throws Exception {
//        mock();
        mockMvc.perform(get("/books").with(user(userDetails)))
                .andExpect(status().isOk())
                .andExpect(view().name("books"));
    }
//
//
//    @DisplayName("Должен вернуть страницу добавления книги")
//    @Test
//    void createBookPage() throws Exception {
//        mock();
//
//        mockMvc.perform(get("/create/book").with(user(userDetails)))
//                .andExpect(status().isOk())
//                .andExpect(view().name("create"))
//                .andExpect(model().attribute("genres", genres))
//                .andExpect(model().attribute("authors", authors));
//    }
//
//    @DisplayName("Должен вернуть страницу редактирования книги")
//    @Test
//    void editPage() throws Exception {
//        mock();
//        final BookDto bookDto = books.get(0);
//
//        mockMvc.perform(get(String.format("/edit/book/%d", bookDto.getId())).with(user(userDetails)))
//                .andExpect(status().isOk())
//                .andExpect(view().name("edit"))
//                .andExpect(model().attribute("genres", genres))
//                .andExpect(model().attribute("authors", authors))
//                .andExpect(model().attribute("book", bookDto));
//    }
//
//
//    @DisplayName("Должен вернуть страницу удаления книги")
//    @Test
//    void deletePage() throws Exception {
//        mock();
//        final BookDto bookDto = books.get(0);
//
//        mockMvc.perform(get(String.format("/delete/book/%d", bookDto.getId())).with(user(userDetails)))
//                .andExpect(status().isOk())
//                .andExpect(view().name("delete"))
//                .andExpect(model().attribute("book", bookDto));
//    }
//
//    @DisplayName("Должен изменить книгу")
//    @Test
//    void update() throws Exception {
//        final BookDto bookDto = books.get(0);
//        final BookUpdateDto bookUpdateDto = getBookUpdateDtoByBookDto(bookDto);
//
//        when(bookService.update(bookUpdateDto)).thenReturn(bookDto);
//
//        mockMvc.perform(post(String.format("/edit/book/%d", bookUpdateDto.getId())).with(user(userDetails))
//                .param("id", bookUpdateDto.getId().toString())
//                .param("title", bookUpdateDto.getTitle())
//                .param("authorId", bookUpdateDto.getAuthorId().toString())
//                .param("genreId", bookUpdateDto.getGenreId().toString())
//        )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/list"));
//    }
//
//    @DisplayName("Должен вернуть страницу редактирования книги если был передан не допустимый title книги")
//    @Test
//    void editBookValidTitle() throws Exception {
//        final BookDto bookDto = books.get(0);
//        final BookUpdateDto bookUpdateDto = getBookUpdateDtoByBookDto(bookDto);
//        bookUpdateDto.setTitle("R");
//
//        when(bookService.update(bookUpdateDto)).thenReturn(bookDto);
//
//        mockMvc.perform(post(String.format("/edit/book/%d", bookUpdateDto.getId())).with(user(userDetails))
//                .param("id", bookUpdateDto.getId().toString())
//                .param("title", bookUpdateDto.getTitle())
//                .param("authorId", bookUpdateDto.getAuthorId().toString())
//                .param("genreId", bookUpdateDto.getGenreId().toString())
//        )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name(String.format("redirect:/edit/book/%d", bookUpdateDto.getId())));
//    }
//
//    @DisplayName("Должен удалить книгу")
//    @Test
//    void deleteBook() throws Exception {
//        mockMvc.perform(post(String.format("/delete/book/%d", books.get(0).getId())).with(user(userDetails)))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/list"));
//    }
//
//    @DisplayName("Должен добавить книгу")
//    @Test
//    void createBook() throws Exception {
//        final BookDto bookDto = books.get(0);
//        final BookCreateDto bookCreateDto = getBookCreateDtoByBookDto(bookDto);
//
//        when(bookService.create(bookCreateDto)).thenReturn(bookDto);
//
//        mockMvc.perform(post("/create/book").with(user(userDetails))
//                .param("title", bookCreateDto.getTitle())
//                .param("authorId", bookCreateDto.getAuthorId().toString())
//                .param("genreId", bookCreateDto.getGenreId().toString())
//        )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/list"));
//    }
//
//    @DisplayName("Должен вернуть на страницу добавления книги")
//    @Test
//    void createBookValidTitle() throws Exception {
//        final BookDto bookDto = books.get(0);
//        final BookCreateDto bookCreateDto = getBookCreateDtoByBookDto(bookDto);
//        bookCreateDto.setTitle("r");
//
//        when(bookService.create(bookCreateDto)).thenReturn(bookDto);
//
//        mockMvc.perform(post("/create/book").with(user(userDetails))
//                .param("title", bookCreateDto.getTitle())
//                .param("authorId", bookCreateDto.getAuthorId().toString())
//                .param("genreId", bookCreateDto.getGenreId().toString())
//        )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/create/book"));
//    }
//
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