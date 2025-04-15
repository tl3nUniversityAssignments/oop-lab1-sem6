package com.library.model;

import lombok.Getter;

@Getter
public enum BookAuthorSearchParameter {
    BOOK_ID("book_id"),
    AUTHOR_ID("author_id");

    private final String name;

    BookAuthorSearchParameter(String name) {
        this.name = name;
    }
}
