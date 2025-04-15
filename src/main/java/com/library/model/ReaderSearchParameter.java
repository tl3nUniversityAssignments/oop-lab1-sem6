package com.library.model;

import lombok.Getter;

@Getter
public enum ReaderSearchParameter {
    READER_ID("reader_id"),
    NAME("name"),
    ADDRESS("address"),
    PHONE("phone"),
    EMAIL("email");

    private final String name;

    ReaderSearchParameter(String name) {
        this.name = name;
    }
}
