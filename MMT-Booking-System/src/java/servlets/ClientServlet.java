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
import services.AccountService;
import services.ClientService;

/**
 *
 * @author Keith
 */
public class ClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        AccountService as = new AccountService();
        
        try {
            Account account = as.get(username);
            request.setAttribute("account", account);
        } catch (Exception ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("username", username);
        
        getServletContext().getRequestDispatcher("/WEB-INF/Client.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        
        AccountService as = new AccountService();

        ClientService cs = new ClientService();
        String action = request.getParameter("action");

        try {
            Account account = as.get(username);
            
            if (action.equalsIgnoreCase("createClient")) {
                
                String fullName = request.getParameter("full_name");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                
                String ecName = request.getParameter("ec_name");
                String ecPhone = request.getParameter("ec_phone");
                
                cs.insert(fullName, account.getEmail(), phone, address);
                request.setAttribute("message", "Client has been added successfully!");
                
                request.setAttribute("username", username);
                response.sendRedirect("account");
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("client");
        return;
    }
}
