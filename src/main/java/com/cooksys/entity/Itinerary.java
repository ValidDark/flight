package com.cooksys.entity;

import java.util.List;

import javax.persistence.*;

import com.cooksys.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Itineraries")
public class Itinerary {
	
	@Id
	@GeneratedValue
	private Integer id;	

	@OneToMany(mappedBy="itinerary", fetch=FetchType.EAGER, cascade=CascadeType.ALL )
	private List<Flight> flights;
	
	@ManyToOne(fetch = FetchType.LAZY )
	private User owner;

	@Column
	private Integer flightTime;
	
	@Column
	private Integer totalDelay;
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Integer getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(Integer flightTime) {
		this.flightTime = flightTime;
	}
	public Integer getTotalDelay() {
		return totalDelay;
	}
	public void setTotalDelay(Integer totalDelay) {
		this.totalDelay = totalDelay;
	}
	public Itinerary() {
		super();
		setTotalDelay(0);
		setFlightTime(0);
	}
	public Itinerary(Itinerary copy) {
		super();
		setFlights(copy.getFlights());
		setTotalDelay(0);
		setFlightTime(0);
	}
	
	
}
