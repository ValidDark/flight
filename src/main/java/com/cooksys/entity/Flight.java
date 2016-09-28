package com.cooksys.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Flights")
public class Flight {
	
	@Id
	@GeneratedValue
	private Integer id;	
	
	
	
	public Flight() {

	}
	
	//Name of city where flight originates
	@Column(nullable=false)
	private String origin;
	
	//Name of city where flight lands
	@Column(nullable=false)
	private String destination;
	
	//How many hours flight is in the air
	@Column(nullable=false)
	private long flightTime;
	
	//How many hours after the start of the day until the flight takes off
	@Column(nullable=false, name="ofset")
	private long offset;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="itinerary_id")
	private Itinerary itinerary;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public long getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(long flightTime) {
		this.flightTime = flightTime;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public Itinerary getItinerary() {
		return itinerary;
	}
	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}
	public Flight(String origin, String destination, long flightTime, long offset) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightTime = flightTime;
		this.offset = offset;
	}
	
}
