package com.library.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reader {
    private int readerId;
    private String name;
    private String address;
    private String phone;
    private String email;
}
