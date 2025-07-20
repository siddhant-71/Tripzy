package com.tripzy.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItineraryResponseDTO {
    private Long id;
    private String requestedCities;
    private Integer numberOfDays;
    private Long userId;
    private LocalDateTime requestedTime;
    private String aiResponse;
}
