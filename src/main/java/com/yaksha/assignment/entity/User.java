package com.yaksha.assignment.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;

	// One-to-many relationship with UserBookClub (join entity)
	@OneToMany(mappedBy = "user")
	@JsonManagedReference // Manages the serialization of the nested relationship (user -> userBookClubs)
	private Set<UserBookClub> userBookClubs = new HashSet<>();

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserBookClub> getUserBookClubs() {
		return userBookClubs;
	}

	public void setUserBookClubs(Set<UserBookClub> userBookClubs) {
		this.userBookClubs = userBookClubs;
	}
}
