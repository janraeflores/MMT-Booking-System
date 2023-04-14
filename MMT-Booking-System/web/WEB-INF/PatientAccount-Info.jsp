<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <%-- 
    Document   : PatientAccount-Info
    Created on : 6-Feb-2023, 5:48:21 PM
    Author     : janraeSAIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> -->
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>MMT - Account</title>

        <!-- Stylesheet -->
        <link rel="stylesheet" type="text/css" href="Assets/Styles/PatientAccount.css">
        <link rel="stylesheet" type="text/css" href="Assets/Styles/NavigationBar.css">
        <link rel="stylesheet" type="text/css" href="Assets/Styles/Footer.css">
    </head>

    <body>
        <header>
            <div class="mmt-logo">
                <img src="./Assets/Images/white-logo-tp.png" alt="">
            </div>
            <h1>Massage Master Therapy</h1>

            <input type="checkbox" id="active">
            <p class="menu" style="top: 0px;">MENU</p>
            <label for="active" class="menu-btn"><span></span></label>
            <label for="active" class="close"></label>
            <div class="wrapper">
                <ul>
                    <li><a href="MMT">HOME</a></li>
                        <c:if test="${account.role.roleName eq 'administrator'}">
                        <li><a href="admin">MAIN</a></li>
                        <li><a href="clients">CLIENTS</a>
                        </c:if>
                    <li><a href="account">ACCOUNT</a></li>
                    <li><a href="reservation">RESERVATION</a></li>
                        <c:if test="${account.role.roleName eq 'client'}">
                        <li><a href="service">SERVICES</a></li>
                        <li><a href="contact">CONTACT</a></li>
                        </c:if>
                    <li><a href="login?logout">LOGOUT</a></li>
                </ul>
            </div>
        </header>

        <main>
            <div class="title-container">
                <h2 id="title">ACCOUNT DETAILS</h2>
            </div>
            <div class="line"></div>

            <div class="flex-container">
                <div class="left-column">
                    <p><a id="bookings" href="booking">BOOKINGS</a></p>
                    <p><a id="current" href="account">PERSONAL INFO</a></p>
                </div>

                <div class="right-column">
                    <p>PROFILE</p>
                    <div class="line"></div>
                    <div id="message">${message}</div>
                    <form action="account?username=${account.username}" method="POST">
                        <table>
                            <tbody>
                                <tr>
                                    <td class="left-table">Full Name</td>
                                    <td class="right-table">
                                        <input type="text" class="right-table-input" name="full_name" value="${account.fullName}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">Email</td>
                                    <td class="right-table">
                                        <input type="text" class="right-table-input" name="email" value="${account.email}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">Phone Number</td>
                                    <td class="right-table">

                                        <input type="text" class="right-table-input" name="phone" value="${account.phone}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">Address</td>
                                    <td class="right-table">
                                        <input type="text" class="right-table-input" name="address" value="${account.address}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">Birthday</td>
                                    <td class="right-table">
                                        <input type="date" class="right-table-input" name="birthdate" 
                                               value="<fmt:formatDate value='${account.birthdate}' pattern='yyyy-MM-dd' />">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">Username</td>
                                    <td class="right-table">
                                        <input type="text" class="right-table-input" name="username" value="${account.username}" readonly>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">Password</td>
                                    <td class="right-table">
                                        <input type="password" class="right-table-input" name="password" value="${account.password}">
                                    </td>
                                </tr>
                            <br>
                            </tbody>
                        </table>
                        <div id="emergency-contact">
                            <p id="emergency-contact-heading">EMERGENCY CONTACT 
                                <a id="add-emergency" href="<c:url value='/account?action=add'>
                                       <c:param name='username' value='${account.username}' />
                                   </c:url>">
                                    Add contact
                                </a>
                            </p>
                            <c:choose>
                                <c:when test="${empty emergencyContact}">
                                    <p id="no-contacts">No emergency contacts</p>
                                </c:when>
                                <c:otherwise>
                                    <table border="2">
                                        <tr>
                                            <th>Name</th>
                                            <th>Relation</th>
                                            <th>Phone Number</th>
                                            <th>Email</th>
                                            <th></th>
                                        </tr>
                                        <c:forEach items="${emergencyContact}" var="emergencyContact">
                                            <tr>
                                                <td>                   
                                                    <input type="text" name="ec_name" value="${emergencyContact.ecName}">
                                                </td>
                                                <td>
                                                    <input type="text" name="ec_relation" value="${emergencyContact.ecRelation}">
                                                </td>
                                                <td>
                                                    <input type="text" name="ec_phone" value="${emergencyContact.ecPhone}">
                                                </td>
                                                <td>
                                                    <input type="text" name="ec_email" value="${emergencyContact.ecEmail}">
                                                </td>
                                                <td>
                                                    <a id="delete-emergency" href="<c:url value='/account?action=delete'>
                                                           <c:param name='ec_id' value='${emergencyContact.ecId}'></c:param>
                                                       </c:url>">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </c:otherwise>
                            </c:choose>

                        </div>
                        <c:if test="${add eq true}">
                            <div class="emerg-contact-form">
                                <p>Add an Emergency Contact</p>
                                <label>Name</label>
                                <input required type="text" id="contact-name" name="contact_name" value="">
                                <br>
                                <label>Relation</label>
                                <input required type="text" id="contact-relation" name="contact_relation" value="">
                                <br>
                                <label>Phone Number</label>
                                <input required type="text" id="contact-phone" name="contact_phone" value="">
                                <br>
                                <label>Email</label>
                                <input type="text" id="contact-email" name="contact_email" value="">
                                <div id="add-emerg-contact">
                                    <a id="cancel-button" href="account?action=cancel">Cancel</a>
                                    <button class="add-contact-btn">Add</button>
                                    <input type="hidden" name="action" value="addContact">
                                </div>

                            </div>
                        </c:if>

                        <div class="save-container">
                            <button class="save">SAVE</button>
                            <input type="hidden" name="action" value="updateAccount">
                        </div>


                    </form>

                </div>


            </div>
        </main>

        <div class="footer-basic">
            <footer>
                <ul class="list-inline">
                    <li class="list-inline-item"><a href="MMT">Home</a></li>
                    <li class="list-inline-item"><a href="service">Services</a></li>
                    <li class="list-inline-item"><a href="reservation">Reservations</a></li>
                    <li class="list-inline-item"><a href="contact">Contact</a></li>
                </ul>
                <p class="copyright">Massage Master Therapy © 2023</p>
            </footer>
        </div>
    </body>
</html>
