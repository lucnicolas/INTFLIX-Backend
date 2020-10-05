package edu.intech.series.dao.interfaces;

import edu.intech.series.dao.DaoException;
import edu.intech.series.model.Season;

import java.util.List;

public interface ISeasonDao {

    /**
     * Create a new Season
     *
     * @param season
     * @throws DaoException
     */
    void createSeason(Season season) throws DaoException;

    /**
     * Get a Season
     *
     * @param seasonId
     * @return Season
     * @throws DaoException
     */
    Season readSeason(int seasonId) throws DaoException;

    /**
     * Get all Seasons
     *
     * @return List Season
     * @throws DaoException
     */
    List<Season> readAllSeasons() throws DaoException;

    /**
     * Update Season
     *
     * @param season
     * @throws DaoException
     */
    void updateSeason(Season season) throws DaoException;

    /**
     * Delete Season
     *
     * @param season
     * @throws DaoException
     */
    void deleteSeason(Season season) throws DaoException;

}
