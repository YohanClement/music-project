package fr.formation.inti.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.inti.model.UserRoles;
import fr.formation.inti.model.Users;
import fr.formation.inti.repository.UserRolesDAO;
import fr.formation.inti.repository.UsersDAO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersDAO userRepo;
	
	@Autowired
	private UserRolesDAO roleRepo;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByEmail(username);
		//System.out.println("UserInfo= " + user);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		System.out.println("Found User: " + user);
		Set<UserRoles> roles = user.getUseroles(); // usersDAO.getUserRoles(email);

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

		if (roles != null) {
			for (UserRoles r : roles) {
				// ROLE_USER, ROLE_ADMIN,..
				
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r.getUsersroles().toUpperCase());
				grantList.add(authority);
			}}
//		} else {
//			 Set<UserRoles> roleuser = new HashSet<UserRoles>();
//			 UserRoles userrole = new UserRoles(user, "USER", user.getEmailaddress());
//			 roleuser.add(userrole);
//			user.setUseroles(roleuser);
//			userRepo.saveAndFlush(user);
//			System.out.println("pas de roles user apres update : " + user);
//			System.out.println("pas de roles userrole apres update : " + userrole);
//			System.out.println("pas de roles roleuser apres update : " + roleuser);
//			//roleRepo.save(userrole);
//			
//		}
		System.out.println(grantList.toString());

		UserDetails userDetails = (UserDetails) new User(user.getEmailaddress(), user.getPassword(), grantList);
		System.out.println(userDetails);
		return userDetails;
	}

}