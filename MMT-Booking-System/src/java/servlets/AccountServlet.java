package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.Account;
import models.EmergencyContact;
import services.AccountService;
import services.EmergencyContactService;
import services.Validate;

/**
 *
 * @author Keith
 */
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        
        EmergencyContactService ecs = new EmergencyContactService();

        try {
            request.setAttribute("account", account);

            EmergencyContact ec = ecs.get(account.getEcContact().getEcName());
            request.setAttribute("ec", ec);
        } catch (Exception ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        AccountService as = new AccountService();
        EmergencyContactService ecs = new EmergencyContactService();
        String action = request.getParameter("action");

        String username = account.getUsername();
        String ecName = account.getEcContact().getEcName();
        
        String fullName = request.getParameter("full_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        boolean active = true;
        String ecNameInput = request.getParameter("ec_name");
        String ecPhone = request.getParameter("ec_phone");
        String ecEmail = request.getParameter("ec_email");
        
        if (action.equalsIgnoreCase("updateAccount")) {
            if (!Validate.isEmpty(new String[]{fullName, email, phone, address, password, ecNameInput, ecPhone})) {
                try {
                    Role role = account.getRole();
                    EmergencyContact ec = account.getEcContact();

                    if (ec == null) {
                        ecs.insert(as.get(username), ecName, ecPhone, ecEmail);
                    } else {
                        ecs.update(as.get(username), ecName, ecPhone, ecEmail);
                    }

                    as.update(fullName, email, active, username, password, phone, role, address, ec);
                    
                    request.setAttribute("message", "Account has been updated successfully!");
                    request.setAttribute("account", as.get(username));

                    getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
                    
                } catch (Exception e) {
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                try {
                    request.setAttribute("account", as.get(username));
                    request.setAttribute("message", "Fields must not be empty.");
                    getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
