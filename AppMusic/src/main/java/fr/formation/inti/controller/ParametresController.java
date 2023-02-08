package fr.formation.inti.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

//	@GetMapping("/parametres")
//	public String parametres(HttpSession session, Model model) {
//		Users Myuser = (Users) session.getAttribute("UsersSession");
//		model.addAttribute("MyId", Myuser.getUsersId());
//		System.out.println("MyUser :" + Myuser);
//		System.out.println("MyId :" + Myuser.getUsersId());
//		return "parametres";
//	}
//
//	@GetMapping("/update")
//	public String update(Model model, @RequestParam(value = "MyId", required = true) Integer id) {
//		//Integer id1 =Integer.parseInt(id);
//		
//		Users user = ur.findById(id).get();
//
//		System.out.println(user);
//		System.out.println(id);
//		model.addAttribute("user", user);
//		System.out.println(ur.findById(id).get());
//		return "update";
//	}
	
//	@GetMapping("/update")
//	public String update() {
//		return "update";
//	}

//	@GetMapping("/update")
//	public String update123(@RequestParam("id") Integer id, Model model) {
//	Users user = ur.findById(id).get();
//		System.out.println("User à modifier : "+user);
//		System.out.println("Son id : "+id);
//		model.addAttribute("user", user);
//		return "update";
//	}

	@PostMapping("/update")
	public String updateProfile(@ModelAttribute("user")@RequestBody Users users, Model model, @RequestParam Integer id) {
		System.out.println(id);
		users.setUsersId(id);
		
			Users user = ur.findById(id).get();
			
			user.setUsersFirstName(user.getUsersFirstName());
			user.setUsersLastName(user.getUsersLastName());
			user.setUsersEmail(user.getUsersEmail());
			user.setUsersCity(user.getUsersCity());
			user.setUsersBio(user.getUsersBio());
			user.setUsersZip(user.getUsersZip());
		
			ur.save(user);
			return "parametres";
		}

	@PostMapping("/updategroup")
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
