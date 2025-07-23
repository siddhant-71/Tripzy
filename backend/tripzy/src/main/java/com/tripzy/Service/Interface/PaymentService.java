package com.tripzy.Service.Interface;

import com.tripzy.Configurations.RequiredDTO.PaymentRequest;
import com.tripzy.Configurations.RequiredDTO.PaymentResponse;
import com.tripzy.Configurations.RequiredDTO.RazorpayResponseDTO;
import com.tripzy.DTOs.PaymentResponseDTO;
import com.tripzy.Entities.Payment;
import com.tripzy.Enums.PaymentStatus;

import java.util.Optional;

public interface PaymentService {
    PaymentResponse initiatePayment(PaymentRequest request);
    Optional<Payment> getPaymentByReference(String reference);
    boolean verifyPayment(RazorpayResponseDTO dto);
    Optional<Payment> getPaymentByBookingId(Long bookingId);
}
