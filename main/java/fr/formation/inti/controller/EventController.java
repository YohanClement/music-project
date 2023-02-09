package fr.formation.inti.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.inti.entity.Evenement;
import fr.formation.inti.entity.GenreMusic;
import fr.formation.inti.entity.Users;
import fr.formation.inti.entity.UsersEvenement;
import fr.formation.inti.model.Location;
import fr.formation.inti.repository.EvenementRepository;
import fr.formation.inti.repository.GenreMusicRepository;
import fr.formation.inti.repository.UserRepository;
import fr.formation.inti.repository.UsersEvenementRepository;
import fr.formation.inti.repository.UsersInstruDAO;

@Controller
public class EventController {

	@Autowired
	private EvenementRepository eventRepository;

	@Autowired
	private GenreMusicRepository gmr;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UsersEvenementRepository uer;

	@Autowired
	private UsersInstruDAO uInstrRepo;


	public EventController() {

	}

	@GetMapping("/pageEvent")
	public String PageEvent(Model model, @RequestParam Integer id) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		Users activeuser = userRepository.findByEmail(username);

		model.addAttribute("user", activeuser);

		Evenement event = eventRepository.findById(id).get();
		model.addAttribute("event", event);
		System.out.println(event.getPhotosImagePath());

		// Récupérer le genre de musique
		Integer Idgenre = event.getGenreMusic().getGenreId();
		GenreMusic genre = gmr.findById(Idgenre).get();
		System.out.println(genre.getGenreName());
		model.addAttribute("genre", genre.getGenreName());

		// Récupérer le user créateur
		Integer idcrea = event.getUsers().getUsersId();
		Users userCrea = userRepository.findById(idcrea).get();
		System.out.println(userCrea.getUsersFirstName());
		model.addAttribute("userCrea", userCrea);

		// Récupérer les personnes intéressées
		Set<UsersEvenement> userEvenement = event.getUsersEvenements();
		System.out.println("list " + userEvenement);
		List<Users> list_users = new ArrayList<Users>();
		for (UsersEvenement e : userEvenement) {
			Integer id1 = e.getUsers().getUsersId();
			System.out.println("id user qui sont intérréssés : " + id1);
			Users u = userRepository.findById(id1).get();
			System.out.println("user " + u.getUsersFirstName());
			list_users.add(u);
		}

		// partie carte

		Location location;

		String name = event.getEvenementAdress() + " " + event.getEvenementCity();
		String name2 = event.getEvenementLieu();
		System.out.println(name);
		location = new Location(name);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> topic_body = restTemplate
				.exchange("https://nominatim.openstreetmap.org/?addressdetails=1&q=" + location.getName()
						+ "&format=json&limit=1", HttpMethod.GET, null, String.class);

		String topics = topic_body.getBody();
		System.out.println("test : " + topics);
		topics = topics.replace("\"address\":{", "");
		topics = topics.replace("[", "");
		topics = topics.replace("]", "");
		topics = topics.replace("}}", "");
		topics = topics.replace("{", "");

		List<String> test = new ArrayList<String>();

		String[] list = topics.split(",\"");
		Location l = new Location();

		for (int i = 0; i < list.length; i++) {

			String j = list[i].replace("\"", "");
			list[i] = j;

			String[] list1 = list[i].split(":");

			if (list1[0].equals("lat")) {
				test.add(list1[1]);
			}
			if (list1[0].equals("lon")) {

				test.add(list1[1]);
			}
			if (list1[0].equals("place_id")) {

				test.add(list1[1]);
			}
			if (list1[0].equals("country")) {

				test.add(list1[1]);
			}
		}
		System.out.println("test1 : " + test);
		location.setCountry(test.get(3));
		location.setPlace_id(test.get(0));
		location.setLatitude(test.get(1));
		location.setLongitud(test.get(2));
//				String longe = location.getLongitud();
//				String late = location.getLatitude();
//				String[] locationliste =  new String[2];
//				locationliste[0]=late;
//				locationliste[1]=longe;

		List<UsersEvenement> listuserE = uer.findAllByEvenement(event);
		boolean interrested = false;
		for (int i = 0; i < listuserE.size(); i++) {

			if (listuserE.get(i).getUsers().getUsersId() == activeuser.getUsersId()) {
				interrested = true;
			}
		}

		model.addAttribute("interrested", interrested);

		System.out.println("test 5: " + location);
		model.addAttribute("location", location);

		model.addAttribute("userinterressed", list_users);

