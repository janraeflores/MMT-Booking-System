
package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Role;
import services.AccountService;

/**
 *
 * @author janraeSAIT
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String logout = request.getParameter("logout");

        if (logout != null) {
            session.invalidate();
            request.setAttribute("message", "You have successfully logged out!");
            
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            AccountService as = new AccountService();
            
            Account account = as.login(username, password);
            
            if (isEmpty(new String[]{username, password})) {
                request.setAttribute("errorMessage", "Please provide a valid username or password.");
                getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
                return;
            }
            
            if (account == null) {
                request.setAttribute("errorMessage", "Your credentials cannot be verified.");
                getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
                return;
            }
            
            session.setAttribute("account", as.get(username));
            
            boolean isActive = account.getActive();
            
            if (isActive == true) {
                Role role = account.getRole();
                
                if (role.getRoleId() == 1) {
                    response.sendRedirect("admin");
                } 
                else {
                    response.sendRedirect("booking");
                }
            } 
            else {
                request.setAttribute("errorMessage", "Your account has been deactivated. Please contact the admin to activate your account.");
                getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Tests an array of input fields if an input is empty or null
     * @param input as an array of inputs
     * @return true if any of the fields contained in the array are empty, otherwise, returns false
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
