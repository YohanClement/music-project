package fr.formation.inti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UsersInstruments generated by hbm2java
 */
@Entity
@Table(name = "users_instruments", catalog = "bd_music_project")
public class UsersInstruments implements java.io.Serializable{
	
	private Integer userInstrumentId;
	private Musicinstruments musicinstruments;
	private Users users;
	private Integer niveau;

	public UsersInstruments() {
	}

	public UsersInstruments(Integer userInstrumentId, Musicinstruments musicinstruments, Users users) {
		this.userInstrumentId = userInstrumentId;
		this.musicinstruments = musicinstruments;
		this.users = users;
	}

	public UsersInstruments(Users users, Musicinstruments musicinstruments,  Integer niveau) {
		super();
		this.users = users;
		this.musicinstruments = musicinstruments;
		this.niveau = niveau;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "users_instrument_id")
	public Integer getUserInstrumentId() {
		return userInstrumentId;
	}

	public void setUserInstrumentId(Integer userInstrumentId) {
		this.userInstrumentId = userInstrumentId;
	}

	@ManyToOne
	@JoinColumn(name = "Instr_id")
	public Musicinstruments getMusicinstruments() {
		return this.musicinstruments;
	}

	public void setMusicinstruments(Musicinstruments musicinstruments) {
		this.musicinstruments = musicinstruments;
	}

	@ManyToOne
	@JoinColumn(name="Users_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "Niveau")
	public Integer getNiveau() {
		return niveau;
	}

	public void setNiveau(Integer niveau) {
		this.niveau = niveau;
	}
	
	

}
