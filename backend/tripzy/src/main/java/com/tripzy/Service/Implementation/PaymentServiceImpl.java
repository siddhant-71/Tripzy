package com.tripzy.Service.Implementation;

import com.tripzy.DTOs.PaymentRequestDTO;
import com.tripzy.DTOs.PaymentResponseDTO;
import com.tripzy.Entities.Booking;
import com.tripzy.Entities.Payment;
import com.tripzy.Enums.PaymentStatus;
import com.tripzy.Repository.BookingRepository;
import com.tripzy.Repository.PaymentRepository;
import com.tripzy.Service.Interface.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Override
    public PaymentResponseDTO initiatePayment(PaymentRequestDTO request) {
        Booking booking=bookingRepository.findById(request.getBookingId()).orElseThrow(()->new RuntimeException("Booking not found"));
        Payment payment=new Payment();
        payment.setPaymentTime(LocalDateTime.now());
        payment.setAmount(request.getAmount());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setBooking(booking);
        payment.setCurrency(request.getCurrency());
        payment.setPaymentReference(UUID.randomUUID().toString());
        paymentRepository.save(payment);
        PaymentResponseDTO ans=new PaymentResponseDTO();
        ans.setPaymentReference(payment.getPaymentReference());
        ans.setAmount(payment.getAmount());
        ans.setCurrency(payment.getCurrency());
        ans.setPaymentStatus(payment.getPaymentStatus());
        ans.setPaymentTime(payment.getPaymentTime());
        return ans;
    }

    @Override
    public Payment completePayment(Payment payment,PaymentStatus status){
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
        return payment;
    }
    @Override
    public Optional<Payment> getPaymentByReference(String reference) {
        return paymentRepository.findByPaymentReference(reference);
    }


    @Override
    public Optional<Payment> getPaymentByBookingId(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }
}
