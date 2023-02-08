package fr.formation.inti.entity;
// Generated 7 f�vr. 2023 � 14:53:51 by Hibernate Tools 5.1.12.Final

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
 * Contact generated by hbm2java
 */
@Entity
@Table(name = "contact", catalog = "bd_music_project")
public class Contact implements java.io.Serializable {

	private ContactId id;
	private Users usersByUsersId;
	private Users usersByContactId;

	public Contact() {
	}

	public Contact(ContactId id, Users usersByUsersId, Users usersByContactId) {
		this.id = id;
		this.usersByUsersId = usersByUsersId;
		this.usersByContactId = usersByContactId;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "usersId", column = @Column(name = "Users_id", nullable = false)),
			@AttributeOverride(name = "contactId", column = @Column(name = "contact_id", nullable = false)) })
	public ContactId getId() {
		return this.id;
	}

	public void setId(ContactId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Users_id", nullable = false, insertable = false, updatable = false)
	public Users getUsersByUsersId() {
		return this.usersByUsersId;
	}

	public void setUsersByUsersId(Users usersByUsersId) {
		this.usersByUsersId = usersByUsersId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id", nullable = false, insertable = false, updatable = false)
	public Users getUsersByContactId() {
		return this.usersByContactId;
	}

	public void setUsersByContactId(Users usersByContactId) {
		this.usersByContactId = usersByContactId;
	}

}
