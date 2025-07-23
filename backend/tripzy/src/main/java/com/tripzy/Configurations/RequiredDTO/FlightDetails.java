package com.tripzy.Configurations.RequiredDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDetails {
    String airline;
    String flightNumber;
    LocalDateTime departureTime;
    String departureAirportName;
    String departureAirportCode;
    LocalDateTime arrivalTime;
    String arrivalAirportName;
    String arrivalAirportCode;
    double price;
    double totalDurationInMinutes;
    String logo;
}
