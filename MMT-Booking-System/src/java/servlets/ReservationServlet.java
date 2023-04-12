package servlets;

import java.io.IOException;
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
import services.MassageService;

/**
 *
 * @author Keith
 */
public class ReservationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            request.setAttribute("message", "Please log in to make a reservation.");

            getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        }

        AccountService as = new AccountService();
        MassageService ms = new MassageService();

        try {
            request.setAttribute("service", ms.getAll());
            request.setAttribute("account", account);

        } catch (Exception ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Reservation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        AccountService as = new AccountService();
        AppointmentService apptserv = new AppointmentService();
        MassageService ms = new MassageService();

        try {
            request.setAttribute("account", as.get(account.getUsername()));
            request.setAttribute("service", ms.getAll());

            int serviceType = Integer.parseInt(request.getParameter("s-type"));
            int serviceDuration = Integer.parseInt(request.getParameter("s-duration"));
            String selectedDate = request.getParameter("selected-date");
            String timeSlot = request.getParameter("t-slot");
            String address = request.getParameter("u-address");
            String additionalInfo = request.getParameter("med-concerns");

            additionalInfo = additionalInfo == null ? "N/A" : additionalInfo;

            selectedDate = selectedDate.trim();
            String appointmentDate = selectedDate + " " + timeSlot;

            if (selectedDate.equals("") || selectedDate == null) {
                request.setAttribute("message", "Please select a date.");
                getServletContext().getRequestDispatcher("/WEB-INF/Reservation.jsp").forward(request, response);
                return;
            }

            if (serviceType == 0) {
                if (serviceDuration == 0) {
                    request.setAttribute("message", "Please select a massage type and duration.");
                    getServletContext().getRequestDispatcher("/WEB-INF/Reservation.jsp").forward(request, response);
                    return;
                }
                request.setAttribute("message", "Please select a massage type.");
                getServletContext().getRequestDispatcher("/WEB-INF/Reservation.jsp").forward(request, response);
                return;
            }

            //Checks if appointment timeslot is available
            List<Appointment> appointments = apptserv.getAll();

            for (Appointment appt : appointments) {

                Date date = convertToDate(appointmentDate);

                if (date.compareTo(appt.getAppointmentDate()) == 0) {
                    request.setAttribute("message", "Time slot already taken, please select a different time.");
                    getServletContext().getRequestDispatcher("/WEB-INF/Reservation.jsp").forward(request, response);
                    return;
                }
            }

            apptserv.insert(serviceType, account, address, convertToDate(appointmentDate), serviceDuration, additionalInfo);

            if (serviceDuration == 120) {
                Date date = convertToDate(appointmentDate);

                Date secondHour = addOneHour(date);

                apptserv.insert(serviceType, account, address, secondHour, (serviceDuration - 60), additionalInfo);
            }

            response.sendRedirect("booking");
        } catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/Reservation.jsp").forward(request, response);

            Logger.getLogger(ReservationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Converts a selectedDate, that includes the date and time, as a String to
     * a Date object
     *
     * @param selectedDate
     * @return
     */
    private Date convertToDate(String selectedDate) {

        DateTimeFormatter selectedDateFormat = DateTimeFormatter.ofPattern("d MMMM, yyyy h:mm a");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime ldt = LocalDateTime.parse(selectedDate, selectedDateFormat);

        String formattedSelectedDate = ldt.format(dateFormat);

        Date date = Date.from(LocalDateTime.parse(formattedSelectedDate, dateFormat).atZone(ZoneId.systemDefault()).toInstant());

        return date;
    }

    /**
     * Adds an hour to an appointment time
     */
    private Date addOneHour(Date date) {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        LocalDateTime updatedLdt = ldt.plusHours(1);

        String formattedDt = updatedLdt.format(dateFormat);

        LocalDateTime parsedDt = LocalDateTime.parse(formattedDt, dateFormat);

        Date updatedDate = Date.from(parsedDt.atZone(ZoneId.systemDefault()).toInstant());

        return updatedDate;
    }
}
