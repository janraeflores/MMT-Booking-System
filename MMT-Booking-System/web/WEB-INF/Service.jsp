<%-- 
    Document   : Services
    Created on : 21-Feb-2023, 12:46:41 PM
    Author     : marce
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>MMT - Massage Services</title>

        <!--     Stylesheet -->
        <link rel="shortcut icon" href="">
        <link rel="stylesheet" type="text/css" href="Assets/Styles/Service.css">
        <link rel="stylesheet" type="text/css" href="Assets/Styles/NavigationBar.css">
        <link rel="stylesheet" type="text/css" href="Assets/Styles/Footer.css">
    </head>

    <header>
        <div class="mmt-logo">
            <img src="./Assets/Images/white-logo-tp.png" alt="">
        </div>
        <h1>Massage Master Therapy</h1>

        <input type="checkbox" id="active">
        <p class="menu" style="top: 0px;">MENU</p>
        <label for="active" class="menu-btn"><span></span></label>
        <label for="active" class="close"></label>
        <div class="wrapper">
            <ul>
                <li><a href="MMT">HOME</a></li>
                    <c:choose>
                        <c:when test="${account ne null}">
                            <li><a href="account">ACCOUNT</a></li>
                        </c:when>
                        <c:otherwise>
                             <li><a href="login">LOGIN</a></li>
                        </c:otherwise>
                    </c:choose>

                <li><a href="reservation">RESERVATION</a></li>
                <li><a href="service">SERVICES</a></li>
                <li><a href="contact">CONTACT</a></li>
                <c:if test="${account ne null}">
                    <li><a href="login?logout">LOGOUT</a></li>
                </c:if>
            </ul>
        </div>
    </header>

    <body>
        <h1>Services & Reservations</h1>
        <h2>Massage Services</h2>
        <h3>Therapeutic Massage</h3>
        <div class="servicesDescription">
            <p>
                <img src="./Assets/Images/therapeutic.jpg" alt="">
                Therapeutic massage will help relieve pain, reduce stress, and work on a
                specific problem such as stiff injuries. This massage will also loosen
                tense musculature and break down scar tissue to increase range of motion.
                I can use my hands, forearms, and elbows to treat problems in the soft
                tissue.
            </p>
            <a href="reservation" class="button">SCHEDULE AN APPOINTMENT</a>

            <h3>Deep Tissue Massage</h3>
            <p>
                <img src="./Assets/Images/deep_tissue.jpg" alt="">
                Deep tissue massage is similar to therapeutic massage but it is suitable
                for breaking down scar or muscle tissue to increase blood circulation,
                carrying oxygen, and nutrients to the needed areas. I will apply the
                specific techniques and the right amount of pressure to address your
                needs.
            </p>
            <a href="reservation" class="button">SCHEDULE AN APPOINTMENT</a>

            <h3>Relaxation Massage</h3>
            <p>
                <img src="./Assets/Images/relaxation.jpg" alt="">
                Relaxation massage is a gentle massage which will help you let go of
                anxieties, promote flexibility, and stimulate repair in injury areas.
                Long slow massage strokes increase your circulation and reduce nerve
                stimulation. sedating your nervous system while resting your mind can
                give you the needed time to restore your better-self.
            </p>
            <a href="reservation" class="button">SCHEDULE AN APPOINTMENT</a>

            <h3>Lymphatic Massage</h3>
            <p>
                <img src="./Assets/Images/lymphatic.jpg" alt="">
                Lymphatic massage helps to relieve swollen limbs when medical treatment
                or illness blocks your lymphatic system. I would lightly stretch the
                skin in the direction that the lymph flows naturally which it stimulates
                the lymphatic vessels.
            </p>
            <a href="reservation" class="button">SCHEDULE AN APPOINTMENT</a>

            <h2>Special Massage Services</h2>
            <h3>Hot Stone Massage</h3>
            <p>
                <img src="./Assets/Images/hot_stone.jpg" alt="">
                Hot stone massage helps treating body aches and pain without deep
                pressure. Hot stones would treat muscle tension very efficiently and
                increase circulation. I will also guide hot stones over your body using
                massage oil to create relaxation and releasing tension throughout the
                body.
            </p>
            <a href="reservation" class="button">SCHEDULE AN APPOINTMENT</a>

            <h3>Cupping</h3>
            <p>
                <img src="./Assets/Images/cupping.jpg" alt="">
                Hot stone massage helps treating body aches and pain without deep
                pressure. Hot stones would treat muscle tension very efficiently and
                increase circulation. I will also guide hot stones over your body using
                massage oil to create relaxation and releasing tension throughout the
                body.
            </p>
            <a href="reservation" class="button">SCHEDULE AN APPOINTMENT</a>

            <h2>Other Services</h2>

            <h3>Teeth Whitening</h3>
            <p>
                <img src="./Assets/Images/teeth_whitening2.jpg" alt="">
                Teeth whitening improves the overall appearance of a stained,
                discoloured smile which you can receive the positive reactions. Using
                state-of-the-art whitening treatments, I can make your teeth brighter
                and whiter.
            </p>
            <a href="reservation" class="button">SCHEDULE AN APPOINTMENT</a>

            <h3>Facial Skin Treatment</h3>
            <p>
                <img src="./Assets/Images/facial2.jpg" alt="">
                Hot stone massage helps treating body aches and pain without deep
                pressure. Hot stones would treat muscle tension very efficiently and
                increase circulation. I will also guide hot stones over your body using
                massage oil to create relaxation and releasing tension throughout the
                body.
            </p>
            <a href="reservation" class="button">SCHEDULE AN APPOINTMENT</a>
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