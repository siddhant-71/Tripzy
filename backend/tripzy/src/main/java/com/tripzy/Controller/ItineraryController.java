package com.tripzy.Controller;

import com.tripzy.DTOs.ItineraryRequestDto;
import com.tripzy.DTOs.ItineraryResponseDTO;
import com.tripzy.Entities.Itinerary;
import com.tripzy.Service.Interface.ItineraryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/itineraries")
@AllArgsConstructor
public class ItineraryController {
    private final ItineraryService itineraryService;


    @PostMapping("/create")
    public ResponseEntity<ItineraryResponseDTO> createItinerary(@RequestBody ItineraryRequestDto request){
        Itinerary itinerary=itineraryService.createItinerary(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToItineraryResponseDTO(itinerary));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ItineraryResponseDTO>> getItinerariesByUserId(@PathVariable("userId") Long userId){
        List<Itinerary>list=itineraryService.getItinerariesByUserId(userId);
        List<ItineraryResponseDTO>ans=new ArrayList<>();
        for(Itinerary iti:list)ans.add(mapToItineraryResponseDTO(iti));
        return ResponseEntity.ok(ans);
    }
    @GetMapping("/city/{userId}/{city}")
    public ResponseEntity<List<ItineraryResponseDTO>> getItinerariesByUserIdAndCity(@PathVariable("userId") Long userId,@PathVariable("city") String city){
        List<Itinerary>list=itineraryService.getByUserAndCity(userId,city);
        List<ItineraryResponseDTO>ans=new ArrayList<>();
        for(Itinerary iti:list)ans.add(mapToItineraryResponseDTO(iti));
        return ResponseEntity.ok(ans);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItinerary(@PathVariable("id") Long id){
        itineraryService.deleteItinerary(id);
        return ResponseEntity.ok("Itinerary Deleted Successfully");
    }
    private ItineraryResponseDTO mapToItineraryResponseDTO(Itinerary itinerary){
        ItineraryResponseDTO ans=new ItineraryResponseDTO();
        ans.setId(itinerary.getId());
        ans.setNumberOfDays(itinerary.getNumberOfDays());
        ans.setRequestedCities(itinerary.getRequestedCities());
        ans.setRequestedTime(itinerary.getRequestedTime());
        ans.setUserId(itinerary.getUser().getId());
        ans.setAiResponse(itinerary.getAiResponse());
        return ans;
    }
}
