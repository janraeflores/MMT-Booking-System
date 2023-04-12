<%-- 
    Document   : PatientAccount-Bookings
    Created on : 18-Feb-2023, 4:35:44 PM
    Author     : Flores
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>MMT - Account</title>

        <!-- Stylesheet -->
        <link rel="stylesheet" type="text/css" href="Assets/Styles/PatientAccount-Bookings.css">
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
                <h2>BOOKINGS</h2>
            </div>
            <div class="line"></div>

            <div class="flex-container">
                <div class="left-column">
                    <p><a id="current" href="#">BOOKINGS</a></p>
                    <p><a href="account">PERSONAL INFO</a></p>
                </div>
                <c:choose>
                    <c:when test="${empty appointment}">
                        <h3>You have no scheduled appointments.</h3>
                    </c:when>
                    <c:otherwise>
                        <div class="right-column-container">
                            <c:if test="${message ne null}">
                                <div id="message">${message}</div>
                            </c:if>
                            <table id="booking-details">
                                <tr>
                                    <th>Service</th>
                                    <th>Duration</th>
                                    <th>Date and Time</th>
                                    <th>Location</th>
                                    <th>Status</th>
                                    <th></th>
                                </tr>
                                <c:forEach items="${appointment}" var="appointment">
                                    <tr>
                                        <td>${appointment.service.serviceType}</td>
                                        <td>${appointment.duration} mins</td>
                                        <td>
                                            <fmt:formatDate pattern="MMM dd, yyyy hh:mm a z" timeZone="America/Denver" value="${appointment.appointmentDate}"/>
                                        </td>
                                        <td>58 Fredson Dr SE Calgary, AB T2H 1E1</td>
                                        <td id="status">
                                            <c:choose>
                                                <c:when test="${appointment.status == false}">
                                                    &#x1F5D9
                                                </c:when>
                                                <c:otherwise>
                                                    &#x2713;
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td><a href="<c:url value='/booking?action=cancel'>
                                                   <c:param name='appointment_id' value='${appointment.appointmentId}'></c:param>
                                               </c:url>">Cancel</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
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
                <p class="copyright">Massage Master Therapy © ?YEAR?</p>
            </footer>
        </div>
    </body>

</html>