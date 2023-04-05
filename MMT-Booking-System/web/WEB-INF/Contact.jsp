<%-- 
    Document   : Contact
    Created on : 23-Mar-2023, 10:27:19 AM
    Author     : Ben Roker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>MMT - Contact Us</title>
        
        <!-- Stylesheet -->
        <link rel="stylesheet" type="text/css" href="Assets/Styles/ContactPage.css">

    </head>

    <header>
        <input type="checkbox" id="active">
        <label for="active" class="menu-btn"><span></span></label>
        <label for="active" class="close"></label>
        <div class="wrapper">
            <ul>
                <li><a href="MMT">HOME</a></li>
                <li><a href="login">LOGIN</a></li>
                <li><a href="#">SERVICES</a></li>
                <li><a href="reservation">RESERVATION</a></li>
                <li><a href="contact">CONTACT</a></li>
            </ul>
        </div>
    </header>
    <body>
        <main>
            <div class="inquirycontain">
                <div class ="inquirydisplay">
                    <img class="contactimage" src="./Assets/Images/hans-vivek-UiMkBvDQSAA-unsplash.jpg">
                <div class="inquirymessage">
                    <h1>For Booking Or Any Questions, Please Feel Free To Contact Us</h1>
                    <h1>Phone Number: 587-889-2061</h1>
                    <h1>Address: 58 Fredson Drive Southeast Calgary, AB T2H 1E1 Canada </h1>
                </div>
                    </div>
                <div class="hours">
                    <h1>Business Hours: </h1>
                    <p>Mon: 10:00am - 7:00pm</p>
                    <p>Tue: 10:00am - 7:00pm</p>
                    <p>Wed: 10:00am - 7:00pm</p>
                    <p>Thu: 10:00am - 7:00pm</p>
                    <p>Fri: 10:00am - 7:00pm</p>
                    <p>Sat: 10:00am - 7:00pm</p>
                    <p>Sun: Closed</p>
                </div>
                <div class="bookbutton">
                    <p><a href="booking">Book Now!</a></p>
                </div>
            </div>
        </main>
        <div class="footer-basic">
        <footer>
            <ul class="list-inline">
                <li class="list-inline-item"><a href="MMT">Home</a></li>
                <li class="list-inline-item"><a href="#">Services</a></li>
                <li class="list-inline-item"><a href="reservation">Reservations</a></li>
                <li class="list-inline-item"><a href="contact">Contact</a></li>
            </ul>
            <p class="copyright">Massage Master Therapy © ?YEAR?</p>
        </footer>
    </div>
    </body>
</html>
