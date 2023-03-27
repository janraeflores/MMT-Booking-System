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
    </head>

    <header>
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
        <main>
            <div>
                <img class="mmt-logo" src="./Assets/Images/white-logo-tp.png" />

                <form class="form" action="login" method="POST">
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
                    ${message}
                    <div class="login-container">
                        <button class="login">LOGIN</button>
                    </div>
                    <div class="forgot-password"><a href="#">Forgot Password?</a></div>
                </form>
                <div class="register-container">
                    New here? Register an account here: <a href="registration">Register!</a>
                </div>
            </div>
        </main>

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
    </body>

</html>
