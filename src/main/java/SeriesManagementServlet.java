import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SeriesManagementServlet")
public class SeriesManagementServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        request.setAttribute("TestString", "VarChar");
        request.setAttribute("TestInt", 666);
        request.setAttribute("TestObject", new ServletException());
        this.getServletContext().getRequestDispatcher("/seriesManagement.jsp").forward(request, response);
    }
}
