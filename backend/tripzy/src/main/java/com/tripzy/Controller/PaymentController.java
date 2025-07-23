package com.tripzy.Controller;


import com.tripzy.Configurations.RequiredDTO.PaymentRequest;
import com.tripzy.Configurations.RequiredDTO.PaymentResponse;
import com.tripzy.Configurations.RequiredDTO.RazorpayResponseDTO;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody PaymentRequest request){
        PaymentResponse ans=paymentService.initiatePayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ans);
    }
    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody RazorpayResponseDTO responseDTO) {
        boolean isVerified = paymentService.verifyPayment(responseDTO);

        if (isVerified) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.ok("FAILED");
        }
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
