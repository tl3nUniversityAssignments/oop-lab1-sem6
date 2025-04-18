package com.library.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Copy {
    private int copyId;
    private int bookId;
    private boolean available;
}
