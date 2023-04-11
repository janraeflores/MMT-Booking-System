package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.Account;
import services.AccountService;
import services.EmergencyContactService;

/**
 *
 * @author Keith
 */
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        EmergencyContactService ecs = new EmergencyContactService();

        String username = account.getUsername();
        String action = request.getParameter("action");
        action = action == null ? "" : action;

        try {
            switch (action) {
                case "add":
                    request.setAttribute("add", true);
                    request.setAttribute("account", account);
                    request.setAttribute("emergencyContact", ecs.getAll(username));
                    break;
                case "delete":
                    int ecId = Integer.parseInt(request.getParameter("ec_id"));
                    ecs.delete(ecId);
                    request.setAttribute("account", account);
                    request.setAttribute("emergencyContact", ecs.getAll(username));
            }

//            request.setAttribute("account", account);
            request.setAttribute("emergencyContact", ecs.getAll(username));
            getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
        } catch (Exception ex) {

            getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
            System.out.println("ecId: " + request.getParameter("ecId"));
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        AccountService as = new AccountService();
        EmergencyContactService ecs = new EmergencyContactService();

        String action = request.getParameter("action");

        String username = account.getUsername();

        String fullName = request.getParameter("full_name");
        String birthdate = request.getParameter("birthdate");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        String ecId = request.getParameter("ec_id");
        String ecName = request.getParameter("ec_name");
        String ecRelation = request.getParameter("ec_relation");
        String ecPhone = request.getParameter("ec_phone");
        String ecEmail = request.getParameter("ec_email");

        String contactName = request.getParameter("contact_name");
        String contactRelation = request.getParameter("contact_relation");
        String contactPhone = request.getParameter("contact_phone");
        String contactEmail = request.getParameter("contact_email");

        try {
            switch (action) {
                case "updateAccount":
                    if (!isEmpty(new String[]{fullName, email, phone, address, password})) {
                        Role role = account.getRole();

                        if (birthdate.equals("")) {
                            as.update(username, fullName, email, password, phone, address);
                        } else {
                            as.update(fullName, email, true, username, password, formatPhoneNumber(phone), role, convertBirthday(birthdate), address);
                        }
                        request.setAttribute("message", "Account has been updated successfully!");
                        request.setAttribute("account", as.get(username));
                        request.setAttribute("emergencyContact", ecs.getAll(username));
                    } else {
                        request.setAttribute("message", "Fields must not be empty.");
                    }
                    getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
                    break;
                case "addContact":
                    ecs.insert(contactName, formatPhoneNumber(contactPhone), contactEmail, contactRelation, username);
                    request.setAttribute("account", as.get(username));
                    request.setAttribute("emergencyContact", ecs.getAll(username));

                    getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
                    break;
                case "cancel":
                    request.setAttribute("add", false);
                    request.setAttribute("account", as.get(username));
                    request.setAttribute("emergencyContact", ecs.getAll(username));
                    getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
                    break;
                default:
                    getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("message", "An error occurred.");
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            getServletContext().getRequestDispatcher("/WEB-INF/PatientAccount-Info.jsp").forward(request, response);
        }
    }

    /**
     * Converts a birthdate to a Date object that can be stored in the database
     * as DATETIME
     *
     * @param birthdate to be converted
     * @return the birthdate converted to a Date object
     */
    private Date convertBirthday(String birthdate) {
        DateTimeFormatter birthdateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDate ld = LocalDate.parse(birthdate, birthdateFormat);
        LocalDateTime ldt = ld.atStartOfDay();

        String formattedBirthdate = ldt.format(dateFormat);

        Date birthday = Date.from(LocalDateTime.parse(formattedBirthdate, dateFormat).atZone(ZoneId.systemDefault()).toInstant());

        return birthday;
    }

    /**
     * Tests an array of input fields if an input is empty or null
     *
     * @param input as an array of inputs
     * @return true if any of the fields contained in the array are empty,
     * otherwise, returns false
     */
    public static boolean isEmpty(String[] input) {
        for (String s : input) {
            if (s.equals("") || s == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method for formatting phone numbers in a (###) ###-#### format
     *
     * @param phoneNumber
     * @return a formatted phone number
     */
    private String formatPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^\\d]", "");
        return String.format("(%s) %s-%s", phoneNumber.substring(0, 3), phoneNumber.substring(3, 6), phoneNumber.substring(6));
    }
}
