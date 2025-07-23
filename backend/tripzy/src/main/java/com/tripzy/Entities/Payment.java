package com.tripzy.Entities;

import com.tripzy.Enums.Currency;
import com.tripzy.Enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookingId",nullable = false,unique = true)
    private Booking booking;

    private String paymentReference;

    private String RazorpayPaymentID;

    private String RazorpayOrderID;

    private double amount;

    private Currency currency;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime paymentTime;

    @PrePersist
    protected void onCreate(){
        this.paymentTime=LocalDateTime.now();
    }
}
