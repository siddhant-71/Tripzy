package com.tripzy.Configurations.RequiredDTO;

import com.tripzy.Enums.Currency;
import com.tripzy.Enums.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponse {
    private String paymentReference;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String RazorpayOrderID;
    private double amount;
    private LocalDateTime paymentTime;

}
