package com.qa.sea.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String species;
	private String colouring;

	private int lifeSpan;
	private int depthFound;

	public Animal(int id, String species, String colouring, int lifeSpan, int depthFound) {
		super();
		this.id = id;
		this.species = species;
		this.colouring = colouring;
		this.lifeSpan = lifeSpan;
		this.depthFound = depthFound;

	}

	public Animal(String species, String colouring, int lifeSpan, int depthFound) {
	super();
	
	this.species = species;
	this.colouring = colouring;
	this.lifeSpan = lifeSpan;
	this.depthFound = depthFound;

}
	
	public Animal() {
	}

	@Override 
	
	public int hashCode() {
		final int prime = 31; 
		int result = 1; 
		result = prime * result + lifeSpan;
		result = prime * result + ((colouring == null) ? 0 : colouring.hashCode());
		result = prime * result + depthFound; 
		result = prime * result + id; 
		result = prime * result + ((species == null) ? 0 : species.hashCode()); 
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
	Animal other = (Animal) obj;
		if (lifeSpan != other.lifeSpan)
			return false;
		if (colouring == null) {
			if (other.colouring != null)
				return false;
		} else if (!colouring.equals(other.colouring))
			return false;
		if (depthFound != other.depthFound)
			return false;
		if (id != other.id)
			return false;
		if (species == null) {
			if (other.species != null)
				return false;
		} else if (!species.equals(other.species))
			return false;
		return true;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getColouring() {
		return colouring;
	}

	public void setColouring(String colouring) {
		this.colouring = colouring;
	}

	public int getlifeSpan() {
		return lifeSpan;
	}

	public void setlifeSpan(int lifeSpan) {
		this.lifeSpan = lifeSpan;
	}

	public int getDepthFound() {
		return depthFound;
	}

	public void setDepthfound(int depthFound) {
		this.depthFound = depthFound;
	}
	
	@Override

	public String toString() {
		return "Animal [species=" + species + ", colouring=" + colouring + ", lifespan=" + lifeSpan + ", depthFound=" + depthFound + "]";
	}

}


