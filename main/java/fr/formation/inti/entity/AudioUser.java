package fr.formation.inti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * AudioUser generated by hbm2java
 */
@Entity
@Table(name = "audio_user", catalog = "bd_music_project")
public class AudioUser {

	private Integer audioId;
	private Users users;
	private String audioName;
	private String audioType;
	private String audioFormat;
	private String audioPath;

	public AudioUser() {
	}

	public AudioUser(Integer audioId, Users users) {
		this.audioId = audioId;
		this.users = users;
	}

	public AudioUser(Integer audioId, Users users, String audioName) {
		this.audioId = audioId;
		this.users = users;
		this.audioName = audioName;
	}

	public AudioUser(String audioName, String fileFormat, String fileType) {
		this.audioName = audioName;
		this.audioType = fileType;
		this.audioFormat = fileFormat;
	}

	@Id

	@Column(name = "Audio_id", unique = true, nullable = false)
	public Integer getAudioId() {
		return this.audioId;
	}

	public void setAudioId(Integer audioId) {
		this.audioId = audioId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Users_id", nullable = false, updatable=false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "Audio_name", length = 200)
	public String getAudioName() {
		return this.audioName;
	}

	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}

	@Column(name = "Audio_type", length = 45)
	public String getAudioType() {
		return audioType;
	}

	public void setAudioType(String audioType) {
		this.audioType = audioType;
	}

	@Column(name = "audio_format", length = 45)
	public String getAudioFormat() {
		return audioFormat;
	}

	@Column(name = "audio_path", length = 45)
	public void setAudioFormat(String audioFormat) {
		this.audioFormat = audioFormat;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}

	@Transient
	public String getAudioPath() {
		if (audioName == null || users == null)
			return null;

		return "/users-audio/" + users + "/" + audioName;
	}

}
