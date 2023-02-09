package fr.formation.inti.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

		return "redirect:/pageEvent?id="+ev.getEvenementId();

	}
	
	

	

}
