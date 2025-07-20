package com.tripzy.DTOs;

import com.tripzy.Enums.Currency;
import com.tripzy.Enums.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponseDTO {
    private String paymentReference;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private double amount;
    private LocalDateTime paymentTime;
}
