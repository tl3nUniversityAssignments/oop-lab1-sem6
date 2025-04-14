package com.library.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Loan {
    private int loanId;
    private int copyId;
    private int readerId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus status;

    public enum LoanStatus {
        RETURNED, CHECKED_OUT, READING_ROOM
    }
}
