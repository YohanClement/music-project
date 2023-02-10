package fr.formation.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Integer>{
	
	List<Evenement> findAllByOrderByEvenementDatedebutAsc();

}
