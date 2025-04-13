package com.library.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    private int authorId;
    private String name;
}
