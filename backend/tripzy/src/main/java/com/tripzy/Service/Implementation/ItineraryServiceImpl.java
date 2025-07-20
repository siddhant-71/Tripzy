package com.tripzy.Service.Implementation;

import com.tripzy.DTOs.ItineraryRequestDto;
import com.tripzy.Entities.Itinerary;
import com.tripzy.Entities.User;
import com.tripzy.Repository.ItineraryRepository;
import com.tripzy.Repository.UserRepository;
import com.tripzy.Service.Interface.ItineraryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {
    private final UserRepository userRepository;
    private final ItineraryRepository itineraryRepository;

    @Override
    public Itinerary createItinerary(ItineraryRequestDto request) {
        User user=userRepository.findById(request.getUserId()).orElseThrow(()->new RuntimeException("User not found"));
        Itinerary itinerary=new Itinerary();
        itinerary.setUser(user);
        itinerary.setRequestedCities(request.getRequestedCities());
        itinerary.setNumberOfDays(request.getNumberOfDays());
        itinerary.setAiResponse("AI RESPONSE");
        itineraryRepository.save(itinerary);
        return itinerary;
    }

    @Override
    public List<Itinerary> getByUserAndCity(Long userId,String city){
        return itineraryRepository.findAllByUserIdAndRequestedCities(userId,city);
    }
    @Override
    public List<Itinerary> getItinerariesByUserId(Long userId) {
        return itineraryRepository.findAllByUserId(userId);
    }

    @Override
    public Itinerary getItineraryById(Long id) {
        return itineraryRepository.findById(id).orElseThrow(()->new RuntimeException("Itinerary not found"));
    }

    @Override
    public void deleteItinerary(Long id) {
        Itinerary itinerary=itineraryRepository.findById(id).orElseThrow(()->new RuntimeException("Itinerary not found"));
        itineraryRepository.delete(itinerary);
    }
}
