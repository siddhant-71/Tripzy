package com.tripzy.Entities;

import com.tripzy.Enums.Currency;
import com.tripzy.Enums.FlightBookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",nullable = false)
    private User user;

    private String flightId;

    private LocalDateTime bookingTime;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String source;

    private String destination;

    private double totalPrice;

    private String seatNumber;

    private String seatClass;

    @Enumerated(EnumType.STRING)
    private FlightBookingStatus bookingStatus;

    private String paymentReference;

    @PrePersist
    protected void onCreate(){
        this.bookingTime=LocalDateTime.now();
    }
}
