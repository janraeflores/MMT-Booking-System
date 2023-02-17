<%-- 
    Document   : Reservation
    Created on : 10-Feb-2023, 6:07:32 PM
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

        <title>MMT - Reservations</title>
        <link rel="stylesheet" type="text/css" href="Assets/Styles/Reservation.css">
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
            <div class="wrapper stack-top">
                <ul>
                    <li><a href="account">HOME</a></li>
                    <li><a href="login">LOGIN</a></li>
                    <li><a href="#">SERVICES</a></li>
                    <li><a href="reservation">RESERVATION</a></li>
                    <li><a href="#">CONTACT</a></li>
                    <li><a href="#">LOGOUT</a></li>
                </ul>
            </div>
        </header>

        <main>
            <div class="container">
                <div class="left">
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
                            <button class="today-btn">TODAY</button>
                        </div>
                    </div>
                </div>

                <div class="right">
                    <div class="today-date">
                        <div class="event-day"></div>
                        <div class="event-date"></div>
                        <form action="reservation" method="post">
                            <div id="services" class="services hide">
                                <p>SERVICES</p>
                                <div class="service-select">

                                    <select name="s-type" id="s-type">
                                        <c:forEach items="${service}" var="service">
                                            <option value="${service.serviceId}">${service.serviceType}</option>    
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="service-length">
                                    <select name="s-duration" id="s-duration">
                                        <option value="0">Session Duration</option>
                                        <option value="60">60 MINS (1 hour)</option>
                                        <option value="90">90 MINS (1.5 hours)</option>
                                        <option value="120">120 MINS (2 hours)</option>
                                    </select>
                                </div>
                                <div class="time-slot">
                                    <select name="t-slot" id="t-slot">
                                        <option value="0">Time Slot</option>
                                        <option value="1">10:00AM</option>
                                        <option value="2">11:00AM</option>
                                        <option value="3">12:00PM</option>
                                        <option value="4">1:00PM</option>
                                        <option value="5">2:00PM</option>
                                        <option value="6">3:00PM</option>
                                        <option value="7">4:00PM</option>
                                        <option value="8">5:00PM</option>
                                        <option value="9">6:00PM</option>
                                    </select>
                                </div>                                    
                                <div class="patient-info">
                                    <table>
                                        <tbody>
                                            <tr>
                                        <p>INFORMATION</p>
                                        </tr>
                                        <tr>
                                            <td>NAME:</td>
                                            <td><input type="text" name="u-name" id="u-name" value="${client.fullName}"></td>
                                        </tr>
                                        <tr>
                                            <td>PHONE:</td>
                                            <td><input type="tel" name="u-tel" id="u-tel" value="${client.phone}"></td>
                                        </tr>
                                        <tr>
                                            <td>EMAIL:</td>
                                            <td><input type="email" name="u-email" id="u-email" value="${client.contactEmail}"></td>
                                        </tr>
                                        <tr>
                                            <td>ADDRESS:</td>
                                            <td><input type="text" name="u-address" id="u-address" value="${client.address}"></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div class="medical-concerns">
                                        <p>MEDICAL CONCERNS:</p>
                                        <input type="text" name="med-concerns" id="med-concerns" value="">
                                    </div>
                                    <div class="reserve-container">
                                        <button id="reserve" type="submit">BOOK</button>

                                        <p>Client: ${appointment.client.fullName}</p>             
                                        <p>Service: ${appointment.service.serviceType}</p>  
                                        <p>Length: ${duration} mins</p>
                                        <p>Appointment Date: ${appointment.appointmentDate}</p>   
                                        <p>Appointment Address: ${appointment.appointmentAddress}</p>   
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>                       
                </div>
            </div>
        </main>



        <script src="Assets/Scripts/Reservation.js"></script>
    </body>

</html>
