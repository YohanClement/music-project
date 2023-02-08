package fr.formation.inti.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.inti.entity.Contact;
import fr.formation.inti.entity.GenreMusic;
import fr.formation.inti.entity.Musicinstruments;
import fr.formation.inti.entity.UserRoles;
import fr.formation.inti.entity.Users;
import fr.formation.inti.entity.UsersGenre;
import fr.formation.inti.entity.UsersInstruments;
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
			Integer id_contact = c.getId().getContactId();
			System.out.print("id du contact : " + id_contact);
			Users u = userRepository.findById(id_contact).get();
			System.out.println(" | prénom ami " + u.getUsersFirstName());
			list_amis.add(u);
		}

		model.addAttribute("mesamis", list_amis);

		model.addAttribute("mesgenres", genre);

		model.addAttribute("user", user1);
		model.addAttribute("liste", list_instru);

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
		return "login";
	}
}
