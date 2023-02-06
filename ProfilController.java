package fr.formation.inti.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.inti.entity.GenreMusic;
import fr.formation.inti.entity.Musicinstruments;
import fr.formation.inti.entity.Users;
import fr.formation.inti.entity.UsersGenre;
import fr.formation.inti.entity.UsersInstruments;
import fr.formation.inti.repository.GenreMusicRepository;
import fr.formation.inti.repository.MusicinstrumentsRepository;
import fr.formation.inti.repository.UserRepository;

@Controller
public class ProfilController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GenreMusicRepository gmr;
	@Autowired
	private MusicinstrumentsRepository mir;
	
	
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
		
		
		
		
//		List<Musicinstruments> list_instrument = new ArrayList<Musicinstruments>();
//		List<Integer> list_niveau = new ArrayList<Integer>();
//		for (UsersInstruments i : instrument) {
//			Integer id2 = i.getId().getInstrId();
//			Integer niveau=i.getNiveau();
//			System.out.print("id instrument : " +id2);
//			System.out.println(" niveau : " + niveau);
//			list_niveau.add(niveau);
//			Musicinstruments instru = mir.findById(id2).get();
//			System.out.println("instrument : " + instru);
//			list_instrument.add(instru);
//		}
	
		model.addAttribute("mesgenres", genre);
//		model.addAttribute("instruments", list_instrument);
		
		
		model.addAttribute("user", user1);
		model.addAttribute("liste",list_instru);
		
		return "profil";
	}

	

}
