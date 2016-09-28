package com.cooksys.entity;

import java.util.List;

import javax.persistence.*;

import com.cooksys.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Itineraries")
public class Itinerary {
	
	@Id
	@SequenceGenerator(name="user_id_seq", sequenceName="user_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="user_id_seq")
	@Column(updatable=false)
	private Integer id;	
	
	
	@OneToMany(mappedBy="itinerary", fetch=FetchType.LAZY, cascade=CascadeType.MERGE )
	private List<Flight> flights;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade=CascadeType.MERGE )
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
	
	
}
