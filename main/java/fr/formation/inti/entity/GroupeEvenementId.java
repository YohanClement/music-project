package fr.formation.inti.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GroupeEvenementId generated by hbm2java
 */
@Embeddable

public class GroupeEvenementId implements java.io.Serializable{
	
	private Integer groupeId;
	private Integer evenementId;

	public GroupeEvenementId() {
	}

	public GroupeEvenementId(Integer groupeId, Integer evenementId) {
		this.groupeId = groupeId;
		this.evenementId = evenementId;
	}

	@Column(name = "Groupe_id", nullable = false)
	public Integer getGroupeId() {
		return this.groupeId;
	}

	public void setGroupeId(Integer groupeId) {
		this.groupeId = groupeId;
	}

	@Column(name = "Evenement_id", nullable = false)
	public Integer getEvenementId() {
		return this.evenementId;
	}

	public void setEvenementId(Integer evenementId) {
		this.evenementId = evenementId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GroupeEvenementId))
			return false;
		GroupeEvenementId castOther = (GroupeEvenementId) other;

		return (this.getGroupeId() == castOther.getGroupeId()) && (this.getEvenementId() == castOther.getEvenementId());
	}

	public int hashCode() {
		Integer result = 17;

		result = 37 * result + this.getGroupeId();
		result = 37 * result + this.getEvenementId();
		return result;
	}


}