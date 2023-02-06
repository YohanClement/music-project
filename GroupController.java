package fr.formation.inti.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.formation.inti.entity.GenreGroupe;
import fr.formation.inti.entity.GenreMusic;
import fr.formation.inti.entity.Groupe;
import fr.formation.inti.entity.GroupeMembers;
import fr.formation.inti.entity.Users;
import fr.formation.inti.repository.GenreMusicRepository;
import fr.formation.inti.repository.GroupeRepository;
import fr.formation.inti.repository.UsersRepository;

@Controller
public class GroupController {

	@Autowired
	private GroupeRepository grrepo;
	
	@Autowired
	private GenreMusicRepository gmr;
	
	@Autowired
	private UsersRepository ur;

	@GetMapping("/group")
	public String viewgrouppage(Model model) {
		Groupe gr = grrepo.findById(1).get();
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
