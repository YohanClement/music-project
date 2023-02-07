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

import fr.formation.inti.config.FileUploadUtil;
import fr.formation.inti.model.UserRoles;
import fr.formation.inti.model.Users;
import fr.formation.inti.model.UsersInstru;
import fr.formation.inti.repository.InstrumentsDAO;
import fr.formation.inti.repository.UserRolesDAO;
import fr.formation.inti.repository.UsersDAO;
import fr.formation.inti.repository.UsersInstruDAO;

@Controller
public class AppController {

	@Autowired
	private UsersDAO userRepo;
	@Autowired
	private UserRolesDAO rolesRepo;
	@Autowired
	private InstrumentsDAO instrRepo;
	@Autowired
	private UsersInstruDAO uInstrRepo;
	
	private boolean isAuthenticated() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || AnonymousAuthenticationToken.class.
	      isAssignableFrom(authentication.getClass())) {
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
	
	@GetMapping("/connexion")
	public String showlogForm(Model model) {
	    model.addAttribute("user", new Users());
	     
	    return "Connexion";
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
	
	@PostMapping("/process_register")
	public String processRegister(Users user,@RequestParam("instruments") List<String> instruments, @RequestParam("niveau") Integer niveau, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		System.out.println(user);
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(formatter.format(date));
		user.setDatecrea(now);
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        user.setPhotos(fileName);
        
	    Users savedUser =userRepo.save(user);
	    UserRoles userrole =new UserRoles(user, "USER", user.getEmailaddress());
	    rolesRepo.save(userrole);
	    
	    
	    UsersInstru uInst = new UsersInstru(savedUser, instrRepo.findByinstrname(instruments.get(0)), niveau);
	    uInstrRepo.save(uInst);
	    
	    String uploadDir = "user-photos/" + savedUser.getUsersid();
	    
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	    return "register_success";
	}
	
	@GetMapping("/logoutsuccessful")
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		System.out.println("marqueur déconnexion");
		return "logoutSuccessful";
	}
	

	@GetMapping(value = "/403")
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			model.addAttribute("message",
					"Hi " + principal.getName() + "<br> You do not have permission to access this page!");
		} else {
			model.addAttribute("msg", "You do not have permission to access this page!");
		}
		return "403Page";
	}
}
