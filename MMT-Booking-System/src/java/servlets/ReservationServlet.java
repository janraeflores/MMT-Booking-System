package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
        String username = (String) session.getAttribute("username");
        
        AccountService as = new AccountService();
        MassageService ms = new MassageService();
        
        try {
            Account account = as.get(username);
            
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
        String username = (String) session.getAttribute("username");
            
        AccountService as = new AccountService();
        AppointmentService apptserv = new AppointmentService();
        MassageService ms = new MassageService();
       
        try {
            Account account = as.get(username);
            
            request.setAttribute("account", account);
            request.setAttribute("service", ms.getAll());
            
            int serviceType = Integer.parseInt(request.getParameter("s-type"));
            int serviceDuration = Integer.parseInt(request.getParameter("s-duration")); 
            String selectedDate = request.getParameter("selected-date");
            String timeSlot = request.getParameter("t-slot");
            String address = request.getParameter("u-address");
            
            String appointmentDate = selectedDate + " " + timeSlot;
            
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
            
            apptserv.insert(serviceType, account, address, convertToDate(appointmentDate), serviceDuration);

            // gets results of the appointment newly made 
            int length = apptserv.getAll(username).size();
            Appointment appt = apptserv.getAll(username).get(length - 1);
            
            request.setAttribute("appointment", appt);
            request.setAttribute("duration", serviceDuration);
            
        } catch (Exception ex) {
            Logger.getLogger(ReservationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("booking");
        
    }
    /**
     * Converts a selectedDate, that includes the day and time, as a String to a Date object
     * @param selectedDate
     * @return 
     */
    private Date convertToDate(String selectedDate) {
        
        // creates a formatter that matches 'selectedDate' format
        DateTimeFormatter selectedDateFormat = DateTimeFormatter.ofPattern("d MMMM, yyyy h:mm a");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        // selected date is converted to '' format
        LocalDateTime ldt = LocalDateTime.parse(selectedDate, selectedDateFormat);
        
        String formattedSelectedDate = ldt.format(dateFormat);
        
        // converts LocalDate to Date
        Date date = Date.from(LocalDateTime.parse(formattedSelectedDate, dateFormat).atZone(ZoneId.systemDefault()).toInstant());
        
        return date;
    }
}
