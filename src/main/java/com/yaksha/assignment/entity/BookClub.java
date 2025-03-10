package com.yaksha.assignment.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class BookClub {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	// One-to-many relationship with UserBookClub (join entity)
	@OneToMany(mappedBy = "bookClub")
	@JsonManagedReference // Manages the serialization of the nested relationship (bookClub ->
							// userBookClubs)
	private Set<UserBookClub> userBookClubs = new HashSet<>();

	// Many-to-many relationship with Book
	@ManyToMany
	@JoinTable(name = "book_club_book", joinColumns = @JoinColumn(name = "book_club_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	@JsonManagedReference // Manages the serialization of the nested relationship (bookClub -> books)
	private Set<Book> books = new HashSet<>();

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

	public Set<UserBookClub> getUserBookClubs() {
		return userBookClubs;
	}

	public void setUserBookClubs(Set<UserBookClub> userBookClubs) {
		this.userBookClubs = userBookClubs;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
