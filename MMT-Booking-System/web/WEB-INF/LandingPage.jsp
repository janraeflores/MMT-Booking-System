<%-- 
    Document   : LandingPage
    Created on : 29-Mar-2023, 4:52:05 PM
    Author     : janraeSAIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>MMT</title>

        <!-- Stylesheet -->
        <link rel="stylesheet" type="text/css" href="Assets/Styles/LandingPage.css">
        <link rel="stylesheet" type="text/css" href="Assets/Styles/NavigationBar.css">
        <link rel="stylesheet" type="text/css" href="Assets/Styles/Footer.css">

    </head>

    <header>
        <input type="checkbox" id="active">
        <label for="active" class="menu-btn"><span></span></label>
        <label for="active" class="close"></label>
        <div class="wrapper">
            <ul>
                <li><a href="MMT">HOME</a></li>
                <li><a href="login">LOGIN</a></li>
                <li><a href="reservation">RESERVATION</a></li>
                <li><a href="service">SERVICES</a></li>
                <li><a href="contact">CONTACT</a></li>
            </ul>
        </div>
    </header>

    <body>
        <div class="sectionA">
            <div id="background-img"></div>
        </div>

        <div class="sectionB">
            <h2>Services & Reservations</h2>

            <div class="float-container">
                <a href="service">
                    <div class="float-child1">
                        <div class="masssage">
                            <P>MASSAGE</P>
                        </div>
                    </div>
                </a>

                <a href="service">
                    <div class="float-child2">
                        <div class="spec-massage">
                            <p>SPECIAL</p>
                            <p>MASSAGE</p>
                        </div>
                    </div>
                </a>

                <a href="service">
                    <div class="float-child3">
                        <div class="box teeth-white">
                            <p>TEETH</p>
                            <p>WHITENING</p>
                        </div>
                    </div>
                </a>

                <a href="service">
                    <div class="float-child4">
                        <div class="box facial-etc">
                            <p>FACIAL,</p>
                            <p>ETC</p>
                        </div>
                    </div>
                </a>
            </div>
        </div>

        <div class="sectionC">
            <figure class="portrait">
                <div>
                    <img src="./Assets/Images/hans-vivek-UiMkBvDQSAA-unsplash.jpg" alt="">
                </div>
            </figure>
            <div class="c-description">
                <h3>EXPERIENCES RENEWED</h3>
                <h2>MASSAGE MASTER THERAPY</h2>
                <p>OFFERING A RANGE OF SERVICES TO HELP CLIENTS RELAX AND REJUVENATE. OUR GOAL IS TO MAKE YOU FEEL LIKE A
                    BRAND NEW PERSON.</p>
                <p>FROM MASSAGES (RELAXATION, DEEP TISSUE, LYMPHATIC, OR THERAPEUTIC), CUPPING THERAPY, FACIAL'S, AND EVEN
                    TEETH WHITENING, OUR SERVICES ARE CATERED TO YOUR SPECIFIC NEEDS.</p>
                <p>WITH A GUARANTEED UNPARALLELED EXPERIENCE YOU CAN BE SURE THAT YOUR TIME WILL BE UNFORGETTABLE.</p>
                <p>- MMT</p>
            </div>
        </div>

        <div class="sectionD">
            <p>SOLVING THE WORLD'S PROBLEMS, ONE MASSAGE AT A TIME.</p>
            <hr>
        </div>

        <div class="sectionE">

        </div>

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
