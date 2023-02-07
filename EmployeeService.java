package fr.formation.inti.service;

import java.util.List;
import java.util.Optional;

import fr.formation.inti.model.Employees;

public interface EmployeeService {
	List<Employees> findAll();
	Employees save(Employees emp);
	void delete(Employees emp);
	Optional<Employees> findById(Integer id);
	void deleteById(Integer id);

}
