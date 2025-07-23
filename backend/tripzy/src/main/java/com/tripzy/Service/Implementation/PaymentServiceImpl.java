package com.tripzy.Service.Implementation;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.tripzy.Configurations.RequiredDTO.PaymentRequest;
import com.tripzy.Configurations.RequiredDTO.PaymentResponse;
import com.tripzy.Configurations.RequiredDTO.RazorpayResponseDTO;
import com.tripzy.DTOs.PaymentRequestDTO;
import com.tripzy.DTOs.PaymentResponseDTO;
import com.tripzy.Entities.Booking;
import com.tripzy.Entities.Payment;
import com.tripzy.Enums.PaymentStatus;
import com.tripzy.Repository.BookingRepository;
import com.tripzy.Repository.PaymentRepository;
import com.tripzy.Service.Interface.PaymentService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Override
    public PaymentResponse initiatePayment(PaymentRequest request) {
        Booking booking=bookingRepository.findById(request.getBookingId()).orElseThrow(()->new RuntimeException("Booking not found"));

        RazorpayClient client;
        String clientKeyId;
        try{
            client=new RazorpayClient("rzp_test_YmTwJC615FKYFA","VDx0JBuiVVgordrjrDbGh6bs");
            JSONObject options=new JSONObject();
            options.put("amount",100);
            options.put("currency",request.getCurrency());
            options.put("receipt","receipt"+booking.getId());

            Order order=client.orders.create(options);
            clientKeyId=order.get("id");
        }
        catch (Exception e){
            throw new RuntimeException("Error while initiating payment");
        }


        Payment payment=new Payment();
        payment.setPaymentTime(LocalDateTime.now());
        payment.setAmount(request.getAmount());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setBooking(booking);
        payment.setRazorpayOrderID(clientKeyId);
        payment.setCurrency(request.getCurrency());
        payment.setPaymentReference(UUID.randomUUID().toString());
        paymentRepository.save(payment);

        PaymentResponse ans=new PaymentResponse();
        ans.setPaymentReference(payment.getPaymentReference());
        ans.setAmount(payment.getAmount());
        ans.setRazorpayOrderID(payment.getRazorpayOrderID());
        ans.setCurrency(payment.getCurrency());
        ans.setPaymentStatus(payment.getPaymentStatus());
        ans.setPaymentTime(payment.getPaymentTime());
        return ans;
    }

    @Override
    public Optional<Payment> getPaymentByReference(String reference) {
        return paymentRepository.findByPaymentReference(reference);
    }


//    private String getSHA256HMAC(String data, String secret) throws Exception {
//        SecretKeySpec key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
//        Mac mac = Mac.getInstance("HmacSHA256");
//        mac.init(key);
//        byte[] hmacBytes = mac.doFinal(data.getBytes());
//        return new String(Base64.getEncoder().encode(hmacBytes));
//    }
    private String getSHA256HMAC(String data, String secret) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
        byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hmacBytes); // Don't wrap in new String()
    }

    @Override
    public boolean verifyPayment(RazorpayResponseDTO dto) {
        try {
            System.out.println("oneStep Cleared");
            String generatedSignature = getSHA256HMAC(dto.getRazorpayOrderId() + "|" + dto.getRazorpayPaymentId(),
                    "VDx0JBuiVVgordrjrDbGh6bs");
                Payment payment = paymentRepository.findByPaymentReference(dto.getPaymentReference())
                        .orElseThrow(() -> new RuntimeException("Payment not found"));
                payment.setPaymentStatus(PaymentStatus.SUCCESS);
                payment.setRazorpayPaymentID(dto.getRazorpayPaymentId());
                paymentRepository.save(payment);
                return true;
        } catch (Exception e) {
            Payment payment=paymentRepository.findByPaymentReference(dto.getPaymentReference()).orElseThrow(()->new RuntimeException("payment not found"));
            payment.setPaymentStatus(PaymentStatus.FAILED);
            System.out.println(e.toString());
            return false;
        }
    }


    @Override
    public Optional<Payment> getPaymentByBookingId(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }
}
