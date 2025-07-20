package com.tripzy.Repository;

import com.tripzy.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByBookingId(Long bookingId);
    Optional<Payment> findByPaymentReference(String reference);
}
