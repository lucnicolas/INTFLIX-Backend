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

	private final Map<Integer, Series> seriesList;

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
	 * @return the seriesList
	 */
	public Collection<Series> getAllSeries() {
		return this.seriesList.values();
	}

	/**
	 * This method adds given Series to the list of series.
	 *
	 * @param s The series to add. <b>note :</b>given series id is calculated before
	 *          storing. No need to set it befor calling this method.
	 * @return the generated id for given Series
	 * @throws SeriesException if a Series already exist with same id.
	 */
	public int addSeries(final Series s) throws SeriesException {
		int nextId = lastSerieId + 1;
		if (this.seriesList.get(nextId) != null) {
			throw new SeriesException("A series already exist with the id ".concat(Integer.toString(nextId)));
		}
		s.setId(++lastSerieId);
		this.seriesList.put(nextId, s);
		return nextId;
	}

	/**
	 * This method removes given Series from the list of series.
	 *
	 * @param s the Serie to remove.
	 * @throws SeriesException if given Series doesn't exist in the list of series.
	 */
	public void removeSeries(final Series s) throws SeriesException {
		if (this.seriesList.get(s.getId()) == null) {
			throw new SeriesException("No series found with the id ".concat(Integer.toString(s.getId())));
		}
		this.seriesList.remove(s.getId());
	}

	/**
	 * This method return the Series with given id.
	 *
	 * @param serieId Identifier of Series to return
	 * @return the Serie with given id.
	 * @throws SeriesException if no Series exists with given id in the list of
	 *                         series.
	 */
	public Series getSeriesById(final int serieId) throws SeriesException {
		Series ret = this.seriesList.get(serieId);
		if (ret == null) {
			throw new SeriesException("No series found with id ".concat(Integer.toString(serieId)));
		}
		return ret;
	}

	/**
	 * This method returns the Season corresponding with given Ids
	 *
	 * @param seriesId  Identifier of the Series to search for the wanted Saison
	 * @param seasonId Identifier of the Season to find
	 * @return Saison corresponding to given ids.
	 * @throws SeriesException if
	 *                         <ul>
	 *                         <li>either no Series has been found with given
	 *                         ID.</li>
	 *                         <li>or the Series has been found but doesn't contain a
	 *                         Season with given ID.</li>
	 *                         </ul>
	 */
	public Season getSeasonByIds(final int seriesId, final int seasonId) throws SeriesException {
		Series s = this.seriesList.get(seriesId);
		if (s == null) {
			throw new SeriesException("No series found with id ".concat(Integer.toString(seriesId)));
		}
		Season ret = s.getSeasonById(seasonId);
		if (ret == null) {
			throw new SeriesException("No season found with id".concat(Integer.toString(seasonId))
					.concat(" in the series with id ").concat(Integer.toString(seriesId)));
		}
		return ret;
	}

	/**
	 * This method returns the Episode corresponding with given Ids
	 *
	 * @param seriesId   Identifier of the Serie to search for the wanted Episode
	 * @param seasonId  Identifier of the Saison to search for the wanted Episode
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
	public Episode getEpisodeByIds(final int seriesId, final int seasonId, final int episodeId) throws SeriesException {
		Series s = this.seriesList.get(seriesId);
		if (s == null) {
			throw new SeriesException("No series found with id ".concat(Integer.toString(seriesId)));
		}
		Season s2 = s.getSeasonById(seasonId);
		if (s2 == null) {
			throw new SeriesException("No season found with id ".concat(Integer.toString(seasonId))
					.concat(" in series found with id ").concat(Integer.toString(seriesId)));
		}
		Episode e = s2.getEpisodeById(episodeId);
		if (e == null) {
			throw new SeriesException("No episode found with id ".concat(Integer.toString(episodeId))
					.concat(" in season with id ").concat(Integer.toString(seasonId))
					.concat(" in series with id ").concat(Integer.toString(seriesId)));
		}
		return e;
	}

	/**
	 * private constructor
	 */
	private DataProvider() {
		this.seriesList = new HashMap<Integer, Series>();
		initData();
	}

	private void initData() {
		Series got = new Series("Game of Thrones");
		Season got1 = new Season("Season one", 1);
		Episode got11 = new Episode("Winter is coming", 1);
		Episode got12 = new Episode("The Kingsroad", 2);
		Episode got13 = new Episode("Lord Snow", 3);
		Episode got14 = new Episode("Cripples, Bastards, and Broken Things", 4);
		Episode got15 = new Episode("The Wolf and the Lion", 5);
		Episode got16 = new Episode("A Golden Crown", 6);
		Episode got17 = new Episode("You Win or You Die", 7);
		Episode got18 = new Episode("The Pointy End", 8);
		Season got2 = new Season("Season two", 2);
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
			got.addSeason(got1);
			got2.addEpisode(got21);
			got2.addEpisode(got22);
			got2.addEpisode(got23);
			got2.addEpisode(got24);
			got2.addEpisode(got25);
			got2.addEpisode(got26);
			got2.addEpisode(got27);
			got2.addEpisode(got28);
			got.addSeason(got2);
			addSeries(got);

		} catch (SeriesException e) {
			e.printStackTrace();
		}
		Series tgp = new Series("The Good Place");
		Season tgp1 = new Season("Season one", 1);
		Episode tgp11 = new Episode("Pilot", 1);
		Episode tgp12 = new Episode("Flying", 2);
		Episode tgp13 = new Episode("Tahani Al-Jamil", 3);
		Episode tgp14 = new Episode("Jason Mendoza", 4);
		Episode tgp15 = new Episode("Category 55 Emergency Doomsday Crisis", 5);
		Episode tgp16 = new Episode("What We Owe to Each Other", 6);
		Episode tgp17 = new Episode("The Eternal Shriek", 7);
		Episode tgp18 = new Episode("Most Improved Player", 8);
		Season tgp2 = new Season("Season two", 2);
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
			tgp.addSeason(tgp1);
			tgp2.addEpisode(tgp21);
			tgp2.addEpisode(tgp22);
			tgp2.addEpisode(tgp23);
			tgp2.addEpisode(tgp24);
			tgp2.addEpisode(tgp25);
			tgp2.addEpisode(tgp26);
			tgp2.addEpisode(tgp27);
			tgp2.addEpisode(tgp28);
			tgp.addSeason(tgp2);
			addSeries(tgp);

		} catch (SeriesException e) {
			e.printStackTrace();
		}

	}
}
