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
 * @author Keith
 */
public class BookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        AccountService as = new AccountService();
        AppointmentService apptserv = new AppointmentService();

        String username = account.getUsername();
        

        String action = request.getParameter("action");
        action = action == null ? "" : action;
        
        try {
            request.setAttribute("account", as.get(username));
            request.setAttribute("appointment", apptserv.getAll(username));
        } catch (Exception ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        try {
            String appointmentId = request.getParameter("appointment_id");
            if (action.equalsIgnoreCase("cancel")) {
                apptserv.cancel(Integer.parseInt(appointmentId));
                Appointment appt = apptserv.get(Integer.parseInt(appointmentId));
                request.setAttribute("message", "Your appointment at " + appt.getAppointmentDate() + " has been cancelled.");
                request.setAttribute("appointment", apptserv.getAll(username));
                request.setAttribute("account", as.get(username));
           
            }
            
        } catch (Exception ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Bookings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
