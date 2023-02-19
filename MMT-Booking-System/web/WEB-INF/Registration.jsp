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
        <p>Create an account or <a href="#">login here.</a></p>
    </div>
    <div class="line"></div>

    <div class="flex-container">
        <div class="form-container">
            <form action="" method="">
                <div class="email-container">
                    <p>Email Address:</p>
                    <div class="email">
                        <input type="email" name="email-input" id="email-input" value="">
                    </div>
                </div>
                <div class="username-container">
                    <p>Username:</p>
                    <div class="username">
                        <input type="text" name="username-input" id="username-input" value="">
                    </div>
                </div>
                <div class="password-container">
                    <p>Password:</p>
                    <div class="password">
                        <input type="password" name="pass-input" id="pass-input" value="">
                    </div>
                </div>
                <div class="fullName-container">
                    <p>Full Name:</p>
                    <div class="fullName">
                        <input type="text" name="fullName-input" id="fullName-input" value="">
                    </div>
                </div>
                <div class="telephone-container">
                    <p>Phone Number:</p>
                    <div class="telphone">
                        <input type="tel" name="tel-input" id="tel-input" value="">
                    </div>
                </div>
                <div class="address-container">
                    <p>Address: (optional)</p>
                    <div class="address">
                        <input type="text" name="address-input" id="address-input" value="">
                    </div>
                </div>
                <div class="button-container">
                    <input type="submit" class="button" value="REGISTER">
                    <!-- Not sure if the below line (line 81) is needed for backend -->
                    <!-- <input type="hidden" name="action" value="register"> -->
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
