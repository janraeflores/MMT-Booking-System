<%-- 
    Document   : PatientAccount-Bookings
    Created on : 18-Feb-2023, 4:35:44 PM
    Author     : Flores
--%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>MMT - Account</title>

    <!-- Stylesheet -->
    <link rel="stylesheet" type="text/css" href="Assets/Styles/PatientAccount-Bookings.css">
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
                <p><a href="#">BOOKINGS</a></p>
                <p><a href="#">PERSONAL INFO</a></p>
            </div>
            <div class="right-column-container">
                <table id="booking-details">
                    <tr>
                        <th>Name</th>
                        <th>Service</th>
                        <th>Duration</th>
                        <th>Date</th>
                        <th>Status</th>
                    </tr>
                    <tr>
                        <td>Keith C.</td>
                        <td>Massage</td>
                        <td>60mins</td>
                        <td>02/18/2023</td>
                        <td>Confirmed</td>
                    </tr>
                </table>
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