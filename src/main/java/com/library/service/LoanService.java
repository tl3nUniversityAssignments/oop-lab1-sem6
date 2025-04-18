package com.library.service;

import com.library.dao.LoanDAO;
import com.library.model.Loan;
import com.library.model.LoanSearchParameter;
import com.library.model.LoanStatus;
import lombok.extern.log4j.Log4j2;

import java.sql.Date;
import java.util.List;

@Log4j2
public class LoanService {
    private final LoanDAO dao;

    public LoanService() {
        this.dao = new LoanDAO();
    }

    public Loan create(int copyId, int readerId, Date loanDate, Date dueDate, LoanStatus status) {
        Loan loan = Loan.builder()
                .copyId(copyId)
                .readerId(readerId)
                .loanDate(loanDate)
                .dueDate(dueDate)
                .status(status)
                .build();

        int createdId = dao.create(loan);
        loan.setLoanId(createdId);
        log.info("Created loan {}", loan.toString());
        return loan;
    }

    public List<Loan> getAll() {
        log.info("Getting all loans");
        return dao.getAll();
    }

    public List<Loan> getById(int loanId) {
        log.info("Getting loans by id {}",loanId);
        return dao.getBy(LoanSearchParameter.LOAN_ID, loanId);
    }

    public List<Loan> getByCopyId(int copyId) {
        log.info("Getting loans by copy id {}", copyId);
        return dao.getBy(LoanSearchParameter.COPY_ID, copyId);
    }

    public List<Loan> getByReaderId(int readerId) {
        log.info("Getting loans by reader id {}", readerId);
        return dao.getBy(LoanSearchParameter.READER_ID, readerId);
    }

    public List<Loan> getByLoanDate(Date loanDate) {
        log.info("Getting loans by loan date {}", loanDate);
        return dao.getBy(LoanSearchParameter.LOAN_DATE, loanDate);
    }

    public List<Loan> getByDueDate(Date dueDate) {
        log.info("Getting loans by due date {}", dueDate);
        return dao.getBy(LoanSearchParameter.DUE_DATE, dueDate);
    }

    public List<Loan> getByReturnDate(Date returnDate) {
        log.info("Getting loans by return date {}", returnDate);
        return dao.getBy(LoanSearchParameter.RETURN_DATE, returnDate);
    }

    public List<Loan> getByStatus(LoanStatus status) {
        log.info("Getting loans by status {}", status.getName());
        return dao.getBy(LoanSearchParameter.STATUS, status.getName());
    }

    public Loan update(Loan loan, Date returnDate, LoanStatus status) {
        if (returnDate != null) {
            loan.setReturnDate(returnDate);
        }
        if (status != null) {
            loan.setStatus(status);
        }

        dao.update(loan);
        log.info("Updated loan {}", loan);
        return loan;
    }

    public void delete(int loanId) {
        log.info("Deleting loan with id {}", loanId);
        dao.delete(loanId);
    }
}
