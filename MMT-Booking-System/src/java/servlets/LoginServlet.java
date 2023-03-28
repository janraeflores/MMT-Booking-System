
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Role;
import services.AccountService;
import services.Validate;

/**
 *
 * @author janraeSAIT
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();

        getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountService as = new AccountService();
        Account account = as.login(username, password);
        
        if (Validate.isEmpty(new String[]{username, password})) {
            request.setAttribute("message", "Please provide a valid username or password.");
            getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
            return;
        }
        if (account == null) {
            request.setAttribute("message", "Your credentials cannot be verified.");
            getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
            return;
        }

        session.setAttribute("username", username);

        boolean isActive = account.getActive();

        if (isActive == true) {
            Role role = account.getRole();

            if (role.getRoleId() == 1) {
                response.sendRedirect("");
            } else {
                response.sendRedirect("account");
            }
        }
    }
}
