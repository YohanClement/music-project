package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.GroupeMembers;

public interface GmembersRepository extends JpaRepository<GroupeMembers, Integer> {

}
