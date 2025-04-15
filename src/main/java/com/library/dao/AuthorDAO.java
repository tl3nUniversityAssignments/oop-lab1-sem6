package com.library.dao;

import com.library.model.Author;
import com.library.model.AuthorSearchParameter;
import com.library.util.DBConnection;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AuthorDAO {
    Connection conn = DBConnection.getConnection();

    public AuthorDAO(Connection conn) {
        this.conn = conn;
    }

    public int create(Author author) {
        int affectedRows = 0;
        String sql = "INSERT INTO authors (name) VALUES (?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, author.getName());
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while CREATING AUTHOR {}: {}", author.toString(), String.valueOf(e));
        }

        return affectedRows;
    }

    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM authors";

        try(Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                Author author = Author.builder()
                        .authorId(result.getInt("author_id"))
                        .name(result.getString("name"))
                        .build();

                authors.add(author);
            }
        } catch (SQLException e) {
            log.error("SQLException while GETTING ALL AUTHORS: {}", String.valueOf(e));
        }

        return authors;
    }

    public List<Author> getBy(AuthorSearchParameter parameter, Object value) {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM authors WHERE " + parameter.getName() + " = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, value);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                Author author = Author.builder()
                        .authorId(result.getInt("author_id"))
                        .name(result.getString("name"))
                        .build();

                authors.add(author);
            }
        } catch (SQLException e) {
            log.error("SQLException while GETTING AUTHOR BY {} with value {}: {}", parameter.getName(), value, String.valueOf(e));
        }

        return authors;
    }

    public int update(Author author) {
        int affectedRows = 0;
        String sql = "UPDATE authors SET name = ? WHERE author_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, author.getName());
            ps.setInt(2, author.getAuthorId());

            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while UPDATING AUTHOR {}: {}", author.toString(), String.valueOf(e));
        }

        return affectedRows;
    }

    public int delete(int authorId) {
        int affectedRows = 0;
        String sql = "DELETE FROM authors WHERE author_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, authorId);

            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while DELETING AUTHOR WITH ID {}: {}", authorId, String.valueOf(e));
        }

        return affectedRows;
    }
}
