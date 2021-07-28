package com.qa.sea.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.sea.data.Animal;
@Repository
public interface AnimalRepo extends JpaRepository<Animal, Integer>{

	List<Animal> findBySpeciesIgnoreCase(String species);
}


