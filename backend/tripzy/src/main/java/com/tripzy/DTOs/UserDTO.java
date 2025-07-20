package com.tripzy.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String surname;
    private String phone;
    private LocalDate dateOfBirth;
    private String password;
}
