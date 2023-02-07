package fr.formation.inti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.model.Employees;



public interface EmployeesDao extends JpaRepository<Employees, Integer> {
	
	List<Employees> findAll();
	void deleteById(Integer id);
	<S extends Employees> S save(Employees emp);
	Optional<Employees> findById(Integer ID);
	
	

}
