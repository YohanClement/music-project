package fr.formation.inti.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.formation.inti.entity.Contact;
import fr.formation.inti.entity.Evenement;
import fr.formation.inti.entity.GenreMusic;
import fr.formation.inti.entity.Musicinstruments;
import fr.formation.inti.entity.UserRoles;
import fr.formation.inti.entity.Users;
import fr.formation.inti.entity.UsersEvenement;
import fr.formation.inti.entity.UsersGenre;
import fr.formation.inti.entity.UsersInstruments;
import fr.formation.inti.repository.ContactRepository;
import fr.formation.inti.repository.EvenementRepository;
import fr.formation.inti.repository.GenreMusicRepository;
import fr.formation.inti.repository.MusicInstrumentRepository;
import fr.formation.inti.repository.UserRepository;
import fr.formation.inti.repository.UserRoleRepository;
import fr.formation.inti.repository.UsersInstruDAO;

@Controller
public class ProfilController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GenreMusicRepository gmr;

	@Autowired
	private MusicInstrumentRepository mir;

	@Autowired
	private UserRoleRepository rolesRepo;

	@Autowired
	private UsersInstruDAO uInstrRepo;

	@Autowired
	private EvenementRepository er;
	
	@Autowired
	private ContactRepository cr;

	@GetMapping("/profil")
	public String index(Model model, @RequestParam Integer id) {

		Users user1 = userRepository.findById(id).get();

		// recupérer la liste des styles musicaux du profil
		Set<UsersGenre> style = user1.getUsersGenres();
		List<GenreMusic> genre = new ArrayList<GenreMusic>();
		for (UsersGenre g : style) {
			Integer id1 = g.getId().getGenreId();
			System.out.println("id : " + id1);
			GenreMusic m = gmr.findById(id1).get();
			System.out.println("genre musique " + m.getGenreName());
			genre.add(m);
		}

		// Récupérer la liste des instruments dont joue le profil et son niveau
		Set<UsersInstruments> instrument = user1.getUsersInstrumentses();
		Map<Musicinstruments, Integer> list_instru = new HashMap<Musicinstruments, Integer>();
		for (UsersInstruments i : instrument) {
			Integer id2 = i.getMusicinstruments().getInstrId();
			Integer niveau = i.getNiveau();
			System.out.print("id instrument : " + id2);
			System.out.println(" niveau : " + niveau);
			Musicinstruments instru = mir.findById(id2).get();
			System.out.println("instrument : " + instru);
			list_instru.put(instru, niveau);
		}

		// Récupérer la liste des amis
		Set<Contact> contact = user1.getContactsForUsersId();
		List<Users> list_amis = new ArrayList<Users>();
		for (Contact c : contact) {
			Integer id_contact = c.getUsersByContactId().getUsersId();
			System.out.print("id du contact : " + id_contact);
			Users u = userRepository.findById(id_contact).get();
			System.out.println(" | prénom ami " + u.getUsersFirstName());
			list_amis.add(u);
		}
		
		// Récupérer la liste des évènements
		Set<UsersEvenement> userEvenement = user1.getUsersEvenements();
		List<Evenement> list_evenements = new ArrayList<Evenement>();
		for (UsersEvenement e : userEvenement) {
			Integer id_event = e.getEvenement().getEvenementId();
			System.out.print("id evenement : " + id_event);
			Evenement event = er.findById(id_event).get();
	System.out.println("nom evenement " + event.getEvenementName() + " id : " + event.getEvenementId());
		list_evenements.add(event);
		}

		model.addAttribute("mesamis", list_amis);
		model.addAttribute("mesgenres", genre);
		model.addAttribute("user", user1);
		model.addAttribute("liste", list_instru);
		model.addAttribute("evenements", list_evenements);

		return "profil";

	}
	
	

	@PostMapping("/process_register")
	public String processRegister(Users user, @RequestParam("instruments") String instrument,
			@RequestParam("niveau") Integer niveau, @RequestParam("image") MultipartFile multipartFile,
			@RequestParam("files") MultipartFile files) throws IOException {

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
		
		
		
		// Settings roles
		Users savedUser = userRepository.save(user);
		UserRoles userrole = new UserRoles(user, "USER", user.getUsersEmail());
		rolesRepo.save(userrole);

		// Saving Instruments
		UsersInstruments uInst = new UsersInstruments(savedUser, mir.findByinstrName(instrument), niveau);
		UsersInstruments uInstsaved = uInstrRepo.save(uInst);
		System.out.println(uInstsaved.getUserInstrumentId());

		// Saving THez profile picture
		String uploadDir = "user-photos/" + savedUser.getUsersId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
		String UploadAudio = "user-audio/" + savedUser.getUsersId();
		FileUploadUtil.saveFile(UploadAudio, NameFile, files);
		return "index";
	}
	
	@GetMapping("/update")
	public String edit(Principal principal, Model model) {

		String username = principal.getName();
		System.out.println("case 1 : " + username);
		Users u = userRepository.findByEmail(username);
		Set<UsersInstruments> instrument = u.getUsersInstrumentses();
//		Map<Musicinstruments, Integer> instru = new HashMap<Musicinstruments, Integer>();
		List<Musicinstruments> list = new ArrayList<Musicinstruments>();
		List<Integer> niv = new ArrayList<Integer>();
		for (UsersInstruments i : instrument) {
			Integer id2 = i.getMusicinstruments().getInstrId();
			Integer niveau = i.getNiveau();
			Musicinstruments musique = mir.findById(id2).get();
			niv.add(niveau);
			list.add(musique);
		}

		Map<String, Integer> list_instru = new HashMap<String, Integer>();
		for (UsersInstruments i : instrument) {
			Integer id2 = i.getMusicinstruments().getInstrId();
			Integer niveau = i.getNiveau();
			Musicinstruments instru = mir.findById(id2).get();
			list_instru.put(instru.getInstrName(), niveau);
		}

		model.addAttribute("Myuser", u);
		model.addAttribute("inst", list);
		model.addAttribute("map", list_instru);

		return "EditProfil";
	}

	@PostMapping("/update")
	public String update(Users u, @RequestParam("instruments") String instrument,
			@RequestParam("niveau") Integer niveau, @RequestParam("image") MultipartFile multipartFile,
			@RequestParam("audios") MultipartFile files) throws IOException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		Users user = userRepository.findByEmail(username);

		user.setUsersBio(u.getUsersBio());
		user.setUsersEmail(u.getUsersEmail());
		user.setUsersFirstName(u.getUsersFirstName());
		user.setUsersLastName(u.getUsersLastName());
		user.setUsersCity(u.getUsersCity());
		user.setUsersZip(u.getUsersZip());

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		System.out.println(user.getPhotos());
		if (!multipartFile.isEmpty()) {
			Path path = Paths.get(user.getPhotosImagePath());
			Files.deleteIfExists(path);
			user.setPhotos(fileName);
			
			String uploadDir = "user-photos/" + user.getUsersId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}
		
		String AudioName = StringUtils.cleanPath(files.getOriginalFilename());
		if (!files.isEmpty()) {
			String uploadAud = "user-audio/" + user.getUsersId();
			Path path = Paths.get(user.getAudioPath());
			Files.deleteIfExists(path);
			user.setPhotos(AudioName);
			
			FileUploadUtil.saveFile(uploadAud, AudioName, multipartFile);
		}

		// Settings roles
		Users savedUser = userRepository.save(user);

		// Saving Instruments
