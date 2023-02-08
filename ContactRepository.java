package fr.formation.inti.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Contact;
import fr.formation.inti.entity.Users;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

//	@Query("SELECT c FROM Contact c WHERE c.user_id = :Users_id and  c.contact_id =:contact_id" )
//	List<Contact> findByusersByUsersIdAndusersByContactId (Users userSession, Users contact);

	
}
