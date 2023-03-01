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
import models.Client;
import services.AccountService;
import services.ClientService;

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
        ClientService cs = new ClientService();

        try {
            Client client = cs.get(username);
            request.setAttribute("client", client);
        } catch (Exception ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Account account = as.get(username);
            request.setAttribute("account", account);
        } catch (Exception ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");

        AccountService as = new AccountService();
        ClientService cs = new ClientService();
        String action = request.getParameter("action");

        try {
            if (action.equalsIgnoreCase("updateAccount")) {
                Account account = as.get(username);
                Client client = cs.get(account.getUsername());
                Role role = account.getRole();
                String fullName = request.getParameter("full_name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String password = request.getParameter("password");
                String ecName = request.getParameter("ec_name");
                String ecPhone = request.getParameter("ec_phone");                
                boolean active = false;

                cs.update(client.getClientId(), fullName, email, phone, address);
                request.setAttribute("message", "Account has been updated successfully!");
            }

        } catch (Exception e) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        response.sendRedirect("account");
        return;
    }
}
