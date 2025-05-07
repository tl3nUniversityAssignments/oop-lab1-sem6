package com.library.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Builder
public class LoanDTO {
    private String bookTitle;
    private List<String> authorNames;
    private String readerName;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private String loanStatus;
}
