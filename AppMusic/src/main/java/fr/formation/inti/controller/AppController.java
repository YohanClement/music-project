package fr.formation.inti.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.inti.entity.Evenement;
import fr.formation.inti.entity.GenreMusic;
import fr.formation.inti.entity.UserRoles;
import fr.formation.inti.entity.Users;
import fr.formation.inti.entity.UsersEvenement;
import fr.formation.inti.entity.UsersInstruments;
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
			return "return:accueillogged";
		}
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new Users());
		if (isAuthenticated()) {
			return "redirect:accueillogged";
		}
		return "creationprofil";
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
	public String index() {
		return "index";
	}

	@GetMapping("/accueillogged")
	public String accueil(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepo.findByEmail(auth.getName());
		model.addAttribute("user", user);
		return "accueillogged";
	}

	@PostMapping("/process_register")
	public String processRegister(Users user, @RequestParam("instruments") String instrument,
			@RequestParam("niveau") Integer niveau, @RequestParam("image") MultipartFile multipartFile,
			@RequestParam("files") MultipartFile files, @RequestParam("audios") MultipartFile multipartFile1) throws IOException {

//		System.out.println(user);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

//      System.out.println(formatter.format(date));
		user.setUsersDateCrea(now);
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		user.setPhotos(fileName);

		String NameFile = StringUtils.cleanPath(files.getOriginalFilename());
		user.setAudio(NameFile);
		
		//Sauvegarde de l'audio maquette
      	String fileName1 = StringUtils.cleanPath(multipartFile1.getOriginalFilename());	
        user.setAudio(fileName1);
			
		
		// Settings roles
		Users savedUser = userRepo.save(user);
		UserRoles userrole = new UserRoles(user, "USER", user.getUsersEmail());
		rolesRepo.save(userrole);

		// Saving Instruments
		UsersInstruments uInst = new UsersInstruments(savedUser, instrRepo.findByinstrName(instrument), niveau);
		UsersInstruments uInstsaved = uInstrRepo.save(uInst);
		System.out.println(uInstsaved.getUserInstrumentId());

		// Saving THez profile picture
		String uploadDir = "user-photos/" + savedUser.getUsersId();

		//Sauvegarde l'audio maquette dans un dossier
        String uploadDir1 = "user-audio/" + savedUser.getUsersId();
        FileUploadUtil.saveFile(uploadDir1, fileName1, multipartFile1);
		
		return "login";
	}

	@GetMapping("/logoutsuccessful")
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		System.out.println("marqueur déconnexion");
		return "index";
	}

//	public static void main(String[] args) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		
//		String password="Abc123!";
//		String encodePw = encoder.encode(password);
//		System.out.println(encodePw);
//		System.out.println(encodePw);
//	}
	
	@GetMapping("/creaevent")
	public String CreaEvent(Model model) {
	   
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    System.out.println(auth.getName());
	    model.addAttribute("user", new Users());
	    if (isAuthenticated()) {
	    	return "CreaEvent";
	    }
	    return "login";
	}
	
	@PostMapping("/process_creaevent")
	public String processCreaEvent(Evenement event,@RequestParam("evenementDatedebut1") String datedebut, 
			@RequestParam("evenementDatefin1") String datefin,@RequestParam("genreMusic1") String genremusic) throws IOException {
		System.out.println("tostring1 :  " + event.toString());
		
		
	
		System.out.println(datedebut);
		System.out.println(datefin);
		
	//	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); //H majuscule pour etre en format 24h
		
		LocalDateTime debut = LocalDateTime.parse(datedebut, dtf); 
		LocalDateTime fin = LocalDateTime.parse(datefin, dtf); 
		System.out.println("marqueur 2 : " + debut);
		System.out.println("marqueur 3 : " + fin);
		
		event.setEvenementDatedebut(debut);
		event.setEvenementDatefin(fin);
		
		GenreMusic genremusic1 = genreMusRepo.findByGenreName(genremusic);
		
		event.setGenreMusic(genremusic1);

		System.out.println("tostring2 :  " + event.toString());

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
		  String username = ((UserDetails)principal).getUsername();
		  System.out.println("case 1 : " + username);
		  Users activeuser = userRepo.findByEmail(username);
		  event.setUsers(activeuser);
		} else {
		  String username = principal.toString();
		  System.out.println("case 2 : " + username);

		}
		//Il faut que le user qui créé l'event soit enregistré par défaut ds la colonne user crea
		// il faut aussi mettre à jour la table d'association user-event en y ajoutant le couple createur -event
		//ensuite il faudra gerer les invitations via la page d'evenement, un "je participe" mets a jour la table event -user
		
		String username = ((UserDetails)principal).getUsername();
		Users activeuser = userRepo.findByEmail(username);
		System.out.println("user actif id : " + activeuser.getUsersId());
		
//	    Users savedUser =userRepo.save(user);
		Evenement savedEvent = eventRepo.save(event);
		System.out.println("saved event id : " + savedEvent.getEvenementId());
		System.out.println("saved event  : " + savedEvent.getEvenementId());
		System.out.println("saved event id 2: " + event.getEvenementId());
		
		
		
		UsersEvenement uEvent = new UsersEvenement(savedEvent,activeuser);
		UsersEvenement saveduEvent = uEventRepo.save(uEvent);
		System.out.println(saveduEvent.toString());
	    return "event_created_successfully";
	}
	
	@GetMapping("/listeevent")
	public String listEvent(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
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
//		Users Myuser = (Users) session.getAttribute("UsersSession");
//		model.addAttribute("MyId", Myuser.getUsersId());
//		System.out.println("MyUser :" + Myuser);
//		System.out.println("MyId :" + Myuser.getUsersId());
		return "parametres";
	}

	@GetMapping("/update")
	public String update(Model model, @RequestParam() Integer id) {
		// Integer id1 =Integer.parseInt(id);

		Users user = userRepo.findById(id).get();

		System.out.println(user);
		System.out.println(id);
		model.addAttribute("user", user);
		System.out.println(userRepo.findById(id).get());
		return "update";
	}

}
