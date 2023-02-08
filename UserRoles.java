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
@Table(name = "users_roles")
public class UserRoles {
	
private Integer Rolesid;
	
	private Users users;
	
	private String usersroles;
	
	private String email;



	public UserRoles() {
		// TODO Auto-generated constructor stub
	}

	public UserRoles(String usersroles, Users users, Integer Rolesid) {
		
		this.users = users;
		this.Rolesid = Rolesid;
		this.usersroles = usersroles;

	}
	public UserRoles(Users users, String roles, String email) {
		
		this.users = users;
		this.usersroles = roles;
		this.email = email;

	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Rolesid")
	public Integer getRolesid() {
		return Rolesid;
	}

	public void setRolesid(Integer rolesid) {
		Rolesid = rolesid;
	}

	@ManyToOne
	@JoinColumn(name="usersid")
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "USERS_ROLE")
	public String getUsersroles() {
		return usersroles;
	}

	public void setUsersroles(String usersroles) {
		this.usersroles = usersroles;
	}
	
	@Column(name = "Users_email")
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return usersroles;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
