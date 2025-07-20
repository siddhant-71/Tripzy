package com.tripzy.Service.Interface;

import com.tripzy.DTOs.ItineraryRequestDto;
import com.tripzy.Entities.Itinerary;

import java.util.List;

public interface ItineraryService {
    Itinerary createItinerary(ItineraryRequestDto request);
    List<Itinerary> getItinerariesByUserId(Long userId);
    Itinerary getItineraryById(Long id);
    void deleteItinerary(Long id);
    public List<Itinerary> getByUserAndCity(Long userId,String city);
}
