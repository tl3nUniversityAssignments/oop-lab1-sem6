package com.library.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookDTO {
    private String title;
    private String isbn;
    private int publicationYear;
    private List<String> authorNames;
    private int availableCopies;
}
