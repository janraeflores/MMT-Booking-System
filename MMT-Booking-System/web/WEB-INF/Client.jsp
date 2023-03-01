<%-- 
    Document   : PatientAccount-Info
    Created on : 28-Feb-2023, 9:59:13 PM
    Author     : Keith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>MMT - Client</title>

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
                    <li><a href="login">LOGOUT</a></li>
                </ul>
            </div>
        </header>

        <main>
            <div class="title-container">
                <h2>CLIENT DETAILS</h2>
            </div>
            <div class="line"></div>

            <div class="flex-container">
                <form action="client" method="POST">
                    <div class="right-column">
                        <p>CREATE CLIENT PROFILE</p>
                        <div class="line"></div>

                        <table>
                            <tbody>
                                <tr>
                                    <td class="left-table">EMAIL</td>
                                    <td class="right-table">
                                        <b>${account.email}</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">FULL NAME</td>
                                    <td class="right-table">
                                        <input type="text" name="full_name" placeholder="full name">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">PHONE NUMBER</td>
                                    <td class="right-table">
                                        <input type="text" name="phone" placeholder="#########">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="left-table">ADDRESS</td>
                                    <td class="right-table">
                                        <input type="text" name="address" placeholder="Massage Clinic">
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
                            </tbody>
                        </table>

                        <div class="save-container">
                            <button class="save">SUBMIT</button>
                            <input type="hidden" name="action" value="createClient">
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
