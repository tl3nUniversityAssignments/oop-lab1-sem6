package com.library.dao;

import com.library.model.Loan;
import com.library.model.LoanSearchParameter;
import com.library.model.LoanStatus;
import com.library.util.DBConnection;
import lombok.extern.log4j.Log4j2;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class LoanDAO {
    private final Connection conn;

    public LoanDAO() {
        this.conn = DBConnection.getConnection();
    }

    public int create(Loan loan) {
        int affectedRows = 0;
        String sql = "INSERT INTO loans (copy_id, reader_id, loan_date, due_date, return_date, status) VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, loan.getCopyId());
            ps.setInt(2, loan.getReaderId());
            ps.setDate(3, loan.getLoanDate());
            ps.setDate(4, loan.getDueDate());
            ps.setDate(5, loan.getReturnDate());
            ps.setString(6, loan.getStatus().getName());

            affectedRows = ps.executeUpdate();
        } catch(SQLException e) {
            log.error("SQLException while CREATING LOAN {}: {}", loan.toString(), String.valueOf(e));
        }

        return affectedRows;
    }

    public List<Loan> getAll() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM loans";

        try(Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                Loan loan = Loan.builder()
                        .loanId(result.getInt("loan_id"))
                        .copyId(result.getInt("copy_id"))
                        .readerId(result.getInt("reader_id"))
                        .loanDate(result.getDate("loan_date"))
                        .dueDate(result.getDate("due_date"))
                        .returnDate(result.getDate("return_date"))
                        .status(LoanStatus.valueOf(result.getString("status")))
                        .build();

                loans.add(loan);
            }
        } catch(SQLException e) {
            log.error("SQLException while GETTING ALL LOANS: {}", String.valueOf(e));
        }

        return loans;
    }

    public List<Loan> getBy(LoanSearchParameter parameter, Object value) {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM loans WHERE " + parameter.getName() + " = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, value);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                Loan loan = Loan.builder()
                        .loanId(result.getInt("loan_id"))
                        .copyId(result.getInt("copy_id"))
                        .readerId(result.getInt("reader_id"))
                        .loanDate(result.getDate("loan_date"))
                        .dueDate(result.getDate("due_date"))
                        .returnDate(result.getDate("return_date"))
                        .status(LoanStatus.valueOf(result.getString("status")))
                        .build();

                loans.add(loan);
            }
        } catch (SQLException e) {
            log.error("SQLException while GETTING LOANS BY {}: {}", parameter.getName(), String.valueOf(e));
        }

        return loans;
    }

    public int update(Loan loan) {
        int affectedRows = 0;
        String sql = "UPDATE loans SET copy_id = ?, reader_id = ?, loan_date = ?, due_date = ?, return_date = ?, status = ? WHERE loan_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, loan.getCopyId());
            ps.setInt(2, loan.getReaderId());
            ps.setDate(3, loan.getLoanDate());
            ps.setDate(4, loan.getDueDate());
            ps.setDate(5, loan.getReturnDate());
            ps.setString(6, loan.getStatus().getName());
            ps.setInt(7, loan.getLoanId());

            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException while UPDATING LOANS {}: {}", loan.toString(), String.valueOf(e));
        }

        return affectedRows;
    }

    public int delete(int loanId) {
        int affectedRows = 0;
        String sql = "DELETE FROM loans WHERE loan_id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, loanId);
            affectedRows = ps.executeUpdate();
        } catch(SQLException e) {
            log.error("SQLException while DELETING LOAN WITH ID {}: {}", loanId, String.valueOf(e));
        }

        return affectedRows;
    }
}
