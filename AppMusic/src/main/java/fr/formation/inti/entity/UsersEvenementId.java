package fr.formation.inti.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UsersEvenementId generated by hbm2java
 */
@Embeddable
public class UsersEvenementId implements java.io.Serializable{
	
	private int usersId;
	private int evenementId;

	public UsersEvenementId() {
	}

	public UsersEvenementId(int usersId, int evenementId) {
		this.usersId = usersId;
		this.evenementId = evenementId;
	}

	@Column(name = "Users_id", nullable = false)
	public int getUsersId() {
		return this.usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	@Column(name = "Evenement_id", nullable = false)
	public int getEvenementId() {
		return this.evenementId;
	}

	public void setEvenementId(int evenementId) {
		this.evenementId = evenementId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsersEvenementId))
			return false;
		UsersEvenementId castOther = (UsersEvenementId) other;

		return (this.getUsersId() == castOther.getUsersId()) && (this.getEvenementId() == castOther.getEvenementId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUsersId();
		result = 37 * result + this.getEvenementId();
		return result;
	}

}