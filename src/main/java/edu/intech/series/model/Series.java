package edu.intech.series.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.intech.series.exception.SeriesException;

public class Series {

	private int id = -1;
	private final String name;
	private boolean seen = false;
	private final Map<Integer, Season> season = new HashMap<Integer, Season>();

	/**
	 * @param nom Nom de la série.
	 */
	public Series(final String nom) {
		this.name = nom;
	}

	/**
	 * @return the id
	 */
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
	 * @return the nom
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * @return the saisons
	 */
	public final Collection<Season> getSeason() {
		return this.season.values();
	}

	/**
	 * This method adds given Season to the list of Seasons.
	 *
	 * @param s The Season to add. <b>note :</b>given Season id is calculated before
	 *          storing. No need to set it before calling this method.
	 * @return the generated id for given Season
	 * @throws SeriesException if a Season already exist with same id.
	 */
	public int addSeason(final Season s) throws SeriesException {
		int nextId = DataProvider.lastSaisonId + 1;
		if (this.season.get(nextId) != null) {
			throw new SeriesException("A season already exists with the id ".concat(Integer.toString(nextId)));
		}
		s.setId(++DataProvider.lastSaisonId);
		this.season.put(nextId, s);
		return nextId;
	}

	/**
	 * This method removes given Season from the list of Seasons.
	 *
	 * @param s the Season to remove.
	 * @throws SeriesException if given Season doesn't exist in the list of Saisons.
	 */
	public void removeSeason(final Season s) throws SeriesException {
		if (this.season.get(s.getId()) == null) {
			throw new SeriesException("No season has been found with the id ".concat(Integer.toString(s.getId())));
		}
		this.season.remove(s.getId());
	}

	/**
	 * @param seen the toutVu to set
	 */
	public final void setSeen(final boolean seen) {
		this.seen = seen;
	}

	/**
	 *
	 * This method returns the visualization state of the Serie.
	 *
	 * @return <code>true</code> if all Seasons of the Series have been fully
	 *         watched, <code>false</code> otherwise.
	 */
	public boolean isSeen() {
		this.seen = true;
		if (this.season.size() > 0) {
			for (Season s : this.season.values()) {
				if (!s.isSeen()) {
					this.seen = false;
					return this.seen;
				}
			}
		}
		return this.seen;
	}

	/**
	 * This method return the Season with given id.
	 *
	 * @param id Identifier of Season to return
	 * @return the Season with given id or <code>null</code> if no Season exists
	 *         with given Id.
	 */
	public Season getSeasonById(final int id) {
		return this.season.get(id);
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
		if (!(obj instanceof Series)) {
			return false;
		}
		return ((Series) obj).getId() == getId();
	}

}
