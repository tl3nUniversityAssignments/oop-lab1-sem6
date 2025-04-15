package com.library.dao;

import com.library.model.Book;
import com.library.model.BookSearchParameter;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BookDAO {
    private final Connection conn;

    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    public int create(Book book) {
        int affectedRows = 0;
        String sql = "INSERT INTO books (title, isbn, publication_year) VALUES (?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setInt(3, book.getPublicationYear());

            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while CREATING BOOK: {}", String.valueOf(e));
        }

        return affectedRows;
    }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try(Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                Book book = Book.builder()
                        .bookId(result.getInt("book_id"))
                        .title(result.getString("title"))
                        .isbn(result.getString("isbn"))
                        .publicationYear(result.getInt("publication_year"))
                        .build();

                books.add(book);
            }
        } catch (SQLException e) {
            log.error("SQLException while GETTING ALL BOOKS: {}", String.valueOf(e));
        }

        return books;
    }

    public List<Book> getBy(BookSearchParameter parameter, Object value) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE " + parameter.getName() + " = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, value);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Book book = Book.builder()
                        .bookId(result.getInt("book_id"))
                        .title(result.getString("title"))
                        .isbn(result.getString("isbn"))
                        .publicationYear(result.getInt("publication_year"))
                        .build();

                books.add(book);
            }
        } catch (SQLException e) {
            log.error("SQLException while GETTING BOOK BY {} with value {}: {}", parameter.getName(), value, String.valueOf(e));
        }

        return books;
    }

    public int update(Book book) {
        int affectedRows = 0;
        String sql = "UPDATE books SET title = ?, isbn = ?, publication_year = ? WHERE book_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setInt(3, book.getPublicationYear());
            ps.setInt(4, book.getBookId());

            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while UPDATING BOOK {}: {}", book.toString(), String.valueOf(e));

        }

        return affectedRows;
    }

    public int delete(int bookId) {
        int affectedRows = 0;
        String sql = "DELETE FROM books WHERE book_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);

            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while DELETING BOOK WITH ID {}: {}", bookId, String.valueOf(e));
        }

        return affectedRows;
    }
}