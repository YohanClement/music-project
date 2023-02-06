package fr.formation.inti.controller;

import java.io.IOException;
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
		System.out.println("value du groupe frequence"+groupe.getGroupeFrequence());

		//permet de changer en chaine de caracter pour savoir si le groupe recrute

		if ("on".equals(groupe.getGroupeIsRecruting())) {
			groupe.setGroupeIsRecruting("Oui");
		} else {
			groupe.setGroupeIsRecruting("Non");
		}
		System.out.println(groupe.getGroupeIsRecruting());
		
		//pour le telechargement de la photo

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		groupe.setPhotos(fileName);

		Groupe savedGroupe = grouperep.save(groupe);
		
		//ppur creer le chemin de la photo
		String uploadDir = "groupe-photos/" + savedGroupe.getGroupeId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		return "groupeenregistre";

	}
	
	@GetMapping("/groupe")
	public String viewgrouppage(Model model) {
		Groupe gr = grouperep.findById(63).get();
		Set<GenreGroupe> style = gr.getGenreGroupes();
		List<GenreMusic> genre = new ArrayList<GenreMusic>();
		for (GenreGroupe g : style) {
			Integer id = g.getId().getGenreId();
			GenreMusic m = gmr.findById(id).get();
			genre.add(m);
		}
		
		Set<GroupeMembers> nous = gr.getGroupeMemberses();
		List<Users> members = new ArrayList<Users>();
		for (GroupeMembers user : nous) {
			Integer id = user.getId().getUsersMembers();
			Users u = ur.findById(id).get();
			members.add(u);
		}	
		
		model.addAttribute("groupe", gr);
		model.addAttribute("NosGenres", genre);
		model.addAttribute("NosMembres", members);
		return "groupe";
	}

}
