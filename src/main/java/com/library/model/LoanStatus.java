package com.library.model;

import lombok.Getter;

@Getter
public enum LoanStatus {
    ORDERED("Ordered"),
    RETURNED("Returned"),
    CHECKED_OUT("Checked Out"),
    READING_ROOM("Reading Room");

    private final String name;

    LoanStatus(String name) {
        this.name = name;
    }

    public static LoanStatus fromName(String name) {
        for (LoanStatus status : values()) {
            if (status.getName().equalsIgnoreCase(name)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid loan status: " + name);
    }

}
