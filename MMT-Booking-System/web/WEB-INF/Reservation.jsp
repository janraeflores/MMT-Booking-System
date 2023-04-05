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
                    <li><a href="MMT">HOME</a></li>
                    <li><a href="account">ACCOUNT</a></li>
                    <li><a href="#">SERVICES</a></li>
                    <li><a href="reservation">RESERVATION</a></li>
                    <li><a href="contact">CONTACT</a></li>
                    <li><a href="logout">LOGOUT</a></li>
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
                                        <option value="0">Massage Options</option>
                                        <c:forEach items="${service}" var="service">
                                            <option value="${service.serviceId}">${service.serviceType}</option>    
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="service-length">
                                    <select name="s-duration" id="s-duration">
                                        <option value="0">Session Duration</option>
                                        <option value="60">60 Mins (1 hr)</option>
                                        <option value="90">90 Mins (1.5 hrs)</option>
                                        <option value="120">120 Mins (2 hrs)</option>
                                    </select>
                                </div>
                                
                                <input type="hidden" id="selected-date" name="selected-date">
                                
                                <div class="time-slot">
                                    <select name="t-slot" id="t-slot">
                                        <option value="0">Time Slot</option>
                                        <option value="10:00 AM">10:00 AM</option>
                                        <option value="11:00 AM">11:00 AM</option>
                                        <option value="12:00 PM">12:00 PM</option>
                                        <option value="1:00 PM">1:00 PM</option>
                                        <option value="2:00 PM">2:00 PM</option>
                                        <option value="3:00 PM">3:00 PM</option>
                                        <option value="4:00 PM">4:00 PM</option>
                                        <option value="5:00 PM">5:00 PM</option>
                                        <option value="6:00 PM">6:00 PM</option>
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
                                            <td><input type="text" name="u-name" id="u-name" value="${account.fullName}"></td>
                                        </tr>
                                        <tr>
                                            <td>PHONE:</td>
                                            <td>
                                                <fmt:formatNumber pattern='###-###-####' type='number' value='${account.phone}' var='accountPhone' />
                                                <input type="text" name="u-tel" id="u-tel" 
                                                       value="<fmt:formatNumber pattern='NNN-NNN-NNNN' value='${accountPhone}'/>">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>EMAIL:</td>
                                            <td><input type="email" name="u-email" id="u-email" value="${account.email}"></td>
                                        </tr>
                                        <tr>
                                            <td>ADDRESS:</td>
                                            <td><input type="text" name="u-address" id="u-address" value="${account.address}"></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div class="medical-concerns">
                                        <p>MEDICAL CONCERNS:</p>
                                        <textarea rows="5" cols="30" name="med-concerns" id="med-concerns"></textarea>
                                    </div>
                                    <div class="reserve-container">
                                        <button id="reserve" type="submit">BOOK</button>
                                    </div>
                                    <div id="message">${message}</div>
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
