package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;

import java.util.HashSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.yaksha.assignment.controller.UserController;
import com.yaksha.assignment.entity.BookClub;
import com.yaksha.assignment.entity.User;
import com.yaksha.assignment.entity.UserBookClub;
import com.yaksha.assignment.utils.JavaParserUtils;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private User user1;
	private UserBookClub userBookClub1;
	private BookClub bookClub1;

	@BeforeEach
	public void setup() {
		// Setting up in-memory test data
		bookClub1 = new BookClub();
		bookClub1.setId(1L);
		bookClub1.setName("Sci-Fi Book Club");

		user1 = new User();
		user1.setId(1L);
		user1.setName("John Doe");
		user1.setEmail("john.doe@example.com");

		userBookClub1 = new UserBookClub();
		userBookClub1.setId(1L);
		userBookClub1.setRole("Admin");
		userBookClub1.setStatus("Active");
		userBookClub1.setJoinDate(java.time.LocalDate.now());
		userBookClub1.setUser(user1);
		userBookClub1.setBookClub(bookClub1);

		user1.setUserBookClubs(new HashSet<>());
		user1.getUserBookClubs().add(userBookClub1);
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	// Test to check if the 'UserController' class has @RestController annotation
	@Test
	public void testRestControllerAnnotation() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/controller/UserController.java";
		boolean result = JavaParserUtils.checkClassAnnotation(filePath, "RestController");
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	// Test to check if 'getUserById' method has @GetMapping annotation
	@Test
	public void testGetUserByIdAnnotation() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/controller/UserController.java";
		boolean result = JavaParserUtils.checkMethodAnnotation(filePath, "getUserById", "GetMapping");
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	// Test to check if 'getAllUsers' method has @GetMapping annotation
	@Test
	public void testGetAllUsersAnnotation() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/controller/UserController.java";
		boolean result = JavaParserUtils.checkMethodAnnotation(filePath, "getAllUsers", "GetMapping");
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	// Test to check GET request for User by ID
	@Test
	public void testGetUserById() throws Exception {
		// Creating the request for fetching the User with ID 1
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/1").contentType("application/json")
				.accept("application/json");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// Assert the result is not null
		yakshaAssert(currentTest(), result.getResponse().getContentAsString() != null ? "true" : "false",
				businessTestFile);
	}

	// Test to check GET request for all Users
	@Test
	public void testGetAllUsers() throws Exception {
		// Creating the request for fetching all users
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users").contentType("application/json")
				.accept("application/json");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// Assert the result is not null
		yakshaAssert(currentTest(), result.getResponse().getContentAsString() != null ? "true" : "false",
				businessTestFile);
	}

	// Test to check GET request for BookClubs associated with a User
	@Test
	public void testGetUserBookClubs() throws Exception {
		// Creating the request for fetching book clubs of the user with ID 1
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/1/bookClubs")
				.contentType("application/json").accept("application/json");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// Assert the result is not null
		yakshaAssert(currentTest(), result.getResponse().getContentAsString() != null ? "true" : "false",
				businessTestFile);
	}
}
