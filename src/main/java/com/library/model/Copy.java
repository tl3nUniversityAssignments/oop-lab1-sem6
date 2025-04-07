package com.library.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Copy {
    private int copyId;
    private int bookId;
    private LocalDate purchaseDate;
    private String status; // "Available", "Checked Out", "Reading Room"
}
