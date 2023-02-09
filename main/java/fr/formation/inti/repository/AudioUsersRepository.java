package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.AudioUser;

public interface AudioUsersRepository extends JpaRepository<AudioUser, Integer> {

}
