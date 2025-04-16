package com.library.model;

import lombok.Builder;
import lombok.Data;


import java.sql.Date;

@Data
@Builder
public class Loan {
    private int loanId;
    private int copyId;
    private int readerId;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private LoanStatus status;
}
