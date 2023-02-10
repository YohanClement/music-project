package fr.formation.inti.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.inti.entity.Evenement;
import fr.formation.inti.entity.GenreMusic;
import fr.formation.inti.entity.Users;
import fr.formation.inti.entity.UsersEvenement;
import fr.formation.inti.repository.EvenementRepository;
import fr.formation.inti.repository.GenreMusicRepository;
import fr.formation.inti.repository.MusicInstrumentRepository;
import fr.formation.inti.repository.UserEventRepository;
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

	@Autowired
	private EvenementRepository eventRepo;

	@Autowired
	private GenreMusicRepository genreMusRepo;

	@Autowired
	private UserEventRepository uEventRepo;

	private boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
			System.out.println("authentication : " + authentication);
			System.out.println("autre truc long : "
					+ AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass()));
			return false;
		}
		return authentication.isAuthenticated();
	}

	@GetMapping("/login")
	public String viewLoginPage(Model model) {
		System.out.println("marqueur : " + isAuthenticated());
		if (isAuthenticated()) {
			return "redirect:/accueillogged";
		}
		return "redirect:/index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		List<GenreMusic> list = genreMusRepo.findAll();
		model.addAttribute("genre", list);
		model.addAttribute("user", new Users());
		if (isAuthenticated()) {
			return "redirect:/accueillogged";
		}
		return "creationprofil";
	}

	@GetMapping("/*")
	public String anyrequest(Model model) {
		model.addAttribute("user", new Users());
		if (isAuthenticated()) {
			return "redirect:/accueillogged";
		}
		List<Evenement> listEvent = new ArrayList<Evenement>();
		listEvent = eventRepo.findAllByOrderByEvenementDatedebutAsc();
		System.out.println(listEvent);
		model.addAttribute("events", listEvent);

		return "redirect:/index";
	}

//	@GetMapping("/")
//	public void users(Principal pr, Model m) {
//		Users u = userRepo.findByEmail(pr.getName());
//		m.addAttribute("Moi", u.getUsersId());
//		
//	}

//	@GetMapping("/listusers")
//	public String listUsers(Model model) {
//		List<Users> listUsers = userRepo.findAll();
//		model.addAttribute("listUsers", listUsers);
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(auth.getName());
//		Users user = userRepo.findByEmail(auth.getName());
//		model.addAttribute("user", user);
//		return "listusers";
//	}

//	@GetMapping("/listusers")
//	public String listUsers(Model model, HttpSession session, Principal principal) {
//		session.setAttribute("UsersSession", userRepo.findByEmail(principal.getName()));
//		System.out.println(userRepo.findByEmail(principal.getName()));
//	    List<Users> listUsers = userRepo.findAll();
//	    model.addAttribute("listUsers", listUsers);
//	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	    System.out.println(auth.getName());
//	    Users user = userRepo.findByEmail(auth.getName());
//	    model.addAttribute("user", user);
//	    return "listusers";
//	}

	@GetMapping("/chat")
	public String chat() {
		return "chat";
	}

	@GetMapping("/index")
	public String index(Model model) {
		List<Evenement> listEvent = new ArrayList<Evenement>();
		listEvent = eventRepo.findAllByOrderByEvenementDatedebutAsc();
		System.out.println(listEvent);

		ChronoLocalDateTime now = LocalDateTime.from(ZonedDateTime.now());
		List<Evenement> filteredEvents = new ArrayList<>();
		System.out.println(now);
		for (Evenement event : listEvent) {
			if (event.getEvenementDatedebut().isAfter(now) || event.getEvenementDatedebut().isEqual(now)) {
				filteredEvents.add(event);
			}
		}
		model.addAttribute("events", filteredEvents);
		return "index";
	}

	@GetMapping("/accueillogged")
	public String accueil(Model model, Principal principal) {

		//Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getName();
		Users activeuser = userRepo.findByEmail(username);

		List<Evenement> listEvent = new ArrayList<Evenement>();
		listEvent = eventRepo.findAllByOrderByEvenementDatedebutAsc();
		System.out.println(listEvent);
		ChronoLocalDateTime now = LocalDateTime.from(ZonedDateTime.now());
		List<Evenement> filteredEvents = new ArrayList<>();
		System.out.println(now);
		for (Evenement event : listEvent) {
			if (event.getEvenementDatedebut().isAfter(now) || event.getEvenementDatedebut().isEqual(now)) {
				filteredEvents.add(event);
			}
		}
		model.addAttribute("events", filteredEvents);

		// Récupérer la liste des évènements
		Set<UsersEvenement> userEvenement = activeuser.getUsersEvenements();
		List<Evenement> list_evenements = new ArrayList<Evenement>();
		System.out.println(list_evenements);
		for (UsersEvenement e : userEvenement) {
			Integer id_event = e.getEvenement().getEvenementId();
			System.out.print("id evenement : " + id_event);
			Evenement event = eventRepo.findById(id_event).get();
			if (event.getEvenementDatedebut().isAfter(now) || event.getEvenementDatedebut().isEqual(now)) {
				System.out.println("nom evenement " + event.getEvenementName() + " id : " + event.getEvenementId());
				list_evenements.add(event);
			}
		}

		model.addAttribute("evenements", list_evenements);
		System.out.println(list_evenements);
		

		return "accueillogged";
	}

