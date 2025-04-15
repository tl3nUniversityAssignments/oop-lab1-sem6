package com.library.model;

import lombok.Getter;

@Getter
public enum CopySearchParameter {
    COPY_ID("copy_id"),
    BOOK_ID("book_id"),
    AVAILABLE("available");

    private final String name;

    CopySearchParameter(String name) {
        this.name = name;
    }
}
