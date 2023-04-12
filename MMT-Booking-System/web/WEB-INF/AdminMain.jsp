<%-- 
    Document   : AdminMain
    Created on : 27-Mar-2023, 6:20:44 PM
    Author     : Taburada
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Main Page</title>

        <!--Stylesheet-->
        <link rel="stylesheet" type="text/css" href="Assets/Styles/AdminMain.css">

    </head>
    <header>
        <!--Menu button stuff-->
        <input type="checkbox" id="active">
        <label for="active" class="menu-btn"><span></span></label>
        <label for="active" class="close"></label>
        <div class="wrapper stack-top">
            <ul>
                <li><a href="admin">MAIN</a></li>
                <li><a href="account">ACCOUNT</a></li>
                <li><a href="clients">CLIENTS</a></li>
                <li><a href="reservation">RESERVATION</a></li>
                <li><a href="login">LOGOUT</a></li>
            </ul>
        </div>
    </header>
    <body>
        <main>
            <div class="container">
                <div class="left">
                    <div id="calendar">
                        <div class="calendar">
                            <div class="month">
                                <i class="gg-chevron-left prev"></i>
                                <div class="date"></div>
                                <i class="gg-chevron-right next"></i>
                            </div>
                            <div class="weekdays">
                                <div>Sun</div>
                                <div>Mon</div>
                                <div>Tues</div>
                                <div>Wed</div>
                                <div>Thurs</div>
                                <div>Fri</div>
                                <div>Sat</div>
                            </div>

                            <div class="days">
                                <!-- days added with javascript -->
                            </div>

                            <div class="goto-today">
                                <div class="goto">
                                    <input type="text" placeholder="MM/YYYY" class="date-input" />
                                    <button class="goto-btn">GO</button>
                                </div>
                                <button class="today-btn">CURRENT MONTH</button>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="right">
                    <div id="viewAppoints">
                        <div class="today-date">
                            <div class="event-day"></div>
                            <form action="admin" method="post">
                                <div class="event-date"></div>
                                <input type="hidden" id="selected-date" name="selected-date">
                                <button id="showAppoints" type="sumbit">show appointments</button>

                                <div class="appointment">
                                    <c:forEach items="${dispAppoints}" var="dispAppoints">

                                        <h2>
                                            <a id="appointmentTime" 
                                               href="<c:url value='/admin?action=displayAppoints'>
                                                   <c:param name='appointId' value='${dispAppoints.appointmentId}'></c:param>
                                               </c:url>">
                                                <fmt:formatDate pattern="hh:mm a" timeZone="America/Denver" value="${dispAppoints.appointmentDate}"/>
                                                <br>
                                                <div class="apptDay">
                                                    <fmt:formatDate pattern="dd MMMM, yyyy" timeZone="America/Denver" value="${dispAppoints.appointmentDate}"/>
                                                </div>
                                            </a>
                                        </h2>
                                        <div id="apppointInfo ${dispAppoints.appointmentId}">
                                            <p><b>Client: </b>${dispAppoints.account.fullName}</p>
                                            <p><b>Email: </b>${dispAppoints.account.email}</p>
                                            <p><b>Phone: </b>${dispAppoints.account.phone}</p>
                                            <p><b>Medical Concerns: </b>${dispAppoints.account.medicalInfo}</p>
                                            <p><b>Service: </b>${dispAppoints.service.serviceType}</p>
                                            <p><b>Service: </b>${dispAppoints.duration} mins</p>
                                            <p><b>Location: </b>${dispAppoints.appointmentAddress}</p>
                                        </div>
                                    </c:forEach>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <script src="Assets/Scripts/Reservation.js"></script>
</body>
</html>
