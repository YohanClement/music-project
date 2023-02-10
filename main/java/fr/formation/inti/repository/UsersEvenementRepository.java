package fr.formation.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Evenement;
import fr.formation.inti.entity.UsersEvenement;

public interface UsersEvenementRepository extends JpaRepository<UsersEvenement, Integer> {

	public List<UsersEvenement> findAllByEvenement(Evenement event);

}
