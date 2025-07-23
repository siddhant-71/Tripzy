package com.tripzy.Configurations.RequiredDTO;

import lombok.Data;

@Data
public class RazorpayResponseDTO {
    String paymentReference;
    String razorpayOrderId;
    String razorpayPaymentId;
    String razorpaySignature;
}
