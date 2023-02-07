package fr.formation.inti.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.inti.model.Employees;
import fr.formation.inti.repository.EmployeesDao;

@Service
@Transactional
public class EmpServiceImp implements EmployeeService {
	
	@Autowired
	EmployeesDao dao;

	@Override
	public List<Employees> findAll() {
		return dao.findAll();
	}

	@Override
	public Employees save(Employees emp) {
		 dao.saveAndFlush(emp);
		 return emp;
	}

	@Override
	public void delete(Employees emp) {
		dao.delete(emp);
		
	}

	@Override
	public Optional<Employees> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

}
