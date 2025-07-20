package com.tripzy.Repository;

import com.tripzy.Entities.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary,Long> {
    List<Itinerary> findAllByUserId(Long userId);

    @Query("SELECT i FROM Itinerary i WHERE i.user.id = :userId AND LOWER(i.requestedCities) LIKE LOWER(CONCAT('%',:city,'%'))")
    List<Itinerary> findAllByUserIdAndRequestedCities(@Param("userId") Long userId, @Param("city") String city);
}