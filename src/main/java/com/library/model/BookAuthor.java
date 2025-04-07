package com.library.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookAuthor {
    private int bookId;
    private int authorId;
}
