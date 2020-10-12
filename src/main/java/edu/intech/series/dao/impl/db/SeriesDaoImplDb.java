package edu.intech.series.dao.impl.db;

import edu.intech.series.dao.DaoFactory;
import edu.intech.series.dao.exception.DaoException;
import edu.intech.series.dao.interfaces.ISeriesDao;
import edu.intech.series.model.Series;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeriesDaoImplDb implements ISeriesDao {

    private static final String SQL_SEL_ALL_SERIES = "SELECT * FROM Serie";
    private static final String COL_NAME = "nom";
    private final Connection connection;
    private final DaoFactory factory;

    public SeriesDaoImplDb(Connection connection, DaoFactory factory) {
        this.connection = connection;
        this.factory = factory;
    }

    @Override
    public void createSeries(Series series) {

    }

    @Override
    public Series readSeries(int seriesId) {
        return null;
    }

    @Override
    public List<Series> readAllSeries() {
        List<Series> listSeries = new ArrayList<Series>();
        ResultSet result = null;
        try {
            PreparedStatement selAllSeries;
            selAllSeries = this.factory.getPreparedStatement(SQL_SEL_ALL_SERIES);
            result = selAllSeries.executeQuery();
            while (result.next()) {
                Series series = new Series(result.getString(COL_NAME));
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot read all series", e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    // Nothing to do
                }
            }
        }
        return listSeries;
    }

    @Override
    public void updateSeries(Series series) {

    }

    @Override
    public void deleteSeries(Series series) {

    }
}
