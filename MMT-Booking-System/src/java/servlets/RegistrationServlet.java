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

        String email = request.getParameter("email-input");
        String username = request.getParameter("username-input");
        String password = request.getParameter("pass-input");

        String action = request.getParameter("action");

        AccountService as = new AccountService();
        RoleService rs = new RoleService();

        String message;

        try {
            if (action != null) {
                switch (action) {
                    case "register":
                        if (Validate.isEmpty(new String[]{email, username, password})) {
                            message = "Please fill out all fields";

                            request.setAttribute("message", message);
                            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
                        } else {
                            Role role = rs.get(2);

                            as.insert(email, true, username, password, role);
                            
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
