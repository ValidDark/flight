package com.cooksys.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cooksys.entity.Flight;
import com.cooksys.entity.Itinerary;
import com.cooksys.repository.ItineraryRepository;
import com.cooksys.service.FlightService;
import com.cooksys.service.ItineraryService;

@Service
public class ItineraryServiceImpl implements ItineraryService {
	

	@Autowired
	FlightService flightService;
	
	private final ItineraryRepository repo;

	
	
	@Autowired
	public ItineraryServiceImpl(ItineraryRepository repo) {
		super();
		this.repo = repo;
	}
	

	
//	public void recurse(Flight flight, String city1, String city2, List<Flight> checked, List<Flight> IflightList, Set<Itinerary> result) {
//
//		ArrayList<Flight> flightList = flightService.getDailyFlightList();
//		
//		IflightList.add(flight);
//		checked.add(flight);
//		
//		if((flight.getOrigin().equals(city1))&&(flight.getDestination().equals(city2)))
//		{
//			Itinerary totalFlight = new Itinerary();
//			totalFlight.setFlights(new ArrayList<Flight>(IflightList));
//			IflightList.remove(flight);
//			
//			int flightT = 0;
//			int delayT = 0;
//			List<Flight> flights = totalFlight.getFlights();
//			int fix = 0; //quick fix for delay time issue
//			int index = 0;
//			for (Flight flight2 : flights) {
//				flightT += flight2.getFlightTime();
//				
//				if(flight2 != flights.get(0))
//				{
//					fix++;
//					delayT += flight2.getOffset()-((flights.get(index).getOffset()+flights.get(index).getFlightTime())/fix);
//				}
//				index++;
//				
//				//TODO
//				//if statement always firing with mutable delayT causing issue,  example issue
//				//flight 0 total time = 4, flight 1 offset = 7,  subtracting 4 from 7 twice since 2 flights
//				//will fix later.
//			}
//			
//			totalFlight.setFlightTime(flightT);
//			totalFlight.setTotalDelay(delayT);
//			
//			result.add(totalFlight);
//			//base case
//		}
//		else
//		{
//			boolean another = false;
//			
//			for (Flight flight2 : flightList) {			
//				if((flight2.getOffset()>(flight.getOffset()+flight.getFlightTime())) && (!flight2.getOrigin().equals(city1)) && (flight2.getOrigin().equals(flight.getDestination())) && (flight2.getDestination()!=flight.getOrigin()))
//				{
//					another = true;
//				}
//			}
//			if(!another)
//			{
//				IflightList.remove(flight); //if the flight isn't the final flight, and there are no more connecting flights, remove self from list.
//			}
//			for (Flight flight2 : flightList) {			
//				if((!checked.contains(flight2))&&(flight2.getOffset()>(flight.getOffset()+flight.getFlightTime())) && (flight2.getOrigin().equals(flight.getDestination())) && (flight2.getDestination()!=flight.getOrigin()))
//				{
//					recurse(flight2, flight2.getOrigin(), city2, checked, IflightList, result);
//				}
//			}
//		}
//	}
	
//	@Override
//	public Set<Itinerary> findBetween(String city1, String city2) {
//		
//		
//		List<Flight> IflightList = new ArrayList<>();
//		List<Flight> checked = new ArrayList<>();
//		List<Flight> flightList = flightService.getDailyFlightList();
//		Set<Itinerary> result = new HashSet<>();
//		
//		
//		for (Flight flight : flightList) {
//			if(flight.getOrigin().equals(city1))
//			recurse(flight, city1, city2, checked, IflightList, result);
//		}
//		
//		return result;
//	}

//Gets a list of all possible itineraries up to 4 flights long, not focusing on endpoint.	
	public Set<Itinerary> allPaths(String city1, String city2){
		List<Flight> flights = new ArrayList<>();
		Itinerary totalFlight = new Itinerary();
		Set<Itinerary> result = new HashSet<>();
		List<Flight> flightList = flightService.getDailyFlightList();
		
		for (Flight flight : flightList) {
			if(flight.getOrigin().equals(city1)){
				flights.add(flight);
				totalFlight.setFlights(new ArrayList<Flight>(flights));
				result.add(new Itinerary(totalFlight));
				
				
				for (Flight flight2 : flightList) {
					
					if((flight2.getOffset()>(flight.getOffset()+flight.getFlightTime())) && (flight2.getOrigin().equals(flight.getDestination())))
						{
						flights.add(flight2);
						totalFlight.setFlights(new ArrayList<Flight>(flights));
						result.add(new Itinerary(totalFlight));
						
						for (Flight flight3 : flightList) {
							
							if((flight3.getOffset()>(flight.getOffset()+flight.getFlightTime()+flight2.getOffset()+flight2.getFlightTime())) && (flight3.getOrigin().equals(flight2.getDestination())))
								{
								flights.add(flight3);
								totalFlight.setFlights(new ArrayList<Flight>(flights));
								result.add(new Itinerary(totalFlight));
								
								for (Flight flight4 : flightList) {
									
									if((flight4.getOffset()>(flight.getOffset()+flight.getFlightTime()+flight2.getOffset()+flight2.getFlightTime()+flight3.getOffset()+flight3.getFlightTime())) && (flight4.getOrigin().equals(flight3.getDestination())))
										{
										flights.add(flight4);
										totalFlight.setFlights(new ArrayList<Flight>(flights));
										result.add(new Itinerary(totalFlight));
										flights.remove(flight4);
										}
								}
								flights.remove(flight3);
								}
						}
						flights.remove(flight2);
						}
				}
				flights.remove(flight);
			}
		}
		
		return result;
	}
	
	//reduce list of all itineraries to just valid ones
	public Set<Itinerary> reduce(Set<Itinerary> set,String city1, String city2){
		Set<Itinerary> result = new HashSet<>();
		for (Itinerary itin : set) {
			if((itin.getFlights().get(0).getOrigin().equals(city1)) && (itin.getFlights().get(itin.getFlights().size()-1).getDestination().equals(city2))){
				result.add(itin);
			}
		}
		return result;
	}

	public Set<Itinerary> popTimes(Set<Itinerary> set){

		for (Itinerary itin : set) {
			long flightTime = 0;
			long airTime = 0;
			
			flightTime = (itin.getFlights().get(itin.getFlights().size()-1).getOffset() + itin.getFlights().get(itin.getFlights().size()-1).getFlightTime()) - itin.getFlights().get(0).getOffset();
			itin.setFlightTime((int)flightTime);

			for (Flight flight : itin.getFlights()) {
				airTime += flight.getFlightTime();
			}
			itin.setTotalDelay((int)(flightTime-airTime));
		}
		return set;
	}

	@Override
	public Set<Itinerary> findBetween(String city1, String city2) {
		Set<Itinerary> result = new HashSet<>();

		
		Set<Itinerary> allPossible = allPaths(city1, city2);
		result = reduce(allPossible, city1, city2);
		
		result = popTimes(result);
		
		return result;
	}

	
	
	
	
	@Override
	public Itinerary saveItinerary(Itinerary itinerary) {
		return this.repo.save(itinerary);
	}


}
