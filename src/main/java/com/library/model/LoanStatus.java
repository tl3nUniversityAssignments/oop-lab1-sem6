package com.library.model;

import lombok.Getter;

@Getter
public enum LoanStatus {
    RETURNED("Returned"),
    CHECKED_OUT("Checked Out"),
    READING_ROOM("Reading Room");

    private final String name;

    LoanStatus(String name) {
        this.name = name;
    }
}
