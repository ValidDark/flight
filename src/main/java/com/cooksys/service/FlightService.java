package com.cooksys.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.component.FlightGenerator;
import com.cooksys.entity.Flight;
import com.cooksys.repository.FlightRepository;

@Service
public class FlightService {

	@Autowired
	FlightRepository repo;
	
	@Autowired
	FlightGenerator generator;

	private ArrayList<Flight> flightList = new ArrayList<>();
	
	public ArrayList<Flight> getDailyFlightList()
	{
		return flightList;
	}
	
	//The fixedDelay parameter determines how often a new day is generated as expressed in milliseconds
	//currently set to 5 seconds, can decrease for testing.
	@Scheduled(fixedDelay=30000)
	private void refreshFlights()
	{
		flightList = generator.generateNewFlightList();

		
		for (Flight flight : flightList) {
			repo.save(flight);
		}
		
	}
	
}
