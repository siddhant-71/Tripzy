package com.tripzy.DTOs;

import lombok.Data;

@Data
public class JwtResponseDTO {
    private String jwtToken;
    private Long userId;
}
