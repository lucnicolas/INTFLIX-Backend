package edu.intech.series.servlet.listeners;

import edu.intech.series.dao.DaoFactory;
import edu.intech.series.dao.exception.DaoException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SeriesContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            DaoFactory.getInstance().closeAll();
        } catch (DaoException e) {
            // Nothing to do
        }
    }
}
