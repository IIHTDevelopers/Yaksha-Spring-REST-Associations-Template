package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;

import java.time.LocalDate;
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

import com.yaksha.assignment.controller.BookClubController;
import com.yaksha.assignment.entity.BookClub;
import com.yaksha.assignment.entity.User;
import com.yaksha.assignment.entity.UserBookClub;
import com.yaksha.assignment.utils.JavaParserUtils;

@WebMvcTest(BookClubController.class)
@AutoConfigureMockMvc
public class BookClubControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private BookClub bookClub;
	private User user1;
	private UserBookClub userBookClub1;

	@BeforeEach
	public void setup() {
		// Setting up test data
		bookClub = new BookClub();
		bookClub.setId(1L);
		bookClub.setName("Sci-Fi Book Club");

		user1 = new User();
		user1.setId(1L);
		user1.setName("John Doe");
		user1.setEmail("john.doe@example.com");

		userBookClub1 = new UserBookClub();
		userBookClub1.setId(1L);
		userBookClub1.setRole("Admin");
		userBookClub1.setStatus("Active");
		userBookClub1.setJoinDate(LocalDate.now());
		userBookClub1.setUser(user1);
		userBookClub1.setBookClub(bookClub);

		bookClub.setUserBookClubs(new HashSet<>());
		bookClub.getUserBookClubs().add(userBookClub1);
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	// Test to check if the 'BookClubController' class has @RestController
	// annotation
	@Test
	public void testRestControllerAnnotation() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookClubController.java";
		boolean result1 = JavaParserUtils.checkClassAnnotation(filePath, "RestController");
		boolean result2 = JavaParserUtils.checkClassAnnotation(filePath, "RequestMapping");
		yakshaAssert(currentTest(), result1 && result2, businessTestFile);
	}

	// Test to check if 'getBookClubById' method has @GetMapping annotation
	@Test
	public void testGetBookClubByIdAnnotation() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookClubController.java";
		boolean result = JavaParserUtils.checkMethodAnnotation(filePath, "getBookClubById", "GetMapping");
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	// Test to check if 'getAllBookClubs' method has @GetMapping annotation
	@Test
	public void testGetAllBookClubsAnnotation() throws Exception {
		String filePath = "src/main/java/com/yaksha/assignment/controller/BookClubController.java";
		boolean result = JavaParserUtils.checkMethodAnnotation(filePath, "getAllBookClubs", "GetMapping");
		yakshaAssert(currentTest(), result, businessTestFile);
	}

	// Test to check GET request for BookClub by ID
	@Test
	public void testGetBookClubById() throws Exception {
		// Creating the request for fetching the BookClub with ID 1
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookClubs/1").contentType("application/json")
				.accept("application/json");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// Assert the result is not null
		yakshaAssert(currentTest(), result.getResponse().getContentAsString() != null ? "true" : "false",
				businessTestFile);
	}

	// Test to check GET request for all BookClubs
	@Test
	public void testGetAllBookClubs() throws Exception {
		// Creating the request for fetching all book clubs
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookClubs").contentType("application/json")
				.accept("application/json");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// Assert the result is not null
		yakshaAssert(currentTest(), result.getResponse().getContentAsString() != null ? "true" : "false",
				businessTestFile);
	}

	// Test to check GET request for users in a BookClub
	@Test
	public void testGetBookClubUsers() throws Exception {
		// Creating the request for fetching users in the BookClub with ID 1
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookClubs/1/users")
				.contentType("application/json").accept("application/json");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// Assert the result is not null
		yakshaAssert(currentTest(), result.getResponse().getContentAsString() != null ? "true" : "false",
				businessTestFile);
	}
}
