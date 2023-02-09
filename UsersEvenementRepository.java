package fr.formation.inti.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.UsersEvenement;

public interface UsersEvenementRepository extends JpaRepository<UsersEvenement, Integer> {

}
