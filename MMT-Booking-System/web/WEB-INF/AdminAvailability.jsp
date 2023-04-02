<%-- 
    Document   : AdminAvailability
    Created on : 28-Mar-2023, 11:42:44 AM
    Author     : Taburada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Availability Page</title>

        <!--Stylesheet-->
        <link rel="stylesheet" type="text/css" href="Assets/Styles/AdminAvailability.css">

    </head>
    <header>

        <!--Menu button stuff-->
        <input type="checkbox" id="active">
        <label for="active" class="menu-btn"><span></span></label>
        <label for="active" class="close"></label>
        <div class="wrapper stack-top">
            <ul>
                <li><a href="admin">MAIN</a></li>
                <li><a href="availability">AVAILABILITY</a></li>
                <li><a href="clients">CLIENTS</a></li>
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
                                <button class="today-btn">TODAY</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="right">
                    <div id="viewAppoints">
                        <div class="today-date">

                            <form method="POST" action="">
                                <h1>Set Availability</h1>
                                <h2>
                                    <div class="event-day"></div><div class="event-date"></div>
                                </h2>

                                <div id="date">
                                    <h3>DATE</h3>
                                    <input type="date" value="" class="dateSelected">
                                    <select name="repeat" id="repeat" class="repeat">
                                        <option value="noRepeat">don't repeat</option>
                                        <option value="daily">daily</option>
                                        <option value="weekly">weekly</option>
                                        <option value="monthly">monthly</option>
                                        <option value="yearly">yearly</option>
                                    </select>
                                </div>

                                <div id="time">
                                    <h3>TIME</h3>
                                    <div id="inputTime">
                                        <p>
                                            <input type="time" id="timeStart" class="time"/>
                                            TO
                                            <input type="time" id="timeEnd" class="time"/>
                                        </p>
                                        <div class="dateTime">
                                            <label for="allDay">
                                                <input type="checkbox" value="all-day" id="allDay" class="allDay" onclick="disableTime()" style="display: block;">
                                                <span>all-day</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div id="buttons">
                                    <button class="confirmAppoint">confirm</button>
                                    <button class="cancelAppoint">cancel</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <script src="Assets/Scripts/Reservation.js"></script>
        <script src="Assets/Scripts/AdminAvailability.js"></script>
    </body>
</html>
