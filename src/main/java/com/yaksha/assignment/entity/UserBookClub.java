package com.yaksha.assignment.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserBookClub {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String role; // Role in the book club (e.g., Admin, Member, Moderator)
	private String status; // Status in the club (e.g., Active, Inactive, Suspended)
	private LocalDate joinDate; // Date the user joined the book club

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference // Prevents infinite recursion when serializing UserBookClub -> User ->
						// UserBookClub
	private User user;

	@ManyToOne
	@JoinColumn(name = "book_club_id")
	@JsonBackReference // Prevents infinite recursion when serializing UserBookClub -> BookClub ->
						// UserBookClub
	private BookClub bookClub;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BookClub getBookClub() {
		return bookClub;
	}

	public void setBookClub(BookClub bookClub) {
		this.bookClub = bookClub;
	}
}
