package fr.formation.inti.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Groupe generated by hbm2java
 */
@Entity
@Table(name = "groupe", catalog = "bd_music_project", uniqueConstraints = @UniqueConstraint(columnNames = "Groupe_Email"))

public class Groupe implements java.io.Serializable{

	private Integer groupeId;
	private Users users;
	private String groupeName;
	private String groupeEmail;
	private String groupePassword;
	private String groupeFrequence;
	private Integer groupeMembersNmb;
	private String groupeIsRecruting;
	private String groupeDescription;
	private String audio; 
	private String photos;
	private Set<AudioGroupe> audioGroupes = new HashSet<AudioGroupe>(0);
	private Set<GroupeMembers> groupeMemberses = new HashSet<GroupeMembers>(0);
	private Set<GroupeEvenement> groupeEvenements = new HashSet<GroupeEvenement>(0);
	private Set<Evenement> evenements = new HashSet<Evenement>(0);
	private Set<GenreGroupe> genreGroupes = new HashSet<GenreGroupe>(0);

	public Groupe() {
	}

	public Groupe(String groupeName, String groupeEmail) {
		this.groupeName = groupeName;
		this.groupeEmail = groupeEmail;
	}

	public Groupe(Integer groupeId, Users users, String groupeName, String groupeEmail, String groupePassword,
			Integer groupeMembersNmb, String groupeIsRecruting, String groupeDescription, String groupeFrequence,
			Set<GroupeMembers> groupeMemberses, Set<GroupeEvenement> groupeEvenements, Set<Evenement> evenements,
			Set<GenreGroupe> genreGroupes) {
		super();
		this.groupeId = groupeId;
		this.users = users;
		this.groupeName = groupeName;
		this.groupeEmail = groupeEmail;
		this.groupePassword = groupePassword;
		this.groupeMembersNmb = groupeMembersNmb;
		this.groupeIsRecruting = groupeIsRecruting;
		this.groupeDescription = groupeDescription;
		this.groupeMemberses = groupeMemberses;
		this.groupeEvenements = groupeEvenements;
		this.evenements = evenements;
		this.genreGroupes = genreGroupes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Groupe_id", unique = true, nullable = false)
	public Integer getGroupeId() {
		return this.groupeId;
	}

	public void setGroupeId(Integer groupeId) {
		this.groupeId = groupeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Creator_id", updatable=false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "Groupe_name", nullable = false, length = 500)
	public String getGroupeName() {
		return this.groupeName;
	}

	public void setGroupeName(String groupeName) {
		this.groupeName = groupeName;
	}

	@Column(name = "Groupe_Email", unique = true, nullable = false, length = 500)
	public String getGroupeEmail() {
		return this.groupeEmail;
	}

	public void setGroupeEmail(String groupeEmail) {
		this.groupeEmail = groupeEmail;
	}
	
	@Column(name = "Groupe_password", nullable = false, length = 500)
	public String getGroupePassword() {
		return groupePassword;
	}

	public void setGroupePassword(String groupePassword) {
		this.groupePassword = groupePassword;
	}

	@Column(name = "Groupe_description", length = 2000)
	public String getGroupeDescription() {
		return groupeDescription;
	}

	public void setGroupeDescription(String groupeDescription) {
		this.groupeDescription = groupeDescription;
	}

	@Column(name = "Groupe_frequence", length = 100)
	public String getGroupeFrequence() {
		return groupeFrequence;
	}

	public void setGroupeFrequence(String groupeFrequence) {
		this.groupeFrequence = groupeFrequence;
	}

	@Column(name = "photos")
	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
	@Column(name = "audio")	
	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	@Transient
	public String getPhotosImagePath() {
		if (photos == null ||  groupeId == null)
			return null;

		return "/groupe-photos/" +  groupeId + "/" + photos;
	}
	
	@Transient
	public String getAudioPath() {
		if (audio == null ||  groupeId == null)
			return null;

		return "/groupe-audio/" +  groupeId + "/" + audio;
	}

	@Column(name = "Groupe_members_nmb")
	public Integer getGroupeMembersNmb() {
		return this.groupeMembersNmb;
	}

	public void setGroupeMembersNmb(Integer groupeMembersNmb) {
		this.groupeMembersNmb = groupeMembersNmb;
	}

	@Column(name = "Groupe_isrecruting")
	public String getGroupeIsRecruting() {
		return this.groupeIsRecruting;
	}

	public void setGroupeIsRecruting(String groupeIsRecruting) {
		this.groupeIsRecruting = groupeIsRecruting;
	}

//	@Column(name = "Groupe_typemusique")
//	public String getGroupeTypeMusique() {
//		return groupeTypeMusique;
//	}
//
//	public void setGroupeTypeMusique(String groupeTypeMusique) {
//		this.groupeTypeMusique = groupeTypeMusique;
//	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groupe")
	public Set<GroupeMembers> getGroupeMemberses() {
		return this.groupeMemberses;
	}

	public void setGroupeMemberses(Set<GroupeMembers> groupeMemberses) {
		this.groupeMemberses = groupeMemberses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groupe")
	public Set<GroupeEvenement> getGroupeEvenements() {
		return this.groupeEvenements;
	}

	public void setGroupeEvenements(Set<GroupeEvenement> groupeEvenements) {
		this.groupeEvenements = groupeEvenements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groupe")
	public Set<Evenement> getEvenements() {
		return this.evenements;
	}

	public void setEvenements(Set<Evenement> evenements) {
		this.evenements = evenements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groupe")
	public Set<GenreGroupe> getGenreGroupes() {
		return this.genreGroupes;
	}

	public void setGenreGroupes(Set<GenreGroupe> genreGroupes) {
		this.genreGroupes = genreGroupes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groupe")
	public Set<AudioGroupe> getAudioGroupes() {
		return audioGroupes;
	}

	public void setAudioGroupes(Set<AudioGroupe> audioGroupes) {
		this.audioGroupes = audioGroupes;
	}
	
	
}
