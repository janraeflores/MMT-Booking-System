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
import services.AccountService;
import services.RoleService;
import services.Validate;

/**
 *
 * @author Keith
 */
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        RoleService rs = new RoleService();
        
        String fullName = request.getParameter("full_name-input");
        String email = request.getParameter("email-input");
        String username = request.getParameter("username-input");
        String password = request.getParameter("pass-input");
        String phone = request.getParameter("phone-input");
        String address = request.getParameter("address-input");

        String action = request.getParameter("action");
        
        // removes dashes from phone number
        phone = phone.replaceAll("-","");

        try {
            if (action != null) {
                switch (action) {
                    case "register":
                        if (Validate.isEmpty(new String[]{email, username, password, fullName, phone, address})) {
                            
                            request.setAttribute("message", "Please fill out all fields.");
                            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
                        } else if (!email.contains("@") && !email.contains(".com")) {

                            request.setAttribute("message", "Your email must be valid.");
                            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
                        } else if (as.get(username) != null) {
                            request.setAttribute("message", "This username is taken, please try again.");
                            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
                        } else if (Validate.passwordRequirement(password)) {
                            request.setAttribute("message", "Password have 8 or more characters.");
                            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
                        }
                        else {
                            Role role = rs.get(2);
                            as.insert(username, fullName, email, true, password, Integer.parseInt(phone), role, address); 
                            
                            session.setAttribute("account", as.get(username));
                            response.sendRedirect("booking"); 
                        }
                        break;
                    case "cancel":
                        response.sendRedirect("login");
                        break;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
