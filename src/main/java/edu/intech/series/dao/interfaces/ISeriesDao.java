package edu.intech.series.dao.interfaces;

import edu.intech.series.dao.DaoException;
import edu.intech.series.model.Series;

import java.util.List;

public interface ISeriesDao {

    /**
     * Create a new Series
     *
     * @param series
     * @throws DaoException
     */
    void createSeries(Series series) throws DaoException;

    /**
     * Get a Series
     *
     * @param seriesId
     * @return Series
     * @throws DaoException
     */
    Series readSeries(int seriesId) throws DaoException;

    /**
     * Get all Series
     *
     * @return List Series
     * @throws DaoException
     */
    List<Series> readAllSeries() throws DaoException;

    /**
     * Update a Series
     *
     * @param series
     * @throws DaoException
     */
    void updateSeries(Series series) throws DaoException;

    /**
     * Delete a Series
     *
     * @param series
     * @throws DaoException
     */
    void deleteSeries(Series series) throws DaoException;
}
