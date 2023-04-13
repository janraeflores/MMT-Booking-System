package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

        System.out.println(request.getParameter("selected-date") + " is the selected date in doGet");

        if (request.getParameter("selected-date") != null) {
            request.setAttribute("selected-date", request.getParameter("selected-date"));
        }

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
            Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);

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

        String selectedDate = request.getParameter("selected-date");
        
        if(selectedDate == null || selectedDate == ""){
            request.setAttribute("emptyMessage", "You haven't chosen a day!");
        } else {
            request.setAttribute("emptyMessage", "");
        }

        try {
            request.setAttribute("appointment", apptserv.getAll());

            List<Appointment> appointments = apptserv.getAll();
            List<Appointment> dispAppoints = new ArrayList<>();

            //read through all appointments and display only the ones that occur on that date
            for (Appointment appt : appointments) {
                Date selDate = stringToDate(selectedDate);
                Date apptDate = dateToDate(appt.getAppointmentDate());

                if (apptDate.equals(selDate)) {
                    dispAppoints.add(appt);
                }
            }

            request.setAttribute("selected-date", selectedDate);
            request.setAttribute("dispAppoints", dispAppoints);

            getServletContext().getRequestDispatcher("/WEB-INF/AdminMain.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /**
     * Converts a selectedDate (string), that includes the date and time, as a
     * String to a Date object
     *
     * @param selectedDate
     * @return
     */
    private Date stringToDate(String selectedDate) {

        try {

            DateTimeFormatter selectedDateFormat = DateTimeFormatter.ofPattern("d MMMM, yyyy");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            LocalDateTime ldt = LocalDate.parse(selectedDate, selectedDateFormat).atStartOfDay();

            String formattedSelectedDate = ldt.format(dateFormat);

            Date date = Date.from(LocalDateTime.parse(formattedSelectedDate, dateFormat).atZone(ZoneId.systemDefault()).toInstant());

            return date;
        } catch (Exception ex) {
            Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    /**
     * Reformats a date object to be able to compare to other values regardless
     * of time
     *
     * @param date
     * @return
     */
    private Date dateToDate(Date date) {

        try {

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate().atStartOfDay();

            String formattedSelectedDate = ldt.format(dateFormat);

            Date dateUpdated = Date.from(LocalDateTime.parse(formattedSelectedDate, dateFormat).atZone(ZoneId.systemDefault()).toInstant());

            return dateUpdated;
        } catch (Exception ex) {
            Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

}
