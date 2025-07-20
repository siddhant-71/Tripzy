package com.tripzy.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequestDTO {
    private Long userId;
    private String flightId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String source;
    private String destination;
    private String seatNumber;
    private String seatClass;
    private double totalPrice;
}
