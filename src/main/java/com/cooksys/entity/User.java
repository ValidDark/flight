package com.cooksys.entity;

import java.util.Set;
import javax.persistence.*;

import com.cooksys.entity.Itinerary;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "User")
public class User {

	@Id
	@SequenceGenerator(name="user_id_seq", sequenceName="user_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="user_id_seq")
	@Column(updatable=false)
	private Integer id;	
	
	@Column(nullable=false)
	private String userName;

	@Column(nullable=false)
	public String password;
	
	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;
	
	@Column(nullable=false)
	private String email;
	
	@OneToMany(mappedBy="owner", fetch=FetchType.LAZY)
    private Set<Itinerary> itineraries;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Itinerary> getItineraries() {
		return itineraries;
	}

	public void setItineraries(Set<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}
	
}