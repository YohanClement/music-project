package fr.formation.inti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "postulations")
public class Postulations implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer postulationsId;

	private Users users;

	private Groupe groupe;

	private String statut;

	public Postulations() {
		// TODO Auto-generated constructor stub
	}

	public Postulations(Users user, Groupe groupe, String statut) {
		super();
		this.users = user;
		this.groupe = groupe;
		this.statut = statut;

	}

	public Postulations(Users user, Groupe groupe) {
		super();
		this.users = user;
		this.groupe = groupe;
		this.statut = "en attente";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "postulations_id")
	public Integer getPostulationId() {
		return postulationsId;
	}

	public void setPostulationId(Integer postulationId) {
		this.postulationsId = postulationId;
	}

	@ManyToOne
	@JoinColumn(name = "postulant_id")
	public Users getUser() {
		return users;
	}

	public void setUser(Users user) {
		this.users = user;
	}

	@ManyToOne
	@JoinColumn(name = "groupe_id")
	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	@Column(name = "statut")
	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	@Override
	public String toString() {
		return "Postulations [postulationId=" + postulationsId + ", user=" + users + ", groupe=" + groupe + ", statut="
				+ statut + "]";
	}

}