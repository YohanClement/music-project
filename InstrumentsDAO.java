package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.model.musicinstruments;

public interface InstrumentsDAO extends JpaRepository<musicinstruments, Integer>{

	
	public musicinstruments findByinstrname(String name);
}
