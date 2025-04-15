package com.library.dao;

import com.library.model.BookAuthor;
import com.library.model.BookAuthorSearchParameter;
import com.library.util.DBConnection;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BookAuthorDAO {
    Connection conn = DBConnection.getConnection();

    public BookAuthorDAO(Connection conn) {
        this.conn = conn;
    }

    public int create(BookAuthor bookAuthor) {
        int affectedRows = 0;
        String sql = "INSERT INTO book_authors (book_id, author_id) VALUES (?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookAuthor.getBookId());
            ps.setInt(2, bookAuthor.getAuthorId());

            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while CREATING BOOK_AUTHOR {}: {}", bookAuthor.toString(), String.valueOf(e));
        }

        return affectedRows;
    }

    public List<BookAuthor> getAll() {
        List<BookAuthor> bookAuthors = new ArrayList<>();
        String sql = "SELECT * FROM book_authors";

        try(Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                BookAuthor bookAuthor = BookAuthor.builder()
                        .bookId(result.getInt("book_id"))
                        .authorId(result.getInt("author_id"))
                        .build();

                bookAuthors.add(bookAuthor);
            }
        } catch (SQLException e) {
            log.error("SQLException while GETTING ALL BOOK_AUTHORS: {}", String.valueOf(e));
        }

        return bookAuthors;
    }

    public List<BookAuthor> getBy(BookAuthorSearchParameter parameter, Object value) {
        List<BookAuthor> bookAuthors = new ArrayList<>();
        String sql = "SELECT * FROM book_authors WHERE " + parameter.getName() + " = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, value);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                BookAuthor bookAuthor = BookAuthor.builder()
                        .bookId(result.getInt("book_id"))
                        .authorId(result.getInt("author_id"))
                        .build();

                bookAuthors.add(bookAuthor);
            }
        } catch (SQLException e) {
            log.error("SQLException WHILE GETTING BOOK_AUTHOR BY {}: {}", parameter.getName(), String.valueOf(e));
        }

        return bookAuthors;
    }

    public int delete(BookAuthor bookAuthor) {
        int affectedRows = 0;
        String sql = "DELETE FROM book_authors WHERE book_id = ? AND author_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookAuthor.getBookId());
            ps.setInt(2, bookAuthor.getAuthorId());

            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while DELETING BOOK_AUTHOR {}: {}", bookAuthor.toString(), String.valueOf(e));
        }

        return affectedRows;
    }
}
