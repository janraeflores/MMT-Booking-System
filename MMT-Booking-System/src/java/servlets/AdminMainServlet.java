package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Appointment;
import services.AccountService;
import services.AppointmentService;

/**
 *
 * @author Taburada
 */
public class AdminMainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        AppointmentService apptserv = new AppointmentService();
        String action = request.getParameter("action");
        action = action == null ? "" : action;

        try {
            request.setAttribute("appointment", apptserv.getAll());
        } catch (Exception ex) {
            Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //take appointment id and turn it into an int so it can be compared
        String appointIdString = request.getParameter("appointId");
        int appointId = 0;
        try {
            appointId = Integer.parseInt(appointIdString);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        try {
            //display appointments according to appointment id
            Appointment selectedAppointment = apptserv.get(appointId);

            session.setAttribute("displayAppoints", true);
            request.setAttribute("appointment", selectedAppointment);
            request.setAttribute("action", null);

            request.setAttribute("appointment", apptserv.getAll());
            getServletContext().getRequestDispatcher("/WEB-INF/AdminMain.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);

        }

        getServletContext().getRequestDispatcher("/WEB-INF/AdminMain.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
