package com.library.model;

import lombok.Getter;

@Getter
public enum AuthorSearchParameter {
    AUTHOR_ID("author_id"),
    NAME("name");

    private final String name;

    AuthorSearchParameter(String name) {
        this.name = name;
    }
}
