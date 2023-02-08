package fr.formation.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.inti.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}