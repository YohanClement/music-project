package fr.formation.inti.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.formation.inti.entity.AudioGroupe;
import fr.formation.inti.entity.GenreGroupe;
import fr.formation.inti.entity.GenreMusic;
import fr.formation.inti.entity.Groupe;
import fr.formation.inti.entity.GroupeMembers;
import fr.formation.inti.entity.Postulations;
import fr.formation.inti.entity.Users;
import fr.formation.inti.repository.GenreGroupeRepository;
import fr.formation.inti.repository.GenreMusicRepository;
import fr.formation.inti.repository.GmembersRepository;
import fr.formation.inti.repository.GroupeRepository;
import fr.formation.inti.repository.PostulationRepository;
import fr.formation.inti.repository.UserRepository;

@Controller
public class GroupeController {

	@Autowired
	private GroupeRepository grouperep;

	@Autowired
	private UserRepository ur;

	@Autowired
	private GenreMusicRepository gmr;

	@Autowired
	private PostulationRepository postRepo;

	@Autowired
	private GmembersRepository gmRep;

	@Autowired
	private GenreGroupeRepository ggRep;

	@Autowired
	private PostulationRepository postRep;

	@GetMapping("/pagegroupe")
	public String viewgroupepage(Model model) {
		return "creationgroupe";
	}

	@PostMapping("/creationgroupe")
	public String addGroupPost(Groupe groupe, @RequestParam("genre") String genre,
			@RequestParam("image") MultipartFile multipartFile, @RequestParam("audios") MultipartFile multipartFile1,
			Principal principal) throws IOException {
		System.out.println(groupe);

		// permet de changer en chaine de caracter pour savoir si le groupe recrute

		if ("on".equals(groupe.getGroupeIsRecruting())) {
			groupe.setGroupeIsRecruting("Oui");
		} else {
			groupe.setGroupeIsRecruting("Non");
		}
		System.out.println(groupe.getGroupeIsRecruting());

		// pour le telechargement de la photo

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		groupe.setPhotos(fileName);

		// Sauvegarde de l'audio maquette
		String fileName1 = StringUtils.cleanPath(multipartFile1.getOriginalFilename());
		groupe.setAudio(fileName1);

		System.out.println("nom du fileName:" + fileName);

		if ("".equals(fileName)) {
			groupe.setPhotos(null);
		}
		System.out.println("photos après :" + groupe.getPhotos());

		// Object principal =
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getName();
		Users activeuser = ur.findByEmail(username);

		groupe.setUsers(activeuser);

		Groupe savedGroupe = grouperep.save(groupe);

		GroupeMembers gm = new GroupeMembers(savedGroupe, activeuser);

		gmRep.save(gm);

		GenreMusic genre1 = gmr.findByGenreName(genre);

		GenreGroupe ggroupe = new GenreGroupe(genre1, savedGroupe);

		ggRep.save(ggroupe);

		// pour créer le chemin de la photo
		String uploadDir = "groupe-photos/" + savedGroupe.getGroupeId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		// Sauvegarde l'audio maquette dans un dossier
		String uploadDir1 = "groupe-audio/" + savedGroupe.getGroupeId();
		FileUploadUtil.saveFile(uploadDir1, fileName1, multipartFile1);

		// metttre l'audio
		return "redirect:/accueillogged";

	}

	@GetMapping("/groupe")
	public String viewgrouppage(@RequestParam("id") Integer id, Model model, HttpSession session, Principal principal) {
		// afficher selon id
		Groupe gr = grouperep.findById(id).get();

		// Object principal =
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getName();
		Users activeuser = ur.findByEmail(username);
		
		Set<GenreGroupe> style = gr.getGenreGroupes();
		List<GenreMusic> genre = new ArrayList<GenreMusic>();
		for (GenreGroupe g : style) {
			Integer id1 = g.getGenreMusic().getGenreId();
			GenreMusic m = gmr.findById(id1).get();
			genre.add(m);
		}

		Set<GroupeMembers> nous = gr.getGroupeMemberses();
		List<Users> members = new ArrayList<Users>();
		for (GroupeMembers user : nous) {
			Integer id2 = user.getUsers().getUsersId();
			Users u = ur.findById(id2).get();
			members.add(u);
		}

		Set<AudioGroupe> ag = gr.getAudioGroupes();

//		for (AudioGroupe sono : ag) {
//			String a = sono.getAudioName();
//		}

		List<Postulations> list_postu = postRep.findAllByGroupe(gr);
		boolean apply = false;

		for (int i = 0; i < list_postu.size(); i++) {

			if (list_postu.get(i).getUser().getUsersId() == activeuser.getUsersId()) {
				apply = true;
			}
		}
		
		List<GroupeMembers> groupemembers = gmRep.findAllByGroupe(gr);
		for(int i = 0; i < groupemembers.size(); i++) {
			
			if (groupemembers.get(i).getUsers().getUsersId() == activeuser.getUsersId()) {
				apply = true;
			}
		}
		

		model.addAttribute("apply", apply);

		model.addAttribute("groupe", gr);
		model.addAttribute("NosGenres", genre);
		model.addAttribute("NosMembres", members);
		// Object principal =
		model.addAttribute("user", activeuser);
		model.addAttribute("postulations", session.getAttribute("postulations"));
		return "groupe";
	}

