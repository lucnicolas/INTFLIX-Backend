package edu.intech.series.servlet;

import edu.intech.series.exception.SeriesException;
import edu.intech.series.model.DataProvider;
import edu.intech.series.model.Episode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StateServlet", urlPatterns = "/etat/")
public class StateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataProvider data = DataProvider.getInstance();
        int serieId = Integer.parseInt(request.getParameter("serie"));
        int seasonId = Integer.parseInt(request.getParameter("saison"));
        int episodeId = Integer.parseInt(request.getParameter("episode"));
        try {
            Episode e = data.getEpisodeByIds(serieId, seasonId, episodeId);
            e.setSeen(!e.isSeen());
        } catch (SeriesException e) {
            request.setAttribute("erreur", e.getMessage());
            e.printStackTrace();
        }
        response.sendRedirect("../SeriesServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
