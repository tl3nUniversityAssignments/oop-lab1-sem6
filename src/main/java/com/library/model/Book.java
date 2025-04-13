package com.library.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private int bookId;
    private String title;
    private String isbn;
    private int publicationYear;
}
