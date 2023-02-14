package fr.formation.inti.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UsersGenreId generated by hbm2java
 */
@Embeddable
public class UsersGenreId implements java.io.Serializable{
	
	private Integer usersId;
	private Integer genreId;

	public UsersGenreId() {
	}

	public UsersGenreId(Integer usersId, Integer genreId) {
		this.usersId = usersId;
		this.genreId = genreId;
	}

	@Column(name = "Users_id", nullable = false)
	public Integer getUsersId() {
		return this.usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}

	@Column(name = "Genre_id", nullable = false)
	public Integer getGenreId() {
		return this.genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsersGenreId))
			return false;
		UsersGenreId castOther = (UsersGenreId) other;

		return (this.getUsersId() == castOther.getUsersId()) && (this.getGenreId() == castOther.getGenreId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUsersId();
		result = 37 * result + this.getGenreId();
		return result;
	}

}