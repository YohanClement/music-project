package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

   Users findByusersId(Integer usersId)	;
  
   
}
