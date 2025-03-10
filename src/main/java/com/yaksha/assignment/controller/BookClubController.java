package com.yaksha.assignment.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yaksha.assignment.entity.BookClub;
import com.yaksha.assignment.entity.User;
import com.yaksha.assignment.entity.UserBookClub;

@RestController
@RequestMapping("/api/bookClubs")
public class BookClubController {

	// In-memory collection to simulate a database for BookClubs
	private final Map<Long, BookClub> bookClubs = new HashMap<>();
	private final Map<Long, User> users = new HashMap<>();

	// Constructor to simulate data initialization
	public BookClubController() {
		// Simulate some BookClub instances
		BookClub bookClub1 = new BookClub();
		bookClub1.setId(1L);
		bookClub1.setName("Sci-Fi Book Club");

		BookClub bookClub2 = new BookClub();
		bookClub2.setId(2L);
		bookClub2.setName("Fantasy Book Club");

		bookClubs.put(1L, bookClub1);
		bookClubs.put(2L, bookClub2);

		// Simulate some User instances
		User user1 = new User();
		user1.setId(1L);
		user1.setName("John Doe");
		user1.setEmail("john.doe@example.com");

		UserBookClub userBookClub1 = new UserBookClub();
		userBookClub1.setId(1L);
		userBookClub1.setRole("Admin");
		userBookClub1.setStatus("Active");
		userBookClub1.setJoinDate(LocalDate.now());
		userBookClub1.setUser(user1);
		userBookClub1.setBookClub(bookClub1);

		Set<UserBookClub> userBookClubs = new HashSet<>();
		userBookClubs.add(userBookClub1);
		bookClub1.setUserBookClubs(userBookClubs);

		users.put(1L, user1);
	}

	// Get book club by ID
	@GetMapping("/{bookClubId}")
	public BookClub getBookClubById(@PathVariable Long bookClubId) {
		return bookClubs.get(bookClubId); // Return book club from in-memory collection
	}

	// Get all book clubs
	@GetMapping
	public List<BookClub> getAllBookClubs() {
		return new ArrayList<>(bookClubs.values());
	}

	// Get all users in a book club
	@GetMapping("/{bookClubId}/users")
	public Set<UserBookClub> getBookClubUsers(@PathVariable Long bookClubId) {
		return bookClubs.get(bookClubId).getUserBookClubs(); // Return all users associated with the book club
	}
}
