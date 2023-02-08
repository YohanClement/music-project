package fr.formation.inti.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import fr.formation.inti.entity.Users;
import fr.formation.inti.repository.GenreMusicRepository;
import fr.formation.inti.repository.GroupeRepository;
import fr.formation.inti.repository.UserRepository;

@Controller
public class GroupeController {

	@Autowired
	private GroupeRepository grouperep;

	@Autowired
	private UserRepository ur;

	@Autowired
	private GenreMusicRepository gmr;

	@GetMapping("/pagegroupe")
	public String viewgroupepage(Model model) {
		return "creationgroupe";
	}

	@PostMapping("/creationgroupe")
	public String addGroupPost(Groupe groupe, @RequestParam("image") MultipartFile multipartFile) throws IOException {
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
		System.out.println("nom du fileName:" + fileName);

		if ("".equals(fileName)) {
			groupe.setPhotos(null);
		}
		System.out.println("photos après :" + groupe.getPhotos());

		Groupe savedGroupe = grouperep.save(groupe);

		// pour créer le chemin de la photo
		String uploadDir = "groupe-photos/" + savedGroupe.getGroupeId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		return "groupeenregistre";

	}

	@GetMapping("/groupe")
	public String viewgrouppage(Model model, @RequestParam("id") Integer id) {
		Groupe gr = grouperep.findById(id).get();
		Set<GenreGroupe> style = gr.getGenreGroupes();
		List<GenreMusic> genre = new ArrayList<GenreMusic>();
		for (GenreGroupe g : style) {
			Integer id1 = g.getId().getGenreId();
			GenreMusic m = gmr.findById(id1).get();
			genre.add(m);
		}

		Set<GroupeMembers> nous = gr.getGroupeMemberses();
		List<Users> members = new ArrayList<Users>();
		for (GroupeMembers user : nous) {
			Integer id2 = user.getId().getUsersMembers();
			System.out.println(id2);
			Users u = ur.findById(id2).get();
			members.add(u);
		}

		Set<AudioGroupe> ag = gr.getAudioGroupes();

//		for (AudioGroupe sono : ag) {
//			String a = sono.getAudioName();
//		}

		model.addAttribute("groupe", gr);
		model.addAttribute("NosGenres", genre);
		model.addAttribute("NosMembres", members);
		return "groupe";
	}
	

	@GetMapping("/updategroupe")
	public String editg(@RequestParam("id") Integer id, Model model) {
		Groupe gro = grouperep.findById(id).get();

		List<GenreMusic> style = gmr.findAll();

		Set<GenreGroupe> Nosstyle = gro.getGenreGroupes();
		List<GenreMusic> genre = new ArrayList<GenreMusic>();
		for (GenreGroupe g : Nosstyle) {
			Integer id1 = g.getId().getGenreId();
			GenreMusic m = gmr.findById(id1).get();
			genre.add(m);
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
}
