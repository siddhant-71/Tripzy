package com.tripzy.Controller;


import com.tripzy.DTOs.PaymentRequestDTO;
import com.tripzy.DTOs.PaymentResponseDTO;
import com.tripzy.Entities.Payment;
import com.tripzy.Enums.PaymentStatus;
import com.tripzy.Repository.PaymentRepository;
import com.tripzy.Service.Implementation.PaymentServiceImpl;
import com.tripzy.Service.Interface.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponseDTO> initiatePayment(@RequestBody PaymentRequestDTO requestDTO){
        PaymentResponseDTO ans=paymentService.initiatePayment(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ans);
    }
    @PostMapping("/complete/{id}")
    public ResponseEntity<PaymentResponseDTO> completePayment(@PathVariable("id") Long bookingId,@RequestParam String status){
        Payment payment=paymentService.getPaymentByBookingId(bookingId).orElseThrow(()->new RuntimeException("Payment not found"));
        paymentService.completePayment(payment,PaymentStatus.valueOf(status));
        return ResponseEntity.ok(mapToPaymentResponse(payment));
    }
    @GetMapping("/booking/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByBookingId(@PathVariable("id") Long bookingId){
        Optional<Payment> ans=paymentService.getPaymentByBookingId(bookingId);
        return ans.map(payment -> ResponseEntity.ok(mapToPaymentResponse(payment))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<PaymentResponseDTO> getPaymentByReference(@PathVariable("reference")String reference){
        Optional<Payment> payment=paymentService.getPaymentByReference(reference);
        return payment.map(value -> ResponseEntity.ok(mapToPaymentResponse(value))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    private PaymentResponseDTO mapToPaymentResponse(Payment payment){
        PaymentResponseDTO ans=new PaymentResponseDTO();
        ans.setPaymentTime(payment.getPaymentTime());
        ans.setPaymentReference(payment.getPaymentReference());
        ans.setPaymentStatus(payment.getPaymentStatus());
        ans.setCurrency(payment.getCurrency());
        ans.setAmount(payment.getAmount());
        return ans;
    }
}
