package com.yaksha.assignment.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String author;

	// Many-to-many relationship with BookClub
	@ManyToMany(mappedBy = "books")
	@JsonBackReference // Prevents infinite recursion when serializing Book -> BookClubs
	private Set<BookClub> bookClubs = new HashSet<>();

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Set<BookClub> getBookClubs() {
		return bookClubs;
	}

	public void setBookClubs(Set<BookClub> bookClubs) {
		this.bookClubs = bookClubs;
	}
}
