package com.tripzy.Service.Interface;

import com.tripzy.DTOs.PaymentRequestDTO;
import com.tripzy.DTOs.PaymentResponseDTO;
import com.tripzy.Entities.Payment;
import com.tripzy.Enums.PaymentStatus;

import java.util.Optional;

public interface PaymentService {
    PaymentResponseDTO initiatePayment(PaymentRequestDTO request);
    Optional<Payment> getPaymentByReference(String reference);
    Payment completePayment(Payment payment,PaymentStatus status);
    Optional<Payment> getPaymentByBookingId(Long bookingId);
}
