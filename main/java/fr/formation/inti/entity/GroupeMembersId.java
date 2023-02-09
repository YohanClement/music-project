package fr.formation.inti.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GroupeMembersId generated by hbm2java
 */
@Embeddable
public class GroupeMembersId implements java.io.Serializable {
	
	private Integer groupeId;
	private Integer usersMembers;

	public GroupeMembersId() {
	}

	public GroupeMembersId(Integer groupeId, Integer usersMembers) {
		this.groupeId = groupeId;
		this.usersMembers = usersMembers;
	}

	@Column(name = "Groupe_id", nullable = false)
	public Integer getGroupeId() {
		return this.groupeId;
	}

	public void setGroupeId(Integer groupeId) {
		this.groupeId = groupeId;
	}

	@Column(name = "Users_members", nullable = false)
	public Integer getUsersMembers() {
		return this.usersMembers;
	}

	public void setUsersMembers(Integer usersMembers) {
		this.usersMembers = usersMembers;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GroupeMembersId))
			return false;
		GroupeMembersId castOther = (GroupeMembersId) other;

		return (this.getGroupeId() == castOther.getGroupeId())
				&& (this.getUsersMembers() == castOther.getUsersMembers());
	}

	public int hashCode() {
		Integer result = 17;

		result = 37 * result + this.getGroupeId();
		result = 37 * result + this.getUsersMembers();
		return result;
	}

}
