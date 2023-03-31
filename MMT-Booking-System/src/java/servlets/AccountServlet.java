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
        String username = (String) session.getAttribute("username");

        AccountService as = new AccountService();
        EmergencyContactService ecs = new EmergencyContactService();

        try {
            Account account = as.get(username);
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
        String username = (String) session.getAttribute("username");

        AccountService as = new AccountService();
        EmergencyContactService ecs = new EmergencyContactService();
        String action = request.getParameter("action");

        try {
            if (action.equalsIgnoreCase("updateAccount")) {
                Account account = as.get(username);
                Role role = account.getRole();
                EmergencyContact ec = account.getEcContact();

                String fullName = request.getParameter("full_name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String userName = request.getParameter("user_name");
                String password = request.getParameter("password");
                boolean active = true;
                String ecName = request.getParameter("ec_name");
                String ecPhone = request.getParameter("ec_phone");
                String ecEmail = request.getParameter("ec_email");
                
                if (Validate.isEmpty(new String[]{fullName, email, phone, address, password})) {
                    request.setAttribute("message", "Changes not saved.");
                    getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
                    return;
                }
                
                if (ec == null) {
                    ecs.insert(ecName, ecPhone, ecEmail);
                } else {
                    ecs.update(ecName, ecPhone, ecEmail);
                }

                as.update(fullName, email, active, userName, password, phone, role, address, ec);
                request.setAttribute("message", "Account has been updated successfully!");
                //getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
                
            }
        } catch (Exception e) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        response.sendRedirect("account");
    }
}
