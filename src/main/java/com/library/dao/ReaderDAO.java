package com.library.dao;

import com.library.model.Reader;
import com.library.model.ReaderSearchParameter;
import com.library.util.DBConnection;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ReaderDAO {
    private final Connection conn;

    public ReaderDAO() {
        this.conn = DBConnection.getConnection();
    }

    public int create(Reader reader) {
        int createdId = -1;
        String sql = "INSERT INTO readers (name, address, phone, email) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reader.getName());
            ps.setString(2, reader.getAddress());
            ps.setString(3, reader.getPhone());
            ps.setString(4, reader.getEmail());

            ResultSet result = ps.executeQuery();
            while(result.next()) {
                createdId = result.getInt("reader_id");
            }
        } catch(SQLException e) {
            log.error("SQLException while CREATING READER {}: {}", reader.toString(), String.valueOf(e));
        }

        return createdId;
    }

    public List<Reader> getAll() {
        List<Reader> readers = new ArrayList<>();
        String sql = "SELECT * FROM readers";

        try(Statement statement = conn.createStatement()) {
           ResultSet result = statement.executeQuery(sql);

           while(result.next()) {
               Reader reader = Reader.builder()
                       .readerId(result.getInt("reader_id"))
                       .name(result.getString("name"))
                       .address(result.getString("address"))
                       .phone(result.getString("phone"))
                       .email(result.getString("email"))
                       .build();

               readers.add(reader);
           }
        } catch(SQLException e) {
            log.error("SQLException while GETTING ALL READERS: {}", String.valueOf(e));
        }

        return readers;
    }

    public List<Reader> getBy(ReaderSearchParameter parameter, Object value) {
        List<Reader> readers = new ArrayList<>();
        String sql = "SELECT * FROM readers WHERE " + parameter.getName() + " = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, value);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                Reader reader = Reader.builder()
                        .readerId(result.getInt("reader_id"))
                        .name(result.getString("name"))
                        .address(result.getString("address"))
                        .phone(result.getString("phone"))
                        .email(result.getString("email"))
                        .build();

                readers.add(reader);
            }
        } catch (SQLException e) {
            log.error("SQLException while GETTING READERS BY {}: {}", parameter.getName(), String.valueOf(e));
        }

        return readers;
    }

    public int update(Reader reader) {
        int affectedRows = 0;
        String sql = "UPDATE readers SET name = ?, address = ?, phone = ?, email = ? WHERE reader_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reader.getName());
            ps.setString(2, reader.getAddress());
            ps.setString(3, reader.getPhone());
            ps.setString(4, reader.getEmail());
            ps.setInt(5, reader.getReaderId());

            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while UPDATING READER {}: {}", reader.toString(), String.valueOf(e));
        }

        return affectedRows;
    }

    public int delete(int readerId) {
        int affectedRows = 0;
        String sql = "DELETE FROM readers WHERE reader_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, readerId);
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while DELETING READER WITH ID {}: {}", readerId, String.valueOf(e));
        }

        return affectedRows;
    }
}
