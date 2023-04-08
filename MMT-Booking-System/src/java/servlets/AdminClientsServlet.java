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
        
        AccountService as = new AccountService();
        
        String accountUsername = request.getParameter("username");
        String action = request.getParameter("action");
        action = action == null ? "" : action;
        
        try {
            switch (action) {
                case "display":
                    request.setAttribute("displayClient", true);
                    Account selectedAccount = as.get(accountUsername);
                    request.setAttribute("account", selectedAccount);
                    
                    break;
                case "delete":
                    as.delete(accountUsername);
                    
                    break;
            }
            request.setAttribute("accounts", as.getAll());
            getServletContext().getRequestDispatcher("/WEB-INF/AdminClients.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AdminClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        getServletContext().getRequestDispatcher("/WEB-INF/AdminClients.jsp").forward(request, response);
    }
}
