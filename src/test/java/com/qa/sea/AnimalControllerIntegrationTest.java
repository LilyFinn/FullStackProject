package com.qa.sea;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

@Sql(scripts = { "classpath:animal-schema.sql",
		"classpath:animal-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class AnimalControllerIntegrationTest {
	
	

}
