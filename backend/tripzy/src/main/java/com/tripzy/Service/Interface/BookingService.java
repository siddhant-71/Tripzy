package com.tripzy.Service.Interface;

import com.tripzy.DTOs.BookingRequestDTO;
import com.tripzy.Entities.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequestDTO bookingRequestDTO);
    List<Booking> getAllBookingsByUserId(Long userId);
    Booking getBookingById(Long id);
    void completeBooking(Long id);
    void cancelBooking(Long id);

}
