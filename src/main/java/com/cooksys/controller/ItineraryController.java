package com.cooksys.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Flight;
import com.cooksys.entity.Itinerary;
import com.cooksys.service.ItineraryService;

@RestController
@RequestMapping("itinerary")
@CrossOrigin
public class ItineraryController {
	
	private final ItineraryService service;
	
	@Autowired
	public ItineraryController(ItineraryService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Itinerary create(@RequestBody Itinerary itinerary) {
		return this.service.saveItinerary(itinerary);
	}
	
	@RequestMapping(value = "/{city1}/{city2}", method = RequestMethod.GET)
	public Set<Itinerary> find(@PathVariable String city1, @PathVariable String city2) {
		return this.service.findBetween(city1,city2);
	}

}
