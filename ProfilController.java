package fr.formation.inti.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.inti.entity.Contact;
import fr.formation.inti.entity.GenreMusic;
import fr.formation.inti.entity.Musicinstruments;
import fr.formation.inti.entity.Users;
import fr.formation.inti.entity.UsersGenre;
import fr.formation.inti.entity.UsersInstruments;
import fr.formation.inti.repository.ContactRepository;
import fr.formation.inti.repository.GenreMusicRepository;
import fr.formation.inti.repository.MusicinstrumentsRepository;
import fr.formation.inti.repository.UserRepository;
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

@Controller
public class ProfilController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GenreMusicRepository gmr;
	@Autowired
	private MusicinstrumentsRepository mir;
//	@Autowired
//	private ContactRepository cr;
	
	
	public ProfilController() {
		
	}
	
	@GetMapping("/profil")
	public String index (Model model, @RequestParam Integer id ) {

		Users user1=userRepository.findById(id).get();
		
		// recupérer la liste des styles musicaux du profil
		Set<UsersGenre> style= user1.getUsersGenres();
		List<GenreMusic> genre = new ArrayList<GenreMusic>();
		for (UsersGenre g : style) {
			Integer id1 = g.getId().getGenreId();
			System.out.println("id : " +id1);
			GenreMusic m = gmr.findById(id1).get();
			System.out.println("genre musique " + m.getGenreName());
			genre.add(m);
		}
		
		//Récupérer la liste des instruments dont joue le profil et son niveau
		Set<UsersInstruments> instrument = user1.getUsersInstrumentses();
		Map<Musicinstruments, Integer> list_instru= new HashMap<Musicinstruments, Integer>();
		for (UsersInstruments i : instrument) {
			Integer id2 = i.getId().getInstrId();
			Integer niveau=i.getNiveau();
		System.out.print("id instrument : " +id2);
			System.out.println(" niveau : " + niveau);
			Musicinstruments instru = mir.findById(id2).get();
			System.out.println("instrument : " + instru);
			list_instru.put(instru, niveau);
		}
		
		
		//Récupérer la liste des amis
	Set<Contact> contact=user1.getContactsForUsersId();
	List<Users> list_amis = new ArrayList<Users>();
		for (Contact c : contact) {
		Integer id_contact = c.getId().getContactId();
		System.out.print("id du contact : " +id_contact);
			Users u = userRepository.findById(id_contact).get();
			System.out.println(" | prénom ami " + u.getUsersFirstName());
			list_amis.add(u);
	}

		model.addAttribute("mesamis", list_amis);
		model.addAttribute("mesgenres", genre);
		model.addAttribute("user", user1);
		model.addAttribute("liste",list_instru);
		
		return "profil";
	}

	

}
