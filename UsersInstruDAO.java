package fr.formation.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Users;
import fr.formation.inti.entity.UsersInstruments;

public interface UsersInstruDAO extends JpaRepository<UsersInstruments, Integer>{
	
	public List<UsersInstruments> findByUsers(Users user);

}
