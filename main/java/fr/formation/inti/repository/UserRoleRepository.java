package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.UserRoles;

public interface UserRoleRepository extends JpaRepository<UserRoles, Integer>{

}
