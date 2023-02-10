package fr.formation.inti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Users;
import fr.formation.inti.entity.UsersEvenement;

public interface UserEventRepository extends JpaRepository<UsersEvenement, Integer> {

	public List<UsersEvenement> findAllByUsers(Users user);

}
