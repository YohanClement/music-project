package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.UsersGenre;

public interface UserGenreRepository extends JpaRepository<UsersGenre, Integer> {

}
