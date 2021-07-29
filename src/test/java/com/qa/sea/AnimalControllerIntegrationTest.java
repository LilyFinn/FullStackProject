package com.qa.sea;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.sea.data.Animal;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

@Sql(scripts = { "classpath:animal-schema.sql",
		"classpath:animal-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

@ActiveProfiles("test")
public class AnimalControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Animal testAn = new Animal(3, "SeaTurtle", "Green", 50, 2900);

		String testAnAsJSON = this.mapper.writeValueAsString(testAn);

		System.out.println(testAn);
		System.out.println(testAnAsJSON);

		RequestBuilder request = post("/createAnimal").contentType(MediaType.APPLICATION_JSON).content(testAnAsJSON);

		ResultMatcher checkStatus = status().is(201);

		Animal testCreatedAn = new Animal(3, "SeaTurtle", "Green", 50, 2900);
		testCreatedAn.setId(3);
		String testCreatedKitAsJSON = this.mapper.writeValueAsString(testCreatedAn);

		ResultMatcher checkBody = content().json(testCreatedKitAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test

	void testDelete() throws Exception {
		RequestBuilder request = delete("/deleteAnimal/1");

		ResultMatcher checkStatus = status().is(204);
		ResultMatcher checkBody = content().string("Deleted: 1");

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test

	void testGetAll() throws Exception {

		List<Animal> allAn = new ArrayList<>();
		Animal testAn = new Animal(1, "Dolphin", "blue", 60, 2800);
		Animal testAn2 = new Animal(2, "Killer whale", "Black&White", 60, 850);

		allAn.add(testAn);
		allAn.add(testAn2);

		String listAsJSON = this.mapper.writeValueAsString(allAn);
		RequestBuilder request = get("/animal/all").contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().is(200);
		ResultMatcher checkBody = content().string(listAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testGetAnimal() throws Exception {

		Animal testAn = new Animal(1, "Dolphin", "blue", 60, 2800);
		String testAnAsJSON = this.mapper.writeValueAsString(testAn);

		RequestBuilder request = get("/getAnimal/1").contentType(MediaType.APPLICATION_JSON).content(testAnAsJSON);

		ResultMatcher checkStatus = status().is(200);
		ResultMatcher checkBody = content().string(testAnAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetBySpecies() throws Exception {

		List<Animal> findAn = new ArrayList<>();
		Animal testGetBySpecies = new Animal(1, "Dolphin", "blue", 60, 2800);
		findAn.add(testGetBySpecies);

		String testCreatedAsJSON = this.mapper.writeValueAsString(findAn);

		RequestBuilder request = get("/getBySpecies/Dolphin").contentType(MediaType.APPLICATION_JSON)
				.content(testCreatedAsJSON);

		ResultMatcher checkStatus = status().is(202);
		ResultMatcher checkBody = content().string(testCreatedAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testUpdate() throws Exception {
		int id = 1;
		Animal newAnimal = new Animal(id, "Oyster", "black", 60, 2800);
		String newAnimalAsJSON = this.mapper.writeValueAsString(newAnimal);

		RequestBuilder req = put("/replaceAnimal/" + id).contentType(MediaType.APPLICATION_JSON)
				.content(newAnimalAsJSON);

		ResultMatcher checkStatus = status().is(200);

		ResultMatcher checkBody = content().json(newAnimalAsJSON);

		this.mockMVC.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

}
