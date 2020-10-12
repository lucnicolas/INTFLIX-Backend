package edu.intech.series.dao.impl.db;

import edu.intech.series.dao.interfaces.IEpisodeDao;
import edu.intech.series.model.Episode;

import java.sql.Connection;
import java.util.List;

public class EpisodeDaoImplDb implements IEpisodeDao {

    private Connection connection;

    public EpisodeDaoImplDb(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createEpisode(Episode episode) {

    }

    @Override
    public Episode readEpisode(int episodeId) {
        return null;
    }

    @Override
    public List<Episode> readAllEpisode() {
        return null;
    }

    @Override
    public void updateEpisode(Episode episode) {

    }

    @Override
    public void deleteEpisode(Episode episode) {

    }
}
