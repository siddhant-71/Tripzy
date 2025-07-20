package com.tripzy.DTOs;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserNoPasswordDTO {
    private Long id;
    private String name;
    private String email;
    private String surname;
    private String phone;
    private LocalDate dateOfBirth;
}
