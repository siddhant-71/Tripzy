package com.tripzy.Controller;

import com.tripzy.DTOs.BookingRequestDTO;
import com.tripzy.DTOs.BookingResponseDTO;
import com.tripzy.Entities.Booking;
import com.tripzy.Service.Interface.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/initiate")
    public ResponseEntity<BookingResponseDTO> initiateBooking(@RequestBody BookingRequestDTO bookingRequestDTO){
        Booking booking=bookingService.createBooking(bookingRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapBookingToResponse(booking));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookingsByUserId(@PathVariable("userId")Long userId){
        List<Booking>list=bookingService.getAllBookingsByUserId(userId);
        List<BookingResponseDTO>ans=list.stream().map(this::mapBookingToResponse).toList();
        return ResponseEntity.ok(ans);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable("id")Long id){
        return ResponseEntity.ok(mapBookingToResponse(bookingService.getBookingById(id)));
    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable("id")Long id){
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking Cancelled Successfully");
    }
    public BookingResponseDTO mapBookingToResponse(Booking booking){
        BookingResponseDTO ans=new BookingResponseDTO();
        ans.setId(booking.getId());
        ans.setBookingTime(booking.getBookingTime());
        ans.setFlightId(booking.getFlightId());
        ans.setDepartureTime(booking.getDepartureTime());
        ans.setArrivalTime(booking.getArrivalTime());
        ans.setSource(booking.getSource());
        ans.setDestination(booking.getDestination());
        ans.setSeatNumber(booking.getSeatNumber());
        ans.setSeatClass(booking.getSeatClass());
        ans.setTotalPrice(booking.getTotalPrice());
        ans.setBookingStatus(booking.getBookingStatus());
        ans.setUserId(booking.getUser().getId());
        return ans;
    }
}
