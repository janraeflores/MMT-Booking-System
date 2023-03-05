package servlets;

import java.io.IOException;
import java.time.LocalDate;
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
import models.Client;
import services.AccountService;
import services.AppointmentService;
import services.ClientService;
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
        ClientService cs = new ClientService();
        MassageService ms = new MassageService();
        
        try {
            Account account = as.get(username);
            Client client = cs.get(username);
            
            request.setAttribute("service", ms.getAll());
            request.setAttribute("account", account);
            request.setAttribute("client", client);
            
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
        ClientService cs = new ClientService();
        AppointmentService apptserv = new AppointmentService();
        MassageService ms = new MassageService();
       
        try {
            
            Client client = cs.get(username);
           
            int serviceType = Integer.parseInt(request.getParameter("s-type"));
            String serviceDuration = request.getParameter("s-duration"); 
            String selectedDate = request.getParameter("selected-date");
            String timeSlot = request.getParameter("t-slot");
            String address = request.getParameter("u-address");

            //Date startTime = null;
            //Date endTime = null;
            
            if (serviceType == 1 || timeSlot.equals("")) {
                // error message to select a service or timeslot
                return;
            }

            apptserv.insert(client, serviceType, address, convertToDateTime(selectedDate));

            // gets results of the appointment newly made 
            int length = apptserv.getAll(client.getClientId()).size();
            Appointment appt = apptserv.getAll(client.getClientId()).get(length - 1);
            
            request.setAttribute("appointment", appt);
            request.setAttribute("client", client);
            request.setAttribute("duration", serviceDuration);
            request.setAttribute("service", ms.getAll());
        } catch (Exception ex) {
            Logger.getLogger(ReservationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Reservation.jsp").forward(request, response);
    }
    /**
     * 
     * @param selectedDate
     * @return 
     */
    private Date convertToDateTime(String selectedDate) {
        
        // creates a formatter that matches 'selectedDate' format
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMMM, yyyy");
        
        
        // selected date is converted to 'yyyy-mm-dd' format
        LocalDate ld = LocalDate.parse(selectedDate, dateFormat);
        
        // converts LocalDate to Date
        Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        return date;
    }

}
