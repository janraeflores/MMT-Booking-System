package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;


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

        String fullName = request.getParameter("full_name-input");
        String email = request.getParameter("email-input");
        String username = request.getParameter("username-input");
        String password = request.getParameter("pass-input");
        String phone = request.getParameter("phone-input");
        String address = request.getParameter("address-input");

        String action = request.getParameter("action");

        try {
            if (action != null) {
                switch (action) {
                    case "register":
                        if (isEmpty(new String[]{email, username, password, fullName, phone, address})) {
                            request.setAttribute("message", "Please fill out all fields.");
                            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
                            return;
                        } 
                        
                        if (!email.contains("@") && !email.contains(".com")) {
                            request.setAttribute("message", "Your email must be valid.");
                            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
                            return;
                        } 
                        
                        if (as.get(username) != null) {
                            request.setAttribute("message", "This username is taken, please try again.");
                            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
                            return;
                        } 
                        
                        if (password.length() < 8) {
                            request.setAttribute("message", "Password have 8 or more characters.");
                            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
                            return;
                        }
                        
                        if (phone.length() < 10) {
                            request.setAttribute("message", "Phone number is invalid. Please try again.");
                            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
                            return;
                        }
                        
                        phone = formatPhoneNumber(phone);
                        as.insert(username, fullName, email, password, phone, address); 

                        session.setAttribute("account", as.get(username));
                        response.sendRedirect("booking"); 
                        break;
                        
                    case "cancel":
                        response.sendRedirect("login");
                        break;
                }
            }
        } catch (Exception e) {
            request.setAttribute("message", "An error occured. Please try again.");
            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Method for formatting phone numbers in a (###) ###-#### format
     * @param phoneNumber
     * @return a formatted phone number
     */
    private String formatPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^\\d]", "");
        return String.format("(%s) %s-%s", phoneNumber.substring(0,3), phoneNumber.substring(3,6), phoneNumber.substring(6));
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
