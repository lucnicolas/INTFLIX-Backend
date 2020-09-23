package edu.intech.series.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.intech.series.exception.SeriesException;

public class DataProvider {

	private static DataProvider instance;
	static int lastSerieId = 0;
	static int lastSaisonId = 0;
	static int lastEpisodeId = 0;

	private final Map<Integer, Serie> listeSeries;

	/**
	 * Returns the instance of this singleton.
	 *
	 * @return the instance of this singleton.
	 *
	 */
	public static final DataProvider getInstance() {
		if (instance == null) {
			instance = new DataProvider();
		}
		return instance;
	}

	/**
	 * @return the listeSeries
	 */
	public Collection<Serie> getAllSeries() {
		return this.listeSeries.values();
	}

	/**
	 * This method adds given Serie to the list of series.
	 *
	 * @param s The serie to add. <b>note :</b>given serie id is calculated before
	 *          storing. No need to set it befor calling this method.
	 * @return the generated id for given Serie
	 * @throws SeriesException if a Serie already exist with same id.
	 */
	public int addSerie(final Serie s) throws SeriesException {
		int nextId = lastSerieId + 1;
		if (this.listeSeries.get(nextId) != null) {
			throw new SeriesException("Une série existe déjà avec l'identifiant ".concat(Integer.toString(nextId)));
		}
		s.setId(++lastSerieId);
		this.listeSeries.put(nextId, s);
		return nextId;
	}

	/**
	 * This method removes given Serie from the list of series.
	 *
	 * @param s the Serie to remove.
	 * @throws SeriesException if given Serie doesn't exist in the list of series.
	 */
	public void removeSerie(final Serie s) throws SeriesException {
		if (this.listeSeries.get(s.getId()) == null) {
			throw new SeriesException("Aucune série trouvée avec l'identifiant ".concat(Integer.toString(s.getId())));
		}
		this.listeSeries.remove(s.getId());
	}

	/**
	 * This method return the Serie with given id.
	 *
	 * @param serieId Identifier of Serie to return
	 * @return the Serie with given id.
	 * @throws SeriesException if no Serie exists with given id in the list of
	 *                         series.
	 */
	public Serie getSerieById(final int serieId) throws SeriesException {
		Serie ret = this.listeSeries.get(serieId);
		if (ret == null) {
			throw new SeriesException("Aucune série trouvée avec l'identifiant ".concat(Integer.toString(serieId)));
		}
		return ret;
	}

	/**
	 * This method returns the Saison corresponding with given Ids
	 *
	 * @param serieId  Identifier of the Serie to search for the wanted Saison
	 * @param saisonId Identifier of the Saison to find
	 * @return Saison corresponding to given ids.
	 * @throws SeriesException if
	 *                         <ul>
	 *                         <li>either no Serie has been found with given
	 *                         ID.</li>
	 *                         <li>or the Serie has been found but doesn't contain a
	 *                         Saison with given ID.</li>
	 *                         </ul>
	 */
	public Saison getSaisonByIds(final int serieId, final int saisonId) throws SeriesException {
		Serie s = this.listeSeries.get(serieId);
		if (s == null) {
			throw new SeriesException("Aucune série trouvée avec l'identifiant ".concat(Integer.toString(serieId)));
		}
		Saison ret = s.getSaisonById(saisonId);
		if (ret == null) {
			throw new SeriesException("Aucune saison trouvée avec l'identifiant ".concat(Integer.toString(saisonId))
					.concat(" dans la série ayant l'identifiant ").concat(Integer.toString(serieId)));
		}
		return ret;
	}

	/**
	 * This method returns the Episode corresponding with given Ids
	 *
	 * @param serieId   Identifier of the Serie to search for the wanted Episode
	 * @param saisonId  Identifier of the Saison to search for the wanted Episode
	 * @param episodeId Identifier of the Episode to find
	 * @return Episode corresponding to given ids.
	 * @throws SeriesException if
	 *                         <ul>
	 *                         <li>either no Serie has been found with given
	 *                         ID.</li>
	 *                         <li>or the Serie has been found but doesn't contain a
	 *                         Saison with given ID.</li>
	 *                         <li>or the Serie and Saison have been found but
	 *                         Saison doesn't contain a Episode with given ID.</li>
	 *                         </ul>
	 */
	public Episode getEpisodeByIds(final int serieId, final int saisonId, final int episodeId) throws SeriesException {
		Serie s = this.listeSeries.get(serieId);
		if (s == null) {
			throw new SeriesException("Aucune série trouvée avec l'identifiant ".concat(Integer.toString(serieId)));
		}
		Saison s2 = s.getSaisonById(saisonId);
		if (s2 == null) {
			throw new SeriesException("Aucune saison trouvée avec l'identifiant ".concat(Integer.toString(saisonId))
					.concat(" dans la série ayant l'identifiant ").concat(Integer.toString(serieId)));
		}
		Episode e = s2.getEpisodeById(episodeId);
		if (e == null) {
			throw new SeriesException("Aucun épisode trouvé avec l'identifiant ".concat(Integer.toString(episodeId))
					.concat(" dans la saison ayant l'identifiant ").concat(Integer.toString(saisonId))
					.concat(" de la série ayant l'identifiant ").concat(Integer.toString(serieId)));
		}
		return e;
	}

