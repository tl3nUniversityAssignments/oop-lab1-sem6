package com.library.model;

import lombok.Getter;

@Getter
public enum LoanSearchParameter {
    LOAN_ID("loan_id"),
    COPY_ID("copy_id"),
    READER_ID("reader_id"),
    LOAN_DATE("loan_date"),
    DUE_DATE("due_date"),
    RETURN_DATE("return_date"),
    STATUS("status");

    private final String name;

    LoanSearchParameter(String name) {
        this.name = name;
    }
}
