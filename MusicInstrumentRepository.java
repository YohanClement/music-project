package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Musicinstruments;

public interface MusicInstrumentRepository extends JpaRepository<Musicinstruments, Integer>{

	public Musicinstruments findByinstrName(String name);
}
