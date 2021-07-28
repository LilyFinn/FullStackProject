package com.qa.sea.service;

import java.util.ArrayList;
import java.util.List;

import com.qa.sea.data.Animal;

public class AnimalServiceList implements AnimalService {

	private List<Animal> animals = new ArrayList<>();

	public Animal createAnimal(Animal animal) {
		System.out.println(animal);
		this.animals.add(animal);
		return this.animals.get(this.animals.size() - 1);
	}

	public List<Animal> getAllAnimals() {
		return animals;

	}

	public List<Animal> findByName(String name) {
		return null;
	}

	public Animal getAnimal(int id) {
		Animal found = this.animals.get(id);
		return found;
	}

	public Animal replaceAnimal(int id, Animal newAnimal) {
		return this.animals.set(id, newAnimal);
	}

	public String deleteAnimal(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Animal> findBySpecies(String species) {
		// TODO Auto-generated method stub
		return null;
	}

}
