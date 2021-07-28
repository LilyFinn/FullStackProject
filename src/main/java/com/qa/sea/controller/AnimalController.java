package com.qa.sea.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.sea.data.Animal;
import com.qa.sea.service.AnimalService;

@RestController
@CrossOrigin
public class AnimalController {

	private AnimalService service;

	public AnimalController(AnimalService service) {
		super();
		this.service = service;
	}

	@GetMapping("/")
	public String hello() {
		return "Hello, World!";
	}

	@PostMapping("/createAnimal")
	public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
		Animal created = this.service.createAnimal(animal);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/animal/all")
	public ResponseEntity<List<Animal>> getAllAnimals() {
		List<Animal> created = this.service.getAllAnimals();
		return new ResponseEntity<>(created, HttpStatus.OK);

	}

	@GetMapping("/getBySpecies/{species}")
	public ResponseEntity<List<Animal>> findBySpecies(@PathVariable String species) {
		return new ResponseEntity<>(this.service.findBySpecies(species), HttpStatus.ACCEPTED);

	}

	@GetMapping("/getAnimal/{id}")
	public Animal getAnimal(@PathVariable int id) {
		return this.service.getAnimal(id);
	}

	@PutMapping("/replaceAnimal/{id}")
	public Animal replaceAnimal(@PathVariable int id, @RequestBody Animal newAnimal) {
		return this.service.replaceAnimal(id, newAnimal);
	}

	@DeleteMapping("/deleteAnimal/{id}")
	public ResponseEntity<String> deleteAnimal(@PathVariable int id) {
		String body = service.deleteAnimal(id);
		return new ResponseEntity<String>(body, HttpStatus.NO_CONTENT);
	}

}
