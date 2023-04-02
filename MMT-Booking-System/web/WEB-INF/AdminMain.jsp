<%-- 
    Document   : AdminMain
    Created on : 27-Mar-2023, 6:20:44 PM
    Author     : Taburada
--%>

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
                            <div class="event-day"></div>
                            <div class="event-date"></div>
                            <!-- figure out how to display appointments based on day selected-->

                            <!--hardcoded for css-->
                            <!-- I would say prolly change appointInfo1 with $/{somevalue} that way each button would correlate with the following appointment div-->

                            <form action="" method="POST">
                                <div class="appointment">
                                    <h2>
                                        <input type="button" onclick="displayInfo()" id="displaySymbol" class="displaySymbol" value="˅">
                                        <input type="hidden" id="appoint" name="appoint" value="appointInfo1">
                                        11:30am - 1:00pm
                                        <button class="acceptAppoint hidden">accept</button>
                                        <button class="declineAppoint hidden">decline</button>
                                    </h2>
                                    <div id="appointInfo">
                                        <p><b>Client:</b> Donatello Hamato</p>
                                        <p><b>Email:</b> bootyyyshaker9000@someemail.com</p>
                                        <p><b>Phone:</b> I wish I had it bro</p>
                                        <p><b>Medical Concerns:</b> Too smart tbh plus his shell is super soft. Chronically has 3 dumb brothers and constantly has to save the world. Honestly prolly diagnosed with the autism</p>
                                        <p><b>Service:</b> Please give him a massage this mans back is prolly busted from slouching all the time lmAOOOO</p>
                                        <p><b>Location:</b> The sewers frfr</p>
                                    </div>
                                </div>
                            </form>

                            <form action="" method="POST">
                                <div class="appointment">
                                    <h2>
                                        <input type="button" onclick="displayInfo()" id="displaySymbol" class="displaySymbol" value="˅">
                                        <input type="hidden" id="appoint" name="appoint" value="appointInfo2">
                                        1:30am - 3:00pm
                                        <button class="acceptAppoint hidden">accept</button>
                                        <button class="declineAppoint hidden">decline</button>
                                    </h2>
                                    <div id="appointInfo">
                                        <p><b>Client:</b></p>
                                        <p><b>Email:</b></p>
                                        <p><b>Phone:</b></p>
                                        <p><b>Medical Concerns:</b></p>
                                        <p><b>Service:</b></p>
                                        <p><b>Location:</b></p>
                                    </div>
                                </div>
                            </form>

                            <form action="" method="POST">
                                <div class="appointment">
                                    <h2>
                                        <input type="button" onclick="displayInfo()" id="displaySymbol" class="displaySymbol" value="˅">
                                        <input type="hidden" id="appoint" name="appoint" value="appointInfo3">
                                        6:00am - 7:00pm
                                        <!-- prolly do something where if the status of the appointment is accepted hide
                                        them but if they're declined then remove the appointment from being displayed-->
                                        <button class="acceptAppoint">accept</button>
                                        <button class="declineAppoint">decline</button>
                                    </h2>
                                    <div id="appointInfo">
                                        <p><b>Client:</b></p>
                                        <p><b>Email:</b></p>
                                        <p><b>Phone:</b></p>
                                        <p><b>Medical Concerns:</b></p>
                                        <p><b>Service:</b></p>
                                        <p><b>Location:</b></p>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <script src="Assets/Scripts/Reservation.js"></script>
        <script src="Assets/Scripts/AdminMain.js"></script>
    </body>
</html>
