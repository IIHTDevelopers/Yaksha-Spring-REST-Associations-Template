package com.yaksha.assignment.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
@RequestMapping("/api/users")
public class UserController {

    // In-memory collection to simulate a database for Users
    private final Map<Long, User> users = new HashMap<>();
    private final Map<Long, BookClub> bookClubs = new HashMap<>();

    // Constructor to simulate data initialization
    public UserController() {
        // Simulate some BookClub instances
        BookClub bookClub1 = new BookClub();
        bookClub1.setId(1L);
        bookClub1.setName("Sci-Fi Book Club");
        bookClubs.put(1L, bookClub1);

        BookClub bookClub2 = new BookClub();
        bookClub2.setId(2L);
        bookClub2.setName("Fantasy Book Club");
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

        user1.setUserBookClubs(new HashSet<>(Collections.singletonList(userBookClub1)));
        users.put(1L, user1);
    }

    // Get user by ID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return users.get(userId); // Return user from in-memory collection
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    // Get all book clubs for a user
    @GetMapping("/{userId}/bookClubs")
    public Set<UserBookClub> getUserBookClubs(@PathVariable Long userId) {
        return users.get(userId).getUserBookClubs(); // Return user's book clubs
    }
}
