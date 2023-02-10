package fr.formation.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Groupe;
import fr.formation.inti.entity.GroupeMembers;

public interface GmembersRepository extends JpaRepository<GroupeMembers, Integer> {

	List<GroupeMembers> findAllByGroupe(Groupe gr);

}
