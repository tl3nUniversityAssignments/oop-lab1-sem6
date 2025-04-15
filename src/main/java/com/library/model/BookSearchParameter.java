package com.library.model;

import lombok.Getter;

@Getter
public enum BookSearchParameter {
    BOOK_ID("book_id"),
    TITLE("title"),
    ISBN("isbn"),
    PUBLISHED_YEAR("publication_year");

    private final String name;

    BookSearchParameter(String name) {
        this.name = name;
    }

}
