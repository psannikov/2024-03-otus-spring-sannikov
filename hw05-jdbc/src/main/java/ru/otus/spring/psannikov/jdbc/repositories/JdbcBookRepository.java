package ru.otus.spring.psannikov.jdbc.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.psannikov.jdbc.models.Author;
import ru.otus.spring.psannikov.jdbc.models.Book;
import ru.otus.spring.psannikov.jdbc.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcBookRepository implements BookRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public JdbcBookRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Optional<Book> findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return Optional.of(namedParameterJdbcOperations.queryForObject(
                    "select books.id, books.title, author_id, full_name, genre_id, name " +
                            "from books " +
                            "join authors on books.author_id = authors.id " +
                            "join genres on books.genre_id = genres.id " +
                            "where books.id = :id",
                    params,
                    new BookRowMapper()));
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }
    }

    @Override
    public List<Book> findAll() {
        return namedParameterJdbcOperations.query(
                "select books.id, books.title, author_id, full_name, genre_id, name " +
                        "from books " +
                        "join authors on books.author_id = authors.id " +
                        "join genres on books.genre_id = genres.id ",
                new BookRowMapper());
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            return insert(book);
        }
        return update(book);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params);
    }

    private Book insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());
        var keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                "insert into books (title, author_id, genre_id) " +
                        "values (:title, :author_id, :genre_id)",
                params, keyHolder,new String[]{"id"});
        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    private Book update(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", book.getId());
        params.addValue("title", book.getTitle());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());
        namedParameterJdbcOperations.update(
                "update books " +
                        "set title = :title, author_id = :author_id, genre_id = :genre_id " +
                        "where id = :id", params);
        return book;
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            var id = rs.getLong("id");
            var title = rs.getString("title");
            var authorId = rs.getLong("author_id");
            var authorFullName = rs.getString("full_name");
            var genreId = rs.getLong("genre_id");
            var genreName = rs.getString("name");
            return new Book(id,title,new Author(authorId,authorFullName), new Genre(genreId,genreName));
        }
    }
}
