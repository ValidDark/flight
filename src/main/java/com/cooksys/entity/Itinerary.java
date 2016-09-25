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
	
	@JsonIgnore
	@ManyToMany(mappedBy="itinerary", fetch=FetchType.LAZY)
	private List<Flight> flights;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private User owner;

	
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
}
