package com.tripzy.Configurations.RequiredDTO;

import com.tripzy.Enums.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class PaymentRequest {
    long bookingId;
    double amount;
    @Enumerated(EnumType.STRING)
    Currency currency;
}
