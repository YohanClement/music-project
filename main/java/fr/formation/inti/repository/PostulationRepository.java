package fr.formation.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Groupe;
import fr.formation.inti.entity.Postulations;

public interface PostulationRepository extends JpaRepository<Postulations, Integer> {

	public List<Postulations> findAllByGroupe(Groupe gr);

}
