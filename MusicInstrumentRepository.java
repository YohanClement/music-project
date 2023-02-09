package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.inti.entity.Musicinstruments;

@Repository
public interface MusicInstrumentRepository extends JpaRepository<Musicinstruments, Integer>{

	public Musicinstruments findByinstrName(String name);
}
