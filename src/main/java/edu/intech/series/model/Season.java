package edu.intech.series.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.intech.series.dao.DaoFactory;
import edu.intech.series.exception.SeriesException;

import javax.persistence.*;

@Entity
@Table(name = "Season")
@NamedQueries({
		@NamedQuery(name = "Saison.findAll", query = "SELECT s FROM Season s"),
		@NamedQuery(name = "Saison.findBySerie",
				query = "SELECT sais FROM Series ser, IN(ser.season) sais WHERE ser.id = :id")
})
public class Season {

	private int id = -1;
	private int number;
	private final String name;
	private boolean seen = false;
	private final Map<Integer, Episode> episodes = new HashMap<Integer, Episode>();

	/**
	 * @param name Season name.
	 */
	public Season(final String name, final int number) {
		this.name = name;
		this.number = number;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	public int getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the number
	 */
	@Column(name = "number")
	public int getNumber() {
		return this.number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(final int number) {
		this.number = number;
	}

	/**
	 * @return the name
	 */
	@Column(name = "name")
	public final String getName() {
		return this.name;
	}

	/**
	 * @return the season
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumns({
			@JoinColumn(name = "fk_series", referencedColumnName = "id")
	})
	public final Collection<Episode> getEpisodes() {
		return this.episodes.values();
	}

	/**
	 * This method adds given Episode to the list of Episodes.
	 *
	 * @param e The Episode to add. <b>note :</b>given Episode id is calculated
	 *          before storing. No need to set it before calling this method.
	 * @return the generated id for given Episode
	 * @throws SeriesException if a Episode already exist with same id.
	 */
	public int addEpisode(final Episode e) throws SeriesException {
		int nextId = DaoFactory.lastEpisodeId + 1;
		if (this.episodes.get(nextId) != null) {
			throw new SeriesException("An episode already exist with the id ".concat(Integer.toString(nextId)));
		}
		e.setId(++DaoFactory.lastEpisodeId);
		this.episodes.put(nextId, e);
		return nextId;
	}

	/**
	 * This method removes given Episode from the list of Episodes.
	 *
	 * @param e the Episode to remove.
	 * @throws SeriesException if given Episode doesn't exist in the list of
	 *                         Episodes.
	 */
	public void removeEpisode(final Episode e) throws SeriesException {
		if (this.episodes.get(e.getId()) == null) {
			throw new SeriesException("No episode found with the id ".concat(Integer.toString(e.getId())));
		}
		this.episodes.remove(e.getId());
	}

	/**
	 * @param seen the seen to set
	 */
	public final void setSeen(final boolean seen) {
		this.seen = seen;
	}

	public boolean isSeen() {
		this.seen = true;
		if (this.episodes.size() > 0) {
			for (Episode e : this.episodes.values()) {
				if (!e.isSeen()) {
					this.seen = false;
					return this.seen;
				}
			}
		}
		return this.seen;
	}

	/**
	 * This method return the Episode with given id.
	 *
	 * @param id Identifier of Episode to return
	 * @return the Episode with given id or <code>null</code> if no Episode exists
	 *         with given Id.
	 */
	public Episode getEpisodeById(final int id) {
		return this.episodes.get(id);
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Season)) {
			return false;
		}
		return ((Season) obj).getId() == getId();
	}

}
