package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
        }

        try {
            //display appointments according to appointment id
            Appointment selectedAppointment = apptserv.get(appointId);

            session.setAttribute("displayAppoints", true);
            request.setAttribute("appointment", selectedAppointment);

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

        HttpSession session = request.getSession();
        AppointmentService apptserv = new AppointmentService();
        DateFormat df = new SimpleDateFormat();

        try {
            request.setAttribute("appointment", apptserv.getAll());
            
            List<Appointment> appointments = apptserv.getAll();
            
            //idea is to read through all the appointments and see which ones match w the selected one (will have to do date formatting) and then push that as the appointment request attribute
            for(Appointment appt: appointments){
                
            }

            String selectedDate = request.getParameter("selected-date");
            String appointmentDate = request.getParameter("appointmentDate");

            System.out.println(selectedDate);
            System.out.println(appointmentDate);

            String date = convertToDate(selectedDate);
//            System.out.println(date);

            request.setAttribute("appointmentDay", date);
            request.setAttribute("appointmentDate", appointmentDate);

            getServletContext().getRequestDispatcher("/WEB-INF/AdminMain.jsp").forward(request, response);

        } catch (Exception ex) {

        }

    }

    private String convertToDate(String selectedDate) {

        try {

            DateTimeFormatter selectedDateFormat = DateTimeFormatter.ofPattern("d MMMM, yyyy");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

            LocalDate ld = LocalDate.parse(selectedDate, selectedDateFormat);

            String formattedSelectedDate = ld.format(dateFormat);

            return formattedSelectedDate;
        } catch (Exception ex) {
            Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

}
