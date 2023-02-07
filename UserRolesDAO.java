package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.model.UserRoles;


public interface UserRolesDAO extends JpaRepository<UserRoles, Integer>{

}
