package fr.formation.inti.entity;
// Generated 6 f�vr. 2023 � 12:03:42 by Hibernate Tools 5.1.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "bd_music_project", uniqueConstraints = @UniqueConstraint(columnNames = "Users_Email"))
public class Users implements java.io.Serializable {

	private Integer usersId;
	private String password;
	private String usersFirstName;
	private String usersLastName;
	private String usersAdress;
	private String usersEmail;
	private String usersBio;
	private String usersZip;
	private String usersCity;
	private Date usersDateCrea;
	private Integer usersLinkedAccountsNmbr;
	private String photos;
	private Set<UsersGenre> usersGenres = new HashSet<UsersGenre>(0);
	private Set<UsersEvenement> usersEvenements = new HashSet<UsersEvenement>(0);
	private Set<UsersInstruments> usersInstrumentses = new HashSet<UsersInstruments>(0);
	private Set<Evenement> evenements = new HashSet<Evenement>(0);
	private Set<Groupe> groupes = new HashSet<Groupe>(0);
	private Set<GroupeMembers> groupeMemberses = new HashSet<GroupeMembers>(0);

	public Users() {
	}

	public Users(String password, String usersFirstName, String usersLastName, String usersEmail, Date usersDateCrea) {
		this.password = password;
		this.usersFirstName = usersFirstName;
		this.usersLastName = usersLastName;
		this.usersEmail = usersEmail;
		this.usersDateCrea = usersDateCrea;
	}

	public Users(String password, String usersFirstName, String usersLastName, String usersAdress, String usersEmail,
			String usersBio, String usersZip, String usersCity, Date usersDateCrea, Integer usersLinkedAccountsNmbr,
			Set<UsersGenre> usersGenres, Set<UsersEvenement> usersEvenements, Set<UsersInstruments> usersInstrumentses,
			Set<Evenement> evenements, Set<Groupe> groupes, Set<GroupeMembers> groupeMemberses) {
		this.password = password;
		this.usersFirstName = usersFirstName;
		this.usersLastName = usersLastName;
		this.usersAdress = usersAdress;
		this.usersEmail = usersEmail;
		this.usersBio = usersBio;
		this.usersZip = usersZip;
		this.usersCity = usersCity;
		this.usersDateCrea = usersDateCrea;
		this.usersLinkedAccountsNmbr = usersLinkedAccountsNmbr;
		this.usersGenres = usersGenres;
		this.usersEvenements = usersEvenements;
		this.usersInstrumentses = usersInstrumentses;
		this.evenements = evenements;
		this.groupes = groupes;
		this.groupeMemberses = groupeMemberses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Users_id", unique = true, nullable = false)
	public Integer getUsersId() {
		return this.usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}

	@Column(name = "Password", nullable = false, length = 200)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Users_first_name", nullable = false, length = 500)
	public String getUsersFirstName() {
		return this.usersFirstName;
	}

	public void setUsersFirstName(String usersFirstName) {
		this.usersFirstName = usersFirstName;
	}

	@Column(name = "Users_last_name", nullable = false, length = 500)
	public String getUsersLastName() {
		return this.usersLastName;
	}

	public void setUsersLastName(String usersLastName) {
		this.usersLastName = usersLastName;
	}

	@Column(name = "Users_Adress", length = 1000)
	public String getUsersAdress() {
		return this.usersAdress;
	}

	public void setUsersAdress(String usersAdress) {
		this.usersAdress = usersAdress;
	}

	@Column(name = "Users_Email", unique = true, nullable = false, length = 500)
	public String getUsersEmail() {
		return this.usersEmail;
	}

	public void setUsersEmail(String usersEmail) {
		this.usersEmail = usersEmail;
	}

	@Column(name = "Users_bio", length = 2000)
	public String getUsersBio() {
		return this.usersBio;
	}

	public void setUsersBio(String usersBio) {
		this.usersBio = usersBio;
	}

	@Column(name = "Users_Zip", length = 50)
	public String getUsersZip() {
		return this.usersZip;
	}

	public void setUsersZip(String usersZip) {
		this.usersZip = usersZip;
	}

	@Column(name = "Users_City", length = 200)
	public String getUsersCity() {
		return this.usersCity;
	}

	public void setUsersCity(String usersCity) {
		this.usersCity = usersCity;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Users_date_crea", nullable = false, length = 19)
	public Date getUsersDateCrea() {
		return this.usersDateCrea;
	}

	public void setUsersDateCrea(Date usersDateCrea) {
		this.usersDateCrea = usersDateCrea;
	}

	@Column(name = "Users_Linked_Accounts_Nmbr")
	public Integer getUsersLinkedAccountsNmbr() {
		return this.usersLinkedAccountsNmbr;
	}

	public void setUsersLinkedAccountsNmbr(Integer usersLinkedAccountsNmbr) {
		this.usersLinkedAccountsNmbr = usersLinkedAccountsNmbr;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<UsersGenre> getUsersGenres() {
		return this.usersGenres;
	}

	public void setUsersGenres(Set<UsersGenre> usersGenres) {
		this.usersGenres = usersGenres;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<UsersEvenement> getUsersEvenements() {
		return this.usersEvenements;
	}

	public void setUsersEvenements(Set<UsersEvenement> usersEvenements) {
		this.usersEvenements = usersEvenements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<UsersInstruments> getUsersInstrumentses() {
		return this.usersInstrumentses;
	}

	public void setUsersInstrumentses(Set<UsersInstruments> usersInstrumentses) {
		this.usersInstrumentses = usersInstrumentses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Evenement> getEvenements() {
		return this.evenements;
	}

	public void setEvenements(Set<Evenement> evenements) {
		this.evenements = evenements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Groupe> getGroupes() {
		return this.groupes;
	}

	public void setGroupes(Set<Groupe> groupes) {
		this.groupes = groupes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<GroupeMembers> getGroupeMemberses() {
		return this.groupeMemberses;
	}

	public void setGroupeMemberses(Set<GroupeMembers> groupeMemberses) {
		this.groupeMemberses = groupeMemberses;
	}

	
	@Column(name = "photos", length = 45)
	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	@Transient
	public String getPhotosImagePath() {
		if (photos == null ||  usersId == null)
			return null;

		return "/user-photos/" +  usersId + "/" + photos;
	}


	
	
	
	
}