		return "Event";
	}

	@GetMapping("/interested")
	public String InteretEvenement(Model model, @RequestParam Integer id, Principal principal) {
		String Session = principal.getName();
		Users userSession = userRepository.findByEmail(Session);
		System.out.println("user session : " + userSession.getUsersId());

		// recupérer id de l'évènement
		Evenement evenement = eventRepository.findById(id).get();

		System.out.println("evenement : " + evenement.getEvenementName());

		UsersEvenement newInteret = new UsersEvenement(eventRepository.findById(id).get(),
				userRepository.findByEmail(Session));
		System.out.println(newInteret);
		UsersEvenement newInteretSaved = uer.save(newInteret);
		System.out.println(newInteretSaved.getEvenement().getEvenementName());

		String retour = "redirect:/pageEvent?id=" + id;
		return retour;

	}

	@GetMapping("/EditEvent")
	public String editg(@RequestParam Integer id, Model model) {
		Evenement e = eventRepository.findById(id).get();

		List<GenreMusic> style = gmr.findAll();

		final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime debut = e.getEvenementDatedebut();
		String d = debut.format(CUSTOM_FORMATTER);

		LocalDateTime fin = e.getEvenementDatefin();
		String f = fin.format(CUSTOM_FORMATTER);

		model.addAttribute("event", e);
		model.addAttribute("genre", style);
		model.addAttribute("debut", d);
		model.addAttribute("fin", f);
		return "EditEvent";
	}

	@PostMapping("/EditEvent")
	public String savecrea(Evenement event, @RequestParam("debut") String datedebut,
			@RequestParam("fin") String datefin, @RequestParam("genre") String genre) {

		Integer id = event.getEvenementId();

		Evenement ev = eventRepository.findById(id).get();
		System.out.println("id : " + ev.getEvenementId());
		ev.setEvenementAdress(event.getEvenementAdress());
		ev.setEvenementBio(event.getEvenementBio());
		ev.setEvenementCity(event.getEvenementCity());
		ev.setEvenementLieu(event.getEvenementLieu());
		ev.setEvenementName(event.getEvenementName());
		ev.setEvenementType(event.getEvenementType());
		ev.setEvenementZip(event.getEvenementZip());

		GenreMusic genremusic1 = gmr.findByGenreName(genre);

		event.setGenreMusic(genremusic1);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); // H majuscule pour etre en format
																					// 24h
		LocalDateTime debut = LocalDateTime.parse(datedebut, dtf);
		LocalDateTime fin = LocalDateTime.parse(datefin, dtf);

		ev.setEvenementDatedebut(debut);
		ev.setEvenementDatefin(fin);

		eventRepository.save(ev);

		return "redirect:/pageEvent?id=" + ev.getEvenementId();

	}

	@GetMapping("/eventNonLog")
	public String PageEventNonLoog(Model model, @RequestParam Integer id) {

		Evenement event = eventRepository.findById(id).get();
		model.addAttribute("event", event);

		// Récupérer le genre de musique
		Integer Idgenre = event.getGenreMusic().getGenreId();
		GenreMusic genre = gmr.findById(Idgenre).get();
		System.out.println(genre.getGenreName());
		model.addAttribute("genre", genre.getGenreName());

		// Récupérer le user créateur
		Integer idcrea = event.getUsers().getUsersId();
		Users userCrea = userRepository.findById(idcrea).get();
		System.out.println(userCrea.getUsersFirstName());
		model.addAttribute("userCrea", userCrea);

		return "EventNonLog";
	}
	
	
	@PostMapping("/process_creaevent")
	public String processCreaEvent(Evenement event,  
			@RequestParam("evenementDatedebut1") String datedebut,
			@RequestParam("evenementDatefin1") String datefin, @RequestParam("genre") String genremusic,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		
		
		System.out.println("tostring1 :  " + event.toString());

		System.out.println(datedebut);
		System.out.println(datefin);

		GenreMusic g = gmr.findByGenreName(genremusic);
		event.setGenreMusic(g);

		// DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); // H majuscule pour etre en format
																					// 24h

		LocalDateTime debut = LocalDateTime.parse(datedebut, dtf);
		LocalDateTime fin = LocalDateTime.parse(datefin, dtf);
		System.out.println("marqueur 2 : " + debut);
		System.out.println("marqueur 3 : " + fin);

		event.setEvenementDatedebut(debut);
		event.setEvenementDatefin(fin);

		System.out.println("tostring2 :  " + event.toString());

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			System.out.println("case 1 : " + username);
			Users activeuser = userRepository.findByEmail(username);
			event.setUsers(activeuser);
		} else {
			String username = principal.toString();
			System.out.println("case 2 : " + username);

		}
		// Il faut que le user qui créé l'event soit enregistré par défaut ds la colonne
		// user crea
		// il faut aussi mettre à jour la table d'association user-event en y ajoutant
		// le couple createur -event
		// ensuite il faudra gerer les invitations via la page d'evenement, un "je
		// participe" mets a jour la table event -user

		String username = ((UserDetails) principal).getUsername();
		Users activeuser = userRepository.findByEmail(username);
		System.out.println("user actif id : " + activeuser.getUsersId());

		// sauvegarder la photo
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		event.setPhotos(fileName);

//	    Users savedUser =userRepo.save(user);
		Evenement savedEvent = eventRepository.save(event);

		// Saving THez profile picture
		String uploadDir = "event-photos/" + savedEvent.getEvenementId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		System.out.println("saved event id : " + savedEvent.getEvenementId());
		System.out.println("saved event  : " + savedEvent.getEvenementId());
		System.out.println("saved event id 2: " + event.getEvenementId());

		UsersEvenement uEvent = new UsersEvenement(savedEvent, activeuser);
		UsersEvenement saveduEvent = uer.save(uEvent);
		System.out.println(saveduEvent.toString());
		return "event_created_successfully";
	}

}
