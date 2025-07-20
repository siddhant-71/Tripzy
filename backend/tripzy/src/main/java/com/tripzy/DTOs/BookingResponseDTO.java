package com.tripzy.DTOs;

import com.tripzy.Entities.User;
import com.tripzy.Enums.FlightBookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class BookingResponseDTO {

    private Long id;

    private String flightId;

    private LocalDateTime bookingTime;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String source;

    private String destination;

    private double totalPrice;

    private String seatNumber;

    private String seatClass;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private FlightBookingStatus bookingStatus;
}
