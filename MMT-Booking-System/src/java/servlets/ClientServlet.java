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
import models.Client;
import services.AccountService;
import services.ClientService;

/**
 *
 * @author Keith
 */
public class ClientServlet extends HttpServlet {

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
