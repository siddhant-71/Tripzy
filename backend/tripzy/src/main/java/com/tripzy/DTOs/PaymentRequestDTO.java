package com.tripzy.DTOs;

import com.tripzy.Enums.Currency;
import com.tripzy.Enums.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long bookingId;
    private double amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
