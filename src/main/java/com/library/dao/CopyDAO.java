package com.library.dao;

import com.library.model.Copy;
import com.library.model.CopySearchParameter;
import com.library.util.DBConnection;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CopyDAO {
    private final Connection conn;

    public CopyDAO() {
        this.conn = DBConnection.getConnection();
    }

    public int create(Copy copy) {
        int affectedRows = 0;
        String sql = "INSERT INTO copies (book_id, available) VALUES (?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, copy.getBookId());
            ps.setBoolean(2, copy.isAvailable());

            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while CREATING COPY {}: {}", copy.toString(), String.valueOf(e));
        }

        return affectedRows;
    }

    public List<Copy> getAll() {
        List<Copy> copies = new ArrayList<>();
        String sql = "SELECT * FROM copies";

        try(Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                Copy copy = Copy.builder()
                        .copyId(result.getInt("copy_id"))
                        .bookId(result.getInt("book_id"))
                        .available(result.getBoolean("available"))
                        .build();

                copies.add(copy);
            }
        } catch (SQLException e) {
            log.error("SQLException while GETTING ALL COPIES: {}", String.valueOf(e));
        }

        return copies;
    }

    public List<Copy> getBy(CopySearchParameter parameter, Object value) {
        List<Copy> copies = new ArrayList<>();
        String sql = "SELECT * FROM copies WHERE " + parameter.getName() + " = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, value);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                Copy copy = Copy.builder()
                        .copyId(result.getInt("copy_id"))
                        .bookId(result.getInt("book_id"))
                        .available(result.getBoolean("available"))
                        .build();

                copies.add(copy);
            }
        } catch (SQLException e) {
            log.error("SQLException while GETTING COPIES BY {}: {}", parameter.getName(), String.valueOf(e));
        }

        return copies;
    }

    public int update(Copy copy) {
        int affectedRows = 0;
        String sql = "UPDATE copies SET book_id = ?, available = ? WHERE copy_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, copy.getBookId());
            ps.setBoolean(2, copy.isAvailable());
            ps.setInt(3, copy.getCopyId());

            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while UPDATING COPY {}: {}", copy.toString(), String.valueOf(e));
        }

        return affectedRows;
    }

    public int delete(int copyId) {
        int affectedRows = 0;
        String sql = "DELETE FROM copies WHERE copy_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, copyId);
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while DELETING COPY WITH ID {}: {}", copyId, String.valueOf(e));
        }

        return affectedRows;
    }
}
