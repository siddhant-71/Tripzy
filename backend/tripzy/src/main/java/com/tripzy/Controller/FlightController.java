package com.tripzy.Controller;


import com.tripzy.Configurations.RequiredDTO.FlightSearchResponse;
import com.tripzy.ExternalService.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {

    @Autowired
    private FlightService flightService;
    @GetMapping("/")
    public ResponseEntity<FlightSearchResponse> getFlights(@RequestParam String source, @RequestParam String destination, @RequestParam String returnDate, @RequestParam String deptDate){
        return ResponseEntity.ok(flightService.getFlights(source,destination,returnDate,deptDate));
    }
























}
