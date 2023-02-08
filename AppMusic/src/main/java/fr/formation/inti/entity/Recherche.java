package fr.formation.inti.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recherche", catalog = "bd_music_project")
public class Recherche {
	
	private String evenementtype;
	private String evenementgenre;
	private String evenementnom;
	private String evenementcity;
	private String numerorecherche;
	
	
	public Recherche() {
		// TODO Auto-generated constructor stub
	}
	
	@Column(name = "evenement_type")
	public String getEvenementtype() {
		return evenementtype;
	}
	public void setEvenementtype(String evenementtype) {
		this.evenementtype = evenementtype;
	}
	
	@Column(name = "evenement_genre")
	public String getEvenementgenre() {
		return evenementgenre;
	}
	public void setEvenementgenre(String evenementgenre) {
		this.evenementgenre = evenementgenre;
	}
	
	@Column(name = "evenement_nom")
	public String getEvenementnom() {
		return evenementnom;
	}
	public void setEvenementnom(String evenementnom) {
		this.evenementnom = evenementnom;
	}
	
	@Column(name = "evenement_city")
	public String getEvenementcity() {
		return evenementcity;
	}
	public void setEvenementcity(String evenementcity) {
		this.evenementcity = evenementcity;
	}
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "numerorecherche", unique = true, nullable = false)
	public String getNumerorecherche() {
		return numerorecherche;
	}
	public void setNumerorecherche(String numerorecherche) {
		this.numerorecherche = numerorecherche;
	}
	
	
	@Override
	public String toString() {
		return "Recherche [evenementtype=" + evenementtype + ", evenementgenre=" + evenementgenre + ", evenementnom="
				+ evenementnom + ", evenementcity=" + evenementcity + "]";
	}
	
	
	
	
	
	
	

}