	@GetMapping("/updategroupe")
	public String editg(@RequestParam("id") Integer id, Model model) {
		Groupe gro = grouperep.findById(id).get();

		List<GenreMusic> style = gmr.findAll();

		Set<GenreGroupe> Nosstyle = gro.getGenreGroupes();
		List<GenreMusic> genre = new ArrayList<GenreMusic>();
		for (GenreGroupe g : Nosstyle) {
//			Integer id1 = g.getId().getGenreId();
//			GenreMusic m = gmr.findById(id1).get();
//			genre.add(m);
		}

		model.addAttribute("Mygroupe", gro);
		model.addAttribute("genre", style);
		model.addAttribute("Mygenre", genre);
		return "EditGroupe";
	}

	@PostMapping("/updategroupe")
	public String editgroupe(Groupe groupe, @RequestParam(name = "image") MultipartFile multipartFile,
			@RequestParam(name = "audios") MultipartFile audio) throws IOException {
		Integer id = groupe.getGroupeId();
		Groupe gro = grouperep.findById(id).get();

		gro.setGroupeName(groupe.getGroupeName());
		gro.setGroupeEmail(groupe.getGroupeEmail());
		gro.setGroupeDescription(groupe.getGroupeDescription());
		gro.setGroupeFrequence(groupe.getGroupeFrequence());

		if ("on".equals(groupe.getGroupeIsRecruting())) {
			gro.setGroupeIsRecruting("Oui");
		} else {
			gro.setGroupeIsRecruting("Non");
		}
		System.out.println(groupe.getGroupeIsRecruting());

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		System.out.println(gro.getPhotos());
		if (!multipartFile.isEmpty()) {
			Path path = Paths.get(gro.getPhotosImagePath());
			Files.deleteIfExists(path);
			gro.setPhotos(fileName);

			String uploadDir = "user-photos/" + groupe.getGroupeId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}

		String AudioName = StringUtils.cleanPath(audio.getOriginalFilename());
		if (!audio.isEmpty()) {
			String uploadAud = "user-audio/" + groupe.getGroupeId();
			Path path = Paths.get(gro.getAudio());
			try {
				Files.deleteIfExists(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gro.setPhotos(AudioName);

			FileUploadUtil.saveFile(uploadAud, AudioName, audio);
		}

		grouperep.save(gro);

		return "redirect:/groupe?id=" + gro.getGroupeId();

	}

	@GetMapping("/postulations")
	public String viewpostulations(Model model, HttpSession session, @RequestParam("id") Integer id) {
		System.out.println(id);
		Groupe gr = grouperep.findById(id).get();
		List<Postulations> lpostulations2 = postRepo.findAllByGroupe(gr);
		if (lpostulations2.size() > 0) {
			List<Postulations> lpostulations = new ArrayList<>();
			for(int i = 0; i < lpostulations2.size(); i++) {
				if (lpostulations2.get(i).getStatut().equals("en attente")){
					lpostulations.add(lpostulations2.get(i));
				}
				
			}
			model.addAttribute("postulations", lpostulations);
			session.setAttribute("postulations", lpostulations);
		}
		
		
		
		String str = id.toString();
		System.out.println(str);
		return "redirect:/groupe?id=" + str;
	}

	@GetMapping("/postulationvalider")
	public String validerpostulation(Model model, Postulations pos, @RequestParam("id") Integer id) {
//		public String validerpostulation(Model model, @RequestParam("id") Integer id) {
		System.out.println(id);
		System.out.println("Id postulation"+pos.getPostulationId());
		Postulations post = postRepo.findById(id).get();
		System.out.println(post.toString());
		post.setStatut("oui");
		postRepo.save(post);

		// Users user = ur.findByEmail(post.)
		Users user = ur.findByEmail(post.getUser().getUsersEmail());
		Groupe groupe = grouperep.findById(post.getGroupe().getGroupeId()).get();
		System.out.println(groupe.getGroupeId());
		GroupeMembers gmembers = new GroupeMembers(groupe, user);
		gmRep.save(gmembers);
		// ajouter a groupe member le nouveau membre
		// aussi faire en sorte qu'à la création d'un groupe le user crea soit d'office
		// mis membre
		// mettre a jour groupe member et lui mettre 1 membre mini par groupe (le créa)
		// + mettre un groupe createur pr chaque groupe

		String str = post.getGroupe().getGroupeId().toString();
		System.out.println(str);
		return "redirect:/groupe?id=" + str;

	}

	@PostMapping("/postuler")
	public String postuler1(@RequestParam("id") Integer id, Principal principal) {
		// Object principal =
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getName();
		Users activeuser = ur.findByEmail(username);
		Groupe groupe = grouperep.findById(id).get();
		System.out.println(id);
		Postulations post = new Postulations(activeuser, groupe);
		System.out.println(post.toString());
		Postulations savedpost = postRepo.save(post);

		return "redirect:/profil";
	}
}
