package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cooksys.entity.Itinerary;

public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {

}