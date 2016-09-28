package com.cooksys.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Flight;
import com.cooksys.entity.User;
import com.cooksys.service.FlightService;
import com.cooksys.service.LocationService;

@RestController
@RequestMapping("flights")
@CrossOrigin
public class FlightsController {
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	FlightService flightService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Flight> getFlightList()
	{
		return flightService.getDailyFlightList();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Flight create(@RequestBody Flight flight) {
		return flightService.saveFlight(flight);
	}
	
	public FlightsController() {
		super();
	}

	
	
}
