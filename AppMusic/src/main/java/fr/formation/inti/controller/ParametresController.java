package fr.formation.inti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.formation.inti.entity.Groupe;
import fr.formation.inti.entity.Users;
import fr.formation.inti.repository.GroupeRepository;
import fr.formation.inti.repository.UserRepository;

@Controller
public class ParametresController {

	@Autowired
	private GroupeRepository grouperep;

	@Autowired
	private UserRepository ur;

	@PutMapping("/updateprofile")
	public Users updateProfile(@PathVariable Integer id, @RequestBody Users users) {
		users.setUsersId(id);
		return ur.save(users);
	}

	@PutMapping("/updategroup")
	public Groupe updateGroup(@PathVariable Integer id, @RequestBody Groupe groupe) {
		groupe.setGroupeId(id);
		return grouperep.save(groupe);
	}
	
	@DeleteMapping("/deleteprofil")
	public void deleteProfile(@PathVariable Integer id) {
		ur.deleteById(id);
	}
	
	@DeleteMapping("/deletegroup")
	public void deleteGroup(@PathVariable Integer id) {
		grouperep.deleteById(id);
	}
	
	
}
