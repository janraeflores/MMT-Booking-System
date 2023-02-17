<%-- 
    Document   : PatientAccount-Info
    Created on : 6-Feb-2023, 5:48:21 PM
    Author     : janraeSAIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>MMT - Account</title>

        <!-- Stylesheet -->
        <link rel="stylesheet" type="text/css" href="Assets/Styles/PatientAccount.css">
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
            <div class="wrapper">
                <ul>
                    <li><a href="#">HOME</a></li>
                    <li><a href="#">LOGIN</a></li>
                    <li><a href="#">SERVICES</a></li>
                    <li><a href="#">RESERVATION</a></li>
                    <li><a href="#">CONTACT</a></li>
                    <li><a href="#">LOGOUT</a></li>
                </ul>
            </div>
        </header>

        <main>
            <div class="title-container">
                <h2>ACCOUNT DETAILS</h2>
            </div>
            <div class="line"></div>

            <div class="flex-container">
                <div class="left-column">
                    <p><a href="reservation">BOOKINGS</a></p>
                    <p><a href="#">PERSONAL INFO</a></p>
                </div>
                <form action="" method="POST">
                    <div class="right-column">
                        <p>PROFILE</p>
                        <div class="line"></div>

                        <table>
                            <tbody>
                                <tr>
                                    <td class="left-table">FULL NAME</td>
                                    <td class="right-table">
                                        <input type="text" name="full_name" value="${client.fullName}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">EMAIL</td>
                                    <td class="right-table">
                                        <input type="text" name="email" value="${client.contactEmail}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">PHONE NUMBER</td>
                                    <td class="right-table">
                                        <input type="text" name="phone" value="${client.phone}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">ADDRESS</td>
                                    <td class="right-table">
                                        <input type="text" name="address" value="${client.address}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">EMERGENCY CONTACT</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="left-table-sub">Full Name:</td>
                                    <td class="right-table">
                                        <input type="text" name="ec_name">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table-sub">Phone Number:</td>
                                    <td class="right-table">
                                        <input type="text" name="ec_phone">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">PASSWORD</td>
                                    <td class="right-table">
                                        <input type="password" name="password">
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <div class="save-container">
                            <button class="save">SAVE</button>
                            <input type="hidden" name="action" value="updateAccount">
                        </div>
                        <h4>${message}</h4>
                    </div>
                </form>
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
