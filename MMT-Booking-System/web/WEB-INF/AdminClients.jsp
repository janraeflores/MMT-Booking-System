<%-- 
    Document   : AdminClients
    Created on : 28-Mar-2023, 12:03:17 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Clients Page</title>

        <!--Stylesheet-->
        <link rel="stylesheet" type="text/css" href="Assets/Styles/AdminClients.css">
    </head>
    <header>

        <!--Menu button stuff-->
        <input type="checkbox" id="active">
        <label for="active" class="menu-btn"><span></span></label>
        <label for="active" class="close"></label>
        <div class="wrapper">
            <ul>
                <li><a href="admin">MAIN</a></li>
                <li><a href="availability">AVAILABILITY</a></li>
                <li><a href="clients">CLIENTS</a></li>
                <li><a href="login">LOGOUT</a></li>
            </ul>
        </div>
    </header>
    <body>
        <main>
            <div class="clientList">

                <div class="clientHead">
                    <h2 class="clients-head">clients</h2>
                </div>
                <!-- code to autopopulate all the clients in the database (probably use a foreach for every client in the database like 
                <c:foreach var="client" items="$/{clients}"> type vibe-->

                <!--hardcode for styling-->
                <button onclick="displayClient()" id="client1" class="client">Client1</button>
                <br>
                <button onclick="displayClient()" id="client2" class="client">Client2</button>
                <br>
                <button onclick="displayClient()" id="client3" class="client">Client3</button>
                <br>
                <button onclick="displayClient()" id="client4" class="client">Client4</button>
                <br>
                <button onclick="displayClient()" id="client5" class="client">Client5</button>
                <br>
                <button onclick="displayClient()" id="client6" class="client">Client6</button>
                <br>
                <button onclick="displayClient()" id="client7" class="client">Client7</button>
                <br>
                <button onclick="displayClient()" id="client8" class="client">Client8</button>
                <br>
                <button onclick="displayClient()" id="client9" class="client">Client9</button>
                <br>
                <button onclick="displayClient()" id="client10" class="client">Client10</button>
                <br>
                <button onclick="displayClient()" id="client11" class="client">Client11</button>
                <br>
                <button onclick="displayClient()" id="client12" class="client">Client12</button>
                <br>
                <button onclick="displayClient()" id="client13" class="client">Client13</button>
                <br>
                <button onclick="displayClient()" id="client14" class="client">Client14</button>
                <br>
                <button onclick="displayClient()" id="client15" class="client">Client15</button>
                <br>
                <button onclick="displayClient()" id="client16" class="client">Client16</button>
                <br>
                <button onclick="displayClient()" id="client17" class="client">Client17</button>
                <br>
                <button onclick="displayClient()" id="client18" class="client">Client18</button>
                <br>
                <button onclick="displayClient()" id="client19" class="client">Client19</button>
                <br>
                <button onclick="displayClient()" id="client20" class="client">Client20</button>
                <br>
            </div>


            <div class="clientInfo">
                <!--what will be shown if no client has been selected-->
                <!--            <h1>client information</h1>
                
                            <h2>No client selected</h2>
                -->
                <!--what to be shown if client is selected (for this i'm auto populating the name and random info)-->
                <h1>client information <button onclick='saveClient()' class="saveButt">save</button><button onclick='deleteClient()' class="deleteButt">delete</button></h1>

                <div class="info">
                    <!--replace the values of the inputs with like,,, $/{valueofthing} :)-->
                    <h3>NAME:</h3>
                    <input type="text" id="name" name="name" value="nameofperson" class="text"><br>
                    <h3>PHONE:</h3>
                    <input type="tel" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" id="phone" name="phone" value="000-000-0000" class="text"><br>
                    <h3>EMAIL:</h3>
                    <input type="email" id="email" name="email" value="kirbybcisaidso@nintendosmashbros.ca" class="text"><br>
                    <h3>ADDRESS:</h3>
                    <input type="text" id="address" name="address" value="Somewhere on rainbow road or something" class="text"><br>
                    <h3>BIRTHDAY:</h3>
                    <input type="date" id="birthday" name="birthday" value="02/29/2020"><br>
                    <h3>EMERGENCY CONTACT:</h3>
                    <div class="emergency">
                        <p>relation: <input type="text" id="contact" name="contact" value="Their mom frfr"></p>
                        <p>contact: <input type="tel" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" id="phone" name="phone" value="888-000-0000"></p>
                    </div>
                    <h3>MEDICAL INFORMATION:</h3>
                    <textarea id="medinfo" name="medinfo" class="textBig">According to all known laws of aviation, there is no way a bee should be able to fly. Its wings are too small to get its fat little body off the ground. The bee, of course, flies anyway because bees don't care what humans think is impossible. Yellow, black. Yellow, black. Yellow, black. Yellow, black. Ooh, black and yellow! Let's shake it up a little. Barry! Breakfast is ready! Ooming! Hang on a second. Hello? - Barry? - Adam? - Oan you believe this is happening? - I can't. I'll pick you up. Looking sharp. Use the stairs. Your father paid good money for those. Sorry. I'm excited. Here's the graduate. We're very proud of you, son. A perfect report card, all B's. Very proud. Ma! I got a thing going here. - You got lint on your fuzz. - Ow! That's me! - Wave to us! We'll be in row 118,000. - Bye! Barry, I told you, stop flying in the house! - Hey, Adam. - Hey, Barry. - Is that fuzz gel? - A little. Special day, graduation. Never thought I'd make it. Three days grade school, three days high school. Those were awkward. Three days college. I'm glad I took a day and hitchhiked around the hive. You did come back different</textarea><br>
                </div>
            </div>
        </main>
    </body>
</html>
