package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.GenreMusic;

public interface GenreMusicRepository extends JpaRepository<GenreMusic, Integer>{

	public GenreMusic findByGenreName(String name);

}
