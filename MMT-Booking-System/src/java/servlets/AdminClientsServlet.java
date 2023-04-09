package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Account;
import models.EmergencyContact;
import services.AccountService;
import services.AppointmentService;
import services.EmergencyContactService;
import services.Validate;

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
        
        String clientUsername = request.getParameter("username");
        String action = request.getParameter("action");
        action = action == null ? "" : action;
        
        try {
            request.setAttribute("accounts", as.getAll());

        } catch (Exception ex) {
            Logger.getLogger(AdminClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
//          
            switch (action) {
                case "display":
                    Account selectedAccount = as.get(clientUsername);
                    request.setAttribute("display", true);
                    request.setAttribute("account", selectedAccount);
                    request.setAttribute("appointments", apptserv.getAll(selectedAccount.getUsername()));
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        getServletContext().getRequestDispatcher("/WEB-INF/AdminClients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            //        getServletContext().getRequestDispatcher("/WEB-INF/AdminClients.jsp").forward(request, response);
            
            AccountService as = new AccountService();
            EmergencyContactService ecs = new EmergencyContactService();
            
            String action = request.getParameter("action");
            action = action == null ? "" : action;
            
            String clientName = request.getParameter("name");
            String clientPhone = request.getParameter("phone");
            String clientEmail = request.getParameter("email");
            String clientAddress = request.getParameter("address");
            String clientBirthday = request.getParameter("birthday");
            
            String ecName = request.getParameter("ec_name");
            String ecRelation = request.getParameter("ec_relation");
            String ecPhone = request.getParameter("ec_phone");
            String ecEmail = request.getParameter("ec_email");
            
            String clientUsername = request.getParameter("username");
            Account clientAccount = as.get(clientUsername);
            
//            EmergencyContact ec = as.get(clientUsername).getEcContact();
            
            switch (action) {
                case "edit":
                    if (!Validate.isEmpty(new String[]{clientName, clientPhone, clientEmail, clientAddress})) {
                        try {
//                            if (ec == null) {
//                                ecs.insert(clientAccount, ecName, ecPhone, ecEmail, ecRelation);
//                            } else {
//                                ecs.update(clientAccount, ecName, ecPhone, ecEmail, ecRelation);
//                            }
                            
//                            as.update(clientUsername, clientName, clientEmail, clientPhone, convertBirthday(clientBirthday), clientAddress, ec);
                            
                            request.setAttribute("message", "Client information has been successfully saved.");
                            request.setAttribute("account", as.get(clientUsername));
                            
                            getServletContext().getRequestDispatcher("/WEB-INF/AdminClients.jsp").forward(request, response);
                            
                        } catch (Exception ex) {
                            Logger.getLogger(AdminClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        request.setAttribute("errorMessage", "Fields cannot be empty.");
                        getServletContext().getRequestDispatcher("/WEB-INF/AdminClients.jsp").forward(request, response);
                    }
                    break;
                case "delete":
                    try {
                        as.delete(clientUsername);
                        
                        List<Account> accounts = (List<Account>) request.getSession().getAttribute("accounts");
                        accounts.remove(clientAccount);
                        
                        request.setAttribute("display", false);
                        request.setAttribute("accounts", accounts);
                        request.setAttribute("deletedMessage", clientName + " has been deleted.");
                        getServletContext().getRequestDispatcher("/WEB-INF/AdminClients.jsp").forward(request, response);
          
                    } catch (Exception ex) {
                        Logger.getLogger(AdminClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }
 
        } catch (Exception ex) {
            Logger.getLogger(AdminClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Converts a birthdate to a Date object in yyyy-MM-dd format
     * @param birthdate to be converted, as a String
     * @return the birthdate converted to a Date object
     */
    private Date convertBirthday(String birthdate) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime ldt = LocalDateTime.parse(birthdate, dateFormat);

        Date date = Date.from(ldt.atZone(java.time.ZoneId.systemDefault()).toInstant());
        
        return date;
    }
}
