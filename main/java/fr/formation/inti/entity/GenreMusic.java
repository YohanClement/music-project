package fr.formation.inti.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * GenreMusic generated by hbm2java
 */
@Entity
@Table(name = "genre_music", catalog = "bd_music_project", uniqueConstraints = @UniqueConstraint(columnNames = "Genre_name"))
public class GenreMusic implements java.io.Serializable{

	private Integer genreId;
	private GenreMusic genreMusic;
	private String genreName;
	private Set<GenreMusic> genreMusics = new HashSet<GenreMusic>(0);
	private Set<Evenement> evenements = new HashSet<Evenement>(0);
	private Set<UsersGenre> usersGenres = new HashSet<UsersGenre>(0);
	private Set<GenreGroupe> genreGroupes = new HashSet<GenreGroupe>(0);

	public GenreMusic() {
	}

	public GenreMusic(GenreMusic genreMusic, String genreName, Set<GenreMusic> genreMusics, Set<Evenement> evenements,
			Set<UsersGenre> usersGenres, Set<GenreGroupe> genreGroupes) {
		this.genreMusic = genreMusic;
		this.genreName = genreName;
		this.genreMusics = genreMusics;
		this.evenements = evenements;
		this.usersGenres = usersGenres;
		this.genreGroupes = genreGroupes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Genre_id", unique = true, nullable = false)
	public Integer getGenreId() {
		return this.genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Genre_parent", updatable=false)
	public GenreMusic getGenreMusic() {
		return this.genreMusic;
	}

	public void setGenreMusic(GenreMusic genreMusic) {
		this.genreMusic = genreMusic;
	}

	@Column(name = "Genre_name", unique = true, length = 200)
	public String getGenreName() {
		return this.genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genreMusic")
	public Set<GenreMusic> getGenreMusics() {
		return this.genreMusics;
	}

	public void setGenreMusics(Set<GenreMusic> genreMusics) {
		this.genreMusics = genreMusics;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genreMusic")
	public Set<Evenement> getEvenements() {
		return this.evenements;
	}

	public void setEvenements(Set<Evenement> evenements) {
		this.evenements = evenements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genreMusic")
	public Set<UsersGenre> getUsersGenres() {
		return this.usersGenres;
	}

	public void setUsersGenres(Set<UsersGenre> usersGenres) {
		this.usersGenres = usersGenres;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genreMusic")
	public Set<GenreGroupe> getGenreGroupes() {
		return this.genreGroupes;
	}

	public void setGenreGroupes(Set<GenreGroupe> genreGroupes) {
		this.genreGroupes = genreGroupes;
	}
}
