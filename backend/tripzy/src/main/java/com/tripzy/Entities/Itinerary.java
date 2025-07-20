package com.tripzy.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "itineraries")
@AllArgsConstructor
@NoArgsConstructor
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",nullable = false)
    private User user;
    private String requestedCities;
    private Integer numberOfDays;
    private LocalDateTime requestedTime;
    @Lob
    private String aiResponse;
    @PrePersist
    protected void onCreate(){
        this.requestedTime=LocalDateTime.now();
    }
}
