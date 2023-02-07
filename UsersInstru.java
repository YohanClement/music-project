package fr.formation.inti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_instruments")
public class UsersInstru {

	
	private Users user;
	private musicinstruments musicinstr;
	private Integer niveau;
	private Integer usersinstrid;
	
	
	
	
	
	

	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "users_instrument_id")
	public Integer getUsersinstrid() {
		return usersinstrid;
	}
	public void setUsersinstrid(Integer usersinstrid) {
		this.usersinstrid = usersinstrid;
	}
	@Override
	public String toString() {
		return "UsersInstru [user=" + user + ", musicinstr=" + musicinstr + ", niveau=" + niveau + ", usersinstrid="
				+ usersinstrid + "]";
	}
	public UsersInstru() {
		// TODO Auto-generated constructor stub
	}
	public UsersInstru(Users user, musicinstruments musicinstr, Integer niveau) {
		super();
		this.user = user;
		this.musicinstr = musicinstr;
		this.niveau = niveau;
	}
	@ManyToOne
	@JoinColumn(name="Users_id")
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name="Instr_id")
	public musicinstruments getMusicinstr() {
		return musicinstr;
	}
	public void setMusicinstr(musicinstruments musicinstr) {
		this.musicinstr = musicinstr;
	}
	
	@Column(name = "Niveau")
	public Integer getNiveau() {
		return niveau;
	}
	public void setNiveau(Integer niveau) {
		this.niveau = niveau;
	} 
	
	
}
