package com.cooksys.service;

import java.util.List;
import java.util.Set;

import com.cooksys.entity.Itinerary;

public interface ItineraryService {

	Set<Itinerary> findBetween(String city1, String city2);

	Itinerary saveItinerary(Itinerary itinerary);


}
