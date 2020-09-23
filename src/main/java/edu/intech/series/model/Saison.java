package edu.intech.series.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.intech.series.exception.SeriesException;

public class Saison {

	private int id = -1;
	private int numero;
	private final String nom;
	private boolean toutVu = false;
	private final Map<Integer, Episode> episodes = new HashMap<Integer, Episode>();

	/**
	 * @param nom Nom de la saison.
	 */
	public Saison(final String nom, final int numero) {
		this.nom = nom;
		this.numero = numero;
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
	 * @return the numero
	 */
	public int getNumero() {
		return this.numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(final int numero) {
		this.numero = numero;
	}

	/**
	 * @return the nom
	 */
	public final String getNom() {
		return this.nom;
	}

	/**
	 * @return the saisons
	 */
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
		int nextId = DataProvider.lastEpisodeId + 1;
		if (this.episodes.get(nextId) != null) {
			throw new SeriesException("Un épisode existe déjà avec l'identifiant ".concat(Integer.toString(nextId)));
		}
		e.setId(++DataProvider.lastEpisodeId);
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
			throw new SeriesException("Aucun épisode trouvée avec l'identifiant ".concat(Integer.toString(e.getId())));
		}
		this.episodes.remove(e.getId());
	}

	/**
	 * @param toutVu the toutVu to set
	 */
	public final void setToutVu(final boolean toutVu) {
		this.toutVu = toutVu;
	}

	public boolean isToutVu() {
		this.toutVu = true;
		if (this.episodes.size() > 0) {
			for (Episode e : this.episodes.values()) {
				if (!e.isVu()) {
					this.toutVu = false;
					return this.toutVu;
				}
			}
		}
		return this.toutVu;
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
		return getNom();
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Saison)) {
			return false;
		}
		return ((Saison) obj).getId() == getId();
	}

}
