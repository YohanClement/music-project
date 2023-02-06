package fr.formation.inti;
// Generated 6 f�vr. 2023 � 12:03:42 by Hibernate Tools 5.1.12.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * GroupeMembers generated by hbm2java
 */
@Entity
@Table(name = "groupe_members", catalog = "bd_music_project")
public class GroupeMembers implements java.io.Serializable {

	private GroupeMembersId id;
	private Groupe groupe;
	private Users users;

	public GroupeMembers() {
	}

	public GroupeMembers(GroupeMembersId id, Groupe groupe, Users users) {
		this.id = id;
		this.groupe = groupe;
		this.users = users;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "groupeId", column = @Column(name = "Groupe_id", nullable = false)),
			@AttributeOverride(name = "usersMembers", column = @Column(name = "Users_members", nullable = false)) })
	public GroupeMembersId getId() {
		return this.id;
	}

	public void setId(GroupeMembersId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Groupe_id", nullable = false, insertable = false, updatable = false)
	public Groupe getGroupe() {
		return this.groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Users_members", nullable = false, insertable = false, updatable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
