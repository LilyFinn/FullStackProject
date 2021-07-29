package com.qa.sea.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.sea.data.Animal;
import com.qa.sea.repo.AnimalRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AnimalServiceDBUnitTest {

	@Autowired
	private AnimalServiceDB service;

	@MockBean
	private AnimalRepo repo;

	@Test
	void testUpdate() {
		int id = 1;

		Animal testAnimal = new Animal(id, "Sea Turtle", "Green", 50, 3900);
		Animal testNewAnimal = new Animal(id, "Pajama Cardinalfish", "Polkadot", 5, 82);

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(testAnimal));
		Mockito.when(this.repo.save(new Animal(id, "Pajama Cardinalfish", "Polkadot", 5, 82)))
				.thenReturn(testNewAnimal);

		Animal actual = this.service.replaceAnimal(id, testNewAnimal);
		assertThat(actual).isEqualTo(testNewAnimal);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(new Animal(id, "Pajama Cardinalfish", "Polkadot", 5, 82));

	}

	@Test
	void testCreate() {

		Animal newAnimal = new Animal("Sea Turtle", "Green", 50, 3900);

		Animal savedAnimal = new Animal(1, "Sea Turtle", "Green", 50, 3900);

		Mockito.when(this.repo.save(newAnimal)).thenReturn(savedAnimal);

		assertThat(this.service.createAnimal(newAnimal)).isEqualTo(savedAnimal);

		Mockito.verify(this.repo, Mockito.times(1)).save(newAnimal);
	}

	@Test
	void testDelete() {
		int id = 1;

		assertThat(this.service.deleteAnimal(id)).isEqualTo("Deleted: " + id);
	}

	@Test
	void testGetAll() {
		List<Animal> testAnimal = List.of(new Animal(1, "Sea Turtle", "Green", 50, 3900));

		Mockito.when(this.repo.findAll()).thenReturn(testAnimal);

		assertThat(this.service.getAllAnimals()).isEqualTo(testAnimal);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testGetAllBySpecies() {

		List<Animal> testAnimal = List.of(new Animal(1, "Sea Turtle", "Green", 50, 3900));

		String search = "Sea Turtle";
		Mockito.when(this.repo.findBySpeciesIgnoreCase(search)).thenReturn(testAnimal);

		assertThat(this.service.findBySpecies(search)).isEqualTo(testAnimal);

		Mockito.verify(this.repo, Mockito.times(1)).findBySpeciesIgnoreCase(search);
	}

	@Test
	void testgetById() {
		int id = 1;

		Animal newAnimal = new Animal("Sea Turtle", "Green", 50, 3900);

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(newAnimal));

		assertThat(this.service.getAnimal(id)).isEqualTo(newAnimal);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

}
