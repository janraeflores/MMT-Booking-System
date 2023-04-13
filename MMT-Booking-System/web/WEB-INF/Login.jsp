<%-- 
    Document   : Login
    Created on : 6-Feb-2023, 5:47:32 PM
    Author     : janraeSAIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>MMT - Login</title>

        <!-- Stylesheet -->
        <link rel="stylesheet" type="text/css" href="Assets/Styles/LoginPage.css">
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
                <li><a href="service">SERVICES</a></li>
                <li><a href="contact">CONTACT</a></li>
            </ul>
        </div>
    </header>

    <body>
        <main>
            <div>
                <img class="mmt-logo" src="./Assets/Images/white-logo-tp.png" />
                <form class="form" action="login" method="POST">
                    <div id="error-message">${errorMessage}</div>
                    <div class="user-container">
                        <i class="gg-user"></i>
                        <div class="username">
                            <input id="username" type="text" name="username" placeholder="Username" value="">
                        </div>
                    </div>
                    <div class="password-container">
                        <i class="gg-lock"></i>
                        <div class="password">
                            <input id="password" type="password" name="password" placeholder="Password" value="">
                        </div>
                    </div>
                    
                    <div class="login-container">
                        <button class="login">LOGIN</button>
                    </div>
                    <div class="forgot-password"><a href="#">Forgot Password?</a></div>
                </form>
                <div class="register-container">
                    New here? Register an account <a href="registration">here</a>
                </div>
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