package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Evenement;

public interface EventRepository extends JpaRepository<Evenement, Integer> {

}
