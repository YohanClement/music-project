package fr.formation.inti;
// Generated 6 f�vr. 2023 � 12:03:42 by Hibernate Tools 5.1.12.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GenreGroupeId generated by hbm2java
 */
@Embeddable
public class GenreGroupeId implements java.io.Serializable {

	private int groupeId;
	private int genreId;

	public GenreGroupeId() {
	}

	public GenreGroupeId(int groupeId, int genreId) {
		this.groupeId = groupeId;
		this.genreId = genreId;
	}

	@Column(name = "Groupe_id", nullable = false)
	public int getGroupeId() {
		return this.groupeId;
	}

	public void setGroupeId(int groupeId) {
		this.groupeId = groupeId;
	}

	@Column(name = "Genre_id", nullable = false)
	public int getGenreId() {
		return this.genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GenreGroupeId))
			return false;
		GenreGroupeId castOther = (GenreGroupeId) other;

		return (this.getGroupeId() == castOther.getGroupeId()) && (this.getGenreId() == castOther.getGenreId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getGroupeId();
		result = 37 * result + this.getGenreId();
		return result;
	}

}
