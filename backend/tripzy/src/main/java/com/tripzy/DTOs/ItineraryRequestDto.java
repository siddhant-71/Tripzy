package com.tripzy.DTOs;

import lombok.Data;

@Data
public class ItineraryRequestDto {
    private Long userId;
    private String requestedCities;
    private Integer numberOfDays;
}
