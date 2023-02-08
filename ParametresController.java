package fr.formation.inti.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
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
import fr.formation.inti.repository.GenreMusicRepository;
import fr.formation.inti.repository.GroupeRepository;
import fr.formation.inti.repository.UserRepository;

@Controller
public class ParametresController {

	@Autowired
	private UserRepository ur;

	@Autowired
	private GroupeRepository gr;

	@Autowired
	private GenreMusicRepository gmr;

	@GetMapping("/parametres")
	public String param() {
		return "parametres";
	}

	@GetMapping("/delete")
	public String suppr(Principal pr) {
		ur.deleteById(ur.findByEmail(pr.getName()).getUsersId());
		return "index";
	}

}
