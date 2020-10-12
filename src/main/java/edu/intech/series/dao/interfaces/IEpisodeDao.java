package edu.intech.series.dao.interfaces;

import edu.intech.series.dao.exception.DaoException;
import edu.intech.series.model.Episode;

import java.util.List;

public interface IEpisodeDao {

    /**
     * Create an Episode
     *
     * @param episode
     * @throws DaoException
     */
    void createEpisode(Episode episode) throws DaoException;

    /**
     * Get an Episode
     *
     * @param episodeId
     * @return Episode
     * @throws DaoException
     */
    Episode readEpisode(int episodeId) throws DaoException;

    /**
     * Get all Episode
     *
     * @return List Episode
     * @throws DaoException
     */
    List<Episode> readAllEpisode() throws DaoException;

    /**
     * Update an Episode
     *
     * @param episode
     * @throws DaoException
     */
    void updateEpisode(Episode episode) throws DaoException;

    /**
     * Delete an Episode
     *
     * @param episode
     * @throws DaoException
     */
    void deleteEpisode(Episode episode) throws DaoException;

}
