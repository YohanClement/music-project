package fr.formation.inti;
// Generated 6 f�vr. 2023 � 12:03:42 by Hibernate Tools 5.1.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Evenement generated by hbm2java
 */
@Entity
@Table(name = "evenement", catalog = "bd_music_project")
public class Evenement implements java.io.Serializable {

	private int evenementId;
	private GenreMusic genreMusic;
	private Groupe groupe;
	private Users users;
	private String evenementName;
	private String evenementBio;
	private String evenementAdress;
	private String evenementZip;
	private String evenementCity;
	private Date evenementDate;
	private Set<UsersEvenement> usersEvenements = new HashSet<UsersEvenement>(0);
	private Set<GroupeEvenement> groupeEvenements = new HashSet<GroupeEvenement>(0);

	public Evenement() {
	}

	public Evenement(int evenementId, GenreMusic genreMusic, Groupe groupe, Users users, String evenementName,
			String evenementAdress, String evenementZip, String evenementCity, Date evenementDate) {
		this.evenementId = evenementId;
		this.genreMusic = genreMusic;
		this.groupe = groupe;
		this.users = users;
		this.evenementName = evenementName;
		this.evenementAdress = evenementAdress;
		this.evenementZip = evenementZip;
		this.evenementCity = evenementCity;
		this.evenementDate = evenementDate;
	}

	public Evenement(int evenementId, GenreMusic genreMusic, Groupe groupe, Users users, String evenementName,
			String evenementBio, String evenementAdress, String evenementZip, String evenementCity, Date evenementDate,
			Set<UsersEvenement> usersEvenements, Set<GroupeEvenement> groupeEvenements) {
		this.evenementId = evenementId;
		this.genreMusic = genreMusic;
		this.groupe = groupe;
		this.users = users;
		this.evenementName = evenementName;
		this.evenementBio = evenementBio;
		this.evenementAdress = evenementAdress;
		this.evenementZip = evenementZip;
		this.evenementCity = evenementCity;
		this.evenementDate = evenementDate;
		this.usersEvenements = usersEvenements;
		this.groupeEvenements = groupeEvenements;
	}

	@Id

	@Column(name = "Evenement_id", unique = true, nullable = false)
	public int getEvenementId() {
		return this.evenementId;
	}

	public void setEvenementId(int evenementId) {
		this.evenementId = evenementId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Evenement_genre", nullable = false)
	public GenreMusic getGenreMusic() {
		return this.genreMusic;
	}

	public void setGenreMusic(GenreMusic genreMusic) {
		this.genreMusic = genreMusic;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Groupe_crea", nullable = false)
	public Groupe getGroupe() {
		return this.groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Users_crea", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "Evenement_name", nullable = false, length = 500)
	public String getEvenementName() {
		return this.evenementName;
	}

	public void setEvenementName(String evenementName) {
		this.evenementName = evenementName;
	}

	@Column(name = "Evenement_bio", length = 2000)
	public String getEvenementBio() {
		return this.evenementBio;
	}

	public void setEvenementBio(String evenementBio) {
		this.evenementBio = evenementBio;
	}

	@Column(name = "Evenement_Adress", nullable = false, length = 1000)
	public String getEvenementAdress() {
		return this.evenementAdress;
	}

	public void setEvenementAdress(String evenementAdress) {
		this.evenementAdress = evenementAdress;
	}

	@Column(name = "Evenement_Zip", nullable = false, length = 50)
	public String getEvenementZip() {
		return this.evenementZip;
	}

	public void setEvenementZip(String evenementZip) {
		this.evenementZip = evenementZip;
	}

	@Column(name = "Evenement_City", nullable = false, length = 200)
	public String getEvenementCity() {
		return this.evenementCity;
	}

	public void setEvenementCity(String evenementCity) {
		this.evenementCity = evenementCity;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Evenement_date", nullable = false, length = 19)
	public Date getEvenementDate() {
		return this.evenementDate;
	}

	public void setEvenementDate(Date evenementDate) {
		this.evenementDate = evenementDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "evenement")
	public Set<UsersEvenement> getUsersEvenements() {
		return this.usersEvenements;
	}

	public void setUsersEvenements(Set<UsersEvenement> usersEvenements) {
		this.usersEvenements = usersEvenements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "evenement")
	public Set<GroupeEvenement> getGroupeEvenements() {
		return this.groupeEvenements;
	}

	public void setGroupeEvenements(Set<GroupeEvenement> groupeEvenements) {
		this.groupeEvenements = groupeEvenements;
	}

}
