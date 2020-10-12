package edu.intech.series.dao.impl.db;

import edu.intech.series.dao.interfaces.ISeasonDao;
import edu.intech.series.model.Season;

import java.sql.Connection;
import java.util.List;

public class SeasonDaoImplDb implements ISeasonDao {

    private Connection connection;

    public SeasonDaoImplDb(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createSeason(Season season) {

    }

    @Override
    public Season readSeason(int seasonId) {
        return null;
    }

    @Override
    public List<Season> readAllSeasons() {
        return null;
    }

    @Override
    public void updateSeason(Season season) {

    }

    @Override
    public void deleteSeason(Season season) {

    }
}