//		UsersInstruments uInst = new UsersInstruments(savedUser, mir.findByinstrName(instrument), niveau);
		List<UsersInstruments> list = uInstrRepo.findByUsers(savedUser);
		UsersInstruments ui = new UsersInstruments();
		Musicinstruments m = mir.findByinstrName(instrument);
		for (UsersInstruments a : list) {
			if (a.getMusicinstruments() == m) {
				ui = a;
				ui.setNiveau(niveau);
			}
		}

		uInstrRepo.save(ui);

		return "redirect:/parametres";
	}
	
	@GetMapping("/ajouter")
	public String PageAjouter(Model model, @RequestParam Integer id, Principal principal) {
		String Session = principal.getName();
		Users userSession = userRepository.findByEmail(Session);
		System.out.println("user session : " + userSession.getUsersId());

		// recupérer id de l'ami
		Users userContact = userRepository.findById(id).get();

		System.out.println("contact : " + userContact.getUsersId());
//		List<Contact> contact=cr.findByusersByUsersIdAndusersByContactId (userSession, userContact);
	if (userSession.getUsersId()!=userContact.getUsersId()) {
		Contact newFriend = new Contact(userRepository.findByEmail(Session), userRepository.findById(id).get());
		System.out.println(newFriend.getUsersByContactId().getUsersFirstName());
		Contact newFriendSaved = cr.save(newFriend);
		System.out.println(newFriendSaved.getUsersByContactId().getUsersFirstName());

		String retour="redirect:/profil?id="+id;
		return retour;

	}else {
		String retour="redirect:/profil?id="+id;
		return retour;
}

	}

}
