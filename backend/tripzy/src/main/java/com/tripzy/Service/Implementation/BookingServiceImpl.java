package com.tripzy.Service.Implementation;

import com.tripzy.DTOs.BookingRequestDTO;
import com.tripzy.Entities.Booking;
import com.tripzy.Entities.User;
import com.tripzy.Enums.FlightBookingStatus;
import com.tripzy.Repository.BookingRepository;
import com.tripzy.Repository.UserRepository;
import com.tripzy.Service.Interface.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepo;
    @Override
    public Booking createBooking(BookingRequestDTO bookingRequestDTO) {
        User user=userRepo.findById(bookingRequestDTO.getUserId()).orElseThrow(()->new RuntimeException("User not found"));
        Booking booking=new Booking();
        //IF SEAT IS ALREADY BOOKED RETURN NULL NO BOOKING WILL HAPPEN DO THIS LATER
        booking.setUser(user);
        booking.setFlightId(bookingRequestDTO.getFlightId());
        booking.setDepartureTime(bookingRequestDTO.getDepartureTime());
        booking.setArrivalTime(bookingRequestDTO.getArrivalTime());
        booking.setSource(bookingRequestDTO.getSource());
        booking.setDestination(bookingRequestDTO.getDestination());
        booking.setSeatNumber(bookingRequestDTO.getSeatNumber());
        booking.setSeatClass(bookingRequestDTO.getSeatClass());
        booking.setTotalPrice(bookingRequestDTO.getTotalPrice());
        booking.setBookingStatus(FlightBookingStatus.BOOKED);
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public List<Booking> getAllBookingsByUserId(Long userId) {
        return bookingRepository.findAllByUserId(userId);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(()->new RuntimeException("Booking not found"));
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking=bookingRepository.findById(id).orElseThrow(()->new RuntimeException("Booking not found"));
        booking.setBookingStatus(FlightBookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }
}