	/**
	 * private constructor
	 */
	private DataProvider() {
		this.listeSeries = new HashMap<Integer, Serie>();
		initData();
	}

	private void initData() {
		Serie got = new Serie("Game of Thrones");
		Saison got1 = new Saison("Season one", 1);
		Episode got11 = new Episode("Winter is coming", 1);
		Episode got12 = new Episode("The Kingsroad", 2);
		Episode got13 = new Episode("Lord Snow", 3);
		Episode got14 = new Episode("Cripples, Bastards, and Broken Things", 4);
		Episode got15 = new Episode("The Wolf and the Lion", 5);
		Episode got16 = new Episode("A Golden Crown", 6);
		Episode got17 = new Episode("You Win or You Die", 7);
		Episode got18 = new Episode("The Pointy End", 8);
		Saison got2 = new Saison("Season two", 2);
		Episode got21 = new Episode("The North Remembers", 1);
		Episode got22 = new Episode("The Night Lands", 2);
		Episode got23 = new Episode("What Is Dead May Never Die", 3);
		Episode got24 = new Episode("Garden of Bones", 4);
		Episode got25 = new Episode("The Ghost of Harrenhal", 5);
		Episode got26 = new Episode("The Old Gods and the New", 6);
		Episode got27 = new Episode("A Man Without Honor", 7);
		Episode got28 = new Episode("The Prince of Winterfell", 8);
		try {
			got1.addEpisode(got11);
			got1.addEpisode(got12);
			got1.addEpisode(got13);
			got1.addEpisode(got14);
			got1.addEpisode(got15);
			got1.addEpisode(got16);
			got1.addEpisode(got17);
			got1.addEpisode(got18);
			got.addSaison(got1);
			got2.addEpisode(got21);
			got2.addEpisode(got22);
			got2.addEpisode(got23);
			got2.addEpisode(got24);
			got2.addEpisode(got25);
			got2.addEpisode(got26);
			got2.addEpisode(got27);
			got2.addEpisode(got28);
			got.addSaison(got2);
			addSerie(got);

		} catch (SeriesException e) {
			e.printStackTrace();
		}
		Serie tgp = new Serie("The Good Place");
		Saison tgp1 = new Saison("Season one", 1);
		Episode tgp11 = new Episode("Pilot", 1);
		Episode tgp12 = new Episode("Flying", 2);
		Episode tgp13 = new Episode("Tahani Al-Jamil", 3);
		Episode tgp14 = new Episode("Jason Mendoza", 4);
		Episode tgp15 = new Episode("Category 55 Emergency Doomsday Crisis", 5);
		Episode tgp16 = new Episode("What We Owe to Each Other", 6);
		Episode tgp17 = new Episode("The Eternal Shriek", 7);
		Episode tgp18 = new Episode("Most Improved Player", 8);
		Saison tgp2 = new Saison("Season two", 2);
		Episode tgp21 = new Episode("Everything Is Great!", 1);
		Episode tgp22 = new Episode("Dance Dance Resolution", 2);
		Episode tgp23 = new Episode("Team Cockroach", 3);
		Episode tgp24 = new Episode("Existential Crisis", 4);
		Episode tgp25 = new Episode("The Trolley Problem", 5);
		Episode tgp26 = new Episode("Janet and Michael", 6);
		Episode tgp27 = new Episode("Derek", 7);
		Episode tgp28 = new Episode("Leap to Faith", 8);
		try {
			tgp1.addEpisode(tgp11);
			tgp1.addEpisode(tgp12);
			tgp1.addEpisode(tgp13);
			tgp1.addEpisode(tgp14);
			tgp1.addEpisode(tgp15);
			tgp1.addEpisode(tgp16);
			tgp1.addEpisode(tgp17);
			tgp1.addEpisode(tgp18);
			tgp.addSaison(tgp1);
			tgp2.addEpisode(tgp21);
			tgp2.addEpisode(tgp22);
			tgp2.addEpisode(tgp23);
			tgp2.addEpisode(tgp24);
			tgp2.addEpisode(tgp25);
			tgp2.addEpisode(tgp26);
			tgp2.addEpisode(tgp27);
			tgp2.addEpisode(tgp28);
			tgp.addSaison(tgp2);
			addSerie(tgp);

		} catch (SeriesException e) {
			e.printStackTrace();
		}

	}
}
