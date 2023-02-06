package fr.formation.inti.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	private Integer usersid;
	private String password;
	private String firstname;
	private String lastname;
	private String address;

	private String emailaddress;
	private String bio;
	private String zip;
	private String city;
	private LocalDateTime datecrea;
	private Integer userslinked;

	private Set<UserRoles> useroles = new HashSet<UserRoles>(0);

	public Users() {

	}
	public Users(Users user) {
		this.firstname = user.firstname;
		this.lastname = user.lastname;
		this.password =  user.password;
		this.zip= user.zip;
		this.emailaddress =  user.emailaddress;
		this.city= user.city;
		this.bio=user.bio;
		this.address=user.address;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(formatter.format(date));
		this.datecrea = now;
	}

	public Users(String firstname, String lastname, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(formatter.format(date));
		this.datecrea = now;
	}

	public Users(String firstname, String lastname, String password, String emailaddress) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.emailaddress = emailaddress;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(formatter.format(date));
		this.datecrea = now;
	}
	
	public Users(String firstname, String lastname, String password, String emailaddress, String city) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.emailaddress = emailaddress;
		this.city=city;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(formatter.format(date));
		this.datecrea = now;
	}
	public Users(String firstname, String lastname, String password, String emailaddress, String city, String bio, String zip) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.emailaddress = emailaddress;
		this.zip=zip;
		this.bio=bio;
		this.city=city;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(formatter.format(date));
		this.datecrea = now;
	}
	public Users(String firstname, String lastname, String password, String emailaddress, String city, String zip) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.zip=zip;
		this.emailaddress = emailaddress;
		this.city=city;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		// System.out.println(formatter.format(date));
		this.datecrea = now;
	}


	@Override
	public String toString() {
		return "Users [firstname=" + firstname + ", lastname=" + lastname + ", emailaddress=" + emailaddress
				+ ", useroles=" + useroles + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Users_id")
	public Integer getUsersid() {
		return usersid;
	}

	public void setUsersid(Integer usersid) {
		this.usersid = usersid;
	}

	@Column(name = "Password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Users_first_name")
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "Users_last_name")
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "Users_Adress")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "Users_email")
	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	@Column(name = "Users_bio")
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Column(name = "Users_Zip")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "Users_City")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "Users_date_crea")
	public LocalDateTime getDatecrea() {
		return datecrea;
	}

	public void setDatecrea(LocalDateTime datecrea) {
		this.datecrea = datecrea;
	}

	@Column(name = "Users_Linked_Accounts_Nmbr")
	public Integer getUserslinked() {
		return userslinked;
	}

	public void setUserslinked(Integer userslinked) {
		this.userslinked = userslinked;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
	public Set<UserRoles> getUseroles() {
		return useroles;
	}

	public void setUseroles(Set<UserRoles> useroles) {
		this.useroles = useroles;
	}

}
