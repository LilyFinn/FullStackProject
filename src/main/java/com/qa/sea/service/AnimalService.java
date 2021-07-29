package com.qa.sea.service;

import java.util.List;

import com.qa.sea.data.Animal;

public interface AnimalService {
	public Animal createAnimal(Animal animal);

	public List<Animal> getAllAnimals();

	public Animal getAnimal(int id);

	public Animal replaceAnimal(int id, Animal newAnimal);

	public String deleteAnimal(int id);

	public List<Animal> findBySpecies(String species);

}


