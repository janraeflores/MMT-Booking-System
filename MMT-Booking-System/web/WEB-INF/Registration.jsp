<%-- 
    Document   : Registration
    Created on : 18-Feb-2023, 5:15:18 PM
    Author     : Flores
--%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>MMT - Sign Up!</title>

        <!-- Stylesheet -->
        <link rel="stylesheet" type="text/css" href="Assets/Styles/Registration.css">
    </head>

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
                <li><a href="#">HOME</a></li>
                <li><a href="#">LOGIN</a></li>
                <li><a href="#">SERVICES</a></li>
                <li><a href="#">RESERVATION</a></li>
                <li><a href="#">CONTACT</a></li>
            </ul>
        </div>
    </header>

    <body>
        <div class="title-container">
            <h2>Register Now (:</h2>
            <p>Already have an account? <a href="login">Login here!</a></p>
        </div>
        <div class="line"></div>

        <div class="flex-container">
            <div class="form-container">
                <div id="message">${message}</div>
                
                <form action="registration" method="POST">
                    <div class="email-container">
                        <p>Email Address:</p>
                        <div class="email">
                            <input type="email" name="email-input" id="email-input">
                        </div>
                    </div>
                    <div class="username-container">
                        <p>Username:</p>
                        <div class="username">
                            <input type="text" name="username-input" id="username-input">
                        </div>
                    </div>
                    <div class="password-container">
                        <p>Password:</p>
                        <div class="password">
                            <input type="password" name="pass-input" id="pass-input">
                        </div>
                    </div>
                    <div class="fullName-container">
                        <p>Full Name:</p>
                        <div class="full_name">
                            <input type="text" name="full_name-input" id="full_name-input">
                        </div>
                    </div>
                    <div class="phone-container">
                        <p>Phone Number:</p>
                        <div class="phone">
                            <input type="tel" name="phone-input" id="phone-input"> 
                        </div>
                    </div>
                    <div class="address-container">
                        <p>Address:</p>
                        <div class="address">
                            <input type="text" name="address-input" id="address-input">
                        </div>
                    </div>
                    <div class="button-container">
                        <input type="submit" class="button" value="REGISTER">
                        <input type="hidden" name="action" value="register">
                        <!-- Not sure if the below line (line 81) is needed for backend -->
                        <!-- <input type="hidden" name="action" value="register"> -->
                    </div>
                </form>
                <form action="" method="POST">
                    <div class="button-container">
                        <input type="submit" class="button" value="CANCEL">
                        <input type="hidden" name="action" value="cancel">
                    </div>
                </form>

            </div>
        </div>

        <div class="footer-basic">
            <footer>
                <ul class="list-inline">
                    <li class="list-inline-item"><a href="#">Home</a></li>
                    <li class="list-inline-item"><a href="#">Services</a></li>
                    <li class="list-inline-item"><a href="#">Reservations</a></li>
                    <li class="list-inline-item"><a href="#">Contact</a></li>
                </ul>
                <p class="copyright">Massage Master Therapy © ?YEAR?</p>
            </footer>
        </div>

        <script src="Assets/Scripts/Reservation.js"></script>
    </body>

</html>
