package fr.formation.inti.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.inti.entity.UserRoles;
import fr.formation.inti.entity.Users;
import fr.formation.inti.entity.UsersInstruments;
import fr.formation.inti.repository.MusicInstrumentRepository;
import fr.formation.inti.repository.UserRepository;
import fr.formation.inti.repository.UserRoleRepository;
import fr.formation.inti.repository.UsersInstruDAO;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MusicInstrumentRepository instrRepo;
	
	@Autowired
	private UserRoleRepository rolesRepo;
	
	@Autowired
	private UsersInstruDAO uInstrRepo;

	private boolean isAuthenticated() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || AnonymousAuthenticationToken.class.
	      isAssignableFrom(authentication.getClass())) {
	    	System.out.println("authentication : "+authentication);
	    	System.out.println("autre truc long : "+AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass()));
	        return false;
	    }
	    return authentication.isAuthenticated();
	}
	
	@GetMapping("/login")
	public String viewLoginPage(Model  model) {
		System.out.println("marqueur : " + isAuthenticated());
		 if (isAuthenticated()) {
		        return "redirect:listusers";
		    }
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new Users());
	    if (isAuthenticated()) {
	        return "redirect:listusers";
	    }
	    return "creationprofil";
	}
	
	@GetMapping("/listusers")
	public String listUsers(Model model) {
	    List<Users> listUsers = userRepo.findAll();
	    model.addAttribute("listUsers", listUsers);
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    System.out.println(auth.getName());
	    Users user = userRepo.findByEmail(auth.getName());
	    model.addAttribute("user", user);
	    return "listusers";
	}
	
	@GetMapping("/logoutsuccessful")
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		System.out.println("marqueur déconnexion");
		return "logoutSuccessful";
	}
	
//	public static void main(String[] args) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		
//		String password="Abc123!";
//		String encodePw = encoder.encode(password);
//		System.out.println(encodePw);
//		System.out.println(encodePw);
//	}

}
