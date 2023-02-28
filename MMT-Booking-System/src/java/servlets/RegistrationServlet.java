/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import services.RoleService;

/**
 *
 * @author Keith
 */
public class RegistrationServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                        if (email == null || email.equals("") || password == null || password.equals("")
                                || username == null || username.equals("")) {
                            message = "Please fill out all fields";

                            request.setAttribute("message", message);
                            getServletContext().getRequestDispatcher("/WEB-INF/Registration.jsp").forward(request, response);
                            return;
                        } else {
                        Role role = rs.get(2);

                        as.insert(email, true, username, password, role);

                        response.sendRedirect("login");
                        break;
                        }
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
