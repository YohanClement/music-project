package fr.formation.inti.entity;

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
 * GenreGroupe generated by hbm2java
 */
@Entity
@Table(name = "genre_groupe", catalog = "bd_music_project")

public class GenreGroupe implements java.io.Serializable{
	
	private GenreGroupeId id;
	private GenreMusic genreMusic;
	private Groupe groupe;

	public GenreGroupe() {
	}

	public GenreGroupe(GenreGroupeId id, GenreMusic genreMusic, Groupe groupe) {
		this.id = id;
		this.genreMusic = genreMusic;
		this.groupe = groupe;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "groupeId", column = @Column(name = "Groupe_id", nullable = false)),
			@AttributeOverride(name = "genreId", column = @Column(name = "Genre_id", nullable = false)) })
	public GenreGroupeId getId() {
		return this.id;
	}

	public void setId(GenreGroupeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Genre_id", nullable = false, insertable = false, updatable=false)
	public GenreMusic getGenreMusic() {
		return this.genreMusic;
	}

	public void setGenreMusic(GenreMusic genreMusic) {
		this.genreMusic = genreMusic;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Groupe_id", nullable = false, insertable = false, updatable=false)
	public Groupe getGroupe() {
		return this.groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

}
