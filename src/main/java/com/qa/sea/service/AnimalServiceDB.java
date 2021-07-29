package com.qa.sea.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.qa.sea.data.Animal;
import com.qa.sea.repo.AnimalRepo;

@Service
public class AnimalServiceDB implements AnimalService{

	private AnimalRepo repo;

	public AnimalServiceDB(AnimalRepo repo) {
		super();
		this.repo = repo;
	}

	@Override

	public List<Animal> findBySpecies(String species) {
		return this.repo.findBySpeciesIgnoreCase(species);
	}

	@Override
	public Animal createAnimal(Animal animal) {
		return this.repo.save(animal);

	}

	@Override
	@Transactional
	public Animal getAnimal(int id) {
		Animal found = this.repo.findById(id).get();
		return found;
	}

	@Override
	public List<Animal> getAllAnimals() {
		return this.repo.findAll();
	}

	@Override
	public Animal replaceAnimal(int id, Animal newAnimals) {
		Animal found = this.repo.findById(id).get();
		
		System.out.println("FOUND: " + found);
		
		found.setlifeSpan(newAnimals.getlifeSpan());
		found.setSpecies(newAnimals.getSpecies());
		found.setColouring(newAnimals.getColouring());
		found.setDepthfound(newAnimals.getDepthFound());

		System.out.println("FOUND AFTER UPDATE: " + found);
		Animal updated = this.repo.save(found);
		System.out.println("UPDATE: " + updated);
		return updated;
	}

	@Override
	public String deleteAnimal(int id) {
		this.repo.deleteById(id);
		
		if(this.repo.existsById(id)) {
			return "Not deleted: " +id;
		} else {
			return "Deleted: " + id; 
		}
	}


}

	


