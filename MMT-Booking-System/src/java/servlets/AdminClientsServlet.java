package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Account;
import services.AccountService;
import services.AppointmentService;
import services.EmergencyContactService;

/**
 *
 * @author User
 */
public class AdminClientsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("display", false);

        AccountService as = new AccountService();
        AppointmentService apptserv = new AppointmentService();
        EmergencyContactService ecs = new EmergencyContactService();

        String clientUsername = request.getParameter("username");
        String action = request.getParameter("action");
        action = action == null ? "" : action;

        try {
            request.setAttribute("accounts", as.getAllActive());

        } catch (Exception ex) {
            request.setAttribute("display", false);
            Logger.getLogger(AdminClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            switch (action) {
                case "display":
                    request.setAttribute("display", true);
                    request.setAttribute("account", as.get(clientUsername));
                    request.setAttribute("emergencyContacts", ecs.getAll(clientUsername));
                    request.setAttribute("appointments", apptserv.getAll(clientUsername));
                    
                    break;
                case "deactivate":
                    clientUsername = request.getParameter("username");
                    String clientName = as.get(clientUsername).getFullName();

                    as.deactivate(clientUsername);

                    request.setAttribute("display", false);
                    request.setAttribute("showDeletedMessage", true);
                    request.setAttribute("deletedMessage", clientName + "'s account has been deactivated");
                    request.setAttribute("accounts", as.getAllActive());

                    break;
                case "delete":
                    int appointmentId = Integer.parseInt(request.getParameter("appointment_id"));
                    clientUsername = request.getParameter("username");
                    apptserv.delete(appointmentId);

                    response.sendRedirect(request.getContextPath() + "/clients?action=display&username=" + clientUsername);
                    return;
            }
             
        } catch (Exception ex) {
            Logger.getLogger(AdminClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/AdminClients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AccountService as = new AccountService();
        AppointmentService apptserv = new AppointmentService();
        EmergencyContactService ecs = new EmergencyContactService();

        String action = request.getParameter("action");

        String clientUsername = request.getParameter("username");

        try {
            if (action.equalsIgnoreCase("update")) {
                Account selectedAccount = as.get(clientUsername);
                String clientName = request.getParameter("name");
                String clientPhone = request.getParameter("phone");
                String clientEmail = request.getParameter("email");
                String clientAddress = request.getParameter("address");

                if (!isEmpty(new String[]{clientName, clientPhone, clientEmail, clientAddress})) {
                    as.update(clientUsername, clientName, clientEmail, clientPhone, clientAddress);
                } else {
                    request.setAttribute("errorMessage", "Fields cannot be empty");
                }

                request.setAttribute("display", true);
                request.setAttribute("account", as.get(clientUsername));
                request.setAttribute("emergencyContacts", ecs.getAll(clientUsername));
                request.setAttribute("appointments", apptserv.getAll(selectedAccount.getUsername()));
//                getServletContext().getRequestDispatcher("/WEB-INF/AdminClients.jsp").forward(request, response);

            }

        } catch (Exception ex) {
            Logger.getLogger(AdminClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Account selectedAccount = as.get(clientUsername);
            request.setAttribute("account", selectedAccount);
            request.setAttribute("accounts", as.getAllActive());

        } catch (Exception ex) {
            Logger.getLogger(AdminClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("display", true);
        getServletContext().getRequestDispatcher("/WEB-INF/AdminClients.jsp").forward(request, response);
    }

    /**
     * Tests an array of input fields if an input is empty or null
     *
     * @param input as an array of inputs
     * @return true if any of the fields contained in the array are empty,
     * otherwise, returns false
     */
    public static boolean isEmpty(String[] input) {
        for (String s : input) {
            if (s.equals("") || s == null) {
                return true;
            }
        }
        return false;
    }
}
