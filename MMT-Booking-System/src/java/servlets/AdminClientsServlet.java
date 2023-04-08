package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Account;
import services.AccountService;

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
        
        try {
            request.setAttribute("accounts", as.getAll());
        } catch (Exception ex) {
            Logger.getLogger(AdminClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String accountUsername = request.getParameter("username");
        String action = request.getParameter("action");
        action = action == null ? "" : action;
        
        
        try {
            Account selectedAccount = as.get(accountUsername);
            switch (action) {
                case "display":
                    request.setAttribute("display", true);
                    request.setAttribute("account", selectedAccount);
                    
                    break;
                case "delete":
                    as.delete(selectedAccount.getUsername());
                    response.sendRedirect("clients");
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
//        getServletContext().getRequestDispatcher("/WEB-INF/AdminClients.jsp").forward(request, response);
    }
}