//	@PostMapping("/process_register")
//	public String processRegister(Users user, @RequestParam("instruments") String instrument,
//			@RequestParam("niveau") Integer niveau, @RequestParam("image") MultipartFile multipartFile,
//			@RequestParam("files") MultipartFile files, @RequestParam("audios") MultipartFile multipartFile1) throws IOException {
//
////		System.out.println(user);
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//		LocalDateTime now = LocalDateTime.now();
//
////      System.out.println(formatter.format(date));
//		user.setUsersDateCrea(now);
//		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//		user.setPhotos(fileName);
//
//		String NameFile = StringUtils.cleanPath(files.getOriginalFilename());
//		user.setAudio(NameFile);
//		
//		//Sauvegarde de l'audio maquette
//      	String fileName1 = StringUtils.cleanPath(multipartFile1.getOriginalFilename());	
//        user.setAudio(fileName1);
//			
//		
//		// Settings roles
//		Users savedUser = userRepo.save(user);
//		UserRoles userrole = new UserRoles(user, "USER", user.getUsersEmail());
//		rolesRepo.save(userrole);
//
//		// Saving Instruments
//		UsersInstruments uInst = new UsersInstruments(savedUser, instrRepo.findByinstrName(instrument), niveau);
//		UsersInstruments uInstsaved = uInstrRepo.save(uInst);
//		System.out.println(uInstsaved.getUserInstrumentId());
//
//		// Saving THez profile picture
//		String uploadDir = "user-photos/" + savedUser.getUsersId();
//
//		//Sauvegarde l'audio maquette dans un dossier
//        String uploadDir1 = "user-audio/" + savedUser.getUsersId();
//        FileUploadUtil.saveFile(uploadDir1, fileName1, multipartFile1);
//		
//		return "login";
//	}

	@GetMapping("/logoutsuccessful")
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		System.out.println("marqueur déconnexion");
		return "redirect:/index";
	}

//	public static void main(String[] args) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		
//		String password="Abc123!";
//		String encodePw = encoder.encode(password);
//		System.out.println(encodePw);
//		System.out.println(encodePw);
//	}

	@GetMapping("/CreaEvent")
	public String CreaEvent(Model model) {
		System.out.println("Crea event");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		model.addAttribute("user", new Users());
		List<GenreMusic> list = genreMusRepo.findAll();
		System.out.println(list);
		model.addAttribute("genre", list);
		if (isAuthenticated()) {
			return "CreaEvent";
		}
		
		model.addAttribute("events", new Evenement());

		return "accueillogged";
	}

	

	@GetMapping("/listeevent")
	public String listEvent(Model model, Principal principal) {
		//Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getName();
		Users activeuser = userRepo.findByEmail(username);

		List<UsersEvenement> listuEvenement = uEventRepo.findAllByUsers(activeuser);
		model.addAttribute("listuEvent", listuEvenement);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		Users user = userRepo.findByEmail(auth.getName());
		model.addAttribute("user", user);
		return "listevent";
	}

	@GetMapping("/parametres")
	public String parametres(HttpSession session, Model model) {
		return "parametres";
	}

	@GetMapping("/event_created_successfully")
	public String eventcreatedsuccessfully() {
		return "event_created_successfully";
	}

}
